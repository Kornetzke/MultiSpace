package unitTests;

import static org.junit.Assert.*;

import java.awt.Point;


import game.GamePlay;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SpaceClient.SpaceShip;
import SpaceClient.SpaceShip.PlayerState;
import SpaceClient.SpaceShip.shipStatus;

public class TestSpaceShip {
SpaceShip testShip;
GamePlay gameTest;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testShip = new SpaceShip(5,4,3);
		gameTest = new GamePlay("Test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetPlacement() {
		double x = 100.4;
		double y = 122.3;
		int direction = 123;
		testShip.setPlacement(x, y, direction);
		assertTrue("The value of x should be 100.4", x == testShip.getX());
		assertTrue("The value of y should be 122.3", y == testShip.getY());
		assertTrue("The value of direction should be 123", direction == testShip.getDirection());
	}

	@Test
	public void testSetForward() {
		testShip.setForward(false);
		assertFalse("This should have set forward to false",testShip.getForward());
		testShip.setForward(true);
		assertTrue("This should have set forward to true",testShip.getForward());
	}
	@Test
	public void testGetForward(){
		testShip.setForward(false);
		assertFalse("This should have set forward to false",testShip.getForward());
		testShip.setForward(true);
		assertTrue("This should have set forward to true",testShip.getForward());
	}

	@Test
	public void testSetLeft() {
		testShip.setLeft(false);
		assertFalse("This should have set left to false",testShip.getLeft());
		testShip.setLeft(true);
		assertTrue("This should have set left to true",testShip.getLeft());
	
	}
	@Test
	public void testGetLeft() {
		testShip.setLeft(false);
		assertFalse("This should have set left to false",testShip.getLeft());
		testShip.setLeft(true);
		assertTrue("This should have set left to true",testShip.getLeft());
	
	
	}
	@Test
	public void testSetRight() {
		testShip.setRight(false);
		assertFalse("This should have set Right to false",testShip.getRight());
		testShip.setRight(true);
		assertTrue("This should have set Right to true",testShip.getRight());
	}
	@Test
	public void tesGetRight() {
		testShip.setRight(false);
		assertFalse("This should have set Right to false",testShip.getRight());
		testShip.setRight(true);
		assertTrue("This should have set Right to true",testShip.getRight());
	}
	@Test
	public void testSetBreak() {
		testShip.setBreak(false);
		assertFalse("This should have set break to false",testShip.getBreak());
		testShip.setBreak(true);
		assertTrue("This should have set break to true",testShip.getBreak());
	}
	@Test
	public void testGetBreak() {
		testShip.setBreak(false);
		assertFalse("This should have set break to false",testShip.getBreak());
		testShip.setBreak(true);
		assertTrue("This should have set break to true",testShip.getBreak());
	}
	@Test
	public void testSetFire() {
		testShip.setFire(false);
		assertFalse("This should have set Fire to false",testShip.getFire());
		testShip.setFire(true);
		assertTrue("This should have set fire to true",testShip.getFire());
		}
	@Test
	public void testGetFire() {
		testShip.setFire(false);
		assertFalse("This should have set Fire to false",testShip.getFire());
		testShip.setFire(true);
		assertTrue("This should have set fire to true",testShip.getFire());
		}
	@Test
	public void testComputeTurnDirection() {
		testShip.setDirection(50);
		testShip.setMouseLocation(new Point(10,10));
		testShip.computeTurnDirection();
		assertEquals("The compute method should return 53 as a direction",53,testShip.getDirection());
		testShip.setMouseLocation(new Point(1000,1000));
		testShip.computeTurnDirection();
		assertEquals("The compute method should return 56 as a direction",56,testShip.getDirection());
		testShip.setMouseLocation(new Point(10,-10));
		testShip.computeTurnDirection();
		assertEquals("The compute method should return 53 as a direction",53,testShip.getDirection());
	}

	@Test
	public void testAccelerate() {
		testShip.Accelerate(-10, .25);
		assertEquals("After calculating the acceleration, dx should be 0.02170602220836629", 0.02170602220836629, testShip.getDx(),0.0);
		assertEquals("After calculating the acceleration, dy should be -0.123100969126526", -0.123100969126526, testShip.getDy(),0.0);
		assertEquals("After calculating the acceleration, speed should be 0.124999999999999999", 0.12499999999999999, testShip.getSpeed(),0.0);
		
	}

	@Test
	public void testMove() {
		testShip.Move();
		assertEquals("after move, the ship should have stayed at x=4",4,testShip.getX(),0.0);
		assertEquals("after move, the ship should have stayed at y=4",3,testShip.getY(),0.0);
		testShip.setDy(12);
		testShip.setDx(45);
		testShip.Move();
		assertEquals("after move, the ship should be at x=18.493524094018696",18.493524094018696,testShip.getX(),0.0);
		assertEquals("after move, the ship should be at y=6.864939758404985",6.864939758404985,testShip.getY(),0.0);
	}

