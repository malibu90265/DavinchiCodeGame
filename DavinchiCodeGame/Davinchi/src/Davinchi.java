import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class Davinchi {
	String userName;

	Davinchi() {
		new MainMenu();
	}

	class MainMenu extends JFrame {
		JButton mainBtn[] = new JButton[4]; // ���θ޴� ��ư

		MainMenu() { // ���θ޴�
			setTitle("�ٺ�ġ �ڵ� ����");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(null);

			ImageIcon bgImg = new ImageIcon("background.png");
			getContentPane();

			JLabel bg = new JLabel(bgImg);
			bg.setLocation(0, 0);
			bg.setSize(400, 650);

			ImageIcon[] mainImg = new ImageIcon[4];
			mainImg[0] = new ImageIcon("gameStart.png");
			mainImg[1] = new ImageIcon("gameHow.png");
			mainImg[2] = new ImageIcon("gameInfo.png");
			mainImg[3] = new ImageIcon("gameExit.png");

			mainBtn = new JButton[4];
			for (int i = 0; i < mainBtn.length; i++) {

				mainBtn[i] = new JButton(mainImg[i]);
				mainBtn[i].setOpaque(true);
				mainBtn[i].setBorderPainted(false);
				mainBtn[i].setLocation(100, 140 + i * 50); // ��ġ����
				mainBtn[i].setSize(200, 50); // ũ������
				mainBtn[i].addActionListener(new MainBtnListener());
				mainBtn[i].setContentAreaFilled(false);
				add(mainBtn[i]); // Pane�� �߰�
			}

			add(bg);

			setSize(400, 650);
			setVisible(true);

		}

		class MainBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Object btn = e.getSource(); // ���õ� ��ư

				if (btn == mainBtn[0]) { // ���ӽ���
					dispose();
					new GameStart();
				} else if (btn == mainBtn[1]) { // ���ӹ��
					dispose();
					new GameRule();
				} else if (btn == mainBtn[2]) { // ��������
					dispose();
					new GameInformation();
				} else if (btn == mainBtn[3]) { // ��������
					System.exit(0);
				}
			}
		}
	}

	public static void main(String[] args) {
		new Davinchi();
	}
}