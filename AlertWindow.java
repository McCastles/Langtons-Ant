import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


class AlertWindow {

    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 200;
    private Text messageArea;
    private Stage window;
    private VBox content;

    /*CONSTRUCTOR*/
    AlertWindow(String message)
    {
        window = new Stage();
        window.setAlwaysOnTop(true);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Something's Wrong!");
        messageArea = new Text(message);
        messageArea.setStyle("-fx-font-size: 20");

        content = new VBox(20);
        content.setPadding(new Insets(10));
        content.setAlignment(Pos.CENTER);

    }

    /*ASK IF THE USER IS SURE*/
    static void popUpClose (AlertWindow alertWindow)
    {
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(yesButton, noButton);
        buttons.setAlignment(Pos.CENTER);

        yesButton.setOnAction(e -> System.exit(0));
        noButton.setOnAction(e -> alertWindow.window.close());

        alertWindow.content.getChildren().addAll(alertWindow.messageArea, buttons);

        alertWindow.window.setScene(
                new Scene( alertWindow.content, alertWindow.WINDOW_WIDTH, alertWindow.WINDOW_HEIGHT) );
        alertWindow.window.show();
    }

    /*POP UP MESSAGE*/
    static void popUp (AlertWindow alertWindow)
    {
        Button okButton = new Button("Return");

        alertWindow.content.getChildren().addAll(alertWindow.messageArea, okButton);

        okButton.setOnAction(e -> alertWindow.window.close());

        alertWindow.window.setScene(
                new Scene( alertWindow.content, alertWindow.WINDOW_WIDTH, alertWindow.WINDOW_HEIGHT) );
        alertWindow.window.show();
    }

}
