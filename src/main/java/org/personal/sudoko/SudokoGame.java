package org.personal.sudoko;

public class SudokoGame {
    Board board;

    Solver solver;

    public SudokoGame() {
        this.board = new Board(3);
        this.solver = new Solver();
    }

    public void startGame() {
        board.generatePuzzle();  // Generate a new puzzle
        board.display();
    }

    public boolean isSolved() {
        return board.isComplete();
    }

    public void makeMove() {

    }

    public void solveGame() {
        solver.solve(board);
    }
}
