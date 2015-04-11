package unitTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import menu.GameButton;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.Board;

public class TestGameButton {
	GameButton testButton;
	static Board testBoard;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testBoard = new Board();
		Board.width=500;
		Board.height = 500;
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testButton = new GameButton(new Point(2,2), Color.white,Color.lightGray, "TestMessage");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChangeInsideColor() {
		testButton.changeInsideColor(Color.black);
		assertEquals("Changing Inside Color to black",true,Color.black == testButton.getInsideColor());
		testButton.changeInsideColor(Color.WHITE);
		assertEquals("Changing Inside Color to WHITE",true,Color.WHITE == testButton.getInsideColor());
		testButton.changeInsideColor(Color.gray);
		assertEquals("Changing Inside Color to gray",true,Color.GRAY == testButton.getInsideColor());
		testButton.changeInsideColor(Color.RED);
		assertEquals("Changing Inside Color to RED",true,Color.RED == testButton.getInsideColor());
		testButton.changeInsideColor(Color.YELLOW);
		assertEquals("Changing Inside Color to YELLOW",true,Color.YELLOW == testButton.getInsideColor());
	
	}

	@Test
	public void testChangeOutsideColor() {
		testButton.changeOutsideColor(Color.black);
		assertEquals("Changing outside Color to black",true,Color.black == testButton.getOutsideColor());
		testButton.changeOutsideColor(Color.WHITE);
		assertEquals("Changing outside Color to WHITE",true,Color.WHITE == testButton.getOutsideColor());
		testButton.changeOutsideColor(Color.gray);
		assertEquals("Changing outside Color to gray",true,Color.GRAY == testButton.getOutsideColor());
		testButton.changeOutsideColor(Color.RED);
		assertEquals("Changing outside Color to RED",true,Color.RED == testButton.getOutsideColor());
		testButton.changeOutsideColor(Color.YELLOW);
		assertEquals("Changing outside Color to YELLOW",true,Color.YELLOW == testButton.getOutsideColor());
	
		
	}

	@Test
	public void testUpdate() {
		assertTrue("The point 10,10 should be in the textField",testButton.contain(new Point(10,10)));
		assertFalse("The point 800,800 should NOT be in the textField",testButton.contain(new Point(800,800)));
		testButton.update(new Point(789,789));
		assertFalse("The point 10,10 should NOT be in the textField",testButton.contain(new Point(10,10)));
		assertTrue("The point 800,800 should be in the textField",testButton.contain(new Point(800,800)));
	
	}

	@Test
	public void testContain() {
		testButton.setBounds(new Dimension(500,500));
		assertEquals("The point 400,300 should be inside",true,testButton.contain(new Point(400,300)));
		testButton.setBounds(new Dimension(500,500));
		assertEquals("The point 4004,3003 should be outside",false,testButton.contain(new Point(4004,3003)));
		testButton.setBounds(new Dimension(500,500));
		assertEquals("The point 7,7 should be inside",true,testButton.contain(new Point(7,7)));
		testButton.setBounds(new Dimension(500,500));
		assertEquals("The point 492,492 should be inside",true,testButton.contain(new Point(492,492)));
	}
	@Test
	public void testGetInsideColor() {
	assertEquals("Inside Color should be white",Color.white,testButton.getInsideColor());
	testButton.changeInsideColor(Color.black);
	assertEquals("Inside Color should be black",Color.black,testButton.getInsideColor());
	testButton.changeInsideColor(Color.gray);
	assertEquals("Inside Color should be gray",Color.gray,testButton.getInsideColor());
	testButton.changeInsideColor(Color.red);
	assertEquals("Inside Color should be red",Color.red,testButton.getInsideColor());
	}
	@Test
	public void testGetOutsideColor() {
		assertEquals("outside Color should be light gray",Color.lightGray,testButton.getOutsideColor());
		testButton.changeOutsideColor(Color.black);
		assertEquals("outside Color should be black",Color.black,testButton.getOutsideColor());
		testButton.changeOutsideColor(Color.gray);
		assertEquals("outside Color should be gray",Color.gray,testButton.getOutsideColor());
		testButton.changeOutsideColor(Color.red);
		assertEquals("outside Color should be red",Color.red,testButton.getOutsideColor());
		
	}
}
