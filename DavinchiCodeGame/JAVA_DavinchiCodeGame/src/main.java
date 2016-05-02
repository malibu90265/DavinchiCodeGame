import java.util.Random;
import java.util.Scanner;

class Pull{
	   static int color;
	   static Scanner n = new Scanner(System.in);
	   static Random rand = new Random();
	   static void pullColor(boolean random) { // ���� ����
		      if (random == false) {
		         while (true) {
		            System.out.println("������ ���� ���� : " + Card.numBcard
		                  + " ��� ���� ���� : " + Card.numWcard);
		            System.out.print("�� ���� >> ");
		            color = n.nextInt();
		            if (color == 0 && Card.numBcard > 0)
		               return;
		            else if (color == 1 && Card.numWcard > 0)
		               return;
		            else
		               System.out.println("�߸��Է��ϼ̽��ϴ�. �ٽ��Է����ֽñ� �ٶ��ϴ�.");
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

		   static int pullCard() { // ī�弱��
		      int card = -1;
		      while (color == 0) { // ������ �̱�
		         card = (rand.nextInt(26) / 2) * 2;
		         if (Card.mainCard[card] != -1) {
		            Card.numBcard--;
		            Card.mainCard[card] = -1;
		            return card;
		         }
		      }
		      while (color == 1) { // ����� �̱�
		         card = (rand.nextInt(26) / 2) * 2 + 1;
		         if (Card.mainCard[card] != -1) {
		            Card.numWcard--;
		            Card.mainCard[card] = -1;
		            return card;
		         }
		      }
		      return card;
		   }
		   static boolean CheckPullCard(){ // ī�带 ������ �ִ��� ������ �˻��ϴ� �Լ�
		      if((Card.numWcard==0) && (Card.numBcard==0))
		         return true;
		      else return false;
		   }
}

class Card {
   static int mainCard[] = new int[26]; // ���� ������ ���� ī���� �迭, ������ ���� -1�� ����
   static int numWcard = 13; // ���� ī���� ��
   static int numBcard = 13; // ���� ī���� ��
   int mycard[] = new int[13]; // ���� ���� ������ �ִ� ī��
   int showcard[] = new int[13]; // ���濡�� �������� �ִ� ī��
   int pullcard[] = new int[2]; // 0�� ���� ī���� �ε���, 1�� ����ī���� ��
   int cardNum; // ���� ���� ������ �ִ� ī���� ��
   Scanner n = new Scanner(System.in);
   Random rand = new Random();

   Card() {
      cardNum = 0;
      for (int i = 0; i < 13; i++)
         showcard[i] = -1;
   }
   void pull(boolean com) { // ī�� �̱�
	      int i;
	      if(Pull.CheckPullCard()) // ����ī�尡 ���� ��� ����
	    	  return;
	      if(com == true)
	    	  Pull.pullColor(true);
	      else
	    	  Pull.pullColor(false);
	      pullcard[1] = Pull.pullCard();
	      cardNum++;
	      for (i = cardNum - 2; i >= 0 && mycard[i] > pullcard[1]; i--)
	         mycard[i + 1] = mycard[i]; // ���ڵ��� ������ �̵�
	      mycard[i + 1] = pullcard[1];
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
	      if (location == -1)
	         showcard[pullcard[0]] = pullcard[1];
	      else
	         showcard[location] = mycard[location];
	   }

	   int getGuessLocation() { // com�� ������ ��ġ�� ���ڸ� �������ִ� �Լ�
	      int location = -1;
	      while (true) {
	         location = rand.nextInt(cardNum);
	         if (showcard[location] == -1)
	            return location;
	      }
	   }

	   boolean CheckGuessNumber(int num, int turn) { // �����Ϸ��� ���� ���� �̹� �������ִ��� �ƴ���
	                                       // �Ǵ�(false�� ��� ���� ����)
	      if (turn == 0) { // �� ���� ���
	         for (int i = 0; i < cardNum; i++)
	            if (mycard[i] == num)
	               return true;
	      } else { // ���� ���� ���
	         for (int i = 0; i < cardNum; i++)
	            if (showcard[i] == num)
	               return true;
	      }
	      return false;
	   }

	   boolean CheckGuessLocation(int num) { // �����Ϸ��� ��ġ�� �̹� �����Ǿ��ִ��� �ƴ��� �Ǵ�(false��
	                                 // ��� ���� ����)
	      if (showcard[num] == -1)
	         return false;
	      else
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

class View {
	   static void viewMyCard(int cardNum, int mycard[] ) { // ���� ������ �ִ� �и� ���� ���
	      for (int i = 0; i < cardNum; i++)
	         System.out.print(mycard[i] + " ");
	   }

	   static void viewShowCard(int cardNum, int showcard[]) { // ���� ���濡�� �������� �ִ� �и� ���
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
      System.out.println("# ������ �ִ� ��");
      System.out.print("Com : ");
      View.viewMyCard(com.cardNum, com.mycard);
      System.out.print("\nUser : ");
      View.viewMyCard(user.cardNum, user.mycard);
      System.out.println("\n\n");
   }

   void viewShowCard() {
      System.out.println("# ���� �������� ī�� ��");
      System.out.print("Com : ");
      View.viewShowCard(com.cardNum, com.mycard);
      System.out.print("\nUser : ");
      View.viewShowCard(user.cardNum, user.mycard);
      System.out.println("\n\n");
   }

   void Start() {
      //System.out.println("# com�� user�� ������ ���� ���� 5���� �и� �������ϴ�.(������ : 0, ��� : 1)");
      
      for (int i = 0; i < 5; i++) {
         com.pull(true);
         user.pull(false);
      }
      viewMyCard();
   }

   void firstTurn() { // ��ǻ���� ù��° ��
      int location = rand.nextInt(2);
      int guess = -1;
      if (location == 1)
         location = 4;
      com.pull(true);
      guess = com.firstTurn(location, user.infoCardColor(location));
      System.out.println("location : " + location + " guess : " + guess
            + "\n\n");
      if (!(user.testCard(location, guess))) {
         System.out.println("# Ʋ�Ƚ��ϴ�. ������ �и� �����մϴ�. ");
         com.openCard(-1);
         viewShowCard();
      } else {
         System.out.println("# �����Դϴ�. ���� �и� �����մϴ�. ");
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
            System.out.print("# ������ ��ǻ���� �� ��ġ�� �Է��ϼ��� >> ");
            location = n.nextInt();
         } while (com.CheckGuessLocation(location));
         do {
            System.out.print("# �����ϴ� ���ڸ� �Է��ϼ��� >> ");
            guess = n.nextInt();
         } while ((com.CheckGuessNumber(guess, 1))
               || (user.CheckGuessNumber(guess, 0)));
         if (!(com.testCard(location, guess))) {
            System.out.println("# Ʋ�Ƚ��ϴ�. ������ �и� �����մϴ�. ");
            user.openCard(-1);
            viewShowCard();
            break;
         } else {
            System.out.println("# �����Դϴ�. ���� �и� �����մϴ�. ");
            com.openCard(location);
            viewShowCard();
            System.out.print("# �ѹ� �� �Ͻðڽ��ϱ�? (1) yes  (0) no >> ");
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
            System.out.println("# Ʋ�Ƚ��ϴ�. ������ �и� �����մϴ�. ");
            com.openCard(-1);
            viewShowCard();
            break;
         } else {
            System.out.println("# �����Դϴ�. ���� �и� �����մϴ�. ");
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

		// ī���� �� �ʱ�ȭ
		for (int i = 0; i < 26; i++) {
			Card.mainCard[i] = i;
		}

		player.gamePlay();
	}
}