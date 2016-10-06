package eightQueens;

import geneticEngine.Chromosome;
import geneticEngine.Gene;
import geneticEngine.God;

/**
 * An implementation of the God class to solve the Eight Queen Puzzle.
 * To solve the Eight Queen Puzzle you must place eight queens on a chess board
 * such that no queen can capture any other queen.  One thing to note: since
 * the Genes to crossover are picked at random, it will not always solve the puzzle,
 * if not re-run the program, or try crossing over every other Gene instead of picking randomly.
 * 
 * @author Sam Ragusa
 */
public class EightQueenGod extends God
{
	/**
	 * An array of boolean values representing if a gene should be crossed over or not
	 * in the crossover method.  If an index is true, then that Gene should be crossed over, 
	 * if it is false it should not. 
	 */
	public boolean[] crossoverGenes;

	
	/**
	 * Constructor that uses the constructor from it's superclass (God) with the same parameters,
	 * and then generates a random crossover function.  
	 * 
	 * @see geneticEngine.God#God(Class[], int, int, double)
	 */
	public EightQueenGod(Class[] geneticMakeUp, int population, int mutationFrequency, double terminationCriterion)
	{
		super(geneticMakeUp, population, mutationFrequency, terminationCriterion);
		generateRandomCrossover();
	}

	/**
	 * Constructor that uses the constructor from it's superclass (God) with the same parameters,
	 * and then generates a random crossover function.  
	 * 
	 * @see geneticEngine.God#God(Class[], int[][], int, int, double)
	 */
	public EightQueenGod(Class[] shortenedGeneticMakeUp, int[][] geneticFrequency, int population, int mutationFrequency, double terminationCriterion)
	{
		super(shortenedGeneticMakeUp, geneticFrequency, population, mutationFrequency, terminationCriterion);
		generateRandomCrossover();
	}

	/**
	 * Constructor that uses the constructor from it's superclass (God) with the same parameters,
	 * and then generates a random crossover function.  
	 * 
	 * @see geneticEngine.God#God(Class[], int, int, int, double)
	 */
	public EightQueenGod(Class[] geneticMakeUp, int population, int mutationFrequency, int maxGenerations, double terminationCriterion)
	{
		super(geneticMakeUp, population, mutationFrequency, maxGenerations, terminationCriterion);
		generateRandomCrossover();
	}

	/**
	 * Constructor that uses the constructor from it's superclass (God) with the same parameters,
	 * and then generates a random crossover function.  
	 * 
	 * @see geneticEngine.God#God(Class[], int[][], int, int, int, double)
	 */
	public EightQueenGod(Class[] shortenedGeneticMakeUp, int[][] geneticFrequency, int population,int mutationFrequency, int maxGenerations, double terminationCriterion)
	{
		super(shortenedGeneticMakeUp, geneticFrequency, population, mutationFrequency, maxGenerations,terminationCriterion);
		generateRandomCrossover();
	}

	/**
	 * Constructor that uses the constructor from it's superclass (God) with the same parameters,
	 * and then generates a random crossover function.  
	 * 
	 * @see geneticEngine.God#God(Class[], int, int, int)
	 */
	public EightQueenGod(Class[] geneticMakeUp, int population, int mutationFrequency, int maxGenerations)
	{
		super(geneticMakeUp, population, mutationFrequency, maxGenerations);
		generateRandomCrossover();
	}

	/**
	 * Constructor that uses the constructor from it's superclass (God) with the same parameters,
	 * and then generates a random crossover function.  
	 * 
	 * @see geneticEngine.God#God(Class[], int[][], int, int, int)
	 */
	public EightQueenGod(Class[] shortenedGeneticMakeUp, int[][] geneticFrequency, int population, int mutationFrequency, int maxGenerations)
	{
		super(shortenedGeneticMakeUp, geneticFrequency, population, mutationFrequency, maxGenerations);
		generateRandomCrossover();
	}

	/**
	 * Applies the random crossover stored in crossoverGenes, where if the
	 * index is true, it will crossover that Gene, and it will not if it is false. . 
	 */
	public Chromosome[] crossover(Chromosome mom, Chromosome dad)
	{
		Chromosome[] answer = new Chromosome[2];
		Gene[] tempGenes1 = new QueenGene[mom.numGenes()];
		Gene[] tempGenes2 = new QueenGene[mom.numGenes()];
		for(int j=0; j<mom.numGenes(); j++)
		{
			if(crossoverGenes[j])
			{
				tempGenes1[j] = dad.getGene(j);
				tempGenes2[j] = mom.getGene(j);
			}
			else
			{
				tempGenes1[j] = mom.getGene(j);
				tempGenes2[j] = dad.getGene(j);
			}
		}
		
		answer[0] = new Chromosome(tempGenes1);
		answer[1] = new Chromosome(tempGenes2);
		
		return answer;
	}

	/**
	 * Evaluates the Chromosome object's fitness at the 8 Queens puzzle on a scale from
	 * 0 to 21 (inclusive).  The fitness of a Chromosome that represents a chess board
	 * with n queen collisions is equal to 21-n.
	 */
	public double fitness(Chromosome par)
	{
		return 21 - numberOfCollisions(par);
	}
	
	/**
	 * Gets the number of collisions (times a queen can take another queen) in the chess board
	 * represented by the Chromosome object given.
	 * 
	 * @param par The Chromosome object to check for collisions
	 * @return The number of collisions in the board represented by the Chromosome object.
	 */
	public int numberOfCollisions(Chromosome par)
	{
		int totalCollisions = 0;
		
		//get number of vertical collisions
		int[] verticalOccurences = new int[10];
		for(int j=0; j<par.numGenes(); j++)
			verticalOccurences[(int) par.getGene(j).getInfo()]++;
		
		for(int j=0; j<verticalOccurences.length; j++)
			if(verticalOccurences[j] > 1)
				totalCollisions += ((verticalOccurences[j] -1) * verticalOccurences[j])/2;
		
		// get (add) number of diagonal collisions
		for(int j=0; j<par.numGenes(); j++)
			for(int i=0; i<par.numGenes(); i++)
				if(i < j)
					if(Math.abs(j-i) == Math.abs((int) par.getGene(j).getInfo() - (int) par.getGene(i).getInfo()))
						totalCollisions++;
				
		return totalCollisions;
	}
	
	/**
	 * Generates a random crossover function.  It initializes the instance variable
	 * crossoverGenes, if an index is true, it will be crossed over, if false, it won't.  
	 * This strategy will usually work because of how simple the puzzle is, but since it is random,
	 * it will not always work.  For a less fun, more successful method, try crossing over every other Gene. 
	 */
	public void generateRandomCrossover()
	{
		crossoverGenes = new boolean[chromes[0].numGenes()];
		for(int j=0; j<crossoverGenes.length; j++)
			crossoverGenes[j] = rand.nextBoolean();
	}

}
