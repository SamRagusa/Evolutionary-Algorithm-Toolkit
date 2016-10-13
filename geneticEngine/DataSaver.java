package geneticEngine;

import java.util.ArrayList;

/**
 * NOTE: Since I wrote this I have learned Google's Protocol Buffers and would like to replace this with an implementation
 * using them.  May be difficult due to the possible variance in different Gene implementations, but seems more
 * than do-able given more free time.
 * 
 * 
 * 
 * This static class is for use by the God class to store the Chromesome objects with the highest fitness 
 * from ANY generation.  
 * 
 * @author Sam Ragusa
 *
 */
public class DataSaver 
{
	
	/**
	 * An arrayList of the Chromosome objects which have/had the highest fitnesses of any Chromosome objects
	 * constructed in any generation.
	 */
	public static ArrayList<Chromosome> theBest = new ArrayList<Chromosome>(); 
	
	/**
	 * An ArrayList of values each representing the fitness of the Chromosome in the same index of the
	 * instance variable "theBest".
	 */
	public static ArrayList<Double> theBestFitnesses = new ArrayList<Double>();  //same shit as above	

	/**
	 * The maximum number of Chromosome Objects and fitnesses to store in the instance variables 
	 * "theBest" and "theBestFitnesses".
	 */
	public static int numBestChromes = 100;
	

	/**
	 * Takes an array of Chromosome objects, along with an array of their fitnesses, and updates the 
	 * ArrayList of Chromosome objects stored in the instance variable "theBest", and their corresponding 
	 * fitness values in "theBestFitnesses" to account for the potentially more fit Chromosome Objects.
	 * 
	 * @param theChromes The array of Chromosome objects to store if they are fit enough for the list.
	 * @param theFitnesses An array of fitnesses corresponding to the Chromosome objects given by the other parameter.
	 */
	public static void updateBestChromosomes(Chromosome[] theChromes, double[] theFitnesses)
	{		
		for(int j=0; j < theChromes.length; j++)
		{
			if(theBest.size() < numBestChromes || theBestFitnesses.get(theBest.size()-1) < theFitnesses[j])
				addChromosome(theChromes[j], theFitnesses[j]); 
			while(theBest.size() > numBestChromes)
			{
				theBest.remove(theBest.size()-1);
				theBestFitnesses.remove(theBestFitnesses.size()-1);
			}
		}
	}
	
	/**
	 * Adds a given Chromosome and it's fitness to the instance variables "theBest" and
	 * "theBestFitnesses" respectively.  It places them in an appropriate location
	 * to keep the fitness rankings decreasing.
	 * 
	 * @param chrome the Chromosome to 
	 * @param fitness The fitness associated with the given Chromosome object.
	 */
	public static void addChromosome(Chromosome chrome, double fitness)
	{
		int index = 0;
		while(index != theBest.size() && fitness <= theBestFitnesses.get(index))
			index++;
		theBest.add(index, chrome);
		theBestFitnesses.add(index, fitness);			
	}
	
	/**
	 * A method to set the instance variable "numBestChromes" to a given value.
	 * 
	 * @param num The value to set "numBestChromes" to.
	 */
	public static void setNumBestChromesStored(int num)
	{
		numBestChromes = num;
	}
	
	/**
	 * Prints the highest fitness values of any Chromosome in any generation that has been created
	 * in descending order.
	 */
	public static void printBestFitnesses()
	{
		for(int j=0; j<theBestFitnesses.size(); j++)
			System.out.println("Chromosome #" + (j+1) + " fitness: " + theBestFitnesses.get(j));
	}
	
	
	/**
	 * Prints the String representation of Chromosome objects which have/had the highest
	 * fitness values of any Chromosome in any generation that has been created so far (in descending order).
	 */
	public static void printBestChromosomes()
	{
		for(int j=0; j<theBestFitnesses.size(); j++)
			System.out.println("Chromosome #" + (j+1) + ": " + theBest.get(j));
	}
	
}