	@Test
	public void testApplySpaceBreak() {
		testShip.setDx(10.0);
		testShip.setDy(10.0);
		testShip.applySpaceBreak();
		assertEquals("Applying the spaceBreak yields dx=9.6",9.6,testShip.getDx(),0.0);
		assertEquals("Applying the spaceBreak yields dy=9.6",9.6,testShip.getDy(),0.0);
		testShip.setDx(0.0);
		testShip.setDy(0.0);
		testShip.applySpaceBreak();
		assertEquals("Applying the spaceBreak yields dx=0",0.0,testShip.getDx(),0.0);
		assertEquals("Applying the spaceBreak yields dy=0",0.0,testShip.getDy(),0.0);
	}

	@Test
	public void testRegenerateStats() {
		testShip.setHealth(10.0);
		testShip.setShield(10.0);
		testShip.setEnergy(10.0);
		testShip.regenerateStats();
		assertTrue("The health should stay the same", 10.0 == testShip.getHealth());
		assertTrue("The shield should increment by .6", 10.6 == testShip.getShield());
		assertTrue("The energy should have incremented",10.5 == testShip.getEnergy());
		testShip.regenerateStats();
		assertTrue("The health should stay the same", 10.0 == testShip.getHealth());
		assertTrue("The shield should increment by .6", 11.2 == testShip.getShield());
		assertTrue("The energy should have incremented",11.0 == testShip.getEnergy());
		testShip.setHealth(100.0);
		testShip.setShield(100.0);
		testShip.setEnergy(100.0);
		testShip.regenerateStats();
		assertTrue("The health should stay the same", 100.0 == testShip.getHealth());
		assertTrue("The shield should istay the same", 100.0 == testShip.getShield());
		assertTrue("The energy should have incremented",100.0 == testShip.getEnergy());
		
	}
	@Test
	public void testResetChange() {
		testShip.setChanged(true);
		assertTrue("Ship should have toggled changed", true == testShip.isChanged());
		testShip.resetChange();
		assertFalse("The should should have reset changed",true == testShip.isChanged());
	}

	@Test
	public void testToString() {
		testShip.setName("LOL");
		testShip.setDirection(15);
		assertEquals("This should print out the name:x:y","LOL:4.0:3.0:15",testShip.toString());
		testShip.setName("TestName");
		testShip.setDirection(352);
		testShip.setX(12590);
		testShip.setY(125890);
		assertEquals("This should print out the name:x:y","TestName:12590.0:125890.0:352",testShip.toString());
		
	}

	@Test
	public void testGetDx() {
		testShip.setDx(50.0);
		assertTrue("The dx should be 50",50.0 == testShip.getDx());
		testShip.setDx(5012.0);
		assertTrue("The dx should be 5012",5012.0 == testShip.getDx());
		testShip.setDx(-10.0);
		assertTrue("The dx should be -10",-10.0 == testShip.getDx());
	}

	@Test
	public void testSetDx() {
		testShip.setDx(50.0);
		assertTrue("The dx should be 50",50.0 == testShip.getDx());
		testShip.setDx(5012.0);
		assertTrue("The dx should be 5012",5012.0 == testShip.getDx());
		testShip.setDx(-10.0);
		assertTrue("The dx should be -10",-10.0 == testShip.getDx());
	}

	@Test
	public void testGetDy() {
		testShip.setDy(50.0);
		assertTrue("The dy should be 50",50.0 == testShip.getDy());
		testShip.setDy(5012.0);
		assertTrue("The dy should be 5012",5012.0 == testShip.getDy());
		testShip.setDy(-10.0);
		assertTrue("The dy should be -10",-10.0 == testShip.getDy());
	}

	@Test
	public void testSetDy() {
		testShip.setDy(50.0);
		assertTrue("The dy should be 50",50.0 == testShip.getDy());
		testShip.setDy(5012.0);
		assertTrue("The dy should be 5012",5012.0 == testShip.getDy());
		testShip.setDy(-10.0);
		assertTrue("The dy should be -10",-10.0 == testShip.getDy());
	}

	@Test
	public void testSetMouseLocation() {
		testShip.setMouseLocation(new Point(256,654));
		assertEquals("The mouse x location should be 256",256,testShip.getMouseLocation().x);
		assertEquals("The mouse y location should be 654",654,testShip.getMouseLocation().y);
		testShip.setMouseLocation(new Point(2565,-51));
		assertEquals("The mouse x location should be 2565",2565,testShip.getMouseLocation().x);
		assertEquals("The mouse y location should be -51",-51,testShip.getMouseLocation().y);
	}

