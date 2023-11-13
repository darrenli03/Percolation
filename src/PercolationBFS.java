import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDefault{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }

    @Override
    protected void search(int row, int col) {
        if (!inBounds(row, col) || isFull(row, col) || !isOpen(row, col)) {
            return;
        }
        //confirmed the input cell is open (not full or closed), now mark all connected cells and itself as full
        Queue<int[]> qp = new LinkedList<>();
        int size = 0;
        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        myGrid[row][col] = FULL;
        qp.add(new int[]{row, col});
        while (qp.size() != 0) {
            int[] p = qp.remove();
            for (int k = 0; k < rowDelta.length; k++) {
                row = p[0] + rowDelta[k];
                col = p[1] + colDelta[k];
                if (inBounds(row, col) && !isFull(row, col) && isOpen(row, col)) {
                    qp.add(new int[]{row, col});
                    myGrid[row][col] = FULL;
                    size++;
                }
            }
        }
    }


}
