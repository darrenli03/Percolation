import java.util.LinkedList;
import java.util.Queue;

public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size)
    {
        myGrid = new boolean[size][size];
        finder.initialize(size * size + 2);
        myFinder = finder;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
    }

    @Override
    public boolean isOpen(int row, int col) {
		
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];//check
	}

    @Override
    public boolean isFull(int row, int col) {
		
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		return !myGrid[row][col];
	}

    @Override
    public boolean percolates()
    {
        return connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites(){
        return myOpenCount;
    }

    @Override
    public void open(int row, int col){

    }

    public boolean inBounds(int row, int col)
    {
        if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

    public boolean connected(int row, int col){
        if (!inBounds(row, col) || isFull(row, col) || !isOpen(row, col)) {
            return false;
        }
        //confirmed the input cell is open (not full or closed), now mark all connected cells and itself as full
        Queue<int[]> qp = new LinkedList<>();
        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        qp.add(new int[]{row, col});
        while (qp.size() != 0) {
            int[] p = qp.remove();
            for (int k = 0; k < rowDelta.length; k++) {
                row = p[0] + rowDelta[k];
                col = p[1] + colDelta[k];
                if (inBounds(row, col) && !isFull(row, col) && isOpen(row, col)) {
                    if(row == Math.sqrt(VTOP)) return true;
                    qp.add(new int[]{row, col});
                }

            }
        }

        return false;
    }
}



    

