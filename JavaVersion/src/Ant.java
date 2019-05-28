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
    private Color color;
    private Dir dir;

    /* CLONING CONSTRUCTOR */
    Ant(Ant ant)
    {
        this.id = ant.id;
        this.isAlive = ant.isAlive;
        this.x = ant.x;
        this.y = ant.y;
        this.color = ant.color;
        this.dir = ant.dir;
    }

    /* CONSTRUCTOR */
    Ant(String id, boolean isAlive, int x, int y, Color color, Dir dir)
    {
        this.id = id;
        this.isAlive = isAlive;
        this.x = x;
        this.y = y;
        this.color = color;
        this.dir = dir;
    }

    /*PAINT THE CELL ON (X,Y)*/
    void paintCell(GridPane pane, int x, int y, Color color)
    {
        Rectangle rectangle = new Rectangle();

        for (Node node : pane.getChildren())
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y)
                rectangle = (Rectangle) node;

        rectangle.setFill(color);
    }

    /*MOVE FOR THE NEXT STEP*/
    void antStep(Board board, GridPane pane)
    {
        if (board.getTableElement(x,y) == 0) {

            board.setTableElement(x,y,1);
            paintCell(pane, x, y, color);
            antTurn(Dir.RIGHT); }

        else {
            board.setTableElement(x,y,0);
            paintCell(pane, x, y, Color.WHITE);
            antTurn(Dir.LEFT); }

        antMove(board);
    }

    /*CHANGE POSITION OF THE ANT*/
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

    /*CHANGE DIRECTION OF THE ANT*/
    private void antTurn(Dir direction)
    {
        if (direction == Dir.RIGHT)
            switch (dir)
            {
                case UP: dir = Dir.RIGHT; break;
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

    /*COLOR TO STRING*/
    static String colorToString(Color color)
    {
        if (color == Color.BLACK) return "Black";
        if (color == Color.RED) return "Red";
        if (color == Color.GREEN) return "Green";
        if (color == Color.BLUE) return "Blue";
        if (color == Color.YELLOW) return "Yellow";
        if (color == Color.ORANGE) return "Orange";

        return "Black";
    }

    /*STRING TO COLOR*/
    static Color assignColor(String color)
    {
        switch (color)
        {
            case "Black": return Color.BLACK;
            case "Blue": return Color.BLUE;
            case "Red": return Color.RED;
            case "Green": return Color.GREEN;
            case "Yellow": return Color.YELLOW;
            case "Orange": return Color.ORANGE;
        }

        return Color.BLACK;
    }

    /*ACCESSORS AND MUTATORS*/
    String getId() {
        return id;
    }

    Dir getDir()
    {
        return dir;
    }

    boolean getIsAlive() {
        return isAlive;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Color getColor() {
        return color;
    }

}
