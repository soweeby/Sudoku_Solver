package Sudoku_Puzzle;

public class Backtracker {

    public SudokuInstance solve(SudokuInstance c) {
        if (c.isSolution()) {
            System.out.println("Solution:");
            System.out.println(c);
            return c;
        }
        else { //puzzle not solution
            for (SudokuInstance p : c.getPermutations()) {
                if (p.isValid()) { //solves next valid puzzle from list of permutations
                    SudokuInstance s = solve(p);
                    if (s != null) {
                        return s;
                    }
                }
            }
        }
        return null;
    }
}
