/**
 * @author Roshan Nunna
 * Test file to make sure toString for SudokuInstance is working
 */
package Sudoku_Puzzle;

import java.io.IOException;

public class SudokuTest {
    public static void main(String args[]) throws IOException {
        String filename = args[0];

        SudokuInstance puzzle = new SudokuInstance(filename);

        System.out.println(puzzle);
    }
}
