import java.util.*;
public class Flatspace {
	public static ArrayList<Double> XObject = new ArrayList<Double>();
	public static ArrayList<Double> YObject = new ArrayList<Double>();
	public static ArrayList<Double> VXObject = new ArrayList<Double>();
	public static ArrayList<Double> VYObject = new ArrayList<Double>();
	public static ArrayList<Double> TypeObject = new ArrayList<Double>();
	public static void main(String args[]) throws InterruptedException {
		Random rng = new Random();
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(0, 0, 2);
		double XShip = 0, YShip = 0, ShipAngle, XMouse, YMouse, StartMs = System.currentTimeMillis();
		boolean DebugMenu = false, LoopRunning = true, GameRunning = true, FancyGraphics = false, Invincible = false, BulletsOn = true;
		int BulletScore = 0, timer = 30, KeyTimeout = 0, GameScore = 0, DetectCount = 0, FPS = 0, LongTimer = 0;
		while (GameRunning) {
			StdDraw.setPenColor(255, 0, 0);
		//FPS Calculation
			if (timer == 30) FPS = (int) (60 * Math.floor(1000 / (System.currentTimeMillis() - StartMs)));
			StdDraw.textRight(1, 1, FPS + " FPS");
			if (timer == 30) StartMs = System.currentTimeMillis();
		//Calculate Ship & Fire Angle
			XMouse = StdDraw.mouseX();
			YMouse = StdDraw.mouseY();
			ShipAngle = Math.toDegrees(Math.atan((YMouse - YShip)/(XMouse - XShip))) - 90;
			if (StdDraw.mouseX() < XShip) {ShipAngle = ShipAngle - 180;}
		//Draws Ship and Target
			if (ShipAngle > -360 && ShipAngle < 360) {
				StdDraw.picture(XShip, YShip, "FixedTriangle.png", .06, .06, ShipAngle);
				StdDraw.circle(XMouse, YMouse, .03); }
		//Moves Ship When Mouse Pressed
			if (StdDraw.mousePressed()) {
				XShip = XShip + (XMouse - XShip) / 50;
				YShip = YShip + (YMouse - YShip) / 50; }
		//Creates Bullets Every 30 Ticks (15 On Fancy)
			if (ShipAngle > -360 && ShipAngle < 360 && BulletsOn) {
				if (timer == 15 || timer == 30) {
					BulletScore++;
					XObject.add(XShip);
					YObject.add(YShip);
					VXObject.add(.02 * Math.cos(Math.toRadians(ShipAngle + 90)));
					VYObject.add(.02 * Math.sin(Math.toRadians(ShipAngle + 90)));
					TypeObject.add(2.0); } }
		//Creates Boxes Every 30 Ticks, Resets Tick Timer
			if (timer == 30) {
				XObject.add((rng.nextDouble() * 2) - 1);
				YObject.add((rng.nextDouble() * 2) - 1);
				if (rng.nextDouble() > .5) {VXObject.add(.002);}
				else {VXObject.add(-.002);}
				if (rng.nextDouble() > .5) {VYObject.add(.002);}
				else {VYObject.add(-.002);}
				TypeObject.add(1.0);
				timer = 0; }
		//API For Object Arrays
			int objectCount = 0;
			while (objectCount < XObject.size()) {
			//Recoil
				if (TypeObject.get(objectCount) == 1.0) {
					if (XObject.get(objectCount) > 1.0 || XObject.get(objectCount) < -1.0) {
						VXObject.set(objectCount, VXObject.get(objectCount) * -1.0);
						VYObject.set(objectCount, VYObject.get(objectCount) * -1.0); }
					if (YObject.get(objectCount) > 1.0 || YObject.get(objectCount) < -1.0) {
						VXObject.set(objectCount, VXObject.get(objectCount) * -1.0);
						VYObject.set(objectCount, VYObject.get(objectCount) * -1.0); } }
			//Applies Speeds To Objects
				XObject.set(objectCount, XObject.get(objectCount) + VXObject.get(objectCount));
				YObject.set(objectCount, YObject.get(objectCount) + VYObject.get(objectCount));
			//For Standard Boxes:
				if (TypeObject.get(objectCount) == 1.0) {
					while (LoopRunning && DetectCount < TypeObject.size()) {
						if (TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 && Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
						//Removes Bullet	
							XObject.remove(DetectCount);
							YObject.remove(DetectCount);
							VXObject.remove(DetectCount);
							VYObject.remove(DetectCount);
							TypeObject.remove(DetectCount);
						//Removes Square
							if (XObject.size() != objectCount) {
								XObject.remove(objectCount);
								YObject.remove(objectCount);
								VXObject.remove(objectCount);
								VYObject.remove(objectCount);
								TypeObject.remove(objectCount); }
							GameScore = GameScore + 5; 
							LoopRunning = false; }
						else {DetectCount++;} }
					//If Square Still Exists, Plots Square
					if (LoopRunning) {
						StdDraw.setPenColor(255, 255, 255);
						StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .04); } }
			//For Bullets:
				else if (TypeObject.get(objectCount) == 2.0) { 
					StdDraw.setPenColor(255, 255, 0);
					StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01); }
			//Moves On To Next Object, If Nothing Deleted
				DetectCount = 0;
				if (LoopRunning) {objectCount++;}
				LoopRunning = true;}
		//Ship Hit Detection
			if (!Invincible) {
				objectCount = 0;
				while (LoopRunning && objectCount < XObject.size()) {
					if (TypeObject.get(objectCount) == 1.0 && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
						GameRunning = false;
						LoopRunning = false;}
					else {objectCount++;} }
				LoopRunning = true;
				objectCount = 0;}
		//Invincible Text & Bullets Off Text
			StdDraw.setPenColor(StdDraw.WHITE);
			if (!BulletsOn && Invincible) StdDraw.textRight(1, .9, "Invincible, Bullets Off");
			else if (Invincible) StdDraw.textRight(1, .9, "Invincible");
		//Gray HUD With Scores
			StdDraw.setPenColor(192, 192, 192);
			StdDraw.filledRectangle(0, -1, 1.5, .2);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textLeft(-1, -.9, "Score: " + GameScore);
		//Debug Menu Hotkey = F3
			if (StdDraw.isKeyPressed(114) && KeyTimeout > 30) {
				if (DebugMenu) {DebugMenu = false; KeyTimeout = 0;}
				else {DebugMenu = true; KeyTimeout = 0;} }
		//Fancy Graphics Hotkey = F1
			if (StdDraw.isKeyPressed(112) && KeyTimeout > 30) {
				if (FancyGraphics) {FancyGraphics = false; KeyTimeout = 0;}
				else {FancyGraphics = true; KeyTimeout = 0;} }
		//Invincible Hotkey = F2
			if (StdDraw.isKeyPressed(113) && KeyTimeout > 30) {
				if (Invincible) {Invincible = false; KeyTimeout = 0;}
				else {Invincible = true; KeyTimeout = 0;} }
		//Bullets Off Hotkey = F4
			if (StdDraw.isKeyPressed(115) && KeyTimeout > 30) {
				if (BulletsOn) {BulletsOn = false; KeyTimeout = 0;}
				else {BulletsOn = true; KeyTimeout = 0;} }
		//Debug Menu HUD
			if (DebugMenu) {
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.textLeft(-1, 1, "DEBUG MENU - E: " + XObject.size());
				StdDraw.textLeft(-1, .9, "S: " + BulletScore);
				StdDraw.textLeft(-1, .8, "T: " + LongTimer);}
		//Shows and Clears Background For Next Frame
			StdDraw.show(0);
			if (FancyGraphics) {StdDraw.picture(0, 0, "Space.jpg", 2.5, 2.5);}
			else {StdDraw.setPenColor(0, 0, 0); StdDraw.filledSquare(0, 0, 2);}
		//Moves Timers Forward
			timer++;
			LongTimer++;
			if (KeyTimeout < 32) {KeyTimeout++;}
			Thread.sleep(16);
}
	//Final Actions
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(0, 0, .25);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0, .1, "You died!");
		StdDraw.text(0, -.1, "Score: " + GameScore);
		StdDraw.show(0);} }
