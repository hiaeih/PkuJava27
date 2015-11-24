//�㷨����ٵݹ鷨��ֻ������java����д��һ��
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/* 
 *main����������ChessFrame���һ��ʵ������cf���� 
 *��������Ļ��ʾ��ʾ��ʵ������ 
 * **/
public class wuziqi {
	public static void main(String args[]) {
		ChessFrame cf = new ChessFrame();
		cf.setVisible(true);
	}
};

/*
 * ��ChessFrame��Ҫ�����Ǵ�����������Ϸ������Ͳ˵�
 */
class ChessFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 2183726320279905885L;
	private String[] strmode = { "�˻�����", "���˶���" };
	private String[] strorder = { "�������", "���������" };
	public static boolean iscomputer = true;
	public static boolean checkcomputer = true;
	public static boolean isorder = true;
	private int width = 15, height = 15;
	private ChessModel cm;
	private MainPanel mp;
	public static int qipan[][] = new int[15][15];

	// ������������Ϸ��������
	public ChessFrame() {
		this.setTitle("��������Ϸ");
		cm = new ChessModel(1);
		mp = new MainPanel(cm);
		Container con = this.getContentPane();
		con.add(mp, "Center");
		this.setResizable(false);
		this.addWindowListener(new ChessWindowEvent());
		MapSize(14, 14);
		setLocation(400, 100);
		JMenuBar mbar = new JMenuBar();
		this.setJMenuBar(mbar);
		JMenu gameMenu = new JMenu("��Ϸ");
		mbar.add(makeMenu(gameMenu, new Object[] { "����", "ģʽ", "����˳��", "����",
				null, "�˳�" }, this));
		JMenu lookMenu = new JMenu("��ͼ");
		mbar.add(makeMenu(lookMenu,
				new Object[] { "Metal", "Motif", "Windows" }, this));
		JMenu helpMenu = new JMenu("����");
		mbar.add(makeMenu(helpMenu, new Object[] { "����" }, this));
	}

	// ������������Ϸ�����˵�
	public JMenu makeMenu(Object parent, Object items[], Object target) {
		JMenu m = null;
		if (parent instanceof JMenu)
			m = (JMenu) parent;
		else if (parent instanceof String)
			m = new JMenu((String) parent);
		else
			return null;
		for (int i = 0; i < items.length; i++)
			if (items[i] == null)
				m.addSeparator();
			else if (items[i] == "ģʽ") {
				JMenu jm = new JMenu("ģʽ");
				ButtonGroup group = new ButtonGroup();
				JRadioButtonMenuItem rmenu;
				for (int h = 0; h < strmode.length; h++) {
					rmenu = makeRadioButtonMenuItem(strmode[h], target);
					if (h == 0)
						rmenu.setSelected(true);
					jm.add(rmenu);
					group.add(rmenu);
				}
				m.add(jm);
			} else if (items[i] == "����˳��") {
				JMenu jm = new JMenu("����˳��");
				ButtonGroup group = new ButtonGroup();
				JRadioButtonMenuItem rmenu;
				for (int k = 0; k < strorder.length; k++) {
					rmenu = makeRadioButtonMenuItem(strorder[k], target);
					if (k == 0)
						rmenu.setSelected(true);
					jm.add(rmenu);
					group.add(rmenu);
				}
				m.add(jm);
			} else
				m.add(makeMenuItem(items[i], target));
		return m;
	}

	// ������������Ϸ�Ĳ˵���
	public JMenuItem makeMenuItem(Object item, Object target) {
		JMenuItem r = null;
		if (item instanceof String)
			r = new JMenuItem((String) item);
		else if (item instanceof JMenuItem)
			r = (JMenuItem) item;
		else
			return null;
		if (target instanceof ActionListener)
			r.addActionListener((ActionListener) target);
		return r;
	}

	// ������������Ϸ�ĵ�ѡ��ťʽ�˵���
	public JRadioButtonMenuItem makeRadioButtonMenuItem(Object item,
			Object target) {
		JRadioButtonMenuItem r = null;
		if (item instanceof String)
			r = new JRadioButtonMenuItem((String) item);
		else if (item instanceof JRadioButtonMenuItem)
			r = (JRadioButtonMenuItem) item;
		else
			return null;
		if (target instanceof ActionListener)
			r.addActionListener((ActionListener) target);
		return r;
	}

	public void MapSize(int w, int h) {
		setSize(w * 30 + 67, h * 30 + 110);
		if (!ChessFrame.checkcomputer) {
			ChessFrame.iscomputer = false;
		} else {
			ChessFrame.iscomputer = true;
		}
		mp.setModel(cm);
		mp.repaint();
	}

	public boolean getiscomputer() {
		return ChessFrame.iscomputer;
	}

	public void restart() {
		int modeChess = cm.getModeChess();
		if (modeChess <= 3 && modeChess >= 1) {
			cm = new ChessModel(modeChess);
			MapSize(cm.getWidth(), cm.getHeight());
			System.out.println("/u81EA/u5B9A/u4E49");
		}
	}

	public void actionPerformed(ActionEvent e) {
		String arg = e.getActionCommand();
		try {
			if (arg.equals("Windows"))
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			else if (arg.equals("Motif"))
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			else
				UIManager
						.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ee) {
		}
		if (arg.equals("�˻�����")) {
			ChessFrame.checkcomputer = true;
			ChessFrame.iscomputer = true;
			cm = new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(), cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("���˶���")) {
			ChessFrame.checkcomputer = false;
			ChessFrame.iscomputer = false;
			cm = new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(), cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("���������")) {
			ChessFrame.isorder = false;
			cm = new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(), cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("�������")) {
			ChessFrame.isorder = true;
			cm = new ChessModel(cm.getModeChess());
			MapSize(cm.getWidth(), cm.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (arg.equals("����")) {
			restart();
			if (ChessFrame.isorder == false)
				cm.first();
		}
		if (arg.equals("����")) {
			int modeChess = cm.getModeChess();
			if (modeChess <= 3 && modeChess >= 1) {
				cm = new ChessModel(modeChess);
				for (int i = 0; i < this.width; i++) {
					for (int j = 0; j < this.height; j++) {
						cm.arrMapShow[i][j] = qipan[i][j];
					}
				}
				MapSize(cm.getWidth(), cm.getHeight());
			}
		}
		if (arg.equals("����"))
			JOptionPane.showMessageDialog(this, "��������Ϸ���԰汾����By �����ƾ���ѧ08��һ��˧",
					"����", 0);
		if (arg.equals("�˳�"))
			System.exit(0);
	}
}

/*
 * ��ChessModelʵ������������������㷨�ĺ���
 */
class ChessModel {
	// ���̵Ŀ�ȡ��߶ȡ����̵�ģʽ����20��15��
	private int width = 15;
	private int modeChess;
	// ���̷���ĺ�����������
	private int x = 0, y = 0;
	private int temp1;
	private int temp2;
	private int temp3;
	private int temp4;
	int[][] arrMapShow;
	// �������ֵı�ʶ�����̷������Ƿ������ӵı�ʶ��,����˳��ı�ʶ
	private boolean isOdd, isExist;
	private ChessFrame cf;

	// 1��ʾ���� ��2��ʾ���壬-1��ʾ�հ׿��´�
	public ChessModel() {
	}

	// �ù��췽�����ݲ�ͬ������ģʽ��modeChess����������Ӧ��С������
	public ChessModel(int modeChess) {
		this.isOdd = true;
		if (modeChess == 1) {
			PanelInit(14, 14, modeChess);
		}
	}

	// ��������ģʽ�������̴�С
	private void PanelInit(int width, int height, int modeChess) {
		this.width = width;
		this.modeChess = modeChess;
		arrMapShow = new int[width + 1][height + 1];
		for (int i = 0; i <= width; i++) {
			for (int j = 0; j <= height; j++) {
				arrMapShow[i][j] = -1;
			}
		}
	}

	// ��ȡ�Ƿ񽻻����ֵı�ʶ��
	public boolean getisOdd() {
		return this.isOdd;
	}

	// ���ý������ֵı�ʶ��
	public void setisOdd(boolean isodd) {
		if (isodd)
			this.isOdd = true;
		else
			this.isOdd = false;
	}

	// ��ȡĳ���̷����Ƿ������ӵı�ʶֵ
	public boolean getisExist() {
		return this.isExist;
	}

	// ��ȡ���̿��
	public int getWidth() {
		return this.width;
	}

	// ��ȡ���̸߶�
	public int getHeight() {
		return this.width;
	}

	// ��ȡ����ģʽ
	public int getModeChess() {
		return this.modeChess;
	}

	// ��ȡ���̷��������ӵ���Ϣ
	public int[][] getarrMapShow() {
		return arrMapShow;
	}

	// �ж����ӵĺ������������Ƿ�Խ��
	private boolean badxy(int x, int y) {
		if (x >= width + 30 || x < 0)
			return true;
		return y >= width + 30 || y < 0;
	}

	// ����������ĳһ�����ϰ˸��������ӵ����ֵ��
	// ��˸�����ֱ��ǣ����ҡ��ϡ��¡����ϡ����¡����ϡ�����
	public boolean chessExist(int i, int j) {
		if (this.arrMapShow[i][j] == 1 || this.arrMapShow[i][j] == 2)
			return true;
		return false;
	}

	// �жϸ�����λ���Ƿ��������
	// public void readyplay(int x,int y){
	// if(badxy(x,y))
	// return;
	// if (chessExist(x,y))
	// return;
	// this.arrMapShow[x][y]=3;
	// }

	// �ڸ�����λ��������
	public void play(int x, int y) {
		if (badxy(x, y))
			return;
		if (chessExist(x, y)) {
			this.isExist = true;
			return;
		} else
			this.isExist = false;
		if (getisOdd()) {
			setisOdd(false);
			this.arrMapShow[x][y] = 1;
		} else {
			setisOdd(true);
			this.arrMapShow[x][y] = 2;
		}
	}

	// ���������
	/*
	 * ˵��������ٷ��ж�ÿһ���������ĸ�����ĵ���������������ó����������ֵ�����꣬����
	 */
	void computerDo(int flag) {
		int max_black, max_white, count = 0, max = 0, max_temp, level, x[] = {
				-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
				-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
				-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2 }, y[] = { -2,
				-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
				-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
				-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2 }, i, j;
		setisOdd(true);
		for (i = 0; i < width; i++) {
			for (j = 0; j < width; j++) {
				if (this.arrMapShow[i][j] == -1) {// �㷨�ж��Ƿ�����
					if (lian_si(i, j, 2) || duan_lian_si(i, j, 2)) {
						level = 43;
						x[level] = i;
						y[level] = j;
					} else {
						if (lian_si(i, j, 1) || duan_lian_si(i, j, 1)) {
							level = 42;
							x[level] = i;
							y[level] = j;
						} else {
							if (si_si(i, j, 2) && zhouwei3(i, j, 2)
									|| si_san(i, j, 2) && zhouwei3(i, j, 2)) {
								level = 41;
								x[level] = i;
								y[level] = j;
							} else {
								if (si_si(i, j, 2) && zhouwei2(i, j, 2)
										|| si_san(i, j, 2) && zhouwei2(i, j, 2)) {
									level = 40;
									x[level] = i;
									y[level] = j;
								} else {
									if (si_si(i, j, 2) && zhouwei1(i, j, 2)
											|| si_san(i, j, 2)
											&& zhouwei1(i, j, 2)) {
										level = 39;
										x[level] = i;
										y[level] = j;
									} else {
										if (si_si(i, j, 2) || si_san(i, j, 2)) {
											level = 38;
											x[level] = i;
											y[level] = j;
										} else {
											if (huo_lian_san(i, j, 1)
													&& huo_lian_san(i, j, 2)) {
												level = 37;
												x[level] = i;
												y[level] = j;
											} else {
												if (huo_lian_san(i, j, 2)
														&& huo_duan_er(i, j, 2)
														|| huo_lian_san(i, j, 2)
														&& huo_er(i, j, 2)) {
													level = 36;
													x[level] = i;
													y[level] = j;
												} else {
													if (huo_lian_san(i, j, 2)
															&& zhouwei3(i, j, 2)
															|| duan_lian_san(i,
																	j, 2)
															&& zhouwei3(i, j, 2)) {
														level = 35;
														x[level] = i;
														y[level] = j;
													} else {
														if (huo_lian_san(i, j,
																2)
																&& zhouwei2(i,
																		j, 2)
																|| duan_lian_san(
																		i, j, 2)
																&& zhouwei2(i,
																		j, 2)) {
															level = 34;
															x[level] = i;
															y[level] = j;
														} else {
															if (huo_lian_san(i,
																	j, 2)
																	&& zhouwei1(
																			i,
																			j,
																			2)
																	|| duan_lian_san(
																			i,
																			j,
																			2)
																	&& zhouwei1(
																			i,
																			j,
																			2)) {
																level = 33;
																x[level] = i;
																y[level] = j;
															} else {
																if (huo_lian_san(
																		i, j, 2)
																		|| duan_lian_san(
																				i,
																				j,
																				2)) {
																	level = 32;
																	x[level] = i;
																	y[level] = j;
																} else {
																	if (si_si(
																			i,
																			j,
																			1)
																			&& zhouwei3(
																					i,
																					j,
																					1)
																			|| si_san(
																					i,
																					j,
																					1)
																			&& zhouwei3(
																					i,
																					j,
																					1)) {
																		level = 31;
																		x[level] = i;
																		y[level] = j;
																	} else {
																		if (si_si(
																				i,
																				j,
																				1)
																				&& zhouwei2(
																						i,
																						j,
																						1)
																				|| si_san(
																						i,
																						j,
																						1)
																				&& zhouwei2(
																						i,
																						j,
																						1)) {
																			level = 30;
																			x[level] = i;
																			y[level] = j;
																		} else {
																			if (si_si(
																					i,
																					j,
																					1)
																					&& zhouwei1(
																							i,
																							j,
																							1)
																					|| si_san(
																							i,
																							j,
																							1)
																					&& zhouwei1(
																							i,
																							j,
																							1)) {
																				level = 29;
																				x[level] = i;
																				y[level] = j;
																			} else {
																				if (si_si(
																						i,
																						j,
																						1)
																						|| si_san(
																								i,
																								j,
																								1)) {
																					level = 28;
																					x[level] = i;
																					y[level] = j;
																				} else {
																					if (huo_lian_san(
																							i,
																							j,
																							1)
																							&& huo_er(
																									i,
																									j,
																									2)
																							|| huo_lian_san(
																									i,
																									j,
																									1)
																							&& huo_duan_er(
																									i,
																									j,
																									2)) {
																						level = 27;
																						x[level] = i;
																						y[level] = j;
																					} else {
																						if (huo_lian_san(
																								i,
																								j,
																								1)
																								&& zhouwei3(
																										i,
																										j,
																										1)
																								|| duan_lian_san(
																										i,
																										j,
																										1)
																								&& zhouwei3(
																										i,
																										j,
																										1)) {
																							level = 26;
																							x[level] = i;
																							y[level] = j;
																						} else {
																							if (huo_lian_san(
																									i,
																									j,
																									1)
																									&& zhouwei2(
																											i,
																											j,
																											1)
																									|| duan_lian_san(
																											i,
																											j,
																											1)
																									&& zhouwei2(
																											i,
																											j,
																											1)) {
																								level = 25;
																								x[level] = i;
																								y[level] = j;
																							} else {
																								if (huo_lian_san(
																										i,
																										j,
																										1)
																										&& zhouwei1(
																												i,
																												j,
																												1)
																										|| duan_lian_san(
																												i,
																												j,
																												1)
																										&& zhouwei1(
																												i,
																												j,
																												1)) {
																									level = 24;
																									x[level] = i;
																									y[level] = j;
																								} else {
																									if (huo_lian_san(
																											i,
																											j,
																											1)
																											|| duan_lian_san(
																													i,
																													j,
																													1)) {
																										level = 23;
																										x[level] = i;
																										y[level] = j;
																									} else {
																										if (lian_duan_si(
																												i,
																												j,
																												2)
																												&& huo_er(
																														i,
																														j,
																														2)
																												|| lian_duan_si(
																														i,
																														j,
																														2)
																												&& huo_lian_san(
																														i,
																														j,
																														2)
																												|| lian_duan_si(
																														i,
																														j,
																														2)
																												&& huo_duan_er(
																														i,
																														j,
																														2)
																												|| si_lian_san(
																														i,
																														j,
																														2)
																												&& huo_er(
																														i,
																														j,
																														2)
																												|| si_lian_san(
																														i,
																														j,
																														2)
																												&& huo_lian_san(
																														i,
																														j,
																														2)
																												|| si_lian_san(
																														i,
																														j,
																														2)
																												&& huo_duan_er(
																														i,
																														j,
																														2)) {
																											level = 22;
																											x[level] = i;
																											y[level] = j;
																										} else {
																											if (san_san(
																													i,
																													j,
																													2)) {
																												level = 21;
																												x[level] = i;
																												y[level] = j;
																											} else {
																												if ((huo_er(
																														i,
																														j,
																														2) && san_san(
																														i,
																														j,
																														1))
																														|| (huo_duan_er(
																																i,
																																j,
																																2) && san_san(
																																i,
																																j,
																																1))) {
																													level = 20;
																													x[level] = i;
																													y[level] = j;
																												} else {
																													if (san_san(
																															i,
																															j,
																															1)) {
																														level = 19;
																														x[level] = i;
																														y[level] = j;
																													} else {
																														if (lian_duan_si(
																																i,
																																j,
																																1)
																																&& huo_er(
																																		i,
																																		j,
																																		1)
																																|| lian_duan_si(
																																		i,
																																		j,
																																		1)
																																&& huo_lian_san(
																																		i,
																																		j,
																																		1)
																																|| lian_duan_si(
																																		i,
																																		j,
																																		1)
																																&& huo_duan_er(
																																		i,
																																		j,
																																		1)
																																|| si_lian_san(
																																		i,
																																		j,
																																		1)
																																&& huo_er(
																																		i,
																																		j,
																																		1)
																																|| si_lian_san(
																																		i,
																																		j,
																																		1)
																																&& huo_lian_san(
																																		i,
																																		j,
																																		1)
																																|| si_lian_san(
																																		i,
																																		j,
																																		1)
																																&& huo_duan_er(
																																		i,
																																		j,
																																		1)) {
																															level = 18;
																															x[level] = i;
																															y[level] = j;
																														} else {
																															if (si_lian_san(
																																	i,
																																	j,
																																	2)
																																	|| lian_duan_si(
																																			i,
																																			j,
																																			2)) {
																																level = 17;
																																x[level] = i;
																																y[level] = j;
																															} else {
																																if (huo_er(
																																		i,
																																		j,
																																		2)
																																		&& shi_zi_er(
																																				i,
																																				j,
																																				2)) {
																																	level = 16;
																																	x[level] = i;
																																	y[level] = j;
																																} else {
																																	if ((huo_er(
																																			i,
																																			j,
																																			2) && huo_er(
																																			i,
																																			j,
																																			1))
																																			|| (huo_duan_er(
																																					i,
																																					j,
																																					2) && huo_er(
																																					i,
																																					j,
																																					1))
																																			|| (huo_duan_er(
																																					i,
																																					j,
																																					2) && huo_duan_er(
																																					i,
																																					j,
																																					1))
																																			|| (huo_er(
																																					i,
																																					j,
																																					2) && huo_duan_er(
																																					i,
																																					j,
																																					1))) {
																																		level = 15;
																																		x[level] = i;
																																		y[level] = j;
																																	} else {
																																		if ((huo_er(
																																				i,
																																				j,
																																				2) && si_er(
																																				i,
																																				j,
																																				2))
																																				|| (huo_er(
																																						i,
																																						j,
																																						2) && si_er(
																																						i,
																																						j,
																																						1))) {
																																			level = 14;
																																			x[level] = i;
																																			y[level] = j;
																																		} else {
																																			if (huo_er(
																																					i,
																																					j,
																																					2)
																																					|| huo_duan_er(
																																							i,
																																							j,
																																							2)) {
																																				level = 13;
																																				x[level] = i;
																																				y[level] = j;
																																			} else {
																																				if (huo_er(
																																						i,
																																						j,
																																						2)
																																						&& zhouwei3(
																																								i,
																																								j,
																																								2)
																																						|| huo_duan_er(
																																								i,
																																								j,
																																								2)
																																						&& zhouwei3(
																																								i,
																																								j,
																																								2)) {
																																					level = 12;
																																					x[level] = i;
																																					y[level] = j;
																																				} else {
																																					if (huo_er(
																																							i,
																																							j,
																																							2)
																																							&& zhouwei2(
																																									i,
																																									j,
																																									2)
																																							|| huo_duan_er(
																																									i,
																																									j,
																																									2)
																																							&& zhouwei2(
																																									i,
																																									j,
																																									2)) {
																																						level = 11;
																																						x[level] = i;
																																						y[level] = j;
																																					} else {
																																						if (huo_er(
																																								i,
																																								j,
																																								2)
																																								&& zhouwei1(
																																										i,
																																										j,
																																										2)
																																								|| huo_duan_er(
																																										i,
																																										j,
																																										2)
																																								&& zhouwei1(
																																										i,
																																										j,
																																										2)) {
																																							level = 10;
																																							x[level] = i;
																																							y[level] = j;
																																						} else {
																																							if (huo_er(
																																									i,
																																									j,
																																									1)
																																									&& shi_zi_er(
																																											i,
																																											j,
																																											1)) {
																																								level = 9;
																																								x[level] = i;
																																								y[level] = j;
																																							} else {
																																								if (huo_er(
																																										i,
																																										j,
																																										1)
																																										&& zhouwei3(
																																												i,
																																												j,
																																												1)
																																										|| huo_duan_er(
																																												i,
																																												j,
																																												1)
																																										&& zhouwei3(
																																												i,
																																												j,
																																												1)) {
																																									level = 8;
																																									x[level] = i;
																																									y[level] = j;
																																								} else {
																																									if (huo_er(
																																											i,
																																											j,
																																											1)
																																											&& zhouwei2(
																																													i,
																																													j,
																																													1)
																																											|| huo_duan_er(
																																													i,
																																													j,
																																													1)
																																											&& zhouwei2(
																																													i,
																																													j,
																																													1)) {
																																										level = 7;
																																										x[level] = i;
																																										y[level] = j;
																																									} else {
																																										if (huo_er(
																																												i,
																																												j,
																																												1)
																																												&& zhouwei1(
																																														i,
																																														j,
																																														1)
																																												|| huo_duan_er(
																																														i,
																																														j,
																																														1)
																																												&& zhouwei1(
																																														i,
																																														j,
																																														1)) {
																																											level = 6;
																																											x[level] = i;
																																											y[level] = j;
																																										} else {
																																											if (huo_er(
																																													i,
																																													j,
																																													1)
																																													|| huo_duan_er(
																																															i,
																																															j,
																																															1)) {
																																												level = 5;
																																												x[level] = i;
																																												y[level] = j;
																																											} else {
																																												if (xie_zi_er(
																																														i,
																																														j,
																																														2)
																																														|| shi_zi_er(
																																																i,
																																																j,
																																																2)) {
																																													level = 4;
																																													x[level] = i;
																																													y[level] = j;
																																												} else {
																																													if (xie_zi_er(
																																															i,
																																															j,
																																															1)
																																															|| shi_zi_er(
																																																	i,
																																																	j,
																																																	1)) {
																																														level = 3;
																																														x[level] = i;
																																														y[level] = j;
																																													} else {
																																														if (si_er(
																																																i,
																																																j,
																																																2)) {
																																															level = 2;
																																															x[level] = i;
																																															y[level] = j;
																																														} else {
																																															max_white = checkMax(
																																																	i,
																																																	j,
																																																	2);// �жϰ��ӵ����ֵ
																																															max_black = checkMax(
																																																	i,
																																																	j,
																																																	1);// �жϺ��ӵ����ֵ
																																															if (max_white > max_black)
																																																max_temp = max_white;
																																															else
																																																max_temp = max_black;
																																															if (max_temp > max
																																																	&& count == 0) {
																																																max = max_temp;
																																																count++;
																																																level = 1;
																																																x[level] = i;
																																																y[level] = j;
																																															} else if (count == 1) {
																																																for (int m = 0; m < width - 1; m++)
																																																	for (int n = 0; n < width - 1; n++)
																																																		if (arrMapShow[m][n] == 2) {
																																																			level = 1;
																																																			if (m < this.width - 1
																																																					&& m >= 1
																																																					&& n >= 1
																																																					&& n < this.width - 1) {
																																																				if (arrMapShow[m + 1][n - 1] == -1) {
																																																					x[level] = m + 1;
																																																					y[level] = n - 1;
																																																				} else if (arrMapShow[m - 1][n + 1] == -1) {
																																																					x[level] = m - 1;
																																																					y[level] = n + 1;
																																																				}
																																																			}
																																																		}
																																																count = 0;
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (flag == 4) {
			for (i = 43; i >= 1; i--) {
				if (x[i] != -2) {
					temp4 = i;
					break;
				}
			}
		}
		if (flag == 3) {
			for (i = 43; i >= 1; i--) {
				if (x[i] != -2) {
					this.arrMapShow[x[i]][y[i]] = 1;
					computerDo(flag + 1);
					this.arrMapShow[x[i]][y[i]] = -1;
					temp3 = i;
					break;
				}
			}
		}
		if (flag == 2) {
			for (i = 43; i >= 1; i--) {
				if (x[i] != -2) {
					this.arrMapShow[x[i]][y[i]] = 2;
					computerDo(flag + 1);
					this.arrMapShow[x[i]][y[i]] = -1;
					temp2 = i;
					break;
				}
			}
		} else if (flag == 1) {
			for (i = 43; i >= 1; i--) {
				if (x[i] != -2) {
					this.arrMapShow[x[i]][y[i]] = 1;
					computerDo(flag + 1);
					this.arrMapShow[x[i]][y[i]] = -1;
					temp1 = i;
					break;
				}
			}
		} else if (flag == 0) {
			for (i = 43; i >= 1; i--) {
				if (x[i] != -2) {
					this.arrMapShow[x[i]][y[i]] = 2;
					computerDo(flag + 1);
					this.arrMapShow[x[i]][y[i]] = -1;
					if (i >= 19
							&& (temp4 != 19 && temp4 != 20 && temp4 != 31
									&& temp4 != 23 && temp4 != 24
									&& temp4 != 25 && temp4 != 26
									&& temp4 != 27 && temp4 != 28
									&& temp4 != 29 && temp4 != 30 && temp4 != 42)
							|| temp4 >= 19) {
						this.x = x[i];
						this.y = y[i];
						this.arrMapShow[this.x][this.y] = 2;
						setX(this.x);
						setY(this.y);
						if (judgeSuccess(this.x, this.y, true) == true) {
							JPanel jp = new JPanel();
							showDefeat(jp);
							cf.restart();
						}
						return;
					}
					if (i >= 19
							&& (temp3 != 20 && temp3 != 19 && temp3 != 31
									&& temp3 != 23 && temp3 != 24
									&& temp3 != 25 && temp3 != 26
									&& temp3 != 27 && temp3 != 28
									&& temp3 != 29 && temp3 != 30
									&& temp3 != 37 && temp3 != 42)
							|| (temp3 >= 19 && (temp3 != 20 && temp3 != 19
									&& temp3 != 31 && temp3 != 23
									&& temp3 != 24 && temp3 != 25
									&& temp3 != 26 && temp3 != 27
									&& temp3 != 28 && temp3 != 29
									&& temp3 != 30 && temp3 != 37 && temp3 != 42))) {
						this.x = x[i];
						this.y = y[i];
						this.arrMapShow[this.x][this.y] = 2;
						setX(this.x);
						setY(this.y);
						if (judgeSuccess(this.x, this.y, true) == true) {
							JPanel jp = new JPanel();
							showDefeat(jp);
							cf.restart();
						}
						return;
					}
					if (i >= 19
							&& (temp2 != 20 && temp2 != 19 && temp2 != 31
									&& temp2 != 23 && temp2 != 24
									&& temp2 != 25 && temp2 != 26
									&& temp2 != 27 && temp2 != 28
									&& temp2 != 29 && temp2 != 30 && temp2 != 42)
							|| temp2 >= 19) {
						this.x = x[i];
						this.y = y[i];
						this.arrMapShow[this.x][this.y] = 2;
						setX(this.x);
						setY(this.y);
						if (judgeSuccess(this.x, this.y, true) == true) {
							JPanel jp = new JPanel();
							showDefeat(jp);
							cf.restart();
						}
						return;
					}
					if (i >= 19
							&& (temp1 != 20 && temp1 != 19 && temp1 != 31
									&& temp1 != 23 && temp1 != 24
									&& temp1 != 25 && temp1 != 26
									&& temp1 != 27 && temp1 != 28
									&& temp1 != 29 && temp1 != 30
									&& temp1 != 37 && temp1 != 42)
							|| (temp1 >= 19 && (temp1 != 20 && temp1 != 19
									&& temp1 != 31 && temp1 != 23
									&& temp1 != 24 && temp1 != 25
									&& temp1 != 26 && temp1 != 27
									&& temp1 != 28 && temp1 != 29
									&& temp1 != 30 && temp1 != 37 && temp1 != 42))) {
						this.x = x[i];
						this.y = y[i];
						this.arrMapShow[this.x][this.y] = 2;
						setX(this.x);
						setY(this.y);
						if (judgeSuccess(this.x, this.y, true) == true) {
							JPanel jp = new JPanel();
							showDefeat(jp);
							cf.restart();
						}
						return;
					}
				}
			}
			for (i = 43; i >= 1; i--) {
				if (x[i] != -2) {
					this.x = x[i];
					this.y = y[i];
					break;
				}
			}
			this.arrMapShow[this.x][this.y] = 2;
			setX(this.x);
			setY(this.y);
			if (judgeSuccess(this.x, this.y, true) == true) {
				JPanel jp = new JPanel();
				showDefeat(jp);
				cf.restart();
			}
		}
	}

	public void first() {
		int height, width;
		width = getWidth();
		height = getHeight();
		setX(width / 2);
		setY(height / 2);
		this.arrMapShow[width / 2][height / 2] = 2;
	}

	// ��¼�������Ӻ�ĺ�������
	public void setX(int x) {
		this.x = x;
	}

	// ��¼�������Ӻ����������
	public void setY(int y) {
		this.y = y;
	}

	// ��ȡ�������ӵĺ�������
	public int getX() {
		return this.x;
	}

	// ��ȡ�������ӵ���������
	public int getY() {
		return this.y;
	}

	// ����������ĳһ�����ϰ˸��������ӵ����ֵ��
	// ��˸�����ֱ��ǣ����ҡ��ϡ��¡����ϡ����¡����ϡ�����
	int checkMax(int x, int y, int black_or_white) {
		int num = 0, max_num, max_temp = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right
		for (i = 1; i < 5; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		// judge left
		x_temp1 = x_temp;
		for (i = 1; i < 5; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num < 5)
			max_temp = num;

		// judge up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 5; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		// judge down
		y_temp1 = y_temp;
		for (i = 1; i < 5; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;

		// judge left_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 5; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		// judge right_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 5; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;

		// judge right_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 5; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		// judge left_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 5; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (num > max_temp && num < 5)
			max_temp = num;
		max_num = max_temp;
		return max_num;
	}

	// �ж� ��������
	boolean san_san(int x, int y, int black_or_white) {
		int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0, flag9 = 0, flag10 = 0, flag11 = 0, flag12 = 0;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge up
		if (x_temp1 >= 4) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == -1)
				flag1 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == -1)
				flag1 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == -1)
				flag1 = 1;
		}
		if (x_temp1 >= 4 && x_temp1 <= width - 1)
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == -1)
				flag1 = 1;
		// judge down
		x_temp1 = x_temp;
		if (x_temp1 < width - 4) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == -1)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == -1)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == -1)
				flag2 = 1;
		}
		if (x_temp1 >= 1 && x_temp1 < width - 4)
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == -1)
				flag2 = 1;
		if (x_temp1 >= 2 && x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1)
				flag9 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white)
				flag9 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1)
				flag9 = 1;
		}

		// judge left
		x_temp1 = x_temp;
		if (y_temp1 >= 4) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == -1)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == -1)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == -1)
				flag3 = 1;
		}
		if (y_temp1 >= 4 && y_temp1 < width - 1)
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == -1)
				flag3 = 1;
		// judge right
		y_temp1 = y_temp;
		if (y_temp1 < width - 4) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == -1)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == -1)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == -1)
				flag4 = 1;
		}
		if (y_temp1 >= 1 && y_temp1 < width - 4)
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == -1)
				flag4 = 1;
		if (y_temp1 >= 2 && y_temp1 < width - 2) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1)
				flag10 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white)
				flag10 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1)
				flag10 = 1;
		}

		// judge left_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		if (y_temp1 >= 4 && x_temp1 >= 4) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == -1)
				flag5 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == -1)
				flag5 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == -1)
				flag5 = 1;
		}
		if (y_temp1 >= 4 && x_temp1 >= 4 && y_temp1 < width - 1
				&& x_temp1 < width - 1)
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == -1)
				flag5 = 1;
		// judge right_down
		if (y_temp1 < width - 4 && x_temp1 < width - 4) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == -1)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == -1)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == -1)
				flag6 = 1;
		}
		if (y_temp1 >= 1 && x_temp1 >= 1 && y_temp1 < width - 4
				&& x_temp1 < width - 4)
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == -1)
				flag6 = 1;
		if (y_temp1 >= 2 && y_temp1 < width - 2 && x_temp1 >= 2
				&& x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1)
				flag11 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white)
				flag11 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1)
				flag11 = 1;
		}

		// judge left_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		if (y_temp1 >= 4 && x_temp1 < width - 4) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == -1)
				flag7 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == -1)
				flag7 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == -1)
				flag7 = 1;
		}
		if (y_temp1 >= 4 && x_temp1 >= 1 && y_temp1 < width - 1
				&& x_temp1 < width - 4)
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == -1)
				flag7 = 1;
		// judge right_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		if (y_temp1 < width - 4 && x_temp1 >= 4) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == -1)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == -1)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == -1)
				flag8 = 1;
		}
		if (y_temp1 >= 1 && x_temp1 >= 4 && y_temp1 < width - 4
				&& x_temp1 < width - 1)
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == -1)
				flag8 = 1;
		if (y_temp1 >= 2 && y_temp1 < width - 2 && x_temp1 >= 2
				&& x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1)
				flag12 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white)
				flag12 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1)
				flag12 = 1;
		}
		if (flag1 + flag2 + flag3 + flag4 + flag5 + flag6 + flag7 + flag8
				+ flag9 + flag10 + flag11 + flag12 >= 2) {
			return true;
		}
		return false;
	}

	// �ж���������
	boolean si_san(int x, int y, int black_or_white) {
		int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right_left_san
		if (x_temp1 < width - 4) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == -1)
				flag1 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == -1
					&& arrMapShow[x_temp1 + 4][y_temp1] == -1)
				flag1 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == -1)
				flag1 = 1;
		}
		if (x_temp1 >= 4) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == -1)
				flag1 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == -1
					&& arrMapShow[x_temp1 - 4][y_temp1] == -1)
				flag1 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == -1)
				flag1 = 1;
		}
		if (x_temp1 >= 2 && x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1)
				flag1 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white)
				flag1 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white)
				flag1 = 1;
		}
		// judge right_left_si
		if (x_temp1 < width - 5) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 < width - 4 && x_temp1 >= 1) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 < width - 3 && x_temp1 >= 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 < width - 2 && x_temp1 >= 3) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 5) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 4 && x_temp1 < width - 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 3 && x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 2 && x_temp1 < width - 3) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}

		// judge up_down_san
		if (y_temp1 < width - 4 && y_temp1 >= 1) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == -1)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == -1
					&& arrMapShow[x_temp1][y_temp1 + 4] == -1)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == -1)
				flag3 = 1;
		}
		if (y_temp1 >= 4) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == -1)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == -1
					&& arrMapShow[x_temp1][y_temp1 - 4] == -1)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == -1)
				flag3 = 1;

		}
		if (y_temp1 >= 2 && y_temp1 < width - 2) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white)
				flag3 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white)
				flag3 = 1;
		}
		// judge up_down_si
		if (y_temp1 < width - 5) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 5] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 5] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 < width - 4 && y_temp1 >= 1) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 < width - 3 && y_temp1 >= 2) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 < width - 2 && y_temp1 >= 3) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 5) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 4 && y_temp1 < width - 1) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 3 && y_temp1 < width - 2) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 2 && y_temp1 < width - 3) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == 3 - black_or_white)
				flag4 = 1;
		}

		// judge D_L_R_U_san
		if (y_temp1 < width - 4 && y_temp1 >= 1 && x_temp1 >= 4
				&& x_temp1 < width - 4) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == -1)
				flag5 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == -1
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == -1)
				flag5 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == -1)
				flag5 = 1;
		}
		if (y_temp1 >= 4 && y_temp1 < width - 1 && x_temp1 < width - 4
				&& x_temp1 >= 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == -1)
				flag5 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == -1
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == -1)
				flag5 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == -1)
				flag5 = 1;
		}
		if (y_temp1 >= 2 && x_temp1 < width - 2 && y_temp1 < width - 2
				&& x_temp1 >= 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1)
				flag5 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white)
				flag5 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white)
				flag5 = 1;
		}
		// judge D_L_R_U_si
		if (y_temp1 < width - 5 && x_temp1 >= 5) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 5] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 5] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 < width - 4 && x_temp1 >= 4 && y_temp1 >= 1
				&& x_temp1 < width - 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 < width - 3 && x_temp1 >= 3 && y_temp1 >= 2
				&& x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 < width - 2 && x_temp1 >= 2 && y_temp1 >= 3
				&& x_temp1 < width - 3) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 5 && x_temp1 < width - 5) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 - 5] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 - 5] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 4 && x_temp1 < width - 4 && y_temp1 < width - 1
				&& x_temp1 >= 1) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 3 && x_temp1 < width - 3 && y_temp1 < width - 2
				&& x_temp1 >= 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 2 && x_temp1 < width - 2 && y_temp1 < width - 3
				&& x_temp1 >= 3) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == 3 - black_or_white)
				flag6 = 1;
		}

		// judge L_U_R_D_san
		if (y_temp1 < width - 4 && y_temp1 >= 1 && x_temp1 < width - 4
				&& x_temp1 >= 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == -1)
				flag7 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == -1
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == -1)
				flag7 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == -1)
				flag7 = 1;
		}
		if (y_temp1 >= 4 && x_temp1 >= 4 && y_temp1 < width - 1
				&& x_temp1 < width - 1) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == -1)
				flag7 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == -1
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == -1)
				flag7 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == -1)
				flag7 = 1;
		}
		if (y_temp1 >= 2 && x_temp1 >= 2 && y_temp1 < width - 2
				&& x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1)
				flag7 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white)
				flag7 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white)
				flag7 = 1;
		}
		// judge L_U_R_D_si
		if (y_temp1 < width - 5 && x_temp1 < width - 5) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 + 5] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 + 5] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 < width - 4 && x_temp1 < width - 4 && y_temp1 >= 1
				&& x_temp1 >= 1) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 < width - 3 && x_temp1 < width - 3 && y_temp1 >= 2
				&& x_temp1 >= 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 < width - 2 && x_temp1 < width - 2 && y_temp1 >= 3
				&& x_temp1 >= 3) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 5 && x_temp1 >= 5) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 4 && x_temp1 >= 4 && y_temp1 < width - 1
				&& x_temp1 < width - 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 3 && x_temp1 >= 3 && y_temp1 < width - 2
				&& x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 2 && x_temp1 >= 2 && y_temp1 < width - 3
				&& x_temp1 < width - 3) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == 3 - black_or_white)
				flag8 = 1;
		}
		if (flag1 + flag3 + flag5 + flag7 >= 1
				&& flag2 + flag4 + flag6 + flag8 >= 1)
			return true;
		return false;
	}

	// �ж���������
	boolean si_si(int x, int y, int black_or_white) {
		int flag2 = 0, flag4 = 0, flag6 = 0, flag8 = 0;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right_left_si
		if (x_temp1 < width - 5) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 < width - 4 && x_temp1 >= 1) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 < width - 3 && x_temp1 >= 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 < width - 2 && x_temp1 >= 3) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 5) {
			if (arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 4 && x_temp1 < width - 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 3 && x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}
		if (x_temp1 >= 2 && x_temp1 < width - 3) {
			if (arrMapShow[x_temp1 + 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1] == 3 - black_or_white)
				flag2 = 1;
		}

		// judge up_down_si
		if (y_temp1 < width - 5) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 5] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 5] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 < width - 4 && y_temp1 >= 1) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 4] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 < width - 3 && y_temp1 >= 2) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 < width - 2 && y_temp1 >= 3) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 5) {
			if (arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 4 && y_temp1 < width - 1) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 4] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 3 && y_temp1 < width - 2) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 3] == 3 - black_or_white)
				flag4 = 1;
		}
		if (y_temp1 >= 2 && y_temp1 < width - 3) {
			if (arrMapShow[x_temp1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == 3 - black_or_white)
				flag4 = 1;
			else if (arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1][y_temp1 - 2] == 3 - black_or_white)
				flag4 = 1;
		}

		// judge D_L_R_U_si
		if (y_temp1 < width - 5 && x_temp1 >= 5) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 5] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 5] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 < width - 4 && x_temp1 >= 4 && y_temp1 >= 1
				&& x_temp1 < width - 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 + 4] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 < width - 3 && x_temp1 >= 3 && y_temp1 >= 2
				&& x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 < width - 2 && x_temp1 >= 2 && y_temp1 >= 3
				&& x_temp1 < width - 3) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 5 && x_temp1 < width - 5) {
			if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 - 1] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 - 1] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 4 && x_temp1 < width - 4 && y_temp1 < width - 1
				&& x_temp1 >= 1) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 - 4] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 3 && x_temp1 < width - 3 && y_temp1 < width - 2
				&& x_temp1 >= 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 - 3] == 3 - black_or_white)
				flag6 = 1;
		}
		if (y_temp1 >= 2 && x_temp1 < width - 2 && y_temp1 < width - 3
				&& x_temp1 >= 3) {
			if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == 3 - black_or_white)
				flag6 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 - 2] == 3 - black_or_white)
				flag6 = 1;
		}

		// judge L_U_R_D_si
		if (y_temp1 < width - 5 && x_temp1 < width - 5) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 + 5] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == black_or_white
					&& arrMapShow[x_temp1 + 5][y_temp1 + 5] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 < width - 4 && x_temp1 < width - 4 && y_temp1 >= 1
				&& x_temp1 >= 1) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 + 4][y_temp1 + 4] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 < width - 3 && x_temp1 < width - 3 && y_temp1 >= 2
				&& x_temp1 >= 2) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 < width - 2 && x_temp1 < width - 2 && y_temp1 >= 3
				&& x_temp1 >= 3) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 5 && x_temp1 >= 5) {
			if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 4 && x_temp1 >= 4 && y_temp1 < width - 1
				&& x_temp1 < width - 1) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == -1
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == black_or_white
					&& arrMapShow[x_temp1 - 4][y_temp1 - 4] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 3 && x_temp1 >= 3 && y_temp1 < width - 2
				&& x_temp1 < width - 2) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == black_or_white
					&& arrMapShow[x_temp1 - 3][y_temp1 - 3] == 3 - black_or_white)
				flag8 = 1;
		}
		if (y_temp1 >= 2 && x_temp1 >= 2 && y_temp1 < width - 3
				&& x_temp1 < width - 3) {
			if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == black_or_white
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == -1
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == 3 - black_or_white)
				flag8 = 1;
			else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp1 + 2][y_temp1 + 2] == black_or_white
					&& arrMapShow[x_temp1 + 3][y_temp1 + 3] == black_or_white
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == black_or_white
					&& arrMapShow[x_temp1 - 2][y_temp1 - 2] == 3 - black_or_white)
				flag8 = 1;
		}
		if (flag2 + flag4 + flag6 + flag8 >= 2)
			return true;
		return false;
	}

	// �ж���������
	boolean si_er(int x, int y, int black_or_white) {
		int num = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge left_up
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (x_temp1 < 0 || y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == 0 || y_temp1 == width - 1 || x_temp == width - 2
				|| y_temp == 1)
			return false;
		else if (x_temp1 >= 1 && x_temp <= width - 3 && y_temp >= 2
				&& y_temp1 <= width - 2)
			if (num == 2
					&& arrMapShow[x_temp1 - 1][y_temp1 + 1] == 3 - black_or_white
					&& arrMapShow[x_temp + 1][y_temp - 1] == -1
					&& arrMapShow[x_temp + 2][y_temp - 2] == -1)
				return true;
		// judge right_down
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (x_temp1 >= width || y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == width - 1 || y_temp1 == 0 || x_temp == 1
				|| y_temp == width - 2)
			return false;
		else if (x_temp >= 2 && x_temp1 <= width - 2 && y_temp1 >= 1
				&& y_temp <= width - 3)
			if (num == 2
					&& arrMapShow[x_temp1 + 1][y_temp1 - 1] == 3 - black_or_white
					&& arrMapShow[x_temp - 1][y_temp + 1] == -1
					&& arrMapShow[x_temp - 2][y_temp + 2] == -1)
				return true;
		// judge left_down
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (x_temp1 < 0 || y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == 0 || y_temp1 == 0 || x_temp == width - 2
				|| y_temp == width - 2)
			return false;
		else if (x_temp1 >= 1 && x_temp <= width - 3 && y_temp1 >= 1
				&& y_temp <= width - 4)
			if (num == 2
					&& arrMapShow[x_temp1 - 1][y_temp1 - 1] == 3 - black_or_white
					&& arrMapShow[x_temp + 1][y_temp + 1] == -1
					&& arrMapShow[x_temp + 2][y_temp + 2] == -1)
				return true;
		// judge right_up
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (x_temp1 >= width || y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == width - 1 || y_temp1 == width - 1 || x_temp == 1
				|| y_temp == 1)
			return false;
		else if (x_temp >= 2 && x_temp1 <= width - 2 && y_temp >= 2
				&& y_temp1 <= width - 2)
			if (num == 2
					&& arrMapShow[x_temp1 + 1][y_temp1 + 1] == 3 - black_or_white
					&& arrMapShow[x_temp - 1][y_temp - 1] == -1
					&& arrMapShow[x_temp - 2][y_temp - 2] == -1)
				return true;
		// judge right
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp == 1 || x_temp1 == width - 1)
			return false;
		else if (x_temp >= 2 && x_temp1 <= width - 2)
			if (num == 2
					&& arrMapShow[x_temp1 + 1][y_temp1] == 3 - black_or_white
					&& arrMapShow[x_temp - 1][y_temp] == -1
					&& arrMapShow[x_temp - 2][y_temp] == -1)
				return true;
		// judeg left
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == 0 || x_temp == width - 2)
			return false;
		else if (x_temp1 >= 1 && x_temp <= width - 3)
			if (num == 2
					&& arrMapShow[x_temp1 - 1][y_temp1] == 3 - black_or_white
					&& arrMapShow[x_temp + 1][y_temp] == -1
					&& arrMapShow[x_temp + 2][y_temp] == -1)
				return true;
		// judge up
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (y_temp == 1 || y_temp1 == width - 1)
			return false;
		else if (y_temp >= 2 && y_temp1 <= width - 2)
			if (num == 2
					&& arrMapShow[x_temp1][y_temp1 + 1] == 3 - black_or_white
					&& arrMapShow[x_temp][y_temp - 1] == -1
					&& arrMapShow[x_temp][y_temp - 2] == -1)
				return true;
		// judge down
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (y_temp1 == 1 || y_temp == width - 1)
			return false;
		else if (y_temp1 >= 2 && y_temp <= width - 2)
			if (num == 2
					&& arrMapShow[x_temp1][y_temp1 - 1] == 3 - black_or_white
					&& arrMapShow[x_temp][y_temp + 1] == -1
					&& arrMapShow[x_temp][y_temp + 2] == -1)
				return true;

		return false;
	}

	// �жϻ������
	boolean huo_er(int x, int y, int black_or_white) {
		int num = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge left_up
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (x_temp1 < 0 || y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == 0 || y_temp1 == width - 1 || x_temp1 == width - 1
				|| y_temp == 0)
			return false;
		else if (x_temp1 >= 1 && x_temp <= width - 2 && y_temp >= 1
				&& y_temp1 <= width - 2)
			if (num == 2 && arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp + 1][y_temp - 1] == -1)
				return true;
		// judge right_down
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (x_temp1 >= width || y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == width - 1 || y_temp1 == 0 || x_temp == 0
				|| y_temp1 == width - 1)
			return false;
		else if (x_temp >= 1 && x_temp1 <= width - 2 && y_temp1 >= 1
				&& y_temp <= width - 2)
			if (num == 2 && arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp - 1][y_temp + 1] == -1)
				return true;
		// judge left_down
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (x_temp1 < 0 || y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == 0 || y_temp1 == 0 || x_temp1 == width - 1
				|| y_temp1 == width - 1)
			return false;
		else if (x_temp1 >= 1 && x_temp <= width - 2 && y_temp1 >= 1
				&& y_temp <= width - 2)
			if (num == 2 && arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp + 1][y_temp + 1] == -1)
				return true;
		// judge right_up
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (x_temp1 >= width || y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == width - 1 || y_temp1 == width - 1 || x_temp == 0
				|| y_temp == 0)
			return false;
		else if (x_temp >= 1 && x_temp1 <= width - 2 && y_temp >= 1
				&& y_temp1 <= width - 2)
			if (num == 2 && arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp - 1][y_temp - 1] == -1)
				return true;
		// judge right
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp == 0 || x_temp1 == width - 1)
			return false;
		else if (x_temp >= 1 && x_temp1 <= width - 2)
			if (num == 2 && arrMapShow[x_temp1 + 1][y_temp1] == -1
					&& arrMapShow[x_temp - 1][y_temp] == -1)
				return true;
		// judeg left
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (x_temp1 == 0 || x_temp == width - 1)
			return false;
		else if (x_temp1 >= 1 && x_temp <= width - 2)
			if (num == 2 && arrMapShow[x_temp1 - 1][y_temp1] == -1
					&& arrMapShow[x_temp + 1][y_temp] == -1)
				return true;
		// judge up
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (y_temp == 0 || y_temp1 == width - 1)
			return false;
		else if (y_temp >= 1 && y_temp1 <= width - 2)
			if (num == 2 && arrMapShow[x_temp1][y_temp1 + 1] == -1
					&& arrMapShow[x_temp][y_temp - 1] == -1)
				return true;
		// judge down
		num = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else
				break;
		}
		if (y_temp1 == 0 || y_temp == width - 1)
			return false;
		else if (y_temp1 >= 1 && y_temp <= width - 2)
			if (num == 2 && arrMapShow[x_temp1][y_temp1 - 1] == -1
					&& arrMapShow[x_temp][y_temp + 1] == -1)
				return true;

		return false;
	}

	// �ж�бʮ�ֶ�����
	boolean xie_zi_er(int x, int y, int black_or_white) {
		int num1 = 0, i, num2 = 0;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;

		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (x_temp1 >= width || y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 -= 1;
			x_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;

		num1 = 0;
		num2 = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (x_temp1 < 0 || y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 -= 1;
			x_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;

		num1 = 0;
		num2 = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (x_temp1 >= width || y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 += 1;
			x_temp1 += 1;
			if (y_temp1 >= width || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;

		num1 = 0;
		num2 = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (x_temp1 < 0 || y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 += 1;
			x_temp1 += 1;
			if (y_temp1 >= width || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;
		return false;
	}

	// �ж�ʮ�ֶ�����
	boolean shi_zi_er(int x, int y, int black_or_white) {
		int num1 = 0, i, num2 = 0;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;

		// judge right
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		// judeg down
		x_temp1 = x_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;
		// judge right
		num1 = 0;
		num2 = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		// judge up
		x_temp1 = x_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;
		// judge left
		num1 = 0;
		num2 = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		// judge down
		x_temp1 = x_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;
		// judge left
		num1 = 0;
		num2 = 0;
		x_temp = x;
		y_temp = y;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 3; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		// judge up
		x_temp1 = x_temp;
		for (i = 1; i < 3; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num1 == 1 && num2 == 1)
			return true;
		return false;
	}

	// �жϻ�϶�����
	boolean huo_duan_er(int x, int y, int black_or_white) {
		int x_temp, y_temp;
		x_temp = x;
		y_temp = y;
		// judge left_right
		if (x_temp >= 2 && x_temp <= width - 3)
			if (arrMapShow[x_temp - 1][y_temp] == black_or_white
					&& arrMapShow[x_temp - 2][y_temp] == -1
					&& arrMapShow[x_temp + 1][y_temp] == black_or_white
					&& arrMapShow[x_temp + 2][y_temp] == -1)
				return true;
		if (x_temp >= 2 && x_temp <= width - 4)
			if (arrMapShow[x_temp - 1][y_temp] == black_or_white
					&& arrMapShow[x_temp - 2][y_temp] == -1
					&& arrMapShow[x_temp + 1][y_temp] == -1
					&& arrMapShow[x_temp + 2][y_temp] == black_or_white
					&& arrMapShow[x_temp + 3][y_temp] == -1)
				return true;
		if (x_temp >= 3 && x_temp <= width - 3)
			if (arrMapShow[x_temp - 1][y_temp] == -1
					&& arrMapShow[x_temp - 2][y_temp] == black_or_white
					&& arrMapShow[x_temp - 3][y_temp] == -1
					&& arrMapShow[x_temp + 1][y_temp] == black_or_white
					&& arrMapShow[x_temp + 2][y_temp] == -1)
				return true;
		// judge up_down
		if (y_temp >= 2 && y_temp <= width - 3)
			if (arrMapShow[x_temp][y_temp - 1] == black_or_white
					&& arrMapShow[x_temp][y_temp - 2] == -1
					&& arrMapShow[x_temp][y_temp + 1] == black_or_white
					&& arrMapShow[x_temp][y_temp + 2] == -1)
				return true;
		if (y_temp >= 2 && y_temp <= width - 4)
			if (arrMapShow[x_temp][y_temp - 1] == black_or_white
					&& arrMapShow[x_temp][y_temp - 2] == -1
					&& arrMapShow[x_temp][y_temp + 1] == -1
					&& arrMapShow[x_temp][y_temp + 2] == black_or_white
					&& arrMapShow[x_temp][y_temp + 3] == -1)
				return true;
		if (y_temp >= 3 && y_temp <= width - 3)
			if (arrMapShow[x_temp][y_temp - 1] == -1
					&& arrMapShow[x_temp][y_temp - 2] == black_or_white
					&& arrMapShow[x_temp][y_temp - 3] == -1
					&& arrMapShow[x_temp][y_temp + 1] == black_or_white
					&& arrMapShow[x_temp][y_temp + 2] == -1)
				return true;

		// judge LU_RD
		if (y_temp >= 2 && y_temp <= width - 3 && x_temp >= 2
				&& x_temp <= width - 3)
			if (arrMapShow[x_temp - 1][y_temp - 1] == black_or_white
					&& arrMapShow[x_temp - 2][y_temp - 2] == -1
					&& arrMapShow[x_temp + 1][y_temp + 1] == black_or_white
					&& arrMapShow[x_temp + 2][y_temp + 2] == -1)
				return true;
		if (y_temp >= 2 && y_temp <= width - 4 && x_temp >= 2
				&& x_temp <= width - 4)
			if (arrMapShow[x_temp - 1][y_temp - 1] == black_or_white
					&& arrMapShow[x_temp - 2][y_temp - 2] == -1
					&& arrMapShow[x_temp + 1][y_temp + 1] == -1
					&& arrMapShow[x_temp + 2][y_temp + 2] == black_or_white
					&& arrMapShow[x_temp + 3][y_temp + 3] == -1)
				return true;
		if (y_temp >= 3 && y_temp <= width - 3 && x_temp >= 3
				&& x_temp <= width - 3)
			if (arrMapShow[x_temp - 1][y_temp - 1] == -1
					&& arrMapShow[x_temp - 2][y_temp - 2] == black_or_white
					&& arrMapShow[x_temp - 3][y_temp - 3] == -1
					&& arrMapShow[x_temp + 1][y_temp + 1] == black_or_white
					&& arrMapShow[x_temp + 2][y_temp + 2] == -1)
				return true;

		// judge LD_RU
		if (y_temp >= 2 && y_temp <= width - 3 && x_temp >= 2
				&& x_temp <= width - 3)
			if (arrMapShow[x_temp - 1][y_temp + 1] == black_or_white
					&& arrMapShow[x_temp - 2][y_temp + 2] == -1
					&& arrMapShow[x_temp + 1][y_temp - 1] == black_or_white
					&& arrMapShow[x_temp + 2][y_temp - 2] == -1)
				return true;
		if (y_temp >= 2 && y_temp <= width - 4 && x_temp >= 2
				&& x_temp <= width - 4)
			if (arrMapShow[x_temp - 1][y_temp + 1] == black_or_white
					&& arrMapShow[x_temp - 2][y_temp + 2] == -1
					&& arrMapShow[x_temp + 1][y_temp - 1] == -1
					&& arrMapShow[x_temp + 2][y_temp - 2] == black_or_white
					&& arrMapShow[x_temp + 3][y_temp - 3] == -1)
				return true;
		if (y_temp >= 3 && y_temp <= width - 3 && x_temp >= 3
				&& x_temp <= width - 3)
			if (arrMapShow[x_temp - 1][y_temp + 1] == -1
					&& arrMapShow[x_temp - 2][y_temp + 2] == black_or_white
					&& arrMapShow[x_temp - 3][y_temp + 3] == -1
					&& arrMapShow[x_temp + 1][y_temp - 1] == black_or_white
					&& arrMapShow[x_temp + 2][y_temp - 2] == -1)
				return true;
		return false;
	}

	// �жϻ���������
	boolean huo_lian_san(int x, int y, int black_or_white) {
		int num = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (x_temp1 < this.width - 1 && x_temp >= 1)
				if (this.arrMapShow[x_temp1 + 1][y_temp1] == -1
						&& this.arrMapShow[x_temp - 1][y_temp] == -1)
					return true;
		}
		// judge left
		x_temp1 = x_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (x_temp1 >= 1 && x_temp < this.width - 1)
				if (this.arrMapShow[x_temp1 - 1][y_temp1] == -1
						&& this.arrMapShow[x_temp + 1][y_temp] == -1)
					return true;
		}

		// judge up
		num = 0;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (y_temp1 >= 1 && y_temp < this.width - 1)
				if (this.arrMapShow[x_temp1][y_temp1 - 1] == -1
						&& this.arrMapShow[x_temp][y_temp + 1] == -1)
					return true;
		}
		// judge down
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (y_temp >= 1 && y_temp1 < this.width - 1)
				if (this.arrMapShow[x_temp1][y_temp1 + 1] == -1
						&& this.arrMapShow[x_temp][y_temp - 1] == -1)
					return true;
		}

		// judge left_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (x_temp1 >= 1 && y_temp1 >= 1 && x_temp < this.width - 1
					&& y_temp < this.width - 1)
				if (this.arrMapShow[x_temp1 - 1][y_temp1 - 1] == -1
						&& this.arrMapShow[x_temp + 1][y_temp + 1] == -1)
					return true;
		}
		// judge right_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 >= width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (x_temp >= 1 && y_temp >= 1 && x_temp1 < this.width - 1
					&& y_temp1 < this.width - 1)
				if (this.arrMapShow[x_temp1 + 1][y_temp1 + 1] == -1
						&& this.arrMapShow[x_temp - 1][y_temp - 1] == -1)
					return true;
		}

		// judge right_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 >= width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (x_temp >= 1 && y_temp1 >= 1 && x_temp1 < this.width - 1
					&& y_temp < this.width - 1)
				if (this.arrMapShow[x_temp1 + 1][y_temp1 - 1] == -1
						&& this.arrMapShow[x_temp - 1][y_temp + 1] == -1)
					return true;
		}

		// judge left_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (this.arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (num == 3) {
			if (x_temp1 >= 1 && y_temp >= 1 && x_temp < this.width - 1
					&& y_temp1 < this.width - 1)
				if (this.arrMapShow[x_temp1 - 1][y_temp1 + 1] == -1
						&& this.arrMapShow[x_temp + 1][y_temp - 1] == -1)
					return true;
		}
		return false;
	}

	// �ж���������
	boolean si_lian_san(int x, int y, int black_or_white) {
		int num = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (x_temp1 <= width - 2 && x_temp >= 1)
			if (num == 3) {
				if (arrMapShow[x_temp1 + 1][y_temp1] == 3 - black_or_white
						&& arrMapShow[x_temp - 1][y_temp] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1 + 1][y_temp1] == 3 - black_or_white
						&& arrMapShow[x_temp - 1][y_temp] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}
		// judge left
		x_temp1 = x_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (x_temp1 >= 1 && x_temp <= width - 2)
			if (num == 3) {
				if (arrMapShow[x_temp1 - 1][y_temp1] == 3 - black_or_white
						&& arrMapShow[x_temp + 1][y_temp] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1 - 1][y_temp1] == 3 - black_or_white
						&& arrMapShow[x_temp + 1][y_temp] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}

		// judge up
		num = 0;
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (y_temp1 >= 1 && y_temp <= width - 2)
			if (num == 3) {
				if (arrMapShow[x_temp1][y_temp1 - 1] == 3 - black_or_white
						&& arrMapShow[x_temp][y_temp + 1] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1][y_temp1 - 1] == 3 - black_or_white
						&& arrMapShow[x_temp][y_temp + 1] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}
		// judge down
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (y_temp1 <= width - 2 && y_temp >= 1)
			if (num == 3) {
				if (arrMapShow[x_temp1][y_temp1 + 1] == 3 - black_or_white
						&& arrMapShow[x_temp][y_temp - 1] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1][y_temp1 + 1] == 3 - black_or_white
						&& arrMapShow[x_temp][y_temp - 1] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}

		// judge left_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (x_temp1 >= 1 && y_temp1 >= 1 && y_temp <= width - 2
				&& x_temp <= width - 2)
			if (num == 3) {
				if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == 3 - black_or_white
						&& arrMapShow[x_temp + 1][y_temp + 1] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1 - 1][y_temp1 - 1] == 3 - black_or_white
						&& arrMapShow[x_temp + 1][y_temp + 1] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}
		// judge right_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (x_temp >= 1 && y_temp >= 1 && y_temp1 <= width - 2
				&& x_temp1 <= width - 2)
			if (num == 3) {
				if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == 3 - black_or_white
						&& arrMapShow[x_temp - 1][y_temp - 1] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1 + 1][y_temp1 + 1] == 3 - black_or_white
						&& arrMapShow[x_temp - 1][y_temp - 1] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}

		// judge right_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (x_temp >= 1 && y_temp1 >= 1 && y_temp <= width - 2
				&& x_temp1 <= width - 2)
			if (num == 3) {
				if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == 3 - black_or_white
						&& arrMapShow[x_temp - 1][y_temp + 1] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1 + 1][y_temp1 - 1] == 3 - black_or_white
						&& arrMapShow[x_temp - 1][y_temp + 1] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}

		// judge left_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num--;
			else
				break;
		}
		if (x_temp1 >= 1 && y_temp >= 1 && y_temp1 <= width - 2
				&& x_temp <= width - 2)
			if (num == 3) {
				if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == 3 - black_or_white
						&& arrMapShow[x_temp + 1][y_temp - 1] == 3 - black_or_white)
					return false;
				else if (arrMapShow[x_temp1 - 1][y_temp1 + 1] == 3 - black_or_white
						&& arrMapShow[x_temp + 1][y_temp - 1] == -1) {
					if (black_or_white == 2)
						return true;
					else
						return false;
				} else
					return true;
			}
		return false;
	}

	boolean duan_lian_san(int x, int y, int black_or_white) {
		int num1 = 0, num2 = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width - 1)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& x_temp1 - x_temp != 3) {
				num1--;
				break;
			} else
				break;
		}

		x_temp1 = x_temp;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			if (x_temp1 <= 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& x_temp - x_temp1 != 3) {
				num2--;
				break;
			} else
				break;
		}
		if (num1 != 3 && num2 != 3 && num1 + num2 > 2)
			return true;

		// judge up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 4; i++) {
			y_temp1 -= 1;
			if (y_temp1 <= 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& y_temp - y_temp1 != 3) {
				num1--;
				break;
			} else
				break;
		}
		// judge down
		y_temp1 = y_temp;
		for (i = 1; i < 4; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width - 1)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& y_temp1 - y_temp != 3) {
				num2--;
				break;
			} else
				break;
		}
		if (num1 != 3 && num2 != 3 && num1 + num2 > 2)
			return true;

		// judge left_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 <= 0 || x_temp1 <= 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& x_temp - x_temp1 != 3) {
				num1--;
				break;
			} else
				break;
		}
		// judge right_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 >= width - 1 || x_temp1 >= width - 1)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& x_temp1 - x_temp != 3) {
				num2--;
				break;
			} else
				break;
		}
		if (num1 != 3 && num2 != 3 && num1 + num2 > 2)
			return true;

		// judge right_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 <= 0 || x_temp1 >= width - 1)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& x_temp1 - x_temp != 3) {
				num1--;
				break;
			} else
				break;
		}
		// judge left_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 >= width - 1 || x_temp1 <= 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white
					&& x_temp - x_temp1 != 3) {
				num2--;
				break;
			} else
				break;
		}
		if (num1 != 3 && num2 != 3 && num1 + num2 > 2)
			return true;
		return false;
	}

	boolean lian_si(int x, int y, int black_or_white) {
		int num1 = 0, max_num, max_temp = 0, num2 = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right
		for (i = 1; i < 5; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		if (num1 == 4)
			return true;
		// judge left
		x_temp1 = x_temp;
		for (i = 1; i < 5; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		max_temp = num1 + num2;

		// judge up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 5; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		if (num1 == 4)
			return true;
		// judge down
		y_temp1 = y_temp;
		for (i = 1; i < 5; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num2 == 4)
			return true;
		if (num1 + num2 > max_temp)
			max_temp = num1 + num2;

		// judge left_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 5; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		if (num1 == 4)
			return true;
		// judge right_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 5; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num2 == 4)
			return true;
		if (num1 + num2 > max_temp)
			max_temp = num1 + num2;

		// judge right_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 5; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num1--;
			else
				break;
		}
		if (num1 == 4)
			return true;
		// judge left_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 5; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else if (arrMapShow[x_temp1][y_temp1] == 3 - black_or_white)
				num2--;
			else
				break;
		}
		if (num2 == 4)
			return true;
		if (num1 + num2 > max_temp)
			max_temp = num1 + num2;
		max_num = max_temp;
		if (max_num > 3)
			return true;
		return false;
	}

	// �ж�����������
	boolean lian_duan_si(int x, int y, int black_or_white) {
		int x_temp = x, y_temp = y;
		// judeg left
		if (x_temp <= width - 5 && x_temp >= 0)
			if (arrMapShow[x_temp + 1][y_temp] == -1
					&& arrMapShow[x_temp + 2][y_temp] == black_or_white
					&& arrMapShow[x_temp + 3][y_temp] == black_or_white
					&& arrMapShow[x_temp + 4][y_temp] == black_or_white)
				return true;
		// judge right
		if (x_temp <= width - 5 && x_temp >= 4)
			if (arrMapShow[x_temp - 1][y_temp] == -1
					&& arrMapShow[x_temp - 2][y_temp] == black_or_white
					&& arrMapShow[x_temp - 3][y_temp] == black_or_white
					&& arrMapShow[x_temp - 4][y_temp] == black_or_white)
				return true;
		// judeg up
		if (y_temp <= width - 5 && y_temp >= 0)
			if (arrMapShow[x_temp][y_temp + 1] == -1
					&& arrMapShow[x_temp][y_temp + 2] == black_or_white
					&& arrMapShow[x_temp][y_temp + 3] == black_or_white
					&& arrMapShow[x_temp][y_temp + 4] == black_or_white)
				return true;
		// judge down
		if (y_temp <= width - 5 && y_temp >= 4)
			if (arrMapShow[x_temp][y_temp - 1] == -1
					&& arrMapShow[x_temp][y_temp - 2] == black_or_white
					&& arrMapShow[x_temp][y_temp - 3] == black_or_white
					&& arrMapShow[x_temp][y_temp - 4] == black_or_white)
				return true;

		// judge U_L
		if (x_temp <= width - 5 && x_temp >= 0 && y_temp <= width - 5
				&& y_temp >= 0)
			if (arrMapShow[x_temp + 1][y_temp + 1] == -1
					&& arrMapShow[x_temp + 2][y_temp + 2] == black_or_white
					&& arrMapShow[x_temp + 3][y_temp + 3] == black_or_white
					&& arrMapShow[x_temp + 4][y_temp + 4] == black_or_white)
				return true;
		// judge D_L
		if (x_temp <= width - 5 && x_temp >= 0 && y_temp <= width - 5
				&& y_temp >= 4)
			if (arrMapShow[x_temp + 1][y_temp - 1] == -1
					&& arrMapShow[x_temp + 2][y_temp - 2] == black_or_white
					&& arrMapShow[x_temp + 3][y_temp - 3] == black_or_white
					&& arrMapShow[x_temp + 4][y_temp - 4] == black_or_white)
				return true;
		// judge U_R
		if (x_temp <= width - 5 && x_temp >= 4 && y_temp <= width - 5
				&& y_temp >= 0)
			if (arrMapShow[x_temp - 1][y_temp + 1] == -1
					&& arrMapShow[x_temp - 2][y_temp + 2] == black_or_white
					&& arrMapShow[x_temp - 3][y_temp + 3] == black_or_white
					&& arrMapShow[x_temp - 4][y_temp + 4] == black_or_white)
				return true;
		// judge D_R
		if (x_temp <= width - 5 && x_temp >= 4 && y_temp <= width - 5
				&& y_temp >= 4)
			if (arrMapShow[x_temp - 1][y_temp - 1] == -1
					&& arrMapShow[x_temp - 2][y_temp - 2] == black_or_white
					&& arrMapShow[x_temp - 3][y_temp - 3] == black_or_white
					&& arrMapShow[x_temp - 4][y_temp - 4] == black_or_white)
				return true;
		return false;
	}

	boolean duan_lian_si(int x, int y, int black_or_white) {
		int num1 = 0, max_temp = 0, num2 = 0, i;
		int x_temp = x, y_temp = y;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// judge right
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			if (x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else
				break;
		}
		// judge left
		x_temp1 = x_temp;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else
				break;
		}
		max_temp = num1 + num2;
		if (max_temp >= 4)
			return true;

		// judge up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 4; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else
				break;
		}
		// judge down
		y_temp1 = y_temp;
		for (i = 1; i < 4; i++) {
			y_temp1 += 1;
			if (y_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else
				break;
		}
		max_temp = num1 + num2;
		if (max_temp >= 4)
			return true;

		// judge left_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else
				break;
		}
		// judge right_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else
				break;
		}
		max_temp = num1 + num2;
		if (max_temp >= 4)
			return true;

		// judge right_up
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 0;
		num2 = 0;
		for (i = 1; i < 4; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 >= width)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num1++;
			else
				break;
		}
		// judge left_down
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (i = 1; i < 4; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 >= width || x_temp1 < 0)
				break;
			if (arrMapShow[x_temp1][y_temp1] == black_or_white)
				num2++;
			else
				break;
		}
		max_temp = num1 + num2;
		if (max_temp >= 4)
			return true;
		return false;
	}

	boolean zhouwei3(int x, int y, int black_or_white) {
		int x_temp = x, y_temp = y, num1 = 0, num2 = 0;
		if (x_temp >= 1) {
			if (arrMapShow[x_temp - 1][y_temp] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1) {
			if (arrMapShow[x_temp + 1][y_temp] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp + 1][y_temp] == 3 - black_or_white)
				num2++;
		}
		if (y_temp >= 1) {
			if (arrMapShow[x_temp][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (y_temp < width - 1) {
			if (arrMapShow[x_temp][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp >= 1 && y_temp >= 1) {
			if (arrMapShow[x_temp - 1][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1 && y_temp >= 1) {
			if (arrMapShow[x_temp + 1][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp + 1][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp >= 1 && y_temp < width - 1) {
			if (arrMapShow[x_temp - 1][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1 && y_temp < width - 1) {
			if (arrMapShow[x_temp + 1][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (num2 - num1 >= 3)
			return true;
		return false;
	}

	boolean zhouwei2(int x, int y, int black_or_white) {
		int x_temp = x, y_temp = y, num1 = 0, num2 = 0;
		if (x_temp >= 1) {
			if (arrMapShow[x_temp - 1][y_temp] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1) {
			if (arrMapShow[x_temp + 1][y_temp] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp + 1][y_temp] == 3 - black_or_white)
				num2++;
		}
		if (y_temp >= 1) {
			if (arrMapShow[x_temp][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (y_temp < width - 1) {
			if (arrMapShow[x_temp][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp >= 1 && y_temp >= 1) {
			if (arrMapShow[x_temp - 1][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1 && y_temp >= 1) {
			if (arrMapShow[x_temp + 1][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp + 1][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp >= 1 && y_temp < width - 1) {
			if (arrMapShow[x_temp - 1][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1 && y_temp < width - 1) {
			if (arrMapShow[x_temp + 1][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (num2 - num1 >= 2)
			return true;
		return false;
	}

	boolean zhouwei1(int x, int y, int black_or_white) {
		int x_temp = x, y_temp = y, num1 = 0, num2 = 0;
		if (x_temp >= 1) {
			if (arrMapShow[x_temp - 1][y_temp] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1) {
			if (arrMapShow[x_temp + 1][y_temp] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp + 1][y_temp] == 3 - black_or_white)
				num2++;
		}
		if (y_temp >= 1) {
			if (arrMapShow[x_temp][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (y_temp < width - 1) {
			if (arrMapShow[x_temp][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp >= 1 && y_temp >= 1) {
			if (arrMapShow[x_temp - 1][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1 && y_temp >= 1) {
			if (arrMapShow[x_temp + 1][y_temp - 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp + 1][y_temp - 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp >= 1 && y_temp < width - 1) {
			if (arrMapShow[x_temp - 1][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp - 1][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (x_temp < width - 1 && y_temp < width - 1) {
			if (arrMapShow[x_temp + 1][y_temp + 1] == black_or_white)
				num1++;
			else if (arrMapShow[x_temp][y_temp + 1] == 3 - black_or_white)
				num2++;
		}
		if (num2 - num1 >= 1)
			return true;
		return false;
	}

	// �ж�ʤ��
	public boolean judgeSuccess(int x, int y, boolean isodd) {
		int num1 = 1, num2 = 1;
		int arrvalue;
		int x_temp = x, y_temp = y;
		if (isodd)
			arrvalue = 2;
		else
			arrvalue = 1;
		int x_temp1 = x_temp, y_temp1 = y_temp;
		// �ж��ұ�
		for (int i = 1; i < 6; i++) {
			x_temp1 += 1;
			if (x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num1++;
			else
				break;
		}
		if (num1 == 5)
			return true;
		// �ж����
		x_temp1 = x_temp;
		for (int i = 1; i < 6; i++) {
			x_temp1 -= 1;
			if (x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num2++;
			else
				break;
		}
		if (num2 == 5)
			return true;
		if ((num1 + num2) >= 6)
			return true;

		// �ж��Ϸ�
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 1;
		num2 = 1;
		for (int i = 1; i < 6; i++) {
			y_temp1 -= 1;
			if (y_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num1++;
			else
				break;
		}
		if (num1 == 5)
			return true;
		// �ж��·�
		y_temp1 = y_temp;
		for (int i = 1; i < 6; i++) {
			y_temp1 += 1;
			if (y_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num2++;
			else
				break;
		}
		if (num2 == 5)
			return true;
		if ((num1 + num2) >= 6)
			return true;

		// �ж�����
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 1;
		num2 = 1;
		for (int i = 1; i < 6; i++) {
			x_temp1 -= 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num1++;
			else
				break;
		}
		if (num1 == 5)
			return true;
		// �ж�����
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (int i = 1; i < 6; i++) {
			x_temp1 += 1;
			y_temp1 += 1;
			if (y_temp1 > this.width || x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num2++;
			else
				break;
		}
		if (num2 == 5)
			return true;
		if ((num1 + num2) >= 6)
			return true;

		// �ж�����
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		num1 = 1;
		num2 = 1;
		for (int i = 1; i < 6; i++) {
			x_temp1 += 1;
			y_temp1 -= 1;
			if (y_temp1 < 0 || x_temp1 > this.width)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num1++;
			else
				break;
		}
		if (num1 == 5)
			return true;
		// �ж�����
		x_temp1 = x_temp;
		y_temp1 = y_temp;
		for (int i = 1; i < 6; i++) {
			x_temp1 -= 1;
			y_temp1 += 1;
			if (y_temp1 > this.width || x_temp1 < 0)
				break;
			if (this.arrMapShow[x_temp1][y_temp1] == arrvalue)
				num2++;
			else
				break;
		}
		if (num2 == 5)
			return true;
		if ((num1 + num2) >= 6)
			return true;
		return false;
	}

	// �ڷ���ʤ�����ʾ
	public void blackSuccess(JPanel jp) {
		JOptionPane.showMessageDialog(jp, "�ڷ���ʤ!", "win",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// �׷���ʤ�����ʾ
	public void whiteSuccess(JPanel jp) {
		JOptionPane.showMessageDialog(jp, "�׷���ʤ!", "win",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// Ӯ������ʾ
	public void showSuccess(JPanel jp) {
		JOptionPane.showMessageDialog(jp, "��Ӯ�ˣ�������!", "win",
				JOptionPane.INFORMATION_MESSAGE);
		cf.setFocusable(false);
	}

	// ��������ʾ
	public void showDefeat(JPanel jp) {
		JOptionPane.showMessageDialog(jp, "�����ˣ������¿�ʼ!", "lost",
				JOptionPane.INFORMATION_MESSAGE);
		cf.setFocusable(false);
	}
}

/*
 * ��MainPanel��Ҫ������¹��ܣ�1������һ����壬�ڸ�����ϻ������̣�2�������ڸ������ϵ�����¼��������������������Ҽ����������϶��ȣ�
 */
class MainPanel extends JPanel implements MouseListener, MouseMotionListener {
	/** 
     *  
     */
	private static final long serialVersionUID = 1L;
	private int width, height;// ���̵Ŀ�Ⱥ͸߶�
	private ChessModel cm;

	// ��������ģʽ�趨���Ĵ�С
	MainPanel(final ChessModel mm) {
		cm = mm;
		width = cm.getWidth();
		height = cm.getWidth();
		addMouseListener(this);
	}

	// ��������ģʽ�趨���̵Ŀ�Ⱥ͸߶�
	public void setModel(final ChessModel mm) {
		cm = mm;
		width = cm.getWidth();
		height = cm.getWidth();
	}

	// ���������������̷������ӵ���Ϣ������ӻ��Ǻ��ӣ���
	// Ȼ�����draw�����������ϻ�����Ӧ������
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		for (int j = 0; j <= height; j++) {
			for (int i = 0; i <= width; i++) {
				final int v = cm.getarrMapShow()[i][j];
				draw(g, i, j, v);
			}
		}
	}

	// �����ṩ��������Ϣ����ɫ�����꣩������
	public void draw(final Graphics g, final int i, final int j, final int v) {
		final int x = 30 * i + 30;
		final int y = 30 * j + 30;

		// ������
		if (i != width && j != height) {
			g.setColor(Color.gray);
			g.drawRect(x, y, 30, 30);
			g.setColor(Color.black);
			g.fillRect(width * 15 + 27, height * 15 + 27, 7, 7);// ���м��־��
			g.fillRect(width * 7 + 19, height * 7 + 19, 7, 7); // �����ϽǱ�־��
			g.fillRect(width * 23 + 35, height * 23 + 35, 7, 7);
			g.fillRect(width * 23 + 35, height * 7 + 19, 7, 7);
			g.fillRect(width * 7 + 19, height * 23 + 35, 7, 7);
		}

		// ����ɫ����
		if (v == 1) {
			g.setColor(Color.gray);
			g.drawOval(x - 11, y - 11, 22, 22);
			g.setColor(Color.black);
			g.fillOval(x - 11, y - 11, 22, 22);
		}
		// ����ɫ����
		if (v == 2) {
			g.setColor(Color.gray);
			g.drawOval(x - 11, y - 11, 22, 22);
			g.setColor(Color.white);
			g.fillOval(x - 11, y - 11, 22, 22);
		}
		if (v == 3) {
			g.setColor(Color.cyan);
			g.drawOval(x - 11, y - 11, 22, 22);
		}
	}

	// ��Ӧ���ĵ���¼����������ĵ�������壬
	// ���������ж�ʤ����
	public void mousePressed(final MouseEvent evt) {
		final int x = (evt.getX() - 10) / 30;
		final int y = (evt.getY() - 10) / 30;
		int flag = 1;
		System.out.println(x + " " + y);
		if (evt.getModifiers() == MouseEvent.BUTTON1_MASK) {
			for (int m = 0; m <= cm.getWidth(); m++) {
				for (int n = 0; n <= cm.getWidth(); n++) {
					ChessFrame.qipan[m][n] = cm.arrMapShow[m][n];
				}
			}
			if (cm.arrMapShow[x][y] == -1) {
				cm.play(x, y);
				System.out.println(cm.getisOdd() + " "
						+ cm.getarrMapShow()[x][y]);
				repaint();
				// �ж��Ƿ�Ϊ�˻�����
				if (ChessFrame.iscomputer && !cm.getisExist()) {
					if (cm.judgeSuccess(x, y, false) == true) {
						JPanel jp = new JPanel();
						cm.showSuccess(jp);
						flag = 0;
					}
					repaint();
					if (flag == 1)
						cm.computerDo(0);
				} else if (!ChessFrame.iscomputer && !cm.getisExist()) {
					if (cm.judgeSuccess(x, y, false) == true) {
						JPanel jp = new JPanel();
						cm.blackSuccess(jp);
					} else if (cm.judgeSuccess(x, y, true) == true) {
						JPanel jp = new JPanel();
						cm.whiteSuccess(jp);
					}
					repaint();
				}
			}
		}
	}

	public void mouseClicked(final MouseEvent evt) {
	}

	public void mouseReleased(final MouseEvent evt) {
	}

	public void mouseEntered(final MouseEvent mouseevt) {
	}

	public void mouseExited(final MouseEvent mouseevent) {
	}

	public void mouseDragged(final MouseEvent evt) {
	}

	// ��Ӧ�����϶��¼�
	public void mouseMoved(final MouseEvent moveevt) {
		// final int x = (moveevt.getX()-10) / 20;
		// final int y = (moveevt.getY()-10) / 20;
		// cm.readyplay(x,y);
		// repaint();
	}
}

class ChessWindowEvent extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	ChessWindowEvent() {
	}
}
