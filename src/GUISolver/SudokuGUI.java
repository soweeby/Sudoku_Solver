package GUISolver;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SudokuGUI extends Application {
    private SudokuModel model;
    private List<Button> buttons;
    private GridPane pane;
    private Stage stage;

    String filename;
    public void init() throws IOException {
        try {
            filename = getParameters().getRaw().get(0);
        }
        catch (NullPointerException e) {
            filename = null;
        }
        this.model = new SudokuModel();
        this.model.addGUI(this);
        this.model.loadPuzzle(filename);
    }


    public static void main(String[] args) {
        launch(args);
    }


    private void makeButtons(GridPane pane, Stage stage, boolean change) {
        this.buttons = new LinkedList<>();
        pane.getChildren().clear();
        //setting buttons for each number in the puzzle
        int id = 0;
        for(int row = 0; row < this.model.getSize(); ++row) {
            for (int col = 0; col < this.model.getSize(); ++col) {
                int num = this.model.getPuzzle().getBoard()[row][col];
                Button button = (change ? this.buttons.get(id) : new Button());
                id += 1;
                buttons.add(button);
                button.setStyle("fx-font-size: " + 20 + ";"); //todo: get rid of magic number
                button.setMinSize(75, 75);
                button.setMaxSize(75, 75);
                button.setText(Integer.toString(num));
                pane.add(button, col, row);
            }
        }

    }


    @Override
    public void start(Stage primaryStage) {
        Button load = new Button("Load");
        load.setId("load");
        //TODO: SET UP EVENT HANDLER FOR LOAD BUTTON

        Button solve = new Button("Solve");
        //TODO: SET UP EVENT HANDLER FOR SOLVE BUTTON

        //making grid pane with number buttons
        GridPane gridPane = new GridPane();
        makeButtons(gridPane, primaryStage, false);
        this.pane = gridPane;

        //creating hbox for solve and load buttons on bottom
        HBox hbox = new HBox();

        hbox.getChildren().addAll(load, solve);

        BorderPane bottomPane = new BorderPane();
        bottomPane.setCenter(hbox);
        hbox.setAlignment(Pos.CENTER);

        //creating main borderpane
        BorderPane mainPane = new BorderPane();
        mainPane.setBottom(bottomPane);
        BorderPane.setAlignment(bottomPane, Pos.BOTTOM_CENTER);
        mainPane.setCenter(gridPane);

        //creating window
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sudoku Solver");
        this.stage = primaryStage;
        primaryStage.show();
    }

    public void update(String data) {
        if (!buttons.isEmpty()) {
            makeButtons(this.pane, this.stage, true);
        }
    }
}
