import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static VisualWindow visualWindow;
    private static ControlWindow controlWindow;

    @Override
    public void start(Stage primaryStage) {

        controlWindow = new ControlWindow(primaryStage);
    }

    static ControlWindow getControlWindow() {
        return controlWindow;
    }

    static void setVisualWindow(VisualWindow newVisualWindow)
    { visualWindow = newVisualWindow; controlWindow.getStartButton().setDisable(true); }



    public static void main(String[] args) {
        launch(args);
    }


}




