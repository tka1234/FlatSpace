//FlatSpace Beta Wilsonis 1.1: Opens the CD drive to insert a quarter, lol!
import java.awt.Font; import java.awt.FontFormatException; import java.io.*; import java.util.*;
public class Flatspace {
	public static ArrayList<Double> XObject = new ArrayList<Double>();
	public static ArrayList<Double> YObject = new ArrayList<Double>();
	public static ArrayList<Double> VXObject = new ArrayList<Double>();
	public static ArrayList<Double> VYObject = new ArrayList<Double>();
	public static ArrayList<Double> TypeObject = new ArrayList<Double>();
	public static ArrayList<Double> ItemValues = new ArrayList<Double>();
	public static ArrayList<String> ItemNames = new ArrayList<String>();
	//Remember to add more powerups and fix all of the textures!
	public static void main(String args[]) throws InterruptedException, FileNotFoundException {
		Random rng = new Random();
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);
		StdDraw.picture(0, 0, "Splash1.jpg", 2.2, 2.2);
		StdDraw.show(0);
		while (!StdDraw.mousePressed()) {}
		try {StdDraw.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("ARCADE_R.ttf")).deriveFont((float) 20)); }
		catch (FontFormatException e) {e.printStackTrace(); }
		catch (IOException e) {e.printStackTrace(); }
		String FileSBox = "FSSquare.png";
		String FileLBox = "FSSquare.png";
		String FileStar = "FSStar.png";
		String FileFrag = "FSFrag.png";
		String FileNPCs = "FSNpc.png";
		String FileCurs = "FSTriangle.png";
		int TextureChosen = 1;
		while (true) {
		Thread.sleep(50);
		boolean FirstRunning = true, AllRunning = true, OpenCD = false;
		File inFile = new File("FlatspaceOptions.txt");
		Scanner in = new Scanner(inFile);
		boolean VSync = true;
		if (in.nextDouble() == 0.0) VSync = false;
		in.close();
		inFile = new File("FlatspaceConfig.txt");
		in = new Scanner(inFile);
		boolean ItemsEnabled = true;
		if (in.nextDouble() == 0.0) ItemsEnabled = false;
		in.close();
		StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
		StdDraw.show(0);
		while (FirstRunning) {
		//Start Game
			if (StdDraw.isKeyPressed(112)) {FirstRunning = false; }
		//View High Scores
			if (StdDraw.isKeyPressed(113)) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				inFile = new File("FlatspaceHighScore.txt");
				in = new Scanner(inFile);
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
				StdDraw.text(0, -1, "Press ESC to return to the menu.");
				StdDraw.show(0);
				while (!StdDraw.isKeyPressed(27)) {}
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
				StdDraw.show(0);
				Thread.sleep(100);}
		//Inventory
			if (StdDraw.isKeyPressed(114)) {
				StdDraw.setPenColor(StdDraw.WHITE);
				if (!ItemsEnabled) {
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(0, 0, 2);
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.text(0, 0, "No textures are enabled.");
					StdDraw.text(0, -1, "Press ESC to return to the menu.");
					StdDraw.show(0);
					while (!StdDraw.isKeyPressed(27)) {}
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(0, 0, 2);
					StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
					StdDraw.show(0); }
				else {
					StdDraw.picture(0, 0, "GInventory.jpg", 2.2, 2.2);
					StdDraw.text(0, -1.05, "Current Texture: #" + TextureChosen);
					StdDraw.show(0);
					boolean WaitLooping = true;
					while (WaitLooping) {
						if (StdDraw.isKeyPressed(49)) { //Original
							FileSBox = "FSSquare.png";
							FileLBox = "FSSquare.png";
							FileStar = "FSStar.png";
							FileFrag = "FSFrag.png";
							FileNPCs = "FSNpc.png";
							FileCurs = "FSTriangle.png";
							TextureChosen = 1;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(50)) { //Space Potato
							FileSBox = "Texture2/FSSquare.png";
							FileLBox = "Texture2/FSSquare.png";
							FileStar = "Texture2/FSStar.png";
							FileFrag = "Texture2/FSFrag.png";
							FileNPCs = "Texture2/FSNpc.png";
							FileCurs = "Texture2/FSTriangle.png";
							TextureChosen = 2;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(51)) { //Trademark of Valve
							FileSBox = "Texture3/FSSquare.png";
							FileLBox = "Texture3/FSSquare.png";
							FileStar = "Texture3/FSStar.png";
							FileFrag = "Texture3/FSFrag.png";
							FileNPCs = "Texture3/FSNpc.png";
							FileCurs = "Texture3/FSTriangle.png";
							TextureChosen = 3;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(52)) { //Give Diretide
							FileSBox = "Texture4/FSSquare.png";
							FileLBox = "Texture4/FSSquare.png";
							FileStar = "Texture4/FSStar.png";
							FileFrag = "Texture4/FSFrag.png";
							FileNPCs = "Texture4/FSNpc.png";
							FileCurs = "Texture4/FSTriangle.png";
							TextureChosen = 4;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(53)) { //Manuel's Twinky
							FileSBox = "Texture5/FSSquare.png";
							FileLBox = "Texture5/FSSquare.png";
							FileStar = "Texture5/FSStar.png";
							FileFrag = "Texture5/FSFrag.png";
							FileNPCs = "Texture5/FSNpc.png";
							FileCurs = "Texture5/FSTriangle.png";
							TextureChosen = 5;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(54)) { //Carry Visage
							FileSBox = "Texture6/FSSquare.png";
							FileLBox = "Texture6/FSSquare.png";
							FileStar = "Texture6/FSStar.png";
							FileFrag = "Texture6/FSFrag.png";
							FileNPCs = "Texture6/FSNpc.png";
							FileCurs = "Texture6/FSTriangle.png";
							TextureChosen = 6;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(55)) { //Wilsonian Antimatter
							FileSBox = "Texture7/FSSquare.png";
							FileLBox = "Texture7/FSSquare.png";
							FileStar = "Texture7/FSStar.png";
							FileFrag = "Texture7/FSFrag.png";
							FileNPCs = "Texture7/FSNpc.png";
							FileCurs = "Texture7/FSTriangle.png";
							TextureChosen = 7;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(56)) { //The Fancee Kar
							FileSBox = "Texture8/FSSquare.png";
							FileLBox = "Texture8/FSSquare.png";
							FileStar = "Texture8/FSStar.png";
							FileFrag = "Texture8/FSFrag.png";
							FileNPCs = "Texture8/FSNpc.png";
							FileCurs = "Texture8/FSTriangle.png";
							TextureChosen = 8;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(57)) { //FatSpace
							FileSBox = "Texture9/FSSquare.png";
							FileLBox = "Texture9/FSSquare.png";
							FileStar = "Texture9/FSStar.png";
							FileFrag = "Texture9/FSFrag.png";
							FileNPCs = "Texture9/FSNpc.png";
							FileCurs = "Texture9/FSTriangle.png";
							TextureChosen = 9;
							WaitLooping = false; }
						if (StdDraw.isKeyPressed(27)) {WaitLooping = false;} }
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(0, 0, 2);
					StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
					StdDraw.show(0);
					Thread.sleep(100); } }
		//How To Play
			if (StdDraw.isKeyPressed(115)) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.text(0, 1, "How To Play Flatspace");
				StdDraw.text(0, .7, "Use mouse to target objects!");
				StdDraw.text(0, .6, "Click to move yourself forward");
				StdDraw.text(0, .5, "Powerups give special abilities");
				StdDraw.text(0, .4, "Levels increase every 200 points");
				StdDraw.text(0, .2, "Small Boxes = 5 pts");
				StdDraw.text(0, .1, "Large Boxes = 50 pts");
				StdDraw.text(0, 0, "Stars = 75 pts");
				StdDraw.text(0, -.1, "Star Pieces = 20 pts");
				StdDraw.text(0, -.2, "Enemies = 75 pts");
				StdDraw.text(0, -1, "Press ESC to return to the menu.");
				StdDraw.show(0);
				while (!StdDraw.isKeyPressed(27)) {}
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
				StdDraw.show(0);
				Thread.sleep(100);}
		//Options
			if (StdDraw.isKeyPressed(116)) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.text(0, 0, "VSync On: " + VSync);
				StdDraw.text(0, .2, "To switch press F1.");
				StdDraw.text(0, -1, "Press ESC to return to the menu.");
				StdDraw.show(0);
				while (!StdDraw.isKeyPressed(27)) {
					if (StdDraw.isKeyPressed(112)) {
						if (VSync) VSync = false;
						else VSync = true;
						File outFile = new File("FlatspaceOptions.txt");
						PrintWriter out = new PrintWriter(outFile);
						if (VSync) out.print(1.0);
						else out.print(0.0);
						out.close();
						Thread.sleep(500);
						StdDraw.text(0, -.3, "Change Confirmed. VSync: " + VSync);
						StdDraw.show(0);
						Thread.sleep(1000);
						break; } }
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "Splash2.jpg", 2.2, 2.2);
				StdDraw.show(0); 
				Thread.sleep(100);} }
		while (AllRunning) {
		//gets high score values
			inFile = new File("FlatspaceHighScore.txt");
			in = new Scanner(inFile);
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
			double XShip = 0, YShip = 0, ShipAngle, XMouse, YMouse, StartTime = System.currentTimeMillis(), RandRead = 0, FpsStart = System.currentTimeMillis();
			boolean DebugMenu = false, LoopRunning = true, GameRunning = true, Invincible = false, DisplayLevel = false, PFireRate = false, FastMode = false;
			int KeyTimeout = 0, GameScore = 0, DetectCount = 0, LongTimer = 0, GameLevel = 1, PTimer = 0, rotation = 0, NpcRotation = 0, FramesPassed = 0, FPS = 0, VsyncCount = 16;
			while (GameRunning) {
			//Calculation of FPS
				FramesPassed++;
				if (System.currentTimeMillis() - FpsStart > 1000) {
					FPS = FramesPassed;
					FramesPassed = 0;
					FpsStart = System.currentTimeMillis(); }
				rotation++;
				if (rotation == 360) rotation = 0;
				StdDraw.setPenColor(255, 0, 0);
			//Mouse Positioning
				XMouse = StdDraw.mouseX();
				YMouse = StdDraw.mouseY();
				StdDraw.circle(XMouse, YMouse, .03);
			//Ship Angle Calculation
				ShipAngle = Math.toDegrees(Math.atan((YMouse - YShip)/(XMouse - XShip))) - 90;
				if (StdDraw.mouseX() < XShip) ShipAngle = ShipAngle - 180;
				if (ShipAngle > -360 && ShipAngle < 360) StdDraw.picture(XShip, YShip, FileCurs, .06, .06, ShipAngle);
				if (StdDraw.mousePressed()) {XShip = XShip + (XMouse - XShip) / 50; YShip = YShip + (YMouse - YShip) / 50; }
			//Creation of Bullets
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
			//Creation of Small & Large Boxes
				if (GameLevel < 30 && LongTimer%(30 - GameLevel) == 0 && LongTimer != 0) {
					RandRead = (rng.nextDouble() * 2) - 1;
					while (RandRead > (XShip - .2) && RandRead < (XShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					XObject.add(RandRead);
					RandRead = (rng.nextDouble() * 1.75) - .75;
					while (RandRead < (YShip - .2) && RandRead > (YShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					YObject.add(RandRead);
					if (rng.nextDouble() > .5) {VXObject.add(.002);}
					else {VXObject.add(-.002);}
					if (rng.nextDouble() > .5) {VYObject.add(.002);}
					else {VYObject.add(-.002);}
					if (rng.nextDouble() < .7) {TypeObject.add(1.0); }
					else {TypeObject.add(4.0); } }
				else if (GameLevel >= 30) {
					for (int i = 0; i < (GameLevel - 29); i++) {
						RandRead = (rng.nextDouble() * 2) - 1;
						while (RandRead > (XShip - .2) && RandRead < (XShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
						XObject.add(RandRead);
						RandRead = (rng.nextDouble() * 1.75) - .75;
						while (RandRead < (YShip - .2) && RandRead > (YShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
						YObject.add(RandRead);
						if (rng.nextDouble() > .5) {VXObject.add(.002);}
						else {VXObject.add(-.002);}
						if (rng.nextDouble() > .5) {VYObject.add(.002);}
						else {VYObject.add(-.002);}
						if (rng.nextDouble() < .7) {TypeObject.add(1.0); }
						else {TypeObject.add(4.0); } } }
			//Star Spawning
				if (GameLevel > 5 && rng.nextInt(100) == 50) {
					RandRead = (rng.nextDouble() * 2) - 1;
					while (RandRead > (XShip - .2) && RandRead < (XShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					XObject.add(RandRead);
					RandRead = (rng.nextDouble() * 1.75) - .75;
					while (RandRead < (YShip - .2) && RandRead > (YShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					YObject.add(RandRead);
					if (rng.nextDouble() > .5) {VXObject.add(.003);}
					else {VXObject.add(-.003);}
					if (rng.nextDouble() > .5) {VYObject.add(.003);}
					else {VYObject.add(-.003);}
					if (rng.nextDouble() < .7) {TypeObject.add(1.0); }
					else {TypeObject.add(6.0); } }
			//Spawning of NPC
				if (GameLevel > 10 && rng.nextInt(200) == 50 && !TypeObject.contains(8.0)) {
					NpcRotation = rng.nextInt(360);
					RandRead = (rng.nextDouble() * 2) - 1;
					while (RandRead > (XShip - .2) && RandRead < (XShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					XObject.add(RandRead);
					RandRead = (rng.nextDouble() * 1.75) - .75;
					while (RandRead < (YShip - .2) && RandRead > (YShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					YObject.add(RandRead);
					if (rng.nextDouble() > .5) {VXObject.add(.001);}
					else {VXObject.add(-.001);}
					if (rng.nextDouble() > .5) {VYObject.add(.001);}
					else {VYObject.add(-.001);}
					if (rng.nextDouble() < .7) {TypeObject.add(8.0); } }
			//NPC Bullets
				if (TypeObject.contains(8.0) && LongTimer%12 == 0) {
						XObject.add(XObject.get(TypeObject.indexOf(8.0)));
						YObject.add(YObject.get(TypeObject.indexOf(8.0)));
						VXObject.add(.04 * Math.cos(Math.toRadians(NpcRotation + 90)));
						VYObject.add(.04 * Math.sin(Math.toRadians(NpcRotation + 90)));
						TypeObject.add(9.0); }
			//Power Up Spawning
				if (rng.nextInt(500) == 5) {
					XObject.add((rng.nextDouble() * 2) - 1);
					YObject.add((rng.nextDouble() * 2) - 1);
					VXObject.add(0.0);
					VYObject.add(0.0);
					TypeObject.add(5.0); }
			//Object Array Loop
				int objectCount = 0;
				while (objectCount < XObject.size() && objectCount < TypeObject.size()) {
				//Remove Excess Corrupted Objects
					while (XObject.size() > TypeObject.size()) {
						XObject.remove(XObject.size() - 1);
						YObject.remove(YObject.size() - 1);
						VXObject.remove(VXObject.size() - 1);
						VYObject.remove(VYObject.size() - 1); }
					while (XObject.size() < TypeObject.size()) {
						TypeObject.remove(TypeObject.size() - 1); }
				//Edge Reaction Bounce
					if (TypeObject.get(objectCount) == 1.0 || TypeObject.get(objectCount) == 4.0 || TypeObject.get(objectCount) == 6.0 || TypeObject.get(objectCount) == 7.0 || TypeObject.get(objectCount) == 8.0) {
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
							if (DetectCount != TypeObject.size() && objectCount != XObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									TypeObject.set(objectCount, 3.0);
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.0);
									GameScore = GameScore + 5; 
									LoopRunning = false; } }
							else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.setPenColor(255, 255, 255);
							if (FastMode) StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .04);
							else StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileSBox, .08, .08, rotation);
							} }
				//For Bullets:
					else if (TypeObject.get(objectCount) == 2.0) {
						if (XObject.get(objectCount) > 1.1 || XObject.get(objectCount) < -1.1 || YObject.get(objectCount) > 1.1 || YObject.get(objectCount) < -1.1) {
							XObject.remove(objectCount);
							YObject.remove(objectCount);
							VXObject.remove(objectCount);
							VYObject.remove(objectCount);
							TypeObject.remove(objectCount);
							LoopRunning = false; }
						StdDraw.setPenColor(255, 255, 0);
						if (objectCount != XObject.size() && LoopRunning) StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01); }
				//For Explosion Entity
					else if (TypeObject.get(objectCount) == 3.0) {
						VXObject.set(objectCount, VXObject.get(objectCount) + 0.00001);
						StdDraw.setPenColor(StdDraw.CYAN);
						if (VYObject.get(objectCount) == 0.00001) {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "FSExplosion.png", .16, .16);
							StdDraw.text(XObject.get(objectCount), YObject.get(objectCount) + .08, "+50"); }
						else if (VYObject.get(objectCount) == 0.00002) {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "FSExplosion.png", .08, .08);
							StdDraw.text(XObject.get(objectCount), YObject.get(objectCount) + .08, "+75"); }
						else if (VYObject.get(objectCount) == 0.00003) {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "FSExplosion.png", .08, .08);
							StdDraw.text(XObject.get(objectCount), YObject.get(objectCount) + .08, "+20"); }
						else {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "FSExplosion.png", .08, .08);
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
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .08 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .08 && rng.nextInt(4) == 2) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									TypeObject.set(objectCount, 3.0);
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.00001);
									GameScore = GameScore + 50; 
									LoopRunning = false; } }
							else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.setPenColor(StdDraw.WHITE);
							if (FastMode) StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .08);
							else StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileLBox, .16, .16, rotation); } }
				//For Fire Rate Powerup
					else if (TypeObject.get(objectCount) == 5.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && objectCount != XObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
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
						if (LoopRunning && XObject.size() != objectCount) { StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "PFireRate.jpg", .08, .08); } }
				//For Star
					else if (TypeObject.get(objectCount) == 6.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									for (int i = 1; i != 5; i++) {
										XObject.add(XObject.get(objectCount));
										YObject.add(YObject.get(objectCount));
										VXObject.add(.015 * Math.cos((rng.nextDouble() * 6.28) - 3.14));
										VYObject.add(.015 * Math.sin((rng.nextDouble() * 6.28) - 3.14));
										TypeObject.add(7.0); }
									GameScore = GameScore + 75;
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.00002);
									TypeObject.set(objectCount, 3.0);
									LoopRunning = false; } }
								else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileStar, .08, .08, rotation);} }
				//Star Fragments
					else if (TypeObject.get(objectCount) == 7.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .03 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .03) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									GameScore = GameScore + 20;
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.00003);
									TypeObject.set(objectCount, 3.0);
									LoopRunning = false; } }
								else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileFrag, .05, .05, rotation);} }	
				//NPC
					else if (TypeObject.get(objectCount) == 8.0) {
						NpcRotation++;
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									GameScore = GameScore + 75;
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.00002);
									TypeObject.set(objectCount, 3.0);
									LoopRunning = false; } }
								else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) { StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileNPCs, .08, .08, NpcRotation); } }
				//Bullets from NPC
					else if (TypeObject.get(objectCount) == 9.0) {
						if (XObject.get(objectCount) > 1.1 || XObject.get(objectCount) < -1.1 || YObject.get(objectCount) > 1.1 || YObject.get(objectCount) < -1.1) {
							XObject.remove(objectCount);
							YObject.remove(objectCount);
							VXObject.remove(objectCount);
							VYObject.remove(objectCount);
							TypeObject.remove(objectCount);
							LoopRunning = false; }
						StdDraw.setPenColor(StdDraw.GREEN);
						if (objectCount != XObject.size()) StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01); }
				//Add New Types Here
					else if (TypeObject.get(objectCount) == 10.0) { }
					DetectCount = 0;
					if (LoopRunning) objectCount++;
					else LoopRunning = true; }
				if (PFireRate && PTimer < 50) PTimer++;
				if (PTimer > 48) PFireRate = false;
			//Ship Hit Detection
				if (!Invincible) {
					objectCount = 0;
					while (LoopRunning && objectCount < XObject.size() && objectCount < TypeObject.size()) {
						if (TypeObject.get(objectCount) == 1.0 && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
							GameRunning = false;
							LoopRunning = false;}
						if (TypeObject.get(objectCount) == 8.0 && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
							GameRunning = false;
							LoopRunning = false;}
						if (TypeObject.get(objectCount) == 6.0 && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
							GameRunning = false;
							LoopRunning = false;}
						if (TypeObject.get(objectCount) == 7.0 && Math.abs(XObject.get(objectCount)-XShip) < .04 && Math.abs(YObject.get(objectCount)-YShip) < .04) {
							GameRunning = false;
							LoopRunning = false;}
						if (TypeObject.get(objectCount) == 9.0 && Math.abs(XObject.get(objectCount)-XShip) < .04 && Math.abs(YObject.get(objectCount)-YShip) < .04) {
							GameRunning = false;
							LoopRunning = false;}
						if (TypeObject.get(objectCount) == 4.0 && Math.abs(XObject.get(objectCount)-XShip) < .1 && Math.abs(YObject.get(objectCount)-YShip) < .1) {
							GameRunning = false;
							LoopRunning = false;}
						else {objectCount++;} }
					LoopRunning = true;
					objectCount = 0;}
			//Score HUD
				StdDraw.picture(0, -1, "Footer2.png", 2.2, .5);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(-.95, -.95, "SCR:" + GameScore);
				StdDraw.text(0, -.95, ((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " SEC");
				StdDraw.textRight(.95, -.95, "LVL " + GameLevel);
				StdDraw.text(0, -1.05, "HISCORE:" + bestScore);
			//Debug Menu Hotkey = F3
				if (StdDraw.isKeyPressed(114) && KeyTimeout > 30) {
					if (DebugMenu) {DebugMenu = false; KeyTimeout = 0;}
					else {DebugMenu = true; KeyTimeout = 0;} }
			//Invincible Hotkey = F2
				if (StdDraw.isKeyPressed(113) && KeyTimeout > 30) {
					if (Invincible) {Invincible = false; KeyTimeout = 0;}
					else {Invincible = true; KeyTimeout = 0;} }
				StdDraw.setPenColor(StdDraw.WHITE);
				if (Invincible) StdDraw.textRight(1, .9, "Invincible");
			//Stops Game if Esc Pressed
				if (StdDraw.isKeyPressed(27) && KeyTimeout > 30) {KeyTimeout = 0; GameRunning = false;}
			//Debug Menu HUD
				if (DebugMenu) {
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.textLeft(-1, 1, "DEBUG MENU");
					StdDraw.textLeft(-1, .9, "ENT: " + XObject.size());
					StdDraw.textLeft(-1, .8, "FPS: " + FPS);
					StdDraw.textLeft(-1, .7, "VSY: " + VsyncCount);
					StdDraw.textRight(1, 1, "Beta Wilsonis 1.1");}
			//Shows and Clears Background For Next Frame
				StdDraw.show(0);
				if (GameRunning) {
					StdDraw.setPenColor(0, 0, 0); 
					StdDraw.picture(0, 0, "Grid.jpg", 2.5, 2.5); }
				if (XObject.size() > 150) {
					XObject.remove(0);
					YObject.remove(0);
					VXObject.remove(0);
					VYObject.remove(0);
					TypeObject.remove(0); }
				if (FPS != 0 && FPS < 30 && VsyncCount != 1 && VSync) {VsyncCount--; FPS = 0;}
				if (!VSync && FPS < 25) FastMode = true;
				if (VSync && VsyncCount == 1 && FPS < 25 && FPS != 0) FastMode = true;
				if (FastMode) rotation = 0;
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
				if (LongTimer == 10000) LongTimer = 0;
				if (KeyTimeout < 32) KeyTimeout++;
				if (VSync && !FastMode) Thread.sleep(VsyncCount); }
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(0, -.95, 1.5, .2);
		StdDraw.setPenColor(StdDraw.BLACK);
	//Save Stats To File
		String EndingSentence = "";
		File outFile = new File("FlatspaceHighScore.txt");
		PrintWriter out = new PrintWriter(outFile);
		if (GameScore > bestScore) {
			out.print(GameScore + " ");
			EndingSentence = EndingSentence + "BEST SCR! "; }
		else {out.print(bestScore + " ");}
		if (GameLevel > bestLevel) {
			out.print(GameLevel + " ");
			EndingSentence = EndingSentence + "BEST LVL! "; }
		else {out.print(bestLevel + " ");}
		if (((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) > bestTime) {
			out.print(((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " ");
			EndingSentence = EndingSentence + "BEST TIME! "; }
		else {out.print(bestTime + " ");}
		out.close();
	//HUD for Results
		StdDraw.text(0, -.9,((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " SEC! Press ESC");
		StdDraw.text(0, -1, "SCR: " + GameScore + " - LVL: " + GameLevel);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.textRight(1, .9, EndingSentence);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.show(0);
		if (OpenCD) {
			try {Runtime.getRuntime().exec("wscript OpenCD.vbs");}
			catch (IOException e) {System.exit(0);} }
		Thread.sleep(1000);
		while (!StdDraw.isKeyPressed(27)) {}
		AllRunning = false; } } } }
