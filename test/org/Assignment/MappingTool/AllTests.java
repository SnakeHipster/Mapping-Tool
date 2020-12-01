package org.Assignment.MappingTool;

/**
 * AllTests.java
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *  Class that will run all test classes
 * @author Simon Page
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ DjikstrasMapTest.class, GUITest.class, LocationTest.class, OutPrinterTest.class, PathTest.class, RoadTest.class })
public class AllTests {
}
