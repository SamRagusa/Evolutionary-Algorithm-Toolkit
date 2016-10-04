package geneticEngine;

/**
 * An abstract class that represents some characteristic of a Chromosome object.  
 *
 * @author Sam Ragusa
 * @param <V> An object to store (and manipulate if appropriate) whatever information
 * that the Gene object is supposed to store. 
 */
public abstract class Gene<V>
{
	/**
	 * The information that the Gene stores.
	 */
	public V info;
	
	/**
	 * A constructor to create a Gene containing random information.
	 */
	public Gene() 
	{
		setDataRandomly();
	}
	
	
	/**
	 * Gets and returns the instance variable info.
	 * 
	 * @return The object contained within the instance variable "info".
	 */
	public V getInfo()
	{
		return info;
	}
	
	
	/**
	 * Replace the information contained within the gene by new, random information.
	 */
	abstract public void mutate();

	
	/**
	 * Set the info contained within the Gene to random
	 * values.  This is used when initializing the population.
	 */
	abstract public void setDataRandomly();
	
	
	/**
	 * Gets and returns the String representation of the info contained within
	 * the Gene.
	 * 
	 * @return The String representation of the info instance variable.
	 */
	public String toString()
	{
		return info.toString();
	}
	
}
