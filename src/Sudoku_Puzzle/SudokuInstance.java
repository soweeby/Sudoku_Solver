/**
 * @author: Roshan Nunna
 * This class represents an instance of a sudoku puzzle.
 */
package Sudoku_Puzzle;

import java.io.*;
import java.util.*;

public class SudokuInstance {
    private int rows;
    private int cols;
    private int cursor = -1; //cursor starts off the board
    private int[][] board; //creates a 9x9 board - typical sudoku board

    /**
     * Creates a new SudokuInstance by reading from a file.
     * @param filename filename of file to read
     * @throws IOException
     */
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

    private SudokuInstance(SudokuInstance other, int num) {
        this.cursor = other.cursor + 1;
        this.rows = other.rows;
        this.cols = other.cols;
        this.board = new int[this.rows][this.cols];
        for (int crow = 0; crow < this.rows; ++crow) {
            System.arraycopy(other.board[crow], 0, this.board[crow], 0, this.cols);
        }

        int row = this.cursor / this.rows;
        int col = this.cursor % this.cols;

        this.board[row][col] = num;
    }

    /**
     * Gets permutations of a particular SudokuInstance 1 spot over to the right
     * @return list of permutations
     */
    public List<SudokuInstance> getPermutations() {
        ArrayList<SudokuInstance> permutations = new ArrayList<>();
        for (int i = 1; i < this.rows; ++i) {
            permutations.add(new SudokuInstance(this, i));
        }
        return permutations;
    }

    /**
     * makes sure there are no duplicates in the rows
     * @return true if no duplicates, false if there are duplicates
     */
    public boolean validRows() {
        HashSet<Integer> ints = new HashSet<>();
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                if (ints.contains(this.board[i][j])) {
                    return false;
                }
                if (this.board[i][j] != 0) {
                    ints.add(this.board[i][j]);
                }
            }
        }
        return true;
    }

    /**
     * make sure there are no duplicates in the columns
     * @return true if no duplicates, false if there are duplicates
     */
    public boolean validCols() {
        HashSet<Integer> ints = new HashSet<>();
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                if (ints.contains(this.board[j][i])) {
                    return false;
                }
                if (this.board[j][i] != 0) {
                    ints.add(this.board[j][i]);
                }
            }
        }
        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuInstance that = (SudokuInstance) o;
        return rows == that.rows && cols == that.cols && Arrays.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }
}
