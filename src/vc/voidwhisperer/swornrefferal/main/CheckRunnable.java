package vc.voidwhisperer.swornrefferal.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import vc.voidwhisperer.swornrefferal.util.PlayerDataCollection;

public class CheckRunnable implements Runnable {

	@Override
	public void run() {
	for(Player p : Bukkit.getOnlinePlayers())
	{
		long lasttimereffered = PlayerDataCollection.get(p).getLastReferralTime();
		if(lasttimereffered + 86400000 <= System.currentTimeMillis())
		{
		PlayerDataCollection.get(p).clearRefferalCount();
		PlayerDataCollection.saveData();
		}
	}
	}
}
