package org.Assignment.MappingTool;

public class Road {

	public Location startLocation = null;  // Location to hold the start point of the road
	public Location finishLocation = null;   // Location to hold the finish point of the road
	public double length = 0.0;    // Length of the road (time taken to traverse)
	public String roadName = null;		//Name of the road
	  
	/**
	 * Constructor for road
	 * @param startLocation
	 * @param finishLocation
	 * @param length
	 * @param name
	 */
	public Road(Location startLocation, Location finishLocation, double length, String name){
		this.startLocation = startLocation;
	    this.finishLocation = finishLocation;
	    this.length = length;
	    this.roadName = name;
	}

	/**
	 * Getter for start location
	 * @return
	 */
	public Location getStartLocation() {
		return startLocation;
	}

	/**
	 * Getter for finish location
	 * @return
	 */
	public Location getFinishLocation() {
		return finishLocation;
	}

	/**
	 * Getter for name
	 * @return
	 */
	public String getName() {
		return roadName;
	}
}
