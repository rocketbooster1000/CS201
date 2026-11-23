import java.util.Stack;

public class PercolationDFS extends PercolationDefault{
    public PercolationDFS(int n) {
        super(n);
	}
    
    @Override
    protected void search(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col)){
			return;
		}
        Stack<int[]> stack = new Stack<>();
        int[] rowDelta = {-1,0,0,1}; //row delta, changes cell coordinates
            int[] colDelta = { 0,-1,1,0}; //col delta, same as row delta but with cols
        myGrid[row][col] = FULL;  // mark pixel
        stack.push(new int[]{row,col});
        while (stack.size() != 0){
            int[] coords = stack.pop();
            for (int k = 0; k < rowDelta.length; k++) {
                int neighboringRow = coords[0] + rowDelta[k]; //neighbor row, helps check neighbors on same row
                int neighboringCol = coords[1] + colDelta[k]; //neighbor column, helps check neighbors on same col

                if (inBounds(neighboringRow, neighboringCol) && myGrid[neighboringRow][neighboringCol] == OPEN){
                myGrid[neighboringRow][neighboringCol] = FULL;
                stack.push(new int[]{neighboringRow, neighboringCol});
                }
            }
                
        }
        }
	}

