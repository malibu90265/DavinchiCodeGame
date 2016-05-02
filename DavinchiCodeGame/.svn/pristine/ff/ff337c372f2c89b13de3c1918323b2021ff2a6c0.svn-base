import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GameStart extends JFrame {
	JTextArea textArea;
	JScrollPane scrollPane;

	JButton comBtn[] = new JButton[13];// 컴퓨터 카드
	JButton userBtn[] = new JButton[13];// 사용자 카드 (공개되었을 때 Enable이 true)
	JButton WcardBtn = new JButton("13");
	JButton BcardBtn = new JButton("13");

	int mainCard[] = new int[26]; // 아직 뽑히지 않은 카드의 배열, 뽑히면 값이 -1로 변경
	int numWcard = 13; // 남은 카드의 수
	int numBcard = 13; // 남은 카드의 수
	int turn = 0; // 0일경우 com차례, 1일경우 user가 카드를 뽑을 차례, 2일 경우 user가 맞출차례
	int guessLocation; // // user가 com의 패를 맞추려고 누른 위치

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
		setTitle("다빈치 코드 게임");
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

			for (int i = 0; i < comBtn.length; i++) { // COM카드 버튼
				comBtn[i] = new JButton("");
				comBtn[i].setLocation(50 + i * 60, 70);
				comBtn[i].setSize(60, 90);
				comBtn[i].setBackground(getBackground());
				comBtn[i].addActionListener(new ComBtnListener());
				add(comBtn[i]);
				comBtn[i].setEnabled(false);
			}
			for (int i = 0; i < userBtn.length; i++) { // USER카드 버튼
				userBtn[i] = new JButton("");
				userBtn[i].setLocation(50 + i * 60, 450);
				userBtn[i].setSize(60, 90);
				userBtn[i].setBackground(getBackground());
				add(userBtn[i]);
			}
			for (int i = 0; i < guessBtn.length; i++) { // GUESS카드 버튼
				guessBtn[i] = new JButton("");
				guessBtn[i].setLocation(130 + i * 50, 200);
				guessBtn[i].setSize(50, 75);
				guessBtn[i].addActionListener(new GuessBtnListener());
				add(guessBtn[i]);
				guessBtn[i].setVisible(false);
			}
			// 중앙에 흰색 뽑기 카드 버틍
			WcardBtn.setLocation(310, 300);
			WcardBtn.setSize(80, 90);
			WcardBtn.setBackground(Color.white);
			WcardBtn.addActionListener(new CenterBtnListener());
			add(WcardBtn);
			// 중앙에 검은색 뽑기 카드 버튼
			BcardBtn.setLocation(410, 300);
			BcardBtn.setSize(80, 90);
			BcardBtn.setBackground(Color.black);
			BcardBtn.setForeground(Color.white);
			BcardBtn.addActionListener(new CenterBtnListener());
			add(BcardBtn);
			// 중앙에 GO 버튼 - 흰색카드 위치
			goBtn.setLocation(310, 300);
			goBtn.setSize(80, 90);
			goBtn.addActionListener(new CenterBtnListener());
			add(goBtn);
			goBtn.setVisible(false);
			// 중앙에 STOP 버튼 - 검은색카드 위치
			stopBtn.setLocation(410, 300);
			stopBtn.setSize(80, 90);
			stopBtn.addActionListener(new CenterBtnListener());
			add(stopBtn);
			stopBtn.setVisible(false);
			// start 버튼
			startBtn.setLocation(950, 500);
			startBtn.setSize(100, 100);
			startBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					startBtn.setEnabled(false);
					firstPull();
				}
			});
			add(startBtn);
			// end 버튼
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

		class CenterBtnListener implements ActionListener { // 중앙에있는 버튼
			public void actionPerformed(ActionEvent e) {
				Object btn = e.getSource(); // 선택된 버튼

				if (turn == 1) {
					// 검은색 패를 눌렀을 때 이벤트 설정
					if (btn == BcardBtn) {
						if (numBcard > 0) {
							String num = Integer.toString(--numBcard); // 뽑은카드갯수감소
							BcardBtn.setText(num); // 숫자 출력
							user.pull(0); // 카드 뽑기기기기
							// 뽑은카드를 출력
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
							if (user.mycard[4] == -1)// 처음 5번을 뽑기위해 설정한 함수
								turn = 1;
							else if (com.mycard[5] == -1)
								firstTurn();
							else
								userTurn();
						}
					} else if (btn == WcardBtn) { // 흰색 패를 눌렀을 때 이벤트 설정
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
							if (user.mycard[4] == -1) // 처음 5번을 뽑기위해 설정한 함수
								turn = 1;
							else if (com.mycard[5] == -1)
								firstTurn();
							else
								userTurn();
						}
					}
				}
				if (btn == goBtn) { // go 패를 눌렀을 때 이벤트 설정
					WcardBtn.setVisible(true);
					BcardBtn.setVisible(true);
					goBtn.setVisible(false);
					stopBtn.setVisible(false);
					userTurn();
				} else if (btn == stopBtn) { // stop 패를 눌렀을 때 이벤트 설정
					WcardBtn.setVisible(true);
					BcardBtn.setVisible(true);
					goBtn.setVisible(false);
					stopBtn.setVisible(false);
					comTurn();
				}
			}
		}

		class ComBtnListener implements ActionListener { // COM카드를 눌렀을때
			public void actionPerformed(ActionEvent e) {
				if (turn == 2) {
					Object btn = e.getSource(); // 선택된 버튼
					int num = -1;
					int color = 0;

					for (int i = 0; i < 13; i++)
						if (btn == comBtn[i]) {
							num = i;
							guessLocation = num;
						}

					// 검은색일 경우 최솟값 0, 흰색일경우 최솟값 1
					if (com.mycard[num] % 2 == 1)
						color = 1;

					// 공개되지 않은카드인 경우
					if ((com.showcard[num]) == -1) {
						int j = 0, min = color, max = 26;
						// 추측할수 있는 숫자중에서 제일 작은숫자
						for (int i = 0; i < num; i++)
							if (com.showcard[i] != -1) // 공개된 카드중에서
								if (((com.mycard[num] % 2)
										- (com.mycard[i] % 2) == 1)
										&& (i + 1 == num))
									min = com.showcard[i] + 1;
								else if (color == (com.showcard[i] % 2))
									min = com.showcard[i] + 2;
								else
									min = com.showcard[i] + 1;
						// 추측할수 있는 숫자중에서 제일 큰 숫자+1
						for (int i = 12; i > num; i--)
							if (com.showcard[i] != -1) // 공개된 카드중에서
								max = com.showcard[i];
						// 0부터 위에서 찾은 max값 사이에 숫자에서 추측이 가능한 숫자
						for (int i = min; i < max; i += 2) {
							if ((com.CheckGuessNumber(i, 1))
									&& (user.CheckGuessNumber(i, 0))) {
								String text = Integer.toString(i / 2);
								if (i / 2 == 12) // 조커 출력
									guessBtn[j].setText("━");
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
				JButton btn = (JButton) e.getSource(); // 선택된 버튼
				String text = btn.getText();
				int color, num;

				if (comBtn[guessLocation].getBackground() == Color.black)
					color = 0;
				else
					color = 1;

				if (text == "━")
					num = 13 * 2 + color;
				else
					num = Integer.parseInt(text) * 2 + color;

				textArea.append("사용자가 컴퓨터의 " + (guessLocation + 1) + "번째 카드를 "
						+ (num / 2) + "(으)로 추측하였습니다.\n");

				if (!(com.testCard(guessLocation, num))) { // 틀렸을 경우
					user.openCard(user.pullcard[0]); // 가져온 패 공개
					userBtn[user.pullcard[0]].setEnabled(true);
					printCardNum(userBtn[user.pullcard[0]], user.pullcard[1]);

					textArea.append("사용자가 틀렸습니다.\n");

					if (com.CheckFinish()) {
						GameOver("COM");
						return;
					}
					comTurn();
				} else {
					successMessage("COM");
					com.openCard(guessLocation); // 맞춘패 공개
					printCardNum(comBtn[guessLocation], num);
					textArea.append("사용자가 맞았습니다.\n");
					// COM의 패가 다 공개된 경우 게임 끝!!
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

	void firstPull() { // 맨 처음 5개의 패를 뽑는 함수
		textArea.append("게임이 시작되었습니다\n");
		textArea.append("\n\"컴퓨터\"의 차례입니다.\n5개의 카드를 뽑아주시기 바랍니다.\n");

		for (int i = 0; i < 5; i++) { // 먼저 com이 5개의 패를 뽑음
			com.pull(selectColor()); //
			for (int j = com.pullcard[0]; j < com.cardNum; j++) {
				printCardColor(comBtn[j], com.mycard[j]);
			}
			comBtn[i].setEnabled(true);
		}
		textArea.append("\n\"사용자\"의 차례입니다.\n5개의 카드를 뽑아주시기 바랍니다.\n");
		turn = 1;
	}

	void reset() { // 초기화 하는 함수
		numWcard = 13;
		numBcard = 13;
		turn = 0;

		com.reset();
		user.reset();
	}

	void firstTurn() { // 컴퓨터의 첫번째 턴
		int location = rand.nextInt(2); // 어디 지목할지
		int guess = -1;

		textArea.append("\n\"컴퓨터\"의 차례입니다.\n1개의 카드를 뽑아주시기 바랍니다.\n");
		// 카드 뽑기
		com.pull(selectColor());
		for (int j = com.pullcard[0]; j < com.cardNum; j++)
			printCardColor(comBtn[j], com.mycard[j]);
		comBtn[com.cardNum - 1].setEnabled(true);

		if (location == 1) // 처음 지목할 위치
			location = 4;
		textArea.append("맞출 사용자의 카드를 선택 후 맞춰주시기 바랍니다.\n");
		guess = com.firstTurn(location, user.infoCardColor(location));

		if (!(user.testCard(location, guess))) { // 컴퓨터가 사용자의 카드를 맞추지 못한 경우
			failureMessage("COM");
			com.openCard(com.pullcard[0]);
			printCardNum(comBtn[com.pullcard[0]], com.pullcard[1]);
		} else { // 컴퓨터가 사용자의 카드를 맞춘 경우
			successMessage("COM");
			user.openCard(location);
			userBtn[location].setEnabled(true);
			printCardNum(userBtn[location], user.mycard[location]);
		}

		textArea.append("컴퓨터가 사용자의 " + (location + 1) + "번째 카드를 " + (guess / 2)
				+ "(으)로 추측하였습니다.\n");
		textArea.append("\n\"사용자\"의 차례입니다.\n1개의 카드를 뽑아주시기 바랍니다.\n");
		turn = 1;
	}

	void userTurn() {
		textArea.append("맞출 컴퓨터의 카드를 선택 후 맞춰주시기 바랍니다.\n");
		turn = 2;
	}

	void comTurn() { // 컴퓨터의 첫번째를 제외한 턴
		int location = 0;
		int guess[] = new int[13];
		int color;
		turn = 0;

		textArea.append("\n\"컴퓨터\"의 차례입니다.\n");
		if (com.CheckPullCard()) { // 뽑을카드가 없는 경우 리턴
			textArea.append("1개의 카드를 뽑아주시기 바랍니다.\n");
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
			textArea.append("맞출 사용자의 카드를 선택 후 맞춰주시기 바랍니다.\n");
			location = user.getGuessLocation();
			color = user.infoCardColor(location);
			int j = 0, min = color, max = 26;

			// 공개되지 않은카드인 경우
			if ((user.showcard[location]) == -1) {
				// 추측할수 있는 숫자중에서 제일 작은숫자
				for (int i = 0; i < location; i++)
					if (user.showcard[i] != -1) // 공개된 카드중에서
						if (((user.mycard[location] % 2) - (user.mycard[i] % 2) == 1)
								&& (i + 1 == location))
							min = user.showcard[i] + 1;
						else if (color == (user.showcard[i] % 2))
							min = user.showcard[i] + 2;
						else
							min = user.showcard[i] + 1;
				// 추측할수 있는 숫자중에서 제일 큰 숫자+1
				for (int i = 12; i > location; i--)
					if (user.showcard[i] != -1) // 공개된 카드중에서
						max = user.showcard[i];
				// 0부터 위에서 찾은 max값 사이에 숫자에서 추측이 가능한 숫자
				for (int i = min; i < max; i += 2) {
					if ((user.CheckGuessNumber(i, 1))
							&& (com.CheckGuessNumber(i, 0))) {
						guess[j] = i;
						j++;
					}
				}
			}
			int rg = rand.nextInt(j);
			textArea.append("컴퓨터가 사용자의 " + (rg + 1) + "번째 카드를 "
					+ (guess[rg] / 2) + "로 추측하였습니다.\n");

			if (!(user.testCard(location, guess[rg]))) { // 컴퓨터가 사용자의 카드를 틀린경우
				failureMessage("COM");
				com.openCard(com.pullcard[0]);
				printCardNum(comBtn[com.pullcard[0]], com.pullcard[1]);

				textArea.append("컴퓨터가 틀렸습니다.\n");
				if (com.CheckFinish())
					GameOver("COM");

				break;
			} else { // 컴퓨터가 사용자의 카드를 맞춘경우
				successMessage("COM");
				user.openCard(location);
				userBtn[location].setEnabled(true);
				printCardNum(userBtn[location], user.mycard[location]);

				textArea.append("컴퓨터가 맞았습니다.\n");
				if (user.CheckFinish())
					GameOver("USER");
			}
		} while (rand.nextInt(2) == 1);
		if (com.CheckPullCard()) {
			textArea.append("\n\"사용자\"의 차례입니다.\n1개의 카드를 뽑아주시기 바랍니다.\n");
			turn = 1;
		} else {
			textArea.append("\n\"사용자\"의 차례입니다.\n");
			turn = 2;
		}
		print();
	}

	int selectColor() { // 뽑을 카드 색깔선택하는 함수
		int color = rand.nextInt(2);
		while (true) {
			if (color == 0 && numBcard > 0) {// 검은색이 선택될 경우
				String num = Integer.toString(--numBcard);
				BcardBtn.setText(num);
				break;
			} else if (color == 1 && numWcard > 0) { // 흰색이 선택될 경우
				String num = Integer.toString(--numWcard);
				WcardBtn.setText(num);
				break;
			}
		}
		return color;
	}

	void printCardColor(JButton btn, int cardNum) { // 뽑은 카드를 색만 출력하는 함수
		if (cardNum % 2 == 0)
			btn.setBackground(Color.black);
		else
			btn.setBackground(Color.white);
	}

	void printCardNum(JButton btn, int cardNum) { // 버튼에 숫자 출력하는 함수
		String text = Integer.toString(cardNum / 2);
		btn.setForeground(Color.ORANGE);
		if (cardNum / 2 == 12) { // 조커 출력
			btn.setText("━");
		} else
			btn.setText(text);
	}

	void successMessage(String turn) {
		if (turn.equals("COM"))
			textArea.append("COM이 카드를 맞췄습니다.\n");
		else
			textArea.append("USER가 카드를 맞췄습니다.\n");
	}

	void failureMessage(String turn) {
		if (turn.equals("COM"))
			textArea.append("COM이 틀렸습니다.\n");
		else
			textArea.append("USER가 틀렸습니다.\n");
	}

	void GameOver(String turn) {
		textArea.append("\n ***게임이 종료되었습니다***\n");
		textArea.append(" " + turn + "의 승리입니다\n");

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
		int mycard[] = new int[13]; // 내가 현재 가지고 있는 카드
		int showcard[] = new int[13]; // 상대방에게 보여지고 있는 카드
		int pullcard[] = new int[2]; // 0은 뽑은 카드의 인덱스, 1은 뽑은카드의 값
		int cardNum; // 내가 현재 가지고 있는 카드의 수
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

		void pull(int color) { // 카드 뽑기
			int i = 0;

			pullcard[1] = pullCard(color); // 뽑은 카드 값 저장
			cardNum++;

			for (i = cardNum - 2; i >= 0 && mycard[i] > pullcard[1]; i--) {
				mycard[i + 1] = mycard[i]; // 레코드의 오른쪽 이동
				showcard[i + 1] = showcard[i];
			}
			mycard[i + 1] = pullcard[1];
			showcard[i + 1] = -1;
			pullcard[0] = i + 1;
		}

		int infoCardColor(int location) { // 위치의 색깔을 리턴해주는 함수
			return mycard[location] % 2;
		}

		int infoCardNum(int location) {
			return mycard[location] / 2;
		}

		boolean testCard(int location, int num) { // 추측한 숫자가 맞으면
			if (mycard[location] == num) {
				showcard[location] = num;
				return true;
			} else
				return false;
		}

		void openCard(int location) { // 뽑은 카드를 공개하는 함수
			showcard[location] = mycard[location];
		}

		int getGuessLocation() { // com이 추측할 위치의 숫자를 리턴해주는 함수
			int location = -1;
			while (true) {
				location = rand.nextInt(cardNum);
				if (showcard[location] == -1 && mycard[location] != -1)
					return location;
			}
		}

		// 추측하려는 수를 내가 이미 가지고있는지 아닌지
		boolean CheckGuessNumber(int num, int turn) {
			// 판단(false일 경우 추측 불가능)
			if (turn == 0) { // 내 턴일 경우
				for (int i = 0; i < cardNum; i++)
					if (mycard[i] == num)
						return false;
			} else { // 상대방 턴일 경우
				for (int i = 0; i < cardNum; i++)
					if (showcard[i] == num)
						return false;
			}
			return true;
		}

		boolean CheckGuessLocation(int num) { // 추측하려는 위치가 이미 공개되어있는지 아닌지
			// 판단(false일
			// 경우 추측 가능)
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

		int firstTurn(int location, int color) { // 첫번째차례에 랜덤으로 숫자 리턴
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

		int pullCard(int color) { // 카드선택
			int card = -1;
			while (color == 0) { // 검은패 뽑기
				card = rand.nextInt(13) * 2;
				if (mainCard[card] != -1) {
					mainCard[card] = -1;
					return card;
				}
			}
			while (color == 1) { // 흰색패 뽑기
				card = rand.nextInt(13) * 2 + 1;
				if (mainCard[card] != -1) {
					mainCard[card] = -1;
					return card;
				}
			}
			return card;
		}

		boolean CheckPullCard() { // 카드를 뽑을수 있는지 없는지 검사하는 함수
			if ((numWcard == 0) && (numBcard == 0))
				return false;
			else
				return true;
		}
	}

}