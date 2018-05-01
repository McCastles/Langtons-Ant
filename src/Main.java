import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    private static VisualWindow visualWindow;
    private static ArrayList<Ant> antListCurrent = new ArrayList<>();
    private static ControlWindow controlWindow;

    @Override
    public void start(Stage primaryStage) {

        controlWindow = new ControlWindow(primaryStage);

        //TODO minus button
        controlWindow.getPlus().setOnAction(e ->
            AntCreatorWindow.createDialogWindow(controlWindow, antListCurrent)
        );



        /*
         * TODO set min visualwindow size
         * TODO check if ant's pos more than window
         * TODO finished controlwindow => finished all
         * TODO CSSMain.getAntListCurrent()
         * TODO status bar in VisualWindow
         * */

    }

    public static ControlWindow getControlWindow() {
        return controlWindow;
    }

    public static void setVisualWindow(VisualWindow newVisualWindow)
    { visualWindow = newVisualWindow; controlWindow.getStartButton().setDisable(true); }

    public static ArrayList<Ant> getAntListCurrent() { return antListCurrent; }

    public static void main(String[] args) {
        launch(args);
    }




}




