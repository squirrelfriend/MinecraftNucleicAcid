package net.minecraft.entity.monster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Nucleus {

//statics
	
	//random generator used by newSequence().
	private static Random random = new Random();
	
	//codons follow this
	static List<String> codonStart = new ArrayList<String>()
	{{
		add("1111");
	}};
	static List<String> codonMinine = new ArrayList<String>()
	{{
		add("0010");
		add("0011");
	}};
	static List<String> codonCraftine = new ArrayList<String>()
	{{
		add("0100");
		add("0101");
	}};
	static List<String> codonNetherine = new ArrayList<String>()
	{{
		add("0110");
		add("0111");
	}};
	static List<String> codonEndine = new ArrayList<String>()
	{{
		add("1000");
		add("1001");
	}};
	static List<String> codonCubine = new ArrayList<String>()
	{{
		add("1110");
		add("1111");
	}};
	
//private variables
	
	//The EntityGenetic which this Nucleus belongs to.
	private EntityGenetic theEntity;
	
	//The MNA sequence contained by this Nucleus (a binary string)
	private String theSequence;
	
	//The antiparallel MNA sequence
	private String antiSequence;
	
	private List<String> proteinList;
	
//constructors
	
	//Creates a new Nucleus instance with specified sequence.
	//@param entity: An EntityGenetic which will be set as theEntity.
	//@param sequence: A String of 1s and 0s which will be set as theSequence.
	public Nucleus(EntityGenetic entity, String sequence)
	{
		this.theEntity = entity;
		this.theSequence = sequence;
		this.readSequence();
	}
	

	//Creates a new Nucleus instance with a sequence of length 2048.
	//@param entity: The EntityGenetic to be set as theEntity.
	public Nucleus(EntityGenetic entity)
	{
		this(entity, 2048);
	}
	
	//Creates a new Nucleus instance with a sequence of 
	//@param entity: The EntityGenetic to be set as theEntity.
	//@param length: The length of the sequence to be generated
	public Nucleus(EntityGenetic entity, int length)
	{
		this(entity, newSequence(length));
	}
	
//public methods
	//called when theEntity is done with this Nucleus instance.
	public void detach()
	{
		this.theEntity = null;
	}
	
	//Returns the number of occurrences of proteinName in proteinList.
	//@param proteinName: The protein string to search for.
	public int proteinCount(String proteinName)
	{
		int count = 0;
		for(int searchPos = 0; searchPos <= this.proteinList.size(); searchPos++)
		{
			if(this.proteinList.get(searchPos).equals(proteinName))
			{
				count++;
			}
		}
		return count;
	}
	
	//The String containing the MNA nucleotide sequence.
	public String getSequence()
	{
		return this.theSequence;
	}
	
//private methods
	
	//
	private void readSequence()
	{
		this.proteinList = null;
		List<Integer> readLocations = this.getCodonLocations(this.codonStart);
		Iterator readIterator = readLocations.iterator();
		while(readIterator.hasNext())
		{
			this.readProtein((Integer) readIterator.next());
		}
	}
	
	private String readProtein(int startPos)
	{
		List<String> codonList = new ArrayList();
		int searchPos = 0;
		while(this.hasNextCodon(searchPos))
		{
			codonList.add(this.getSequence().substring(searchPos));
		}
		return this.theEntity.proteinFromCodons(codonList);
	}
	
	private boolean hasNextCodon(int searchPos)
	{
		if(this.getSequence().length() <= searchPos + 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private List<Integer> getCodonLocations(List<String> codonSequence)
	{
		List<Integer> locations = new ArrayList<Integer>();
		Iterator codonVariantIterator = codonSequence.iterator();
		while(codonVariantIterator.hasNext())
		{
			String searchSequence = (String) codonVariantIterator.next();
			int searchPos = 0;
			while(this.getSequence().indexOf(searchSequence,searchPos) != -1)
			{
				locations.add(this.getSequence().indexOf(searchSequence,searchPos));
				searchPos = this.getSequence().indexOf(searchSequence,searchPos) + 1;
			}
		}
		return locations;
	}
	
//static methods
	
	//Returns a String of 1s and 0s.
	//@param length: The length of the String to be returned.
	private static String newSequence(int length)
	{
		String[] sequence = new String[length];
		for(int seqPos = 0; seqPos <= sequence.length; seqPos++)
		{
			if(random.nextBoolean())
			{
				sequence[seqPos] = "1";
			}
			else
			{
				sequence[seqPos] = "0";
			}
		}
		return sequence.toString();
	}
}
