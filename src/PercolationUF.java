import java.util.LinkedList;
import java.util.Queue;

public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        finder.initialize(size * size + 2);
        myFinder = finder;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
    }

    @Override
    public boolean isOpen(int row, int col) {

        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myGrid[row][col];//check
    }

    @Override
    public boolean isFull(int row, int col) {

        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }

        return myFinder.connected(convertCoordinates(row, col), VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    @Override
    public void open(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        if (isOpen(row, col)) return;

    }

    /**
     * checks if the row and column values are valid, returns false if they are not
     *
     * @param row row coordinate
     * @param col column coordinate
     */
    public boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    public int convertCoordinates(int row, int col) {
        return row * myGrid.length + col;
    }


}



    

