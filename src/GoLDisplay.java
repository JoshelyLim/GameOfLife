import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@(#)GoLDisplay.java
 *
 *@author			Joshely Lim
 *@versionCurrent	4 2017/5/17
 *
 * This class implements a model for the whole board, displaying it 
 * on a GUI.
 */
public class GoLDisplay
{
	/*
	 * myFrame = the official JFrame for the GUI
	 * cellPane = the new JPanel for the GoLCells
	 * infoPane = the JPanel that will hold the buttons and dates
	 * 
	 * board = the display's version of GoLBoard
	 * myGoLCell = the display's current cells to be displayed
	 * setSize = the size that the user chose
	 */
  	 private JFrame myFrame = new JFrame("The Game of Life Final");
  	 private JPanel cellPane = new JPanel();
	 private JPanel infoPane = new JPanel();
	 
  	 private GoLBoard board;
  	 private GoLCell[][] myGoLCell;
  	 private int setSize;
  	 
  	 /**
  	  *Constructor that creates a new display, using the given board
  	  *Changes the values according to the board of interest
  	  *
  	  *@param board The board that is being displayed
  	  */
	public GoLDisplay(GoLBoard board)
	{
		/*
		 * installing the values from the gameBoard
		 */
		this.board = board;
		setSize = board.getSize();
		myGoLCell = board.getCells();
	}
	
	/**
	 * Changes the value of the board in GoLDisplay, using the given board
	 * 
	 * @param board The board at play
	 */
	public void setBoard(GoLBoard board)
	{
		this.board = board;
		setSize = board.getSize();
		myGoLCell = board.getCells();
	}
	
	/**
	 Method that adds the GUI components
	 and initiates the frame officially
	 */
	public void displayBoard()
	{  	 	   
		/*
		 * currCell=the cell that is being examined for its class
		 * compString=the string of the cell's class
		 * life=the life status of the cell being examined
		 */
         GoLCell currCell;
         String compString;
         boolean life; 
         
         //the layout is set to the default BorderLayout
	   	 
	   	 //Set the layout of cellPane to the size the user desires
	   	 cellPane.setLayout(new GridLayout(setSize,setSize));
	   	 
	    //Specify what happens when the close button is clicked
	   	//Change the backdrop to WHITE to match the blank cells
	    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    myFrame.setBackground(Color.WHITE);
	   	
	    //Clear the cellPane component
	    cellPane.removeAll();
	    
	    //Determine the true class of the cell and make the 
	    //color of the circle accordingly
        for (int currRow = 1; currRow <= setSize; currRow++)
        {
        	for (int currCol = 1; currCol <= setSize; currCol++)
        	{
        		currCell = myGoLCell[currRow][currCol];
        		compString = currCell.getClass().getName();
        		life = myGoLCell[currRow][currCol].isAlive();
        		
        		if (compString.contains("Strong") && life)
        		{
        			cellPane.add(new strongCircle());
        		}
        		else if (compString.contains("Weak") && life)
        		{
        			cellPane.add(new weakCircle());
        		}
        		else if (life)
        		{
        			cellPane.add(new normCircle());
        		}
        		else 
        		{
        			cellPane.add(new blankCircle());
        		}
        	}
        }
        
        //Add the cellPane to the center of the myFrame
        myFrame.add(cellPane, BorderLayout.CENTER);

	   	//Build the info panel
	    myFrame.add(buildInfoPanel(), BorderLayout.SOUTH);
	    
	    //Adjust the size accordingly
	    myFrame.pack();
	    
	    //Open the window
	    myFrame.setVisible(true);
	}
	
	/**
	 * A method that builds the panel for the buttons and specific time
	 * 
	 * @return The JPanel specifically for the info at the bottom of the GUI
	 */
	public JPanel buildInfoPanel()
	{
		//ButtonHandler Object
		ButtonHandler handler = new ButtonHandler();

		//Clear the infoPane
		infoPane.removeAll();
		
		//DateForm = the DateFormat object for getting the time
		//date = the official date
		//timeLabel = the label for the time
		//birthLabel = label for the birth
		//deathLabel = the label for death
	   	 DateFormat dateForm = 
	   			 new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   	 Date date = new Date();
	   	 JLabel timeLabel = new JLabel(dateForm.format(date));
	   	 JLabel birthLabel = 
	   			 new JLabel("Births: " + board.getBirths());
	   	 JLabel deathLabel = 
	   			 new JLabel("Deaths: " +board.getDeaths());
	   	 
	   	 //Post the time, births, and deaths
	   	infoPane.add(timeLabel);
	   	infoPane.add(birthLabel);
	   	infoPane.add(deathLabel);
		
		//Create the save button and attach listener
		JButton saveButton = new JButton("Save");
		saveButton.setActionCommand("Save");
		saveButton.addActionListener(handler);
		
		//Create the next button and attach listener
		JButton nextButton = new JButton("Next");
		nextButton.setActionCommand("Next");
		nextButton.addActionListener(handler);
		
		//Add the buttons components to the panel.
		infoPane.add(saveButton);
		infoPane.add(nextButton);
		
		//return the official panel with the info
		return infoPane;
	}
	
/**
 * Helper class that implements for when weak GoLCell is alive
 */
class weakCircle extends JPanel
{
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(0,0,this.getWidth(), this.getHeight());
	}
}

/**
 * Helper class that implements for when strong GoLCell is alive
 */
class strongCircle extends JPanel
{
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0,0,this.getWidth(), this.getHeight());
	}
}

/**
 * Helper class that implements for when normal GoLCell is alive
 */
class normCircle extends JPanel
{
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(0,0,this.getWidth(), this.getHeight());
	}
}

/**
 * Helper class that implements for when cell is dead
 */
class blankCircle extends JPanel
{
	
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0,0,this.getWidth(), this.getHeight());
	}
}

/**
 * Establish the button handler for the "Save" button and the "Next" button.
 * Implements the ActionListener class
 * 
 * @exception FileNotFoundException In case there is not file existent
 * @exception IOException any other exception from the IO
 */
private class ButtonHandler implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		//clickedButton = the official JButton getting the source
		JButton clickedButton = (JButton) e.getSource();
		
		//stringButton = get the text from the clicked button
		String stringButton = clickedButton.getText();
		
		//Determine which button was clicked and display a message
		if (stringButton.equals("Next"))
		{
			board = board.nextRound();
			setBoard(board);
			displayBoard();
		}
		else if (stringButton.equals("Save"))
		{
			String filename = "GoLSaved.ser";
    		//Create a binary file
    		//write the board object into the binary file
    		//close the binary file
    		try
    		{
    		ObjectOutputStream objOut =
    				new ObjectOutputStream(new FileOutputStream (filename));
    		
    		objOut.writeObject(board);
    		
    		objOut.close();
    		} catch (FileNotFoundException e1)
    		{
    			e1.printStackTrace();
    		} catch (IOException e1)
    		{
    			e1.printStackTrace();
    		}
		}
	}
}

}
