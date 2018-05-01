import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ant{

    public enum Dir {UP, RIGHT, DOWN, LEFT}

    private String id;
    private boolean isAlive;
    private int x;
    private int y;
    private String color; //TODO change String to Color
    private Dir dir;

    /* CLONING CONSTRUCTOR */
    public Ant(Ant ant)
    {
        this.id = ant.id;
        this.isAlive = ant.isAlive;
        this.x = ant.x;
        this.y = ant.y;
        this.color = ant.color;
        this.dir = ant.dir;
    }

    /* CONSTRUCTOR */
    public Ant(String id, boolean isAlive, int x, int y, String color, Dir dir)
    {
        this.id = id;
        this.isAlive = isAlive;
        this.x = x;
        this.y = y;
        this.color = color;
        this.dir = dir;
    }

    public void paintCell(GridPane pane, int x, int y, String color)
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
            case "White": rectangle.setFill(Color.WHITE); break;
        }
    }

    public void antStep(Board board, GridPane pane)
    {
        if (board.getTableElement(x,y) == 0) {

            board.setTableElement(x,y,1);
            paintCell(pane, x, y, color);
            antTurn(Dir.RIGHT); }

        else {
            board.setTableElement(x,y,0);
            paintCell(pane, x, y, "White");
            antTurn(Dir.LEFT); }

        antMove(board);
    }


    private void antMove(Board board)
    {
        switch (dir)
        {
            case UP: if (y == 0) isAlive = false; else y--; break;
            case RIGHT: if (x == (board.getWidth()-1)) isAlive = false; else x++; break;
            case DOWN: if (y == (board.getHeight()-1)) isAlive = false; else y++; break;
            case LEFT: if (x == 0) isAlive = false; else x--; break;
        }

    }

    private void antTurn(Dir direction)
    {
        if (direction == Dir.RIGHT)
            switch (dir)
            {
                case UP: dir = Dir.RIGHT;; break;
                case RIGHT: dir = Dir.DOWN; break;
                case DOWN: dir = Dir.LEFT; break;
                case LEFT: dir = Dir.UP; break;
            }
        else
            switch (dir)
            {
                case UP: dir = Dir.LEFT; break;
                case RIGHT: dir = Dir.UP; break;
                case DOWN: dir = Dir.RIGHT; break;
                case LEFT: dir = Dir.DOWN; break;
            }
    }


    public static void assignColor(String color, Node node)
    {
        switch (color)
        {
            case "Black": node.setStyle("-fx-background-color: gray"); break;
            case "Blue": node.setStyle("-fx-background-color: CornflowerBlue"); break;
            case "Red": node.setStyle("-fx-background-color: red"); break;
            case "Green": node.setStyle("-fx-background-color: SpringGreen "); break;
            case "Yellow": node.setStyle("-fx-background-color: yellow"); break;
            case "Orange": node.setStyle("-fx-background-color: orange"); break;
        }
    }

    public String getId() {
        return id;
    }

    public Dir getDir()
    {
        return dir;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

}
