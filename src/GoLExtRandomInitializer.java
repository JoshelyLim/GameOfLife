

import java.util.Random;

/**
@(#)GoLExtRandomInitializer.java
@author			Joshely Lim
@versionCurrent	4 2017/5/17

Implements the GoLInitializer interface
Operate like GoLRandomInitializer, but also
	choose randomly whether to assign a regular
		GoLCell or a GoLStrongCell or GoLWeakCell
		1/3 chance each
*/
public class GoLExtRandomInitializer implements GoLInitializer
{
	//myBoard = the board object being used by the methods
	//maxSize = the maximum size of the board
	//setSize = the actual size set by the player
	private GoLCell[][] myBoard;
	int maxSize;
	int setSize;

	/**
	 Constructor that creates a new GoLExtRandomInitializer with a random seed.
	 Assigns the life to each of the different possible choices.
	 
	 @param boardSize The size of the board set by the player
	 */
	public GoLExtRandomInitializer(int boardSize)
	{
		//Creates a random object
		Random rand = new Random();
		maxSize = boardSize + 3;
		setSize = boardSize;
		
		myBoard = new GoLCell[maxSize][maxSize];
		
		//randValue = a random value to be used for assigning
		int randValue;
		
    	//Clear the whole board
    	for (int currRow = 0; currRow < maxSize; currRow++)
    	{
        	//currCol = current number of column target cell is in
        	for (int currCol = 0; currCol < maxSize; currCol++)
        	{    
            	//Generate a random number indicating the cell's state
            	//Assign whether each cell is alive
            	myBoard[currRow][currCol] = new GoLCell(false);
        	}
    	}
		
    	/*
     	* currRow = current row
     	* currCol = current column
     	*/
    	//Loops through each cell to assign type and life
		for (int currRow = 1; currRow <= setSize; currRow++)
		{
			for (int currCol = 1; currCol <= setSize; currCol++)
			{
				//creates a random value for type of cell
				randValue = rand.nextInt(3);
				
				//Assigns type or life with equal 1/3 possibility
				if (randValue == 0)
				{
				myBoard[currRow][currCol] = new GoLCell(rand.nextBoolean());
				}
				else if (randValue == 1)
				{
				myBoard[currRow][currCol] = new GoLWeakCell(rand.nextBoolean());
				}
				else if (randValue == 2)
				{
				myBoard[currRow][currCol] = new GoLStrongCell(rand.nextBoolean());
				}
			}
		}
	}
	
	/**
	 Constructor that creates a new GoLExtRandomInitializer with a given seed.
	 Assigns the life to each of the different possible choices.
	 
	 @param boardSize The size of the board set by the player
	 @param seed the given seed that can be used for the randomizer
	 */
	public GoLExtRandomInitializer(long seed,int boardSize)
	{
		//Creates a Random object with a given seed
		Random rand = new Random(seed);
		
		//randValue = a random value to be used for assigning
		int randValue;
		maxSize = boardSize + 3;
		setSize = boardSize;
		
    	//Clear the whole board
    	for (int currRow = 0; currRow < maxSize; currRow++)
    	{
        	//currCol = current number of column target cell is in
        	for (int currCol = 0; currCol < maxSize; currCol++)
        	{    
            	//Generate a random number indicating the cell's state
            	//Assign whether each cell is alive
            	myBoard[currRow][currCol] = new GoLCell(false);
        	}
    	}
		
    	/*
     	* currRow = current row
     	* currCol = current column
     	*/
		//Loops through each cell to assign type and life
		//See past constructor for further details
		for (int currRow = 1; currRow <= setSize; currRow++)
		{
			for (int currCol = 1; currCol <= setSize; currCol++)
			{
				randValue = rand.nextInt(3);
				if (randValue == 0)
				{
				myBoard[currRow][currCol] = new GoLCell(rand.nextBoolean());
				}
				else if (randValue == 1)
				{
				myBoard[currRow][currCol] = new GoLWeakCell(rand.nextBoolean());
				}
				else if (randValue == 2)
				{
				myBoard[currRow][currCol] = new GoLStrongCell(rand.nextBoolean());
				}
			}
		}
	}
	
	/**
	 Returns whether a cell is weak, normal, or strong
	 
	 @return whether a cell is weak, normal, or strong
	 */
	public GoLCell[][] getCellArray()
	{
		return myBoard;
	}
}