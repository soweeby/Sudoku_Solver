/**
 * @author Roshan Nunna
 * Model for GUI Solver
 */
package GUISolver;

import Sudoku_Puzzle.SudokuInstance;

import java.io.IOException;

public class SudokuModel {
    private SudokuGUI gui;
    private SudokuInstance puzzle;

    public SudokuModel() {

    }

    public void addGUI(SudokuGUI gui) {
        this.gui = gui;
    }

    public void updateGUI(String data) {

    }

    public void load(String filename) throws IOException {
        this.puzzle = new SudokuInstance(filename);
    }
}
