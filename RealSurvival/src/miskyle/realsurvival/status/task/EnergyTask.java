package miskyle.realsurvival.status.task;

import org.bukkit.entity.Player;

import com.github.miskyle.mcpt.MCPT;

import miskyle.realsurvival.Msg;
import miskyle.realsurvival.data.ConfigManager;
import miskyle.realsurvival.data.EffectManager;
import miskyle.realsurvival.data.PlayerManager;
import miskyle.realsurvival.util.RSEntry;

public class EnergyTask implements Runnable{

	@Override
	public void run() {
		PlayerManager.getActivePlayers().forEachValue(
				PlayerManager.getActivePlayers().mappingCount(), pd->{
					Player p = MCPT.plugin.getServer().getPlayer(pd.getPlayerName());
					RSEntry<Double, Double> values;
					if(ConfigManager.getEnergyConfig().getDecreaseSneaking()!=0
							&& p.isSneaking()) {
						values = pd.getEnergy().modify(
								-ConfigManager.getEnergyConfig().getDecreaseSneaking());
					}else if(ConfigManager.getEnergyConfig().getDecreaseSprinting()!=0
							&&p.isSprinting()) {
						values = pd.getEnergy().modify(
								-ConfigManager.getEnergyConfig().getDecreaseSprinting());
					}else {
						values = pd.getEnergy().modify(
								ConfigManager.getEnergyConfig().getIncreaseValue());
					}
					double max = pd.getEnergy().getMaxValue();
					values.set(values.getLeft()*100/max, values.getRight()*100/max);
					attachEffect(p, pd.getEnergy().getValue()*100/max);
				});
		
	}
	
	public static void sendMessage(Player p,RSEntry<Double, Double> values) {
		for(RSEntry<Double, Double> entry: ConfigManager.getEnergyConfig().getEffectData().keySet()) {
			if(entry.getRight()>=entry.getLeft()
					&&values.getLeft()<=entry.getLeft()
					&& values.getRight()>entry.getLeft()
					&& values.getRight()<=entry.getRight()) {
				PlayerManager.bar.sendActionBar(
						p, Msg.tr("message.energy."+entry.getLeft()+"-"+entry.getRight()));
				break;
			}else if(values.getLeft()>entry.getLeft()
					&& values.getRight()<=entry.getLeft()
					&& values.getRight()>=entry.getRight()) {
				PlayerManager.bar.sendActionBar(
						p, Msg.tr("message.energy."+entry.getLeft()+"-"+entry.getRight()));
				break;
			}
		}
	}
	
	public static void attachEffect(Player p,double value) {
		for(RSEntry<Double, Double> entry: ConfigManager.getEnergyConfig().getEffectData().keySet()) {
			if(value<Math.max(entry.getLeft(), entry.getRight())
					&& value>=Math.min(entry.getLeft(), entry.getRight())) {
				ConfigManager.getEnergyConfig().getEffectData().get(entry).forEach(e->{
					EffectManager.effectPlayer(p, e);
				});
				return;
			}
		}
	}

}