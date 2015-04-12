package SpaceClient;

import game.GamePlay;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class SpaceShip extends Drawable {


	
	private String shipName;
	
	private double health;
	private double maxHealth;
	private double shield;
	private double maxShield;
	private double shieldRegen;
	private double shieldRegenDelay;
	private double shieldRegenCount;
	private double energy;
	private double maxEnergy;
	private double energyRegen;
	private int deathCount;
	
	private boolean changed =false;
	private double dx, dy;
	private double acceleration;
	private double spaceBreak;
	private final double MAX_SPEED=15;
	private double speed = 0;
	private final double TURN_SPEED =3;
	private int weaponCounter;
	private final int WEAPON_COOLDOWN = 10;
	private Keydown keydown;
	public boolean left =true;
	private boolean firing=false;
	public  enum ShipStatus  {ALIVE, DEAD};
	public static enum PlayerState {MENU,GAME,DISCONNECT};
	private PlayerState playerState;
	private ShipStatus shipStatus = ShipStatus.ALIVE;
	
	private Vector<Missile> weaponList_ = new Vector<Missile>();
	
	
	private Point mouseLocation_;

	
	/**
	 * Keydown class holds the key states for from the keyEvent listeners.
	 * <p>The cycle method will use these to affect the ship
	 * @author Kyle
	 *
	 */
	class Keydown {
		private boolean keyDownLeft, keyDownRight, keyDownForward, keyDownFire, keyDownSpaceBreak;

		public Keydown() {
			this.keyDownLeft = false;
			this.keyDownRight = false;
			this.keyDownForward = false;
			this.keyDownSpaceBreak = false;
			this.keyDownFire = false;
		}
	}

	/**
	 * This is the main constructor for a players ship. This one will create the keydown class along with some other player used variables.
	 * @param faceNumber - used to select image from Board.iiarray
	 * @param x - location of the x coor
	 * @param y	- location of the y coor
	 */
	public SpaceShip(int faceNumber, int x, int y) {
		super(0, x, y);
		deathCount=0;

		

		imageIcon = Board.imageIconArray[faceNumber];
		image = imageIcon.getImage();
		acceleration=.5;
		spaceBreak=.4;
		direction.setDirection(0);
		mouseLocation_ = new Point(0,0);
		keydown = new Keydown();
		updateMiddle();

		dx = 0;
		dy = 0;
		
		
		
this.setHitBox(faceNumber);
		
		maxShield = 100;
		maxHealth = 100;
		maxEnergy = 100;
		
		shieldRegenDelay = 120;
		shieldRegenCount=0;
		shieldRegen=.6;
		energyRegen=.5;		
		
		shield = 100;
		health = 100;
		energy = 100;
		

	}
	
	/**
	 * more basic constructor used for non-player ships. This is used for the playerHashTable of other ships.
	 * @param face - image from ImageIconArray
	 * @param name - name of the player using the ship
	 * @param x - location of the ship's x
	 * @param y - location of the ship's y
	 * @param dir - direction of the ship
	 */
	public SpaceShip(int face, String name, double x, double y, int dir){
		super(face,x,y);
		shipName = name;
		direction.setDirection(dir);
		updateMiddle();
		this.setHitBox(face);
		
		maxShield = 100;
		maxHealth = 100;
		maxEnergy = 100;
		
		shield = 100;
		health = 100;
		energy = 100;
	}
	

	
	/**
	 * This method changes the x, y, and direction of the ship.
	 * @param x a new value of x for the ship
	 * @param y a new value of y for the ship
	 * @param direction the new direction for the ship
	 * 
	 */
	public SpaceShip() {
		super(0, 0, 0);
	}


	/**
	 * This method changes the x, y, and direction of the ship.
	 * @param x a new value of x for the ship
	 * @param y a new value of y for the ship
	 * @param direction the new direction for the ship
	 * 
	 */
	public void setPlacement(double x, double y, int direction){
		super.x = x;
		super.y = y;
		super.setDirection(direction);
		
	}
	/**
	 * This sets the keydown.forward for the player
	 * @param set - new value
	 */
	public void setForward(boolean set) {
		this.keydown.keyDownForward = set;
	}
/**
 * This returns the keydown.forward from the player
 * @return keydown.forward
 */
	public boolean getForward(){
		return this.keydown.keyDownForward;
	}
	/**
	 * This sets the keydown.left for the player
	 * @param set - new value
	 */
	public void setLeft(boolean set) {
		this.keydown.keyDownLeft = set;
	}
	/**
	 * This returns the value of the keydown.left
	 * @return keydown.left
	 */
	public boolean getLeft(){
		return this.keydown.keyDownLeft;
	}
	/**
	 * this sets the keydown.right of the player
	 * @param set - new value
	 */
	public void setRight(boolean set) {
		this.keydown.keyDownRight = set;
	}
	/**
	 * This returns the value of the player's keydown.right
	 * @return keydown.right
	 */
	public boolean getRight(){
		return this.keydown.keyDownRight;
	}
	/**
	 * This sets the value of keydown.spaceBreak for the player
	 * @param set - new value
	 */
	public void setBreak(boolean set) {
		this.keydown.keyDownSpaceBreak = set;
	}
	
	/**
	 * This returns the value of the players keydown.spaceBreak
	 * @return keydown.spaceBreak
	 */
	public boolean getBreak(){
		return this.keydown.keyDownSpaceBreak;
	}
	/**
	 * This sets the value of the keydown.fire of the player
	 * @param set - new value
	 */
	public void setFire(boolean set) {
		this.keydown.keyDownFire = set;
	}
	/**
	 * This returns the value of the keydown.fire of the player
	 * @return keydown.fire
	 */
	public boolean getFire(){
		return this.keydown.keyDownFire;
	}
	/**
	 * This method finds the direction the ship needs to point at the mouse location and executes a turn based on the ships turnSpeed.
	 * The ship will find the fastest way to get to the direction and will turn to the location.
	 */
	public void computeTurnDirection(){
			Point boardMiddle = new Point(Board.width/2,Board.height/2);

		
		int mouseAngle = (int)Math.toDegrees(Math.atan2(boardMiddle.x - mouseLocation_.x, boardMiddle.y - mouseLocation_.y));

		if (mouseAngle<0)
			mouseAngle=360+mouseAngle;		
		if (direction.getDirection()!=mouseAngle)
			changed=true;
		
		int move = (direction.getRotationDegrees(mouseAngle));
		
		if (move<0){
			if(-TURN_SPEED<move)
				direction.incrementDirection(move);
			else
				direction.incrementDirection(-TURN_SPEED);
		}
		if (move>0){
			if(TURN_SPEED>move)
				direction.incrementDirection(move);
			else
				direction.incrementDirection(TURN_SPEED);
		}
		
	}
	
	/**
	 * This method calculates the displacement of the ship with respect to direction and acceleration of the ship.
	 * The new displacement will be added to the ships displacement 
	 * 
	 * 
	 * @param direction - This is the direction of the acceleration
	 * @param percentage - This is the percentage of the ship's acceleration that will be used
	 * 
	 * 
	 * 
	 */
	public void Accelerate(double deltaDirection, double percentage) {

			double dir = Math.toRadians(direction.getDirection()+deltaDirection);
			dy -= Math.cos(dir) * (acceleration*percentage);
			dx -= Math.sin(dir) * (acceleration*percentage);
			speed = Math.sqrt(Math.pow(dy, 2) + Math.pow(dx, 2));

	}
	public double getSpeed(){
		return speed;
	}
	
	/**
	 * The Move() method is called once the Accelerate() method is finished.
	 * 
	 * This method will change the X and Y by the dx and dy found in Accelerate().
	 * <p>
	 * If dx and dy are not zero the variable changed will be set to true
	 * This will be used when sending data to the SpaceServer.
	 * 
	 * 
	 * 
	 * 
	 */
	public void Move() {
		
			if ((dx < 0.001 && dx > 0) || (dx > -0.001 && dx < 0)) {
				dx = 0;
			}

			if ((dy < 0.001 && dy > 0) || (dy > -0.001 && dy < 0)) {
				dy = 0;
			}
			
			
			
			speed = Math.sqrt(Math.pow(dy, 2) + Math.pow(dx, 2));
			
			if (speed>MAX_SPEED){
				dy = MAX_SPEED / speed * dy;
				dx = MAX_SPEED / speed * dx;
		}

		x += dx;
		y += dy;

		updateMiddle();
		hitBox.update(middle, direction.getDirection());

		MoveMissile();

		GamePlay.handler.sendToServer("Move:" + shipName + ":" + x + ":" + y
				+ ":" + direction.getDirection());

	}
	/**
	 * This method will update the middle of the ship and move the ship's missiles by calling missiles move method.
	 */

	public void MoveMissile(){
		updateMiddle();

		for (int link = 0; link < weaponList_.size(); link++) {
			if (weaponList_.get(link).inRange())
				weaponList_.get(link).move();
			else
				weaponList_.remove(link);
		}
	}
	/**
	 * This method will bring the dx and dy values closer to zero based on the ship's spaceBreak value.
	 */
	public void applySpaceBreak() {

		
			if (dx < 0) {
				if (dx > spaceBreak)
					dx = 0;
				else
					dx += spaceBreak;
			}
			if (dy < 0) {
				if (dy > spaceBreak)
					dy = 0;
				else
					dy += spaceBreak;
			}
			if (dx > 0) {
				if (dx < spaceBreak)
					dx = 0;
				else
					dx -= spaceBreak;
			}
			if (dy > 0) {
				if (dy < spaceBreak)
					dy = 0;
				else
					dy -= spaceBreak;
			}
	}

	/**
	 * This method will changed the ship based on what keydown event is active. Based on what keydown is active, it will have a different affect on the ship.
	 * This method will call the regenerateStats method, and finally the move method.
	 */
	public void cycle() {
		if(shipStatus == ShipStatus.ALIVE){
		regenerateStats();
		

		
		computeTurnDirection();
		
		checkFire();
		
		if (keydown.keyDownForward){
			Accelerate(0, .7);
		}
		if (keydown.keyDownSpaceBreak){
			applySpaceBreak();
		}
		if (keydown.keyDownLeft){
			Accelerate(90,.3);
		}
		if (keydown.keyDownRight){
			Accelerate(-90,.3);
		}
		
		Move();
		}
	}
	/**
	 * The paint method will paint the ship along with what the ship contains. This will paint the ship's missiles before painting the ship itself.
	 */
	public void paint(Graphics2D g2d, double dx, double dy) {
		if(shipStatus == ShipStatus.ALIVE){

			
		for (int x = 0; x < weaponList_.size(); x++) {
			weaponList_.get(x).paint(g2d, dx, dy);
		}
		g2d.rotate(Math.toRadians(-getDirection()), middle.x - dx, middle.y - dy);		
		g2d.drawImage(image, (int) (x - dx), (int) (y - dy), null);
		g2d.rotate(Math.toRadians(getDirection()), middle.x - dx, middle.y - dy);
		g2d.setColor(Color.white);
		g2d.drawString(shipName, (int) (x - dx)+Board.width/20, (int) (y - dy));
		
	
		}
		
	}
	/**
	 * This method will regenerate the shields and energy of the ship. Some of the values will not regen right away.
	 */
	public void regenerateStats(){
		if(energy <maxEnergy){
		energy+=energyRegen;
		}
		if(shieldRegenCount>0){
			
			shieldRegenCount--;
		}
		if(shield < maxShield  && shieldRegenCount <= 0){
			shield +=shieldRegen;
		}
	
	}
	
	/**
	 * This method will check if the ship is able to fire, and if so will create a missile and add it to the missile array.
	 */
	public void checkFire(){
		
		if (weaponCounter > 0){
			weaponCounter--;
		}
		if (keydown.keyDownFire && weaponCounter == 0 && energy >= Missile.getEnergyCost() ) {
	
			weaponCounter = WEAPON_COOLDOWN;
			
			String missileID = shipName+"_"+System.currentTimeMillis();
			fire( dx, dy, direction.getDirection(),missileID);
			
			
			energy -= Missile.getEnergyCost();
			
				GamePlay.handler.sendToServer("AddMissile:" + shipName + ":"
					  + dx + ":"
					+ dy+ ":"+ direction.getDirection()+":"+missileID);
		
			
		}
		
	}

	
	/**
	 * This method will return changed to false
	 */
	public void resetChange(){
		changed = false;
	}
	/**
	 * this method converts the ship's information into a usable string for the communication with the server
	 */
	public String toString(){
		return shipName+":"+x+":"+y+":"+direction.getDirection();
				
	}
	/**
	 * @return the dx
	 */
	public double getDx() {
		return dx;
	}


	/**
	 * @param dx the dx to set
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}


	/**
	 * @return the dy
	 */
	public double getDy() {
		return dy;
	}


	/**
	 * @param dy the dy to set
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	/**
	 * This will return an array of the ship's missiles
	 * @return weaponList_.toArray()
	 */
	public Missile[] getMissiles(){
		return (Missile[]) weaponList_.toArray();
	}

	/**
	 * This method was used to track when other players fired. This should no longer be used
	 * @return
	 * @deprecated
	 */
	public boolean isFiring() {
		if(energy >=5){
		if(firing==true){
			firing=false;
			return true;
		}else
		return firing;
		}
		else
		return false;
	}

	/**
	 * This method creates a missiles in weapon list based on the dx and dy of the ship fired. The missile is given an unique id for the missile
	 * @param dx - starting dx of the missile
	 * @param dy - starting dy of the missile
	 * @param direction - starting direction of the missile
	 * @param missileID - unique id of the missile
	 */
	public void fire(double dx, double dy, int direction, String missileID) {
		double gunx =0;
		double guny =0;
		switch (this.imageNumber){
		
		case 0:
			gunx = Math.cos(Math.toRadians(direction))*44;
			guny = Math.sin(Math.toRadians(direction))*44;	
			break;
			
		case 8:
			gunx = Math.cos(Math.toRadians(direction))*7;
			guny = Math.sin(Math.toRadians(direction))*7;	
			break;

		case 9:
			gunx = Math.cos(Math.toRadians(direction))*57;
			guny = Math.sin(Math.toRadians(direction))*57;	
			break;
			
		case 10:
			gunx = Math.cos(Math.toRadians(direction))*0;
			guny = Math.sin(Math.toRadians(direction))*0;	
			break;
		}
		
					
		if(left){
		weaponList_.add(new Missile(middle.x+gunx, middle.y-guny, direction, dx, dy, missileID));
		left=false;
		}
		else{
			weaponList_.add(new Missile(middle.x-gunx, middle.y+guny, direction, dx, dy, missileID));
		left=true;	
		}

	
			
		
	}
	/**
	 * This sets the new point of the mouseLocation
	 * @param loc - new value
	 */
	public void setMouseLocation(Point loc){
		mouseLocation_ = loc;
	} 
	/**
	 * @return the changed
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return shipName;
	}
	
	/**
	 * @return the mouseLocation
	 */
	public Point getMouseLocation() {
		return mouseLocation_;
	}

	/**
	 * This method checks other players missiles to see if the player ship was hit by any of them. If the ship is hit by the missile damage is applied.
	 * Once a missile hits a ship, the server is informed that the missile should no longer exists and should be removed. The server then spreads this information to other ships
	 * @param otherPlayer - all other players on the server, PlayerHastTable.values()
	 */
	public void checkIfWeaponHit(SpaceShip otherPlayer) {

		List<String> removeList = new LinkedList<String>();
		synchronized(weaponList_){
		for (Missile m : weaponList_) {
			Point[] missilePoints = m.getHitPoints();

			for (int i = 0; i < missilePoints.length; i++) {

				if (otherPlayer.hitBox.getPolygon().contains(missilePoints[i]) || m.hitBox.contains(otherPlayer.hitBox.getPolyPoints())){
					otherPlayer.damageShip(m.getDamage());
					removeList.add("RemoveMissile:"+m.getID());
					break;
					
				}
			}
		}
		}
		for( String str : removeList ){
		GamePlay.handler.sendToServer(str);
		}
	}
	
	/**
	 * This takes away shields if the ship has some, otherwise takes health away from the ship. If the ship drops below 1 health, the ship's status is changed to "dead"
	 * @param damage - damage inflicted on the ship
	 */
	private void damageShip(double damage) {
		
		shieldRegenCount = shieldRegenDelay;

		if(shield > damage){
			shield -= damage;
		}else{
			damage -= shield;
			shield = 0;
			health -= damage;
		}
		if (health < 1){
			setShipStatus("Dead");
			GamePlay.handler.sendToServer("Dead:"+shipName);
		}
	}

	/**
	 * This method changed the ship status 
	 * @param status - this is the new status 
	 */
	public void setShipStatus(String status) {
		if ( status.compareTo("Alive") == 0 ){
		shipStatus = ShipStatus.ALIVE;		
		} else if (status.compareTo("Dead") == 0 ){
			if(shipStatus == ShipStatus.ALIVE){
			incrementDeathCount();
	
			
			}
			shipStatus = ShipStatus.DEAD;
		}
	}
	
	/**
	 * This returns the ship's status
	 * @return shipStatus
	 */
	public ShipStatus getShipStatus(){
		return shipStatus;
	}

	/**
	 * This method changes the playerState
	 * @param string - new state
	 */
	public void setClientState(String string) {
		if( string.compareTo("GAME")==0){
			playerState = PlayerState.GAME;
		}
		else if( string.compareTo("MENU")==0){
			playerState = PlayerState.MENU;
		}if( string.compareTo("DISCONNECTED")==0){
			playerState = PlayerState.DISCONNECT;
		}
		
	}
	/**
	 * 
	 * @return playerState_
	 */
	public PlayerState getPlayerState(){
		return playerState;
	}
	/**
	 * 
	 * @return health_
	 */
	public double getHealth(){
		return health;
	}
	/**
	 * This method changes the current health of the ship 
	 * @param newHealth - new health value
	 */
	public void setHealth(double newHealth){
		health = newHealth;
	}
	/**
	 * This changes the name of the ship
	 * @param name - new value
	 */
	public void setName(String name) {
		this.shipName = name;
	}


	/**
	 * @return the maxHealth
	 */
	public double getMaxHealth() {
		return maxHealth;
	}


	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}


	/**
	 * @return the shield
	 */
	public double getShield() {
		return shield;
	}


	/**
	 * @param shield the shield to set
	 */
	public void setShield(double shield) {
		this.shield = shield;
	}


	/**
	 * @return the maxshield
	 */
	public double getMaxShield() {
		return maxShield;
	}


	/**
	 * @param maxshield the maxshield to set
	 */
	public void setMaxShield(double maxshield) {
		this.maxShield = maxshield;
	}


	/**
	 * @return the energy
	 */
	public double getEnergy() {
		return energy;
	}


	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(double energy) {
		this.energy = energy;
	}


	/**
	 * @return the maxEnergy
	 */
	public double getMaxEnergy() {
		return maxEnergy;
	}


	/**
	 * @param maxEnergy the maxEnergy to set
	 */
	public void setMaxEnergy(double maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	/**
	 * This sets up the hit box for the ship, depending on what ship is chosen. 
	 * @param ship
	 */
	public void setHitBox(int ship) {
	switch(ship){
	
	case 8:
		startingHitBoxPoints = new Point[8];
		startingHitBoxPoints[0] = new Point((int)x+4, (int)y+2);
		startingHitBoxPoints[1] = new Point((int)(x+145), (int)y+4);
		startingHitBoxPoints[2] = new Point((int)(x+146), (int)y+31);
		startingHitBoxPoints[3] = new Point((int)(x+101), (int)y+48);
		startingHitBoxPoints[4] = new Point((int)(x+75), (int)y+146);
		startingHitBoxPoints[5] = new Point((int)(x+46), (int)y+46);
		startingHitBoxPoints[6] = new Point((int)(x+2), (int)y+30);
		startingHitBoxPoints[7] = new Point((int)(x+2), (int)y+6);
		hitBox.setHitbox(startingHitBoxPoints, middle);
		break;
	case 0:
		startingHitBoxPoints = new Point[8];
		startingHitBoxPoints[0] = new Point((int)x+62, (int)y);
		startingHitBoxPoints[1] = new Point((int)(x+82), (int)y+73);
		startingHitBoxPoints[2] = new Point((int)(x+124), (int)y+105);
		startingHitBoxPoints[3] = new Point((int)(x+105), (int)y+125);
		startingHitBoxPoints[4] = new Point((int)(x+62), (int)y+137);
		startingHitBoxPoints[5] = new Point((int)(x+20), (int)y+125);
		startingHitBoxPoints[6] = new Point((int)(x+0), (int)y+105);
		startingHitBoxPoints[7] = new Point((int)(x+42), (int)y+73);
		hitBox.setHitbox(startingHitBoxPoints, middle);
		break;
		
	case 9:
		startingHitBoxPoints = new Point[10];
		startingHitBoxPoints[0] = new Point((int)x+60, (int)y+0);
		startingHitBoxPoints[1] = new Point((int)(x+36), (int)y+69);
		startingHitBoxPoints[2] = new Point((int)(x+1), (int)y+113);
		startingHitBoxPoints[3] = new Point((int)(x+1), (int)y+126);
		startingHitBoxPoints[4] = new Point((int)(x+44), (int)y+220);
		startingHitBoxPoints[5] = new Point((int)(x+104), (int)y+220);
		startingHitBoxPoints[6] = new Point((int)(x+147), (int)y+126);
		startingHitBoxPoints[7] = new Point((int)(x+147), (int)y+113);
		startingHitBoxPoints[8] = new Point((int)(x+116), (int)y+69);
		startingHitBoxPoints[9] = new Point((int)(x+89), (int)y+0);
		hitBox.setHitbox(startingHitBoxPoints, middle);
		break;


	case 10:
		startingHitBoxPoints = new Point[9];
		startingHitBoxPoints[0] = new Point((int)x+60, (int)y+0);
		startingHitBoxPoints[1] = new Point((int)(x+34), (int)y+20);
		startingHitBoxPoints[2] = new Point((int)(x+0), (int)y+97);
		startingHitBoxPoints[3] = new Point((int)(x+14), (int)y+113);
		startingHitBoxPoints[4] = new Point((int)(x+52), (int)y+124);
		startingHitBoxPoints[5] = new Point((int)(x+67), (int)y+124);
		startingHitBoxPoints[6] = new Point((int)(x+105), (int)y+113);
		startingHitBoxPoints[7] = new Point((int)(x+119), (int)y+97);
		startingHitBoxPoints[8] = new Point((int)(x+84), (int)y+20);
		hitBox.setHitbox(startingHitBoxPoints, middle);
		break;
		
	}
	}

	/**
	 * This method searches for a missile with a matching id and removes it from the list
	 * @param string - id value
	 */
	public void removeMissile(String string) {
		int index=-1;
		for(Missile m : weaponList_ ){
			if(m.getID().compareTo(string)==0)
				index = weaponList_.indexOf(m);
				
		}
		if(index!=-1)
		weaponList_.remove(index);
			
	}

	/**
	 * This method returns the ship to normal conditions and the location of x = 0, y = 0
	 */
	public void respawn() {
		health = maxHealth;
		shield = maxShield;
		energy = maxEnergy;
		x = Math.random()*100;
		dx=0;
		dy=0;
		y = Math.random()*100;
		shipStatus = ShipStatus.ALIVE;
		GamePlay.handler.sendToServer("Alive:"+shipName);
	}
	
	/**
	 * This returns the deathCount
	 * @return deathCount_
	 */
public int getDeathCount(){
	return deathCount;
}
	/**
	 * This returns true if the ship is dead. False if not dead
	 * @return
	 */
	public boolean isDead() {
		if(shipStatus == ShipStatus.DEAD){
			return true;
		}
		else
		return false;
	}

	/**
	 * This increases the deathCount by one
	 */
	public void incrementDeathCount() {
		deathCount++;
		
	}


}
