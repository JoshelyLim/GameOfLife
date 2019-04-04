
import java.io.Serializable;

/**
@(#)GoLCell.java
@author			Joshely Lim
@versionCurrent	4 2017/5/17

Class that creates a model for an individual cell.
Implements the java.io.Serializable to notify it may be used
as a serializable object.
*/
public class GoLCell implements java.io.Serializable
{
	//status = whether a cell is alive or dead
	protected boolean status;
   
	/**
	 Constructor that creates a new cell that is alive 
	 if the parameter is true or false otherwise
	  
	 @param myStatus Whether the cell is alive or dead
	 */
	public GoLCell(boolean myStatus)
	{
    	this.status = myStatus;
	}
    
	/**
	 Constructor that updates the cell using the given neighborhood
	 and returns true if alive and false if dead.
	 
	 @param myNeighborhood The neighborhood that is being examined
	 @return The life of the updated cell
	 */
	public boolean updateCell(GoLNeighborhood myNeighborhood)
	{
    	//Adding how many neighbors are alive
		//liveNeighbors= number of neighbors that are alive
    	int liveNeighbors = (myNeighborhood.getCell(0,0).isAlive()?1:0) + (myNeighborhood.getCell(1,0).isAlive()?1:0) +
    		(myNeighborhood.getCell(2,0).isAlive()?1:0) + (myNeighborhood.getCell(0,1).isAlive()?1:0) +
    		(myNeighborhood.getCell(2,1).isAlive()?1:0) + (myNeighborhood.getCell(0,2).isAlive()?1:0) +
    		(myNeighborhood.getCell(1,2).isAlive()?1:0) + (myNeighborhood.getCell(2,2).isAlive()?1:0);

    	//Control life of the center cell depending on number of liveNeighbors
    	if (liveNeighbors == 3)
    	{
        	status = true;
    	}
    	else if (liveNeighbors == 2 && status)
    	{
        	status = true;
    	}
    	else
    	{
        	status = false;
    	}
   	 
    	//return the new status
    	return status;
	}
    
	/**
	 Returns whether the cell is alive or dead
	 
	 @return the state of the cell's life
	 */
	public boolean isAlive()
	{
    	return status;
	}
	
	/**
	 Returns a copy of the current GoLCell object
	 
	 @return A copy of the current GoLCell object
	 */
	public GoLCell getCopy()
	{
		return this;
	}
}
