package org.Assignment.MappingTool;

import java.util.LinkedList;
import java.util.List;

public class Location {

	public String locationName = null;      	    // Locations name
	public List<Road> linkedRoads = null;      	  	// List containing all the available roads that are connected to this location
	public double tLength = 0.0;       				// Total cost to get to this node 
	public Location previousLocation = null;      	// Previous location in the path
	public boolean finished;      					// Variable used to show whether this loaction has been checked by the algorithm
	  
	/**
	 * Constructor for Location
	 * @param name
	 */
	public Location(String locationName){
		this.locationName = locationName; 
		linkedRoads = new LinkedList<Road>(); 
		reintialise(); 
	}
	  
	/**
	 * Method used to reset the variables used in the algorithm so that it can be run again without problems
	 */
	public void reintialise(){  
		tLength = DjikstrasMap.INFININITE;
		previousLocation = null;
		finished = false;
	  }

	/**
	 * Getter for the location's name
	 * @return
	 */
	public String getName() {
		return locationName;
	} 
}
