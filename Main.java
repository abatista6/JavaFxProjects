package sample;

import javafx.application.Application;
import java.util.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.collections.*;

/** JavaFX IntSortingSet Program
 * @author Adrianna Batista
 * @version 1.0
 * file name: Main.java
 */

public class Main extends Application {

    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 220;
    private Text label, prom;
    private TextField input;
    private Button maxButton, insertButton, minButton, clearButton, sortUpButton, sortDownButton;
    private int sum;
    private int min;
    private int max;
    private int number;
    private int size;
    private String s;

    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane gridPane = new GridPane(); //use pane layout for public access to getChildren() method
        gridPane.setId("background");

        //gridpane
        gridPane.setPadding(new Insets(10, 10, 10, 10)); //set padding
        gridPane.setVgap(10); //set vertical column gaps
        gridPane.setHgap(10); //set horizontal column gaps
        gridPane.setAlignment(Pos.CENTER);//Setting the Grid alignment

        //create scene and configure stage
        Scene scene = new Scene(gridPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Integer Sorting Set Program");
        primaryStage.setResizable(false);
        primaryStage.show();
        gridPane.requestFocus();

        //Vector Label
        label = new Text("Contents:");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        gridPane.setConstraints(label, 4, 0);
        gridPane.getChildren().add(label);

        //Vector Contents Text
        prom = new Text();
        gridPane.setConstraints(prom, 5, 0);
        gridPane.getChildren().add(prom);

        //VBox
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #ddcee9");
        vbox.setMinHeight(75);
        vbox.setMinWidth(100);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(prom);
        gridPane.setColumnSpan(vbox,4);
        gridPane.setConstraints(vbox, 4, 1);
        gridPane.getChildren().add(vbox);

        //Input Textfield
        input = new TextField();
        input.setPromptText("Enter an integer...");
        gridPane.setColumnSpan(input,2);
        gridPane.setConstraints(input, 4, 3);
        gridPane.getChildren().add(input);

        //Insert Button
        insertButton = new Button(" Insert ");
        GridPane.setConstraints(insertButton, 6, 3);
        gridPane.getChildren().add(insertButton);

        //Ascending Sort Button
        sortUpButton = new Button("Ascending Sort");
        GridPane.setConstraints(sortUpButton, 4, 4);
        gridPane.getChildren().add(sortUpButton);

        //Descending Sort Button
        sortDownButton = new Button("Descending Sort");
        GridPane.setConstraints(sortDownButton, 5, 4);
        gridPane.getChildren().add(sortDownButton);

        //Minimum Button
        minButton = new Button("Minimum");
        GridPane.setConstraints(minButton, 6,4);
        gridPane.getChildren().add(minButton);

        //Maximum Button
        maxButton = new Button("Maximum");
        GridPane.setConstraints(maxButton, 7,4);
        gridPane.getChildren().add(maxButton);

        //Clear Button
        clearButton = new Button(" Clear ");
        GridPane.setConstraints(clearButton, 7, 3);
        gridPane.getChildren().add(clearButton);

        //set min and max to highest and lowest possible values
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        //Button Action Event Handlers
        insertButton.setOnAction(e -> { //input handler & validation
            s = input.getText(); //string variable contains user input; retrieve user text value
            if (s.length() == 0) {
                showMessage("No numbers entered! Try again.", "error");
                input.setPromptText("Enter an integer...");
            }
            else if (!s.matches("-?\\d+")) { //if not valid positive or negative integer
                showMessage("Invalid entry! Try again.", "error");
                input.setPromptText("Enter an integer...");
            }
            else if (isInt(s)){
                number = Integer.parseInt(s); //integer variable parses valid user input string
                Controller.add(number);
                sum += number;
                input.setText("");
                if (number < min)
                    min = number;
                if (number > max)
                    max = number;
                String list = Controller.listAsString();
                prom.setText(list);
            }
        });

        clearButton.setOnAction(e -> { //clear button handler
            if (Controller.size()!=0) {
                Controller.removeAll();
                //reset set variables
                sum = 0;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
            }
            else {
                showMessage("No numbers entered yet!", "error");
                input.setPromptText("Enter an integer...");
            }
            String list = Controller.listAsString();
            prom.setText(list);
        });

        sortDownButton.setOnAction(e -> { //descending sort button handler
            if (Controller.size()==0) {
                showMessage("No numbers entered yet!", "error");
            }
            else {
                Controller.sortDescend();
                String list = Controller.listAsString();
                prom.setText(list);
            }
        });

        sortUpButton.setOnAction(e -> { //ascending sort button handler
            if (Controller.size()==0) {
                showMessage("No numbers entered yet!", "error");
            }
            else {
                Controller.sortAscend();
                String list = Controller.listAsString();
                prom.setText(list);
            }
        });

        maxButton.setOnAction(e -> { //max button handler
            if (max == Integer.MIN_VALUE)
                showMessage("No numbers entered yet!", "error");
            else
                showMessage("The maximum is "+max+".", "info");
        });

        minButton.setOnAction(e -> { //min button handler
            if (min == Integer.MAX_VALUE)
                showMessage("No numbers entered yet!", "error");
            else
                showMessage("The minimum is "+min+".", "info");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void showMessage(String message, String type) { //function to display message to user
        Alert a = new Alert(AlertType.INFORMATION);
        if (type.equals("error"))
            a = new Alert(AlertType.ERROR);
        a.setContentText(message);
        a.setHeaderText(" ");
        a.setTitle("");
        a.show();
    }

    private boolean isInt(String f) { //function to check if user string is valid integer
        try
        {
            Integer.parseInt(f);
            return true;
        }
        catch (NumberFormatException e)
        {
            showMessage("Data Entry Error","error");
            input.setPromptText("Enter an integer...");
            return false;
        }
    }
}
