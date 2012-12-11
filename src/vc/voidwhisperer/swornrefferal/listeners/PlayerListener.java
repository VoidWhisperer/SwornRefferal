package vc.voidwhisperer.swornrefferal.listeners;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.inventory.ItemStack;

import vc.voidwhisperer.swornrefferal.main.SwornRefferal;
import vc.voidwhisperer.swornrefferal.util.GetRewards;
import vc.voidwhisperer.swornrefferal.util.PlayerData;
import vc.voidwhisperer.swornrefferal.util.PlayerDataCollection;
import vc.voidwhisperer.swornrefferal.util.RainbowColors;
/*Refferal yml data:
 * [Name reffered]:
 *  - Player whom reffered them
 *  
 *  Eg:
 *  
 *  voidwhisperer:
 *   - trollslayer
 */
/*Pstats yml data:
 * [Name (to lowercase)]
 *  level: [level]
 *  
 *  
 *  Eg:
 *  
 *  voidwhisperer:
 *   level: 9001
 *   refferals: 1
 */
public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerPreLogin(PlayerPreLoginEvent e)
	{
		SwornRefferal plugin = SwornRefferal.p;
		OfflinePlayer p = Bukkit.getOfflinePlayer(e.getName());
		if(!p.hasPlayedBefore())
		{
			for(String s : plugin.refferals.getFile().getKeys(false))
			{
			}
			if(plugin.refferals.getFile().getList(e.getName().toLowerCase()) != null)
			{
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) plugin.refferals.getFile().getList(e.getName().toLowerCase());
				String name = p.getName();
				String refferer = list.get(0);
				PlayerDataCollection.get(Bukkit.getPlayer(refferer)).incrementLevel(1);
				int level = PlayerDataCollection.get(Bukkit.getPlayer(refferer)).getLevel();
				Set<ItemStack> rewards = GetRewards.getRewards(level);
				for(ItemStack reward:rewards)
				{
					Bukkit.getPlayer(refferer).getInventory().addItem(reward);
				}
				String rewardtext = "";
				if(rewards.size() > 1)
				{
					rewardtext = "Some enchanted iron armour";
				}else if(((ItemStack)rewards.toArray()[0]).getTypeId() == 276)
				{
					rewardtext = "An enchanted diamond sword.";
				}else{
					rewardtext = ((ItemStack)rewards.toArray()[0]).getAmount() + "x " + ((ItemStack)rewards.toArray()[0]).getType().name().toLowerCase();
				}
				broadcast(RainbowColors.MsgToRainbow(name + " has been reffered by "+refferer+". "+refferer+" has advanced a recruitment level to level "+ (level) +". For this they get: " + rewardtext));
				Bukkit.getLogger().info(name + " has been reffered by "+refferer+". "+refferer+" has advanced a recruitment level to level "+ (level) +". For this they get: " + rewardtext);
				PlayerDataCollection.saveData();
				SwornRefferal.swordToThee.add(e.getName());
				plugin.refferals.getFile().set(e.getName().toLowerCase(),null);
				plugin.refferals.saveFile();
			}
		}
	}
	/*public void onPlayerJoin(PlayerJoinEvent e)
	{
		
	}*/
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
	//	System.out.println("TEST");
		if(PlayerDataCollection.get(e.getPlayer()) == null)
		{
	//	System.out.println("TEST2");
		if(SwornRefferal.p.pstats.getFile().getConfigurationSection(e.getPlayer().getName().toLowerCase()) != null)
		{
	//		System.out.println("TEST3");	
		int level = SwornRefferal.p.pstats.getFile().getInt(e.getPlayer().getName().toLowerCase()+".level");
		System.out.println(SwornRefferal.p.pstats.getFile().getInt(e.getPlayer().getName().toLowerCase()+".refferals"));
		int refferal = SwornRefferal.p.pstats.getFile().getInt(e.getPlayer().getName().toLowerCase()+".refferals");
		long lrt = SwornRefferal.p.pstats.getFile().getLong(e.getPlayer().getName().toLowerCase()+".lrt");
		long lastRewardTime = SwornRefferal.p.pstats.getFile().getLong(e.getPlayer().getName().toLowerCase()+".lastRewardTime");
		PlayerData newp = new PlayerData(e.getPlayer(),level,refferal,lrt,lastRewardTime);
		PlayerDataCollection.pds.add(newp);
		}else{
		//	System.out.println("TEST4");
		PlayerData newp = new PlayerData(e.getPlayer(),1,0,0,0);
		PlayerDataCollection.pds.add(newp);
		PlayerDataCollection.saveData();
		}
		}
		PlayerData p = PlayerDataCollection.get(e.getPlayer());
		if(p.getLastRewardTime() + 86400000 < System.currentTimeMillis())
		{
			
		}
		if(SwornRefferal.swordToThee.contains(e.getPlayer().getName()))
		{
			ItemStack a = new ItemStack(267,1);
			a.addEnchantment(Enchantment.DAMAGE_ALL, 4);
			e.getPlayer().getInventory().addItem(a);
			SwornRefferal.swordToThee.remove(e.getPlayer().getName());
		}
	}
	public void broadcast(String msg)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(msg);
		}
	}
}
