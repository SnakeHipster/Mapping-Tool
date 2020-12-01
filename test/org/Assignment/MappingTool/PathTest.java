package org.Assignment.MappingTool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathTest {

	/**
	 * Constructor test
	 */
	@Test
	public void constructionTest(){
		Location l1 = new Location("Guildford");	//Creates a new Location object
		Path path = new Path(l1, 22.5);		//Tests that the Object was created properly //Tests that the Object was created properly
	}
}
