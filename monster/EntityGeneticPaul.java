package net.minecraft.entity.monster;

import net.minecraft.world.World;

public class EntityGeneticPaul extends EntityGenetic
{
//private variables
	
	//the current nutrient value of this EntityGeneticPaul
	private double nutrition;
	
//constructors
	
	//Creates a new EntityGeneticPaul instance.
	//@param par1World: The World to spawn the EntityGeneticPaul in.
	public EntityGeneticPaul(World par1World)
	{
		super(par1World);
	}
	
//public methods
	
	//Returns nutrition.
	public double getNutrition()
	{
		return this.nutrition;
	}
	
	public void eatPlant(String blockString)
	{
		this.nutrition = this.nutrition + (this.getPlantNutrition(blockString) * this.getHerbiscore());
	}
	
	public int getHerbiscore()
	{
		return this.getNucleus().proteinCount("Herbiscore");
	}
	
	public int getCarniscore()
	{
		return this.getNucleus().proteinCount("Carniscore");
	}
	
	private int getPlantNutrition(String blockString) {
		return 0;
	}
	private int getAnimalNutrition(String EntityString)
	{
		return 0;
	}
	public void eatMeat(String entityString)
	{
		this.nutrition = this.nutrition + (this.getAnimalNutrition(entityString) * this.getCarniscore());
	}
}
