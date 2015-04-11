package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.Direction;

public class TestDirection {

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
	public void testGetDirection() {
		Direction tester = new Direction(120);
		assertEquals("Direction 120 should return 120", 120, tester.getDirection());
		tester.setDirection(450);
		assertEquals("Direction 450 should return 0",0,tester.getDirection());
		tester.setDirection(-180);
		assertEquals("Direction -180 should return 0",0,tester.getDirection());
		tester.setDirection(50);
		assertEquals("Direction 50 should return 50",50,tester.getDirection());
	}

	@Test
	public void testSetDirection() {
		Direction tester = new Direction(0);
		tester.setDirection(120);
		assertEquals("Direction 120 should return 120", 120, tester.getDirection());
		tester.setDirection(450);
		assertEquals("Direction 450 should return 0",0,tester.getDirection());
		tester.setDirection(-180);
		assertEquals("Direction -180 should return 0",0,tester.getDirection());
		tester.setDirection(50);
		assertEquals("Direction 50 should return 50",50,tester.getDirection());
	}

	@Test
	public void testIncrementDirection() {
		Direction tester = new Direction(120);
		tester.incrementDirection(12);
		assertEquals("Direction of 120 increment 12 should be 132", 132, tester.getDirection());
		tester.setDirection(132);
		tester.incrementDirection(-140);
		assertEquals("Direction of 132 increment -140 should be 352", 352, tester.getDirection());
		tester.setDirection(352);
		tester.incrementDirection(-140);
		assertEquals("Direction of 352 increment 120 should be 212", 212, tester.getDirection());
		
	}

	@Test
	public void testGetRotationDegrees() {
		Direction tester = new Direction(120);
		assertEquals("Direction of 120 going to 180", 60, tester.getRotationDegrees(180));
		assertEquals("Direction of 120 going to -180", 60, tester.getRotationDegrees(-180));
		assertEquals("Direction of 120 going to 45", -75, tester.getRotationDegrees(45));
		assertEquals("Direction of 120 going to 255", 135, tester.getRotationDegrees(255));
		assertEquals("Direction of 120 going to -45", -165, tester.getRotationDegrees(-45));
		
	}

}
