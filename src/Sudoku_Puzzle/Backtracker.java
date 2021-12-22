/**
 * @author Roshan Nunna
 * Backtracking algorithm to solve sudoku puzzles
 */
package Sudoku_Puzzle;

public class Backtracker {

    /**
     * solves a given sudoku puzzle
     * @param c puzzle to solve
     * @return solved sudoku puzzle, or null if there is no solution
     */
    public SudokuInstance solve(SudokuInstance c) {
        if (c.isSolution()) {
            System.out.println("Solution:");
            System.out.println(c);
            return c;
        }
        else { //current puzzle not solution
            for (SudokuInstance p : c.getPermutations()) {
                if (p.isValid()) { //recursively solves next valid puzzle from list of permutations
                    SudokuInstance s = solve(p);
                    if (s != null) {
                        return s;
                    }
                }
            }
        }
        //there are no solutions
        return null;
    }
}
