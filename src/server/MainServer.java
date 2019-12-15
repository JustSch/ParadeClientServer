package server;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

	private static int port;
	private static String address;
	private static boolean paradeOngoing = true;

	public MainServer() {

	}

	public static void main(String[] args) {
		
		try {

			ServerSocket server = new ServerSocket(5000); //create server socket to receive connections from all clients
			

			System.out.println("Wating for Client");
			while (paradeOngoing) {
				Socket connection = server.accept();
				new ServerHelper(connection).start();
				System.out.println("new client connected");
			}

			

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
