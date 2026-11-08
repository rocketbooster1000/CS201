import java.util.Arrays;

/**
 * Simulate percolation on an n-by-n grid of hexagons using
 * depth-first-search, aka 'flood-fill', to determine if the top of
 * the grid is connected to the bottom of the grid.
 * <P>
 * Same logic as PercolationDefault, but each cell has six neighbors
 * rather than four. The grid is stored as an n-by-n array where odd
 * rows are shifted right by half a hexagon (an "odd-r" offset layout):
 * <pre>
 *    (0,0) (0,1) (0,2)
 *       (1,0) (1,1) (1,2)
 *    (2,0) (2,1) (2,2)
 * </pre>
 * Cell [row,col] is adjacent to [row,col-1] and [row,col+1] in the same row.
 * In the rows above and below:
 * <ul>
 * <li> for an even row, the neighbors are columns col-1 and col
 * <li> for an odd row, the neighbors are columns col and col+1
 * </ul>
 *
 * Implements IPercolate since cells are still identified by (row,col);
 * only the definition of adjacency differs from PercolationDefault.
 *
 * @author Owen Astrachan (helped by Claude Code given PercolationDefault)
 */

public class HexPercolationDefault implements IPercolate {

	// column offsets for neighbors in the row above/below,
	// indexed by row % 2
	private static final int[][] VERTICAL_COL_OFFSETS = {
			{-1, 0},     // even rows
			{ 0, 1}      // odd rows
	};

	protected int[][] myGrid;    // grid to simulate
	protected int myOpenCount;   // # cells open

	/**
	 * Initialize a grid so that all cells are blocked.
	 *
	 * @param n
	 *            is the size of the simulated grid, n rows of n hexagons
	 */
	public HexPercolationDefault(int n) {
		myGrid = new int[n][n];
		myOpenCount = 0;
		for (int[] row : myGrid) {
			Arrays.fill(row, BLOCKED);
		}
	}

	/**
	 * Return the [row,col] coordinates of the six hexagons adjacent
	 * to [row,col]. Some may be out of bounds; callers should check.
	 * @param row specifies row
	 * @param col specifies column
	 * @return array of six {row,col} pairs
	 */
	protected int[][] neighbors(int row, int col) {
		int[] offs = VERTICAL_COL_OFFSETS[row % 2];
		return new int[][] {
				{row - 1, col + offs[0]},
				{row - 1, col + offs[1]},
				{row,     col - 1},
				{row,     col + 1},
				{row + 1, col + offs[0]},
				{row + 1, col + offs[1]}
		};
	}

	/**
	 * Open the cell at [row,col], no action if the cell is other than BLOCKED
	 * State of cell will change to OPEN, then other cells may change state
	 * because updateOnOpen is called
	 */
	@Override
	public void open(int row, int col) {

		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col] != BLOCKED) {
			return;
		}
		myOpenCount += 1;
		myGrid[row][col] = OPEN;
		updateOnOpen(row,col);
	}

	/**
	 * returns true if and only if cell at [row,col]
	 * is not BLOCKED, i.e., it is OPEN or FULL
	 */
	@Override
	public boolean isOpen(int row, int col) {

		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col] != BLOCKED;
	}

	/**
	 * returns true if and only if cell at [row,col]
	 * has state == FULL
	 */
	@Override
	public boolean isFull(int row, int col) {

		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col] == FULL;
	}

	/**
	 * Update cells that might be FULL rather
	 * than OPEN. The cell at [row,col] should
	 * have been just defined as OPEN rather
	 * than BLOCKED, now check if it should be FULL,
	 * i.e., it's in the top row or a neighbor is FULL
	 * @param row
	 * @param col
	 */
	protected void updateOnOpen(int row, int col) {
		if (row == 0) {
			// it's in the top row, search from there
			search(row,col);
			return;
		}
		for (int[] nb : neighbors(row, col)) {
			int nr = nb[0];
			int nc = nb[1];

			// cell at [row][col] was just opened. Is a neighbor full?
			if (inBounds(nr,nc) && isFull(nr,nc)) {
				search(row,col);
				return;             // no need to continue to search
			}
		}
	}

	/**
	 * Returns true if and only if system percolates
	 */
	@Override
	public boolean percolates() {
		// check bottom row for fullness
		for (int col = 0; col < myGrid[myGrid.length - 1].length; col++) {
			if (isFull(myGrid.length - 1, col)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Mark all cells that are open and reachable from
	 * (row,col) as FULL using recursive depth-first search
	 * over all six hexagonal neighbors.
	 *
	 * @param row
	 *            is the row coordinate of the cell being checked/marked
	 * @param col
	 *            is the col coordinate of the cell being checked/marked
	 */
	protected void search(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) return;

		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col)){
			return;
		}
		myGrid[row][col] = FULL;
		for (int[] nb : neighbors(row, col)) {
			search(nb[0], nb[1]);
		}
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}

	/**
	 * Determine if (row,col) is valid for given grid
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if (row,col) on grid, false otherwise
	 */
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
}
