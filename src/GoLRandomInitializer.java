import java.util.Random;

/**
 @(#)GoLRandomInitializer.java
 @author			Joshely Lim
 @versionCurrent	4 2017/5/17
 
 This class implements an interface for initializing the standard board
 */
public class GoLRandomInitializer implements GoLInitializer
{    
	//rand = a Random object
	//myBoard = board that will be used for the game
	//maxSize = the maximum number of cells on the board
	//setSize = the actual number of cells given by the player
	private GoLCell[][] myBoard;
	private Random rand;
	int maxSize;
	int setSize;
    
	/**
	 Creates a new GoLRandomInitializer with a random seed
	 
	 @param boardSize The size of the board according to the player
	 */
	public GoLRandomInitializer(int boardSize)
	{
		//Create the random object
    	rand = new Random();
    	maxSize = boardSize + 3;
    	setSize = boardSize;
    	
    	//Give the board a size
    	myBoard = new GoLCell[maxSize][maxSize];
    	
    	//clear the board
    	for (int currRow = 0; currRow < maxSize; currRow++)
    	{
    		for (int currCol = 0; currCol < maxSize; currCol++)
    		{
    			myBoard[currRow][currCol] 
    					= new GoLCell(false);
    		}
    	}
    	
    	//Assign life
    	for (int currRow = 1; currRow <= setSize; currRow++)
    	{
    		for (int currCol = 1; currCol <= setSize; currCol++)
    		{
    			myBoard[currRow][currCol] 
    					= new GoLCell(rand.nextBoolean());
    		}
    	}
	}
    
	/**
	 Creates a new GoLRandomInitializer with a given seed
	 
	 @param seed The given seed for the random object
	 @param boardSize The size of the board given by the player
	 */
	public GoLRandomInitializer(long seed, int boardSize)
	{
		//Create the random object with a set seed
    	rand = new Random(seed);
    	maxSize = boardSize + 3;
    	setSize = boardSize + 1;
    	
    	
    	//clear the board
    	for (int currRow = 0; currRow < maxSize; currRow++)
    	{
    		for (int currCol = 0; currCol < maxSize; currCol++)
    		{
    			myBoard[currRow][currCol] 
    					= new GoLCell(false);
    		}
    	}
    	
    	//Assign life
    	for (int currRow = 1; currRow <= setSize; currRow++)
    	{
    		for (int currCol = 1; currCol <= setSize; currCol++)
    		{
    			myBoard[currRow][currCol]
    					= new GoLCell(rand.nextBoolean());
    		}
    	}
	}
    
	/**
	 Return the official cell array produced by the constructor
	 
	 @return GoLCell[][] The official cell array produced by the constructor
	 */
	public GoLCell[][] getCellArray()
	{
    	return myBoard;
	}    
}
