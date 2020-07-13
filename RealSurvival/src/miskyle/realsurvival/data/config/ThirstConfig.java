package miskyle.realsurvival.data.config;

import java.util.ArrayList;
import java.util.HashMap;

import miskyle.realsurvival.data.effect.EffectData;
import miskyle.realsurvival.data.item.RSItem;
import miskyle.realsurvival.util.RSEntry;

public class ThirstConfig {
	private boolean enable;
	private double  maxValue;
	private double  decreaseValue;
	
	
	private HashMap<RSEntry<Double, Double>, ArrayList<EffectData>> effectData;
	private HashMap<String, String> biomeWater;
	
	public ThirstConfig() {
		
	}
	
	public ThirstConfig(boolean enable, double maxValue, double decreaseValue, 
			HashMap<String, String> effectData,HashMap<String, String> biomeWater) {
		super();
		this.enable = enable;
		this.maxValue = maxValue;
		this.decreaseValue = decreaseValue;
		this.biomeWater = biomeWater;
		this.effectData = new HashMap<RSEntry<Double,Double>, ArrayList<EffectData>>();
		effectData.forEach((s1,s2)->{
			String[] temp = s1.split("-");
			String[] temp2 = s2.split(";");
			ArrayList<EffectData> list = new ArrayList<>();
			for(String s:temp2)
				list.add(EffectData.loadFromString(s));
			this.effectData.put(
					new RSEntry<Double, Double>(
							Double.parseDouble(temp[0]), Double.parseDouble(temp[1])), list);
		});
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getDecreaseValue() {
		return decreaseValue;
	}

	public void setDecreaseValue(double decreaseValue) {
		this.decreaseValue = decreaseValue;
	}

	public HashMap<RSEntry<Double, Double>, ArrayList<EffectData>> getEffectData() {
		return effectData;
	}

	public void setEffectData(HashMap<String, String> effectData) {
		this.effectData = new HashMap<RSEntry<Double,Double>, ArrayList<EffectData>>();
		effectData.forEach((s1,s2)->{
			String[] temp = s1.split("-");
			String[] temp2 = s2.split(";");
			ArrayList<EffectData> list = new ArrayList<>();
			for(String s:temp2)
				list.add(EffectData.loadFromString(s));
			this.effectData.put(
					new RSEntry<Double, Double>(
							Double.parseDouble(temp[0]), Double.parseDouble(temp[1])), list);
		});
	}
	
	public RSItem getWater(String biome) {
		if(biomeWater.containsKey(biome))
			return RSItem.load("/water/"+biomeWater.get(biome));
		else
			return RSItem.load("/water/"+biomeWater.get("UNKNOWN"));
	}
	
	
}
