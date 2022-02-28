class SolutionCandyCrush {
    public int[][] candyCrush(int[][] board) {
        
        int rows = board.length;
        int cols = rows == 0 ? 0 : board[0].length;
        boolean todo = false;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols - 2; col++) {
                int val = Math.abs (board[row][col]);
                if (val != 0 && val == Math.abs (board[row][col + 1]) && val == Math.abs (board[row][col + 2])) {
                    todo = true;
                    board[row][col] = board[row][col + 1] = board[row][col + 2] = -val;
                }
            }
        }
        
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows - 2; row++) {
                int val = Math.abs (board[row][col]);
                if (val != 0 && val == Math.abs (board[row + 1][col]) && val == Math.abs (board[row + 2][col])) {
                    todo = true;
                    board[row][col] = board[row + 1][col] = board[row + 2][col] = -val;
                }
            }
        }
        
        for (int col = 0; col < cols; col++) {
            int row2 = rows - 1;
            
            for (int row1 = rows - 1; row1 >= 0; row1--) {
                if (board[row1][col] > 0) {
                    board[row2--][col] = board[row1][col];
                }
            }
            
            while (row2 >= 0) {
                board[row2--][col] = 0;
            }
        }
        
        return todo ? candyCrush (board) : board;
    }
}
