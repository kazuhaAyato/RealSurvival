package com.outlook.schooluniformsama.task;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.outlook.schooluniformsama.RealSurvival;
import com.outlook.schooluniformsama.data.Data;
import com.outlook.schooluniformsama.data.item.ItemLoreData;
import com.outlook.schooluniformsama.data.player.PlayerData;
import com.outlook.schooluniformsama.util.Msg;

public class WeightTask implements Runnable{
	@Override
	public void run() {
		for(PlayerData pd:Data.playerData.values()){
			Player p = RealSurvival.getPlayer(pd.getUuid());
			if(p==null || pd==null || p.isDead())return;
			double weight=0;
			for(ItemStack is:p.getInventory().getContents()){
				if(is==null)continue;
				if(is.hasItemMeta()&&is.getItemMeta().hasLore()){
					double temp=ItemLoreData.getLore("Weight", is.getItemMeta().getLore(), false);
					if(temp!=-1.1111111)
						weight+=temp*is.getAmount();
					else if(Data.itemData.containsKey(is.getType().name()))
						weight+=Data.itemData.get(is.getType().name()).getWeight()*is.getAmount();
				}else if(Data.itemData.containsKey(is.getType().name()))
					weight+=Data.itemData.get(is.getType().name()).getWeight()*is.getAmount();
			}
			Msg.sendRandomTitleToPlayer(p, pd.getWeight().setWeight(weight),true);
		}
	}
}
