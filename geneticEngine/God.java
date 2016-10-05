package geneticEngine;

import java.util.Random;

/**
 * An abstract class for the purpose of  creates a random population of Chromosome objects (based on given specifications).
 * It contains methods which simulate basic evolutionary patters, and thus is able to "evolve" the random population of 
 * Chromosome objects by replacing it with a new generation. 
 * 
 * The building blocks and methods building off of them are separated in such a way that they can be easily
 * overridden for variations of the basic genetic algorithm strategy.  
 * 
 * @author Sam Ragusa
 */
public abstract class God
{
	/**
	 * An array of Chromosome objects representing the population at the current generation.
	 */
	public Chromosome[] chromes;
	
	/**
	 * A double array of fitness values, each corresponding to the Chromosome
	 * in the same index of the instance variable chromes.
	 */
	public double[] theFitnesses;
	
	/**
	 * The maximum number of generations to be created.
	 */
	public int maxGenerations;
	
	/**
	 * How frequently to mutate a Chromosome.  100 would mean every 100 
	 * Chromosomes, mutate 1. 
	 */
	public int mutationFrequency = -1; 
	
	/**
	 * A value to offset the mutation frequency by.  
	 * e.g. if every 100 Chromosome objects created one is mutated, the mutationOffset is
	 * a value such as 25, so the first mutated Chromosome object will be after 75 are created,
	 * instead of 100.  
	 */
	public int mutationOffset;
	
	/**
	 * A value that if a Chromosome object has fitness greater than or equal to 
	 * it, will stop producing further generations.  
	 */
	public double terminationCriterion; 
	
	/**
	 * The number of Chromosomes that have been created so far.
	 * This does not include the Chromosomes in the initial population. 
	 */
	public int numChromosomesCreated = 0;  
	
	/**
	 * A Random object for generating random Integers and Doubles where needed. 
	 */
	public Random rand = new Random();
	

	
	/**
	 * A constructor for when you want there to be no limit on the number of generations the God 
	 * can create.  The only way that simulateEvolution() will terminate is if the termination Criterion is met
	 * by one of the Chromosomes in the current population.  
	 * 
	 * @param geneticMakeUp The list of Genes to be created for every Chromosome.
	 * @param population The number of Chromosomes in every generation.
	 * @param mutationFrequency How frequently to mutate a Chromosome.  100 would mean every 100 
	 * Chromosomes, mutate 1.
	 * @param terminationCriterion The fitness that when reached or exceeded by a Chromosome in the population,
	 * no more generations are created.
	 */
	public God(Class[] geneticMakeUp, int population, int mutationFrequency, double terminationCriterion)
	{
		this.maxGenerations = -1;
		this.terminationCriterion = terminationCriterion;
		this.mutationFrequency = mutationFrequency;
		this.mutationOffset = rand.nextInt(mutationFrequency);
		generateRandomPopulation(population, geneticMakeUp, null);
	}
	
	
	/**
	 * A constructor for when you want there to be no limit on the number of generations the God 
	 * can create.  The only way that simulateEvolution() will terminate is if the termination Criterion is met
	 * by one of the Chromosomes in the current population.  
	 *
	 * note: God objects created through this constructor have been tested, but not extensively.
	 * 
	 * @param shortenedGeneticMakeUp A list of the Gene objects to be contained in each
	 * Chromosome object, not containing duplicates.  
	 * @param geneticFrequency A 2d array where the first index corresponds to the index of a gene
	 * in shortenedGeneticMakeUp.  The second index has size 2, and [0] is the minimum quantity of the gene to 
	 * create in a Chromosome object (inclusive), and [1] is the maximum (exclusive). 
	 * @param population The number of Chromosomes in every generation.
	 * @param mutationFrequency How frequently to mutate a Chromosome.  100 would mean every 100 
	 * Chromosomes, mutate 1.
	 * @param terminationCriterion The fitness that when reached or exceeded by a Chromosome in the population,
	 * no more generations are created.
	 */
	public God(Class[] shortenedGeneticMakeUp, int[][] geneticFrequency,  int population, int mutationFrequency, double terminationCriterion)
	{
		this.maxGenerations = -1;
		this.terminationCriterion = terminationCriterion;
		this.mutationFrequency = mutationFrequency;
		this.mutationOffset = rand.nextInt(mutationFrequency);
		generateRandomPopulation(population, shortenedGeneticMakeUp, geneticFrequency);
	}
	
	
	/**
	 * A constructor for when you want there to be both a maximum number of generations that 
	 * the God can create, and a termination criteria to halt creation of new generations. 
	 * During simulateEvolution(), it will stop when either the maximum number of generation is completed,
	 * or if the termination criteria is met by some Chromosome in the current generation.  
	 * 
	 * @param geneticMakeUp The list of Genes to be created for every Chromosome.
	 * @param population The number of Chromosomes in every generation.
	 * @param mutationFrequency How frequently to mutate a Chromosome.  100 would mean every 100 
	 * Chromosomes, mutate 1.
	 * @param maxGenerations The maximum number of generations to be created.
	 * @param terminationCriterion The fitness that when reached or exceeded by a Chromosome in the population,
	 * no more generations are created.
	 */
	public God(Class[] geneticMakeUp, int population, int mutationFrequency,  int maxGenerations, double terminationCriterion)
	{
		this.maxGenerations = maxGenerations;
		this.terminationCriterion = terminationCriterion;
		this.mutationFrequency = mutationFrequency;
		this.mutationOffset = rand.nextInt(mutationFrequency);
		generateRandomPopulation(population, geneticMakeUp, null);
	}
	
