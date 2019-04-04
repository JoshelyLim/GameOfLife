import java.io.Serializable;

/**
@(#)GoLNeighborhood.java
@author			Joshely Lim
@versionCurrent	4 2017/5/17

Represents model for the neighborhood of an individual cell.
Implements a java.io.Serializable notifying that it may be serialized
*/
public class GoLNeighborhood implements java.io.Serializable
{
	//myNeighbors = array of neighbors surrounding the center cell
	private GoLCell[][] myNeighbors;
    
	/**
	 Creates a new neighborhood using the 3x3 array
	 
	 @param cellNeighbors The cells surrounding
	 */
	public GoLNeighborhood(GoLCell[][] cellNeighbors)
	{
    	myNeighbors = cellNeighbors;
	}
    
	/**
	 Gets the cell at coordinates x,y in the neighborhood
	 
	 @param x The x coordinates for the neighbor
	 @param y The y coordinates for the neighbor
	 */
	public GoLCell getCell(int x, int y)
	{
    	return myNeighbors[x][y];
	}
}
