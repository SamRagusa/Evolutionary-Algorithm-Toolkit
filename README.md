## Synopsis

A toolkit aimed at making implementation and execution of evolutionary algorithms quick and easy.  It accomplishes this with a set of abstract classes, coded for modularity of algorithm design, which can easily be implemented for almost any type of evolutionary algorithm. 


## How to Implement

When using this toolkit to implement an evolutionary algorithm, you must create a class which extends the God class, and one or more classes that extend the Gene class or one of the other classes given which implement the Gene class.

The class which extends the God class must implement only two abstract methods, the crossover method, and the fitness method.  

The classes which extend the Gene class must also implement two abstract methods, these being the mutate and setDataRandomly.  The Gene class uses generics to store any type of data you might wish to within your Gene implementation (for example the WeightArrayGene stores List<Double> as it's data).


## Given Gene Implementations

Four classes which extend the Gene class are given to cut down on implementation time (located within the geneticEngine folder), these include BooleanGene, BoundedDoubleGene, BoundedIntGene and WeightedArrayGene.  All but the BooleanGene are abstract classes whos abstract method/methods require only the setting or returning of constants for implementation.   


## Code Example

Here is some example code of how to solve the Eight Queens Puzzle using this toolkit.  The QueenGene and EightQueenGod classes used below are located within the eightQueens folder.  The code given below will print an array of eight integers between zero to seven (inclusive), representing where on each row a queen should be placed such that it solves the Eight Queens Puzzle. (Since the crossover function used is randomly generated, it may not always succeed in the given number of epochs. This is purposeful.)

```
import eightQueens.EightQueenGod;
import geneticEngine.RunnerHelper;
public class Runner
{
	public static void main(String[] args)
	{	
		int[][] geneticFrequency = {{8,8}};
		EightQueenGod test = new EightQueenGod(RunnerHelper.getClassArrayFromString(
		    "eightQueens.QueenGene"), geneticFrequency, 2500,500,100,21);
		System.out.println(test.simulateEvolution());
	}
}
```

For more information on the Eight Queens Puzzle, visit the [Wikipedia page](https://en.wikipedia.org/wiki/Eight_queens_puzzle).
