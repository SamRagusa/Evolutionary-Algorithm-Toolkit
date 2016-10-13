package geneticEngine;

/**
 * @author Sam Ragusa
 */
public abstract class BoundedDoubleGene extends Gene<Double>
{
	public double lowerBound;
	public double upperBound;

	public BoundedDoubleGene()
	{
		super();
	}
	
	public void mutate()
	{
		info = lowerBound + rand.nextDouble() * (upperBound - lowerBound);	
	}

	public void setDataRandomly()
	{
		setBounds();
		
		info = lowerBound + rand.nextDouble() * (upperBound - lowerBound);
	}
	
	/**
	 * Sets the upperBound and lowerBound instance variables so 
	 * that the Gene knows what range to stay within.
	 */
	public abstract void setBounds();

}