	@Test
	public void testIsChanged() {
		testShip.setChanged(true);
		assertTrue("The changed variable on the testship should be true", testShip.isChanged());
		testShip.setChanged(false);
		assertFalse("The changed variable on the testship should be False", testShip.isChanged());
	}

	@Test
	public void testSetChanged() {
		testShip.setChanged(true);
		assertTrue("The changed variable on the testship should be true", testShip.isChanged());
		testShip.setChanged(false);
		assertFalse("The changed variable on the testship should be False", testShip.isChanged());
	}

	@Test
	public void testGetName() {
		testShip.setName("TestShipName");
		assertEquals("The name should be TestShipName","TestShipName",testShip.getName());
		testShip.setName("Testingof");
		assertEquals("The name should be Testingof","Testingof",testShip.getName());
	}

	@Test
	public void testGetMouseLocation() {
		testShip.setMouseLocation(new Point(256,654));
		assertEquals("The mouse x location should be 256",256,testShip.getMouseLocation().x);
		assertEquals("The mouse y location should be 654",654,testShip.getMouseLocation().y);
		testShip.setMouseLocation(new Point(2565,-51));
		assertEquals("The mouse x location should be 2565",2565,testShip.getMouseLocation().x);
		assertEquals("The mouse y location should be -51",-51,testShip.getMouseLocation().y);
	}

	@Test
	public void testSetShipStatus() {
		testShip.setShipStatus("ALIVE");
		assertEquals("The ship status should be Alive",shipStatus.ALIVE, testShip.getShipStatus());
		testShip.setShipStatus("Dead");
		assertEquals("The ship status should be Dead",shipStatus.DEAD, testShip.getShipStatus());
	}

	@Test
	public void testGetShipStatus() {
		testShip.setShipStatus("ALIVE");
		assertEquals("The ship status should be Alive",shipStatus.ALIVE, testShip.getShipStatus());
		testShip.setShipStatus("Dead");
		assertEquals("The ship status should be Dead",shipStatus.DEAD, testShip.getShipStatus());
	}

	@Test
	public void testSetClientState() {
		testShip.setClientState("GAME");
		assertEquals("The client status should be GAME ", PlayerState.GAME,testShip.getPlayerState());
		testShip.setClientState("MENU");
		assertEquals("The client status should be MENU ", PlayerState.MENU,testShip.getPlayerState());
		testShip.setClientState("DISCONNECTED");
		assertEquals("The client status should be DISCONNECT ", PlayerState.DISCONNECT,testShip.getPlayerState());
	}

	@Test
	public void testGetPlayerState() {
		testShip.setClientState("GAME");
		assertEquals("The ship status should be GAME ", PlayerState.GAME,testShip.getPlayerState());
		testShip.setClientState("MENU");
		assertEquals("The ship status should be MENU ", PlayerState.MENU,testShip.getPlayerState());
		testShip.setClientState("DISCONNECTED");
		assertEquals("The ship status should be DISCONNECT ", PlayerState.DISCONNECT,testShip.getPlayerState());
	}

	@Test
	public void testGetHealth() {
		assertEquals("The Health should be 100",100.0,testShip.getHealth(),0.0);
		testShip.setHealth(120);
		assertEquals("The Health should be 120",120.0,testShip.getHealth(),0.0);
		testShip.setHealth(10);
		assertEquals("The Health should be 10",10.0,testShip.getHealth(),0.0);
	}

	@Test
	public void testSetHealth() {
		assertEquals("The Health should be 100",100.0,testShip.getHealth(),0.0);
		testShip.setHealth(120);
		assertEquals("The Health should be 120",120.0,testShip.getHealth(),0.0);
		testShip.setHealth(10);
		assertEquals("The Health should be 10",10.0,testShip.getHealth(),0.0);
	}

	@Test
	public void testSetName() {
		testShip.setName("TestShipName");
		assertEquals("The name should be TestShipName","TestShipName",testShip.getName());
		testShip.setName("Testingof");
		assertEquals("The name should be Testingof","Testingof",testShip.getName());
	}

	@Test
	public void testGetMaxHealth() {
		assertEquals("The max Health should be 100",100.0,testShip.getMaxHealth(),0.0);
		testShip.setMaxHealth(120);
		assertEquals("The max Health should be 120",120.0,testShip.getMaxHealth(),0.0);
		testShip.setMaxHealth(10);
		assertEquals("The max Health should be 10",10.0,testShip.getMaxHealth(),0.0);
	}

	@Test
	public void testSetMaxHealth() {
		assertEquals("The max Health should be 100",100.0,testShip.getMaxHealth(),0.0);
		testShip.setMaxHealth(120);
		assertEquals("The max Health should be 120",120.0,testShip.getMaxHealth(),0.0);
		testShip.setMaxHealth(10);
		assertEquals("The max Health should be 10",10.0,testShip.getMaxHealth(),0.0);
	}

