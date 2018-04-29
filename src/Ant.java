import javafx.scene.Node;



public class Ant {

    public enum Dir {UP, RIGHT, DOWN, LEFT}

    private String id;
    private boolean isAlive;
    private int x;
    private int y;
    private String color;
    private Dir dir;

//    public Ant(boolean isAlive, int x, int y, String color, int dir)
    public Ant(String id, boolean isAlive, int x, int y, String color, Dir dir)
    {
        this.id = id;
        this.isAlive = isAlive;
        this.x = x;
        this.y = y;
        this.color = color;
        this.dir = dir;
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
