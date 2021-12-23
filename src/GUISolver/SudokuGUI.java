package GUISolver;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuGUI extends Application {
    private SudokuModel model;

    public void init() throws IOException {
        try {
            String filename = getParameters().getRaw().get(0);
        }
        catch (NullPointerException e) {
            String filename = null;
        }

        this.model.addGUI(this);
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
