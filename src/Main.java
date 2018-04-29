import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {


    private static ArrayList<VisualWindow> visualWindows = new ArrayList<>();
    private static ArrayList<Ant> antListCurrent = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) {

        ControlWindow controlWindow = new ControlWindow(primaryStage);

        //TODO minus button
        controlWindow.getPlus().setOnAction(e ->
            AntCreatorWindow.createDialogWindow(controlWindow, antListCurrent)
        );



       /* PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        Color black = Color.BLACK;

        pixelWriter.setColor(100, 100, black);*/

        /*for (int j = 1; j < 50; j++)
            for (int i = 1; i < 50; i++)
                pixelWriter.setColor(i, j, b);*/
    }

    public static ArrayList<VisualWindow> getVisualWindows() { return visualWindows; }

    public static ArrayList<Ant> getAntListCurrent() { return antListCurrent; }

    public static void main(String[] args) {
        launch(args);
    }




}




