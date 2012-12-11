package vc.voidwhisperer.swornrefferal.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import vc.voidwhisperer.swornrefferal.listeners.CommandRefLB;
import vc.voidwhisperer.swornrefferal.listeners.CommandRefer;
import vc.voidwhisperer.swornrefferal.listeners.PlayerListener;
import vc.voidwhisperer.swornrefferal.util.FileAccessor;
import vc.voidwhisperer.swornrefferal.util.PlayerData;
import vc.voidwhisperer.swornrefferal.util.PlayerDataCollection;

public class SwornRefferal extends JavaPlugin {
	public FileAccessor refferals;
	public FileAccessor pstats;
	public static SwornRefferal p;
	public static List<String> swordToThee = new ArrayList<String>();
	public void onEnable()
	{
		p = this;
		Bukkit.getLogger().info("SwornRefferal enabled!");
		refferals = new FileAccessor(this, "refferals.yml");
		pstats = new FileAccessor(this, "pstats.yml");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new CheckRunnable(), 1200L, 1200L);
		getCommand("refer").setExecutor(new CommandRefer());
		getCommand("reflb").setExecutor(new CommandRefLB());
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		/* I seem to be loading data incorrectly here..
		 * reload --> Tick, it resets
		 * :(
		 */
		for(Player p : Bukkit.getOnlinePlayers())
		{
			//System.out.println(SwornRefferal.p.pstats.getFile().getConfigurationSection(p.getName().toLowerCase()) == null);
			if(SwornRefferal.p.pstats.getFile().getConfigurationSection(p.getName().toLowerCase()) != null)
			{
			int level = SwornRefferal.p.pstats.getFile().getInt(p.getName().toLowerCase()+".level");
			int refferal = SwornRefferal.p.pstats.getFile().getInt(p.getName().toLowerCase()+".refferals");
			long lrt = SwornRefferal.p.pstats.getFile().getLong(p.getName().toLowerCase()+".lrt");
			long lastRewardTime = SwornRefferal.p.pstats.getFile().getLong(p.getName().toLowerCase()+".lastRewardTime");
			//System.out.println(level + "--" + refferal + "---" + lrt);
			PlayerData newp = new PlayerData(p,level,refferal,lrt,lastRewardTime);
			PlayerDataCollection.pds.add(newp);
			}else{
			PlayerData newp = new PlayerData(p,1,0,0,0);
			PlayerDataCollection.pds.add(newp);
			PlayerDataCollection.saveData();
			}
		}
	}
}
