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
		JButton mainBtn[] = new JButton[4]; // 메인메뉴 버튼

		MainMenu() { // 메인메뉴
			setTitle("다빈치 코드 게임");
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
				mainBtn[i].setLocation(100, 140 + i * 50); // 위치지정
				mainBtn[i].setSize(200, 50); // 크기지정
				mainBtn[i].addActionListener(new MainBtnListener());
				mainBtn[i].setContentAreaFilled(false);
				add(mainBtn[i]); // Pane에 추가
			}

			add(bg);

			setSize(400, 650);
			setVisible(true);

		}

		class MainBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Object btn = e.getSource(); // 선택된 버튼

				if (btn == mainBtn[0]) { // 게임시작
					dispose();
					new GameStart();
				} else if (btn == mainBtn[1]) { // 게임방법
					dispose();
					new GameRule();
				} else if (btn == mainBtn[2]) { // 게임정보
					dispose();
					new GameInformation();
				} else if (btn == mainBtn[3]) { // 게임종료
					System.exit(0);
				}
			}
		}
	}

	public static void main(String[] args) {
		new Davinchi();
	}
}