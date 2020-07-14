package miskyle.realsurvival.data.playerdata;

import miskyle.realsurvival.data.ConfigManager;
import miskyle.realsurvival.util.RSEntry;

public class PlayerDataSleep extends PlayerDataStatus{
	
	private boolean sleep = false;
	
	public PlayerDataSleep() {
		
	}
	
	public PlayerDataSleep(double value) {
		super(value);
	}
	
	/**
	 * 修改属性值,若为加则数值为正/若为减则数值为负
	 * @param value
	 * @return 属性的原始值(左)及新值(右)
	 */
	@Override
	public RSEntry<Double, Double> modify(double value){
		double oldValue = this.value;
		this.value+=value;
		double max = getMaxValue();
		if(super.value<0)
			super.value = 0;
		else if(super.value > max) 
			super.value = max;
		return new RSEntry<Double, Double>(oldValue,this.value);
	}
	
	/**
	 * 获取Sleep最大属性值
	 * @return
	 */
	public double getMaxValue() {
		return super.getExtraValueSum()+ConfigManager.getSleepConfig().getMaxValue();
	}
	
	/**
	 * 取最大属性值的比例,p应属于0~100
	 * @param p
	 * @return
	 */
	public double getMaxValue(double p) {
		return getMaxValue()*p/100;
	}

	/**
	 * 玩家是否在RealSurvival意义上睡着
	 * @return
	 */
	public boolean isSleep() {
		return sleep;
	}

	/**
	 * 设定玩家在RealSurvival意义上睡着
	 * @param sleep
	 */
	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}
	
	
	
}
