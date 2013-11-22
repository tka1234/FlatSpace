//FlatSpace Alpha Wilsonis 1.7: Faster Bullets PowerUp!! How to Play Menu. When game ends returns to main menu.
import java.io.*; import java.util.*;
public class Flatspace {
	public static ArrayList<Double> XObject = new ArrayList<Double>();
	public static ArrayList<Double> YObject = new ArrayList<Double>();
	public static ArrayList<Double> VXObject = new ArrayList<Double>();
	public static ArrayList<Double> VYObject = new ArrayList<Double>();
	public static ArrayList<Double> TypeObject = new ArrayList<Double>();
	public static ArrayList<Double> ItemValues = new ArrayList<Double>();
	public static ArrayList<String> ItemNames = new ArrayList<String>();
	public static void main(String args[]) throws InterruptedException, FileNotFoundException {
		Random rng = new Random();
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);
		StdDraw.picture(0, 0, "Splash1.jpg", 2.2, 2.2);
		StdDraw.show(0);
		while (!StdDraw.mousePressed()) {}
		while (true) {
		boolean FirstRunning = true, AllRunning = true;
		StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
		StdDraw.show(0);
		while (FirstRunning) {
		//Start Game
			if (StdDraw.isKeyPressed(112)) {FirstRunning = false; }
		//View High Scores
			if (StdDraw.isKeyPressed(113)) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				File inFile = new File("FlatspaceHighScore.txt");
				Scanner in = new Scanner(inFile);
				int bestScore = (int) in.nextDouble();
				int bestLevel = (int) in.nextDouble();
				double bestTime = in.nextDouble();
				in.close();
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.text(0, .8, "Try to beat this next time!");
				StdDraw.text(0, .4, "High Score:");
				StdDraw.text(0, .3, bestScore + " Points");
				StdDraw.text(0, .2, "Level " + bestLevel);
				StdDraw.text(0, .1, bestTime + " Seconds");
				StdDraw.text(0, -.5, "Press ESC to return to the menu.");
				StdDraw.show(0);
				while (!StdDraw.isKeyPressed(27)) {}
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
				StdDraw.show(0); }
		//Hats
			if (StdDraw.isKeyPressed(114)) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				File inFile = new File("ItemConfig.txt");
				Scanner in = new Scanner(inFile);
				while (in.hasNextDouble()) {ItemValues.add(in.nextDouble()); }
				in.close();
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.text(0, 1, "Owned Items - Press Number to Equip");
				double ItemPlotX = .7; int ItemCount = 0;
				while (ItemCount < ItemValues.size()) {
					if (ItemValues.get(ItemCount) == 1.0 && !ItemNames.contains("Rare Space Potato")) {ItemNames.add("Rare Space Potato"); }
					else if (ItemValues.get(ItemCount) == 2.0 && !ItemNames.contains("Rare Ancient Artifact")) {ItemNames.add("Rare Ancient Artifact"); }
					else if (ItemValues.get(ItemCount) == 3.0 && !ItemNames.contains("Common Ode to Valve")) {ItemNames.add("Common Ode to Valve"); }
					else if (ItemValues.get(ItemCount) == 4.0 && !ItemNames.contains("Cursed Give Diretide")) {ItemNames.add("Cursed Give Diretide"); }
					else if (ItemValues.get(ItemCount) == 5.0 && !ItemNames.contains("Common Manuel's Twinky")) {ItemNames.add("Common Manuel's Twinky"); }
					else if (ItemValues.get(ItemCount) == 6.0 && !ItemNames.contains("Rare Carry Visage")) {ItemNames.add("Rare Carry Visage"); }
					else if (ItemValues.get(ItemCount) == 7.0 && !ItemNames.contains("Common Alpha Wilsonian Antimatter")) {ItemNames.add("Common Alpha Wilsonian Antimatter"); }
					else if (ItemValues.get(ItemCount) == 8.0 && !ItemNames.contains("Common Fancee Kar")) {ItemNames.add("Common Fancee Kar"); }
					if (ItemCount < ItemNames.size()) {StdDraw.text(0, ItemPlotX, ItemCount + ": " + ItemNames.get(ItemCount)); }
					ItemPlotX = ItemPlotX - .1;
					ItemCount++; }
				StdDraw.show(0);
				while (!StdDraw.isKeyPressed(27)) {}
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
				StdDraw.show(0); }
		//How To Play
			if (StdDraw.isKeyPressed(115)) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.text(0, 1, "How To Play Flatspace");
				StdDraw.text(0, .7, "Use the mouse to target the boxes!");
				StdDraw.text(0, .6, "Click to move your spaceship forward.");
				StdDraw.text(0, .5, "Destroy power-ups to get special abilities.");
				StdDraw.text(0, .4, "Levels increase and get harder every 200 points.");
				StdDraw.text(0, .3, "Large Boxes = 50 pts, Small Boxes = 5 pts");
				StdDraw.textRight(1, -1, "Press ESC to return to the menu.");
				StdDraw.show(0);
				while (!StdDraw.isKeyPressed(27)) {}
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
				StdDraw.show(0); }
		//Options
			if (StdDraw.isKeyPressed(116)) {}
			}
		while (AllRunning) {
		//gets high score values
			File inFile = new File("FlatspaceHighScore.txt");
			Scanner in = new Scanner(inFile);
			int bestScore = (int) in.nextDouble();
			int bestLevel = (int) in.nextDouble();
			double bestTime = in.nextDouble();
			in.close();
		//clears arrays of previous info
			XObject.clear();
			YObject.clear();
			VXObject.clear();
			VYObject.clear();
			TypeObject.clear();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(0, 0, 2);
		//initialize variables
			double XShip = 0, YShip = 0, ShipAngle, XMouse, YMouse, ProbabilityLargeDelete = .9, StartTime = System.currentTimeMillis(), RandRead = 0;
			boolean DebugMenu = false, LoopRunning = true, GameRunning = true, Invincible = false, DisplayLevel = false;
			boolean PFireRate = false;
			int KeyTimeout = 0, GameScore = 0, DetectCount = 0, LongTimer = 0, GameLevel = 1, PTimer = 0;
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
				if (ShipAngle > -360 && ShipAngle < 360) {
					if (PFireRate && LongTimer%2 == 0) {
						XObject.add(XShip);
						YObject.add(YShip);
						VXObject.add(.04 * Math.cos(Math.toRadians(ShipAngle + 90)));
						VYObject.add(.04 * Math.sin(Math.toRadians(ShipAngle + 90)));
						TypeObject.add(2.0); }
					else if (LongTimer%7 == 0) {
						XObject.add(XShip);
						YObject.add(YShip);
						VXObject.add(.04 * Math.cos(Math.toRadians(ShipAngle + 90)));
						VYObject.add(.04 * Math.sin(Math.toRadians(ShipAngle + 90)));
						TypeObject.add(2.0); } }
			//Creates Boxes Every 30 Ticks
				if (GameLevel < 30 && LongTimer%(30 - GameLevel) == 0 && LongTimer != 0) {
					RandRead = (rng.nextDouble() * 2) - 1;
					while (RandRead > (XShip - .1) && RandRead < (XShip + .1)) {RandRead = (rng.nextDouble() * 2) - 1; }
					XObject.add(RandRead);
					RandRead = (rng.nextDouble() * 1.75) - .75;
					while (RandRead < (YShip - .1) && RandRead > (YShip + .1)) {RandRead = (rng.nextDouble() * 2) - 1; }
					YObject.add(RandRead);
					if (rng.nextDouble() > .5) {VXObject.add(.002);}
					else {VXObject.add(-.002);}
					if (rng.nextDouble() > .5) {VYObject.add(.002);}
					else {VYObject.add(-.002);}
					if (rng.nextDouble() < .7) {TypeObject.add(1.0); }
					else {TypeObject.add(4.0); } }
			//Game Levels over 30...
				else if (GameLevel >= 30) {
					for (int i = 0; i < (GameLevel - 29); i++) {
						RandRead = (rng.nextDouble() * 2) - 1;
						while (RandRead > (XShip - .1) && RandRead < (XShip + .1)) {RandRead = (rng.nextDouble() * 2) - 1; }
						XObject.add(RandRead);
						RandRead = (rng.nextDouble() * 1.75) - .75;
						while (RandRead < (YShip - .1) && RandRead > (YShip + .1)) {RandRead = (rng.nextDouble() * 2) - 1; }
						YObject.add(RandRead);
						if (rng.nextDouble() > .5) {VXObject.add(.002);}
						else {VXObject.add(-.002);}
						if (rng.nextDouble() > .5) {VYObject.add(.002);}
						else {VYObject.add(-.002);}
						if (rng.nextDouble() < .7) {TypeObject.add(1.0); }
						else {TypeObject.add(4.0); } } }
				if (rng.nextInt(500) == 5) {
					XObject.add((rng.nextDouble() * 2) - 1);
					YObject.add((rng.nextDouble() * 2) - 1);
					VXObject.add(0.0);
					VYObject.add(0.0);
					TypeObject.add(5.0); }
			//API For Object Arrays
				int objectCount = 0;
				while (objectCount < XObject.size()) {
				//Bounce
					if (TypeObject.get(objectCount) == 1.0 || TypeObject.get(objectCount) == 4.0) {
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
						StdDraw.setPenColor(StdDraw.CYAN);
						if (VYObject.get(objectCount) != 0) {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "Explosion.jpg", .16, .16);
							StdDraw.text(XObject.get(objectCount), YObject.get(objectCount) + .08, "+50"); }
						else {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "Explosion.jpg", .08, .08);
							StdDraw.text(XObject.get(objectCount), YObject.get(objectCount) + .08, "+5"); }
						StdDraw.setPenColor(StdDraw.WHITE);
						if (VXObject.get(objectCount) == 0.0002) {
							if (XObject.size() != objectCount) {
								XObject.remove(objectCount);
								YObject.remove(objectCount);
								VXObject.remove(objectCount);
								VYObject.remove(objectCount);
								TypeObject.remove(objectCount);
								LoopRunning = false; } } }
				//For Large Square
					else if (TypeObject.get(objectCount) == 4.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .08 && Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .08 && rng.nextDouble() > ProbabilityLargeDelete) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									TypeObject.set(objectCount, 3.0);
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.0000001);
									GameScore = GameScore + 50; 
									LoopRunning = false; } }
							else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.setPenColor(StdDraw.WHITE);
							StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .08); } }
				//For Fire Rate Powerup
					else if (TypeObject.get(objectCount) == 5.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 && Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									XObject.remove(objectCount);
									YObject.remove(objectCount);
									VXObject.remove(objectCount);
									VYObject.remove(objectCount);
									TypeObject.remove(objectCount);
									if (!PFireRate) {
										PFireRate = true;
										PTimer = 0; }
									LoopRunning = false; } }
								else {DetectCount++;} }
						//If Powerup Still Exists, Plots It
						if (LoopRunning && XObject.size() != objectCount) { StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "PFireRate.jpg", .08, .08); } }
					DetectCount = 0;
					if (LoopRunning) {objectCount++;}
					LoopRunning = true;}
				if (PFireRate && PTimer < 50) { PTimer++; }
				if (PTimer > 48) {PFireRate = false;}
			//Ship Hit Detection
				if (!Invincible) {
					objectCount = 0;
					while (LoopRunning && objectCount < XObject.size()) {
						if (TypeObject.get(objectCount) == 1.0 && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
							GameRunning = false;
							LoopRunning = false;}
						if (TypeObject.get(objectCount) == 4.0 && Math.abs(XObject.get(objectCount)-XShip) < .1 && Math.abs(YObject.get(objectCount)-YShip) < .1) {
							GameRunning = false;
							LoopRunning = false;}
						else {objectCount++;} }
					LoopRunning = true;
					objectCount = 0;}
			//Invincible Text & Bullets Off Text
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.textRight(1, 1, "Alpha Wilsonis 1.6");
				if (Invincible) StdDraw.textRight(1, .9, "Invincible");
			//Gray HUD With Scores
				StdDraw.setPenColor(192, 192, 192);
				StdDraw.filledRectangle(0, -1, 1.5, .2);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(-1, -.9, "Score: " + GameScore);
				StdDraw.textLeft(-1, -1, "High Score: " + bestScore);
				StdDraw.textRight(1, -.9, "Level " + GameLevel);
				StdDraw.textRight(1, -1, "Best Level: " + bestLevel);
				StdDraw.text(0, -.9, ((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " Seconds");
				StdDraw.text(0, -1, "Best Time: " + bestTime + " Seconds");
			//Debug Menu Hotkey = F3
				if (StdDraw.isKeyPressed(114) && KeyTimeout > 30) {
					if (DebugMenu) {DebugMenu = false; KeyTimeout = 0;}
					else {DebugMenu = true; KeyTimeout = 0;} }
			//Invincible Hotkey = F2
				if (StdDraw.isKeyPressed(113) && KeyTimeout > 30) {
					if (Invincible) {Invincible = false; KeyTimeout = 0;}
					else {Invincible = true; KeyTimeout = 0;} }
			//Stops Game if Esc Pressed
				if (StdDraw.isKeyPressed(27) && KeyTimeout > 30) {KeyTimeout = 0; GameRunning = false;}
			//Debug Menu HUD
				if (DebugMenu) {
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.textLeft(-1, 1, "DEBUG MENU - E: " + XObject.size());
					StdDraw.textLeft(-1, .8, "Clock: " + LongTimer); }
			//Shows and Clears Background For Next Frame
				StdDraw.show(0);
				if (GameRunning) {
					StdDraw.setPenColor(0, 0, 0); 
					StdDraw.picture(0, 0, "Grid.jpg", 2.5, 2.5);}
				if (XObject.size() > 100) {
					XObject.remove(0);
					YObject.remove(0);
					VXObject.remove(0);
					VYObject.remove(0);
					TypeObject.remove(0); }
			//Moves Timers Forward
				LongTimer++;
				StdDraw.setPenColor(StdDraw.MAGENTA);
				if (GameLevel != (int) Math.floor(GameScore / 200) + 1) { 
					LongTimer = 0;
					KeyTimeout = 0;
					DisplayLevel = true;
					StdDraw.text(0, 0, "Level " + GameLevel);}
				if (DisplayLevel && KeyTimeout < 30) {StdDraw.text(0, 0, "Level " + GameLevel); }
				else {DisplayLevel = false;}
				GameLevel = (int) Math.floor(GameScore / 200) + 1;
				if (LongTimer == 10000) {LongTimer = 0;}
				if (KeyTimeout < 32) {KeyTimeout++;}
				Thread.sleep(16);
				}
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(0, -1, 1.5, .2);
		StdDraw.setPenColor(StdDraw.BLACK);
	//prints high score, level, and time to file.
		String EndingSentence = "";
		File outFile = new File("FlatspaceHighScore.txt");
		PrintWriter out = new PrintWriter(outFile);
		if (GameScore > bestScore) {
			out.print(GameScore + " ");
			EndingSentence = EndingSentence + "New Best Score! "; }
		else {out.print(bestScore + " ");}
		if (GameLevel > bestLevel) {
			out.print(GameLevel + " ");
			EndingSentence = EndingSentence + "New Best Level! "; }
		else {out.print(bestLevel + " ");}
		if (((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) > bestTime) {
			out.print(((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " ");
			EndingSentence = EndingSentence + "New Longest Time! "; }
		else {out.print(bestTime + " ");}
		out.close();
	//hud with results
		StdDraw.text(0, -.9, "You survived for " + ((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " seconds! Press ESC to return to menu.");
		StdDraw.text(0, -1, "Score: " + GameScore + " - Level: " + GameLevel);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.textRight(1, .9, EndingSentence);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.show(0);
		Thread.sleep(1000);
		while (!StdDraw.isKeyPressed(27)) {}
		AllRunning = false;
		} } } }
