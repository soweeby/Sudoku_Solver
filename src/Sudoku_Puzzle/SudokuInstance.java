/**
 * @author: Roshan Nunna
 * This class represents an instance of a sudoku puzzle.
 */
package Sudoku_Puzzle;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * copy constructor to create neighbors
     * @param other SudokuInstance to copy from
     * @param num number to put into next empty spot
     */
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
        //if next spot isn't empty
        while (this.board[row][col] != 0) {
            this.cursor += 1;
            row = this.cursor / this.rows;
            col = this.cursor % this.cols;
        }
        //fills in next empty spot with number
        this.board[row][col] = num;
    }

    /**
     * Gets permutations of a particular SudokuInstance 1 spot over to the right
     * @return list of permutations
     */
    public List<SudokuInstance> getPermutations() {
        ArrayList<SudokuInstance> permutations = new ArrayList<>();
        for (int i = 1; i <= this.rows; ++i) { //keeps adding numbers into the spot to the right
            permutations.add(new SudokuInstance(this, i));
        }
        return permutations;
    }

    /**
     * makes sure there are no duplicates in the rows
     * @return true if no duplicates, false if there are duplicates
     */
    private boolean validRows() {
        for (int i = 0; i < this.rows; ++i) {
            HashSet<Integer> ints = new HashSet<>();
            for (int j = 0; j < this.cols; ++j) {
                if (ints.contains(this.board[i][j])) { //if this is true, this means there are duplicates
                    return false;
                }
                if (this.board[i][j] != 0) { //prevents empty spaces from getting counted as duplicates
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
    private boolean validCols() {

        for (int i = 0; i < this.cols; ++i) {
            HashSet<Integer> ints = new HashSet<>();
            for (int j = 0; j < this.rows; ++j) {
                if (ints.contains(this.board[j][i])) { //if this is true, this means there are duplicates
                    return false;
                }
                if (this.board[j][i] != 0) { //prevents empty spaces from getting counted as duplicates
                    ints.add(this.board[j][i]);
                }
            }
        }
        return true;
    }

    /**
     * checks if the whole board is filled
     * @return true if board is filled, false if board isn't filled
     */
    private boolean isFilled() {
        for (int i = 0; i < this.rows; ++i) {
            //converts array of ints to list of Integers
            List<Integer> ints = Arrays.stream(this.board[i]).boxed().toList();
            if (ints.contains(0)) { //0's represent empty spaces. so, if a row contains a 0, then the board isn't filled
                return false;
            }
        }
        return true;
    }

    /**
     * checks if whole board is valid by checking if the columns & rows are valid
     * @return true if whole board is valid, false if not
     */
    public boolean isValid() {
        return validCols() && validRows();
    }

    /**
     * checks if the current instance is the solution by checkinng if the rows & colums are valid
     * and if the entire board is filled
     * @return true if solution, false if not solution
     */
    public boolean isSolution() {
        return isValid() && isFilled();
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
