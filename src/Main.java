import javafx.application.Application;
import javafx.scene.control.Alert;
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

    static void popUpMessage(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.setTitle("Something went wrong");
        alert.setHeaderText("");
        alert.showAndWait();
    }



    public static void main(String[] args) {
        launch(args);
    }


}




