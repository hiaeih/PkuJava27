import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}
	//����Ƿ��Ѿ��������ӣ���Ϊ�ڲ��ı�Դ������£�����ÿ�α����������ʾ�֣�������¶����������������Ȼֱ����computerDo��
	public boolean chessExist(int i, int j) {
		String[][] board = chessboard.getBoard();
		if (board[i][j]!= "ʮ")
			return true;
		return false;
	}
	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}

	/**
	 * �����������壬΢����
	 */
	public int[] computerDo() {
		int max_black,max_white,max_temp,max = 0;
		for (int i = 0; i < Chessboard.BOARD_SIZE; i++) {
			for (int j = 0; j < Chessboard.BOARD_SIZE; j++) {
				if(!chessExist(i,j)){//û������,�ֱ�õ��Լ��Ͷ����ڵ�ǰ����ϵ����÷֣���������������Ӷ�����Χ�»��Լ�ѡ������λ��
					max_white = checkMax(i,j,Chessman.WHITE);
					max_black = checkMax(i,j,Chessman.BLACK);
					max_temp = Math.max(max_white,max_black);
					if(max_temp > max){
						max = max_temp;
						posX = i;
						posY = j;
					}
				}
			}
		}
		/*int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "ʮ") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}*/
		int[] result = { posX, posY };
		return result;
	}
	//����������ĳһ�����ϰ˸��������ӵ����ֵ��
	//��˸�����ֱ��ǣ����ҡ��ϡ��¡����ϡ����¡����ϡ�����
	private int checkMax(int x, int y, Chessman chessType) {
		String[][] board = chessboard.getBoard();
		int num = 0,max_num,max_temp = 0;
		int x_temp = x,y_temp = y;
		//�ж��Ҳ�
		for(int i = 0;i< 4;i++){
			y_temp += 1;
			if(y_temp > Chessboard.BOARD_SIZE - 1)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		//�ж����
		y_temp = y;
		for(int i = 0;i< 4;i++){
			y_temp -= 1;
			if(y_temp < 0)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		if (num < 5)
			max_temp = num;
		//�ж��Ϸ�
		num = 0;
		x_temp = x;y_temp = y;
		for(int i = 0;i< 4;i++){
			x_temp -= 1;
			if(x_temp < 0)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		//�ж��·�
		x_temp = x;
		for(int i = 0;i< 4;i++){
			x_temp += 1;
			if(x_temp > Chessboard.BOARD_SIZE - 1)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;
		//�ж����Ϸ�
		num = 0;
		x_temp = x;y_temp = y;
		for(int i = 0;i< 4;i++){
			x_temp -= 1;y_temp -= 1;
			if(x_temp < 0 || y_temp < 0)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		//�ж����·�
		x_temp = x;y_temp = y;
		for(int i = 0;i< 4;i++){
			x_temp += 1;y_temp += 1;
			if(x_temp > Chessboard.BOARD_SIZE - 1 || y_temp > Chessboard.BOARD_SIZE - 1)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;
		//�ж����·�
		num = 0;
		x_temp = x;y_temp = y;
		for(int i = 0;i< 4;i++){
			x_temp += 1;y_temp -= 1;
			if(x_temp >Chessboard.BOARD_SIZE - 1 || y_temp < 0)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		//�ж����Ϸ�
		x_temp = x;y_temp = y;
		for(int i = 0;i< 4;i++){
			x_temp -= 1;y_temp += 1;
			if(x_temp < 0 || y_temp > Chessboard.BOARD_SIZE - 1)
				break;
			if (board[x_temp][y_temp].equals(chessType.getChessman()))
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;
		max_num = max_temp;
		return max_num;
	}
	/**
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
	 */
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board = chessboard.getBoard();
		int num = 1;
		int x = posX, y = posY;
		// �ұ��ж�
		for (int i = 0; i < 5; i++) {
			y += 1;
			if (y > board.length - 1)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		// �ж����
		y = posY;
		for (int i = 0; i < 5; i++) {
			y -= 1;
			if (y < 0)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		if (num == WIN_COUNT)
			return true;
		// �ж�����
		x = posX;
		y = posY;
		num = 1;
		for (int i = 0; i < 5; i++) {
			x -= 1;
			if (x < 0)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		// �ж�����
		x = posX;
		for (int i = 0; i < 5; i++) {
			x += 1;
			if (x > board.length - 1)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		if (num == WIN_COUNT)
			return true;
		// �ж�����
		x = posX;
		y = posY;
		num = 1;
		for (int i = 0; i < 5; i++) {
			x -= 1;
			y -= 1;
			if (x < 0 || y < 0)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		// �ж�����
		x = posX;
		y = posY;
		for (int i = 0; i < 5; i++) {
			x += 1;
			y += 1;
			if (x > board.length - 1 || y > board.length - 1)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		if (num == WIN_COUNT)
			return true;
		// �ж�����
		x = posX;
		y = posY;
		num = 1;
		for (int i = 0; i < 5; i++) {
			x += 1;
			y -= 1;
			if (x > board.length - 1 || y < 0)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		// �ж�����
		x = posX;
		y = posY;
		for (int i = 0; i < 5; i++) {
			x -= 1;
			y += 1;
			if (x < 0 || y > board.length - 1)
				break;
			if (board[x][y] == ico)
				num++;
			else
				break;
		}
		if (num == WIN_COUNT)
			return true;
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
