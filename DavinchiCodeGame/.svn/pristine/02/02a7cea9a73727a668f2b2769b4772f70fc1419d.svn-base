import java.net.*;
import java.util.*;
import java.io.*;


public class Server {
	
	private ServerSocket server;
	private printMsg pMsg = new printMsg(); //BManager bMan
	private Random r = new Random();
	
	public Server(){}
	
	void startServer(){
		try{
			server = new ServerSocket(7777);
			System.out.println("서버 소켓이 생성되었습니다");
			while(true){
				Socket socket = server.accept();
				Davinchi_Thread dt = new Davinchi_Thread(socket); // Omok_Thread ot
				dt.start();
				
				pMsg.add(dt);
				System.out.println("현재 접속자 수 : "+pMsg.size());
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.startServer();
	}
	
	class Davinchi_Thread extends Thread{
		private int roomNumber = -1;
		private String userName = null;
		private Socket socket;
		
		private boolean ready = false;
		private BufferedReader reader;
		private PrintWriter writer;
		
		Davinchi_Thread(Socket socket){
			this.socket = socket;
		}
		Socket getSocket(){
			return socket;
		}
		int getRoomNumber(){
			return roomNumber;
		}
		String getUserName(){
			return userName;
		}
		boolean isReady(){
			return ready;
		}
		public void run(){
			try{
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
				
				String msg;
				
				while((msg=reader.readLine()) != null){
					if(msg.startsWith("[NAME]"))
						userName = msg.substring(6);
					else if(msg.startsWith("[ROOM]")){
						int roomNum = Integer.parseInt(msg.substring(6));
						if(!pMsg.isFull(roomNum)){
							if(roomNumber != 1)
								pMsg.sendToOthers(this,"[EXIT]"+userName);
							roomNumber = roomNum;
							writer.println(msg);
							writer.println(pMsg.getNamesInRoom(roomNumber));
							pMsg.sendToOthers(this, "[ENTER]"+userName);
						
						}
						else
							writer.println("[FULL]");
					}
					else if(roomNumber>=1 && msg.startsWith("[STONE]"))
						pMsg.sendToOthers(this.msg);
					else if(msg.startsWith("[MSG]"))
						pMsg.sendToRoom(roomNumber, "["+userName+"]"+msg.substring(5));
					else if(msg.startsWith("[START]")){
						ready = true;
						if(pMsg.isReady(roomNumber)){
							int a = r.nextInt(2);
							if(a==0){
								writer.println("[COLOR]BLACK");//나중에 먼저 시작할 사람으로 정해야할듯!
								pMsg.sendToOthers(this,"[COLOR]WHITE");
							}else{
								writer.println("[COLOR]WHITE");
								pMsg.sendToOthers(this,"[COLOR]BLACK");
							}
						}
					}
					else if(msg.startsWith("[STOPGAME]"))
						ready = false;
					else if(msg.startsWith("[DROPGAME]")){
						ready = false;
						pMsg.sendToOthers(this,"[DROPGAME]");
					}
					else if(msg.startsWith("[WIN]")){
						ready = false;
						writer.println("[WIN]");
						pMsg.sendToOthers(this,"[LOSE]");
					}
					
				}
							
			}catch(Exception e){}
			finally{
				try{
					pMsg.remove(this);
					if(reader != null) reader.close();
					if(writer != null) writer.close();
					if(socket != null) socket.close();
					reader = null;
					writer = null;
					socket = null;
					System.out.println(userName + "님이 접속을 끊었습니다.");
					System.out.println("접속자 수 :"+pMsg.size());
					pMsg.sendToRoom(roomNumber, "[DISCONNECT]"+userName);
				}catch(Exception e){}
			}
		}
	}
	
	class printMsg extends Vector{
		printMsg(){}
		void add(Davinchi_Thread dt){
			super.add(dt);
		}
		void remove(Davinchi_Thread dt){
			super.remove(dt);
		}
		Davinchi_Thread getDT(int i){
			return (Davinchi_Thread)elementAt(i);
		}
		Socket getSocket(int i){
			return getDT(i).getSocket();
		}
		
		void sendTo(int i, String msg){
			try{
				PrintWriter pw = new PrintWriter(getSocket(i), getOutputStream(), true);
				pw.println(msg);
			}catch(Exception e){}
		}
		
		int getRoomNumber(int i){
			return getDT(i).getRoomNumber();
		}
		synchronized boolean isFull(int roomNum){
			if(roomNum==0)
				return false;
			int count = 0;
			for(int i=0; i<size();i++)
				if(roomNum == getRoomNumber(i))
					count ++;
			if(count>=2) return true;
			return false;
		}
		
		void sendToRoom(int roomNum, String msg){
			for(int i=0; i<size(); i++)
				if(roomNum == getRoomNumber(i))
					sendTo(i, msg);
		}
		
		void sendToOthers(Davinchi_Thread dt, String msg){
			for(int i=0; i<size(); i++)
				if(getRoomNumber(i)==dt.getRoomNumber() && getDT(i) !=dt)
					sendTo(i, msg);
		}
		
		synchronized boolean isReady(int roomNum){
			int count =0;
			for(int i=0; i<size(); i++)
				if(roomNum == getRoomNumber(i) && getDT(i).isReady())
					count++;
			if(count==2) return true;
			return false;
		}
		
		String getNamesInRoom(int roomNum){
			StringBuffer sb = new StringBuffer("[PLAYERS]");
			for(int i=0; i<size(); i++)
				if(roomNum == getRoomNumber(i))
					sb.append(getDT(i).getUserName()+"\t");
			return sb.toString();
		}
		
	}
}
