import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GobangGame {
//  ���嵽��Ӯ������������Ŀ
 private final int WIN_COUNT=5;
//�����û����������
 private  int posX=0;
 private  int posY=0;
//��������
 private Chessboard chessboard;
//���캯��
 public GobangGame(Chessboard chessboard)
 {
     this.chessboard=chessboard;
 }
 /*����û������Ƿ����    */
 public boolean isValid(String inputStr)
 {
     // ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
     String [] posStrArr=inputStr.split(",");
     try {
         posX=Integer.parseInt(posStrArr[0])-1;

         posY=Integer.parseInt(posStrArr[1])-1;
          
     } catch (NumberFormatException e) {
         // TODO: handle exception
         chessboard.printBoard();
     System.out.println("�����루���֣����֣���ʽ");
     return false;
     }
     //�������������Ƿ�������Χ��
     if(posX<1||posX>chessboard.BOARD_SIZE||posY<1||posY>chessboard.BOARD_SIZE)
     {
         chessboard.printBoard();
         System.out.println("��������곬�����̷�Χ�����������룡");
         return false;
     }
     //��������λ���Ƿ�������
     String [][] board=chessboard.getBoard();
     if(board[posX][posY]!="+")
     {
         chessboard.printBoard();
         System.out.println("�����λ���Ѿ������ӣ������뵽����λ�ã�");
         return false;
     }
     return true;
      
 }
 /**
  * ��ʼ����
  * @param true Ϊ��Ϸ����
  */
 public void start() throws Exception
 {
     boolean isOver=false;  //isOver�����Ϸ�Ƿ�ʤ��
     //��ʼ���ʹ�ӡ����
     chessboard.initBoard();
     chessboard.printBoard();
     //��ȡ�Ӽ�������
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     String inputStr=null;
     while ((inputStr=br.readLine())!=null)
     {
         isOver=false;
         if(!isValid(inputStr))
         {//������Ϸ�����������
             continue;
         }
     String chessman=Chessman.BLACK.getChessman();
     chessboard.setBoard(posX, posY, chessman);
     //�ж��û��Ƿ�Ӯ��
     if(isWon(posX,posY,chessman))
     {
         isOver=true;
     }else
         //�������λ�����ȡ
     {
         int[] computerPosArr=computerDo();
         chessman=Chessman.WHITE.getChessman();
         chessboard.setBoard(computerPosArr[0], computerPosArr[1], chessman);
         //�ж��Ƿ�����Ӯ��
         if(isWon(computerPosArr[0], computerPosArr[1], chessman))
         {
             isOver=true;
         }
     }
     //���ʤ�߲������Ƿ�Ҫ������Ϸ
     if(isOver)
     {
         if(isReplay(chessman))
         {
             chessboard.initBoard();
             chessboard.printBoard();
             continue;
         }
         break;
     }
     chessboard.printBoard();
     System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
     }
 }
 /**
  * �Ƿ���������
  * @param  chessman "��"Ϊ�û���"��"Ϊ�����
  * @return ��ʼ����true, ���ط���false��
  */
 public boolean isReplay(String chessman) throws Exception
 {
     chessboard.printBoard();
     String message=chessman.equals(Chessman.BLACK.getChessman())? "��ϲ����Ӯ��":"���ź���������";
     System.out.println(message+"������һ��(y/n)");
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     if(br.readLine().equals("y"))
     {
         //��ʼ�µ�һ��
         return true;
     }
     return false;
      
 }
  
 /**
  * ������������
  * 
  */
 public int[] computerDo()
 {
     int posX=(int)(Math.random()*(chessboard.BOARD_SIZE-1));
     int posY=(int)(Math.random()*(chessboard.BOARD_SIZE-1));
     String[][] board=chessboard.getBoard();
     while(board[posX][posY]!="+")
     {
          posX=(int)(Math.random()*(chessboard.BOARD_SIZE-1));
          posY=(int)(Math.random()*(chessboard.BOARD_SIZE-1));
     }
     int [] result={posX,posY};
     return result;
      
 }
 /**
  * �ж���Ӯ
  * @param ico ��������
  * @return ������������������һ��ֱ�����򷵻�true�����򷵻�false
  */
 public boolean isWon(int posX,int posY, String ico)
 {  //ֱ�������յ�����
     int startX=0;
     int startY=0;
     int endX=chessboard.BOARD_SIZE-1;
     int endY=endX;
     //ͬһ��ֱ�����������ӵ���Ŀ
     int sameCount=0;
     int temp=0;
      
     //���������С����
     temp=posX-WIN_COUNT+1;
     startX=temp<0?0:temp;
     temp=posY-WIN_COUNT+1;
     startY=temp<0?0:temp;
     //�����յ��������
     temp=posX+chessboard.BOARD_SIZE-1;
     endX=temp>chessboard.BOARD_SIZE?chessboard.BOARD_SIZE:temp;
     temp=posY+chessboard.BOARD_SIZE-1;
     endY=temp>chessboard.BOARD_SIZE?chessboard.BOARD_SIZE:temp;
      
     //�����Ҽ�����ͬ���ӵ���Ŀ
     String [][] board=chessboard.getBoard();
     for(int i=startY;i<endY;i++)
     {
         if(board[posX][i]==ico&&board[posX][i+1]==ico)
         {
             sameCount++;
         }else if(sameCount!=WIN_COUNT-1)
         {
             sameCount=0;
         }
     }
     if (sameCount == 0) {
         // ���ϵ��¼�����ͬ�������ӵ���Ŀ
         for (int i = startX; i < endX; i++) {
             if (board[i][posY] == ico && board[i + 1][posY] == ico) {
                 sameCount++;
             } else if (sameCount != WIN_COUNT - 1) {
                 sameCount = 0;
             }
         }
     }
     if (sameCount == 0) {
         // �����ϵ����¼�����ͬ�������ӵ���Ŀ
         int j = startY;
         for (int i = startX; i < endX; i++) {
             if (j < endY) {
                 if (board[i][j] == ico && board[i + 1][j + 1] == ico) {
                     sameCount++;
                 } else if (sameCount != WIN_COUNT - 1) {
                     sameCount = 0;
                 }
                 j++;
             }
         }
     }
     return sameCount==WIN_COUNT-1?true:false;
      
 }
 public static void main(String[]args) throws Exception
 {
     GobangGame gb=new GobangGame(new Chessboard());
     gb.start();
 }
}

