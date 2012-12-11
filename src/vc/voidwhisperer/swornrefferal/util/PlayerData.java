package vc.voidwhisperer.swornrefferal.util;

import org.bukkit.entity.Player;

public class PlayerData {
	private Player player;
	private int level;
	private long lastRefferalTime;
	private int refferalsInThePastTwentyFourHours;
	private long lastRewardTime;
	public PlayerData(Player p, int level,int refferals,long lrt,long lastRewardTime)
	{
		player = p;
		this.level = level;
		refferalsInThePastTwentyFourHours = refferals;
		lastRefferalTime = lrt;
		this.lastRewardTime = lastRewardTime;
	}
	public int getLevel()
	{
		return level;
	}
	public void incrementLevel(int amnt)
	{
		level = level + amnt;
	}
	public int getRefferalCount()
	{
		return refferalsInThePastTwentyFourHours;
	}
	public long getLastRewardTime()
	{
		return lastRewardTime;
	}
	public void addRefferalCount()
	{
		refferalsInThePastTwentyFourHours++;
	}
	public void clearRefferalCount()
	{
		refferalsInThePastTwentyFourHours = 0;
	}
	public long getLastReferralTime()
	{
		return lastRefferalTime;
	}
	public void setLastReferralTime(long lrf)
	{
		lastRefferalTime = lrf;
	}
	public void setLastRewardTime(long lrt)
	{
		lastRewardTime = lrt;
	}
	public Player getPlayer()
	{
		return player;
	}
}
