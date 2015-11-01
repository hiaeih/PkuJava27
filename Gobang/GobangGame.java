import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;
	
	private static int flag = 0;

	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}

	/**1,1
	 * 计算机随机下棋
	 */
//	public int[] computerDo() {
//		
//		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		String[][] board = chessboard.getBoard();
//		while (board[posX][posY] != "十") {
//			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		}
//		int[] result = { posX, posY };
//		return result;
//	}
	public int[] computerDo() {
		
		int posX = 0;
		int posY = 0;
		String[][] board = chessboard.getBoard();
		int lastX = chessboard.getLastX();
		int lastY = chessboard.getLastY();
		
		
		int[][] weight = new int[22][22];//存放权值
		//权值数组初始化
		for(int i=0;i<22;i++){
			for(int j=0;j<22;j++){
				weight[i][j] = -100;
			}
		}
		/**
		 *寻找下一步可落棋所有可能位置
		 *双向同时进行（同时获得连续点数0，即sum0）
		 *找到备用位置后，以该位置为中心，顺延原来遍历方向进行单方向查找连续点个数（sum1/sum2），sum0+（sum1/sum2）即为权值
		 */
		
		int sum_0 = 0;//存放连续棋子的个数
		int sum_2 = 0;
		int sum_1 = 0;
		int end = 0;//1->左边找到空点（遍历结束）  2-->右边找到空点（遍历结束）  "○"???
		
		//int iLX=lastX;
		//int jLY=lastY;
		int l = lastY;//横向向左遍历（lastX不变）
		int r = lastY;//横向向右遍历（lastX不变）
		int u = lastX;//纵向向上遍历（lastY不变）
		int d = lastX;//纵向向下遍历（lastY不变）
		int xu = lastX;//斜向左上X坐标
		int yu = lastY;//斜向左上Y坐标
		int xd = lastX;//斜向右下X坐标
		int yd = lastY;//斜向右下Y坐标
		int xr = lastX;//反斜向 右上X坐标
		int yr = lastY;//反斜向 右上Y坐标
		int xl = lastX;//反斜向 左下X坐标
		int yl = lastY;//反斜向 左下Y坐标
		
		int newL_1 = -1;//左新目标（newL_1,newL_2）
		int newL_2 = -1;
		int newR_1 = -1;//右新目标（newR_1,newR_2）
		int newR_2 = -1;
		int newU_1 = -1;//上新目标（newU_1,newU_2）
		int newU_2 = -1;
		int newD_1 = -1;//下新目标（newD_1,newD_2）
		int newD_2 = -1;
		int newLU_1 = -1;//左上新目标（newLU_1,newLU_2）
		int newLU_2 = -1;
		int newRD_1 = -1;//右下新目标（newRD_1,newRD_2）
		int newRD_2 = -1;
		int newRU_1 = -1;//右上新目标（newRU_1,newRU_2）
		int newRU_2 = -1;
		int newLD_1 = -1;//左下新目标（newLD_1,newLD_2）
		int newLD_2 = -1;
		
		//游标定义
		int m = -1;//横向向左遍历
		int n = -1;//横向向右遍历
		int p = -1;//纵向向上遍历
		int q = -1;//纵向向下遍历
		int su = -1;//斜向 左上 x
		int du = -1;//斜向 左上 y
		int sd = -1;//斜向 右下 x
		int dd = -1;//斜向 右下 y
		int tu = -1;//反斜向 右上 x
		int fu = -1;//反斜向 右上 y
		int td = -1;//反斜向 左下 x
		int fd = -1;//反斜向 左下 y
		
		boolean in = true;//控制跳出while循环 
		
		System.out.println("lastX="+lastX+",lastY="+lastY);
		
		//横向开始遍历（单向向左进行）
		while(l>=Math.max(lastY-4,0) && in){
			//int flagL=-1;
			//int flagR=-1;
			//System.out.println("l="+l);
			if(board[lastX][l].equals("●")){//结束后要判断是空点还是已经被计算机占用了，如果计算机占用，放弃该侧继续单向寻找权值！
				l--;
				sum_0++;
				//flagL = 1;
			}else if(board[lastX][l].equals("十")){//是空位置
				//sum_0 = l - lastY;
				newL_1 = lastX;
				newL_2 = l;
				in = false;
			}else {
				in = false;
			}
//			if(board[lastX][r].equals("●") && (end!=2)){
//				r++;
//				sum_0++;
//				flagR = 1;
//			}
//			
//			if(flagL == -1){//左边遍历结束
//				end=1;
//			}
//			if(flagR == -1){//右遍历结束
//				end=2;
//			}
//			if((flagL == -1) && (flagR == -1)){
//				if(board[lastX][l]!="○"){//是空位置
//					newL_1 = lastX;
//					newL_2 = l;
//				}
//				if(board[lastX][r]!="○"){//是空位置
//					newR_1 = lastX;
//					newR_2 = r;
//				}
//				in = false;
//				break;//out????
//			}
//			System.out.println("横向向左遍历没有跳出while循环！！");
		}
		if(sum_0>0){
			sum_0--;
		}
		in = true;
		while(r<=Math.min(lastY+4,21) && in){
			//System.out.println("r="+r);
			if(board[lastX][r].equals("●")){
				r++;
				sum_0++;
			}else if(board[lastX][r].equals("十")){
				newR_1 = lastX;
				newR_2 = r;
				in = false;
//				break;
			}else{
				in = false;
			}
			//System.out.println("in="+in);
			//System.out.println("没有跳出横向while循环！！");
		}
//		System.out.println("横向sum0="+sum_0);
		//进行单向寻找(条件-->为空位置)  sum_1
		//横向向左遍历
		//int sum_1 = 0;
		System.out.println("横向向左遍历目标坐标为newL_1="+newL_1+",newL_2="+newL_2+"  sum_0="+sum_0);
		in = true;
		while(newL_1>=0 && newL_2>=0 && in){//是空位置
			
			if(newL_2==0){//位于左侧竖边界上
				weight[newL_1][newL_2] = sum_0;
				in = false;
			}else{//继续向左遍历，得到sum_1
				m = newL_2-1;//
				while(board[newL_1][m].equals("●") && m>=0){
					m--;
					sum_1=sum_1 + 1;
				}
				System.out.println("横向向左sum_1="+sum_1+"sum_0="+sum_0);
				weight[newL_1][newL_2] = sum_1 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newL_1][newL_2] ="+weight[newL_1][newL_2]);
			//System.out.println("没有跳出横向最外层while！！");
		}
		//横向向右遍历
		//int sum_2 = 0;
		System.out.println("横向向右遍历目标坐标为newR_1="+newR_1+",newR_2="+newR_2);
		in = true;
		while(newR_1>=0 && newR_2>=0 && in){//是空位置
			
			if(newR_2 == 21){//位于右侧竖边界上
				weight[newL_1][newL_2] = sum_0;
				in = false;
			}else{//继续向右遍历，得到sum_2
				n = newR_2+1;
				while(board[newR_1][n].equals("●") && n<=21){
					n++;
					sum_2=sum_2 + 1;
				}
				System.out.println("横向向右sum_2="+sum_2+"sum_0="+sum_0);
				weight[newR_1][newR_2] = sum_2 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newR_1][newR_2] ="+weight[newR_1][newR_2]);
			//System.out.println("没有跳出横向最外层while！！");
		}
		
//=================================================================横向遍历结束！！======================================================
		
		
//=================================================================纵向遍历开始！！======================================================		
	
	//--------------变量初始化--------------------------
		sum_0 = 0;//存放连续棋子的个数
		end = 0;//1->上边找到空点（遍历结束）  2-->下边找到空点（遍历结束）  "○"
		in = true;
		//iLX=lastX;
		//jLY=lastY;
		
		//int newU_1 = -1;//上新目标（newU_1,newU_2）
		//int newU_2 = -1;
		//int newD_1 = -1;//下新目标（newD_1,newD_2）
		//int newD_2 = -1;
		//开始遍历（单向进行） i-->Up(X(i)值减小，Y值不变)  j-->Down(X(j)值增大，Y值不变)
		while(u>=Math.max(lastX-4,0) && in){
			if(board[u][lastY].equals("●")){//结束后要判断是空点还是已经被计算机占用了，如果计算机占用，放弃该侧继续单向寻找权值！
				u--;
				(sum_0)++;
			}else if(board[u][lastY].equals("十")){//是空格
				newU_1 = u;
				newU_2 = lastY;
				in = false;
			}else{//被计算机占用
				in = false;
			}
		}
		if(sum_0>0){
			sum_0--;
		}
		in = true;
		while(d<=Math.min(lastX+4,21) && in){
			if(board[d][lastY].equals("●")){
				d++;
				(sum_0)++;
			}else if(board[d][lastY].equals("十")){
				newD_1 = d;
				newD_2 = lastY;
				in = false;
				//break;
			}else {
				in = false;
			}
//			System.out.println("没有跳出纵向while循环！！");
		}

		
		//进行单向寻找(条件-->为空位置)  sum_1
		//纵向向上遍历
		sum_1 = 0;
		in = true;
		System.out.println("纵向向上遍历-->"+newU_1+","+newU_2+"  sum_0="+sum_0);
		while(newU_1>=0 && newU_2>=0 && in){//是空位置
			
			if(newU_1==0){//位于上方边界上
				weight[newU_1][newU_2] = sum_0;
				in = false;
			}else{//继续向上遍历(i/X减小，Y不变)，得到sum_1
				p = newU_1-1;
				while(board[p][newU_2].equals("●") && p>=0){
					p = p - 1;
					(sum_1)++;
				}
				System.out.println("纵向向上sum_1="+sum_1+"sum_0="+sum_0);
				weight[newU_1][newU_2] = sum_1 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newU_1][newU_2]="+weight[newU_1][newU_2]);
//			System.out.println("纵向向上遍历没有跳出最外层while！！");
		}
		//纵向向下遍历
		sum_2 = 0;
		in = true;
		System.out.println("纵向向下遍历-->"+newD_1+","+newD_2);
		while(newD_1>=0 && newD_2>=0 && in){//是空位置
			
			if(newD_1==21){//位于下方边界上
				weight[newD_1][newD_2] = sum_0;
				in = false;
			}else{//继续向下遍历，得到sum_2
				q = newD_2 + 1;
				while(board[newD_1][q].equals("●") && q<=21){
					q = q + 1;
					(sum_2)++;
				}
				System.out.println("纵向向下sum_2="+sum_2+"sum_0="+sum_0);
				weight[newD_1][newD_2] = sum_2 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newD_1][newD_2]="+weight[newD_1][newD_2]);
//			System.out.println("纵向向下遍历没有跳出最外层while！！");
		}
//=================================================================纵向遍历结束！！======================================================
		
		
//=================================================================斜向（\）遍历开始！！======================================================		
	//--------------变量初始化--------------------------
		sum_0 = 0;//存放连续棋子的个数
		in = true;
		//end = 0;//1->左上边找到空点（遍历结束）  2-->右下边找到空点（遍历结束）  "○"
		
		//iLX=lastX;
		//jLY=lastY;
		
		//boolean in = true;//控制跳出while循环
		
		//int newLU_1 = -1;//左上新目标（newLU_1,newLU_2）
		//int newLU_2 = -1;
		//int newRD_1 = -1;//右下新目标（newRD_1,newRD_2）
		//int newRD_2 = -1;
		//开始遍历（单向进行）1、左上遍历（i（X）-->减小，j（Y）-->减小）
		while((xu>=Math.max(lastX-4,0))&&(yu>=Math.max(lastY-4,0)) && in){
			//int flagLU=-1;//“-1”第一个不同点（空或计算机占用）
			//int flagRD=-1;
			if(board[xu][yu].equals("●")){//结束后要判断是空点还是已经被计算机占用了，如果计算机占用，放弃该侧继续单向寻找权值！
				(xu)--;
				(yu)--;
				(sum_0)++;
				//flagLU = 1;
			}else if(board[xu][yu].equals("十")){
				newLU_1 = xu;
				newLU_2 = yu;
				in = false;
			}else {
				in = false;
			}
			//if(!board[][].equals())
//			else{
//				newLU_1 = xu;
//				newLU_2 = yu;
//				in = false;
//				break;
//			}
		}
//			System.out.println("开始遍历（单向进行）1、左上遍历左上没有跳出While循环！！");
//		}
		//开始遍历（单向进行）2、右下遍历（i（X）-->增大，j（Y）-->增大） 注意：sum_0继续！！
		//iLX=lastX;
		//jLY=lastY;
		if((sum_0)>=0){//否则自身会多加一次
			(sum_0) = (sum_0 - 1);
		}
		in = true;//控制跳出while循环
		while(xd<=Math.min(lastX+4,21) && yd<=Math.min(lastY+4,21) && in){
//			System.out.println("右下！！！");
			//int flagLU=-1;//“-1”第一个不同点（空或计算机占用）
			//int flagRD=-1;
			if(board[xd][yd].equals("●")){//结束后要判断是空点还是已经被计算机占用了，如果计算机占用，放弃该侧继续单向寻找权值！
				(xd)++;
				(yd)++;
				(sum_0)++;
				//flagLU = 1;
			}else if(board[xd][yd].equals("十")){
				newRD_1 = xd;
				newRD_2 = yd;
				in = false;
				//break;
			}else {
				in = false;
			}
//			System.out.println("斜向 右下没有跳出While循环！！");
		}
			//System.out.println("左上没有跳出While循环！！");
//		}
		//进行单向寻找(条件-->为空位置)  sum_1
		//斜向 左上遍历
		sum_1 = 0;
		in = true;//控制跳出while循环
		System.out.println("斜向 左上遍历-->"+newLU_1+","+newLU_2+"  sum_0="+sum_0);
		while(newLU_1>=0 && newLU_2>=0 && in){//是空位置
			//System.out.println("斜向 左上遍历-->"+newLU_1+","+newLU_2);
			if(newLU_1==0 || newLU_2==0){//到达左上方边界
				weight[newLU_1][newLU_2] = sum_0;
				in = false;
			}else{//继续向左上遍历(i/X减小，j/Y减小)，得到sum_1
				su = newLU_1 - 1;
				du = newLU_2 - 1;
				while(board[su][du].equals("●") && (su)>=0 && (du)>=0){//连续点条件（注意边界）
					(su)--;
					(du)--;
					(sum_1)++;
				}
				System.out.println("斜向 左上sum_1="+sum_1+"sum_0="+sum_0);
				weight[newLU_1][newLU_2] = sum_1 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newLU_1][newLU_2]="+weight[newLU_1][newLU_2]);
//			System.out.println("斜向 左上遍历没有跳出最外层while！！");
		}
		//斜向向右下遍历（i/X-->增大 j/Y-->增大）
		sum_2 = 0;
		in = true;//控制跳出while循环
		System.out.println("斜向向右下遍历-->"+newRD_1+","+newRD_2);
		while(newRD_1>=0 && newRD_2>=0 && in){//是空位置
			//System.out.println("斜向向右下遍历-->"+newRD_1+","+newRD_2);
			if((newRD_1==21) || (newRD_2==21)){//位于下方边界上
				weight[newRD_1][newRD_2] = sum_0;
				in = false;
			}else{//继续向右下遍历，得到sum_2
				sd = newRD_1 + 1;//!!!!!!
				dd = newRD_2 + 1;
				while(board[sd][dd].equals("●") && (sd)<=21 && (dd)<=21){
					(sd)++;
					(dd)++;
					(sum_2)++;
				}
				System.out.println("斜向 右下sum_2="+sum_2+"sum_0="+sum_0);
				weight[newRD_1][newRD_2] = sum_2 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newRD_1][newRD_2]="+weight[newRD_1][newRD_2]);
//			System.out.println("斜向向右下遍历没有跳出最外层while！！");
		}			
//=================================================================斜向遍历结束！！======================================================
		
		
//=================================================================反斜向（/）遍历开始！！======================================================		
	//--------------变量初始化--------------------------
		sum_0 = 0;//存放连续棋子的个数
		//end = 0;//1->左上边找到空点（遍历结束）  2-->右下边找到空点（遍历结束）  "○"
		
		//iLX=lastX;
		//jLY=lastY;
		
		//int newRU_1 = -1;//右上新目标（newRU_1,newRU_2）
		//int newRU_2 = -1;
		//int newLD_1 = -1;//左下新目标（newLD_1,newLD_2）
		//int newLD_2 = -1;
		
		in = true;//控制跳出while循环
		
		//开始遍历（单向进行）1、左下遍历（i（X）-->增大，j（Y）-->减小）
		while((xl)<=Math.min(lastX+4,21) && (yl)>=Math.max(lastY-4,0) && in){
			//int flagLU=-1;//“-1”第一个不同点（空或计算机占用）
			//int flagRD=-1;
			if(board[xl][yl].equals("●")){//结束后要判断是空点还是已经被计算机占用了，如果计算机占用，放弃该侧继续单向寻找权值！
				(xl)++;
				(yl)--;
				(sum_0)++;
				//flagLU = 1;
			}else if(board[xl][yl].equals("十")){
				newLD_1 = xl;
				newLD_2 = yl;
				in = false;
				//break;
			}else {
				in = false;
			}
			//}
//			System.out.println("反斜向 左下遍历没有跳出While循环！！");
		}
		//开始遍历（单向进行）2、右上遍历（i（X）-->减小，j（Y）-->增大） 注意：sum_0继续！！
		//iLX = lastX;
		//jLY = lastY;
		if(sum_0 >= 0){//否则自身会多加一次
			sum_0 = sum_0 - 1;
		}
		in = true;//控制跳出while循环
		while((xr)>=Math.max(lastX-4,0) && (yr)<=Math.min(lastY+4,21) && in){
			//int flagLU=-1;//“-1”第一个不同点（空或计算机占用）
			//int flagRD=-1;
			if(board[xr][yr].equals("●")){//结束后要判断是空点还是已经被计算机占用了，如果计算机占用，放弃该侧继续单向寻找权值！
				(xr)--;
				(yr)++;
				(sum_0)++;
				//flagLU = 1;
			}else if(board[xr][yr].equals("十")){
				newRU_1 = xr;
				newRU_2 = yr;
				in = false;
				//break;
			}else {
				in = false;
			}
			//}
//			System.out.println("反斜向 右上遍历没有跳出While循环！！");
		}
		//进行单向寻找(条件-->为空位置)  sum_1
		//反斜向 左下遍历
		sum_1 = 0;
		in = true;//控制跳出while循环
		System.out.println("反斜向 左下遍历-->"+newLD_1+","+newLD_2+"  sum_0="+sum_0);
		while(newLD_1>=0 && newLD_2>=0 && in){//是空位置
			//System.out.println("反斜向 左下遍历-->"+newLD_1+","+newLD_2);
			if(newLD_1==0 || newLD_2==0){//到达左上方边界
				weight[newLD_1][newLD_2] = sum_0;
				in = false;
//				break;
			}else{//继续向左下遍历(i/X增大，j/Y减小)，得到sum_1
				td = newLD_1 + 1;
				fd = newLD_2 - 1;
				while(board[td][fd].equals("●") && (td)<=21 && (fd)>=0){//连续点条件（注意边界）
					(td)++;
					(fd)--;
					(sum_1)++;
				}
				System.out.println("反斜向 左下sum_1="+sum_1+"sum_0="+sum_0);
				weight[newLD_1][newLD_2] = sum_1 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newLD_1][newLD_2]="+weight[newLD_1][newLD_2]);
//			in = false;
			
//			System.out.println("反斜向 左下2遍历没有跳出最外层while！！");
//			System.out.println("斜向向 左下2遍历没有跳出最外层while！！in="+in);
			
		}
		//斜向向右上遍历（i/X-->减小 j/Y-->增大）
		sum_2 = 0;
		in = true;//控制跳出while循环
		System.out.println("斜向向右上遍历-->"+newRU_1+","+newRU_2);
		while(newRU_1>=0 && newRU_2>=0 && in){
			//System.out.println("斜向向右上遍历-->"+newRU_1+","+newRU_2);
			if((newRU_1==21) || (newRU_2==21)){//位于下方边界上
				weight[newRU_1][newRU_2] = sum_0;
				in = false;
			}else{//继续向右上遍历，得到sum_2
				tu = newRU_1 - 1;
				fu = newRU_2 + 1;
				while(board[tu][fu].equals("●") && (tu)>=0 && (fu)<=21){
					(tu)--;
					(fu)++;
					(sum_2)++;
				}
				System.out.println("反斜向右上sum_2="+sum_2+"sum_0="+sum_0);
				weight[newRU_1][newRU_2] = sum_2 + sum_0;
				in = false;
				//break;//跳出最外层否？？
			}
			System.out.println("weight[newRU_1][newRU_2]="+weight[newRU_1][newRU_2]);
//			in = false;
//			System.out.println("斜向向右上2遍历没有跳出最外层while！！");
//			System.out.println("斜向向右上2遍历没有跳出最外层while！！in="+in);
		}		
		
		
		
		//int posX = 0;
		//int posY = 0;
		//遍历weight数组，找到最大值返回
		for(int i=0; i<=21; i++){
			for(int j=0; j<=21; j++){
				if(weight[i][j]>=0 && weight[i][j]>weight[posX][posY]){
					System.out.println("weight["+i+"]["+j+"]="+weight[i][j]);;
					posX = i;
					posY = j;
				}
			}
		}
		
		int[] result = { posX, posY };
		return result;
	}
	
	/**
	 * 计算机随机下棋
	 * 横向遍历(左、右)
	 * 纵向遍历(上、下)
	 * 斜向遍历(左上、右下)
	 * 反斜向遍历(左下、右上)
	 */
	 //横向遍历
