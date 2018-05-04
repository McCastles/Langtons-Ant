import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static VisualWindow visualWindow;
    private static ControlWindow controlWindow;

    @Override
    public void start(Stage primaryStage) {

        controlWindow = new ControlWindow(primaryStage);

        //TODO minus button


        /*
         * TODO get rid of Main class
         * TODO set min visualwindow size
         * TODO finished controlwindow => finished all
         * TODO status bar in VisualWindow
         * */

    }

    public static ControlWindow getControlWindow() {
        return controlWindow;
    }

    public static VisualWindow getVisualWindow() {
        return visualWindow;
    }

    public static void setVisualWindow(VisualWindow newVisualWindow)
    { visualWindow = newVisualWindow; controlWindow.getStartButton().setDisable(true); }



    public static void main(String[] args) {
        launch(args);
    }


}




