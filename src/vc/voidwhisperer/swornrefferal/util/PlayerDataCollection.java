package vc.voidwhisperer.swornrefferal.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import vc.voidwhisperer.swornrefferal.main.SwornRefferal;

public class PlayerDataCollection {
	public static List<PlayerData> pds = new ArrayList<PlayerData>(); 
	public static void saveData()
	{
		for(PlayerData pd : pds)
		{
			if(!(SwornRefferal.p.pstats.getFile().getConfigurationSection(pd.getPlayer().getName()) != null))
			{
				SwornRefferal.p.pstats.getFile().createSection(pd.getPlayer().getName().toLowerCase());
			}
			SwornRefferal.p.pstats.getFile().set(pd.getPlayer().getName().toLowerCase()+".level", pd.getLevel());
			SwornRefferal.p.pstats.getFile().set(pd.getPlayer().getName().toLowerCase()+".refferals", pd.getRefferalCount());
			SwornRefferal.p.pstats.getFile().set(pd.getPlayer().getName().toLowerCase()+".lrt", pd.getLastReferralTime());
		}
		SwornRefferal.p.pstats.saveFile();
	}
	public static PlayerData get(Player p)
	{
		for(PlayerData pd : pds)
		{
			if(pd.getPlayer().getName().equalsIgnoreCase(p.getName()))
			{
				return pd;
			}
		}
		return null;
	}
}
