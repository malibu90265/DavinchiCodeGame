package com.example.app1;

import java.util.Random;
import java.util.Scanner;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.View.OnClickListener;

//���� ���� activity
public class GameStart extends Activity {
	private int comId = R.id.Button01;
	private int userId = R.id.Button14;
	private int cardId = R.id.Button27;
	private static int color = 2;
	private int userturn = 0; // -1�� ��� ��ǻ�� �� ����, 1�� ��� go,stop����
	private int userloaction = -1; // user�� com�� �и� ���߷��� ���� ��ġ
	private int userguess[] = new int[10]; // ���� ���߱Ⱑ ������ ����
	String black = "#000000";
	String white = "#ffffff";
	String red = "#ff0000";
	// Button 01-13 : com ī��
	// Button 14-26 : user ī��
	// Button 27-36 : user�� comī�� ���� �� �����ϴ� ī��

	Card com = new Card();
	Card user = new Card();
	Scanner n = new Scanner(System.in);
	Random rand = new Random();

	// ���� ��ư
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			android.os.Process.killProcess(android.os.Process.myPid());
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gamestart);

		// ī�� ��ư ����
		final Button blackCard = (Button) findViewById(R.id.Button_Black);
		final Button whiteCard = (Button) findViewById(R.id.Button_White);
		final Button stopCard = (Button) findViewById(R.id.Button_Stop);
		final Button goCard = (Button) findViewById(R.id.Button_Go);
		final Button card00 = (Button) findViewById(cardId);
		final Button card01 = (Button) findViewById(cardId + 1);
		final Button card02 = (Button) findViewById(cardId + 2);
		final Button card03 = (Button) findViewById(cardId + 3);
		final Button card04 = (Button) findViewById(cardId + 4);
		final Button card05 = (Button) findViewById(cardId + 5);
		final Button card06 = (Button) findViewById(cardId + 6);
		final Button card07 = (Button) findViewById(cardId + 7);
		final Button card08 = (Button) findViewById(cardId + 8);
		final Button card09 = (Button) findViewById(cardId + 9);

		Button com00 = (Button) findViewById(comId);
		Button com01 = (Button) findViewById(comId + 1);
		Button com02 = (Button) findViewById(comId + 2);
		Button com03 = (Button) findViewById(comId + 3);
		Button com04 = (Button) findViewById(comId + 4);
		Button com05 = (Button) findViewById(comId + 5);
		Button com06 = (Button) findViewById(comId + 6);
		Button com07 = (Button) findViewById(comId + 7);
		Button com08 = (Button) findViewById(comId + 8);
		Button com09 = (Button) findViewById(comId + 9);
		Button com10 = (Button) findViewById(comId + 10);
		Button com11 = (Button) findViewById(comId + 11);
		Button com12 = (Button) findViewById(comId + 12);
		final TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);

		scrollView.post(new Runnable() {
			@Override
			public void run() {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});

		// �߾� ī���ư �̹��� ����
		blackCard.setBackgroundResource(R.drawable.black);
		whiteCard.setBackgroundResource(R.drawable.white);

		// ������ �и� ������ �� �̺�Ʈ ����
		blackCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (color == -1) { // color�� -1�϶� ī�带 ���� �� �ְ� ����
					color = 0; // �������� �������Ƿ� color�� 0���� ����
					if (Card.numBcard > 0) {
						String num = Integer.toString(--Card.numBcard); // ����ī�尹����
																		// �ϳ� ����
						blackCard.setText(num); // ���� ���
						user.pull(color); // ī�� �̱����
						for (int j = user.pullcard[0]; j < user.cardNum; j++) { // ����ī�带
																				// ����ϴ�
																				// �Լ�
							printCardColor(userId + j, user.mycard[j]);
							if (user.showcard[j] != -1)
								printCardNum(userId + j, user.mycard[j] * -1);
							else
								printCardNum(userId + j, user.mycard[j]);
						}
						if (user.mycard[4] == -1) // ó�� 5���� �̱����� ������ �Լ�
							color = -1;
						else if (com.mycard[5] == -1) {
							textview.append("\n �� COM TURN ��");
							firstTurn();
						} else
							userTurn();
					}
				}
			}
		});
		// ��� �и� ������ �� �̺�Ʈ ����
		whiteCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (color == -1) {
					color = 1;
					if (Card.numWcard > 0) {
						String num = Integer.toString(--Card.numWcard);
						whiteCard.setText(num);
						user.pull(color);
						for (int j = user.pullcard[0]; j < user.cardNum; j++) {
							printCardColor(userId + j, user.mycard[j]);
							if (user.showcard[j] != -1)
								printCardNum(userId + j, user.mycard[j] * -1);
							else
								printCardNum(userId + j, user.mycard[j]);
						}
						if (user.mycard[4] == -1) // ó�� 5���� �̱����� ������ �Լ�
							color = -1;
						else if (com.mycard[5] == -1) {
							textview.append("\n ��  COM TURN ��");
							firstTurn();
						} else
							userTurn();
					}
				}
			}
		});
		// go �и� ������ �� �̺�Ʈ ����
		goCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userturn == 1) {
					userturn = -1; // -1�϶� com�� �и� �����Ҽ��� ����
					stopCard.setBackgroundResource(R.drawable.noframe);
					goCard.setBackgroundResource(R.drawable.noframe);
				}
			}
		});
		// stop �и� ������ �� �̺�Ʈ ����
		stopCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userturn == 1) {
					userturn = 0;
					stopCard.setBackgroundResource(R.drawable.noframe);
					goCard.setBackgroundResource(R.drawable.noframe);
					comTurn();
				}
			}
		});
		// COM00 �и� ������ �� �̺�Ʈ ����
		com00.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[0] == -1)
						&& (com.mycard[0] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 0;
					Button card;
					if (com.mycard[num] % 2 == 1)
						min = 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];
					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 0;
			}
		});
		// COM01 �и� ������ �� �̺�Ʈ ����
		com01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[1] == -1)
						&& (com.mycard[1] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26;
					int num = 1;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 1;
			}
		});
		// COM02 �и� ������ �� �̺�Ʈ ����
		com02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[2] == -1)
						&& (com.mycard[2] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26;
					int num = 2;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}

				userloaction = 2;
			}
		});
		// COM03 �и� ������ �� �̺�Ʈ ����
		com03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[3] == -1)
						&& (com.mycard[3] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 3;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 3;
			}
		});
		// COM04 �и� ������ �� �̺�Ʈ ����
		com04.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[4] == -1)
						&& (com.mycard[4] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 4;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 4;
			}
		});
		// COM05 �и� ������ �� �̺�Ʈ ����
		com05.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[5] == -1)
						&& (com.mycard[5] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 5;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 5;
			}
		});
		// COM06 �и� ������ �� �̺�Ʈ ����
		com06.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[6] == -1)
						&& (com.mycard[6] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 6;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 6;
			}
		});
		// COM07 �и� ������ �� �̺�Ʈ ����
		com07.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[7] == -1)
						&& (com.mycard[7] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 7;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 7;
			}
		});
		// COM08 �и� ������ �� �̺�Ʈ ����
		com08.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[8] == -1)
						&& (com.mycard[8] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 8;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 8;
			}
		});
		// COM09 �и� ������ �� �̺�Ʈ ����
		com09.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[9] == -1)
						&& (com.mycard[9] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 9;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 9;
			}
		});
		// COM10 �и� ������ �� �̺�Ʈ ����
		com10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[10] == -1)
						&& (com.mycard[10] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 10;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 10;
			}
		});
		// COM11 �и� ������ �� �̺�Ʈ ����
		com11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[11] == -1)
						&& (com.mycard[11] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 11;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) {
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 11;
			}
		});
		// COM12 �и� ������ �� �̺�Ʈ ����
		com12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[12] == -1)
						&& (com.mycard[12] != -1)) { // �������� ����ī���� ���
					int j = 0;
					int min = 0, max = 26, num = 12;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // ��-�� �����϶� ���� ������ ���
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // ����
																				// ����
																				// ���
								min = com.showcard[i] + 2;
							else
								// �ٸ� ���� ���
								min = com.showcard[i] + 1;
					for (int i = 12; i > num; i--)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 < com.mycard[i] % 2)
									&& (i - 1 == num))
								max = com.showcard[i] + 1;
							else
								max = com.showcard[i];

					for (int i = min; i < max; i += 2) {
						card = (Button) findViewById(cardId + j);
						if ((com.CheckGuessNumber(i, 1))
								&& (user.CheckGuessNumber(i, 0))) {
							String text = Integer.toString(i / 2);
							if (i / 2 == 12) // ��Ŀ ���
								card.setText("��");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) { // ���ڰ� ��µ� ��ġ�� ��ȫ��ī�� ���
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 12;
			}
		});
		// card00 �и� ������ �� �̺�Ʈ ����
		card00.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[0] != -1) {
					if (!(com.testCard(userloaction, userguess[0]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[0] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card01 �и� ������ �� �̺�Ʈ ����
		card01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[1] != -1) {
					if (!(com.testCard(userloaction, userguess[1]))) { // Ʋ�����
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else { // ������
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[1] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card02 �и� ������ �� �̺�Ʈ ����
		card02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[2] != -1) {
					if (!(com.testCard(userloaction, userguess[2]))) { // Ʋ�����
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else { // ������ ����
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[2] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card03 �и� ������ �� �̺�Ʈ ����
		card03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[3] != -1) {
					if (!(com.testCard(userloaction, userguess[3]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[3] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card04 �и� ������ �� �̺�Ʈ ����
		card04.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[4] != -1) {
					if (!(com.testCard(userloaction, userguess[4]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[4] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card05 �и� ������ �� �̺�Ʈ ����
		card05.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[5] != -1) {
					if (!(com.testCard(userloaction, userguess[5]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[5] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card06 �и� ������ �� �̺�Ʈ ����
		card06.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[6] != -1) {
					if (!(com.testCard(userloaction, userguess[6]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[6] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card07 �и� ������ �� �̺�Ʈ ����
		card07.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[7] != -1) {
					if (!(com.testCard(userloaction, userguess[7]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[7] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card08 �и� ������ �� �̺�Ʈ ����
		card08.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[8] != -1) {
					if (!(com.testCard(userturn, userguess[8]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[8] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card09 �и� ������ �� �̺�Ʈ ����
		card09.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[9] != -1) {
					if (!(com.testCard(userturn, userguess[9]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // ������ �� ����
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // ������ ����
						printCardNum(comId + userloaction, userguess[9] * -1);

						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n ��� ���߽÷��� GO��ư��, �׸��Ͻ÷��� STOP��ư�� �����ּ���\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// ī���� �� �ʱ�ȭ
		for (int i = 0; i < 26; i++)
			Card.mainCard[i] = i;

		firstPull(); // com�� 5���� �и� ����
	}

	int selectColor() { // ���� ī�� �������ϴ� �Լ�
		final Button blackCard = (Button) findViewById(R.id.Button_Black);
		final Button whiteCard = (Button) findViewById(R.id.Button_White);
		while (true) {
			color = rand.nextInt(2);
			if (color == 0 && Card.numBcard > 0) {// �������� ���õ� ���
				String num = Integer.toString(--Card.numBcard);
				blackCard.setText(num);
				break;
			} else if (color == 1 && Card.numWcard > 0) { // ����� ���õ� ���
				String num = Integer.toString(--Card.numWcard);
				whiteCard.setText(num);
				break;
			}
		}
		return color;
	}

	void firstPull() { // �� ó�� 5���� �и� �̴� �Լ�
		TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);

		scrollView.post(new Runnable() {
			@Override
			public void run() {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});

		for (int i = 0; i < 5; i++) { // ���� com�� 5���� �и� ����
			com.pull(selectColor()); //
			for (int j = com.pullcard[0]; j < com.cardNum; j++)
				printCardColor(comId + j, com.mycard[j]);
		}
		color = -1;
		// ���
		textview.append(" ��  USER TURN �� \n �� 5���� ī�带 ����������.\n");
	}

	void firstTurn() { // ��ǻ���� ù��° ��
		final TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
		int location = rand.nextInt(2); // ��� ��������
		int guess = -1;

		scrollView.post(new Runnable() {
			@Override
			public void run() {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});

		if (location == 1)
			location = 4;

		com.pull(selectColor());
		for (int j = com.pullcard[0]; j < com.cardNum; j++)
			printCardColor(comId + j, com.mycard[j]);

		guess = com.firstTurn(location, user.infoCardColor(location));

		if (!(user.testCard(location, guess))) { // Ʋ�����
			failureMessage("COM");
			com.openCard(com.pullcard[0]);
			printCardNum(comId + com.pullcard[0], com.pullcard[1] * -1);
		} else { // ���� ���
			successMessage("USER");
			user.openCard(location);
			printCardNum(userId + location, user.mycard[location] * -1);
		}
		color = -1;
		// ���
		textview.append("\n ��  USER TURN ��\n");
		textview.append(" �� �� ���� ī�带 �̾��ּ���\n");

	}

	void userTurn() {
		final TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
		scrollView.post(new Runnable() {
			@Override
			public void run() {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});
		userturn = -1;

		for (int i = 0; i < 10; i++)
			userguess[i] = -1;
		// ���
		textview.append(" ���߷��� COM�� ī�带 �����ϼ���.\n");
	}

	void comTurn() { // ��ǻ���� ù��°�� ������ ��
		final TextView textview = (TextView) findViewById(R.id.textView1);
		Button card;
		int location = 0;
		int guess = -1;

		if (Pull.CheckPullCard()) { // ����ī�尡 ���� ��� ����
			com.pull(selectColor());
			for (int j = com.pullcard[0]; j < com.cardNum; j++) {
				printCardColor(comId + j, com.mycard[j]);
				if (com.showcard[j] != -1)
					printCardNum(comId + j, com.mycard[j] * -1);
				else {
					card = (Button) findViewById(comId + j);
					card.setText(" ");
				}
			}
		}

		textview.append("\n��  COM TURN ��");

		do {
			location = user.getGuessLocation();
			do {
				guess = rand.nextInt(23);
			} while ((!com.CheckGuessNumber(guess, 0))
					|| !(user.CheckGuessNumber(guess, 1)));

			if (!(user.testCard(location, guess))) {
				failureMessage("COM");
				com.openCard(com.pullcard[0]);
				printCardNum(comId + com.pullcard[0], com.pullcard[1] * -1);

				if (com.CheckFinish())
					GameOver("COM");
				break;
			} else {
				successMessage("USER");
				user.openCard(location);
				printCardNum(userId + location, user.mycard[location] * -1);

				if (user.CheckFinish())
					GameOver("USER");
			}
		} while (rand.nextInt(2) == 1);

		if (Pull.CheckPullCard()) {
			color = -1;
			// ���
			// textview.append("\n ��  USER TURN ��\n");
			// textview.append(" �� �� ���� ī�带 �̾��ּ���\n");
		} else {
			// ���
			textview.append("\n ��  USER TURN ��\n");
			userTurn();
		}
	}

	void printCardColor(int idNum, int cardNum) { // ���� ī�带 ���� ����ϴ� �Լ�
		Button card = (Button) findViewById(idNum);
		if (cardNum % 2 == 0) // �������� ���
			card.setBackgroundResource(R.drawable.black);
		else
			// ����� ���
			card.setBackgroundResource(R.drawable.white);
	}

	void successMessage(String turn) {
		TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);

		scrollView.post(new Runnable() {
			@Override
			public void run() {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});
		textview.append("\n");
		textview.append(" \"�¾ҽ��ϴ�.\"\n ");
		textview.append(" " + turn + "�и� �����մϴ�.\n");
	}

	void failureMessage(String turn) {
		TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);

		scrollView.post(new Runnable() {
			@Override
			public void run() {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});

		textview.append("\n");
		textview.append(" \"Ʋ�Ƚ��ϴ�\"\n ");
		textview.append(" " + turn + "�и� �����մϴ�.\n");
	}

	void printCardNum(int idNum, int cardNum) { // idNum��ġ�� ���� ����ϴ� �Լ�
		Button card = (Button) findViewById(idNum);
		if (cardNum < 0) { // �������� ���
			card.setTextColor(Color.parseColor(red));
			cardNum *= -1;
		} else if (cardNum % 2 == 0) // �������� ���
			card.setTextColor(Color.parseColor(white));
		else
			// ����� ���
			card.setTextColor(Color.parseColor(black));

		String text = Integer.toString(cardNum / 2);
		if (cardNum / 2 == 12) // ��Ŀ ���
			card.setText("��");
		else
			card.setText(text);

		for (int i = 0; i < 10; i++) {
			card = (Button) findViewById(cardId + i);
			card.setBackgroundResource(R.drawable.noframe);
			card.setText(" ");
		}
	}

	void GameOver(String turn) {
		Button score = (Button) findViewById(R.id.btn_score);
		TextView textview = (TextView) findViewById(R.id.textView1);
		// textview.append("\n ***������ ����Ǿ����ϴ�***\n");
		// textview.append(" " + turn + "�� �¸��Դϴ�\n");
		if (turn == "COM") {
			score.setBackgroundResource(R.drawable.win);
			textview.setText(" ");
			score.setOnClickListener(new OnClickListener() {
				public void onClick(android.view.View v) {
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			});
		} else {
			textview.setText(" ");
			score.setBackgroundResource(R.drawable.lose);
			score.setOnClickListener(new OnClickListener() {
				public void onClick(android.view.View v) {
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			});

		}

		//
	}
}

class Pull {
	static Scanner n = new Scanner(System.in);
	static Random rand = new Random();

	static int pullCard(int color) { // ī�弱��
		int card = -1;
		while (color == 0) { // ������ �̱�
			card = (rand.nextInt(26) / 2) * 2;
			if (Card.mainCard[card] != -1) {
				Card.mainCard[card] = -1;
				return card;
			}
		}
		while (color == 1) { // ����� �̱�
			card = (rand.nextInt(26) / 2) * 2 + 1;
			if (Card.mainCard[card] != -1) {
				Card.mainCard[card] = -1;
				return card;
			}
		}
		return card;
	}

	static boolean CheckPullCard() { // ī�带 ������ �ִ��� ������ �˻��ϴ� �Լ�
		if ((Card.numWcard == 0) && (Card.numBcard == 0))
			return false;
		else
			return true;
	}
}

class Card extends Activity {
	static int mainCard[] = new int[26]; // ���� ������ ���� ī���� �迭, ������ ���� -1�� ����
	static int numWcard = 13; // ���� ī���� ��
	static int numBcard = 13; // ���� ī���� ��
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

	void pull(int color) { // ī�� �̱�
		int i = 0;

		pullcard[1] = Pull.pullCard(color); // ���� ī�� �� ����
		cardNum++;

		for (i = cardNum - 2; i >= 0 && mycard[i] > pullcard[1]; i--) {
			mycard[i + 1] = mycard[i]; // ���ڵ��� ������ �̵�
			showcard[i + 1] = showcard[i];
		}
		mycard[i + 1] = pullcard[1] + joker;
		showcard[i + 1] = -1;
		pullcard[0] = i + 1;
	}

	int infoCardColor(int location) { // ��ġ�� ������ �������ִ� �Լ�
		return mycard[location] % 2;
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

	boolean CheckGuessNumber(int num, int turn) { // �����Ϸ��� ���� ���� �̹� �������ִ��� �ƴ���
													// �Ǵ�(false�� ��� ���� ����)
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

	boolean CheckGuessLocation(int num) { // �����Ϸ��� ��ġ�� �̹� �����Ǿ��ִ��� �ƴ��� �Ǵ�(false��
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
}