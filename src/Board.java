
public class Board {
    private int width;
    private int height;
    int[][] table;

    public Board(int width, int height)
    {
        this.height = height;
        this.width = width;
        this.table = new int[height][width];
//        System.out.println(Arrays.deepToString(table));

    }

    public void setTableElement(int x, int y, int value) {
        this.table[x][y] = value;
    }


    //TODO put ant on board

}
