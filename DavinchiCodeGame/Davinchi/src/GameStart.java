import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GameStart extends JFrame {
	JTextArea textArea;
	JScrollPane scrollPane;

	JButton comBtn[] = new JButton[13];// ��ǻ�� ī��
	JButton userBtn[] = new JButton[13];// ����� ī�� (�����Ǿ��� �� Enable�� true)
	JButton WcardBtn = new JButton("13");
	JButton BcardBtn = new JButton("13");

	int mainCard[] = new int[26]; // ���� ������ ���� ī���� �迭, ������ ���� -1�� ����
	int numWcard = 13; // ���� ī���� ��
	int numBcard = 13; // ���� ī���� ��
	int turn = 0; // 0�ϰ�� com����, 1�ϰ�� user�� ī�带 ���� ����, 2�� ��� user�� ��������
	int guessLocation; // // user�� com�� �и� ���߷��� ���� ��ġ

	Card com = new Card();
	Card user = new Card();
	Scanner n = new Scanner(System.in);
	Random rand = new Random();

	void print() {
		System.out.print("COM : ");
		for (int i = 0; i < com.mycard.length; i++)
			System.out.print(com.mycard[i] / 2 + " ");

		System.out.print("\nUSER : ");
		for (int i = 0; i < user.mycard.length; i++)
			System.out.print(user.mycard[i] / 2 + " ");
		System.out.println("\n\n");
	}

	GameStart() {
		setTitle("�ٺ�ġ �ڵ� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBackground(new Color(230, 230, 250));
		setSize(1300, 650);
		setVisible(true);

		new SetButton();
	}

	class SetButton {
		JButton guessBtn[] = new JButton[12];
		JButton goBtn = new JButton("GO");
		JButton stopBtn = new JButton("STOP");
		JButton startBtn = new JButton("START");
		JButton endBtn = new JButton("END");

		SetButton() {
			textArea = new JTextArea(300, 400);
			scrollPane = new JScrollPane(textArea);
			scrollPane.setSize(300, 400);
			scrollPane.setLocation(950, 50);

			// textArea.setFont(new Font("Ghothic", Font.BOLD, 15));
			add(scrollPane);

			for (int i = 0; i < comBtn.length; i++) { // COMī�� ��ư
				comBtn[i] = new JButton("");
				comBtn[i].setLocation(50 + i * 60, 70);
				comBtn[i].setSize(60, 90);
				comBtn[i].setBackground(getBackground());
				comBtn[i].addActionListener(new ComBtnListener());
				add(comBtn[i]);
				comBtn[i].setEnabled(false);
			}
			for (int i = 0; i < userBtn.length; i++) { // USERī�� ��ư
				userBtn[i] = new JButton("");
				userBtn[i].setLocation(50 + i * 60, 450);
				userBtn[i].setSize(60, 90);
				userBtn[i].setBackground(getBackground());
				add(userBtn[i]);
			}
			for (int i = 0; i < guessBtn.length; i++) { // GUESSī�� ��ư
				guessBtn[i] = new JButton("");
				guessBtn[i].setLocation(130 + i * 50, 200);
				guessBtn[i].setSize(50, 75);
				guessBtn[i].addActionListener(new GuessBtnListener());
				add(guessBtn[i]);
				guessBtn[i].setVisible(false);
			}
			// �߾ӿ� ��� �̱� ī�� ���v
			WcardBtn.setLocation(310, 300);
			WcardBtn.setSize(80, 90);
			WcardBtn.setBackground(Color.white);
			WcardBtn.addActionListener(new CenterBtnListener());
			add(WcardBtn);
			// �߾ӿ� ������ �̱� ī�� ��ư
			BcardBtn.setLocation(410, 300);
			BcardBtn.setSize(80, 90);
			BcardBtn.setBackground(Color.black);
			BcardBtn.setForeground(Color.white);
			BcardBtn.addActionListener(new CenterBtnListener());
			add(BcardBtn);
			// �߾ӿ� GO ��ư - ���ī�� ��ġ
			goBtn.setLocation(310, 300);
			goBtn.setSize(80, 90);
			goBtn.addActionListener(new CenterBtnListener());
			add(goBtn);
			goBtn.setVisible(false);
			// �߾ӿ� STOP ��ư - ������ī�� ��ġ
			stopBtn.setLocation(410, 300);
			stopBtn.setSize(80, 90);
			stopBtn.addActionListener(new CenterBtnListener());
			add(stopBtn);
			stopBtn.setVisible(false);
			// start ��ư
			startBtn.setLocation(950, 500);
			startBtn.setSize(100, 100);
			startBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					startBtn.setEnabled(false);
					firstPull();
				}
			});
			add(startBtn);
			// end ��ư
			endBtn.setLocation(1100, 500);
			endBtn.setSize(100, 100);
			endBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					reset();
					new Davinchi();
				}
			});
			add(endBtn);
		}

		class CenterBtnListener implements ActionListener { // �߾ӿ��ִ� ��ư
			public void actionPerformed(ActionEvent e) {
				Object btn = e.getSource(); // ���õ� ��ư

				if (turn == 1) {
					// ������ �и� ������ �� �̺�Ʈ ����
					if (btn == BcardBtn) {
						if (numBcard > 0) {
							String num = Integer.toString(--numBcard); // ����ī�尹������
							BcardBtn.setText(num); // ���� ���
							user.pull(0); // ī�� �̱����
							// ����ī�带 ���
							for (int j = user.pullcard[0]; j < user.cardNum; j++) {
								printCardColor(userBtn[j], user.mycard[j]);
								if (user.showcard[j] != -1) {
									printCardNum(userBtn[j], user.mycard[j]);
									userBtn[j].setEnabled(true);
								} else {
									printCardNum(userBtn[j], user.mycard[j]);
									userBtn[j].setEnabled(false);
								}
							}
							if (user.mycard[4] == -1)// ó�� 5���� �̱����� ������ �Լ�
								turn = 1;
							else if (com.mycard[5] == -1)
								firstTurn();
							else
								userTurn();
						}
					} else if (btn == WcardBtn) { // ��� �и� ������ �� �̺�Ʈ ����
						if (numWcard > 0) {
							String num = Integer.toString(--numWcard);
							WcardBtn.setText(num);
							user.pull(1);
							for (int j = user.pullcard[0]; j < user.cardNum; j++) {
								printCardColor(userBtn[j], user.mycard[j]);
								if (user.showcard[j] != -1) {
									printCardNum(userBtn[j], user.mycard[j]);
									userBtn[j].setEnabled(true);
								} else {
									printCardNum(userBtn[j], user.mycard[j]);
									userBtn[j].setEnabled(false);
								}
							}
							if (user.mycard[4] == -1) // ó�� 5���� �̱����� ������ �Լ�
								turn = 1;
							else if (com.mycard[5] == -1)
								firstTurn();
							else
								userTurn();
						}
					}
				}
				if (btn == goBtn) { // go �и� ������ �� �̺�Ʈ ����
					WcardBtn.setVisible(true);
					BcardBtn.setVisible(true);
					goBtn.setVisible(false);
					stopBtn.setVisible(false);
					userTurn();
				} else if (btn == stopBtn) { // stop �и� ������ �� �̺�Ʈ ����
					WcardBtn.setVisible(true);
					BcardBtn.setVisible(true);
					goBtn.setVisible(false);
					stopBtn.setVisible(false);
					comTurn();
				}
			}
		}

		class ComBtnListener implements ActionListener { // COMī�带 ��������
			public void actionPerformed(ActionEvent e) {
				if (turn == 2) {
					Object btn = e.getSource(); // ���õ� ��ư
					int num = -1;
					int color = 0;

					for (int i = 0; i < 13; i++)
						if (btn == comBtn[i]) {
							num = i;
							guessLocation = num;
						}

					// �������� ��� �ּڰ� 0, ����ϰ�� �ּڰ� 1
					if (com.mycard[num] % 2 == 1)
						color = 1;

					// �������� ����ī���� ���
					if ((com.showcard[num]) == -1) {
						int j = 0, min = color, max = 26;
						// �����Ҽ� �ִ� �����߿��� ���� ��������
						for (int i = 0; i < num; i++)
							if (com.showcard[i] != -1) // ������ ī���߿���
								if (((com.mycard[num] % 2)
										- (com.mycard[i] % 2) == 1)
										&& (i + 1 == num))
									min = com.showcard[i] + 1;
								else if (color == (com.showcard[i] % 2))
									min = com.showcard[i] + 2;
								else
									min = com.showcard[i] + 1;
						// �����Ҽ� �ִ� �����߿��� ���� ū ����+1
						for (int i = 12; i > num; i--)
							if (com.showcard[i] != -1) // ������ ī���߿���
								max = com.showcard[i];
						// 0���� ������ ã�� max�� ���̿� ���ڿ��� ������ ������ ����
						for (int i = min; i < max; i += 2) {
							if ((com.CheckGuessNumber(i, 1))
									&& (user.CheckGuessNumber(i, 0))) {
								String text = Integer.toString(i / 2);
								if (i / 2 == 12) // ��Ŀ ���
									guessBtn[j].setText("��");
								else
									guessBtn[j].setText(text);
								guessBtn[j].setVisible(true);
								j++;
							}
						}
					}
				}
			}
		}

		class GuessBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource(); // ���õ� ��ư
				String text = btn.getText();
				int color, num;

				if (comBtn[guessLocation].getBackground() == Color.black)
					color = 0;
				else
					color = 1;

				if (text == "��")
					num = 13 * 2 + color;
				else
					num = Integer.parseInt(text) * 2 + color;

				textArea.append("����ڰ� ��ǻ���� " + (guessLocation + 1) + "��° ī�带 "
						+ (num / 2) + "(��)�� �����Ͽ����ϴ�.\n");

				if (!(com.testCard(guessLocation, num))) { // Ʋ���� ���
					user.openCard(user.pullcard[0]); // ������ �� ����
					userBtn[user.pullcard[0]].setEnabled(true);
					printCardNum(userBtn[user.pullcard[0]], user.pullcard[1]);

					textArea.append("����ڰ� Ʋ�Ƚ��ϴ�.\n");

					if (com.CheckFinish()) {
						GameOver("COM");
						return;
					}
					comTurn();
				} else {
					successMessage("COM");
					com.openCard(guessLocation); // ������ ����
					printCardNum(comBtn[guessLocation], num);
					textArea.append("����ڰ� �¾ҽ��ϴ�.\n");
					// COM�� �а� �� ������ ��� ���� ��!!
					if (user.CheckFinish())
						GameOver("USER");
					else {
						WcardBtn.setVisible(false);
						BcardBtn.setVisible(false);
						goBtn.setVisible(true);
						stopBtn.setVisible(true);
					}
				}
				for (int i = 0; i < guessBtn.length; i++)
					guessBtn[i].setVisible(false);
			}
		}
	}

	void firstPull() { // �� ó�� 5���� �и� �̴� �Լ�
		textArea.append("������ ���۵Ǿ����ϴ�\n");
		textArea.append("\n\"��ǻ��\"�� �����Դϴ�.\n5���� ī�带 �̾��ֽñ� �ٶ��ϴ�.\n");

		for (int i = 0; i < 5; i++) { // ���� com�� 5���� �и� ����
			com.pull(selectColor()); //
			for (int j = com.pullcard[0]; j < com.cardNum; j++) {
				printCardColor(comBtn[j], com.mycard[j]);
			}
			comBtn[i].setEnabled(true);
		}
		textArea.append("\n\"�����\"�� �����Դϴ�.\n5���� ī�带 �̾��ֽñ� �ٶ��ϴ�.\n");
		turn = 1;
	}

	void reset() { // �ʱ�ȭ �ϴ� �Լ�
		numWcard = 13;
		numBcard = 13;
		turn = 0;

		com.reset();
		user.reset();
	}

	void firstTurn() { // ��ǻ���� ù��° ��
		int location = rand.nextInt(2); // ��� ��������
		int guess = -1;

		textArea.append("\n\"��ǻ��\"�� �����Դϴ�.\n1���� ī�带 �̾��ֽñ� �ٶ��ϴ�.\n");
		// ī�� �̱�
		com.pull(selectColor());
		for (int j = com.pullcard[0]; j < com.cardNum; j++)
			printCardColor(comBtn[j], com.mycard[j]);
		comBtn[com.cardNum - 1].setEnabled(true);

		if (location == 1) // ó�� ������ ��ġ
			location = 4;
		textArea.append("���� ������� ī�带 ���� �� �����ֽñ� �ٶ��ϴ�.\n");
		guess = com.firstTurn(location, user.infoCardColor(location));

		if (!(user.testCard(location, guess))) { // ��ǻ�Ͱ� ������� ī�带 ������ ���� ���
			failureMessage("COM");
			com.openCard(com.pullcard[0]);
			printCardNum(comBtn[com.pullcard[0]], com.pullcard[1]);
		} else { // ��ǻ�Ͱ� ������� ī�带 ���� ���
			successMessage("COM");
			user.openCard(location);
			userBtn[location].setEnabled(true);
			printCardNum(userBtn[location], user.mycard[location]);
		}

		textArea.append("��ǻ�Ͱ� ������� " + (location + 1) + "��° ī�带 " + (guess / 2)
				+ "(��)�� �����Ͽ����ϴ�.\n");
		textArea.append("\n\"�����\"�� �����Դϴ�.\n1���� ī�带 �̾��ֽñ� �ٶ��ϴ�.\n");
		turn = 1;
	}

	void userTurn() {
		textArea.append("���� ��ǻ���� ī�带 ���� �� �����ֽñ� �ٶ��ϴ�.\n");
		turn = 2;
	}

	void comTurn() { // ��ǻ���� ù��°�� ������ ��
		int location = 0;
		int guess[] = new int[13];
		int color;
		turn = 0;

		textArea.append("\n\"��ǻ��\"�� �����Դϴ�.\n");
		if (com.CheckPullCard()) { // ����ī�尡 ���� ��� ����
			textArea.append("1���� ī�带 �̾��ֽñ� �ٶ��ϴ�.\n");
			com.pull(selectColor());
			for (int j = com.pullcard[0]; j < com.cardNum; j++) {
				printCardColor(comBtn[j], com.mycard[j]);
				if (com.showcard[j] != -1)
					printCardNum(comBtn[j], com.mycard[j]);
				else {
					comBtn[j].setText(" ");
				}
			}
			comBtn[com.cardNum - 1].setEnabled(true);
		}
		do {
			textArea.append("���� ������� ī�带 ���� �� �����ֽñ� �ٶ��ϴ�.\n");
			location = user.getGuessLocation();
			color = user.infoCardColor(location);
			int j = 0, min = color, max = 26;

			// �������� ����ī���� ���
			if ((user.showcard[location]) == -1) {
				// �����Ҽ� �ִ� �����߿��� ���� ��������
				for (int i = 0; i < location; i++)
					if (user.showcard[i] != -1) // ������ ī���߿���
						if (((user.mycard[location] % 2) - (user.mycard[i] % 2) == 1)
								&& (i + 1 == location))
							min = user.showcard[i] + 1;
						else if (color == (user.showcard[i] % 2))
							min = user.showcard[i] + 2;
						else
							min = user.showcard[i] + 1;
				// �����Ҽ� �ִ� �����߿��� ���� ū ����+1
				for (int i = 12; i > location; i--)
					if (user.showcard[i] != -1) // ������ ī���߿���
						max = user.showcard[i];
				// 0���� ������ ã�� max�� ���̿� ���ڿ��� ������ ������ ����
				for (int i = min; i < max; i += 2) {
					if ((user.CheckGuessNumber(i, 1))
							&& (com.CheckGuessNumber(i, 0))) {
						guess[j] = i;
						j++;
					}
				}
			}
			int rg = rand.nextInt(j);
			textArea.append("��ǻ�Ͱ� ������� " + (rg + 1) + "��° ī�带 "
					+ (guess[rg] / 2) + "�� �����Ͽ����ϴ�.\n");

			if (!(user.testCard(location, guess[rg]))) { // ��ǻ�Ͱ� ������� ī�带 Ʋ�����
				failureMessage("COM");
				com.openCard(com.pullcard[0]);
				printCardNum(comBtn[com.pullcard[0]], com.pullcard[1]);

				textArea.append("��ǻ�Ͱ� Ʋ�Ƚ��ϴ�.\n");
				if (com.CheckFinish())
					GameOver("COM");

				break;
			} else { // ��ǻ�Ͱ� ������� ī�带 ������
				successMessage("COM");
				user.openCard(location);
				userBtn[location].setEnabled(true);
				printCardNum(userBtn[location], user.mycard[location]);

				textArea.append("��ǻ�Ͱ� �¾ҽ��ϴ�.\n");
				if (user.CheckFinish())
					GameOver("USER");
			}
		} while (rand.nextInt(2) == 1);
		if (com.CheckPullCard()) {
			textArea.append("\n\"�����\"�� �����Դϴ�.\n1���� ī�带 �̾��ֽñ� �ٶ��ϴ�.\n");
			turn = 1;
		} else {
			textArea.append("\n\"�����\"�� �����Դϴ�.\n");
			turn = 2;
		}
		print();
	}

	int selectColor() { // ���� ī�� �������ϴ� �Լ�
		int color = rand.nextInt(2);
		while (true) {
			if (color == 0 && numBcard > 0) {// �������� ���õ� ���
				String num = Integer.toString(--numBcard);
				BcardBtn.setText(num);
				break;
			} else if (color == 1 && numWcard > 0) { // ����� ���õ� ���
				String num = Integer.toString(--numWcard);
				WcardBtn.setText(num);
				break;
			}
		}
		return color;
	}

	void printCardColor(JButton btn, int cardNum) { // ���� ī�带 ���� ����ϴ� �Լ�
		if (cardNum % 2 == 0)
			btn.setBackground(Color.black);
		else
			btn.setBackground(Color.white);
	}

	void printCardNum(JButton btn, int cardNum) { // ��ư�� ���� ����ϴ� �Լ�
		String text = Integer.toString(cardNum / 2);
		btn.setForeground(Color.ORANGE);
		if (cardNum / 2 == 12) { // ��Ŀ ���
			btn.setText("��");
		} else
			btn.setText(text);
	}

	void successMessage(String turn) {
		if (turn.equals("COM"))
			textArea.append("COM�� ī�带 ������ϴ�.\n");
		else
			textArea.append("USER�� ī�带 ������ϴ�.\n");
	}

	void failureMessage(String turn) {
		if (turn.equals("COM"))
			textArea.append("COM�� Ʋ�Ƚ��ϴ�.\n");
		else
			textArea.append("USER�� Ʋ�Ƚ��ϴ�.\n");
	}

	void GameOver(String turn) {
		textArea.append("\n ***������ ����Ǿ����ϴ�***\n");
		textArea.append(" " + turn + "�� �¸��Դϴ�\n");

		ImageIcon com = new ImageIcon("lose.png");
		ImageIcon user = new ImageIcon("win.png");

		if (turn == "COM") {
			JLabel j = new JLabel(com);
			j.setSize(300, 200);
			j.setLocation(510, 200);
			add(j);
		} else {
			JLabel j = new JLabel(user);
			j.setSize(300, 200);
			j.setLocation(510, 200);
			add(j);

		}
	}

	class Card {
		int mycard[] = new int[13]; // ���� ���� ������ �ִ� ī��
		int showcard[] = new int[13]; // ���濡�� �������� �ִ� ī��
		int pullcard[] = new int[2]; // 0�� ���� ī���� �ε���, 1�� ����ī���� ��
		int cardNum; // ���� ���� ������ �ִ� ī���� ��
		int joker = 0;
		Scanner n = new Scanner(System.in);
		Random rand = new Random();

		Card() {
			cardNum = 0;
			for (int i = 0; i < 13; i++) {
				mycard[i] = -1;
				showcard[i] = -1;
			}
		}

		void reset() {
			cardNum = 0;
			for (int i = 0; i < 13; i++) {
				mycard[i] = -1;
				showcard[i] = -1;
			}
		}

		void pull(int color) { // ī�� �̱�
			int i = 0;

			pullcard[1] = pullCard(color); // ���� ī�� �� ����
			cardNum++;

			for (i = cardNum - 2; i >= 0 && mycard[i] > pullcard[1]; i--) {
				mycard[i + 1] = mycard[i]; // ���ڵ��� ������ �̵�
				showcard[i + 1] = showcard[i];
			}
			mycard[i + 1] = pullcard[1];
			showcard[i + 1] = -1;
			pullcard[0] = i + 1;
		}

		int infoCardColor(int location) { // ��ġ�� ������ �������ִ� �Լ�
			return mycard[location] % 2;
		}

		int infoCardNum(int location) {
			return mycard[location] / 2;
		}

		boolean testCard(int location, int num) { // ������ ���ڰ� ������
			if (mycard[location] == num) {
				showcard[location] = num;
				return true;
			} else
				return false;
		}

		void openCard(int location) { // ���� ī�带 �����ϴ� �Լ�
			showcard[location] = mycard[location];
		}

		int getGuessLocation() { // com�� ������ ��ġ�� ���ڸ� �������ִ� �Լ�
			int location = -1;
			while (true) {
				location = rand.nextInt(cardNum);
				if (showcard[location] == -1 && mycard[location] != -1)
					return location;
			}
		}

		// �����Ϸ��� ���� ���� �̹� �������ִ��� �ƴ���
		boolean CheckGuessNumber(int num, int turn) {
			// �Ǵ�(false�� ��� ���� �Ұ���)
			if (turn == 0) { // �� ���� ���
				for (int i = 0; i < cardNum; i++)
					if (mycard[i] == num)
						return false;
			} else { // ���� ���� ���
				for (int i = 0; i < cardNum; i++)
					if (showcard[i] == num)
						return false;
			}
			return true;
		}

		boolean CheckGuessLocation(int num) { // �����Ϸ��� ��ġ�� �̹� �����Ǿ��ִ��� �ƴ���
			// �Ǵ�(false��
			// ��� ���� ����)
			if (showcard[num] == -1)
				return false;
			else
				return true;
		}

		boolean CheckFinish() {
			for (int i = 0; i < cardNum; i++)
				if (showcard[i] != mycard[i])
					return false;
			return true;
		}

		int firstTurn(int location, int color) { // ù��°���ʿ� �������� ���� ����
			int randomNum;
			while (true) {
				if (location == 0) {
					if (color == 0)
						randomNum = ((rand.nextInt(14)) / 2) * 2;
					else
						randomNum = ((rand.nextInt(14)) / 2) * 2 + 1;
				} else {
					if (color == 0)
						randomNum = ((rand.nextInt(14) + 12) / 2) * 2;
					else
						randomNum = ((rand.nextInt(14) + 12) / 2) * 2 + 1;
				}
				for (int i = 0; i < cardNum; i++)
					if (mycard[i] != randomNum)
						return randomNum;
			}
		}

		int pullCard(int color) { // ī�弱��
			int card = -1;
			while (color == 0) { // ������ �̱�
				card = rand.nextInt(13) * 2;
				if (mainCard[card] != -1) {
					mainCard[card] = -1;
					return card;
				}
			}
			while (color == 1) { // ����� �̱�
				card = rand.nextInt(13) * 2 + 1;
				if (mainCard[card] != -1) {
					mainCard[card] = -1;
					return card;
				}
			}
			return card;
		}

		boolean CheckPullCard() { // ī�带 ������ �ִ��� ������ �˻��ϴ� �Լ�
			if ((numWcard == 0) && (numBcard == 0))
				return false;
			else
				return true;
		}
	}

}