package Project.common;

import java.io.Serializable;

public class Grid implements Serializable {
    public static final int ROWS = 8;
    public static final int COLS = 8;

    private char[][] board;

    public Grid() {
        this.board = new char[ROWS][COLS];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void clearBoard() {
        initializeBoard();
    }
}
