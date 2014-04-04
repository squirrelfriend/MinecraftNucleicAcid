package net.minecraft.entity.monster;

//Imported for extension.
import java.util.List;

import net.minecraft.entity.EntityCreature;

//Imported for implementation.
import net.minecraft.entity.passive.IAnimals;

//Imported for construction parameter.
import net.minecraft.world.World;

//This class is responsible for providing a platform which can be extended by different entities.
public abstract class EntityGenetic extends EntityCreature implements IAnimals
{
	
//private variables

	//A Nucleus which contains the MNA sequence and protein counts for this EntityGenetic.
	private Nucleus theNucleus;
	
//constructors
	
	//Creates a new EntityGenetic instance.
	//@param par1World: The World to spawn the EntityGenetic in.
    public EntityGenetic(World par1World)
    {
        super(par1World);
        this.createNucleus();
    }

//public methods
    
    //returns theNucleus.
    public Nucleus getNucleus()
    {
    	return this.theNucleus;
    }
    
    //The time (in ticks) in which this EntityGenetic cannot speak again.
    //from EntityGolem.class
    public int getTalkInterval()
    {
        return 120;
    }
    
    //
    public void setNucleus(Nucleus nucleus)
    {
    	if(this.getNucleus() != null)
    	{
    		this.getNucleus().detach();
    	}
    	this.theNucleus = nucleus;
    }
    
    public String proteinFromCodons(List<String> codonList)
    {
    	return "NONE";
    }
    
//private methods
    
    //creates a new Nucleus; in EntityGenetic.class this means "new Nucleus(this,"0")".
    private void createNucleus()
    {
    	this.setNucleus(new Nucleus(this, "0"));
    }
    
//protected methods
    
    //the name of the sound file this EntityGenetic uses at random intervals.
    //from EntityGolem.class
    protected String getLivingSound()
    {
        return "none";
    }

    //The name of the sound file this EntityGenetic uses when it is hurt.
    //from EntityGolem.class
    protected String getHurtSound()
    {
        return "none";
    }
    
    //The name of the sound file this EntityGenetic uses when it dies.
    //from EntityGolem.class
    protected String getDeathSound()
    {
        return "none";
    }

    //If this EntityGenetic can despawn.
    protected boolean canDespawn()
    {
        return false;
    }
}