	/**
	 * A constructor for when you want there to be both a maximum number of generations that 
	 * the God can create, and a termination criteria to halt creation of new generations. 
	 * During simulateEvolution(), it will stop when either the maximum number of generation is completed,
	 * or if the termination criteria is met by some Chromosome in the current generation.  
	 * 
	 * note: God objects created through this constructor have been tested, but not extensively.
	 * 
	 * @param shortenedGeneticMakeUp A list of the Gene objects to be contained in each
	 * Chromosome object, not containing duplicates.  
	 * @param geneticFrequency A 2d array where the first index corresponds to the index of a gene
	 * in shortenedGeneticMakeUp.  The second index has size 2, and [0] is the minimum quantity of the gene to 
	 * create in a Chromosome object (inclusive), and [1] is the maximum (exclusive). 
	 * @param population The number of Chromosomes in every generation.
	 * @param mutationFrequency How frequently to mutate a Chromosome.  100 would mean every 100 
	 * Chromosomes, mutate 1.
	 * @param maxGenerations The maximum number of generations to be created.
	 * @param terminationCriterion The fitness that when reached or exceeded by a Chromosome in the population,
	 * no more generations are created.
	 */
	public God(Class[] shortenedGeneticMakeUp, int[][] geneticFrequency, int population, int mutationFrequency,  int maxGenerations, double terminationCriterion)
	{
		this.maxGenerations = maxGenerations;
		this.terminationCriterion = terminationCriterion;
		this.mutationFrequency = mutationFrequency;
		this.mutationOffset = rand.nextInt(mutationFrequency);
		generateRandomPopulation(population, shortenedGeneticMakeUp, geneticFrequency);
	}
	
	
	/**
	 * A constructor for when you want there to be no termination criteria, only a maximum number
	 * of generations able to be created.  simulateEvolution() will only terminate when the number of
	 * generations created is equal to the maximum generations given.
	 * 
	 * @param geneticMakeUp The list of Genes to be created for every Chromosome.
	 * @param population The number of Chromosomes in every generation.
	 * @param mutationFrequency How frequently to mutate a Chromosome.  100 would mean every 100 
	 * Chromosomes, mutate 1.
	 * @param maxGenerations The maximum number of generations to be created.
	 */
	public God(Class[] geneticMakeUp, int population, int mutationFrequency, int maxGenerations)
	{
		this.maxGenerations = maxGenerations;
		this.mutationFrequency = mutationFrequency;
		this.mutationOffset = rand.nextInt(mutationFrequency);
		this.terminationCriterion = -1;
		generateRandomPopulation(population, geneticMakeUp, null);
	}
	
