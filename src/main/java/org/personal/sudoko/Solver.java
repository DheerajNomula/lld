package org.personal.sudoko;


// solving the algo using backtracking
public class Solver {
    public boolean solve(Board board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.board[row][col].value == 0) {
                    for (int i = 1; i <= 9; i++) {
                        if (board.isValidMove(row, col, i)) {
                            board.board[row][col].value = i;
                            if (solve(board))
                                return true;
                            board.board[row][col].value = 0; // backtrack

                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
