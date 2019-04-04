/**
 @(#)GoLStrongCell.java
 @author			Joshely Lim
 @versionCurrent	4 2017/5/17
 
 A dead StrongCell with either two or three live neighbors
 		becomes a live cell. 
 A live StrongCell with one to four live neighbors stays alive
 A subclass of the GoLCell
 */
public class GoLStrongCell extends GoLCell
{	
	/**
	 Creates an instance of the GoLStrongCell with 
	 a specific state of life
	 
	 @param myStatus The status of the cell
	 */
	public GoLStrongCell(boolean myStatus)
	{
		super(myStatus);
	}
	
	/**
	 Updates the strong cell using the given neighborhood
	 and returns true if alive and false if dead
	 
	 @param myNeighborhood the neighborhood surrounding the focused cell
	 @return boolean whether the new cell will be alive or dead
	 */
	public boolean updateCell(GoLNeighborhood myNeighborhood)
	{
	  	/*
     	* liveNeighbors = how many neighbors are alive
     	*/
   	 
    	//Determine how many living neighbors surround the target cell
    	int liveNeighbors = (myNeighborhood.getCell(0,0).isAlive()?1:0) + (myNeighborhood.getCell(1,0).isAlive()?1:0) +
        		(myNeighborhood.getCell(2,0).isAlive()?1:0) + (myNeighborhood.getCell(0,1).isAlive()?1:0) +
        		(myNeighborhood.getCell(2,1).isAlive()?1:0) + (myNeighborhood.getCell(0,2).isAlive()?1:0) +
        		(myNeighborhood.getCell(1,2).isAlive()?1:0) + (myNeighborhood.getCell(2,2).isAlive()?1:0);

   	 
    	//if 2 or 3 neighbors, cell comes alive/stays alive
    	if (liveNeighbors == 2 || liveNeighbors == 3)
    	{
        	super.status = true;
    	}
    	//if 0 < liveNeighbors < 5, cell stays alive
    	else if (liveNeighbors > 0 && liveNeighbors < 5 
    				&& super.isAlive())
    	{
        	super.status = true;
    	}
    	//all other instances, cell dies
    	else 
    	{
        	super.status = false;
    	}
   	 
    	//return the new status
    	return super.status;
	}
	
	/**
	 Return the type of cell
	 
	 @return The Strong Cell type
	 */
	public GoLStrongCell getCopy()
	{
		return this;
	}
}
