import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

/*TODO
 * all fields in window must be filled
 * BUG: duplicate names
 *
 * */

public class AntCreatorWindow {
    private final static int WINDOW_WIDTH = 400;
    private final static int WINDOW_HEIGHT = 250;

    public static void createDialogWindow(ControlWindow controlWindow, ArrayList antListCurrent)
    {
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Ant Creator");
        dialogWindow.setAlwaysOnTop(true);
        dialogWindow.setResizable(false);
        dialogWindow.initModality(Modality.APPLICATION_MODAL);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(8);
        pane.setHgap(10);

        /*ID*/
        Label idLabel = new Label("Ant's ID:");
        pane.add(idLabel, 0,0);

        TextField idInput = new TextField();
        idInput.setPromptText("identifier");
        pane.add(idInput, 1,0);

        /*ALIVE*/
        Label aliveLabel = new Label("Is Alive:");
        pane.add(aliveLabel, 0,1);

        CheckBox aliveCheck = new CheckBox();
        aliveCheck.setSelected(true);
        pane.add(aliveCheck, 1,1);

        /*COLOR*/
        Label colorLabel = new Label("Color:");
        pane.add(colorLabel, 0,2);

        ChoiceBox<String> colorChoice = new ChoiceBox<>();
        colorChoice.getItems().addAll("Black", "Blue", "Red", "Green", "Yellow", "Orange");
        colorChoice.setValue("Black");
        colorChoice.setStyle("-fx-background-color: gray");
        colorChoice.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue) -> Ant.assignColor(newValue, colorChoice)
        );
        pane.add(colorChoice,1,2);

        /*DIRECTION*/
        Label dirLabel = new Label("Direction:");
        pane.add(dirLabel, 0,3);

        /*DIRECTION RADIO BUTTONS*/
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton upRadioButton = new RadioButton("Up");
        upRadioButton.setToggleGroup(toggleGroup);
        upRadioButton.setSelected(true);
        RadioButton rightRadioButton = new RadioButton("Right");
        rightRadioButton.setToggleGroup(toggleGroup);
        RadioButton downRadioButton = new RadioButton("Down");
        downRadioButton.setToggleGroup(toggleGroup);
        RadioButton leftRadioButton = new RadioButton("Left");
        leftRadioButton.setToggleGroup(toggleGroup);
        toggleGroup.getSelectedToggle().isSelected();

        HBox radioHBox = new HBox(20);
        radioHBox.getChildren().addAll(upRadioButton,
                rightRadioButton, downRadioButton, leftRadioButton);
        pane.add(radioHBox, 1,3);

        /*X COORD*/
        Label xLabel = new Label("Starting x:");
        pane.add(xLabel,0,4);

        TextField xInput = new TextField();
        xInput.setPromptText("x coordinate");
        pane.add(xInput, 1,4);

        /*Y COORD*/
        Label yLabel = new Label("Starting y:");
        pane.add(yLabel,0,6);

        TextField yInput = new TextField();
        yInput.setPromptText("y coordinate");
        pane.add(yInput, 1,6);

        /*OK BUTTON*/
        Button okButton = new Button("Create Ant");
        pane.add(okButton, 1,7);

        okButton.setOnAction(e ->
                {
                    Ant.Dir tmpDir = Ant.Dir.UP;
                    if (rightRadioButton.isSelected()) tmpDir = Ant.Dir.RIGHT;
                    if (downRadioButton.isSelected()) tmpDir = Ant.Dir.DOWN;
                    if (leftRadioButton.isSelected()) tmpDir = Ant.Dir.LEFT;

                    antListCurrent.add( new Ant(
                            idInput.getText(),
                            aliveCheck.isSelected(),
                            Integer.parseInt(xInput.getText()),
                            Integer.parseInt(yInput.getText()),
                            colorChoice.getValue(),
                            tmpDir ));

                    controlWindow.addToAntList(idInput.getText());
                    dialogWindow.close();
                }
        );

        /*APPLY*/
//        pane.getStylesheets().add("ControlWindowStyle.css");
        dialogWindow.setScene(new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT));
        dialogWindow.show();
    }
}
