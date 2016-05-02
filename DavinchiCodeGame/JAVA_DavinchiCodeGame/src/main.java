import java.util.Random;
import java.util.Scanner;

class Pull{
	   static int color;
	   static Scanner n = new Scanner(System.in);
	   static Random rand = new Random();
	   static void pullColor(boolean random) { // 색깔 선택
		      if (random == false) {
		         while (true) {
		            System.out.println("검은색 패의 갯수 : " + Card.numBcard
		                  + " 흰색 패의 갯수 : " + Card.numWcard);
		            System.out.print("패 색상 >> ");
		            color = n.nextInt();
		            if (color == 0 && Card.numBcard > 0)
		               return;
		            else if (color == 1 && Card.numWcard > 0)
		               return;
		            else
		               System.out.println("잘못입력하셨습니다. 다시입력해주시기 바랍니다.");
		         }
		      } else {
		         while (true) {
		            color = rand.nextInt(2);
		            if (color == 0 && Card.numBcard > 0)
		               return;
		            else if (color == 1 && Card.numWcard > 0)
		               return;
		         }
		      }
		   }

		   static int pullCard() { // 카드선택
		      int card = -1;
		      while (color == 0) { // 검은패 뽑기
		         card = (rand.nextInt(26) / 2) * 2;
		         if (Card.mainCard[card] != -1) {
		            Card.numBcard--;
		            Card.mainCard[card] = -1;
		            return card;
		         }
		      }
		      while (color == 1) { // 흰색패 뽑기
		         card = (rand.nextInt(26) / 2) * 2 + 1;
		         if (Card.mainCard[card] != -1) {
		            Card.numWcard--;
		            Card.mainCard[card] = -1;
		            return card;
		         }
		      }
		      return card;
		   }
		   static boolean CheckPullCard(){ // 카드를 뽑을수 있는지 없는지 검사하는 함수
		      if((Card.numWcard==0) && (Card.numBcard==0))
		         return true;
		      else return false;
		   }
}

class Card {
   static int mainCard[] = new int[26]; // 아직 뽑히지 않은 카드의 배열, 뽑히면 값이 -1로 변경
   static int numWcard = 13; // 남은 카드의 수
   static int numBcard = 13; // 남은 카드의 수
   int mycard[] = new int[13]; // 내가 현재 가지고 있는 카드
   int showcard[] = new int[13]; // 상대방에게 보여지고 있는 카드
   int pullcard[] = new int[2]; // 0은 뽑은 카드의 인덱스, 1은 뽑은카드의 값
   int cardNum; // 내가 현재 가지고 있는 카드의 수
   Scanner n = new Scanner(System.in);
   Random rand = new Random();

   Card() {
      cardNum = 0;
      for (int i = 0; i < 13; i++)
         showcard[i] = -1;
   }
   void pull(boolean com) { // 카드 뽑기
	      int i;
	      if(Pull.CheckPullCard()) // 뽑을카드가 없는 경우 리턴
	    	  return;
	      if(com == true)
	    	  Pull.pullColor(true);
	      else
	    	  Pull.pullColor(false);
	      pullcard[1] = Pull.pullCard();
	      cardNum++;
	      for (i = cardNum - 2; i >= 0 && mycard[i] > pullcard[1]; i--)
	         mycard[i + 1] = mycard[i]; // 레코드의 오른쪽 이동
	      mycard[i + 1] = pullcard[1];
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
	      if (location == -1)
	         showcard[pullcard[0]] = pullcard[1];
	      else
	         showcard[location] = mycard[location];
	   }

	   int getGuessLocation() { // com이 추측할 위치의 숫자를 리턴해주는 함수
	      int location = -1;
	      while (true) {
	         location = rand.nextInt(cardNum);
	         if (showcard[location] == -1)
	            return location;
	      }
	   }

	   boolean CheckGuessNumber(int num, int turn) { // 추측하려는 수를 내가 이미 가지고있는지 아닌지
	                                       // 판단(false일 경우 추측 가능)
	      if (turn == 0) { // 내 턴일 경우
	         for (int i = 0; i < cardNum; i++)
	            if (mycard[i] == num)
	               return true;
	      } else { // 상대방 턴일 경우
	         for (int i = 0; i < cardNum; i++)
	            if (showcard[i] == num)
	               return true;
	      }
	      return false;
	   }

