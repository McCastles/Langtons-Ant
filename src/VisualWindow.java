import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

public class VisualWindow {
    final private static int CELLSIZE = 6;
    private int steps;
    private Stage window;
    private GridPane pane;
    private ArrayList<Ant> antList;
    private Board board;
    private Button startButton;
    private Button stopButton;
    private volatile boolean threadRunning = false;


    VisualWindow(int width, int height, int stepsLimit) {

        /*NULLIFY STEPS COUNTER*/
        steps = 0;

        /*ANT LIST*/
        antList = new ArrayList<>();
        for (Ant ant : ControlWindow.getAntListCurrent())
            antList.add( new Ant(ant) );

        /*BOARD*/
        board = new Board(width, height);

        /*STAGE*/
        window = new Stage();
        window.setTitle("Board Window");
        window.setResizable(false);
        window.setOnCloseRequest(e ->
        {
            threadRunning = false;
            Main.getControlWindow().getStartButton().setDisable(false);
            window.close();
        });

        /*VBox*/
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        /*SET GRAY GRID*/
        pane = new GridPane();
        pane.setStyle("-fx-border-width: 1; -fx-border-color: Gainsboro; -fx-background-color: Gainsboro");
        pane.setVgap(1);
        pane.setHgap(1);

        /*FILL THE BOARD WITH 0'S AND THE PANE WITH WHITE RECTANGLES*/
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {

                board.setTableElement(i, j, 0);

                Rectangle rectangle = new Rectangle(CELLSIZE, CELLSIZE);
                rectangle.setFill(Color.WHITE);
                pane.add(rectangle, i, j);
            }

        /*MARK THE INITIATE POSITION OF THE ANTS*/
        for (Ant ant : antList)
            ant.paintCell(pane, ant.getX(), ant.getY(), ant.getColor());


        /*ADDING BUTTONS*/
        startButton = new Button("Start");
        stopButton = new Button("Stop");

        stopButton.setOnAction(e -> {
            stopButton.setDisable(true);
            startButton.setDisable(false);
            stopGameLoop();});
        stopButton.setDisable(true);

        startButton.setOnAction(e -> {
            stopButton.setDisable(false);
            startButton.setDisable(true);
            beginGameLoop(stepsLimit);});

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(startButton, stopButton);

        /*APPLYING EVERYTHING ON THE SCREEN*/
        content.getChildren().addAll(pane, buttons);
        window.setScene(new Scene(content, (CELLSIZE + 1) * width + 20, CELLSIZE * height + 200));
        window.show();

    }


    /* lambda? */
    private void beginGameLoop(int stepsLimit)
    {
        Thread t = new Thread (
            new Runnable() {

            @Override
            public void run()
            {
                threadRunning = true;
                while(threadRunning){

                    for (Ant ant: antList)
                        if (ant.getIsAlive())
                            ant.antStep(board, pane);

                    steps++;

                    if (steps == stepsLimit)
                    {
                        startButton.setDisable(true);
                        stopButton.setDisable(true);
                        threadRunning = false;
                    }

                    /*try
                        { Thread.sleep(1000); }
                    catch (InterruptedException e)
                        { System.out.println("Interrupted!"); }*/
                }
            }
        });
        t.start();
    }


    private void stopGameLoop()
    { threadRunning = false; System.out.println("Stopped");}

}
