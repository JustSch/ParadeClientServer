package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
		// new ClockHelper();
		try {

			ServerSocket server = new ServerSocket(5000);
			// InetAddress myAddress = InetAddress.getLocalHost();

			System.out.println("Wating for Client");
			while (paradeOngoing) {
				Socket connection = server.accept();
				new ServerHelper(connection).start();
				System.out.println("new client connected");
			}

			// DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
			// DataInputStream dis = new DataInputStream(connection.getInputStream());

			// spawn helper then pass streams into helper

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
