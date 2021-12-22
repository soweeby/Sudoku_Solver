/**
 * @author: Roshan Nunna
 * This class represents an instance of a sudoku puzzle.
 */
package Sudoku_Puzzle;

import java.io.*;

public class SudokuInstance {
    private int rows;
    private int cols;
    private int[][] board; //creates a 9x9 board - typical sudoku board

    public SudokuInstance(String filename) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            //getting board size
            String[] fields = in.readLine().split("\\s+");
            //making empty board
            this.rows = Integer.parseInt(fields[0]);
            this.cols = Integer.parseInt(fields[1]);
            this.board = new int[this.rows][this.cols];
            //reading in pre-filled values for board
            for (int row = 0; row < this.rows; ++row) {
                fields = in.readLine().split("\\s+");
                for (int col = 0; col < this.cols; ++col) {
                    board[row][col] = Integer.parseInt(fields[col]);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    @Override
    public String toString() {
        String finString = "";
        for (int row = 0; row < this.rows; ++row) {
            for (int col = 0; col < this.cols; ++col) {
                finString += Integer.toString(board[row][col]);
                finString += " ";
            }
            finString += "\n";
        }
        return finString;
    }
}
