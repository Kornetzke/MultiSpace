package unitTests;

import static org.junit.Assert.*;
import menu.GameButton;
import menu.GameTextField;
import menu.ModifyMenu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.Board;

public class TestModifyMenu {
static Board testGame;
ModifyMenu testMenu;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame = new Board();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testMenu = new ModifyMenu();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testUpdate() {
		Board.width = 1000;
		Board.height = 1000;
		testMenu.update();
		GameButton[] gameButtons = testMenu.returnButtons();
		GameTextField[] testFields = testMenu.returnTextFields();
		assertTrue("the point of shipOne should be(248,450)",gameButtons[0].toString().equals("248 450"));
		assertTrue("the point of shiptwo should be(502,450)",gameButtons[1].toString().equals("502 450"));
		assertTrue("the point of shipthree should be(248,554)",gameButtons[2].toString().equals("248 554"));
		assertTrue("the point of shipfour should be(502, 554)",gameButtons[3].toString().equals("502 554"));
		assertTrue("the point of back should be(248, 658)",gameButtons[4].toString().equals("248 658"));
		assertTrue("the point of join should be(502, 658)",gameButtons[5].toString().equals("502 658"));
	assertTrue("the point of name should be(248, 398)", testFields[0].toString().equals("248 398"));
	assertTrue("the point of server should be(248, 346)",testFields[1].toString().equals("248 346"));
	
	}

	@Test
	public void testNameReject() {
		testMenu.nameReject();
		assertTrue("The name should be true",testMenu.getReject());
		
	}
	@Test
	public void testGetReject(){
		assertFalse("Should return False",testMenu.getReject());
		testMenu.nameReject();
		assertTrue("Should return True",testMenu.getReject());	
	}
	@Test
	public void testReturnButtons(){
		GameButton[] gameButtons = testMenu.returnButtons();
		assertTrue("the point of shipOne should be(-4,-4)",gameButtons[0].toString().equals("-4 -4"));
		assertTrue("the point of shiptwo should be(-4,0)",gameButtons[1].toString().equals("-4 0"));
		assertTrue("the point of shipthree should be(-4,4)",gameButtons[2].toString().equals("-4 4"));
		assertTrue("the point of shipfour should be(-4, 8)",gameButtons[3].toString().equals("-4 8"));
		assertTrue("the point of back should be(-4, 12)",gameButtons[4].toString().equals("-4 12"));
		assertTrue("the point of join should be(-4, 16)",gameButtons[5].toString().equals("-4 16"));
	
		}
	@Test
	public void testReturnTextFields(){
		GameTextField[] testFields = testMenu.returnTextFields();
		assertTrue("the point of name should be(-4, -8)", testFields[0].toString().equals("-4 -8"));
	assertTrue("the point of server should be(-4, -12)",testFields[1].toString().equals("-4 -12"));
	
	}
}
