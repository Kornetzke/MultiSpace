package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.SpaceJunk;

public class TestSpaceJunk {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSpaceJunk() {
		SpaceJunk testClass = new SpaceJunk(2,40,50);
		assertEquals("The image number should be 2",2,testClass.getImageNumber());
		assertEquals("The x location should be 40",40,(int)testClass.getX());
		assertEquals("The y location should be 50",50,(int)testClass.getY());
		testClass = new SpaceJunk(1,80,-50);
		assertEquals("The image number should be 1",1,testClass.getImageNumber());
		assertEquals("The x location should be 80",80,(int)testClass.getX());
		assertEquals("The y location should be -50",-50,(int)testClass.getY());
	}

}
