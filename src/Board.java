
public class Board {
    private int width;
    private int height;
    int[][] table;

    public Board(int width, int height)
    {
        this.height = height;
        this.width = width;
        this.table = new int[height][width];

        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                table[i][j] = 0;
//        System.out.println(Arrays.deepToString(table));

    }

    //TODO put ant on board

}
