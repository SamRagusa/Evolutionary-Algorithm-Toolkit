package geneticEngine;

/**
 * An abstract class that is used when a Gene object is needed
 * to store an Integer, which can only be between two bounds.
 * 
 * @author Sam Ragusa
 */
public abstract class BoundedIntGene extends Gene<Integer>
{
	
	/**
	 * The lower bound for the value stored by the Gene.
	 * It is inclusive.
	 */
	public int lowerBound;
	
	/**
	 * The upper bound for the value stored by the Gene.
	 * It is exclusive.
	 */
	public int upperBound;

	
	/**
	 * A Constructor that calls the method setDataRandomly() (through super();).
	 */
	public BoundedIntGene()
	{
		super();
	}
	
	/**
	 * Replaces the Integer being stored with a new random integer within the bounds
	 */
	public void mutate()
	{
		info = rand.nextInt(upperBound-lowerBound) + lowerBound;
	}
	
	/**
	 * First it runs the abstract method setBounds() so it knows the bounds for the
	 * instance variable info, then it sets info to a random Integer within those bounds.
	 */
	public void setDataRandomly()
	{
		setBounds();
		info = rand.nextInt(upperBound-lowerBound) + lowerBound;
	}
	
	/**
	 * Sets the upperBound and lowerBound instance variables so 
	 * that the Gene knows what range to stay within.
	 */
	public abstract void setBounds();

}
