//FlatSpace Alpha 1.2
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
		while (true) {
			XObject.clear();
			YObject.clear();
			VXObject.clear();
			VYObject.clear();
			TypeObject.clear();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(0, 0, 2);
			double XShip = 0, YShip = 0, ShipAngle, XMouse, YMouse;
			boolean DebugMenu = false, LoopRunning = true, GameRunning = true, Invincible = false, BulletsOn = true;
			int BulletScore = 0, KeyTimeout = 0, GameScore = 0, DetectCount = 0, LongTimer = 0;
			while (GameRunning) {
				StdDraw.setPenColor(255, 0, 0);
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
			//Creates Bullets Every 30 Ticks
				if (ShipAngle > -360 && ShipAngle < 360 && BulletsOn) {
					if (LongTimer%7 == 0) {
						BulletScore++;
						XObject.add(XShip);
						YObject.add(YShip);
						VXObject.add(.04 * Math.cos(Math.toRadians(ShipAngle + 90)));
						VYObject.add(.04 * Math.sin(Math.toRadians(ShipAngle + 90)));
						TypeObject.add(2.0); } }
			//Creates Boxes Every 30 Ticks, Resets Tick Timer
				if (LongTimer%30 == 0) {
					XObject.add((rng.nextDouble() * 2) - 1);
					YObject.add((rng.nextDouble() * 1.75) - .75);
					if (rng.nextDouble() > .5) {VXObject.add(.002);}
					else {VXObject.add(-.002);}
					if (rng.nextDouble() > .5) {VYObject.add(.002);}
					else {VYObject.add(-.002);}
					TypeObject.add(1.0); }
			//API For Object Arrays
				int objectCount = 0;
				while (objectCount < XObject.size()) {
				//Bounce
					if (TypeObject.get(objectCount) == 1.0) {
						if (XObject.get(objectCount) > 1.0 || XObject.get(objectCount) < -1.0) {
							VXObject.set(objectCount, VXObject.get(objectCount) * -1.0);
							VYObject.set(objectCount, VYObject.get(objectCount) * -1.0); }
						if (YObject.get(objectCount) > 1.0 || YObject.get(objectCount) < -.75) {
							VXObject.set(objectCount, VXObject.get(objectCount) * -1.0);
							VYObject.set(objectCount, VYObject.get(objectCount) * -1.0); } }
				//Applies Speeds To Objects
					XObject.set(objectCount, XObject.get(objectCount) + VXObject.get(objectCount));
					YObject.set(objectCount, YObject.get(objectCount) + VYObject.get(objectCount));
				//For Standard Boxes:
					if (TypeObject.get(objectCount) == 1.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 && Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
							//Removes Bullet
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
							//Changes Square To Explosion Entity
								if (XObject.size() != objectCount) {
									TypeObject.set(objectCount, 3.0);
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.0);
									GameScore = GameScore + 5; 
									LoopRunning = false; } }
							else {DetectCount++;} }
					//If Square Still Exists, Plots Square
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.setPenColor(255, 255, 255);
							StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .04); } }
				//For Bullets:
					else if (TypeObject.get(objectCount) == 2.0) {
						StdDraw.setPenColor(255, 255, 0);
						StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01); }
				//For Explosion Entity
					else if (TypeObject.get(objectCount) == 3.0) {
						VXObject.set(objectCount, VXObject.get(objectCount) + 0.00001);
						StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "Explosion.jpg", .08, .08);
						StdDraw.setPenColor(StdDraw.CYAN);
						StdDraw.text(XObject.get(objectCount), YObject.get(objectCount) + .08, "+5");
						StdDraw.setPenColor(StdDraw.WHITE);
						if (VXObject.get(objectCount) == 0.0002) {
							if (XObject.size() != objectCount) {
								XObject.remove(objectCount);
								YObject.remove(objectCount);
								VXObject.remove(objectCount);
								VYObject.remove(objectCount);
								TypeObject.remove(objectCount);
								LoopRunning = false; } } }
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
				if (GameRunning) {
					StdDraw.setPenColor(0, 0, 0); 
					//StdDraw.filledSquare(0, 0, 2);
					StdDraw.picture(0, 0, "Grid.jpg", 2.5, 2.5);}
			//Moves Timers Forward
				LongTimer++;
				if (KeyTimeout < 32) {KeyTimeout++;}
				Thread.sleep(16); }
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(0, -1, 1.5, .2);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0, -.9, "You died! ESC to restart.");
		StdDraw.text(0, -1, "Score: " + GameScore);
		StdDraw.show(0);
		while (!StdDraw.isKeyPressed(27)) {}
		} } }
