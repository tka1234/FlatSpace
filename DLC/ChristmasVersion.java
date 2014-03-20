//FlatSpace Beta Wilsonis 1.6: Now with Christmas DLC!!!!!!!!! Only $2.99!
import java.awt.*; import java.io.*; import java.net.URI; import java.net.URISyntaxException; import java.util.*;
public class ChristmasVersion {
	public static ArrayList<Double> XObject = new ArrayList<Double>();
	public static ArrayList<Double> YObject = new ArrayList<Double>();
	public static ArrayList<Double> VXObject = new ArrayList<Double>();
	public static ArrayList<Double> VYObject = new ArrayList<Double>();
	public static ArrayList<Double> TypeObject = new ArrayList<Double>();
	public static ArrayList<Double> HealthObject = new ArrayList<Double>();
	public static ArrayList<Double> ItemValues = new ArrayList<Double>();
	public static ArrayList<String> ItemNames = new ArrayList<String>();
	public static boolean OpenCD, VSync = true, ItemsEnabled, Debugging, ShowGrid;
	public static int TextureChosen = 1;
	public static void main(String args[]) throws InterruptedException, IOException, URISyntaxException {
		Random rng = new Random();
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);
		StdDraw.frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 350, (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 400);
		StdDraw.picture(0, 0, "Splash1.jpg", 2.2, 2.2);
		StdDraw.show(0);
		String FileSBox = "FSSquare.png";
		String FileLBox = "FSSquare.png";
		String FileStar = "FSStar.png";
		String FileFrag = "FSFrag.png";
		String FileNPCs = "FSNpc.png";
		String FileCurs = "FSTriangle.png";
		while (true) {
			if (StdDraw.mousePressed()) break;
			if (StdDraw.isKeyPressed(39)) {
				SecretFunction2();
				StdDraw.picture(0, 0, "Splash1.jpg", 2.2, 2.2);
				StdDraw.show(0); }
			if (StdDraw.isKeyPressed(37)) {
				StdDraw.picture(0, 0, "GFatSpace.jpg", 2.2, 2.2);
				FileSBox = "Texture9/FSSquare.png";
				FileLBox = "Texture9/FSSquare.png";
				FileStar = "Texture9/FSStar.png";
				FileFrag = "Texture9/FSFrag.png";
				FileNPCs = "Texture9/FSNpc.png";
				FileCurs = "Texture9/FSTriangle.png";
				TextureChosen = 9;
				StdDraw.show(0); } }
		try {StdDraw.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("ARCADE_R.ttf")).deriveFont((float) 20)); }
		catch (FontFormatException e) {e.printStackTrace(); }
		catch (IOException e) {e.printStackTrace(); }
	//Initialize Game Options
		Scanner OptionsData = new Scanner(new File("GameOptions.dat"));
		OpenCD = OptionsData.nextBoolean();
		ShowGrid = OptionsData.nextBoolean();
		ItemsEnabled = OptionsData.nextBoolean();
		Debugging = OptionsData.nextBoolean();
		OptionsData.close();
	//Dlc Owned?
		Scanner DlcData = new Scanner(new File("DLCPurchase.dat"));
		boolean DlcOwned = DlcData.nextBoolean();
		DlcData.close();
		if (DlcOwned) {
			TextureChosen = 10;
			FileSBox = "Texture10/FSSquare.png";
			FileLBox = "Texture10/FSSquare.png";
			FileStar = "Texture10/FSStar.png";
			FileFrag = "Texture10/FSFrag.png";
			FileNPCs = "Texture10/FSNpc.png";
			FileCurs = "Texture10/FSTriangle.png"; }
	//Game Loop! Here we go!
		while (true) {
		Thread.sleep(50);
		boolean FirstRunning = true, AllRunning = true;
		File inFile;
		Scanner in;
		StdDraw.picture(0, 0, "GMenuDlc.jpg", 2.2, 2.2);
		StdDraw.show(0);
		while (FirstRunning) {
		//Start Game
			if (StdDraw.isKeyPressed(112)) {FirstRunning = false; }
		//View High Scores
			if (StdDraw.isKeyPressed(113)) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				inFile = new File("HiScore.dat");
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
				StdDraw.picture(0, 0, "GMenuDlc.jpg", 2.2, 2.2);
				StdDraw.show(0);
				Thread.sleep(100); }
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
					StdDraw.picture(0, 0, "GMenuDlc.jpg", 2.2, 2.2);
					StdDraw.show(0); }
				else {
					StdDraw.picture(0, 0, "GInventory.jpg", 2.2, 2.2);
					if (TextureChosen == 10) StdDraw.text(0, -1, "Current Texture: Christmas DLC");
					else StdDraw.text(0, -1, "Current Texture: #" + TextureChosen);
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
							FileLBox = "Texture5/LgSquare.png";
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
						if (StdDraw.isKeyPressed(27)) WaitLooping = false; }
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(0, 0, 2);
					StdDraw.picture(0, 0, "GMenuDlc.jpg", 2.2, 2.2);
					StdDraw.show(0);
					Thread.sleep(100); } }
		//How To Play
			if (StdDraw.isKeyPressed(115)) {
				StdDraw.clear();
				StdDraw.picture(0, 0, "GInstructions.jpg", 2.2, 2.2);
				StdDraw.show(0);
				while (!StdDraw.isKeyPressed(27)) {}
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "GMenuDlc.jpg", 2.2, 2.2);
				StdDraw.show(0);
				Thread.sleep(100); }
		//Options
			if (StdDraw.isKeyPressed(116)) {
				OptionsData = new Scanner(new File("GameOptions.dat"));
				OpenCD = OptionsData.nextBoolean();
				ShowGrid = OptionsData.nextBoolean();
				ItemsEnabled = OptionsData.nextBoolean();
				Debugging = OptionsData.nextBoolean();
				OptionsData.close();
				Menu x = new Menu("FlatSpace Options");
				x.pack();
				x.setVisible(true);
				x.setSize(250, 200);
				x.setResizable(false);
				x.InitializeButtons();
				x.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 125, (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 100);
				while (x.isVisible()) { }
				StdDraw.keysDown.remove(116);
				PrintWriter OptionsSave = new PrintWriter(new File("GameOptions.dat"));
				OptionsSave.print(OpenCD + " " + ShowGrid + " " + ItemsEnabled + " " + Debugging);
				OptionsSave.close();
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(0, 0, 2);
				StdDraw.picture(0, 0, "GMenuDlc.jpg", 2.2, 2.2);
				StdDraw.show(0); }
		//DLC
			if (StdDraw.isKeyPressed(117)) {
				StdDraw.picture(0, 0, "GDlcPage.jpg", 2.2, 2.2);
				StdDraw.show(0);
				boolean DlcLoopRunning = true;
				while (DlcLoopRunning) {
					if (StdDraw.isKeyPressed(27)) DlcLoopRunning = false;
					else if (StdDraw.isKeyPressed(10) && !DlcOwned) {
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.filledSquare(0, 0, 2.0);
						StdDraw.setPenColor(StdDraw.WHITE);
						StdDraw.text(0, 0, "DLC Password:");
						StdDraw.show(0);
						if (Desktop.isDesktopSupported()) Desktop.getDesktop().browse(new URI("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=QQT7W6JH7SCSJ"));
					//
						boolean WordLoopRunning = true;
						int keyValuePressed = 0;
						while (WordLoopRunning) {
							if (StdDraw.isKeyPressed(77) && keyValuePressed == 0) keyValuePressed++;
							else if (StdDraw.isKeyPressed(65) && keyValuePressed == 1) keyValuePressed++;
							else if (StdDraw.isKeyPressed(78) && keyValuePressed == 2) keyValuePressed++;
							else if (StdDraw.isKeyPressed(85) && keyValuePressed == 3) keyValuePressed++;
							else if (StdDraw.isKeyPressed(69) && keyValuePressed == 4) keyValuePressed++;
							else if (StdDraw.isKeyPressed(76) && keyValuePressed == 5) {
								keyValuePressed++;
								WordLoopRunning = false; }
							else if (StdDraw.isKeyPressed(27)) WordLoopRunning = false; }
						if (keyValuePressed == 6) {
							DlcOwned = true;
							StdDraw.setPenColor(StdDraw.BLACK);
							StdDraw.filledSquare(0, 0, 2.0);
							StdDraw.setPenColor(StdDraw.WHITE);
							StdDraw.text(0, 0, "You now own the DLC!");
							StdDraw.show(0);
							File outDLC = new File("DLCPurchase.dat");
							PrintWriter DLCOutput = new PrintWriter(outDLC);
							DLCOutput.print(DlcOwned);
							outDLC.setReadOnly();
							DLCOutput.close();
							FileSBox = "Texture10/FSSquare.png";
							FileLBox = "Texture10/FSSquare.png";
							FileStar = "Texture10/FSStar.png";
							FileFrag = "Texture10/FSFrag.png";
							FileNPCs = "Texture10/FSNpc.png";
							FileCurs = "Texture10/FSTriangle.png";
							TextureChosen = 10;
							Thread.sleep(3000); }
						DlcLoopRunning = false; }
					else if (StdDraw.isKeyPressed(10) && DlcOwned) {
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.filledSquare(0, 0, 2.0);
						StdDraw.setPenColor(StdDraw.WHITE);
						StdDraw.text(0, 0, "DLC Already Owned and Equipped");
						StdDraw.show(0);
						Thread.sleep(3000);
						TextureChosen = 10;
						FileSBox = "Texture10/FSSquare.png";
						FileLBox = "Texture10/FSSquare.png";
						FileStar = "Texture10/FSStar.png";
						FileFrag = "Texture10/FSFrag.png";
						FileNPCs = "Texture10/FSNpc.png";
						FileCurs = "Texture10/FSTriangle.png";
						DlcLoopRunning = false; } }
				StdDraw.picture(0, 0, "GMenuDlc.jpg", 2.2, 2.2);
				StdDraw.show(0); } }
		while (AllRunning) {
		//gets high score values
			inFile = new File("HiScore.dat");
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
			HealthObject.clear();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(0, 0, 2);
		//initialize variables
			String ObjectKill = "You quit the game.";
			double XShip = 0, YShip = 0, ShipAngle, XMouse, YMouse, StartTime = System.currentTimeMillis(), RandRead = 0, FpsStart = System.currentTimeMillis();
			double BoxSpeed = .002;
			boolean DebugMenu = false, LoopRunning = true, GameRunning = true, Invincible = false, DisplayLevel = false, PFireRate = false, FastMode = false, PFField = false, ReqInvincible = false;
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
				if (ShipAngle > -360 && ShipAngle < 360 && TextureChosen == 1) StdDraw.picture(XShip, YShip, FileCurs, .06, .06, ShipAngle);
				if (ShipAngle > -360 && ShipAngle < 360 && TextureChosen == 10) StdDraw.picture(XShip, YShip, FileCurs, .12, .25, ShipAngle); //lol the size is Christmas...
				if (ShipAngle > -360 && ShipAngle < 360 && TextureChosen != 1 && TextureChosen != 10) StdDraw.picture(XShip, YShip, FileCurs, .12, .12, ShipAngle);
				if (StdDraw.mousePressed()) {XShip = XShip + (XMouse - XShip) / 50; YShip = YShip + (YMouse - YShip) / 50; }
			//Creation of Bullets
				if (GameLevel%3 == 0) BoxSpeed = ((GameLevel / 3) * .001) + .002;
				if (ShipAngle > -360 && ShipAngle < 360) {
					if (PFireRate && LongTimer%2 == 0) {
						XObject.add(XShip);
						YObject.add(YShip);
						VXObject.add(.04 * Math.cos(Math.toRadians(ShipAngle + 90)));
						VYObject.add(.04 * Math.sin(Math.toRadians(ShipAngle + 90)));
						TypeObject.add(2.0);
						HealthObject.add(1.0);}
					else if (LongTimer%7 == 0) {
						XObject.add(XShip);
						YObject.add(YShip);
						VXObject.add(.04 * Math.cos(Math.toRadians(ShipAngle + 90)));
						VYObject.add(.04 * Math.sin(Math.toRadians(ShipAngle + 90)));
						TypeObject.add(2.0);
						HealthObject.add(1.0);} }
			//Creation of Small & Large Boxes
				if (GameLevel < 30 && LongTimer%(30 - GameLevel) == 0 && LongTimer != 0) {
					RandRead = (rng.nextDouble() * 2) - 1;
					while (RandRead > (XShip - .2) && RandRead < (XShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					XObject.add(RandRead);
					RandRead = (rng.nextDouble() * 1.75) - .75;
					while (RandRead < (YShip - .2) && RandRead > (YShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
					YObject.add(RandRead);
					if (rng.nextDouble() > .5) VXObject.add(BoxSpeed);
					else VXObject.add(-1 * BoxSpeed);
					if (rng.nextDouble() > .5) VYObject.add(BoxSpeed);
					else {VYObject.add(-1 * BoxSpeed);}
					if (rng.nextDouble() < .7) {TypeObject.add(1.0); HealthObject.add(1.0); }
					else {TypeObject.add(4.0); HealthObject.add(4.0); } }
				else if (GameLevel >= 30) {
					for (int i = 0; i < (GameLevel - 29); i++) {
						RandRead = (rng.nextDouble() * 2) - 1;
						while (RandRead > (XShip - .2) && RandRead < (XShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
						XObject.add(RandRead);
						RandRead = (rng.nextDouble() * 1.75) - .75;
						while (RandRead < (YShip - .2) && RandRead > (YShip + .2)) {RandRead = (rng.nextDouble() * 2) - 1; }
						YObject.add(RandRead);
						if (rng.nextDouble() > .5) {VXObject.add(BoxSpeed);}
						else {VXObject.add(-1 * BoxSpeed);}
						if (rng.nextDouble() > .5) {VYObject.add(BoxSpeed);}
						else {VYObject.add(-1 * BoxSpeed);}
						if (rng.nextDouble() < .7) {TypeObject.add(1.0); HealthObject.add(1.0); }
						else {TypeObject.add(4.0); HealthObject.add(4.0); } } }
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
					if (rng.nextDouble() < .7) {TypeObject.add(1.0); HealthObject.add(1.0); }
					else {TypeObject.add(6.0); HealthObject.add(5.0); } }
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
					TypeObject.add(8.0);
					HealthObject.add(10.0); }
			//NPC Bullets
				if (TypeObject.contains(8.0) && LongTimer%12 == 0) {
						XObject.add(XObject.get(TypeObject.indexOf(8.0)));
						YObject.add(YObject.get(TypeObject.indexOf(8.0)));
						VXObject.add(.04 * Math.cos(Math.toRadians(NpcRotation + 90)));
						VYObject.add(.04 * Math.sin(Math.toRadians(NpcRotation + 90)));
						TypeObject.add(9.0);
						HealthObject.add(1.0);}
			//Power Up Spawning
				if (rng.nextInt(500) == 5 && !TypeObject.contains(5.0)) { //PFireRate
					XObject.add((rng.nextDouble() * 2) - 1);
					YObject.add((rng.nextDouble() * 2) - 1);
					VXObject.add(0.0);
					VYObject.add(0.0);
					TypeObject.add(5.0);
					HealthObject.add(1.0); }
				if (rng.nextInt(500) == 5 && !TypeObject.contains(5.1)) { //PFField
					XObject.add((rng.nextDouble() * 2) - 1);
					YObject.add((rng.nextDouble() * 2) - 1);
					VXObject.add(0.0);
					VYObject.add(0.0);
					TypeObject.add(5.1);
					HealthObject.add(1.0); }
				if (rng.nextInt(1000) == 5 && !TypeObject.contains(5.1)) { //PNuke
					XObject.add((rng.nextDouble() * 2) - 1);
					YObject.add((rng.nextDouble() * 2) - 1);
					VXObject.add(0.0);
					VYObject.add(0.0);
					TypeObject.add(5.2);
					HealthObject.add(1.0); }
			//The Wall Pieces
				if (GameLevel >= 15 && GameLevel%5 == 0 && !TypeObject.contains(10.0)) {
				//if (GameLevel == 1 && !TypeObject.contains(10.0)) {
					double wallStartX = -1;
					double wallStartY = 1;
					for (int i = 0; i < 9; i++) {
						XObject.add(wallStartX);
						YObject.add(wallStartY);
						VXObject.add(0.0);
						VYObject.add(-.002);
						TypeObject.add(10.0);
						HealthObject.add(1.0);
						wallStartX = wallStartX + .25; }
					wallStartX = -1;
					wallStartY = 1.3;
					for (int i = 0; i < 9; i++) {
						XObject.add(wallStartX);
						YObject.add(wallStartY);
						VXObject.add(0.0);
						VYObject.add(-.002);
						TypeObject.add(10.0);
						HealthObject.add(1.0);
						wallStartX = wallStartX + .25; } }
				if (PFField) {
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.circle(XShip, YShip, .1); }
			//Object Array Loop
				int objectCount = 0;
				while (objectCount < XObject.size() && objectCount < TypeObject.size() && GameRunning) {
				//Remove Excess Corrupted Objects
					while (XObject.size() > TypeObject.size()) {
						System.out.println("Something Went Wrong");
						XObject.remove(XObject.size() - 1);
						YObject.remove(YObject.size() - 1);
						VXObject.remove(VXObject.size() - 1);
						VYObject.remove(VYObject.size() - 1);
						HealthObject.remove(HealthObject.size() - 1); }
					while (XObject.size() < TypeObject.size()) {
						System.out.println("Something Went Wrong");
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
								HealthObject.remove(DetectCount);
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
							if (!Invincible && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
								ObjectKill = "A small box hit you.";
								GameRunning = false;
								LoopRunning = false; } } }
				//For Bullets:
					else if (TypeObject.get(objectCount) == 2.0) {
						if (XObject.get(objectCount) > 1.1 || XObject.get(objectCount) < -1.1 || YObject.get(objectCount) > 1.1 || YObject.get(objectCount) < -1.1) {
							XObject.remove(objectCount);
							YObject.remove(objectCount);
							VXObject.remove(objectCount);
							VYObject.remove(objectCount);
							TypeObject.remove(objectCount);
							HealthObject.remove(objectCount);
							LoopRunning = false; }
						StdDraw.setPenColor(255, 255, 0);
						if (objectCount != XObject.size() && LoopRunning && TextureChosen == 10) StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "Texture10/FSPresent.png", .06, .06);
						else if (objectCount != XObject.size() && LoopRunning) StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01); }
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
						else if (VYObject.get(objectCount) == 0.00004) StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "FSNukeExplosion.png", .7, .7);
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
								HealthObject.remove(objectCount);
								LoopRunning = false; } } }
				//For Large Square
					else if (TypeObject.get(objectCount) == 4.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .08 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .08) {
								if (HealthObject.get(objectCount) > 1) {
									HealthObject.set(objectCount, HealthObject.get(objectCount) - 1);
									XObject.remove(DetectCount);
									YObject.remove(DetectCount);
									VXObject.remove(DetectCount);
									VYObject.remove(DetectCount);
									TypeObject.remove(DetectCount);
									HealthObject.remove(DetectCount); }
								else if (HealthObject.get(objectCount) == 1) {
									XObject.remove(DetectCount);
									YObject.remove(DetectCount);
									VXObject.remove(DetectCount);
									VYObject.remove(DetectCount);
									TypeObject.remove(DetectCount);
									HealthObject.remove(DetectCount);
									if (XObject.size() != objectCount) {
										TypeObject.set(objectCount, 3.0);
										VXObject.set(objectCount, 0.00001);
										VYObject.set(objectCount, 0.00001);
										GameScore = GameScore + 50; 
										LoopRunning = false; } } }
							else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.setPenColor(StdDraw.WHITE);
							if (FastMode) StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .08);
							else StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileLBox, .16, .16, rotation);
							if (!Invincible && Math.abs(XObject.get(objectCount)-XShip) < .1 && Math.abs(YObject.get(objectCount)-YShip) < .1) {
								ObjectKill = "A large box hit you.";
								GameRunning = false;
								LoopRunning = false; } } }
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
								HealthObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									XObject.remove(objectCount);
									YObject.remove(objectCount);
									VXObject.remove(objectCount);
									VYObject.remove(objectCount);
									TypeObject.remove(objectCount);
									HealthObject.remove(objectCount);
									if (!PFireRate) {
										PFireRate = true;
										PTimer = 0; }
									LoopRunning = false; } }
								else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) { StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "PFireRate.jpg", .08, .08); } }
				//For Invincibility Powerup
					else if (TypeObject.get(objectCount) == 5.1) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && objectCount != XObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								Invincible = true;
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								HealthObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									XObject.remove(objectCount);
									YObject.remove(objectCount);
									VXObject.remove(objectCount);
									VYObject.remove(objectCount);
									TypeObject.remove(objectCount);
									HealthObject.remove(objectCount);
									if (!PFField) {
										PFField = true;
										PTimer = 0; }
									LoopRunning = false; } }
								else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) { StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "PFField.jpg", .08, .08); } }
				//For Nuke Powerup
					else if (TypeObject.get(objectCount) == 5.2) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && objectCount != XObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								LoopRunning = false;
								double TempX = XObject.get(objectCount), TempY = YObject.get(objectCount);
								XObject.clear();
								YObject.clear();
								VXObject.clear();
								VYObject.clear();
								TypeObject.clear();
								HealthObject.clear();
								XObject.add(TempX);
								YObject.add(TempY);
								VXObject.add(0.0);
								VYObject.add(0.00004);
								TypeObject.add(3.0);
								HealthObject.add(1.0); }
							else DetectCount++; }
						if (LoopRunning && XObject.size() != objectCount) StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "PNuke.jpg", .08, .08); }
				//For Star
					else if (TypeObject.get(objectCount) == 6.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								if (HealthObject.get(objectCount) > 1) {
									HealthObject.set(objectCount, HealthObject.get(objectCount) - 1);
									XObject.remove(DetectCount);
									YObject.remove(DetectCount);
									VXObject.remove(DetectCount);
									VYObject.remove(DetectCount);
									TypeObject.remove(DetectCount);
									HealthObject.remove(DetectCount); }
								else if (HealthObject.get(objectCount) == 1) {
									XObject.remove(DetectCount);
									YObject.remove(DetectCount);
									VXObject.remove(DetectCount);
									VYObject.remove(DetectCount);
									TypeObject.remove(DetectCount);
									HealthObject.remove(DetectCount);
									if (XObject.size() != objectCount) {
										for (int i = 1; i != 5; i++) {
											XObject.add(XObject.get(objectCount));
											YObject.add(YObject.get(objectCount));
											VXObject.add(.015 * Math.cos((rng.nextDouble() * 6.28) - 3.14));
											VYObject.add(.015 * Math.sin((rng.nextDouble() * 6.28) - 3.14));
											TypeObject.add(7.0);
											HealthObject.add(1.0); }
										GameScore = GameScore + 75;
										VXObject.set(objectCount, 0.00001);
										VYObject.set(objectCount, 0.00002);
										TypeObject.set(objectCount, 3.0);
										LoopRunning = false; } } }
								else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileStar, .08, .08, rotation);
							if (!Invincible && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
								ObjectKill = "You were hit by a star.";
								GameRunning = false;
								LoopRunning = false; } } }
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
								HealthObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									GameScore = GameScore + 20;
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.00003);
									TypeObject.set(objectCount, 3.0);
									LoopRunning = false; } }
								else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileFrag, .05, .05, rotation);
							if (!Invincible && Math.abs(XObject.get(objectCount)-XShip) < .04 && Math.abs(YObject.get(objectCount)-YShip) < .04) {
								ObjectKill = "You were hit by a star fragment.";
								GameRunning = false;
								LoopRunning = false; } } }	
				//NPC
					else if (TypeObject.get(objectCount) == 8.0) {
						NpcRotation++;
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .04 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .04) {
								if (HealthObject.get(objectCount) > 1) {
									HealthObject.set(objectCount, HealthObject.get(objectCount) - 1);
									XObject.remove(DetectCount);
									YObject.remove(DetectCount);
									VXObject.remove(DetectCount);
									VYObject.remove(DetectCount);
									TypeObject.remove(DetectCount);
									HealthObject.remove(DetectCount); }
								else if (HealthObject.get(objectCount) == 1) {
									XObject.remove(DetectCount);
									YObject.remove(DetectCount);
									VXObject.remove(DetectCount);
									VYObject.remove(DetectCount);
									TypeObject.remove(DetectCount);
									HealthObject.remove(DetectCount);
									if (XObject.size() != objectCount) {
										GameScore = GameScore + 75;
										VXObject.set(objectCount, 0.00001);
										VYObject.set(objectCount, 0.00002);
										TypeObject.set(objectCount, 3.0);
										LoopRunning = false; } } }
							else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							if (TextureChosen == 10) StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileNPCs, .16, .16, NpcRotation);
							else StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), FileNPCs, .08, .08, NpcRotation);
							if (!Invincible && Math.abs(XObject.get(objectCount)-XShip) < .07 && Math.abs(YObject.get(objectCount)-YShip) < .07) {
								ObjectKill = "An enemy ran into you.";
								GameRunning = false;
								LoopRunning = false; } } }
				//Bullets from NPC
					else if (TypeObject.get(objectCount) == 9.0) {
						if (XObject.get(objectCount) > 1.1 || XObject.get(objectCount) < -1.1 || YObject.get(objectCount) > 1.1 || YObject.get(objectCount) < -1.1) {
							XObject.remove(objectCount);
							YObject.remove(objectCount);
							VXObject.remove(objectCount);
							VYObject.remove(objectCount);
							TypeObject.remove(objectCount);
							HealthObject.remove(objectCount);
							LoopRunning = false; }
						StdDraw.setPenColor(StdDraw.GREEN);
						if (objectCount != XObject.size()) {
							StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01);
							if (!Invincible && Math.abs(XObject.get(objectCount)-XShip) < .04 && Math.abs(YObject.get(objectCount)-YShip) < .04) {
								ObjectKill = "An enemy's laser hit you.";
								GameRunning = false;
								LoopRunning = false; } } }
				//The Wall Blocks
					else if (TypeObject.get(objectCount) == 10.0) {
						while (LoopRunning && DetectCount != XObject.size() && objectCount != XObject.size()) {
							if (DetectCount != TypeObject.size() && TypeObject.get(DetectCount) == 2.0 && Math.abs(XObject.get(DetectCount)-XObject.get(objectCount)) < .14 &&
									Math.abs(YObject.get(DetectCount)-YObject.get(objectCount)) < .14 && rng.nextInt(4) == 2) {
								XObject.remove(DetectCount);
								YObject.remove(DetectCount);
								VXObject.remove(DetectCount);
								VYObject.remove(DetectCount);
								TypeObject.remove(DetectCount);
								HealthObject.remove(DetectCount);
								if (XObject.size() != objectCount) {
									TypeObject.set(objectCount, 3.0);
									VXObject.set(objectCount, 0.00001);
									VYObject.set(objectCount, 0.00003);
									GameScore = GameScore + 20; 
									LoopRunning = false; } }
							else {DetectCount++;} }
						if (LoopRunning && XObject.size() != objectCount) {
							StdDraw.setPenColor(StdDraw.MAGENTA);
							StdDraw.square(XObject.get(objectCount), YObject.get(objectCount), .1);
							if (!Invincible && Math.abs(XObject.get(objectCount)-XShip) < .14 && Math.abs(YObject.get(objectCount)-YShip) < .14) {
								ObjectKill = "The Wall hit you.";
								GameRunning = false;
								LoopRunning = false; } } }
					DetectCount = 0;
					if (LoopRunning) objectCount++;
					else LoopRunning = true; }
			//PowerUp Timer
				if (PFField && PTimer < 150) PTimer++;
				if (PFField && PTimer > 148) {
					PFField = false;
					Invincible = ReqInvincible; }
				if (PFireRate && PTimer < 75) PTimer++;
				if (PFireRate && PTimer > 73) PFireRate = false;
			//Score HUD
				StdDraw.picture(0, -1, "Footer2.png", 2.2, .5);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(-.95, -.95, "SCR:" + GameScore);
				StdDraw.text(0, -.95, ((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " SEC");
				StdDraw.textRight(.95, -.95, "LVL " + GameLevel);
				StdDraw.text(0, -1.05, "HISCORE:" + bestScore);
			//Debug Menu Hotkey = F3
				if (StdDraw.isKeyPressed(114) && KeyTimeout > 30 && Debugging) {
					if (DebugMenu) {DebugMenu = false; KeyTimeout = 0;}
					else {DebugMenu = true; KeyTimeout = 0;} }
			//Invincible Hotkey = F2
				if (StdDraw.isKeyPressed(113) && KeyTimeout > 30 && Debugging) {
					if (Invincible) {Invincible = false; KeyTimeout = 0;}
					else {Invincible = true; KeyTimeout = 0;}
					ReqInvincible = Invincible;	}
				StdDraw.setPenColor(StdDraw.WHITE);
				if (Invincible) StdDraw.textRight(1, .9, "Invincible");
				if (StdDraw.isKeyPressed(27) && KeyTimeout > 30) {KeyTimeout = 0; GameRunning = false;}
			//Debug Menu HUD
				if (DebugMenu) {
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.textLeft(-1, 1, "DEBUG MENU");
					StdDraw.textLeft(-1, .9, "ENT: " + XObject.size());
					StdDraw.textLeft(-1, .8, "FPS: " + FPS);
					StdDraw.textLeft(-1, .7, "VSY: " + VsyncCount);
					StdDraw.textRight(1, 1, "Beta Wilsonis 1.6");}
				else {
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.text(0, 1, FPS + " FPS"); }
			//Shows and Clears Background For Next Frame
				StdDraw.show(0);
				if (GameRunning) {
					StdDraw.setPenColor(0, 0, 0);
					if (TextureChosen == 10) StdDraw.picture(0, 0, "Grid.jpg", 2.5, 2.5);
					else {
						if (ShowGrid) StdDraw.picture(0, 0, "Grid.jpg", 2.5, 2.5);
						else StdDraw.filledSquare(0, 0, 2); } }
				if (XObject.size() > 150) {
					XObject.remove(0);
					YObject.remove(0);
					VXObject.remove(0);
					VYObject.remove(0);
					TypeObject.remove(0);
					HealthObject.remove(0); }
				if (FPS != 0 && FPS < 30 && VsyncCount != 1 && VSync) {VsyncCount--; FPS = 0;}
				if (!VSync && FPS < 25) FastMode = true;
				if (VSync && VsyncCount == 1 && FPS < 15 && FPS != 0) FastMode = true;
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
	//Save Stats To File
		String EndingSentence = "";
		new File("HiScore.dat").delete();
		File outFile = new File("HiScore.dat");
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
			out.print(((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10));
			EndingSentence = EndingSentence + "BEST TIME! "; }
		else {out.print(bestTime);}
		outFile.setReadOnly();
		out.close();
	//HUD for Results
		StdDraw.picture(0, 0, "GEndScreen.jpg", 2.2, 2.2);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0, .65, ObjectKill);
		StdDraw.text(0, -.7, GameScore + " PTS, LVL " + GameLevel + ", " + ((double) Math.round(((System.currentTimeMillis() - StartTime) / 1000) * 10) / 10) + " SEC");
		if (EndingSentence.equals("")) StdDraw.text(0, -.8, "BETTER LUCK NEXT TIME!");
		StdDraw.text(0, -.8, " " + EndingSentence);
		StdDraw.show(0);
		Thread.sleep(500);
		if (OpenCD) {
			try {Runtime.getRuntime().exec("wscript OpenCD.vbs");}
			catch (IOException e) {System.exit(0);}
			int CDMessageCounter = 0;
			boolean penColorSwitch = true;
			StdDraw.setPenColor(StdDraw.MAGENTA);
			while (!StdDraw.isKeyPressed(27)) {
				CDMessageCounter++;
				if (CDMessageCounter%100 == 0 && penColorSwitch) {
					penColorSwitch = false;
					StdDraw.setPenColor(StdDraw.GREEN); }
				else if (CDMessageCounter%100 == 0 && !penColorSwitch) {
					penColorSwitch = true;
					StdDraw.setPenColor(StdDraw.MAGENTA); }
				StdDraw.text(0, -.95, "INSERT 25 CENTS TO CONTINUE");
				StdDraw.show(0); } }
		else {
			int CDMessageCounter = 0;
			boolean penColorSwitch = true;
			StdDraw.setPenColor(StdDraw.MAGENTA);
			while (!StdDraw.isKeyPressed(27)) {
				CDMessageCounter++;
				if (CDMessageCounter%100 == 0 && penColorSwitch) {
					penColorSwitch = false;
					StdDraw.setPenColor(StdDraw.GREEN); }
				else if (CDMessageCounter%100 == 0 && !penColorSwitch) {
					penColorSwitch = true;
					StdDraw.setPenColor(StdDraw.MAGENTA); }
				StdDraw.text(0, -.95, "PRESS ESC TO CONTINUE");
				StdDraw.show(0); } }
		AllRunning = false; } } }
	public static void SecretFunction2() throws InterruptedException {
		ArrayList<Double> XObject = new ArrayList<Double>();
		ArrayList<Double> YObject = new ArrayList<Double>();
		ArrayList<Double> VXObject = new ArrayList<Double>();
		ArrayList<Double> VYObject = new ArrayList<Double>();
		ArrayList<Double> DegObject = new ArrayList<Double>();
		ArrayList<Double> TypeObject = new ArrayList<Double>();
		int LongCount = 0, N = 1, score = 0, SaleCounter = 0;
		double[] rx = new double[N];
		double[] ry = new double[N];
		double[] vx = new double[N];
		double[] vy = new double[N];
		double dt = 0.5, mass = 1.0, drag = 1.5, attractionStrength = 0.01, XShip = (Math.random() * 2) - 1, YShip = (Math.random() * 2) - 1;
		double XMouse, YMouse, ShipAngle = 0;
		boolean GameRunning = true, SteamSale = false;
		while (GameRunning) {
			StdDraw.picture(0, 0, "Grid.jpg", 2.5, 2.5);
			XMouse = StdDraw.mouseX();
			YMouse = StdDraw.mouseY();
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.circle(XMouse, YMouse, .03);
			StdDraw.setPenColor(StdDraw.YELLOW);
			ShipAngle = Math.toDegrees(Math.atan((YMouse - YShip)/(XMouse - XShip))) - 90;
			if (StdDraw.mouseX() < XShip) ShipAngle = ShipAngle - 180;
			if (ShipAngle > -360 && ShipAngle < 360) StdDraw.picture(XShip, YShip, "FSTriangle.png", .06, .06, ShipAngle);
			if (StdDraw.mousePressed()) {
				XShip = XShip + (XMouse - XShip) / 50;
				YShip = YShip + (YMouse - YShip) / 50; }
			if (Math.floor(Math.random() * 100) == 80 || StdDraw.isKeyPressed(27)) {
				SteamSale = true;
				SaleCounter = 0; }
			if (LongCount%7 == 0) {
				XObject.add(XShip);
				YObject.add(YShip);
				VXObject.add(.04 * Math.cos(Math.toRadians(ShipAngle + 90)));
				VYObject.add(.04 * Math.sin(Math.toRadians(ShipAngle + 90)));
				DegObject.add(0.0);
				TypeObject.add(1.0); }
			if (LongCount%30 == 0) {
				XObject.add(rx[0]);
				YObject.add(ry[0]);
				double GabenAngle = Math.toDegrees(Math.atan((YShip - ry[0])/(XShip - rx[0]))) - 90;
				if (XShip < rx[0]) GabenAngle = GabenAngle - 180;
				VXObject.add(.04 * Math.cos(Math.toRadians(GabenAngle + 90)));
				VYObject.add(.04 * Math.sin(Math.toRadians(GabenAngle + 90)));
				DegObject.add(GabenAngle);
				TypeObject.add(2.0); }
			if (SteamSale && LongCount%1 == 0) {
				XObject.add(rx[0]);
				YObject.add(ry[0]);
				double GabenAngle = Math.random() * 360;
				VXObject.add(.04 * Math.cos(Math.toRadians(GabenAngle + 90)));
				VYObject.add(.04 * Math.sin(Math.toRadians(GabenAngle + 90)));
				DegObject.add(GabenAngle);
				TypeObject.add(2.0); }
			int objectCount = 0, detectCount = 0;
			while (objectCount < XObject.size()) {
				boolean foundSomething = false;
				detectCount = 0;
				XObject.set(objectCount, XObject.get(objectCount) + VXObject.get(objectCount));
				YObject.set(objectCount, YObject.get(objectCount) + VYObject.get(objectCount));
				if (TypeObject.get(objectCount) == 1.0) StdDraw.filledCircle(XObject.get(objectCount), YObject.get(objectCount), .01);
				if (TypeObject.get(objectCount) == 2.0) {
					if (Math.abs(XObject.get(objectCount) - XShip) < .04 && Math.abs(YObject.get(objectCount) - YShip) < .04) GameRunning = false;
					while (detectCount < XObject.size()) {
						if (TypeObject.get(detectCount) == 1.0) {
							if (objectCount < XObject.size() && detectCount < XObject.size() && Math.abs(XObject.get(objectCount) - XObject.get(detectCount)) < .04 && Math.abs(YObject.get(objectCount) - YObject.get(detectCount)) < .04) {
								XObject.remove(objectCount); 
								YObject.remove(objectCount); 
								VXObject.remove(objectCount); 
								VYObject.remove(objectCount); 
								DegObject.remove(objectCount); 
								TypeObject.remove(objectCount); 
								if (XObject.size() <= 1) {
									XObject.remove(detectCount - 1);
									YObject.remove(detectCount - 1);
									VXObject.remove(detectCount - 1);
									VYObject.remove(detectCount - 1);
									DegObject.remove(detectCount - 1);
									TypeObject.remove(detectCount - 1); }
								foundSomething = true;
								score++; } }
						detectCount++; }
					if (!foundSomething) StdDraw.picture(XObject.get(objectCount), YObject.get(objectCount), "Texture3/FSSquare.png", .2, .2, DegObject.get(objectCount) - 90); }
				if (objectCount < XObject.size()) {
					if (XObject.get(objectCount) > 1.1 || XObject.get(objectCount) < -1.1 || YObject.get(objectCount) > 1.1 || YObject.get(objectCount) < -1.1) {
						XObject.remove(objectCount);
						YObject.remove(objectCount);
						VXObject.remove(objectCount);
						VYObject.remove(objectCount);
						DegObject.remove(objectCount);
						TypeObject.remove(objectCount);
						foundSomething = true; } }
				if (!foundSomething) objectCount++; }
			StdDraw.text(0, 1, score + " Games Purchased");
			if (SteamSale && SaleCounter < 50) {
				SaleCounter++;
				StdDraw.text(0, .9, "STEAM SALE!"); }
			else if (SteamSale) SteamSale = false;
			if (Math.abs(rx[0] - XShip) < .25 && Math.abs(ry[0] - YShip) < .25) GameRunning = false;
			double[] fx = new double[N];
			double[] fy = new double[N];
			double dx = XShip - rx[0];
			double dy = YShip - ry[0];
			fx[0] += attractionStrength * dx;
			fy[0] += attractionStrength * dy;
			fx[0] += -drag * vx[0];
			fy[0] += -drag * vy[0];
			vx[0] += fx[0] * dt / mass;
			vy[0] += fy[0] * dt / mass;
			rx[0] += vx[0] * dt;
			ry[0] += vy[0] * dt;
			StdDraw.picture(rx[0], ry[0], "GabeNewell.png", .5, .5);
			StdDraw.show(0);
			Thread.sleep(15);
			LongCount++; }
		double GabenSize = .5;
		Thread.sleep(1000);
		while (GabenSize < 5) {
			GabenSize = GabenSize + .05;
			StdDraw.picture(rx[0], ry[0], "GabeNewell.png", GabenSize, GabenSize);
			StdDraw.show(0); } } }
