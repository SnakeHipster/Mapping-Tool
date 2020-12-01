package org.Assignment.MappingTool;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DjikstrasMapTest {
	
	/**
	 * tests the algorithm
	 */
	@Test
	public void algorithmTest(){
		DjikstrasMap m = new DjikstrasMap();	//Creates new DjikstrasMap object
		
		m.addRoad("Guildford", "Portsmouth", 20.5, "M3");	//Adds a road from Guildford to portsmouth to the map
        m.addRoad("Portsmouth", "Guildford", 20.5, "M3");
        
        m.addRoad("Guildford", "Winchester", 40.5, "A33");	//Adds a road from Guildford to winchester to the map
        m.addRoad("Winchester", "Guildford", 40.5, "A33");
        
        m.addRoad("Winchester", "Fareham", 30.5, "M27");	//Adds a road from winchester to fareham to the map
        m.addRoad("Fareham", "Winchester", 30.5, "M27");
		
        m.addRoad("Portsmouth", "Fareham", 20.5, "M25");	//Adds a road from fareham to portsmouth to the map
        m.addRoad("Fareham", "Portsmouth", 20.5, "M25");
        
        m.findShortestPath("Fareham");	//Starts the algorithm to find the shortest path
        
        List<String> temp = new ArrayList<String>();	//Holds the route plan
        temp = m.createRoutePlan("Guildford");	//Calls methods to assign the route plan to temp
        
        assertEquals("1. Take the M25 to Portsmouth",temp.get(0));	//tests that the different lines in the route plan are correct and that the algorithm did indeed take the shortest route
        assertEquals("2. Take the M3 to Guildford", temp.get(1));
        assertEquals("", temp.get(2));
        assertEquals("The Journey will take 41 hours and .00 minutes long.", temp.get(3));
        assertEquals("", temp.get(4));
        
        assertEquals(5, temp.size());	//tests to see that the route plan is 5 line large as it should be
	}
	
	/**
	 * Tests that the algorthim still works when multiple solutions are available
	 */
	@Test
	public void multipleSolutionsTest(){
		DjikstrasMap m = new DjikstrasMap();	//Creates new DjikstrasMap object
		
		m.addRoad("Guildford", "Portsmouth", 20.5, "M3");	//Adds a road from Guildford to portsmouth to the map
        m.addRoad("Portsmouth", "Guildford", 20.5, "M3");
        
        m.addRoad("Guildford", "Winchester", 20.5, "A33");	//Adds a road from Guildford to winchester to the map
        m.addRoad("Winchester", "Guildford", 20.5, "A33");
        
        m.addRoad("Winchester", "Fareham", 20.5, "M27");	//Adds a road from winchester to fareham to the map
        m.addRoad("Fareham", "Winchester", 20.5, "M27");
		
        m.addRoad("Portsmouth", "Fareham", 20.5, "M25");	//Adds a road from fareham to portsmouth to the map
        m.addRoad("Fareham", "Portsmouth", 20.5, "M25");
        
        m.findShortestPath("Fareham");	//Starts the algorithm to find the shortest path
        
        List<String> temp = new ArrayList<String>();	//Holds the route plan
        temp = m.createRoutePlan("Guildford");	//Calls methods to assign the route plan to temp
        
        assertEquals("1. Take the M27 to Winchester",temp.get(0));	//tests that the different lines in the route plan are correct and that the algorithm did indeed take the shortest route
        assertEquals("2. Take the A33 to Guildford", temp.get(1));
        assertEquals("", temp.get(2));
        assertEquals("The Journey will take 41 hours and .00 minutes long.", temp.get(3));
        assertEquals("", temp.get(4));
        
        assertEquals(5, temp.size());	//tests to see that the route plan is 5 line large as it should be
	}
	
	/**
	 * tests the convertToTime method
	 */
	@Test
	public void convertToTimeTest(){
		DjikstrasMap m = new DjikstrasMap();	//Creates new DjikstrasMap object
		double time = 1.85;		
		assertEquals("2 hour and .51 minutes", m.convertToTime(time));	//Checks that the convertToTime method formats the time correctly
	}

	/**
	 * tests the addRoad method
	 */
	@Test
	public void addRoadTest(){
		DjikstrasMap m = new DjikstrasMap();	//Creates new DjikstrasMap object
		Location l1 = new Location("Guildford");
		Location l2 = new Location("Portsmouth");
		
		m.addRoad("Guildford", "Portsmouth", 20.5, "M3");	//Adds a road from Guildford to portsmouth to the map
        m.addRoad("Portsmouth", "Guildford", 20.5, "M3");
        
        assertEquals(l1.getName(), m.getNode("Guildford").getName());	//Tests that the method added the roads correctly to teh map
        assertEquals(l2.getName(), m.getNode("Portsmouth").getName());
	}
}
