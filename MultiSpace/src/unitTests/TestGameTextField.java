package unitTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import menu.GameTextField;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.Board;

public class TestGameTextField {
GameTextField testField;
static Board testBoard;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testBoard = new Board();
		Board.width=500;
		Board.height=500;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testField = new GameTextField(new Point(1,1), Color.white, "Test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChangeOutsideColor() {
		Color initialColor = testField.getOutsideColor();
		testField.changeOutsideColor(Color.black);
		assertFalse("The color should change", initialColor == testField.getOutsideColor());
	}

	@Test
	public void testSetString() {
		testField.setString("This is a testString");
		assertFalse("The text should NOT be the same as the origional, which was 'Test'", "Test".equals(testField.getText()));
		testField.setString("Test");
		assertTrue("The text should be the same as the origional, which was 'Test'", "Test".equals(testField.getText()));
	}

	@Test
	public void testUpdate() {
		testField.update(new Point(789,789));
		assertFalse("The point 10,10 should be in the textField",testField.contain(new Point(10,10)));
		assertTrue("The point 800,800 should be in the textField",testField.contain(new Point(800,800)));
	}

	@Test
	public void testClicked() {
		testField.clicked();
		assertFalse("The text should NOT be the same as the origional, which was 'Test'", "Test".equals(testField.getText()));
	}

	@Test
	public void testContain() {
		assertTrue("The point 10,10 should be in the textField",testField.contain(new Point(10,10)));
		assertFalse("The point 0,0 should NOT be in the textField",testField.contain(new Point(0,0)));
		
	}

	@Test
	public void testChangeInsideColor() {
		Color initialColor = testField.getInsideColor();
		testField.changeInsideColor(Color.black);
		assertFalse("The color should change", initialColor == testField.getOutsideColor());
	
	}

	@Test
	public void testGetText() {
		assertTrue("The text recieved should be 'Test'","Test".equals(testField.getText()));
	}

}
