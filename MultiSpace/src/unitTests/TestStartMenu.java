package unitTests;

import static org.junit.Assert.*;

import menu.StartMenu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.Board;

public class TestStartMenu {
static Board testGame;
StartMenu testStart;
static StartMenu origionalStart;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame = new Board();
		Board.height = 1000;
		Board.width = 1000;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testStart = new StartMenu();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdate() {
		StartMenu tester= new StartMenu();
		Board.height = 500;
		Board.width = 500;
		testStart.update();
		assertTrue("The exitPoint should have changed",tester.getExitPoint().x!=testStart.getExitPoint().x || tester.getExitPoint().y!=testStart.getExitPoint().y);
		assertTrue("The joinPoint should have changed",tester.getJoinPoint().x!=testStart.getJoinPoint().x || tester.getJoinPoint().y!=testStart.getJoinPoint().y);
			
	}
	@Test
	public void testGetJoinPoint(){
		assertTrue("The returned point must be ( 375, 837)",375 == testStart.getJoinPoint().x && 450==testStart.getJoinPoint().y);
	}
	@Test
	public void testGetExitPoint(){
		assertTrue("The returned point must be ( 375, 450)",375 == testStart.getExitPoint().x && 837==testStart.getExitPoint().y);
		
	}
}
