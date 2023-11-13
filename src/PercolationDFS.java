import java.util.Stack;

public class PercolationDFS extends PercolationDefault {
    
    public PercolationDFS(int n){
        super(n);
    }

    @Override
    protected void search(int row, int col)
    {
        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        if (!inBounds(row, col) || isFull(row, col) || !isOpen(row, col)) return; // not part of blob

        Stack<int[]> stack = new Stack<>();
        myGrid[row][col] = FULL;  // mark pixel
        stack.push(new int[]{row,col});
        while (stack.size() != 0){
            int[] coords = stack.pop();
            for(int k=0; k < rowDelta.length; k++){
                row = coords[0] + rowDelta[k];
                col = coords[1] + colDelta[k];
                if (inBounds(row, col) && !isFull(row, col) && isOpen(row, col)){
                    stack.push(new int[]{row,col});
                    myGrid[row][col] = FULL;
                }
            }
        }
    }

        //check if full
        //inbounds
        //making the stack
        //whil stack size not 0
        //for each child add to the stack
        //go thought top left right bottom and add to the stack as long as they are in bounds and is not full, if it is fine, we mark as fuoll and push to the stack



    }
