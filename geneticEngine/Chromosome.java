package geneticEngine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * A class to represent an array of characteristics, which are stored as Gene objects. 
 * 
 * @author Sam Ragusa
 */
public class Chromosome 
{
	/**
	 * A random object so that random Gene objects can be selected from the instance variable genes.
	 */
	Random rand = new Random();
	
	/**
	 * An array of Gene objects that represent the characteristics of the Chromosome. 
	 */
	public Gene[] genes;
	
	
	/**
	 * A constructor that takes in a Chromosome, and creates a copy of it.
	 * 
	 * @param toCopy The Chromosome to copy.
	 */
	public Chromosome(Chromosome toCopy)
	{
		this.genes = toCopy.genes;
	}
	
	
	/**
	 * A Chromosome Constructor that takes in an array of genes, and creates a chromosome
	 * with those genes.  This constructor is mainly used when creating crossover functions.
	 * 
	 * @param genes An array of Genes that the Chromosome being created to have.
	 */
	public Chromosome(Gene[] genes)
	{
		this.genes = genes;
	}
	
	
	/**
	 * A constructor for a Chromosome which takes in an array of Class objects, gets
	 * their constructor, and fills it's Gene array with objects it creates from those constructors.  
	 * 
	 * @param geneticStructure An array of Class objects, each of which represents
	 * a gene to be had in the Chromosome being created. 
	 */
	public Chromosome(Class[] geneticStructure)
	{
		genes = new Gene[geneticStructure.length];
		Constructor construct;
		for(int j=0; j < geneticStructure.length; j++)
		{
			try
			{
				construct = geneticStructure[j].getConstructor();
				genes[j] = (Gene) construct.newInstance();
			}
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
			{
				//Should probably handle these in a much better way
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	
	/**
	 * A method to get the String representation of the Chromosome.
	 * The String representation is [Gene1Info , Gene2Info , ... , GeneNInfo].
	 */
	public String toString()
	{
		String answer = "[";
		for(int j=0; j<genes.length; j++)
			answer += genes[j].toString() + " , ";
		return answer.substring(0,answer.length()-3) + "]";
	}
	
	
	/**
	 * Gets the number of Genes within the Chromosome. This is for use
	 * in implementations of the abstract class God.
	 * 
	 * @return The number of Genes within the Chromosome as an int.
	 */
	public int numGenes()
	{
		return genes.length;
	}
	
	
	/**
	 * Gets a Gene from the Chromosome at the requested index
	 * 
	 * @param index The index of the Gene to get
	 * @return The gene at the given index
	 */
	public Gene getGene(int index)
	{
		return genes[index];
	}
	
	
	/**
	 * Mutates the Chromosome by mutating one randomly selected Gene
	 * contained within this Chromosome. 
	 */
	public void mutate()
	{
		genes[rand.nextInt(genes.length)].mutate();
	}
}
