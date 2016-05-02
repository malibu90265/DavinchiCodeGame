#include <iostream>
#include <ctime>
#include <list>
#define X -1
using namespace std;
class Card{
	int color;
public:
	static int mainCard[26]; 
	static int numWcard;
	static int numBcard;
	void pullColor(bool random){ // 색깔 선택
		if(random == false){
			while(true){
				cout << "검은색 패의 갯수 : " << Card::numBcard << " 흰색 패의 갯수 : " << Card::numWcard << endl; 
				cout << "패 색상 >> ";
				cin >> color;
				if(color == 0 && Card::numBcard > 0)
					return;     
				else if(color == 1 && Card::numWcard > 0)
					return;    
				else 
					cout << "잘못입력하셨습니다. 다시입력해주시기 바랍니다." << endl;
			}
		}
		else{
			while(true){
				color=rand()%2;
				if(color == 0 && Card::numBcard > 0)
					return;       
				else if(color == 1 && Card::numWcard > 0)
					return;     
			}
		}
	}
	int pullCard(){ // 카드선택
		int card=X;
		while(color==0){ // 검은패 뽑기
			card=rand()%13+13;
			if(Card::mainCard[card]!=X){
				Card::numBcard--;
				Card::mainCard[card]=X;
				return card;
			}
		}
		while(color==1){ // 흰색패 뽑기
			card=rand()%13;
			if(Card::mainCard[card]!=X){
				Card::numWcard--;
				Card::mainCard[card]=X;
				return card;
			}
		}
	}
};