	/**
	 * A constructor for when you want there to be no termination criteria, only a maximum number
	 * of generations able to be created.  simulateEvolution() will only terminate when the number of
	 * generations created is equal to the maximum generations given.
	 * 
	 * note: God objects created through this constructor have been tested, but not extensively.
	 * 
	 * @param shortenedGeneticMakeUp A list of the Gene objects to be contained in each
	 * Chromosome object, not containing duplicates.  
	 * @param geneticFrequency A 2d array where the first index corresponds to the index of a gene
	 * in shortenedGeneticMakeUp.  The second index has size 2, and [0] is the minimum quantity of the gene to 
	 * create in a Chromosome object (inclusive), and [1] is the maximum (exclusive). 
	 * @param population The number of Chromosomes in every generation.
	 * @param mutationFrequency How frequently to mutate a Chromosome.  100 would mean every 100 
	 * Chromosomes, mutate 1.
	 * @param maxGenerations The maximum number of generations to be created.
	 */
	public God(Class[] shortenedGeneticMakeUp, int[][] geneticFrequency, int population, int mutationFrequency, int maxGenerations)
	{
		this.maxGenerations = maxGenerations;
		this.mutationFrequency = mutationFrequency;
		this.mutationOffset = rand.nextInt(mutationFrequency);
		this.terminationCriterion = -1;
		generateRandomPopulation(population, shortenedGeneticMakeUp, geneticFrequency);
	}
	
	
	/**
	 * A method which takes the population of Chromosomes, and replaces it with the next generation
	 * continuously until either the maximum generation is reached (assuming it is defined), or the
	 * termination criteria is met by one or more of the Chromosomes in the new generation (assuming
	 * it is defined. 
	 * 
	 * note: I should probably make this have the option to save the information through the
	 * DataSaver class, not do it automatically.
	 * 
	 * @return The Chromosome in the population with the highest fitness.  This will either be done during
	 * the generation when a termination criteria is met, or when the maximum generation has been reached.
	 */
	public Chromosome simulateEvolution()
	{
		DataSaver.updateBestChromosomes(chromes, theFitnesses);
		
		//maybe should store successful Chromosome, so it won't have to compute it twice if the statement is true	
		if(getSuccessfulChromosomeIndex() !=  -1)
		{
			System.out.println("had chromosome from start");
			return chromes[getSuccessfulChromosomeIndex()];
		}
		
		int tempMaxGenerations = maxGenerations == -1 ? Integer.MAX_VALUE : maxGenerations;
		
		int answerIndex;
		int counter =0;
		for(int j=0; j<tempMaxGenerations; j++)
		{
			counter++;
			System.out.println("Generation: " + counter + "   Average fitness: " + getAverageFitness());
			
			doOneGeneration();
			
			DataSaver.updateBestChromosomes(chromes, theFitnesses);
			
			if(terminationCriterion != -1)				
			{
				answerIndex = getSuccessfulChromosomeIndex();
				if(answerIndex != -1)    
				{
					return chromes[answerIndex];
				}
			}
			
			if(maxGenerations == -1)
				j--;
		}
		System.out.println("Maximum number of generations reached.");
		System.out.println("Average population fitness: " + getAverageFitness());
		return chromes[getMostFitChromosomeIndex()];
	}
	
	
	/**
	 * Gets a list of Chromosome objects from the current generation to use in creating the next
	 * generation.  It does crossovers with that list of Chromosome objects to get a new generation of
	 * Chromosome objects, mutating the new generation when appropriate.  Then replaces the instance variable
	 * chromes with the new generation.  
	 */
	public void doOneGeneration()
	{
		Chromosome[] partialNextGen;
		Chromosome[] nextGen = new Chromosome[chromes.length];
		Chromosome[] toCrossover = chromes.length % 2 == 0 ? getChromosomesToCrossover(chromes.length) : getChromosomesToCrossover(chromes.length + 1);
		
		for(int j=0; j<toCrossover.length/2; j++)
		{
			partialNextGen = crossover(toCrossover[2*j], toCrossover[2*j+1]);
			
			nextGen[2*j] = new Chromosome(partialNextGen[0]);
			if(shouldMutateNextChromosome())
				nextGen[2*j].mutate();
			numChromosomesCreated++;
			if(j+1 != toCrossover.length/2 || chromes.length % 2 == 0) 
			{
				nextGen[2*j+1] = new Chromosome(partialNextGen[1]);
				if(shouldMutateNextChromosome())
					nextGen[2*j+1].mutate();
				numChromosomesCreated++;
			}
		}
		
		chromes = nextGen;
		setFitnesses(chromes);
	}
	
	
	/**
	 * A method that gets and returns an array of Chromosome objects to go through the
	 * crossover function and create the new generation of Chromosomes.  This uses the "Roulette wheel" technique 
	 * where you assign each Chromosome in the population a decimal value equal to it's fitness divided by the total
	 * fitness of every Chromosome in the current population.  It uses these decimals as probabilities to select which
	 * Chromosome object goes in each spot of the array being returned. 
	 * 
	 * @param quantity  The number of Chromosomes that are needed for crossovers
	 * (a Chromosome that is repeated twice has quantity two not one).
	 * @return An array of Chromosome objects of size "quantity", representing the Chromosomes
	 * to crossover. 
	 */
	public Chromosome[] getChromosomesToCrossover(int quantity)
	{
		double fitRatioSum = 0;
		Chromosome[] answer = new Chromosome[quantity];
		double[] fitRatios = getFitnessRatios();
		double[] chromeNumbersToPick = new double[quantity];
		
		for(int j=0; j < chromeNumbersToPick.length; j++)
			chromeNumbersToPick[j] = rand.nextDouble();
		
		for(int j=0; j < fitRatios.length; j++)
		{
			fitRatioSum += fitRatios[j];
			for(int i=0; i < chromeNumbersToPick.length; i++)
				if(chromeNumbersToPick[i] < fitRatioSum)
				{
					answer[i] = chromes[j]; 
					chromeNumbersToPick[i] = Integer.MAX_VALUE;
				}
		}
		return answer;
	}
	
	
	/**
	 * A method which takes in an array of Chromosome objects and uses it to 
	 * populate the instance variable theFitnesses with double values corresponding
	 * to the evaluated fitnesses of the Chromosomes.
	 * 
	 * @param population An array of Chromosome objects to use as reference
	 * for setting the instance variable theFitnesses. 
	 */
	public void setFitnesses(Chromosome[] population)
	{
		theFitnesses = new double[population.length];
		for(int j=0; j < population.length; j++)
			theFitnesses[j] = fitness(population[j]);
	}
	
	
	/**
	 * Calculates the sum of every Chromosome's fitness
	 * in the population, then uses that to find the ratio
	 * of the total that each Chromosome's fitness represents.
	 * 
	 * @return An array of doubles representing that Chromosome's
	 * portion of the total fitness.
	 */
	public double[] getFitnessRatios()
	{
		double[] answer = new double[chromes.length];
		double total = 0;
		for(int j=0; j<chromes.length; j++)
		{
			answer[j] = theFitnesses[j];
			total += answer[j];
		}
		
		for(int j=0; j<chromes.length; j++)
			answer[j] /= total;
		
		return answer;
	}
	
	
	/**
	 * Gets the index of the Chromosome that has exceeded the termination
	 * Criteria.  If more than one has done this, it will 
	 * return the one with the highest fitness, if they also have 
	 * the same fitness, it returns the one with the lowest index
	 * in the instance variable chromes.
	 * 
	 * @return The index of the most successful Chromosome exceeding the
	 * termination criteria, or -1 if none do so. 
	 */
	public int getSuccessfulChromosomeIndex()
	{
		if(terminationCriterion == -1)
			return -1;
		
		int answer = getMostFitChromosomeIndex();
		if(theFitnesses[answer] < terminationCriterion)
			return -1;
		
		return answer;
		
	}
	
	
	/**
	 * Computes and returns the average fitness of all of the Chromosome objects in the current generation. 
	 * 
	 * @return The average fitness of all of the Chromosome objects in the current generation.
	 */
	public double getAverageFitness()
	{
		double totalFitness = 0;
		for(int j=0; j<chromes.length; j++)
			totalFitness += theFitnesses[j];
		return totalFitness/chromes.length;
	}
	
	
	/**
	 * Gets and returns the index of the Chromosome in the population
	 * which has the highest fitness.
	 * 
	 * @return The index of the Chromosome in the population with the highest fitness.
	 */
	public int getMostFitChromosomeIndex() 
	{
		int answerIndex = 0;
		double numToBeat = theFitnesses[0];
		for(int j=1; j<chromes.length; j++)
			if(theFitnesses[j] > numToBeat)
			{
				numToBeat = theFitnesses[j];
				answerIndex = j;
			}
		return answerIndex;
	}
	
	
	/**
	 * A method to conclude if the next Chromosome object created should be mutated.
	 * This implementation mutates a Chromosome every time the number of Chromsomes
	 * created plus the value of the instance variable mutationOffset is a multiple of 
	 * the instance variable mutationFrequency.
	 * (May want to be overridden for more complicated implementations of God class)
	 * 
	 * @return True if the next Chromosome created should be mutated, false if not.
	 */
	public boolean shouldMutateNextChromosome()
	{
		if((numChromosomesCreated + mutationOffset) % mutationFrequency == 0)
			return true;
		return false;
	}
	
	
	/**
	 * Prints every Chromosome in the population on it's own line.  This method
	 * is used for testing purposes.
	 */
	public void printPopulation()
	{
		for(int j=0; j<chromes.length; j++)
			System.out.println(chromes[j]);
	}
	
	
	/**
	 * Generates a population of Chromosome objects.  The population generated has randomly
	 * selected values for each Gene within each Chromosome (as specified by geneticFrequency).
	 * If geneticFrequency is null, for each Class in shortenedGeneticMakeUp (including duplicates),
	 * Chromosome will be created with an instance of that Gene (multiple if the Gene Class comes up multiple times).  
	 * 
	 * @param population The number of Chromosomes to be created.
	 * @param shortenedGeneticMakeUp A list of the Gene objects to be contained in each
	 * Chromosome object, may contain duplicates and not be shortened, or may not.  
	 * @param geneticFrequency A 2d array where the first index corresponds to the index of a gene
	 * in shortenedGeneticMakeUp.  The second index has size 2, and [0] is the minimum quantity of the gene to 
	 * create in a Chromosome object (inclusive), and [1] is the maximum (exclusive). 
	 */
	public void generateRandomPopulation(int population, Class[] shortenedGeneticMakeUp, int[][] geneticFrequency)
	{
		if(geneticFrequency == null)
		{
			geneticFrequency = new int[shortenedGeneticMakeUp.length][2];
			for(int j=0; j<shortenedGeneticMakeUp.length; j++)
			{
				geneticFrequency[j][0] = 1;
				geneticFrequency[j][1] = 2;
			}
			
		}
		chromes = new Chromosome[population];
		int[][] chromesGeneQuantity = new int[population][shortenedGeneticMakeUp.length];
		int[] chromesTotalNumGenes = new int[population];
		for(int j=0; j<population; j++)
			for(int i=0; i<shortenedGeneticMakeUp.length; i++)
			{
				if(geneticFrequency[i][1] == geneticFrequency[i][0])
					chromesGeneQuantity[j][i] = geneticFrequency[i][0];
				else
					chromesGeneQuantity[j][i] = rand.nextInt(geneticFrequency[i][1]-geneticFrequency[i][0]) + geneticFrequency[i][0];
				chromesTotalNumGenes[j] += chromesGeneQuantity[j][i];
			}
		
		theFitnesses = new double[population];
		Class[] tempGeneticMakeUp;
		int totalGeneCounter;
		for(int j=0; j<population; j++)
		{
			totalGeneCounter = 0;
			tempGeneticMakeUp = new Class[chromesTotalNumGenes[j]];
			for(int i=0; i<shortenedGeneticMakeUp.length; i++)
				for(int k=0; k<chromesGeneQuantity[j][i]; k++)
				{
					tempGeneticMakeUp[totalGeneCounter] = shortenedGeneticMakeUp[i];
					totalGeneCounter++;
				}
			chromes[j] = new Chromosome(tempGeneticMakeUp);
		}
		
		setFitnesses(chromes);
	}
	
	/**
	 * Combines the Genes of two Chromosome objects (parents) to create and return two more 
	 * Chromosome objects (children), with different traits from different parents.  
	 * 
	 * @param mom The first parent involved in the crossover.
	 * @param dad The second parent involved in the crossover.
	 * @return A length 2 array of the two new Chromosomes.
	 */
	abstract public Chromosome[] crossover(Chromosome mom, Chromosome dad);
	
	
	/**
	 * Gets the fitness of a particular Chromosome.  This usually
	 * represents how effective the Chromosome is at solving a given problem.
	 * 
	 * @param par The Chromosome who's fitness should be evaluated.
	 * @return a double value representing the fitness of the given Chromosome.  
	 */
	abstract public double fitness(Chromosome par);
	
}
