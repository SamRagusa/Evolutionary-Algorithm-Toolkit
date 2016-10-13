package geneticEngine;

/**
 * An abstract class that is used when a Gene object is needed
 * to store an Double, which can only be between two bounds.
 * 
 * @author Sam Ragusa
 */
public abstract class BoundedDoubleGene extends Gene<Double>
{
	/**
	 * The lowest value that the instance variable "info" can be.
	 */
	public double lowerBound;
	
	/**
	 * The highest value that the instance variable "info" can be.
	 */
	public double upperBound;

	
	/**
	 * A Constructor that calls the method setDataRandomly() (through super();).
	 */
	public BoundedDoubleGene()
	{
		super();
	}
	
	/**
	 * Randomly sets the value of the instance variable "info" to a double value
	 * within the bounds.
	 */
	public void mutate()
	{
		info = lowerBound + rand.nextDouble() * (upperBound - lowerBound);	
	}

	/**
	 * Sets the bounds by calling the setBounds() method, then sets
	 * the value of the instance variable "info" to a random value within
	 * those bounds.
	 */
	public void setDataRandomly()
	{
		setBounds();
		mutate();
	}
	
	/**
	 * Sets the upperBound and lowerBound instance variables so 
	 * that the Gene knows what range to stay within.
	 */
	public abstract void setBounds();

}
