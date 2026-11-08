/******************************************************************************
 *  Compilation:  javac InteractiveHexagon.java
 *  Execution:    java InteractiveHexagon N
 *  Dependencies: HexPercolationDefault.java, IPercolate.java, StdDraw.java
 *
 *  Same logic as InteractivePercolationVisualizer, but each site (row,col)
 *  of the N-by-N grid is drawn as a "pointy-top" hexagon. Odd rows are
 *  shifted right by half a hexagon so the hexagons tessellate; this leaves
 *  jagged empty space on the left and right sides (and a zig-zag edge on
 *  the top and bottom) where a "missing" hexagon would be.
 *
 *  HexPercolationDefault uses the same odd-rows-shifted-right layout, so
 *  every hexagon that shares an edge on screen is connected in the model
 *  (six neighbors per site).
 *
 * @author Owen Astrachan
 ******************************************************************************/

import java.awt.Font;

public class InteractiveHexagon {

	// hexagon geometry: width (flat side to flat side) is 1.0,
	// circumradius R (center to vertex) is 1/sqrt(3)
	private static final double WIDTH = 1.0;
	private static final double R = WIDTH / Math.sqrt(3.0);
	private static final double ROW_HEIGHT = 1.5 * R;   // vertical distance between row centers
	private static final double INNER = 0.88;           // fraction of hexagon filled with color

	private static int N;

	/** x-coordinate of center of hexagon at (row,col) */
	private static double centerX(int row, int col) {
		double x = col * WIDTH + WIDTH / 2;
		if (row % 2 == 1) x += WIDTH / 2;
		return x;
	}

	/** y-coordinate of center of hexagon at (row,col), row 0 is at top */
	private static double centerY(int row, int col) {
		return (N - 1 - row) * ROW_HEIGHT + R;
	}

	/** total width and height of the hexagon grid */
	private static double gridWidth()  { return N * WIDTH + WIDTH / 2; }
	private static double gridHeight() { return (N - 1) * ROW_HEIGHT + 2 * R; }

	/** draw a filled pointy-top hexagon centered at (cx,cy) with circumradius r */
	private static void filledHexagon(double cx, double cy, double r) {
		double[] xs = new double[6];
		double[] ys = new double[6];
		for (int k = 0; k < 6; k++) {
			double angle = Math.toRadians(60 * k + 30);
			xs[k] = cx + r * Math.cos(angle);
			ys[k] = cy + r * Math.sin(angle);
		}
		StdDraw.filledPolygon(xs, ys);
	}

	/** return true iff (x,y) is inside pointy-top hexagon centered at (cx,cy) */
	private static boolean inHexagon(double x, double y, double cx, double cy) {
		double dx = Math.abs(x - cx);
		double dy = Math.abs(y - cy);
		if (dx > WIDTH / 2) return false;
		return dy <= R - dx / Math.sqrt(3.0);
	}

	/**
	 * Convert screen coordinates to {row,col} of the clicked hexagon,
	 * or return null if (x,y) is not inside any hexagon of the grid.
	 */
	private static int[] locate(double x, double y) {
		// approximate row from y, then check that row and its neighbors
		int approxRow = (int) Math.round(N - 1 - (y - R) / ROW_HEIGHT);
		for (int row = approxRow - 1; row <= approxRow + 1; row++) {
			if (row < 0 || row >= N) continue;
			double shift = (row % 2 == 1) ? WIDTH / 2 : 0;
			int approxCol = (int) Math.floor((x - shift) / WIDTH);
			for (int col = approxCol - 1; col <= approxCol + 1; col++) {
				if (col < 0 || col >= N) continue;
				if (inHexagon(x, y, centerX(row, col), centerY(row, col))) {
					return new int[] {row, col};
				}
			}
		}
		return null;
	}

	/** draw entire percolation system and status messages */
	private static void draw(IPercolate perc, int opened, int[] lastOpened) {
		StdDraw.clear();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				double cx = centerX(r, c);
				double cy = centerY(r, c);
				// full-size black hexagon is the border
				StdDraw.setPenColor(StdDraw.BLACK);
				filledHexagon(cx, cy, R);
				if (perc.isFull(r, c)) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				}
				else if (perc.isOpen(r, c)) {
					StdDraw.setPenColor(StdDraw.WHITE);
				}
				else {
					StdDraw.setPenColor(StdDraw.BLACK);
				}
				filledHexagon(cx, cy, R * INNER);
			}
		}
		double w = gridWidth();
		double h = gridHeight();
		double gap = 0.03 * Math.max(w, h);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(.25 * w, -gap, "sites opened = " + opened);
		if (perc.percolates()) StdDraw.text(.75 * w, -gap, "percolates");
		else                   StdDraw.text(.75 * w, -gap, "does not percolate");

		if (lastOpened[0] != -1) {
			StdDraw.text(.25 * w, h + gap,
					"last opened = [" + lastOpened[0] + "," + lastOpened[1] + "]");
		}
	}

	public static void main(String[] args) {

		// N-by-N percolation system
		N = 10;
		if (args.length > 0) {
			N = Integer.parseInt(args[0]);
		}

		IPercolate perc = new HexPercolationDefault(N);
		System.out.printf("visualizing %dx%d hexagon grid\n", N, N);

		// number of sites opened
		int opened = 0;

		StdDraw.setTitle("Interactive Hexagon Percolation Visualizer");
		StdDraw.enableDoubleBuffering();

		// use same scale in x and y so hexagons aren't distorted,
		// leave margin for writing above and below
		double w = gridWidth();
		double h = gridHeight();
		double span = Math.max(w, h) * 1.1;
		StdDraw.setXscale(w / 2 - span / 2, w / 2 + span / 2);
		StdDraw.setYscale(h / 2 - span / 2, h / 2 + span / 2);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));

		int[] lastOpened = {-1, -1};
		draw(perc, opened, lastOpened);

		// repeatedly open site specified by mouse click and draw resulting system
		while (true) {

			// detected mouse click
			if (StdDraw.isMousePressed()) {

				// screen coordinates
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();

				// convert to row, col of hexagon (null if click is outside grid)
				int[] site = locate(x, y);
				if (site != null) {
					int vi = site[0];
					int vj = site[1];
					if (!perc.isOpen(vi, vj)) {
						opened++;
						perc.open(vi, vj);
						lastOpened[0] = vi;
						lastOpened[1] = vj;
					}
				}
				draw(perc, opened, lastOpened);
			}
			StdDraw.show();
			StdDraw.pause(20);
		}
	}
}
