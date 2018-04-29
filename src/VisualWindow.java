import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VisualWindow
{
    final private int CELLSIZE = 6;
    private Stage window;
    private VBox content;
    private GridPane pane;
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
        window.setTitle("Board Window");
        window.setResizable(false);

        /*VBox*/
        content = new VBox(10);
        content.setPadding(new Insets(10));

        /*SET GRAY GRID*/
        pane = new GridPane();
        pane.setStyle("-fx-border-width: 1; -fx-border-color: Gainsboro; -fx-background-color: Gainsboro");
        pane.setVgap(1);
        pane.setHgap(1);

        /*FILL THE BOARD WITH 0'S AND THE PANE WITH WHITE RECTANGLES*/
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {

                board.setTableElement(i, j,0);

                Rectangle rectangle = new Rectangle(CELLSIZE, CELLSIZE);
                rectangle.setFill(Color.WHITE);
                pane.add(rectangle, i, j); }

        /*MARK THE INITIATE POSITION OF THE ANTS*/
        for (Ant a: antList)
        {
//            board.setTableElement(a.getX(), a.getY(), 1);
            paintCell(pane, a.getX(), a.getY(), a.getColor());
        }

        /*ADDING BUTTONS*/
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button continueButton = new Button("Continue");
        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(startButton, stopButton, continueButton);

        /*APPLYING EVERYTHING ON THE SCREEN*/
        content.getChildren().addAll(pane, buttons);
        window.setScene(new Scene(content, (CELLSIZE+1) * width + 20, CELLSIZE * height + 200));
        window.show();

    }


    private void paintCell(GridPane pane, int x, int y, String color)
    {
        Rectangle rectangle = new Rectangle();

        for (Node node : pane.getChildren())
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y)
                rectangle = (Rectangle) node;
        switch (color)
        {
            case "Black": rectangle.setFill(Color.BLACK); break;
            case "Blue": rectangle.setFill(Color.BLUE); break;
            case "Red": rectangle.setFill(Color.RED); break;
            case "Green": rectangle.setFill(Color.GREEN); break;
            case "Yellow": rectangle.setFill(Color.YELLOW); break;
            case "Orange": rectangle.setFill(Color.ORANGE); break;
        }
    }



}
