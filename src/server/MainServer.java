package server;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

	private static int portNumber;
	//private static String address;
	private static boolean paradeOngoing = true;

	public MainServer() {

	}

	public static void main(String[] args) {
		
		if (args.length !=1) {
			portNumber=5000;
		}
		else
			portNumber=Integer.parseInt(args[0]);
		
		try {

			ServerSocket server = new ServerSocket(portNumber); //create server socket to receive connections from all clients
			

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
