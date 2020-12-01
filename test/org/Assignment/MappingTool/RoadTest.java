package org.Assignment.MappingTool;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoadTest {

	/**
	 * Constructor test
	 */
	@Test
	public void constructionTest(){
		Location l1 = new Location("Guildford"); 	//Creates a new Location object
		Location l2 = new Location("Fareham");	//Creates a new Location object
		Road road = new Road(l1, l2, 22.5, "M3");	 //Creates a new Road object
		
		assertEquals(road.getName(), "M3");		//Tests that the Object was created properly with correct variables and that the getters work as intended
		assertEquals(road.getStartLocation(), l1);		
		assertEquals(road.getFinishLocation(), l2);		
	}
}
