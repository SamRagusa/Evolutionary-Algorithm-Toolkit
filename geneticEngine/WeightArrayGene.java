package geneticEngine;

import java.util.ArrayList;
import java.util.List;

/**
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
	 * 
	 */
	public WeightArrayGene()
	{
		super();
	}
	
	
	/**
	 * 
	 * @param weights
	 */
	public WeightArrayGene(List<Double> weights)
	{
		this.info = weights;
		setDataRandomly();
	}
	
	/**
	 * 
	 */
	public void mutate()
	{
		for(int j=0; j < info.size(); j++)
			info.set(j, minWeights[j] + rand.nextDouble() * (maxWeights[j] - minWeights[j]));
	}

	/**
	 * 
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
	 * 
	 * 
	 * @param index The index of the value in the weight array to get
	 * @return The value in the weight array at the requested index
	 */
	public double getWeight(int index)
	{
		return info.get(index);
	}
	
	/**
	 * 
	 */
	public abstract void setMaxMinWeights();
	
	/**
	 * 
	 * @return
	 */
	public abstract int numWeightsNeeded();
	

}
