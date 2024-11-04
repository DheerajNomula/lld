package org.personal.sudoko;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    int SIZE = 9;
    Cell[][] board;

    Board(int size) {
        this.SIZE = size;
        board = new Cell[size][size];
    }

    public void generatePuzzle() {
        // todo randomly generate values between 1 to 9 in random cells
    }

    public void display() {
    }

    public boolean isComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].value == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setCell(int row, int col, int value) {
        board[row][col].value = value;
    }

    public boolean isValidMove(int x, int y, int val) {
        return isValidInRow(x, val) && isValidInCol(y, val) && isValidInSubArray(x, y, val);
    }

    private boolean isValidInRow(int row, int value) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i].value == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInCol(int col, int value) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col].value == value) {
                return false;
            }
        }
        return true;
    }


    private boolean isValidInSubArray(int row, int col, int value) {
        int blockRowStart = row / 3 * 3; // todo assuming 3 x 3
        int blockColStart = col / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[blockRowStart + i][blockColStart + j].value == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
