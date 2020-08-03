package miskyle.realsurvival.data.recipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import com.github.miskyle.mcpt.MCPT;

import miskyle.realsurvival.data.item.RSItem;

public class FurnaceRecipe extends Recipe{
  private double maxTemperature;
  private double minTemperature;
  
  public FurnaceRecipe(String recipeName, String machineName, String materialShape, int forgeTime,
      HashMap<Character, ItemStack> materials, ArrayList<ItemStack> products,
      double maxTemperature,double minTemperature) {
    super(recipeName, machineName, materialShape, RecipeType.FURNACE, forgeTime, materials, products);
    this.minTemperature = minTemperature;
    this.maxTemperature = maxTemperature;
  }

  public static FurnaceRecipe loadRecipe(File file) {
    if(!file.exists()) {
      return null;
    }
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    String recipeName = config.getString("recipe-name");
    String machineName = config.getString("machine-name");
    String materialShape = config.getString("material-shape",null);
    double maxTemperature = config.getDouble("max-temperature");
    double minTemperature = config.getDouble("min-temperature");
    if(materialShape == null) {
      return null;
    }
    int forgeTime = config.getInt("forge-time");
    HashMap<Character, ItemStack> materials = new HashMap<>();
    for(char c:materialShape.toCharArray()) {
      if(config.contains("materials."+c)) {
        materials.put(c, new RSItem(config.getItemStack("materials."+c)).getItem());
      }
    }
    ArrayList<ItemStack> products = new ArrayList<>();
    int index = 0;
    while(config.contains("products.item-"+index)) {
      products.add(new RSItem(config.getItemStack("products.item-"+index++)).getItem());
    }
    return new FurnaceRecipe(recipeName, machineName, 
        materialShape, forgeTime, materials, products,maxTemperature,minTemperature);
  }
  
  public void save(String fileName) {
    save(new File(MCPT.plugin.getDataFolder()+"/recipe/furnace/"+fileName+".yml"));
  }
  
  public void save(File file) {
    file.getParentFile().mkdirs();
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    config.set("recipe-name", getRecipeName());
    config.set("machine-name", getMachineName());
    config.set("material-shape", getMaterialShape());
    config.set("forge-time", getForgeTime());
    config.set("max-temperature", maxTemperature);
    config.set("min-temperature", minTemperature);
    getMaterials().forEach((k,v)->{
      config.set("materials."+k, new RSItem(v).getSaveItem());
    });
    int index = 0;
    for(ItemStack item : getProducts()) {
      config.set("products.item-"+index++, new RSItem(item).getSaveItem());
    }
    try {
      config.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public double getMaxTemperature() {
    return maxTemperature;
  }

  public void setMaxTemperature(double maxTemperature) {
    this.maxTemperature = maxTemperature;
  }

  public double getMinTemperature() {
    return minTemperature;
  }

  public void setMinTemperature(double minTemperature) {
    this.minTemperature = minTemperature;
  }
  
}