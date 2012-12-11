package vc.voidwhisperer.swornrefferal.listeners;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import vc.voidwhisperer.swornrefferal.main.SwornRefferal;
import vc.voidwhisperer.swornrefferal.util.PlayerDataCollection;
import vc.voidwhisperer.swornrefferal.util.RainbowColors;

public class CommandRefer implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] args) {
		if(args.length < 1)
		{
			arg0.sendMessage(RainbowColors.MsgToRainbow("You should try /refer [playername]!"));
			return true;
		}
		//String refferer = list.get(0);
		int refferals = PlayerDataCollection.get(Bukkit.getPlayer(arg0.getName())).getRefferalCount();
		int level = PlayerDataCollection.get(Bukkit.getPlayer(arg0.getName())).getLevel();
		double levelmodpre = level/5;
		BigDecimal levelmoda = new BigDecimal(levelmodpre);
		levelmoda.setScale(0, RoundingMode.DOWN);
		int levelmod = levelmoda.intValue()+1;
		if(refferals < levelmod)
		{
			//System.out.println(SwornRefferal.p.refferals.getFile().getConfigurationSection(args[0].toLowerCase()) == null);
			if(SwornRefferal.p.refferals.getFile().getList(args[0].toLowerCase()) == null)
			{
			//SwornRefferal.p.pstats.getFile().set(arg0.getName().toLowerCase()+".refferals", refferals+1);
			PlayerDataCollection.get(Bukkit.getPlayer(arg0.getName())).addRefferalCount();
			PlayerDataCollection.get(Bukkit.getPlayer(arg0.getName())).setLastReferralTime(System.currentTimeMillis());
		//	System.out.println(System.currentTimeMillis());
			PlayerDataCollection.saveData();
			SwornRefferal.p.refferals.getFile().createSection(args[0].toLowerCase());
			List<String> a = new ArrayList<String>();
			a.add(arg0.getName());
			SwornRefferal.p.refferals.getFile().set(args[0].toLowerCase(), a);
			SwornRefferal.p.refferals.saveFile();
			arg0.sendMessage(RainbowColors.MsgToRainbow("You've reffered "+args[0].toLowerCase()));
			return true;
			}else{
				arg0.sendMessage(RainbowColors.MsgToRainbow("That person is already reffered by someone."));
				return true;
			}	
		}else{
			arg0.sendMessage(RainbowColors.MsgToRainbow("You cannot refer any more people at this point in time."));
			return true;
		}
	}

}
