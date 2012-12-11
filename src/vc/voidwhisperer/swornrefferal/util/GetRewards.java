package vc.voidwhisperer.swornrefferal.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class GetRewards {
	public static Set<ItemStack>getRewards(int level)
	{
		double levelmodpre = level/5;
		BigDecimal levelmoda = new BigDecimal(levelmodpre);
		levelmoda.setScale(0, RoundingMode.DOWN);
		int levelmod = levelmoda.intValue()+1;
		int itemamnt = 30 + (2 * levelmod);
		boolean armour = (level%5==0) && (level%10!=0);
		boolean weapon = (level%10==0);
		Set<ItemStack> rewards = new HashSet<ItemStack>();
		if(!armour || !weapon)
		{
			int digit = getLastDigit(level);
			switch(digit)
			{
				case 1:
				{
					rewards.add(new ItemStack(49,itemamnt));
					return rewards;
				}
				case 2:
				{
					rewards.add(new ItemStack(266,itemamnt));
					return rewards;
				}
				case 3:
				{
					rewards.add(new ItemStack(384,itemamnt));
					return rewards;
				}
				case 4:
				{
					rewards.add(new ItemStack(264,itemamnt));
					return rewards;
				}
				case 6:
				{
					rewards.add(new ItemStack(49,itemamnt));
					return rewards;
				}
				case 7:
				{
					rewards.add(new ItemStack(266,itemamnt));
					return rewards;
				}
				case 8:
				{
					rewards.add(new ItemStack(384,itemamnt));
					return rewards;
				}
				case 9:
				{
					rewards.add(new ItemStack(264,itemamnt));
					return rewards;
				}
			}
		}else{
			if(weapon)
			{
				int eclevel = level/10;
				if(eclevel > 4)
				{
					eclevel = 4;
				}
				ItemStack sword = new ItemStack(276,1);
				sword.addEnchantment(Enchantment.getById(16), eclevel);
				sword.addEnchantment(Enchantment.getById(17), eclevel);
				sword.addEnchantment(Enchantment.getById(19), eclevel);
				sword.addEnchantment(Enchantment.getById(20), eclevel);
				rewards.add(sword);
				return rewards;
			}
			if(armour)
			{
				int eclevel = 0;
				//because i can't think of a simple way to do this...
				//Helmet 306
				//Chest 307
				//legs 308
				//boots 309
				if(level == 5)
				{
					eclevel = 1;
				}
				if(level == 15)
				{
					eclevel = 2;
				}
				if(level == 25)
				{
					eclevel = 3;
				}
				if(level >= 35)
				{
					eclevel = 4;
				}
				ItemStack helm = new ItemStack(306,1);
				ItemStack chest = new ItemStack(307,1);
				ItemStack legs = new ItemStack(308,1);
				ItemStack boots = new ItemStack(309,1);
				chest.addEnchantment(Enchantment.getById(0), eclevel);
				legs.addEnchantment(Enchantment.getById(1), eclevel);
				boots.addEnchantment(Enchantment.getById(0), eclevel);
				helm.addEnchantment(Enchantment.getById(4), eclevel);
				rewards.add(helm);
				rewards.add(chest);
				rewards.add(legs);
				rewards.add(boots);
				return rewards;
			}
		}
		return rewards;
	}
	public boolean isOdd(int num)
	{
		return (num%2==0);
	}
	public static int getLastDigit(int num)
	{
		return num % 10;
	}
}
