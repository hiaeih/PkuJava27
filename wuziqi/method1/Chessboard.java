
public class Chessboard {
	  //定义一个二维数组来充当棋盘
    private String [][] board;
    //定义棋盘大小
    public static final int BOARD_SIZE=22;
    public void initBoard()
    {
        //初始化棋盘
        board=new String[BOARD_SIZE][BOARD_SIZE];
        //把每个元素输出为“+”，在控制台输出
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
                board[i][j]="+";
        }
    }
     
    /*在控制台输出棋盘的方法*/
    public void printBoard()
    {
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
                //打印后不换行
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }
     
    /*给棋盘的位置赋值*/
    public void setBoard(int posX, int posY, String chessman)
    {
        this.board[posX][posY]=chessman;
    }
    /*返回棋盘*/
    public String [][] getBoard()
    {
        return this.board;
         
    }
}
