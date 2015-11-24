import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GobangGame {
//  定义到达赢条件的棋子数目
 private final int WIN_COUNT=5;
//定义用户输入的坐标
 private  int posX=0;
 private  int posY=0;
//定义棋盘
 private Chessboard chessboard;
//构造函数
 public GobangGame(Chessboard chessboard)
 {
     this.chessboard=chessboard;
 }
 /*检查用户输入是否合理    */
 public boolean isValid(String inputStr)
 {
     // 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
     String [] posStrArr=inputStr.split(",");
     try {
         posX=Integer.parseInt(posStrArr[0])-1;

         posY=Integer.parseInt(posStrArr[1])-1;
          
     } catch (NumberFormatException e) {
         // TODO: handle exception
         chessboard.printBoard();
     System.out.println("请输入（数字，数字）格式");
     return false;
     }
     //检查输入的数字是否在允许范围里
     if(posX<1||posX>chessboard.BOARD_SIZE||posY<1||posY>chessboard.BOARD_SIZE)
     {
         chessboard.printBoard();
         System.out.println("输入的坐标超过棋盘范围，请重新输入！");
         return false;
     }
     //检查输入的位置是否有棋子
     String [][] board=chessboard.getBoard();
     if(board[posX][posY]!="+")
     {
         chessboard.printBoard();
         System.out.println("输入的位置已经有棋子，请输入到其他位置！");
         return false;
     }
     return true;
      
 }
 /**
  * 开始下棋
  * @param true 为游戏结束
  */
 public void start() throws Exception
 {
     boolean isOver=false;  //isOver标记游戏是否胜利
     //初始化和打印棋盘
     chessboard.initBoard();
     chessboard.printBoard();
     //获取从键盘输入
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     String inputStr=null;
     while ((inputStr=br.readLine())!=null)
     {
         isOver=false;
         if(!isValid(inputStr))
         {//如果不合法，重新输入
             continue;
         }
     String chessman=Chessman.BLACK.getChessman();
     chessboard.setBoard(posX, posY, chessman);
     //判断用户是否赢了
     if(isWon(posX,posY,chessman))
     {
         isOver=true;
     }else
         //计算机的位置随机取
     {
         int[] computerPosArr=computerDo();
         chessman=Chessman.WHITE.getChessman();
         chessboard.setBoard(computerPosArr[0], computerPosArr[1], chessman);
         //判断是否计算机赢了
         if(isWon(computerPosArr[0], computerPosArr[1], chessman))
         {
             isOver=true;
         }
     }
     //如果胜者产生，是否还要继续游戏
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
     System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
     }
 }
 /**
  * 是否重新下棋
  * @param  chessman "●"为用户；"○"为计算机
  * @return 开始返回true, 返回返回false；
  */
 public boolean isReplay(String chessman) throws Exception
 {
     chessboard.printBoard();
     String message=chessman.equals(Chessman.BLACK.getChessman())? "恭喜，你赢了":"很遗憾，你输了";
     System.out.println(message+"再来下一局(y/n)");
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     if(br.readLine().equals("y"))
     {
         //开始新的一局
         return true;
     }
     return false;
      
 }
  
 /**
  * 计算机随机下棋
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
  * 判断输赢
  * @param ico 棋子类型
  * @return 如果有相邻五颗棋子在一条直线上则返回true，否则返回false
  */
 public boolean isWon(int posX,int posY, String ico)
 {  //直线起点和终点坐标
     int startX=0;
     int startY=0;
     int endX=chessboard.BOARD_SIZE-1;
     int endY=endX;
     //同一条直线上相邻棋子的数目
     int sameCount=0;
     int temp=0;
      
     //计算起点最小坐标
     temp=posX-WIN_COUNT+1;
     startX=temp<0?0:temp;
     temp=posY-WIN_COUNT+1;
     startY=temp<0?0:temp;
     //计算终点最大坐标
     temp=posX+chessboard.BOARD_SIZE-1;
     endX=temp>chessboard.BOARD_SIZE?chessboard.BOARD_SIZE:temp;
     temp=posY+chessboard.BOARD_SIZE-1;
     endY=temp>chessboard.BOARD_SIZE?chessboard.BOARD_SIZE:temp;
      
     //从左到右计算相同棋子的数目
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
         // 从上到下计算相同相邻棋子的数目
         for (int i = startX; i < endX; i++) {
             if (board[i][posY] == ico && board[i + 1][posY] == ico) {
                 sameCount++;
             } else if (sameCount != WIN_COUNT - 1) {
                 sameCount = 0;
             }
         }
     }
     if (sameCount == 0) {
         // 从左上到右下计算相同相邻棋子的数目
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

