import java.awt.*; import java.awt.event.*; import java.awt.geom.*; import java.awt.image.*; import java.io.*; import java.net.*; import java.util.LinkedList; import java.util.TreeSet; import javax.imageio.ImageIO; import javax.swing.*;
public final class StdDraw implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	public static final Color BLACK = Color.BLACK;
	public static final Color BLUE = Color.BLUE;
	public static final Color CYAN = Color.CYAN;
	public static final Color DARK_GRAY = Color.DARK_GRAY;
	public static final Color GRAY = Color.GRAY;
	public static final Color GREEN = Color.GREEN;
	public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
	public static final Color MAGENTA = Color.MAGENTA;
	public static final Color ORANGE = Color.ORANGE;
	public static final Color PINK = Color.PINK;
	public static final Color RED = Color.RED;
	public static final Color WHITE = Color.WHITE;
	public static final Color YELLOW = Color.YELLOW;
	public static final Color BOOK_BLUE = new Color(9, 90, 166);
	public static final Color BOOK_LIGHT_BLUE = new Color(103, 198, 243);
	private static final Color DEFAULT_PEN_COLOR = BLACK;
	private static final Color DEFAULT_CLEAR_COLOR = WHITE;
	private static Color penColor;
	private static final int DEFAULT_SIZE = 700;
	private static int width = 700;
	private static int height = 700;
	private static final double DEFAULT_PEN_RADIUS = 0.002;
	private static double penRadius;
	private static boolean defer = false;
	private static final double BORDER = 0.05;
	private static final double DEFAULT_XMIN = 0.0;
	private static final double DEFAULT_XMAX = 1.0;
	private static final double DEFAULT_YMIN = 0.0;
	private static final double DEFAULT_YMAX = 1.0;
	private static double xmin, ymin, xmax, ymax;
	private static Object mouseLock = new Object();
	private static Object keyLock = new Object();
	private static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 16);
	private static Font font;
	private static BufferedImage offscreenImage, onscreenImage;
	private static Graphics2D offscreen, onscreen;
	private static StdDraw std = new StdDraw();
	static JFrame frame;
	private static boolean mousePressed = false;
	private static double mouseX = 0;
	private static double mouseY = 0;
	private static LinkedList<Character> keysTyped = new LinkedList<Character>();
	static TreeSet<Integer> keysDown = new TreeSet<Integer>();
	private StdDraw() { }
	static {init();}
	public static void setCanvasSize() {setCanvasSize(DEFAULT_SIZE, DEFAULT_SIZE);}
	public static void setCanvasSize(int w, int h) {
		if (w < 1 || h < 1) throw new IllegalArgumentException("width and height must be positive");
		width = w;
		height = h;
		init(); }
	private static void init() {
		if (frame != null) frame.setVisible(false);
		frame = new JFrame();
		offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		onscreenImage  = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		offscreen = offscreenImage.createGraphics();
		onscreen  = onscreenImage.createGraphics();
		setXscale();
		setYscale();
		offscreen.setColor(DEFAULT_CLEAR_COLOR);
		offscreen.fillRect(0, 0, width, height);
		setPenColor();
		setPenRadius();
		setFont();
		clear();
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		offscreen.addRenderingHints(hints);
		ImageIcon icon = new ImageIcon(onscreenImage);
		JLabel draw = new JLabel(icon);
		draw.addMouseListener(std);
		draw.addMouseMotionListener(std);
		frame.setContentPane(draw);
		frame.addKeyListener(std);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("FlatSpace");
		frame.pack();
		frame.requestFocusInWindow();
		frame.setVisible(true); }
