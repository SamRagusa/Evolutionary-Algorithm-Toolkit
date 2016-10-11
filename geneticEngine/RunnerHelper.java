package geneticEngine;

/**
 * A class to contain static methods to be used when creating/running 
 * implementations of the God class. 
 * 
 * @author Sam Ragusa
 */
public class RunnerHelper
{
	/**
	 * Takes in a String array of Gene Class names and turns them into a class array.
	 * This is usually used when creating the parameters for the God constructors.
	 * 
	 * @param par The String array to convert, with Class names separated by spaces.
	 * @return The class array for the given Class names.
	 */
	public static Class[] getClassArrayFromString(String par)
	{
		String[] classNames = par.split("\\s+");
		Class[] answer = new Class[classNames.length];
		try
		{
			for(int j=0; j<classNames.length; j++)
				answer[j] = Class.forName(classNames[j]);
			return answer;
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("The Gene class \"" + e.getMessage() + "\" was not found.");
			System.exit(1);
		}
		
		return null; //Should never happen
	}
}