class Com{
	//list <int> card;
	int mycard[13];  // 배열 -> 리스트로 수정
	int showcard[13];
	int pullcard[2];// 0은 인덱스, 1은 값
	int cardNum;
	Card card;
public:
	Com(){
		cardNum = 0;
		for(int i=0; i<13; i++)
			showcard[i] = X;
	}
	void Pull(){ // 카드 뽑기
		int i;
		card.pullColor(true);
		pullcard[1] = card.pullCard();
		cardNum++;
		for(i=cardNum-2; i>=0 && mycard[i]>pullcard[1]; i--) 
			mycard[i+1] = mycard[i];   // 레코드의 오른쪽 이동
		mycard[i+1] = pullcard[1];
		pullcard[0] = i+1;
	}
	void viewMyCard(){ // 현재 컴퓨터의 패 정보(myCard)를 출력
		cout << endl << "COM : ";
		for(int i=0; i<cardNum; i++)
			cout << mycard[i] << " ";
	}
	void viewShowCard(){ // 현재 컴퓨터의 알려진 패 정보(showCard)를 출력
		cout << endl << "COM : ";
		for(int i=0; i<cardNum; i++){
			if(showcard[i] == X)
				cout << "--" << " ";
			else
				cout << showcard[i] << " ";
		}
	}
	int infoCardColor(int location){ // 위치의 색깔을 리턴해주는 함수
		return mycard[location]%2;
	}
	int firstTurn(int location, int color){ // 첫번째차례에 랜덤으로 숫자 리턴
		int randomNum;
		while(true){
			if(location == 0){
				if(color == 0)
					randomNum = ((rand()%14)/2)*2;
				else 
					randomNum = ((rand()%14)/2)*2+1;
			}
			else{
				if(color == 0)
					randomNum = ((rand()%14+12)/2)*2;
				else 
					randomNum = ((rand()%14+12)/2)*2+1;
			}
			for(int i=0; i<cardNum; i++)
				if(mycard[i] != randomNum)
					return randomNum;  
		}
	}
	bool testCard(int location, int num){ // user가 가리킨 카드가 맞는지 test
		if(mycard[location] == num){
			showcard[location] = num;
			return true;
		}
		else return false;
	}
	void openCard(int location){ // 뽑은 카드를 공개하는 함수
		if(location == X)
			showcard[pullcard[0]] = pullcard[1]; 
		else
			showcard[location] = mycard[location];
	}
	bool CheckGuessNumber(int num, int turn){ // 추측하려는 수를 내가 이미 가지고있는지 아닌지 판단(false일 경우 추측 가능)
		if(turn ==0){ // 내 턴일 경우
			for(int i=0; i<cardNum; i++)
				if(mycard[i] == num)
					return true;
		}
		else{ // 상대방 턴일 경우
			for(int i=0; i<cardNum; i++)
				if(showcard[i] == num)
					return true;
		}
		return false;
	}
	bool CheckGuessLocation(int num){ // 추측하려는 위치가 이미 공개되어있는지 아닌지 판단(false일 경우 추측 가능)
		if(showcard[num] == X)
			return false;
		else
			return true;
	}
};
class User{
	//list <int> card;
	int mycard[13];  // 배열 -> 리스트로 수정
	int showcard[13];
	int pullcard[2]; // 0은 인덱스, 1은 값
	int cardNum;
	Card card;
public:
	User(){
		cardNum = 0;
		for(int i=0; i<13; i++)
			showcard[i] = X;
	}
	void Pull(){ // 카드 뽑기
		int i;
		card.pullColor(false);
		pullcard[1] = card.pullCard();
		cardNum++;
		for(i=cardNum-2; i>=0 && mycard[i]>pullcard[1]; i--) 
			mycard[i+1] = mycard[i];   // 레코드의 오른쪽 이동
		mycard[i+1] = pullcard[1];
		pullcard[0] = i+1;
	}
	void viewMyCard(){ // 사용자의 패를 출력
		cout << endl << "User : ";
		for(int i=0; i<cardNum; i++)
			cout << mycard[i] << " ";
	}
	void viewShowCard(){
		cout << endl << "User : ";
		for(int i=0; i<cardNum; i++){
			if(showcard[i] == X)
				cout << "--" << " ";
			else
				cout << showcard[i] << " ";
		}
	}
	int infoCardColor(int location){ // 위치의 색깔을 리턴해주는 함수
		return mycard[location]%2;
	}
	bool testCard(int location, int num){ // 추측한 숫자가 맞으면 
		if(mycard[location] == num){
			showcard[location] = num;
			return true;
		}
		else return false;
	}
	void openCard(int location){ // 뽑은 카드를 공개하는 함수
		if(location == X)
			showcard[pullcard[0]] = pullcard[1]; 
		else
			showcard[location] = mycard[location];
	}
	int getGuessLocation(){ // com이 추측할 위치의 숫자를 리턴해주는 함수
		int location = X;
		while(true){
			location = rand()%cardNum;
			if(showcard[location] == X)
				return location;
		}
	}
	bool CheckGuessNumber(int num, int turn){ // 추측하려는 수를 내가 이미 가지고있는지 아닌지 판단(false일 경우 추측 가능)
		if(turn ==0){ // 내 턴일 경우
			for(int i=0; i<cardNum; i++)
				if(mycard[i] == num)
					return true;
		}
		else{ // 상대방 턴일 경우
			for(int i=0; i<cardNum; i++)
				if(showcard[i] == num)
					return true;
		}
		return false;
	}
	bool CheckGuessLocation(int num){ // 추측하려는 위치가 이미 공개되어있는지 아닌지 판단(false일 경우 추측 가능)
		if(showcard[num] == X)
			return false;
		else
			return true;
	}
};
class Game{
	Com com;
	User user;
public:
	void viewMyCard(){
		cout << endl << "# 가지고 있는 패";
		com.viewMyCard();
		user.viewMyCard();
		cout << endl << endl;
	}
	void viewShowCard(){
		cout << endl << "# 실제 보여지는 카드 패";
		com.viewShowCard();
		user.viewShowCard();
		cout << endl << endl;
	}
	void Start(){
		cout << "# com과 user가 번갈아 가며 각각 5개의 패를 가져갑니다.(검은색 : 0, 흰색 : 1)" << endl << endl;
		for(int i=0; i<5; i++){
			com.Pull();
			user.Pull();
		}
		viewMyCard();
	}
	void firstTurn(){ // 컴퓨터의 첫번째 턴
		int location = rand()%2;
		int guess=X;
		if(location == 1)
			location = 4;
		com.Pull(); 
		guess = com.firstTurn(location, user.infoCardColor(location));
		cout << "location : " << location << " guess : " << guess << endl << endl;
		if(!(user.testCard(location, guess))){
			cout << "# 틀렸습니다. 가져온 패를 공개합니다. " << endl;
			com.openCard(X);
			viewShowCard();
		}
		else{
			cout << "# 정답입니다. 맞춘 패를 공개합니다. " << endl;
			user.openCard(location);
			viewShowCard();
		}
	}
	void userTurn(){
		int location = X;
		int guess = X;
		int answer = 0;
		user.Pull();
		do{
			do{
				cout << "# 선택할 컴퓨터의 패 위치를 입력하세요 >> ";
				cin >> location;
			}while(com.CheckGuessLocation(location));
			do{
				cout << "# 예상하는 숫자를 입력하세요 >> ";
				cin >> guess;
			}while((com.CheckGuessNumber(guess,1)) || (user.CheckGuessNumber(guess,0)));
			if(!(com.testCard(location, guess))){
				cout << "# 틀렸습니다. 가져온 패를 공개합니다. " << endl;
				user.openCard(X);
				viewShowCard();
			}
			else{
				cout << "# 정답입니다. 맞춘 패를 공개합니다. " << endl;
				com.openCard(location);
				viewShowCard();
				cout << "# 한번 더 하시겠습니까? (1) yes  (0) no >>";
				cin >> answer;
			}
		}while(answer);
	}
	void comTurn(){
		int location = user.getGuessLocation();
		int guess=X;
		com.Pull(); 
		do{
			guess = rand()%23;
		}while((com.CheckGuessNumber(guess,0)) || (user.CheckGuessNumber(guess,1)));
		cout << "location : " << location << " guess : " << guess << endl << endl;
		if(!(user.testCard(location, guess))){
			cout << "# 틀렸습니다. 가져온 패를 공개합니다. " << endl;
			com.openCard(X);
			viewShowCard();
		}
		else{
			cout << "# 정답입니다. 맞춘 패를 공개합니다. " << endl;
			user.openCard(location);
			viewShowCard();
		}
	}
	void game(){
		Start();
		cout << "===COM TURN===" << endl << endl;
		firstTurn();
		while(true){
			cout << "===USER TURN===" << endl << endl;
			userTurn();
			cout << "===COM TURN===" << endl << endl;
			comTurn();
		}
	}
};

int Card::mainCard[26]={0}; 
int Card::numWcard=0;
int Card::numBcard=0;

int main(){
	Game hayoung;
	// 카드패 값 초기화
	for(int i=0; i<26; i++){
		if(i%2==0)
			Card::mainCard[i] = i*2;
		else
			Card::mainCard[i] = 2*i +1;
	}
	Card::numWcard=13;
	Card::numBcard=13;
	srand((unsigned)time(NULL));
	hayoung.game();
}