package geneticEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class to represent an array of Double values,
 * each which is bounded (not necessarily by the same bounds).  This Gene,
 * though it can be used for other things, was designed for the purpose
 * of having each of the Double values in the instance variable "info"
 * represent a weight associated with the information contained in some
 * other Gene Object in a Chromosome object.
 * 
 * 
 * @author Sam Ragusa
 */
public abstract class WeightArrayGene extends Gene<List<Double>> 
{
	
	/**
	 * An array of values each of which representing the maximum weight that the value in 
	 * the instance variable "info" at the same index can have.
	 */
	public double[] maxWeights;
	
	/**
	 * An array of values each of which representing the minimum  weight that the value in 
	 * the instance variable "info" at the same index can have.
	 */
	public double[] minWeights; 

	
	/**
	 * A Constructor that calls the method setDataRandomly() (through super();).
	 */
	public WeightArrayGene()
	{
		super();
	}

	/**
	 * A constructor that sets the instance variable "info" to a given
	 * List of Double objects, and constructs and sets the values of the instance
	 * variables "maxWeights" and "minWeights"
	 * 
	 * @param weights The List of Double objects to set the instance variable "info" to
	 */
	public WeightArrayGene(List<Double> weights)
	{
		this.info = weights;
		maxWeights = new double[info.size()];
		minWeights = new double[info.size()];
		setMaxMinWeights();
	}
	
	/**
	 * Randomly sets the value for each of the values in the instance
	 * variable "info" within their defined bounds.
	 */
	public void mutate()
	{
		for(int j=0; j < info.size(); j++)
			info.set(j, minWeights[j] + rand.nextDouble() * (maxWeights[j] - minWeights[j]));
	}

	/**
	 * Constructs and sets the values of the instance variables "maxWeights", "minWeights"
	 * and "info".  The values in "maxWeights" and "minWeights" will be set using the method
	 * setMaxMinWeights(), and the values in "info" will be set randomly between the bounds
	 * given in "maxWeights" and "minWeights".
	 */
	public void setDataRandomly()
	{
		info = new ArrayList<Double>();
		int numWeights = numWeightsNeeded();
		maxWeights = new double[numWeights];
		minWeights = new double[numWeights];
		setMaxMinWeights();
		for(int j=0; j < numWeights; j++)
			info.add(minWeights[j] + rand.nextDouble() * (maxWeights[j] - minWeights[j]));
	}
	
	/**
	 * Gets and returns the value in the instance variable "info" at the desired index.
	 * 
	 * @param index The index of the value in the weight array to get
	 * @return The value in the weight array at the requested index
	 */
	public double getWeight(int index)
	{
		return info.get(index);
	}
	
	/**
	 * Sets the values of the instance variables "maxWeights" and "minWeights"
	 */
	public abstract void setMaxMinWeights();
	
	/**
	 * A method to get the number of weights that the Gene needs to store.
	 * 
	 * @return The number of weights to store in the instance variable "info"
	 */
	public abstract int numWeightsNeeded();

}
