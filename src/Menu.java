//Menu Class for FlatSpace Beta Wilsonis 1.5 
import javax.swing.*; import javax.swing.border.Border; import java.awt.*; import java.awt.event.*; import java.io.*;
public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	public Menu(String title) {
		super(title);
		JPanel controlPanel = new JPanel(new GridLayout(5,1));
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		GridButton = new JButton("");
		GridButton.addActionListener(new GridButtonListener());
		controlPanel.add(GridButton);
		DiskDriveButton = new JButton("");
		DiskDriveButton.addActionListener(new DiskDriveButtonListener());
		controlPanel.add(DiskDriveButton);
		TexturesButton = new JButton("");
		TexturesButton.addActionListener(new TextureButtonListener());
		controlPanel.add(TexturesButton);
		DebugButton = new JButton("Debugging Enabled");
		DebugButton.setEnabled(false);
	//DebugButton.addActionListener(new DebugButtonListener());
		controlPanel.add(DebugButton);
		ResetHSButton = new JButton("Reset High Scores");
		ResetHSButton.addActionListener(new ResetHSButtonListener());
		controlPanel.add(ResetHSButton);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); }
	private JButton GridButton, DiskDriveButton, TexturesButton, DebugButton, ResetHSButton;
	public void InitializeButtons() {
		if (Flatspace.ShowGrid) GridButton.setText("Grid Enabled (Recommended)");
		else GridButton.setText("Grid Disabled");
		if (Flatspace.OpenCD) DiskDriveButton.setText("CD Drive Opens");
		else DiskDriveButton.setText("CD Drive Doesn't Open");
		if (Flatspace.ItemsEnabled) TexturesButton.setText("Textures Enabled");
		else TexturesButton.setText("Textures Disabled"); }
	private class GridButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (Flatspace.ShowGrid) {
				Flatspace.ShowGrid = false;
				GridButton.setText("Grid Disabled");}
			else {
				Flatspace.ShowGrid = true;
				GridButton.setText("Grid Enabled (Recommended)"); } } }
	private class DiskDriveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (Flatspace.OpenCD) {
				Flatspace.OpenCD = false;
				DiskDriveButton.setText("CD Drive Doesn't Open");}
			else {
				Flatspace.OpenCD = true;
				DiskDriveButton.setText("CD Drive Opens"); } } }
	private class TextureButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (Flatspace.ItemsEnabled) {
				Flatspace.ItemsEnabled = false;
				TexturesButton.setText("Textures Disabled");}
			else {
				Flatspace.ItemsEnabled = true;
				TexturesButton.setText("Textures Enabled"); } } }
	private class ResetHSButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				new File("HiScore.dat").delete();
				PrintWriter HiScores = new PrintWriter(new File("HiScore.dat"));
				HiScores.print("0.0 0.0 0.0");
				HiScores.close();
				new File("HiScore.dat").setReadOnly();}
			catch (FileNotFoundException e) {e.printStackTrace(); } } }
	public static void main(String args[]) {System.err.print("This program is a dependency of FlatSpace. It cannot be run on its own."); } }
