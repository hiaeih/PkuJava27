
public class Chessboard {
	  //����һ����ά�������䵱����
    private String [][] board;
    //�������̴�С
    public static final int BOARD_SIZE=22;
    public void initBoard()
    {
        //��ʼ������
        board=new String[BOARD_SIZE][BOARD_SIZE];
        //��ÿ��Ԫ�����Ϊ��+�����ڿ���̨���
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
                board[i][j]="+";
        }
    }
     
    /*�ڿ���̨������̵ķ���*/
    public void printBoard()
    {
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
                //��ӡ�󲻻���
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }
     
    /*�����̵�λ�ø�ֵ*/
    public void setBoard(int posX, int posY, String chessman)
    {
        this.board[posX][posY]=chessman;
    }
    /*��������*/
    public String [][] getBoard()
    {
        return this.board;
         
    }
}
