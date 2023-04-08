package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
	
	public static LinkedList<ClientHandler> onlineUsers=new LinkedList<>();
	
	public static void main(String[] args) {
		int port= 9000;
		ServerSocket serverSocket=null;
		Socket communicationSocket=null;
		
		
			try {
				serverSocket=new ServerSocket(port);
				System.out.println("Ceka na konekciju");
			while(true) {
				
				communicationSocket=serverSocket.accept();
				System.out.println("Doslo je do konekcije");
				
				ClientHandler client=new ClientHandler(communicationSocket);
				
				onlineUsers.add(client);
				
				client.start();
				
			}
			} catch (IOException e) {
				System.out.println("Greska prilikom pokretanja servera");
			}
			
	}
}
