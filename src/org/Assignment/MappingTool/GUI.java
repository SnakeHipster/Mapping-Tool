package org.Assignment.MappingTool;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JComboBox;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JTextArea;

import java.awt.Toolkit;
import java.io.FileReader;
import java.io.IOException;

public class GUI {

	private JFrame frame;
	private JComboBox<String> cboStart;
	private JButton btnFindRoute;
	private JComboBox<String> cboFinish;
	private JScrollPane scrollPane;
	private JButton btnPrint;
	private JList<?> lstOutput;
	private JTextArea txtArea;
	List<String>locationsList = new ArrayList<String>();	//List of all the locations that are in the map, used for filling out the drop down boxes in the GUI
	DjikstrasMap g = new DjikstrasMap();		//Creates a new object of Djikstra's map used to hold the data read in from the files and run the algorithm


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		//Attempts to start up the GUI
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialises the contents of the frame.
	 */
	private void initialize() {
	
		loadMapData();
		
		frame = new JFrame();			//Defines the frame that the GUI is built in
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Simon\\Desktop\\New workspace\\sp00307_com1028_MappingToolPrototype\\Map Icon.png"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEnterStartLocation = new JLabel("Enter Start Location:");		//Defines the label for the start location drop down box
		GridBagConstraints gbc_lblEnterStartLocation = new GridBagConstraints();
		gbc_lblEnterStartLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterStartLocation.anchor = GridBagConstraints.EAST;
		gbc_lblEnterStartLocation.gridx = 0;
		gbc_lblEnterStartLocation.gridy = 1;
		frame.getContentPane().add(lblEnterStartLocation, gbc_lblEnterStartLocation);
		
		cboStart = new JComboBox<String>();		//Defines the drop down box for the start location
			GridBagConstraints gbc_cboStart = new GridBagConstraints();
		gbc_cboStart.insets = new Insets(0, 0, 5, 0);
		gbc_cboStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboStart.gridx = 1;
		gbc_cboStart.gridy = 1;
		frame.getContentPane().add(cboStart, gbc_cboStart);
		
		for (String i : locationsList){			//Adds list of locations to the drop down box
			cboStart.addItem(i);
		}

		JLabel lblEnterFinishLocation = new JLabel("Enter Finish Location:");		//Defines the label for the finish location drop down box
		GridBagConstraints gbc_lblEnterFinishLocation = new GridBagConstraints();
		gbc_lblEnterFinishLocation.anchor = GridBagConstraints.EAST;
		gbc_lblEnterFinishLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterFinishLocation.gridx = 0;
		gbc_lblEnterFinishLocation.gridy = 2;
		frame.getContentPane().add(lblEnterFinishLocation, gbc_lblEnterFinishLocation);
		
		btnFindRoute = new JButton("Find Route");		//Defines the button that is used to calculate the shortest route and then display the route plan
		btnFindRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cboStart.getSelectedItem() != "" && cboFinish.getSelectedItem() != "" && cboStart.getSelectedItem() != cboFinish.getSelectedItem()){	//Checks that both drop down boxes have been used and do not contain the same selection before printing the route plan
					long startTime = System.currentTimeMillis();	//Time used to track the time taken for the algorithm to run
									
					txtArea.setText("");	//Items have to be added into the text area in reverse order so that they display correctly
					txtArea.insert("You have reached your destination!\n",0);
					txtArea.insert("\n",0);
					g.findShortestPath(cboStart.getSelectedItem().toString());	//Starts djikstra's algorithm by parsing in the start point
					
					List<String> temp = new ArrayList<String>();	//Temporary list to hold each line of text for the route plan
					temp = g.createRoutePlan(cboFinish.getSelectedItem().toString());		// Gives the algorithm the finish point and retur's the route plan as a list to be stored in temp
					Collections.reverse(temp);	//Reverses the order of temp so it will display correctly on the GUI
					
					for (String i : temp){	//loops through each element in the temp list and inserts it as a string to the textArea
						txtArea.insert(i, 0);
						txtArea.insert("\n",0);	//New line
					}
						    
					txtArea.insert("Route plan starting at "+ cboStart.getSelectedItem() + " and ending at " + cboFinish.getSelectedItem()+"\n", 0);	//Adds the title
				
					long endTime = System.currentTimeMillis();	//Time used to track the time taken for the algorithm to run

					System.out.println("That took " + (endTime - startTime) + " milliseconds");		//Prints out the time it took for the route plan to be created in the console
					
				} else if (cboStart.getSelectedItem() == cboFinish.getSelectedItem()){	//Validates that both the two locations selected are not the same
					txtArea.setText("");
					JOptionPane.showMessageDialog(frame, "Start and finish locations must be different!");	//Throws error message to user
				} else {	//Validates that both a start and finish location have been selected
					txtArea.setText("");
					JOptionPane.showMessageDialog(frame, "You must select a start AND finish point from the drop down bars!");	//Throws error message to user
				}
			}
		});
		
		cboFinish = new JComboBox<String>();		//Defines the drop down box for the finish location
		GridBagConstraints gbc_cboFinish = new GridBagConstraints();
		gbc_cboFinish.insets = new Insets(0, 0, 5, 0);
		gbc_cboFinish.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboFinish.gridx = 1;
		gbc_cboFinish.gridy = 2;
		frame.getContentPane().add(cboFinish, gbc_cboFinish);
		GridBagConstraints gbc_btnFindRoute = new GridBagConstraints();
		gbc_btnFindRoute.insets = new Insets(0, 0, 5, 0);
		gbc_btnFindRoute.gridx = 1;
		gbc_btnFindRoute.gridy = 3;
		frame.getContentPane().add(btnFindRoute, gbc_btnFindRoute);
		
		for (String i : locationsList){			//Adds list of locations to the drop down box
			cboFinish.addItem(i);
		}
		
		btnPrint = new JButton("Print Route Plan");		//Defines the button that will be used to print the text thats currently in the textArea
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		//When the button is pressed a check will be made to ensure the textArea contains a route plan before printing if not a dialogue box id brought up 
				if (txtArea.getLineCount() == 1) {		//
					JOptionPane.showMessageDialog(frame,"A route must be caluclated before you can print!");
				} else {
					printToPrinter();
				}
			}
		});
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrint.gridx = 0;
		gbc_btnPrint.gridy = 4;
		frame.getContentPane().add(btnPrint, gbc_btnPrint);
		
		scrollPane = new JScrollPane();		//Defines the Scroll pane that the route plan textArea will be inside
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(lstOutput);
		
		txtArea = new JTextArea();		//Defines the textArea for the route plan to be listed
		scrollPane.setViewportView(txtArea);
		

	
		
	}
	
	/**
	 * Method used to print using the printer
	 */
	private void printToPrinter(){
	    String printData = txtArea.getText() + "\n";
	    PrinterJob job = PrinterJob.getPrinterJob();		//Creates a new object of type PrinterJob
	    job.setPrintable(new OutPrinter(printData));
	    boolean doPrint = job.printDialog();
	    if (doPrint){ 
	        try {
	            job.print();	//Opens up the print dialogue box
	        } catch (PrinterException e){
	        	JOptionPane.showMessageDialog(frame,"Printing Error!");	//Returns an error if the print dialogue bx could not be reached
	        }
	    }
	}
	
	/**
	 * Method used to read in the data from the .txt file ready for use by the algorithm 
	 */
	private void loadMapData(){
		
		  try{
	      FileReader mapFile = new FileReader("Map_Hampshire.txt");	//Reads in the file using a FileReader class 
	      Scanner scannedFile = new Scanner(mapFile);			//Parses the contents of the file into a scanner so that the data can be read a line at a time
	      
	      // Read the edges and insert
	      String newLine;
	      while(scannedFile.hasNextLine()){
	        newLine = scannedFile.nextLine();		//Reads the next line into a local string variable
	        StringTokenizer tokens = new StringTokenizer(newLine);	//Splits the line up into tokens so that the data can be assigned to the correct places
	        
	        try{	//Validation to make sure a incorrectly formatted line is not read in
	          if (tokens.countTokens() != 5){	//The line must have been split into 5 tokens otherwise it is incorrectly formatted
	            System.err.println("Incorrectly formatted line, skipping..." + newLine);
	            continue;
	          }
	          String location1 = tokens.nextToken();	//Reads in the first location as the first token
	          String location2 = tokens.nextToken();	//Reads in the second location as the second token
	          
	          boolean alreadyInList = false;		//Local variable used to determine whether the location is already in the list of locations
	          
	          for(String i : locationsList){	//Loops through the locationsList in its current state to check that the current line does not contain a location thats already in the list
	        	  if (i.equals(location1)){
	        		  alreadyInList = true;
	        	  }
	          }
	          if (alreadyInList == false){		//If the location is not already in the list then this adds it to the list
	        	  locationsList.add(location1);
	          }
	          
	          alreadyInList = false;
	          for(String i : locationsList){		//Does the same as the previous code but for the other location in the line
	        	  if (i.equals(location2)){
	        		  alreadyInList = true;
	        	  }
	          }
	          if (alreadyInList == false){
	        	  locationsList.add(location2);
	          }
	          
	          //Works out the time taken to traverse a road by dividing the 3rd token (distance in miles) by the 4th token (speed in mph)
	          double cost = (Double.parseDouble(tokens.nextToken())/Double.parseDouble(tokens.nextToken()));
	          
	          String name = tokens.nextToken();		//Defines the name of the road by the 5th token
	          g.addRoad(location1, location2, cost, name);	//Constructs the road for use with the algorithm
	          g.addRoad(location2, location1, cost, name);	//Constructs the road for use with the algorithm in the opposite direction as the map is not directional
	        } catch (NumberFormatException e){
	          System.err.println("Incorrectly formatted line, skipping..." + newLine);
	        } 
	      }
	    }
	    catch(IOException e){
	      System.err.println(e);    
	    }
		Collections.sort(locationsList); 	//Sorts the list of locations alphabetically
	}

}
