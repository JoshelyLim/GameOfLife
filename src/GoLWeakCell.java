/**
 @(#)GoLWeakCell.java
 @author			Joshely Lim
 @versionCurrent	4 2017/5/17
 
 A dead WeakCell with exactly four live neighbors
 		becomes a live cell.
 A live WeakCell with three or four live neighbors 
 		stays alive
 A subclass of the GoLCell class
 */
public class GoLWeakCell extends GoLCell
{
	/**
	 Creates an instance of the GoLWeakCell with 
	 a specific state of life
	 
	 @param myStatus whether the cell is alive or dead
	 */
	public GoLWeakCell(boolean myStatus)
	{
		super(myStatus);
	}
	
	/**
	 Updates the weak cell using the given neighborhood
	 and returns true if alive and false if dead
	 
	 @param myNeighborhood the neighborhood surrounding the focused cell
	 @return boolean whether the new cell will be alive or dead
	 */
	public boolean updateCell(GoLNeighborhood myNeighborhood)
	{
	  	/*
     	* liveNeighbors = how many neighbors are alive
     	*/
   	 
    	//Establish how many living neighbors are surrounding
    	int liveNeighbors = (myNeighborhood.getCell(0,0).isAlive()?1:0) + (myNeighborhood.getCell(1,0).isAlive()?1:0) +
        		(myNeighborhood.getCell(2,0).isAlive()?1:0) + (myNeighborhood.getCell(0,1).isAlive()?1:0) +
        		(myNeighborhood.getCell(2,1).isAlive()?1:0) + (myNeighborhood.getCell(0,2).isAlive()?1:0) +
        		(myNeighborhood.getCell(1,2).isAlive()?1:0) + (myNeighborhood.getCell(2,2).isAlive()?1:0);
   	 
    	//if the liveNeighbors is equal to 4, the cell comes alive or stays alive
    	if (liveNeighbors == 4)
    	{
        	super.status = true;
    	}
    	//if the liveNeighbors is three, the cell stays alive
    	else if (liveNeighbors == 3 && super.status)
    	{
        	super.status = true;
    	}
    	//in all other instances, the cell dies
    	else
    	{
        	super.status = false;
    	}
   	 
    	//return the new status
    	return super.status;
	}
	
	/**
	 Return the type of cell
	 
	 @return The Weak Cell type
	 */
	public GoLWeakCell getCopy()
	{
		return this;
	}
}
