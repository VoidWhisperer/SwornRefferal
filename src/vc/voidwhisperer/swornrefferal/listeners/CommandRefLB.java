package vc.voidwhisperer.swornrefferal.listeners;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import vc.voidwhisperer.swornrefferal.main.SwornRefferal;
import vc.voidwhisperer.swornrefferal.util.RainbowColors;

public class CommandRefLB implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		HashMap<String,Integer> playerstats = new HashMap<String,Integer>();
		ValueComparator bvc =  new ValueComparator(playerstats);
		for(String s : SwornRefferal.p.pstats.getFile().getKeys(false))
		{
			playerstats.put(s, SwornRefferal.p.pstats.getFile().getInt(s+".level"));
		}
		 TreeMap<String,Integer> sorted_playerstats = new TreeMap<String,Integer>(bvc);
			sorted_playerstats.putAll(playerstats);
		NavigableMap<String,Integer> sorted_playerstats2 = sorted_playerstats.descendingMap();
		int itr = 1;
		
		arg0.sendMessage(RainbowColors.MsgToRainbow("Top 10 refferers:"));
		for(Entry<String, Integer> e : sorted_playerstats2.entrySet())
		{
			if(itr <= 10)
			{
			arg0.sendMessage(RainbowColors.MsgToRainbow(e.getKey() + ": " + e.getValue()));
			itr++;
			}else{
			return true;
			}
		}
		return true;
	}
}
class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(HashMap<String, Integer> playerstats) {
        this.base = playerstats;
    }

    public int compare(String a, String b) {
        return base.get(a).compareTo(base.get(b));
    }
}