//	 public int horizToLeft(int x,int y){
//		 
//		 return sum;
//	 }
//	 public int horizToRight(int x,int y){
//		 
//		 return sum;
//	 }
//	 //纵向遍历
//	 public int horizToLeft(int x,int y){
//		 
//		 return sum;
//	 }
//	 public int horizToRight(int x,int y){
//		 
//		 return sum;
//	 }

	 
	 
	 
	 
	 
	 
	 
	 
	 

	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board = chessboard.getBoard();//???
		int sum = 0;//5则赢
		int conI = -1;
		int conJ = -1;
		//System.out.println("ico="+ico);
//		flag++;
//		System.out.println("flag="+flag);
//		if(ico.equals("●")){
			//横向检查(posX不动) 注意：已包含位于边界情况 i->iL,j->jR
			//sum=0;
		if(sum>0){
			sum=sum-1;
		}
		for(int i=posY;i>=Math.max(posY-4,0);i--){
			//System.out.println("横向检查(posX不动)");
				if(board[posX][i].equals(ico)){//向左遍历
					//if(sum>0){
					//	sum=sum-1;
					//}
					sum++;
					//System.out.println("横向检查向左 sum="+sum);
					if(sum==5){
						//System.out.println("横向检查向左遍历连续！");
						return true;
					}
					continue;
				}//else{
		}//for(int i=posY;i>=Math.max(posY-4,0);i--)

		if(sum>0){
			sum=sum-1;
		}
		//左边不连续,右边连续
		for(int j=posY;j<=Math.min(posY+4,21);j++){

			if(board[posX][j].equals(ico)){//向右遍历
				sum++;
				//System.out.println("横向检查向右 sum="+sum);
				if(sum==5){
					//System.out.println("横向检查向左遍历不连续，向右遍历连续！");
					return true;
				}
				continue;
			}else{
				break;
			}
			//}
		}
		//纵向检查(posY不动) i->iU,j->jD
		sum = 0;
		for(int i=posX;i>=Math.max(posX-4,0);i--){//向上
			//System.out.println("纵向检查(posY不动)");
			if(board[i][posY].equals(ico)){
				//if(sum>0){
				//	sum=sum-1;
				//}
				sum++;
				if(sum==5){
					//System.out.println("纵向检查左遍历连续！");
					return true;
				}
				continue;
			}
		}//else{
		if(sum>0){
			sum=sum-1;
		}
		for(int j=posX;j<=Math.min(posX+4,21);j++){	//向下	
				//左边不连续,右边连续
				if(board[j][posY].equals(ico)){
					sum++;
					if(sum==5){
						System.out.println("纵向检查右遍历连续！");
						return true;
					}
					continue;
				}else{
					break;
				}

//			System.out.println("BreakTest（没有跳出for循环！）纵向检查不连续！！！！");
		}
		//斜向检查(遍历左下<-->右上) i->iX,j->jY
		sum = 0;
		for(int i=posX,j=posY;(i>=Math.max(posX-4,0))&&(j<=Math.min(posY-4,21));i--,j++){//斜向上进行(右上)
			//System.out.println("斜向检查(遍历左下<-->右上)斜向上进行(右上)初值sum="+sum);
			//System.out.println("i="+i+" j="+j);
			if(board[i][j].equals(ico)){
				sum++;
				//System.out.println("右上sum="+sum);
				if(sum==5){
					System.out.println("斜向检查-斜向上进行(右上)连续");
					return true;
				}
				continue;
			}
		}
		if(sum>0){
			sum=sum-1;
		}
		for(int i=posX,j=posY;(i<=Math.min(posX+4,21))&&(j>=Math.max(posY-4,0));i++,j--){//斜向下进行（左下）
			//System.out.println("斜向检查(遍历左下<-->右上)斜向下进行（左下）初值sum="+sum);
			//sum = sum-1;
			if(board[i][j].equals(ico)){
				//if(sum>0){
				//	sum=sum-1;
				//}
				sum++;
				//System.out.println("左下：sum="+sum);
				if(sum==5){
					//System.out.println("斜向检查(遍历左下<-->右上)斜向下进行（左下）连续");
					return true;
				}
				continue;
			}else{
				break;
			}
		}
		//反斜向检查(遍历左上<-->右下) i->iX,j->jY
		sum = 0;
		for(int i=posX,j=posY;(i>=Math.max(posX-4,0))&&(j>=Math.max(posY-4,0));i--,j--){//斜向上进行（左上）
			//System.out.println("反斜向检查(遍历左上<-->右下)斜向上进行（左上）");
			//sum = sum-1;
			if(board[i][j].equals(ico)){
				sum++;
				if(sum==5){
					System.out.println("反斜向检查--斜向上进行（左上）连续");
					return true;
				}
				continue;
			}
		}
		if(sum>0){
			sum=sum-1;
		}
		for(int i=posX,j=posY;(i<=Math.min(posX+4,21))&&(j<=Math.min(posY+4,21));i++,j++){//斜向下进行（右下）
			//System.out.println("反斜向检查(遍历左上<-->右下)斜向下进行（右下） 242:i="+i+"j="+j);
			if(board[i][j].equals(ico)){
				sum++;
				if(sum==5){
					System.out.println("反斜向检查--斜向下进行（右下）连续");
					return true;
				}
				continue;
			}else{
				break;
			}
		}
//			flag++;
//		}
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
