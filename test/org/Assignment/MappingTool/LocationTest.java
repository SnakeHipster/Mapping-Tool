package org.Assignment.MappingTool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LocationTest {

	/**
	 * Constructor Test
	 */
	@Test
	public void constructionTest(){
		Location l1 = new Location("Guildford");	//Creates a new Location object
		assertEquals(l1.getName(), "Guildford");	//Tests that the Object was created properly c
	}
	
}
