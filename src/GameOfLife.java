import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
@(#)GameOfLife.java
@author			Joshely Lim
@versionCurrent	4 2017/5/17

The executable controller class
	Initialize other objects and control stepping through the
 	rounds, calling the appropriate methods in both the
	GoLBoard class and the GoLDisplay class. It should
	not contain any code related to updating cells, etc

 @exception FileNotFoundException if the file does not exist
 @exception IOException if any failed or interrupted IO Exceptions
 @exception ClassNotFoundException if no class definition
*/
public class GameOfLife
{
	public static void main(String[] args)
	{
   	 /*
   	  * stringCheck = checking how the player wants the program to behave
   	  * keyboard = Scanner object
   	  * boardSize = the size of the board
   	  * board = official GoLBoard
   	  * display = official GoLDisplay
   	  * filename = the name of the binary file in which object will be saved
   	  */
   	 String stringCheck;
   	 Scanner keyboard = new Scanner(System.in);
   	 int boardSize = 20; 
	 GoLBoard board = new GoLBoard(20);
	 GoLDisplay display;
	 String filename = "GoLSaved.ser";
	 
   	 //Ask the user how he would like the system to behave
   	 System.out.print("How would you like the system to behave?");
   	 stringCheck = keyboard.nextLine(); 

   	 
 	 //If the player wants to resume the last game,
 	 //de-serializes the GoLBoard object in the binary file "saved.ser"
 	 //and uses the saved GoLBoard object for the official game's board.
 	 //objIn = an ObjectInputStream object
 	 if (stringCheck.contains("resume"))
 	 {
 		 try
 		 {
 		 ObjectInputStream objIn =
 			new ObjectInputStream(new FileInputStream (filename));
 		
 		 board = (GoLBoard) objIn.readObject();
 		
 		 objIn.close();
		 } catch (FileNotFoundException e) 
		 {
			e.printStackTrace();
			System.out.print("No saved file exists. "+
						"Please start the game again.");
			System.exit(0);
			board = null;
		 } catch (IOException e) 
		 {
			e.printStackTrace();
			System.out.print("Error.");
			System.exit(0);
			board = null;
		 } catch (ClassNotFoundException e)
 		 {
 		    e.printStackTrace();
 			System.out.print("No class found.");
 			board = null;
 			System.exit(0);
 		 }

 	 	}
    	//Launch the extended version
   	 	else if  (stringCheck.contains("extended")
    			&& !stringCheck.contains("size"))
	   	{
	   		board = new GoLBoard(new GoLExtRandomInitializer(boardSize), boardSize);
	   		System.out.println("Launching extended version.");
	   	}
    	
    	//Launch the extended version with size change
    	else if (stringCheck.contains("extended") 
    			&& stringCheck.contains("size"))
    	{
    		boardSize = getSubInt(stringCheck); 	//get requested size
    		
    		//start the extended version with a different size
    		board = new GoLBoard(new 
    				GoLExtRandomInitializer(boardSize), boardSize);
    		
    		System.out.println("Launching extended version with size: " +boardSize);
    	}
    	
    	//Launch standard version with size change
    	else if(stringCheck.contains("size"))
    	{
    		boardSize = getSubInt(stringCheck); 	//get requested size
    		
    		board = new GoLBoard(new 
    				GoLRandomInitializer(boardSize), boardSize);
    		
    		System.out.println("Launching standard version with size: " +boardSize);
    	}	
    	
    	//Launch standard version
    	else
    	{
    		board = new GoLBoard(boardSize);
    		//Let player know the standard version is running
    		System.out.println("Launching standard version.");
    	}
 	 	
 	 	//Define display and set the board
 	 	display = new GoLDisplay(board);
 	 	display.displayBoard();
	}
 	 	
	
	/**
	 * A static method that returns the integer value following
	 * the word "size".
	 * 
	 * @param strCheck How the player wants the game to behave
	 * @return the integer following the word "size"
	 */
	public static int getSubInt(String strCheck)
	{
		//currString = StringBuilder object
		//startingIndex = get the last index of "size"
		//stringLen = the length of the focused String
		//newIntArray = the integers following "size"
		//strNum = the StringBuilder object
		StringBuilder currString = new StringBuilder(strCheck);
		int startingIndex = currString.lastIndexOf("size");
		int stringLen = strCheck.length();
		List<Integer> newIntArray = new ArrayList<Integer>();
		StringBuilder strNum = new StringBuilder();
		
		//i = place in the newIntArray
		for (int i = startingIndex; i < stringLen; i++)
		{
			//check if it is a digit
			if (Character.isDigit(currString.charAt(i)))
			{
				newIntArray.add(Character.getNumericValue(currString.charAt(i)));
			}
			//nothing is done otherwise	
		}
		
		//Change the intArray into a string
		for (int num : newIntArray)
		{
			strNum.append(num);
		}
		
		//convert the strNum into an integer and return
		return Integer.parseInt(strNum.toString());
	}
}

