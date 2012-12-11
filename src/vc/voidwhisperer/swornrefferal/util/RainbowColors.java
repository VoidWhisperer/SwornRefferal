package vc.voidwhisperer.swornrefferal.util;

import java.util.HashSet;

import org.bukkit.ChatColor;

public class RainbowColors {
	private static ChatColor[] colors = {
			ChatColor.RED,
			ChatColor.GOLD,
			ChatColor.YELLOW,
			ChatColor.GREEN,
			ChatColor.BLUE,
			ChatColor.DARK_PURPLE,
			ChatColor.LIGHT_PURPLE
	};
	public static ChatColor getColor(int index)
	{
		return colors[index];
	}
	public static String MsgToRainbow(String text)
	{
		int coloriterator = 0;
		String finalString = "";
		char[] split = text.toCharArray();
		for(char s : split)
		{
			if(coloriterator == 6)
			{
				coloriterator = 0;
			}
			finalString = finalString + getColor(coloriterator) + s;
			coloriterator++;
		}
		return finalString;
	}
}
