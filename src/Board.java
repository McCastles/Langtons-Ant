
public class Board {
    private int width;
    private int height;
    private int[][] table;

    /*CONSTRUCTOR*/
    public Board(int width, int height)
    {
        this.height = height;
        this.width = width;
        this.table = new int[height][width];
    }

    /*ACCESSORS AND MUTATORS*/
    public int getTableElement(int x, int y) {
        return table[x][y];
    }

    public int getHeight() { return height; }

    public int getWidth() {
        return width;
    }

    public void setTableElement(int x, int y, int value) {
        this.table[x][y] = value;
    }



}
