public class PercolationUF implements IPercolate{
    private boolean[][] myGrid;
    private int myOpenCount;
    private IUnionFind myFinder;
    private final int VTOP, VBOTTOM;

    PercolationUF(IUnionFind ufObject, int n){
        myGrid = new boolean[n][n];
        myFinder = ufObject;
        myFinder.initialize(n * n + 2);
        VTOP = n * n;
        VBOTTOM = n * n + 1;
    }

    @Override
    public void open(int row, int col) {
        if (!inBounds(row, col))
            throw new IndexOutOfBoundsException();
        if (myGrid[row][col]) return;
        myGrid[row][col] = true;
        if (inBounds(row - 1, col) && isOpen(row - 1, col)) {
            myFinder.union((row - 1) * myGrid.length + col, row * myGrid.length + col);
        }
        if (inBounds(row + 1, col) && isOpen(row + 1, col)) {
            myFinder.union((row + 1) * myGrid.length + col, row * myGrid.length + col);
        }
        if (inBounds(row, col - 1) && isOpen(row, col - 1)) {
            myFinder.union((row) * myGrid.length + col - 1, row * myGrid.length + col);
        }
        if (inBounds(row, col + 1) && isOpen(row, col + 1)) {
            myFinder.union((row) * myGrid.length + col + 1, row * myGrid.length + col);
        }
        if (row == 0) {
            myFinder.union(VTOP, row * myGrid.length + col);
        }
        if (row == myGrid.length - 1) {
            myFinder.union(VBOTTOM, row * myGrid.length + col);
        }
        myOpenCount++;
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col))
            throw new IndexOutOfBoundsException();
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col))
            throw new IndexOutOfBoundsException();
        return myFinder.connected(row * myGrid.length + col, VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
}
