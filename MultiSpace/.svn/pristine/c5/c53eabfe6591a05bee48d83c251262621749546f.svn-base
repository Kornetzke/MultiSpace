package unitTests;

import static org.junit.Assert.*;

import java.awt.Point;

import game.GamePlay;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.Background;

public class TestBackground {
static GamePlay testGame;
Background testBack;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame = new GamePlay();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testBack = new Background();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdate() {
		testBack.update(125, 125);
		Point[][] test = testBack.getBackgroundPoints();
		assertTrue("The first point should be (-3000, -3000)",-3000 == test[0][0].x && -3000 == test[0][0].y);
		assertTrue("The second point should be (0, -3000)",0 == test[0][1].x && -3000 == test[0][1].y);
		assertTrue("The third point should be (3000, -3000)",3000 == test[0][2].x && -3000 == test[0][2].y);
		assertTrue("The Fourth point should be (-3000, 0)",-3000 == test[1][0].x && 0 == test[1][0].y);
		assertTrue("The fifth point should be (0, 0)",0 == test[1][1].x && 0 == test[1][1].y);
		assertTrue("The sixth point should be (3000, 3000)",3000 == test[1][2].x && 3000 == test[1][2].y);
		assertTrue("The seventh point should be (-3000, 3000)",-3000 == test[2][0].x && 3000 == test[2][0].y);
		assertTrue("The eight point should be (0, 3000)",0 == test[2][1].x && 3000 == test[2][1].y);
		assertTrue("The ninth point should be (3000,3000)",3000 == test[2][2].x && 3000 == test[2][2].y);
		testBack.update(2900, 2900);
		assertFalse("The first point should have changed",-3000 == test[0][0].x && -3000 == test[0][0].y);
		assertFalse("The second point should have changed",0 == test[0][1].x && -3000 == test[0][1].y);
		assertFalse("The third point should have changed",3000 == test[0][2].x && -3000 == test[0][2].y);
		assertFalse("The Fourth point should have changed",-3000 == test[1][0].x && 0 == test[1][0].y);
		assertFalse("The fifth point should have changed",0 == test[1][1].x && 0 == test[1][1].y);
		assertFalse("The sixth point should have changed",3000 == test[1][2].x && 3000 == test[1][2].y);
		assertFalse("The seventh point should have changed",-3000 == test[2][0].x && 3000 == test[2][0].y);
		assertFalse("The eight point should have changed",0 == test[2][1].x && 3000 == test[2][1].y);
		assertFalse("The ninth point should have changed",3000 == test[2][2].x && 3000 == test[2][2].y);
		
	}

}
