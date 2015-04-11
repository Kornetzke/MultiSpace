package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import game.GamePlay;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.Drawable;
import SpaceClient.SpaceShip;

public class TestGamePlay {
GamePlay testClass;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		testClass = new GamePlay();
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testHitCheck() {
		Hashtable<String, SpaceShip> testHash = new Hashtable<String, SpaceShip>();
		SpaceShip testShip = new SpaceShip(5,4,2);
		testHash.put("test", testShip);
	}

	@Test
	public void testGetPlayerHashTable() {
		Hashtable<String, SpaceShip> testHash = new Hashtable<String, SpaceShip>();
		SpaceShip testShip = new SpaceShip(5,4,2);
		testHash.put("test", testShip);
		GamePlay.setPlayerHashTable(testHash);
		assertEquals("Should return hashTable testHash",testHash,GamePlay.getPlayerHashTable());
		
	}

	@Test
	public void testSetPlayerHashTable() {
	Hashtable<String, SpaceShip> testHash = new Hashtable<String, SpaceShip>();
	SpaceShip testShip = new SpaceShip(5,4,2);
	testHash.put("test", testShip);
	GamePlay.setPlayerHashTable(testHash);
	assertEquals("Should set testHash as Gameplay's hash",testHash,GamePlay.getPlayerHashTable());
	}

	@Test
	public void testGetPlayer() {
		SpaceShip test = new SpaceShip(5,500,500);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be returned from TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(0,50042,50027);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be returned from TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(4,5007824,50077575);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be returned from TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(7,50078542,50078542);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be returned from TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(6,506980,50078542);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be returned from TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(2,0,0);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be returned from TestClass",test,GamePlay.getPlayer());
	
	}

	@Test
	public void testSetPlayer() {
		SpaceShip test = new SpaceShip(5,500,500);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be inserted into TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(4,50042,50027);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be inserted into TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(3,5007824,50077575);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be inserted into TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(7,50078542,50078542);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be inserted into TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(2,506980,50078542);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be inserted into TestClass",test,GamePlay.getPlayer());
		test = new SpaceShip(0,0,0);
		GamePlay.setPlayer(test);
		assertEquals("TestPlayer should be inserted into TestClass",test,GamePlay.getPlayer());
	
	}

	@Test
	public void testGetSpaceList() {
	List<Drawable> testList = new ArrayList<Drawable>();
	testList.add(new SpaceShip(5,5,3));
	GamePlay.setSpaceList(testList);
	assertEquals("Should return testList",testList, GamePlay.getSpaceList());
	
	}

}