	   boolean CheckGuessLocation(int num) { // 추측하려는 위치가 이미 공개되어있는지 아닌지 판단(false일
	                                 // 경우 추측 가능)
	      if (showcard[num] == -1)
	         return false;
	      else
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

class View {
	   static void viewMyCard(int cardNum, int mycard[] ) { // 현재 가지고 있는 패를 전부 출력
	      for (int i = 0; i < cardNum; i++)
	         System.out.print(mycard[i] + " ");
	   }

	   static void viewShowCard(int cardNum, int showcard[]) { // 현재 상대방에게 보여지고 있는 패만 출력
	      for (int i = 0; i < cardNum; i++) {
	         if (showcard[i] == -1)
	            System.out.print("--" + " ");
	         else
	            System.out.print(showcard[i] + " ");
	      }
	   }
}


class Game {
   Card com = new Card();
   Card user = new Card();
   Scanner n = new Scanner(System.in);
   Random rand = new Random();

   void viewMyCard() {
      System.out.println("# 가지고 있는 패");
      System.out.print("Com : ");
      View.viewMyCard(com.cardNum, com.mycard);
      System.out.print("\nUser : ");
      View.viewMyCard(user.cardNum, user.mycard);
      System.out.println("\n\n");
   }

   void viewShowCard() {
      System.out.println("# 실제 보여지는 카드 패");
      System.out.print("Com : ");
      View.viewShowCard(com.cardNum, com.mycard);
      System.out.print("\nUser : ");
      View.viewShowCard(user.cardNum, user.mycard);
      System.out.println("\n\n");
   }

   void Start() {
      //System.out.println("# com과 user가 번갈아 가며 각각 5개의 패를 가져갑니다.(검은색 : 0, 흰색 : 1)");
      
      for (int i = 0; i < 5; i++) {
         com.pull(true);
         user.pull(false);
      }
      viewMyCard();
   }

   void firstTurn() { // 컴퓨터의 첫번째 턴
      int location = rand.nextInt(2);
      int guess = -1;
      if (location == 1)
         location = 4;
      com.pull(true);
      guess = com.firstTurn(location, user.infoCardColor(location));
      System.out.println("location : " + location + " guess : " + guess
            + "\n\n");
      if (!(user.testCard(location, guess))) {
         System.out.println("# 틀렸습니다. 가져온 패를 공개합니다. ");
         com.openCard(-1);
         viewShowCard();
      } else {
         System.out.println("# 정답입니다. 맞춘 패를 공개합니다. ");
         user.openCard(location);
         viewShowCard();
      }
   }

   void userTurn() {
      int location = -1;
      int guess = -1;
      int answer = 0;
      user.pull(false);
      do {
         do {
            System.out.print("# 선택할 컴퓨터의 패 위치를 입력하세요 >> ");
            location = n.nextInt();
         } while (com.CheckGuessLocation(location));
         do {
            System.out.print("# 예상하는 숫자를 입력하세요 >> ");
            guess = n.nextInt();
         } while ((com.CheckGuessNumber(guess, 1))
               || (user.CheckGuessNumber(guess, 0)));
         if (!(com.testCard(location, guess))) {
            System.out.println("# 틀렸습니다. 가져온 패를 공개합니다. ");
            user.openCard(-1);
            viewShowCard();
            break;
         } else {
            System.out.println("# 정답입니다. 맞춘 패를 공개합니다. ");
            com.openCard(location);
            viewShowCard();
            System.out.print("# 한번 더 하시겠습니까? (1) yes  (0) no >> ");
            answer = n.nextInt();
         }
      } while (answer == 1);
   }

   void comTurn() {
      int location = 0;
      int guess = -1;

      com.pull(true);
      
      do{
         location = user.getGuessLocation();
         do {
            guess = rand.nextInt(23);
         } while ((com.CheckGuessNumber(guess, 0)) || (user.CheckGuessNumber(guess, 1)));

         System.out.println("location : " + location + " guess : " + guess + "\n");

         if (!(user.testCard(location, guess))) {
            System.out.println("# 틀렸습니다. 가져온 패를 공개합니다. ");
            com.openCard(-1);
            viewShowCard();
            break;
         } else {
            System.out.println("# 정답입니다. 맞춘 패를 공개합니다. ");
            user.openCard(location);
            viewShowCard();
         }
      } while (rand.nextInt(2) == 1);
   }

   void gamePlay() {
      Start();
      System.out.println("===COM TURN===\n");
      firstTurn();
      while (true) {
         System.out.println("===USER TURN===\n");
         userTurn();
         System.out.println("===COM TURN===\n");
         comTurn();
      }
   }
}

public class main {

	public static void main(String[] args) {

		Game player = new Game();

		// 카드패 값 초기화
		for (int i = 0; i < 26; i++) {
			Card.mainCard[i] = i;
		}

		player.gamePlay();
	}
}