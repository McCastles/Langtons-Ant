import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VisualWindow
{
    final private int RATE = 3;
    private Stage window;
    private VBox content;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Ant> antList;
    private Board board;


    public VisualWindow(int width, int height)
    {
        /*ANT LIST*/
        antList = new ArrayList<>(Main.getAntListCurrent());

        /*BOARD*/
        board = new Board(width, height);

        /*STAGE*/
        window = new Stage();
        window.setTitle("Visual Window");
        window.setResizable(false);
        /*
        * TODO set min visualwindow size
        * TODO check if ant's pos more than window
        * TODO finished controlwindow => finished all
        * TODO
        * TODO
        * */

        /*VBox*/
        content = new VBox(10);
        content.setPadding(new Insets(10));
        //visualWindowContent.setStyle();
//        content.setPadding(new Insets(0,0,50,0));

        canvas = new Canvas(RATE * width, RATE * height);
        StackPane canvasContainer = new StackPane(canvas);
        canvasContainer.setStyle("-fx-border-width: 1; -fx-border-color: gainsboro;");


//        System.out.println("Created a VisualWindow: " + canvas.getHeight() + " " + canvas.getWidth());
        gc = canvas.getGraphicsContext2D();

        content.getChildren().addAll(canvasContainer, new Button("O hi Mark"));
        window.setScene(new Scene(content, RATE * width + 50, RATE * height + 50));
        window.show();

//        try {System.out.print("->"); System.in.read();}catch (IOException e){}

    }


}
