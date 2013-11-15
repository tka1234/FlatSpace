import java.util.*;
public class Flatspace {
	public static ArrayList<Double> XObject = new ArrayList<Double>();
	public static ArrayList<Double> YObject = new ArrayList<Double>();
	public static ArrayList<Double> VXObject = new ArrayList<Double>();
	public static ArrayList<Double> VYObject = new ArrayList<Double>();
	public static ArrayList<Double> TypeObject = new ArrayList<Double>(); //data value: 1.0 = box, 2.0 = bullet
	public static void main(String args[]) {
		Random rng = new Random();
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);
		double XShip = 0; double YShip = 0;
		StdDraw.setPenColor(0, 0, 0);
		StdDraw.filledSquare(0, 0, 2);
		double ShipAngle, XMouse, YMouse;
		boolean DebugMenu = false, LoopRunning = true;
		int BulletScore = 0, timer = 30, KeyTimeout = 0, GameScore = 0, DetectCount = 0;
		while (true) {
			StdDraw.setPenColor(255, 0, 0);
			XMouse = StdDraw.mouseX();
			YMouse = StdDraw.mouseY();
		//calculate angle of ship towards mouse
			ShipAngle = Math.toDegrees(Math.atan((YMouse - YShip)/(XMouse - XShip))) - 90;
			if (StdDraw.mouseX() < XShip) {ShipAngle = ShipAngle - 180;}
		//plots the triangle and cursor
			if (ShipAngle > -360 && ShipAngle < 360) {
				StdDraw.picture(XShip, YShip, "Starship.png", .06, .06, ShipAngle);
				StdDraw.circle(XMouse, YMouse, .03); }
		//movement of triangle
			if (StdDraw.mousePressed()) {
				XShip = XShip + (XMouse - XShip) / 70;
				YShip = YShip + (YMouse - YShip) / 70; }
		//movement of bullet creates bullets as entities
			if (ShipAngle > -360 && ShipAngle < 360) {
				if (timer == 30) {
					BulletScore++;
					XObject.add(XShip);
					YObject.add(YShip);
					VXObject.add(.01 * Math.cos(Math.toRadians(ShipAngle + 90)));
					VYObject.add(.01 * Math.sin(Math.toRadians(ShipAngle + 90)));
					TypeObject.add(2.0); } }
		//adds a new box
			if (timer == 30) {
				XObject.add((rng.nextDouble() * 2) - 1);
				YObject.add((rng.nextDouble() * 2) - 1);
				if (rng.nextDouble() > .5) {VXObject.add(.001);}
				else {VXObject.add(-.001);}
				if (rng.nextDouble() > .5) {VYObject.add(.001);}
				else {VYObject.add(-.001);}
				TypeObject.add(1.0);
				timer = 0; }
			int objectCount = 0;
		//draws objects from object arrays
			while (objectCount < XObject.size()) {
			//computes the new position based on speed
				XObject.set(objectCount, XObject.get(objectCount) + VXObject.get(objectCount));
				YObject.set(objectCount, YObject.get(objectCount) + VYObject.get(objectCount));
			// if it's a square
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
							//StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "Explosion.jpg", .06, .06);
							if (XObject.size() != objectCount) {
								XObject.remove(objectCount);
								YObject.remove(objectCount);
								VXObject.remove(objectCount);
								VYObject.remove(objectCount);
								TypeObject.remove(objectCount); }
							GameScore++; 
							LoopRunning = false; }
						else {DetectCount++;} }
					// if it's not deleted, then it prints the square
					if (LoopRunning) {StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .04); } }
			// if it's a bullet
				else if (TypeObject.get(objectCount) == 2.0) { StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01);}
			//increases object count and resets loop running
				DetectCount = 0;
				if (LoopRunning) {objectCount++;}
				LoopRunning = true;}
			objectCount = 0;
		//edge detection and deletion from object arrays
			while (objectCount < XObject.size()) {
				if (XObject.get(objectCount) > 1 || YObject.get(objectCount) > 1 || XObject.get(objectCount) < -1 || YObject.get(objectCount) < -1) {
					XObject.remove(objectCount);
					YObject.remove(objectCount);
					VXObject.remove(objectCount);
					VYObject.remove(objectCount);
					TypeObject.remove(objectCount); }
				objectCount++; }
		//finalizes frame and clears
			StdDraw.setPenColor(192, 192, 192);
			StdDraw.filledRectangle(0, -1, 1.5, .2);
			if (StdDraw.isKeyPressed(114) && KeyTimeout > 30) {
				if (DebugMenu) {DebugMenu = false; KeyTimeout = 0;}
				else {DebugMenu = true; KeyTimeout = 0;} }
			if (DebugMenu) {
				StdDraw.setPenColor(255, 255, 255); //white
				StdDraw.textLeft(-1, 1, "DEBUG MENU - E: " + XObject.size()); //entities in object array
				StdDraw.textLeft(-1, .9, "S: " + BulletScore); /*shots*/ 
				StdDraw.textLeft(-1, .8, "T: " + timer);}
			StdDraw.setPenColor(0, 0, 0);
			StdDraw.textLeft(-1, -.9, "Score: " + GameScore);
			StdDraw.show(0);
			//StdDraw.picture(0, 0, "Space.jpg", 2.5, 2.5);
			StdDraw.filledSquare(0, 0, 2);
			timer++;
			if (KeyTimeout < 32) {KeyTimeout++;}
} } }
