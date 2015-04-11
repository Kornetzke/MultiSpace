package unitTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.HitBox;

public class TestHitBox {
HitBox testBox;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Point[] points = new Point[3];
		points[0] = new Point(1,2);
		points[1] = new Point(100,200);
		points[2] = new Point(200,100);
		testBox = new HitBox(points, new Point(5,6));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetHitbox() {
		Point[] points = new Point[5];
		points[0] = new Point(1,2);
		points[1] = new Point(10,20);
		points[2] = new Point(200,100);
		points[3] = new Point(50,300);
		points[4] = new Point(300,50);
		testBox.setHitbox(points, new Point(6,7));
		assertTrue("The first point should be 1,2",1==testBox.getPolyPoints()[0].x && 2==testBox.getPolyPoints()[0].y);
		
		
	}

	@Test
	public void testGetPolyPoints() {
		Point[] points = new Point[5];
		points[0] = new Point(1,2);
		points[1] = new Point(10,20);
		points[2] = new Point(200,100);
		points[3] = new Point(50,300);
		points[4] = new Point(300,50);
		testBox.setHitbox(points, new Point(6,7));
		assertTrue("The first point should be 1,2",1==testBox.getPolyPoints()[0].x && 2==testBox.getPolyPoints()[0].y);
		
	}

}
