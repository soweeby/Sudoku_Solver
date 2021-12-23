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
    private int size;

    public SudokuModel() {

    }

    public void addGUI(SudokuGUI gui) {
        this.gui = gui;
        updateGUI(null);
    }

    public void updateGUI(String data) {
        //TODO: IMPLEMENT THIS
    }

    public void loadPuzzle(String filename) throws IOException {
        this.puzzle = new SudokuInstance(filename);
        this.size = this.puzzle.getSize();
        updateGUI(null);
    }

    public int getSize() {
        return this.size;
    }

    public SudokuInstance getPuzzle() {
        return this.puzzle;
    }
}
