package org.Assignment.MappingTool;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DjikstrasMap {

	public static final double INFININITE = Double.MAX_VALUE;			// Variable used to start the current shortest paths as infinity (used at the very start of the algorithm)
	private Map<String,Location> djiktrasMap = new HashMap<String,Location>();		//HashMap used to hold the map
	
	/**
	 * Method used to add a new road into the map
	 * @param sourceName
	 * @param destName
	 * @param cost
	 * @param name
	 */
	public void addRoad(String sourceName, String destName, double length, String name){
		Location temp1 = getNode(sourceName);		//Temporary	Locations object that hold the details of the start and finish points of the road
	    Location temp2 = getNode(destName);
	    
	    temp1.linkedRoads.add(new Road(temp1, temp2, length, name));	//Adds the road to the locations list of linked roads in both directions so that the road can be traversed either way
	    temp2.linkedRoads.add(new Road(temp2, temp1, length, name)); 
	}
	  
	    
	/**
	 * Method that uses djiktras algorithm to find the shortest path and create the path
	 * It does this by assigning the location object's variable 'previousLocation' with the details of the previous location in the path
	 * @param startName
	 */
	public void findShortestPath(String startName){
	      
		PriorityQueue<Path> queue = new PriorityQueue<Path>();		//Priority queue used to track discovered roads and is used to find the shortest ones
	    Location startLocation = djiktrasMap.get(startName);			//Local variable used to hold the starting location for the algorithm

	    clearAll();	//Resets all the variables used in the algorithm ready for a clean run through
	     
	    queue.add(new Path(startLocation, 0));	//Adds the start location to the priority queue
	    startLocation.tLength = 0;		//Sets the total cost to get to this node as 0 as it is the start point
	      
	    int locationsVisited = 0;		//local variable used to keep track of how many locations have been seen so far
	    while (!queue.isEmpty() && locationsVisited < djiktrasMap.size()){		//While loop that keeps running until all locations have been visited and the priority queue has been emptied
	    	Path mostRecentLocation = queue.remove();   // Takes the previous shortest path out of the queue so that the next stage can be found
	    	Location pLocation = mostRecentLocation.location;      // Local variable to hold the previous shortest paths end location
	    	 
	        if (pLocation.finished == false){       // Checks that this path has not already been checked
	        	pLocation.finished = true;          // Changes the locations checked flag to true
	        	locationsVisited++;		
	        
	        	for(Road i : pLocation.linkedRoads){	//Loops through each road connected to the road we are checking
	        		Location temp = i.finishLocation;	//Temporary storage for the finish location on the current road we are checking
	        		double tempLength = i.length;		//Holds the time taken to potentially traverse this road
	          
	        		if (temp.tLength > (pLocation.tLength + tempLength)){
	        			temp.tLength = pLocation.tLength + tempLength;
	        			temp.previousLocation = pLocation;
	        			queue.add(new Path(temp, temp.tLength));   // Adds a new path to the priority queue if it is better than the previous best
	        		}
	        	}
	        }
	    }
	}

	/**
	 * Method used to return the shortest path as a readable route plan and return it as an list of strings
	 * @param destName
	 * @return
	 */
	public List<String> createRoutePlan(String destName){
		List<String> result = new ArrayList<String>();		//Local variable that will hold the list that will be returned once the method is complete
	    String temp = new String();	//Temporary string to hold all the location and road names in order
	    Location destinationLocation = djiktrasMap.get(destName);	//Local variable to hold the destination location
	  
	    temp = temp.concat(findPath(destinationLocation));	//Calls the recursive method findPath to add all the location and road names to temp in the order that they are used
	    
	    StringTokenizer tempTokens = new StringTokenizer(temp);		//Tokenises temp so that the location and road names can be split into two different lists
	    List<String> tempList = new ArrayList<String>();		//Will hold all the road names
	    List<String> locationNameList = new ArrayList<String>();	//Will hold all the location names
	      	
	    while (tempTokens.hasMoreTokens()==true){	//Loops through each token in tempTokens
	    	tempList.add(tempTokens.nextToken()); 	//Adds the next token which is a road name to the list of roads traversed
	    	if (tempTokens.hasMoreTokens()==true){	//Checks that there are still more tokens left in tempTokens
	    		locationNameList.add(tempTokens.nextToken()); 	//Adds the next token which is a location name to the list of locations traversed
	    	}
	    }
	   
	    int i = 1;	//Local variable used as an index
        for (String j : tempList){		//Loops through each road name in tempList
        	result.add(i +". Take the " + j + " to " + locationNameList.get(i-1));	//Creates the next line on the route plan by taking the road name and location name required
	    	i++;		//Increment index
	    }
	    
	    String time = convertToTime(destinationLocation.tLength);	//Uses the convertToTime method to return a string with the journey time
	    result.add("");
	    result.add("The Journey will take " + time + " long.");		//Adds the journey time string to the result
	    result.add("");
	    return result;
	}
	        
	/**              
	 * Recursive routine that steps through the shortest path that has already been found by the algorithm to print it as a string
	 * @param dest
	 * @return
	 */
	public String findPath(Location dest){
		String ans = new String();	//Holds the resulting string
	    String temp = null;		//Temporary string
	    
	    if (dest.previousLocation != null){		//Checks that there are still more locations in the path
	    	ans = findPath(dest.previousLocation); //Adds the previous location to the string
	        ans = ans.concat(" ");		//Adds a space to the string so it can be tokenised later on
	        
	        for (Road i: dest.linkedRoads){		//Loops the roads connecting the current location
	        	if ((i.startLocation == dest.previousLocation && i.finishLocation == dest) || (i.startLocation == dest && i.finishLocation == dest.previousLocation)){
	        		temp = i.getName();		//Sets temp to the name of the road if the road is found to be on the shortest path
	        	}
	        }
	        ans = ans.concat(temp);		//Adds the name of the road to the output string
	        ans = ans.concat(" ");			//Adds a space to the string so it can be tokenised later on
	        ans = ans.concat(dest.locationName);		//Adds the name of the current location to the output sting
	    }
	    return ans;
	}
	        
	    
	    
	/**
	 * Method used to reinitialise all the values of the location objects so that the algorithm can be run again
	 */
	private void clearAll(){
		for (Location v : djiktrasMap.values()){
			v.reintialise();
	    }
	}         
	    
	/**
	 * Method used to get the Location object from the locations name  
	 * @param vertexName
	 * @return
	 */
	protected Location getNode(String vertexName){
		Location v = djiktrasMap.get(vertexName);
		if (v == null){	//Validates that the location exists, if it does not then it adds it to the map
			v = new Location(vertexName);
			djiktrasMap.put(vertexName, v);
		}
		return v;
	}
	 
	/**
	 * Method used to convert the total time taken to traverse the shortest path into a user friendly string to read
	 * @param time
	 * @return
	 */
	public String convertToTime(double time){
	    DecimalFormat mf = new DecimalFormat("#.00");		//Defines the format for the minutes
	    DecimalFormat hf = new DecimalFormat("0"); 			//Defines the format for the hours
	    
	    String result = new String();		//String to hold the resulting string
	    String number = String.valueOf(time);	//Temp string used to hold the parsed in double as a string so it can be formatted
        number = number.substring(number.indexOf("."));	//Removes the numbers before the decimal point
       
	    double temp = Double.parseDouble(number);	//Parses the string back into a double
    
	    temp = ((temp / 100) * 60);	//Converts the decimal to proper minute format
	    
	    if (time >= 1.00 && time < 2.00){	//If the time is between 1 and two hours long
	    	result = (hf.format(time) + " hour and " + mf.format(temp) + " minutes");
	    } else if (time >= 2.00) {		//If the time is larger than 2 hours long
	    	result = (hf.format(time) + " hours and " + mf.format(temp) + " minutes");
	    } else {	//If the time taken is less than an hour
	    	result = mf.format(temp) + " minutes";
	    } 
	    
		return result;
	}
}
