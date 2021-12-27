package GUISolver;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SudokuGUI extends Application {
    private SudokuModel model;
    private List<Button> buttons;
    private GridPane pane;
    private Stage stage;


    public void init() throws IOException {
        String filename;
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
        if (!change) {
            this.buttons = new LinkedList<>();
        }
        pane.getChildren().clear();
        //setting buttons for each number in the puzzle
        int id = 0;
        for(int row = 0; row < this.model.getSize(); ++row) {
            for (int col = 0; col < this.model.getSize(); ++col) {
                int num = this.model.getPuzzle().getBoard()[row][col];
                Button button = (change ? this.buttons.get(id) : new Button());
                id += 1;
                if (!change) {
                    buttons.add(button);
                }
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
        FileChooser fc = new FileChooser(); //filechooser for load

        Button load = new Button("Load");
        load.setId("load");
        //load button brings up a file chooser dialog to load a new puzzle
        load.setOnAction(event -> {
            File selectedFile = fc.showOpenDialog(primaryStage);
            try {
                this.model.loadPuzzle(selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button solve = new Button("Solve");
        solve.setId("solve");
        solve.setOnAction(event -> {
            this.model.solvePuzzle();
        });

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
        if (this.buttons != null) {
            if (data != null && data.equals("Loaded")) {
                makeButtons(this.pane, this.stage, false);
            }
            else {
                makeButtons(this.pane, this.stage, true);
            }
            this.stage.sizeToScene();
        }

    }
}
