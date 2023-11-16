package Project.common;

import java.io.Serializable;
//fss22, Novemebr 15
public class Grid implements Serializable {
    public static final int ROWS = 8;
    public static final int COLS = 8;

    private char[][] board;
    private char[][] colors;

    public Grid() {
        this.board = new char[ROWS][COLS];
        this.colors = new char[ROWS][COLS]; 
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
                colors[i][j] = ' ';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] newBoard) {
        this.board = newBoard;
    }

    public void clearBoard() {
        initializeBoard();
    }
    public char[][] getColors() {
        return colors;
    }

    public void setColors(char[][] newColors) {
        this.colors = newColors;
    }
}