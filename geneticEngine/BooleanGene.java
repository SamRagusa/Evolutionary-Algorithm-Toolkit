package geneticEngine;

/**
 * An abstract class that is used when the value needed to be stored
 * in a Gene is a boolean value.
 * 
 * @author Sam Ragusa
 */
public class BooleanGene extends Gene<Boolean>
{
	/**
	 * A Constructor that calls the method setDataRandomly() (through super();).
	 */
	public BooleanGene()
	{
		super();
	}
	 
	/**
	 * Negates the value of the Boolean object in the instance variable
	 * "info".  So if it were true, it would become false, and if it were 
	 * false it would become true. 
	 */
	public void mutate() 
	{
		if(info.booleanValue())
			info = Boolean.FALSE;
		else
			info = Boolean.TRUE;
	}
	
	/**
	 * Assigns a random boolean value to the instance variable "info".
	 */
	public void setDataRandomly()
	{
		info = rand.nextBoolean();
	}
}