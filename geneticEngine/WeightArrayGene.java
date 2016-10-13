package geneticEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Ragusa
 */
public abstract class WeightArrayGene extends Gene<List<Double>>
{
	
	public double[] maxWeights;
	public double[] minWeights; 

	public WeightArrayGene()
	{
		super();
	}
	
	public WeightArrayGene(List<Double> weights)
	{
		this.info = weights;
		setDataRandomly();
	}
	
	public void mutate()
	{
		for(int j=0; j < info.size(); j++)
			info.set(j, minWeights[j] + rand.nextDouble() * (maxWeights[j] - minWeights[j]));
	}

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
	
	public double getWeight(int index)
	{
		return info.get(index);
	}
	
	public abstract void setMaxMinWeights();
	
	public abstract int numWeightsNeeded();
	

}
