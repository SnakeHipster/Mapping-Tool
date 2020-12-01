package org.Assignment.MappingTool;

class Path implements Comparable<Path>{
	
	public Location location = null;    // holds the location of the current path
	public double length = 0.0;            // holds the length of the current path (time taken to traverse)
	  
	/**
	 * Constructor for Path
	 * @param endLocation
	 * @param length
	 */
	public Path(Location location, double length){
		this.location = location;
	    this.length = length;
	}
	  
	/**
	 * Method used to compare path lengths
	 */
	public int compareTo(Path path){
		double otherLength = path.length;
	    return length < otherLength ? -1 : length > otherLength ? 1 : 0; //// Used to compare path lengths
	}
}
