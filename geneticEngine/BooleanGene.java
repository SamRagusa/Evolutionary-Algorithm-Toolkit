package geneticEngine;

/**
 * @author Sam Ragusa
 */
public class BooleanGene extends Gene<Boolean>
{
	
	public BooleanGene()
	{
		super();
	}
	 
	public void mutate() 
	{
		if(info.booleanValue())
			info = Boolean.FALSE;
		else
			info = Boolean.TRUE;
	}
	
	public void setDataRandomly()
	{
		info = rand.nextBoolean();
	}
}