/* private static JMenuBar createMenuBar() {
JMenuBar menuBar = new JMenuBar();
JMenu menu = new JMenu("Screenshot");
menuBar.add(menu);
JMenuItem menuItem1 = new JMenuItem(" Save Screenshot   ");
menuItem1.addActionListener(std);
menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
menu.add(menuItem1);
return menuBar; }*/
//coords system
	public static void setXscale() { setXscale(DEFAULT_XMIN, DEFAULT_XMAX); }
	public static void setYscale() { setYscale(DEFAULT_YMIN, DEFAULT_YMAX); }
    /**
     * Set the x-scale (a 10% border is added to the values)
     * @param min the minimum value of the x-scale
     * @param max the maximum value of the x-scale
     */
    public static void setXscale(double min, double max) {
        double size = max - min;
        synchronized (mouseLock) {
            xmin = min - BORDER * size;
            xmax = max + BORDER * size;
        }
    }
    /**
     * Set the y-scale (a 10% border is added to the values).
     * @param min the minimum value of the y-scale
     * @param max the maximum value of the y-scale
     */
    public static void setYscale(double min, double max) {
        double size = max - min;
        synchronized (mouseLock) {
            ymin = min - BORDER * size;
            ymax = max + BORDER * size;
        }
    }
    /**
     * Set the x-scale and y-scale (a 10% border is added to the values)
     * @param min the minimum value of the x- and y-scales
     * @param max the maximum value of the x- and y-scales
     */
    public static void setScale(double min, double max) {
        double size = max - min;
        synchronized (mouseLock) {
            xmin = min - BORDER * size;
            xmax = max + BORDER * size;
            ymin = min - BORDER * size;
            ymax = max + BORDER * size;
        }
    }
    // helper functions that scale from user coordinates to screen coordinates and back
    private static double  scaleX(double x) { return width  * (x - xmin) / (xmax - xmin); }
    private static double  scaleY(double y) { return height * (ymax - y) / (ymax - ymin); }
    private static double factorX(double w) { return w * width  / Math.abs(xmax - xmin);  }
    private static double factorY(double h) { return h * height / Math.abs(ymax - ymin);  }
    private static double   userX(double x) { return xmin + x * (xmax - xmin) / width;    }
    private static double   userY(double y) { return ymax - y * (ymax - ymin) / height;   }
    /**
     * Clear the screen to the default color (white).
     */
    public static void clear() { clear(DEFAULT_CLEAR_COLOR); }
    /**
     * Clear the screen to the given color.
     * @param color the Color to make the background
     */
    public static void clear(Color color) {
        offscreen.setColor(color);
        offscreen.fillRect(0, 0, width, height);
        offscreen.setColor(penColor);
        draw();
    }
    /**
     * Get the current pen radius.
     */
    public static double getPenRadius() { return penRadius; }
    /**
     * Set the pen size to the default (.002).
     */
    public static void setPenRadius() { setPenRadius(DEFAULT_PEN_RADIUS); }
    /**
     * Set the radius of the pen to the given size.
     * @param r the radius of the pen
     * @throws IllegalArgumentException if r is negative
     */
    public static void setPenRadius(double r) {
        if (r < 0) throw new IllegalArgumentException("pen radius must be nonnegative");
        penRadius = r;
        float scaledPenRadius = (float) (r * DEFAULT_SIZE);
        BasicStroke stroke = new BasicStroke(scaledPenRadius, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        // BasicStroke stroke = new BasicStroke(scaledPenRadius);
        offscreen.setStroke(stroke);
    }
    /**
     * Get the current pen color.
     */
    public static Color getPenColor() { return penColor; }
    /**
     * Set the pen color to the default color (black).
     */
    public static void setPenColor() { setPenColor(DEFAULT_PEN_COLOR); }
    /**
     * Set the pen color to the given color. The available pen colors are
     * BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY, MAGENTA,
     * ORANGE, PINK, RED, WHITE, and YELLOW.
     * @param color the Color to make the pen
     */
    public static void setPenColor(Color color) {
        penColor = color;
        offscreen.setColor(penColor);
    }
    /**
     * Set the pen color to the given RGB color.
     * @param red the amount of red (between 0 and 255)
     * @param green the amount of green (between 0 and 255)
     * @param blue the amount of blue (between 0 and 255)
     * @throws IllegalArgumentException if the amount of red, green, or blue are outside prescribed range
     */
    public static void setPenColor(int red, int green, int blue) {
        if (red   < 0 || red   >= 256) throw new IllegalArgumentException("amount of red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("amount of red must be between 0 and 255");
        if (blue  < 0 || blue  >= 256) throw new IllegalArgumentException("amount of red must be between 0 and 255");
        setPenColor(new Color(red, green, blue));
    }
    /**
     * Get the current font.
     */
    public static Font getFont() { return font; }
    /**
     * Set the font to the default font (sans serif, 16 point).
     */
    public static void setFont() { setFont(DEFAULT_FONT); }
    /**
     * Set the font to the given value.
     * @param f the font to make text
     */
    public static void setFont(Font f) { font = f; }
   /*************************************************************************
    *  Drawing geometric shapes.
    *************************************************************************/
    /**
     * Draw a line from (x0, y0) to (x1, y1).
     * @param x0 the x-coordinate of the starting point
     * @param y0 the y-coordinate of the starting point
     * @param x1 the x-coordinate of the destination point
     * @param y1 the y-coordinate of the destination point
     */
    public static void line(double x0, double y0, double x1, double y1) {
        offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
        draw();
    }
    /**
     * Draw one pixel at (x, y).
     * @param x the x-coordinate of the pixel
     * @param y the y-coordinate of the pixel
     */
    private static void pixel(double x, double y) {
        offscreen.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
    }
    /**
     * Draw a point at (x, y).
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public static void point(double x, double y) {
        double xs = scaleX(x);
        double ys = scaleY(y);
        double r = penRadius;
        float scaledPenRadius = (float) (r * DEFAULT_SIZE);
        if (scaledPenRadius <= 1) pixel(x, y);
        else offscreen.fill(new Ellipse2D.Double(xs - scaledPenRadius/2, ys - scaledPenRadius/2,
                                                 scaledPenRadius, scaledPenRadius));
        draw();
    }
    /**
     * Draw a circle of radius r, centered on (x, y).
     * @param x the x-coordinate of the center of the circle
     * @param y the y-coordinate of the center of the circle
     * @param r the radius of the circle
     * @throws IllegalArgumentException if the radius of the circle is negative
     */
    public static void circle(double x, double y, double r) {
        if (r < 0) throw new IllegalArgumentException("circle radius must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*r);
        double hs = factorY(2*r);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.draw(new Ellipse2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw filled circle of radius r, centered on (x, y).
     * @param x the x-coordinate of the center of the circle
     * @param y the y-coordinate of the center of the circle
     * @param r the radius of the circle
     * @throws IllegalArgumentException if the radius of the circle is negative
     */
    public static void filledCircle(double x, double y, double r) {
        if (r < 0) throw new IllegalArgumentException("circle radius must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*r);
        double hs = factorY(2*r);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.fill(new Ellipse2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw an ellipse with given semimajor and semiminor axes, centered on (x, y).
     * @param x the x-coordinate of the center of the ellipse
     * @param y the y-coordinate of the center of the ellipse
     * @param semiMajorAxis is the semimajor axis of the ellipse
     * @param semiMinorAxis is the semiminor axis of the ellipse
     * @throws IllegalArgumentException if either of the axes are negative
     */
    public static void ellipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis < 0) throw new IllegalArgumentException("ellipse semimajor axis must be nonnegative");
        if (semiMinorAxis < 0) throw new IllegalArgumentException("ellipse semiminor axis must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*semiMajorAxis);
        double hs = factorY(2*semiMinorAxis);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.draw(new Ellipse2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw an ellipse with given semimajor and semiminor axes, centered on (x, y).
     * @param x the x-coordinate of the center of the ellipse
     * @param y the y-coordinate of the center of the ellipse
     * @param semiMajorAxis is the semimajor axis of the ellipse
     * @param semiMinorAxis is the semiminor axis of the ellipse
     * @throws IllegalArgumentException if either of the axes are negative
     */
    public static void filledEllipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis < 0) throw new IllegalArgumentException("ellipse semimajor axis must be nonnegative");
        if (semiMinorAxis < 0) throw new IllegalArgumentException("ellipse semiminor axis must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*semiMajorAxis);
        double hs = factorY(2*semiMinorAxis);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.fill(new Ellipse2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw an arc of radius r, centered on (x, y), from angle1 to angle2 (in degrees).
     * @param x the x-coordinate of the center of the circle
     * @param y the y-coordinate of the center of the circle
     * @param r the radius of the circle
     * @param angle1 the starting angle. 0 would mean an arc beginning at 3 o'clock.
     * @param angle2 the angle at the end of the arc. For example, if
     *        you want a 90 degree arc, then angle2 should be angle1 + 90.
     * @throws IllegalArgumentException if the radius of the circle is negative
     */
    public static void arc(double x, double y, double r, double angle1, double angle2) {
        if (r < 0) throw new IllegalArgumentException("arc radius must be nonnegative");
        while (angle2 < angle1) angle2 += 360;
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*r);
        double hs = factorY(2*r);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.draw(new Arc2D.Double(xs - ws/2, ys - hs/2, ws, hs, angle1, angle2 - angle1, Arc2D.OPEN));
        draw();
    }
    /**
     * Draw a square of side length 2r, centered on (x, y).
     * @param x the x-coordinate of the center of the square
     * @param y the y-coordinate of the center of the square
     * @param r radius is half the length of any side of the square
     * @throws IllegalArgumentException if r is negative
     */
    public static void square(double x, double y, double r) {
        if (r < 0) throw new IllegalArgumentException("square side length must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*r);
        double hs = factorY(2*r);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.draw(new Rectangle2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw a filled square of side length 2r, centered on (x, y).
     * @param x the x-coordinate of the center of the square
     * @param y the y-coordinate of the center of the square
     * @param r radius is half the length of any side of the square
     * @throws IllegalArgumentException if r is negative
     */
    public static void filledSquare(double x, double y, double r) {
        if (r < 0) throw new IllegalArgumentException("square side length must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*r);
        double hs = factorY(2*r);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.fill(new Rectangle2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw a rectangle of given half width and half height, centered on (x, y).
     * @param x the x-coordinate of the center of the rectangle
     * @param y the y-coordinate of the center of the rectangle
     * @param halfWidth is half the width of the rectangle
     * @param halfHeight is half the height of the rectangle
     * @throws IllegalArgumentException if halfWidth or halfHeight is negative
     */
    public static void rectangle(double x, double y, double halfWidth, double halfHeight) {
        if (halfWidth  < 0) throw new IllegalArgumentException("half width must be nonnegative");
        if (halfHeight < 0) throw new IllegalArgumentException("half height must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*halfWidth);
        double hs = factorY(2*halfHeight);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.draw(new Rectangle2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw a filled rectangle of given half width and half height, centered on (x, y).
     * @param x the x-coordinate of the center of the rectangle
     * @param y the y-coordinate of the center of the rectangle
     * @param halfWidth is half the width of the rectangle
     * @param halfHeight is half the height of the rectangle
     * @throws IllegalArgumentException if halfWidth or halfHeight is negative
     */
    public static void filledRectangle(double x, double y, double halfWidth, double halfHeight) {
        if (halfWidth  < 0) throw new IllegalArgumentException("half width must be nonnegative");
        if (halfHeight < 0) throw new IllegalArgumentException("half height must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*halfWidth);
        double hs = factorY(2*halfHeight);
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else offscreen.fill(new Rectangle2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        draw();
    }
    /**
     * Draw a polygon with the given (x[i], y[i]) coordinates.
     * @param x an array of all the x-coordindates of the polygon
     * @param y an array of all the y-coordindates of the polygon
     */
    public static void polygon(double[] x, double[] y) {
        int N = x.length;
        GeneralPath path = new GeneralPath();
        path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
        for (int i = 0; i < N; i++)
            path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
        path.closePath();
        offscreen.draw(path);
        draw();
    }
    /**
     * Draw a filled polygon with the given (x[i], y[i]) coordinates.
     * @param x an array of all the x-coordindates of the polygon
     * @param y an array of all the y-coordindates of the polygon
     */
    public static void filledPolygon(double[] x, double[] y) {
        int N = x.length;
        GeneralPath path = new GeneralPath();
        path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
        for (int i = 0; i < N; i++)
            path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
        path.closePath();
        offscreen.fill(path);
        draw();
    }
   /*************************************************************************
    *  Drawing images.
    *************************************************************************/
    // get an image from the given filename
    private static Image getImage(String filename) {
        // to read from file
        ImageIcon icon = new ImageIcon(filename);
        // try to read from URL
        if ((icon == null) || (icon.getImageLoadStatus() != MediaTracker.COMPLETE)) {
            try {
                URL url = new URL(filename);
                icon = new ImageIcon(url);
            } catch (Exception e) { /* not a url */ }
        }
        // in case file is inside a .jar
        if ((icon == null) || (icon.getImageLoadStatus() != MediaTracker.COMPLETE)) {
            URL url = StdDraw.class.getResource(filename);
            if (url == null) throw new IllegalArgumentException("image " + filename + " not found");
            icon = new ImageIcon(url);
        }
        return icon.getImage();
    }
    /**
     * Draw picture (gif, jpg, or png) centered on (x, y).
     * @param x the center x-coordinate of the image
     * @param y the center y-coordinate of the image
     * @param s the name of the image/picture, e.g., "ball.gif"
     * @throws IllegalArgumentException if the image is corrupt
     */
    public static void picture(double x, double y, String s) {
        Image image = getImage(s);
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = image.getWidth(null);
        int hs = image.getHeight(null);
        if (ws < 0 || hs < 0) throw new IllegalArgumentException("image " + s + " is corrupt");
        offscreen.drawImage(image, (int) Math.round(xs - ws/2.0), (int) Math.round(ys - hs/2.0), null);
        draw();
    }
    /**
     * Draw picture (gif, jpg, or png) centered on (x, y),
     * rotated given number of degrees
     * @param x the center x-coordinate of the image
     * @param y the center y-coordinate of the image
     * @param s the name of the image/picture, e.g., "ball.gif"
     * @param degrees is the number of degrees to rotate counterclockwise
     * @throws IllegalArgumentException if the image is corrupt
     */
    public static void picture(double x, double y, String s, double degrees) {
        Image image = getImage(s);
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = image.getWidth(null);
        int hs = image.getHeight(null);
        if (ws < 0 || hs < 0) throw new IllegalArgumentException("image " + s + " is corrupt");
        offscreen.rotate(Math.toRadians(-degrees), xs, ys);
        offscreen.drawImage(image, (int) Math.round(xs - ws/2.0), (int) Math.round(ys - hs/2.0), null);
        offscreen.rotate(Math.toRadians(+degrees), xs, ys);
        draw();
    }
    /**
     * Draw picture (gif, jpg, or png) centered on (x, y), rescaled to w-by-h.
     * @param x the center x coordinate of the image
     * @param y the center y coordinate of the image
     * @param s the name of the image/picture, e.g., "ball.gif"
     * @param w the width of the image
     * @param h the height of the image
     * @throws IllegalArgumentException if the width height are negative
     * @throws IllegalArgumentException if the image is corrupt
     */
    public static void picture(double x, double y, String s, double w, double h) {
        Image image = getImage(s);
        double xs = scaleX(x);
        double ys = scaleY(y);
        if (w < 0) throw new IllegalArgumentException("width is negative: " + w);
        if (h < 0) throw new IllegalArgumentException("height is negative: " + h);
        double ws = factorX(w);
        double hs = factorY(h);
        if (ws < 0 || hs < 0) throw new IllegalArgumentException("image " + s + " is corrupt");
        if (ws <= 1 && hs <= 1) pixel(x, y);
        else {
            offscreen.drawImage(image, (int) Math.round(xs - ws/2.0),
                                       (int) Math.round(ys - hs/2.0),
                                       (int) Math.round(ws),
                                       (int) Math.round(hs), null);
        }
        draw();
    }
    /**
     * Draw picture (gif, jpg, or png) centered on (x, y), rotated
     * given number of degrees, rescaled to w-by-h.
     * @param x the center x-coordinate of the image
     * @param y the center y-coordinate of the image
     * @param s the name of the image/picture, e.g., "ball.gif"
     * @param w the width of the image
     * @param h the height of the image
     * @param degrees is the number of degrees to rotate counterclockwise
     * @throws IllegalArgumentException if the image is corrupt
     */
    public static void picture(double x, double y, String s, double w, double h, double degrees) {
        Image image = getImage(s);
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(w);
        double hs = factorY(h);
        if (ws < 0 || hs < 0) throw new IllegalArgumentException("image " + s + " is corrupt");
        if (ws <= 1 && hs <= 1) pixel(x, y);

        offscreen.rotate(Math.toRadians(-degrees), xs, ys);
        offscreen.drawImage(image, (int) Math.round(xs - ws/2.0),
                                   (int) Math.round(ys - hs/2.0),
                                   (int) Math.round(ws),
                                   (int) Math.round(hs), null);
        offscreen.rotate(Math.toRadians(+degrees), xs, ys);

        draw();
    }
   /*************************************************************************
    *  Drawing text.
    *************************************************************************/
    /**
     * Write the given text string in the current font, centered on (x, y).
     * @param x the center x-coordinate of the text
     * @param y the center y-coordinate of the text
     * @param s the text
     */
    public static void text(double x, double y, String s) {
        offscreen.setFont(font);
        FontMetrics metrics = offscreen.getFontMetrics();
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = metrics.stringWidth(s);
        int hs = metrics.getDescent();
        offscreen.drawString(s, (float) (xs - ws/2.0), (float) (ys + hs));
        draw();
    }
    /**
     * Write the given text string in the current font, centered on (x, y) and
     * rotated by the specified number of degrees  
     * @param x the center x-coordinate of the text
     * @param y the center y-coordinate of the text
     * @param s the text
     * @param degrees is the number of degrees to rotate counterclockwise
     */
    public static void text(double x, double y, String s, double degrees) {
        double xs = scaleX(x);
        double ys = scaleY(y);
        offscreen.rotate(Math.toRadians(-degrees), xs, ys);
        text(x, y, s);
        offscreen.rotate(Math.toRadians(+degrees), xs, ys);
    }
    /**
     * Write the given text string in the current font, left-aligned at (x, y).
     * @param x the x-coordinate of the text
     * @param y the y-coordinate of the text
     * @param s the text
     */
    public static void textLeft(double x, double y, String s) {
        offscreen.setFont(font);
        FontMetrics metrics = offscreen.getFontMetrics();
        double xs = scaleX(x);
        double ys = scaleY(y);
        int hs = metrics.getDescent();
        offscreen.drawString(s, (float) (xs), (float) (ys + hs));
        draw();
    }
    /**
     * Write the given text string in the current font, right-aligned at (x, y).
     * @param x the x-coordinate of the text
     * @param y the y-coordinate of the text
     * @param s the text
     */
    public static void textRight(double x, double y, String s) {
        offscreen.setFont(font);
        FontMetrics metrics = offscreen.getFontMetrics();
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = metrics.stringWidth(s);
        int hs = metrics.getDescent();
        offscreen.drawString(s, (float) (xs - ws), (float) (ys + hs));
        draw();
    }
    /**
     * Display on screen, pause for t milliseconds, and turn on
     * <em>animation mode</em>: subsequent calls to
     * drawing methods such as <tt>line()</tt>, <tt>circle()</tt>, and <tt>square()</tt>
     * will not be displayed on screen until the next call to <tt>show()</tt>.
     * This is useful for producing animations (clear the screen, draw a bunch of shapes,
     * display on screen for a fixed amount of time, and repeat). It also speeds up
     * drawing a huge number of shapes (call <tt>show(0)</tt> to defer drawing
     * on screen, draw the shapes, and call <tt>show(0)</tt> to display them all
     * on screen at once).
     * @param t number of milliseconds
     */
    public static void show(int t) {
        defer = false;
        draw();
        try { Thread.sleep(t); }
        catch (InterruptedException e) { System.out.println("Error sleeping"); }
        defer = true;
    }
    /**
     * Display on-screen and turn off animation mode:
     * subsequent calls to
     * drawing methods such as <tt>line()</tt>, <tt>circle()</tt>, and <tt>square()</tt>
     * will be displayed on screen when called. This is the default.
     */
    public static void show() {
        defer = false;
        draw();
    }
    // draw onscreen if defer is false
    private static void draw() {
        if (defer) return;
        onscreen.drawImage(offscreenImage, 0, 0, null);
        frame.repaint();
    }
   /*************************************************************************
    *  Save drawing to a file.
    *************************************************************************/
    /**
     * Save onscreen image to file - suffix must be png, jpg, or gif.
     * @param filename the name of the file with one of the required suffixes
     */
    public static void save(String filename) {
        File file = new File(filename);
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        // png files
        if (suffix.toLowerCase().equals("png")) {
            try { ImageIO.write(onscreenImage, suffix, file); }
            catch (IOException e) { e.printStackTrace(); }
        }
        // need to change from ARGB to RGB for jpeg
        // reference: http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java2d-interest&D=0&P=2727
        else if (suffix.toLowerCase().equals("jpg")) {
            WritableRaster raster = onscreenImage.getRaster();
            WritableRaster newRaster;
            newRaster = raster.createWritableChild(0, 0, width, height, 0, 0, new int[] {0, 1, 2});
            DirectColorModel cm = (DirectColorModel) onscreenImage.getColorModel();
			DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(), cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
			BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster, false,  null);
			try { ImageIO.write(rgbBuffer, suffix, file); }
			catch (IOException e) { e.printStackTrace(); } }
		else {System.out.println("Invalid image file type: " + suffix); } }
	public void actionPerformed(ActionEvent e) {
		FileDialog chooser = new FileDialog(StdDraw.frame, "Use a .png or .jpg extension", FileDialog.SAVE);
		chooser.setVisible(true);
		String filename = chooser.getFile();
		if (filename != null) {
			StdDraw.save(chooser.getDirectory() + File.separator + chooser.getFile()); } }
//Mouse Stuff
	public static boolean mousePressed() {
		synchronized (mouseLock) {
			return mousePressed; } }
	public static double mouseX() {
		synchronized (mouseLock) {
			return mouseX; } }
	public static double mouseY() {
		synchronized (mouseLock) {
			return mouseY; } }
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) {
		synchronized (mouseLock) {
			mouseX = StdDraw.userX(e.getX());
			mouseY = StdDraw.userY(e.getY());
			mousePressed = true; } }
	public void mouseReleased(MouseEvent e) {
		synchronized (mouseLock) {
			mousePressed = false; } }
	public void mouseDragged(MouseEvent e)  {
		synchronized (mouseLock) {
			mouseX = StdDraw.userX(e.getX());
			mouseY = StdDraw.userY(e.getY()); } }
	public void mouseMoved(MouseEvent e) {
		synchronized (mouseLock) {
			mouseX = StdDraw.userX(e.getX());
			mouseY = StdDraw.userY(e.getY()); } }
//Keyboard Interactions
	public static boolean hasNextKeyTyped() {
		synchronized (keyLock) {
			return !keysTyped.isEmpty(); } }
	public static char nextKeyTyped() {
		synchronized (keyLock) {
			return keysTyped.removeLast(); } }
	public static boolean isKeyPressed(int keycode) {
		synchronized (keyLock) {
			return keysDown.contains(keycode); } }
	public void keyTyped(KeyEvent e) {
		synchronized (keyLock) {
			keysTyped.addFirst(e.getKeyChar()); } }
	public void keyPressed(KeyEvent e) {
		synchronized (keyLock) {
			keysDown.add(e.getKeyCode()); } }
	public void keyReleased(KeyEvent e) {
		synchronized (keyLock) {
			keysDown.remove(e.getKeyCode()); } }
	public static void main(String args[]) {System.err.print("You cannot run this program by itself."); } }
