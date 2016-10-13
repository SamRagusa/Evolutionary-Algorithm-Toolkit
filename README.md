## Synopsis

A genetic/evolutionary algorithm toolkit aimed at making implementation and execution quick and easy.  It accomplishes this with a set of abstract classes that can easily be implemented for almost any problem. 

I wrote this code while I took a road trip from New York to Florida during the summer of 2016.


## Code Example

Here is some example code of how to solve the Eight Queens Puzzle using this toolkit.  It will print an array of eight integers between zero to seven (inclusive), representing where on each row a queen should be placed. (Since the crossover function is randomly generated,  it may not always succeed in the given number of epochs. This is purposeful.)

```
import eightQueens.EightQueenGod;
import geneticEngine.RunnerHelper;
public class Runner
{
	public static void main(String[] args)
	{	
		int[][] geneticFrequency = {{8,8}};
		EightQueenGod test = new EightQueenGod(RunnerHelper.getClassArrayFromString("eightQueens.QueenGene"), geneticFrequency, 2500,500,100,21);
		System.out.println(test.simulateEvolution());
	}
}
```

For more information on the Eight Queens Puzzle, visit the [Wikipedia page](https://en.wikipedia.org/wiki/Eight_queens_puzzle).