	@Test
	public void testGetShield() {
		assertEquals("The shield should be 100",100.0,testShip.getShield(),0.0);
		testShip.setShield(120);
		assertEquals("The shield should be 120",120.0,testShip.getShield(),0.0);
		testShip.setShield(0);
		assertEquals("The shield should be 0",0.0,testShip.getShield(),0.0);
	}

	@Test
	public void testSetShield() {
		assertEquals("The shield should be 100",100.0,testShip.getShield(),0.0);
		testShip.setShield(120);
		assertEquals("The shield should be 120",120.0,testShip.getShield(),0.0);
		testShip.setShield(0);
		assertEquals("The shield should be 0",0.0,testShip.getShield(),0.0);
	}

	@Test
	public void testGetMaxShield() {
		assertEquals("The max Shield should be 100",100.0,testShip.getMaxShield(),0.0);
		testShip.setMaxShield(120);
		assertEquals("The max Shield should be 120",120.0,testShip.getMaxShield(),0.0);
		testShip.setMaxShield(0);
		assertEquals("The max Shield should be 0",0.0,testShip.getMaxShield(),0.0);
	}

	@Test
	public void testSetMaxShield() {
		assertEquals("The max Shield should be 100",100.0,testShip.getMaxShield(),0.0);
		testShip.setMaxShield(120);
		assertEquals("The max Shield should be 120",120.0,testShip.getMaxShield(),0.0);
		testShip.setMaxShield(0);
		assertEquals("The max Shield should be 0",0.0,testShip.getMaxShield(),0.0);
	}

	@Test
	public void testGetEnergy() {
		assertEquals("The energy should be 100",100.0,testShip.getEnergy(),0.0);
		testShip.setEnergy(120);
		assertEquals("The energy should be 120",120.0,testShip.getEnergy(),0.0);
		testShip.setEnergy(0);
		assertEquals("The energy should be 0",0.0,testShip.getEnergy(),0.0);
	}

	@Test
	public void testSetEnergy() {
		assertEquals("The energy should be 100",100.0,testShip.getEnergy(),0.0);
		testShip.setEnergy(120);
		assertEquals("The energy should be 120",120.0,testShip.getEnergy(),0.0);
		testShip.setEnergy(0);
		assertEquals("The energy should be 0",0.0,testShip.getEnergy(),0.0);
	}

	@Test
	public void testGetMaxEnergy() {
		assertEquals("The max Energy should be 100",100.0,testShip.getMaxEnergy(),0.0);
		testShip.setMaxEnergy(120);
		assertEquals("The max Energy should be 120",120.0,testShip.getMaxEnergy(),0.0);
		testShip.setMaxEnergy(0);
		assertEquals("The max Energy should be 0",0.0,testShip.getMaxEnergy(),0.0);
	}

	@Test
	public void testSetMaxEnergy() {
		assertEquals("The max Energy should be 100",100.0,testShip.getMaxEnergy(),0.0);
		testShip.setMaxEnergy(120);
		assertEquals("The max Energy should be 120",120.0,testShip.getMaxEnergy(),0.0);
		testShip.setMaxEnergy(0);
		assertEquals("The max Energy should be 0",0.0,testShip.getMaxEnergy(),0.0);
	}

	@Test
	public void testRespawn() {
		testShip.setHealth(10);
		testShip.setEnergy(10);
		testShip.setShield(0);
		testShip.setShipStatus("dead");
		testShip.respawn();
		assertEquals("The ship should have Reset",false ,testShip.isDead());
		assertEquals("The ship should have Reset",testShip.getMaxHealth() ,testShip.getHealth(),0.0);
		assertEquals("The ship should have Reset",testShip.getMaxEnergy() ,testShip.getEnergy(),0.0);
		assertEquals("The ship should have Reset",testShip.getMaxShield(),testShip.getShield(),0.0);
	}

	@Test
	public void testGetDeathCount() {
		assertEquals("Ship should have no deaths", 0,testShip.getDeathCount());
		testShip.incrementDeathCount();
		assertEquals("Ship should have 1 death",1,testShip.getDeathCount());
	}

	@Test
	public void testIsDead() {
		assertEquals("Should return false",false,testShip.isDead());
		testShip.setShipStatus("Dead");
		assertEquals("Should return true",true,testShip.isDead());
	}
	@Test
	public void testIncrementDeathCount(){
		assertEquals("Ship should have no deaths", 0,testShip.getDeathCount());
		testShip.incrementDeathCount();
		assertEquals("Ship should have 1 death",1,testShip.getDeathCount());
	}
}
