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

//게임 시작 activity
public class GameStart extends Activity {
	private int comId = R.id.Button01;
	private int userId = R.id.Button14;
	private int cardId = R.id.Button27;
	private static int color = 2;
	private int userturn = 0; // -1일 경우 컴퓨터 패 선택, 1일 경우 go,stop선택
	private int userloaction = -1; // user가 com의 패를 맞추려고 누른 위치
	private int userguess[] = new int[10]; // 현재 맞추기가 가능한 숫자
	String black = "#000000";
	String white = "#ffffff";
	String red = "#ff0000";
	// Button 01-13 : com 카드
	// Button 14-26 : user 카드
	// Button 27-36 : user가 com카드 맞출 때 선택하는 카드

	Card com = new Card();
	Card user = new Card();
	Scanner n = new Scanner(System.in);
	Random rand = new Random();

	// 이전 버튼
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

		// 카드 버튼 선언
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

		// 중앙 카드버튼 이미지 저장
		blackCard.setBackgroundResource(R.drawable.black);
		whiteCard.setBackgroundResource(R.drawable.white);

		// 검은색 패를 눌렀을 때 이벤트 설정
		blackCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (color == -1) { // color가 -1일때 카드를 뽑을 수 있게 설정
					color = 0; // 검은색을 눌렀으므로 color를 0으로 설정
					if (Card.numBcard > 0) {
						String num = Integer.toString(--Card.numBcard); // 남은카드갯수를
																		// 하나 감소
						blackCard.setText(num); // 숫자 출력
						user.pull(color); // 카드 뽑기기기기
						for (int j = user.pullcard[0]; j < user.cardNum; j++) { // 뽑은카드를
																				// 출력하는
																				// 함수
							printCardColor(userId + j, user.mycard[j]);
							if (user.showcard[j] != -1)
								printCardNum(userId + j, user.mycard[j] * -1);
							else
								printCardNum(userId + j, user.mycard[j]);
						}
						if (user.mycard[4] == -1) // 처음 5번을 뽑기위해 설정한 함수
							color = -1;
						else if (com.mycard[5] == -1) {
							textview.append("\n ━ COM TURN ━");
							firstTurn();
						} else
							userTurn();
					}
				}
			}
		});
		// 흰색 패를 눌렀을 때 이벤트 설정
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
						if (user.mycard[4] == -1) // 처음 5번을 뽑기위해 설정한 함수
							color = -1;
						else if (com.mycard[5] == -1) {
							textview.append("\n ━  COM TURN ━");
							firstTurn();
						} else
							userTurn();
					}
				}
			}
		});
		// go 패를 눌렀을 때 이벤트 설정
		goCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userturn == 1) {
					userturn = -1; // -1일때 com의 패를 선택할수가 있음
					stopCard.setBackgroundResource(R.drawable.noframe);
					goCard.setBackgroundResource(R.drawable.noframe);
				}
			}
		});
		// stop 패를 눌렀을 때 이벤트 설정
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
		// COM00 패를 눌렀을 때 이벤트 설정
		com00.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[0] == -1)
						&& (com.mycard[0] != -1)) { // 공개되지 않은카드인 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM01 패를 눌렀을 때 이벤트 설정
		com01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[1] == -1)
						&& (com.mycard[1] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26;
					int num = 1;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM02 패를 눌렀을 때 이벤트 설정
		com02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[2] == -1)
						&& (com.mycard[2] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26;
					int num = 2;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM03 패를 눌렀을 때 이벤트 설정
		com03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[3] == -1)
						&& (com.mycard[3] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 3;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM04 패를 눌렀을 때 이벤트 설정
		com04.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[4] == -1)
						&& (com.mycard[4] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 4;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM05 패를 눌렀을 때 이벤트 설정
		com05.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[5] == -1)
						&& (com.mycard[5] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 5;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM06 패를 눌렀을 때 이벤트 설정
		com06.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[6] == -1)
						&& (com.mycard[6] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 6;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM07 패를 눌렀을 때 이벤트 설정
		com07.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[7] == -1)
						&& (com.mycard[7] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 7;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM08 패를 눌렀을 때 이벤트 설정
		com08.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[8] == -1)
						&& (com.mycard[8] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 8;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM09 패를 눌렀을 때 이벤트 설정
		com09.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[9] == -1)
						&& (com.mycard[9] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 9;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM10 패를 눌렀을 때 이벤트 설정
		com10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[10] == -1)
						&& (com.mycard[10] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 10;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM11 패를 눌렀을 때 이벤트 설정
		com11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[11] == -1)
						&& (com.mycard[11] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 11;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
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
		// COM12 패를 눌렀을 때 이벤트 설정
		com12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if ((userturn == -1) && (com.showcard[12] == -1)
						&& (com.mycard[12] != -1)) { // 공개되지 않은카드인 경우
					int j = 0;
					int min = 0, max = 26, num = 12;
					Button card;

					if (com.mycard[num] % 2 == 1)
						min = 1;

					for (int i = 0; i < num; i++)
						if (com.showcard[i] != -1)
							if ((com.mycard[num] % 2 > com.mycard[i] % 2)
									&& (i + 1 == num)) // 검-흰 순서일때 같은 숫자일 경우
								min = com.showcard[i] - 1;
							else if (com.mycard[num] % 2 == com.mycard[i] % 2) // 같은
																				// 색일
																				// 경우
								min = com.showcard[i] + 2;
							else
								// 다른 색일 경우
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
							if (i / 2 == 12) // 조커 출력
								card.setText("━");
							else
								card.setText(text);
							userguess[j] = i;
							j++;
						}
					}
					for (j -= 1; j >= 0; j--) { // 숫자가 출력된 위치에 분홍색카드 출력
						card = (Button) findViewById(cardId + j);
						card.setBackgroundResource(R.drawable.pink);
					}
				}
				userloaction = 12;
			}
		});
		// card00 패를 눌렀을 때 이벤트 설정
		card00.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[0] != -1) {
					if (!(com.testCard(userloaction, userguess[0]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[0] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card01 패를 눌렀을 때 이벤트 설정
		card01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[1] != -1) {
					if (!(com.testCard(userloaction, userguess[1]))) { // 틀린경우
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else { // 맞춘경우
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[1] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card02 패를 눌렀을 때 이벤트 설정
		card02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[2] != -1) {
					if (!(com.testCard(userloaction, userguess[2]))) { // 틀린경우
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else { // 맞춘패 공개
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[2] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card03 패를 눌렀을 때 이벤트 설정
		card03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[3] != -1) {
					if (!(com.testCard(userloaction, userguess[3]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[3] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card04 패를 눌렀을 때 이벤트 설정
		card04.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[4] != -1) {
					if (!(com.testCard(userloaction, userguess[4]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[4] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card05 패를 눌렀을 때 이벤트 설정
		card05.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[5] != -1) {
					if (!(com.testCard(userloaction, userguess[5]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[5] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card06 패를 눌렀을 때 이벤트 설정
		card06.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[6] != -1) {
					if (!(com.testCard(userloaction, userguess[6]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[6] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card07 패를 눌렀을 때 이벤트 설정
		card07.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[7] != -1) {
					if (!(com.testCard(userloaction, userguess[7]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[7] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card08 패를 눌렀을 때 이벤트 설정
		card08.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[8] != -1) {
					if (!(com.testCard(userturn, userguess[8]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[8] * -1);
						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// card09 패를 눌렀을 때 이벤트 설정
		card09.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (userguess[9] != -1) {
					if (!(com.testCard(userturn, userguess[9]))) {
						failureMessage("USER");
						user.openCard(user.pullcard[0]); // 가져온 패 공개
						printCardNum(userId + user.pullcard[0],
								user.pullcard[1] * -1);
						if (com.CheckFinish()) {
							GameOver("COM");
							return;
						}
						comTurn();
					} else {
						successMessage("COM");
						com.openCard(userloaction); // 맞춘패 공개
						printCardNum(comId + userloaction, userguess[9] * -1);

						if (user.CheckFinish())
							GameOver("USER");
						else {
							userturn = 1;
							textview.append("\n 계속 맞추시려면 GO버튼을, 그만하시려면 STOP버튼을 눌러주세요\n");
							stopCard.setBackgroundResource(R.drawable.stop);
							goCard.setBackgroundResource(R.drawable.go);
						}
					}
				}
			}
		});
		// 카드패 값 초기화
		for (int i = 0; i < 26; i++)
			Card.mainCard[i] = i;

		firstPull(); // com이 5개의 패를 뽑음
	}

	int selectColor() { // 뽑을 카드 색깔선택하는 함수
		final Button blackCard = (Button) findViewById(R.id.Button_Black);
		final Button whiteCard = (Button) findViewById(R.id.Button_White);
		while (true) {
			color = rand.nextInt(2);
			if (color == 0 && Card.numBcard > 0) {// 검은색이 선택될 경우
				String num = Integer.toString(--Card.numBcard);
				blackCard.setText(num);
				break;
			} else if (color == 1 && Card.numWcard > 0) { // 흰색이 선택될 경우
				String num = Integer.toString(--Card.numWcard);
				whiteCard.setText(num);
				break;
			}
		}
		return color;
	}

	void firstPull() { // 맨 처음 5개의 패를 뽑는 함수
		TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);

		scrollView.post(new Runnable() {
			@Override
			public void run() {
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});

		for (int i = 0; i < 5; i++) { // 먼저 com이 5개의 패를 뽑음
			com.pull(selectColor()); //
			for (int j = com.pullcard[0]; j < com.cardNum; j++)
				printCardColor(comId + j, com.mycard[j]);
		}
		color = -1;
		// 흰색
		textview.append(" ━  USER TURN ━ \n ◀ 5개의 카드를 가져오세요.\n");
	}

	void firstTurn() { // 컴퓨터의 첫번째 턴
		final TextView textview = (TextView) findViewById(R.id.textView1);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
		int location = rand.nextInt(2); // 어디 지목할지
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

		if (!(user.testCard(location, guess))) { // 틀린경우
			failureMessage("COM");
			com.openCard(com.pullcard[0]);
			printCardNum(comId + com.pullcard[0], com.pullcard[1] * -1);
		} else { // 맞춘 경우
			successMessage("USER");
			user.openCard(location);
			printCardNum(userId + location, user.mycard[location] * -1);
		}
		color = -1;
		// 흰색
		textview.append("\n ━  USER TURN ━\n");
		textview.append(" ◀ 한 장의 카드를 뽑아주세요\n");

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
		// 흰색
		textview.append(" 맞추려는 COM의 카드를 선택하세요.\n");
	}

	void comTurn() { // 컴퓨터의 첫번째를 제외한 턴
		final TextView textview = (TextView) findViewById(R.id.textView1);
		Button card;
		int location = 0;
		int guess = -1;

		if (Pull.CheckPullCard()) { // 뽑을카드가 없는 경우 리턴
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

		textview.append("\n━  COM TURN ━");

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
			// 흰색
			// textview.append("\n ━  USER TURN ━\n");
			// textview.append(" ◀ 한 장의 카드를 뽑아주세요\n");
		} else {
			// 흰색
			textview.append("\n ━  USER TURN ━\n");
			userTurn();
		}
	}

	void printCardColor(int idNum, int cardNum) { // 뽑은 카드를 색만 출력하는 함수
		Button card = (Button) findViewById(idNum);
		if (cardNum % 2 == 0) // 검은색일 경우
			card.setBackgroundResource(R.drawable.black);
		else
			// 흰색일 경우
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
		textview.append(" \"맞았습니다.\"\n ");
		textview.append(" " + turn + "패를 공개합니다.\n");
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
		textview.append(" \"틀렸습니다\"\n ");
		textview.append(" " + turn + "패를 공개합니다.\n");
	}

	void printCardNum(int idNum, int cardNum) { // idNum위치에 숫자 출력하는 함수
		Button card = (Button) findViewById(idNum);
		if (cardNum < 0) { // 빨간색일 경우
			card.setTextColor(Color.parseColor(red));
			cardNum *= -1;
		} else if (cardNum % 2 == 0) // 검은색일 경우
			card.setTextColor(Color.parseColor(white));
		else
			// 흰색일 경우
			card.setTextColor(Color.parseColor(black));

		String text = Integer.toString(cardNum / 2);
		if (cardNum / 2 == 12) // 조커 출력
			card.setText("━");
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
		// textview.append("\n ***게임이 종료되었습니다***\n");
		// textview.append(" " + turn + "의 승리입니다\n");
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

	static int pullCard(int color) { // 카드선택
		int card = -1;
		while (color == 0) { // 검은패 뽑기
			card = (rand.nextInt(26) / 2) * 2;
			if (Card.mainCard[card] != -1) {
				Card.mainCard[card] = -1;
				return card;
			}
		}
		while (color == 1) { // 흰색패 뽑기
			card = (rand.nextInt(26) / 2) * 2 + 1;
			if (Card.mainCard[card] != -1) {
				Card.mainCard[card] = -1;
				return card;
			}
		}
		return card;
	}

	static boolean CheckPullCard() { // 카드를 뽑을수 있는지 없는지 검사하는 함수
		if ((Card.numWcard == 0) && (Card.numBcard == 0))
			return false;
		else
			return true;
	}
}

class Card extends Activity {
	static int mainCard[] = new int[26]; // 아직 뽑히지 않은 카드의 배열, 뽑히면 값이 -1로 변경
	static int numWcard = 13; // 남은 카드의 수
	static int numBcard = 13; // 남은 카드의 수
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

	void pull(int color) { // 카드 뽑기
		int i = 0;

		pullcard[1] = Pull.pullCard(color); // 뽑은 카드 값 저장
		cardNum++;

		for (i = cardNum - 2; i >= 0 && mycard[i] > pullcard[1]; i--) {
			mycard[i + 1] = mycard[i]; // 레코드의 오른쪽 이동
			showcard[i + 1] = showcard[i];
		}
		mycard[i + 1] = pullcard[1] + joker;
		showcard[i + 1] = -1;
		pullcard[0] = i + 1;
	}

	int infoCardColor(int location) { // 위치의 색깔을 리턴해주는 함수
		return mycard[location] % 2;
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

	boolean CheckGuessNumber(int num, int turn) { // 추측하려는 수를 내가 이미 가지고있는지 아닌지
													// 판단(false일 경우 추측 가능)
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

	boolean CheckGuessLocation(int num) { // 추측하려는 위치가 이미 공개되어있는지 아닌지 판단(false일
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
}