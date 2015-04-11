package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.SpaceShip;


public class TestDrawable {
	SpaceShip testDraw;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testDraw = new SpaceShip(2,50,152);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetImageNumber() {
		assertEquals("Should return the image number of 0", 0, testDraw.getImageNumber());
	}

	@Test
	public void testSetDirection() {
		testDraw.setDirection(55);
		assertEquals("Should return the direction of 55",55,testDraw.getDirection());
	}

	@Test
	public void testGetDirection() {
		testDraw.setDirection(55);
		assertEquals("Should return the direction of 55",55,testDraw.getDirection());
	}

		@Test
	public void testGetX() {
		testDraw.setX(123456);
		assertEquals("Should return the x of 123456",123456.0,testDraw.getX(),0.0);
	}

	@Test
	public void testGetmiddleX() {
		assertEquals("Should return the MiddleX of 112.0",112.0,testDraw.getmiddleX(),0.0);
		}

	@Test
	public void testGetmiddleY() {
		assertEquals("Should return the MiddleY of 221.0",221.0,testDraw.getmiddleY(),0.0);
	}

	@Test
	public void testSetX() {
		testDraw.setX(123456);
		assertEquals("Should return the x of 123456",123456.0,testDraw.getX(),0.0);
	}

	@Test
	public void testGetY() {
		testDraw.setY(123456);
		assertEquals("Should return the y of 123456",123456.0,testDraw.getY(),0.0);
	}

	@Test
	public void testSetY() {
		testDraw.setY(123456);
		assertEquals("Should return the y of 123456",123456.0,testDraw.getY(),0.0);
	}

	@Test
	public void testGetWidth() {
		testDraw.setWidth(125);
		assertEquals("The width of the testDraw is 125",125, testDraw.getWidth());
	}

	@Test
	public void testSetWidth() {
		testDraw.setWidth(125);
		assertEquals("The width of the testDraw is 125",125, testDraw.getWidth());
	}

	@Test
	public void testGetHeight() {
		testDraw.setHeight(1251);
		assertEquals("The Height of the testDraw is 1251",1251, testDraw.getHeight());
	}

	@Test
	public void testSetHeight() {
		testDraw.setHeight(1251);
		assertEquals("The height of the testDraw is 1251",1251, testDraw.getHeight());
	}

	@Test
	public void testUpdateMiddle() {
		testDraw.setWidth(25);
		testDraw.setHeight(25);
		testDraw.updateMiddle();
		assertEquals("The middle should be 62 164",62+" "+164, testDraw.getmiddleX()+" "+testDraw.getmiddleY());
	}

	@Test
	public void testSetPlacement() {
		double x = 100.4;
		double y = 122.3;
		int direction = 123;
		testDraw.setPlacement(x, y, direction);
		assertTrue("The value of x should be 100.4", x == testDraw.getX());
		assertTrue("The value of y should be 122.3", y == testDraw.getY());
		assertTrue("The value of direction should be 123", direction == testDraw.getDirection());
	
	}


}
