import java.io.Serializable;

/**
@(#)GoLBoard.java
@author			Joshely Lim
@versionCurrent	4 2017/5/17

A class that is a model for the game board that
implements the java.io.Serializable to notify that this class
may be used as a Serializable.
*/
public class GoLBoard implements java.io.Serializable
{
	/*
 	* currentRound = the current round
 	* currentBirths = the current births
 	* currentDeaths = the current deaths
 	* myGoLCell = the state of cells present on the board
 	* maxSize = the maximum number of cells
 	* setSize = the number of cells that are actually being used
 	*/
    private int currentRound = 1;
	private int currentBirths;
	private int currentDeath;
	
	protected GoLCell[][] myGoLCell;
	
	private int setSize;

	/**
	 Constructor that calls the overloaded constructor with 
	 the GoLRandomInitializer with the same boardSize.
	 Setting the values of the setSize and the maxSize.
	 
	 @param boardSize The set size of the board according to the player
	 */
	public GoLBoard(int boardSize)
	{
    	this(new GoLRandomInitializer(boardSize), boardSize);
    	setSize = boardSize;
	}
    
	/**
	 Constructor that creates a new GoLBoard initialized
	 by the provided initializer, using the same setSize.
	 Set the values of the setSize and the maxSize.
	 
	 @param boardSize The set size of the board according to the player
	 */
	public GoLBoard(GoLInitializer myInitializer, int boardSize)
	{
    	myGoLCell = myInitializer.getCellArray();
    	setSize = boardSize;
	}
    
	/**
	 Method updating the board for the next
	 round by calling the updateCell() method of the
	 individual cells, passing a GoLNeighborhood to them.
	 
	 @return The updated GoLBoard
	 */
	public GoLBoard nextRound()
	{ 
		//tempBoard = the new upcoming GoLBoard that will be returned
    	GoLBoard tempBoard = this.copyBoard();   
    	
    	//set the births and deaths to the beginning
    	currentDeath = 0;
    	currentBirths = 0;
   	 
    	//currRow = current row
    	//currCol = current column
    	//Accumulate number of cells that birthed and died
    	//Update the tempBoard using the updateCell method
    	for (int currRow = 1; currRow <= setSize; currRow++)
    	{
        	for (int currCol = 1;currCol <= setSize; currCol++)
        	{
        	 boolean wasAlive = myGoLCell[currRow][currCol].isAlive();
        	 boolean nowAlive =
        	  	tempBoard.getCell(currRow,currCol).updateCell(getNeighborhood(currRow,currCol));
        	 currentDeath += (wasAlive && !nowAlive)?1:0;
        	 currentBirths += (!wasAlive && nowAlive)?1:0;
        	}
    	}
    	
    	//Replace the current myGoLCell with the updated
    	this.myGoLCell = tempBoard.myGoLCell;
    	
    	//Add number of rounds
    	currentRound++;
    	
    	//return the current board
    	return this;
	}
    
	/**
	 Method that returns the cell at the specified coordinates.
	 
	 @param x The x coordinates of the myGoLCell array
	 @param y The y coordinates of the myGoLCell array
	 @return Return the myGoLCell class at that point in the array
	 */
	public GoLCell getCell(int x, int y)
	{
    	return myGoLCell[x][y];
	}
    
	/**
	 Method that returns a copy of the current board
	 
	 @return copy of the current board
	 */
	public GoLBoard copyBoard()
	{
		//tempBoard = A GoLBoard object that will become the copy
		//currCell = the cell that will be examined
		//compString = the actual class of the GoLCell in String form
    	GoLBoard tempBoard = new GoLBoard(setSize);
    	GoLCell currCell;
    	String compString;
    	
    	//currRow = current row
    	//currCol = current column
    	//Copy the current GoLCell onto the copy GoLCell
    	//Determine whether the cell is strong, weak, or normal
    	for (int currRow = 1; currRow <= setSize; currRow++)
    	{
        	for (int currCol = 1; currCol <= setSize; currCol++)
        	{
           		currCell = myGoLCell[currRow][currCol];
        		compString = currCell.getClass().getName();
        		
        		if (compString.contains("Strong"))
        		{
        			tempBoard.myGoLCell[currRow][currCol] =
        					new GoLStrongCell(this.myGoLCell[currRow][currCol].isAlive());
        					
        		}
        		else if (compString.contains("Weak"))
        		{
        			tempBoard.myGoLCell[currRow][currCol] =
        					new GoLWeakCell(this.myGoLCell[currRow][currCol].isAlive());
        		}
        		else
        		{
        			tempBoard.myGoLCell[currRow][currCol] =
        					new GoLCell(this.myGoLCell[currRow][currCol].isAlive());
        		}
        	}
    	}
   	 
    	//return the temporary board that was created
    	return tempBoard;
	}
    
	/**
	 Method that re-initializes the board
	 
	 @return the new re-initialized board
	 */
	public GoLBoard reset()
	{
		return reset(new GoLRandomInitializer(setSize));
	}
    
	/**
	  Method that re-initializes the board with the provided
	 GoLInitializer
	 
	 @param myInitializer The initializer that will be used to 
	 						re-initialize the board
	 @return the re-initialized board
	 */
	public GoLBoard reset(GoLInitializer myInitializer)
	{
    	myGoLCell = myInitializer.getCellArray();
    	return this;
	}
    
	/**
	 Method that returns the number of the current round
	 
	 @return the current round
	 */
	public int getCurrentRound()
	{
    	return currentRound;
	}
    
	/**
	 Method that returns the number of births from the last round
	 
	 @return The current births
	 */
	public int getBirths()
	{
    	return currentBirths;
	}

	/**
	 Method that returns the number of deaths from the last round
	 
	 @return The current deaths
	 */
	public int getDeaths()
	{
    	return currentDeath;
	}
	
	/**
	 Method that returns the size the player set
	 
	 @return the size the player set
	 */
	public int getSize()
	{
		return setSize;
	}
	
	/**
	 * Method for returning the GoLCells
	 * 
	 * @return the myGoLCell of the instance of this class
	 */
	public GoLCell[][] getCells()
	{
		return this.myGoLCell;
	}
	
    /**private utility function to generate the neigborhoods
     * 
     * @param x The position of the x row
     * @param y The position of the y column
     * @return
     */
    private GoLNeighborhood getNeighborhood(int x, int y) {

    	GoLCell[][] tempArray = new GoLCell[3][3];

    	tempArray[0][0] = ((x>0)&&(y>0))?myGoLCell[x-1][y-1]:(new GoLCell(false));
    	tempArray[1][0] = (y>0)?myGoLCell[x][y-1]:(new GoLCell(false));
    	tempArray[2][0] = ((x<19)&&(y>0))?myGoLCell[x+1][y-1]:(new GoLCell(false));
    	tempArray[0][1] = ((x>0))?myGoLCell[x-1][y]:(new GoLCell(false));
    	tempArray[1][1] = myGoLCell[x][y];
    	tempArray[2][1] = ((x<19))?myGoLCell[x+1][y]:(new GoLCell(false));
    	tempArray[0][2] = ((x>0)&&(y<19))?myGoLCell[x-1][y+1]:(new GoLCell(false));
    	tempArray[1][2] = (y<19)?myGoLCell[x][y+1]:(new GoLCell(false));
    	tempArray[2][2] = ((x<19)&&(y<19))?myGoLCell[x+1][y+1]:(new GoLCell(false));

    	return new GoLNeighborhood(tempArray);

    }
}

