import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class ControlWindow
{
    private final int MAX_SIZE = 128;
    private final int MIN_SIZE = 32;
    private final int WINDOW_WIDTH = 580;
    private final int WINDOW_HEIGHT = 660;
    private Stage window;
    private GridPane content;
    private Label antsLabel;
    private Button plus;
    private Button minus;
    private ListView<String> list;
    private Label infoLabel;
    private ArrayList<String> details;
    private GridPane subMenu;
    private ListView<String> infoBlock;
    private Label stepsLabel;
    private TextField stepsInput;
    private Label wLabel;
    private TextField wInput;
    private Label hLabel;
    private TextField hInput;
    private Button startButton;
    private static ArrayList<Ant> antListCurrent;


    private void operateGUI()
    {
        /*SET TITLE*/
        window.setTitle("Control Window");
        window.setResizable(false);

        /*SET CONTENT PANE*/
        content.setPadding(new Insets(0,5,5,5));
        content.setVgap(8);
        content.setHgap(10);

        /*ADDING ANTS LABEL*/
        content.add(antsLabel,1,1, 2, 1);

        /*ADDING PLUS BUTTON*/
        plus.setMinSize(30,30);
        content.add(plus,3,1);

        /*ADDING MINUS BUTTON*/
        minus.setMinSize(30,30);
        content.add(minus,4,1);

        /*ADDING ANT LIST*/
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
           updateInfoBox(list.getSelectionModel().getSelectedIndex(),
                   antListCurrent)
        );

        content.add(list, 1,2,4,7);

        /*ADDING INFO LABEL*/
        content.add(infoLabel, 5,1);

        /*ADDING PARAMETERS TO LIST*/
        details.add("Ant's ID: ");
        details.add("Is alive: ");
        details.add("Ant's color: ");
        details.add("Ant's direction: ");
        details.add("Ant's starting X: ");
        details.add("Ant's starting Y: ");

        /*ADDING INFO BLOCK*/
        content.add(infoBlock,5,2);

        /*SETTING SUB PANE*/
        subMenu.setPadding(new Insets(10));
        subMenu.setHgap(10);
        subMenu.setVgap(8);
        subMenu.setStyle("-fx-border-color: gainsboro; -fx-border-width: 1");

        /*ADDING STEPS LABEL*/
        subMenu.add(stepsLabel,0,0);

        /*ADDING STEPS INPUT*/
        stepsInput.setPromptText("can't be 0");
        subMenu.add(stepsInput, 1,0);

        /*ADDING WIDTH LABEL*/
        subMenu.add(wLabel,0,1);

        /*ADDING WIDTH INPUT*/
        wInput.setPromptText(String.valueOf(MIN_SIZE) + " - " + String.valueOf(MAX_SIZE));
        subMenu.add(wInput, 1,1);

        /*ADDING HEIGHT LABEL*/
        subMenu.add(hLabel,0,2);

        /*ADDING HEIGHT INPUT*/
        hInput.setPromptText(String.valueOf(MIN_SIZE) + " - " + String.valueOf(MAX_SIZE));
        subMenu.add(hInput, 1,2);


        /*ADDING BUTTON*/
        startButton.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        subMenu.add(startButton, 1,3);

        /*APPLYING TO ORIGINAL PANE*/
        content.add(subMenu, 5,7);

        /*ANIMATE CLOSE, PLUS/MINUS AND CREATE BUTTON*/
        animateButtons();
    }

    /*ANIMATE CLOSE, PLUS/MINUS AND CREATE BUTTON*/
    private void animateButtons()
    {
        window.setOnCloseRequest(e -> {
            e.consume();
//            AlertWindow.popUpClose(new AlertWindow("Are you sure you wan't to exit?"));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit the Langton's Ant Simulator?");
            alert.setTitle("Something went wrong");
            alert.setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                System.exit(0);
            } else {
                alert.close();
            }
        });

        plus.setOnAction(e -> {

            if ( (wInput.getText().equals("")) || (hInput.getText().equals("")) ) {
                Main.popUpMessage("You must enter the size of the board first.");
                return; }

            AntCreatorWindow.createDialogWindow(
                    Main.getControlWindow());
        });

        minus.setOnAction(e ->{
            if (antListCurrent.isEmpty()) {
                Main.popUpMessage("You haven't created any Ants yet.");

                return; }

            int deletedId  = list.getSelectionModel().getSelectedIndex();
            antListCurrent.remove( deletedId );
            list.getItems().remove( list.getSelectionModel().getSelectedIndex() );

        });

        startButton.setOnAction(e -> {
            try {

                int tmpW = Integer.parseInt(wInput.getText());
                int tmpH = Integer.parseInt(hInput.getText());
                int tmpSteps = Integer.parseInt(stepsInput.getText());

                if ((tmpH < MIN_SIZE)||(tmpH > MAX_SIZE)) {
                    Main.popUpMessage("The values must be within "+MIN_SIZE+"-"+MAX_SIZE+" range.");
                    return; }
                if ((tmpW < MIN_SIZE)||(tmpW > MAX_SIZE)) {
                    Main.popUpMessage("The values must be within "+MIN_SIZE+"-"+MAX_SIZE+" range.");
                    return; }

                if (antListCurrent.isEmpty()) {
                    Main.popUpMessage("You should add Ants first.");
                    return; }

                ArrayList<String> badList = new ArrayList<>();
                for (Ant ant : antListCurrent)
                    if ( (ant.getX() > tmpW)||(ant.getY() > tmpH) )
                        badList.add(ant.getId());
                if (!badList.isEmpty()) {
                    Main.popUpMessage("The following Ants cannot be placed on this board: " + badList.toString());
                    return; }

                Main.setVisualWindow(new VisualWindow(tmpW, tmpH, tmpSteps));
                System.out.printf("Created new Visual Window, size: %d by %d cells\n", tmpW, tmpH);
            }
            catch (NumberFormatException e1) {
                Main.popUpMessage("You must enter the values above first."); }

                /*TODO colors as string
                * TODO 0-49 cells*/

        });

    }


    /*UPDATE INFO BOX OR CLEAR IF EMPTY*/
    private void updateInfoBox(int id, ArrayList<Ant> antList) {

        if (antList.isEmpty()) {
            infoBlock.getItems().clear();
            return; }

        Ant proto = antList.get(id);
        infoBlock.getItems().clear();
        infoBlock.getItems().addAll(
                details.get(0) + proto.getId(),
                details.get(1) + proto.getIsAlive(),
                details.get(2) + Ant.colorToString(proto.getColor()),
                details.get(3) + proto.getDir(),
                details.get(4) + (proto.getX()+1) ,
                details.get(5) + (proto.getY()+1)
        );
    }



    /*CONSTRUCTOR*/
    ControlWindow(Stage primaryStage) {

        antListCurrent = new ArrayList<>();

        window = primaryStage;

        content = new GridPane();

        antsLabel = new Label("Ants:");

        plus = new Button("+");

        minus = new Button("-");

        list = new ListView<>();

        infoLabel = new Label("Info:");

        details = new ArrayList<>();

        infoBlock = new ListView<>();

        subMenu = new GridPane();

        stepsLabel = new Label("Steps:");

        stepsInput = new TextField();

        wLabel = new Label("Board Width:");

        wInput = new TextField();

        hLabel = new Label("Board Height:");

        hInput = new TextField();

        startButton = new Button("Create New Window");

        operateGUI();
        window.setScene(new Scene(content, WINDOW_WIDTH, WINDOW_HEIGHT));
        window.show();
        System.out.println("Created new Control Window");

    }

    /*ADDING ANT'S TITLE TO VISIBLE LIST*/
    void addToAntList(String id) {

        this.list.getItems().add(id);
        this.list.getSelectionModel().selectLast();
    }

    /*ACCESSORS AND MUTATORS*/
    static ArrayList<Ant> getAntListCurrent() { return antListCurrent; }

    TextField getwInput() {
        return wInput;
    }

    TextField gethInput() {
        return hInput;
    }

    Button getStartButton() { return startButton; }

}
