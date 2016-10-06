package eightQueens;

import geneticEngine.BoundedIntGene;

/**
 * A BoundedIntGene subclass (and thus also a subclass of Gene) to represent a queen on a chess board.
 * For the Eight Queens Puzzle, no two queens can be on the same row. So instead
 * of storing the 2d coordinates, it only stores one.  It stores an int between 0 and 8 (inclusive),
 * which represents it's position in it's row.
 * 
 * @author Sam Ragusa  
 */
public class QueenGene extends BoundedIntGene
{

	/**
	 * A constructor that calls the setDataRandomly() method.
	 * It does this through it's superclasses constructors. 
	 */
	public QueenGene()
	{
		super();
	}

	/**
	 * Set the lower bound to 0, and upper bound to 8.
	 * This is for the 8 spots in a row on a chess board. 
	 */
	public void setBounds()
	{
		upperBound = 8;
		lowerBound = 0;
	}

}
