import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDefault{
    public PercolationBFS(int n) {
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
        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        Queue<int[]> qp = new LinkedList<>();
        myGrid[row][col] = FULL;  // mark pixel
        qp.add(new int[]{row,col});
        while (qp.size() != 0){
            int[] p = qp.remove();
            for(int k=0; k < rowDelta.length; k++){
                row = p[0] + rowDelta[k];
                col = p[1] + colDelta[k];
                if (inBounds(row,col) && myGrid[row][col] == OPEN){
                    qp.add(new int[]{row,col});
                    myGrid[row][col] = FULL;                
                }
            }
        }
        }
	}
