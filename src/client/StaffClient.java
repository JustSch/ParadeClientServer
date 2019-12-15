package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import required.Marching;

public class StaffClient extends Thread {
	private int numSeats;
	private int numOrange;
	private int numGreen;
	private boolean paradeOngoing;
	private Marching march;
	private String hostName;
	private int portNumber;

	public StaffClient(int numOrange, int numGreen, int numSeats, Marching march, String hostName, int portNumber) {
		this.numSeats = numSeats;
		this.numOrange = numOrange;
		this.numGreen = numGreen;
		this.march=march;
		this.hostName=hostName;
		this.portNumber=portNumber;
	}

	public void run() {
		try {

			Socket connection = new Socket(hostName, portNumber);
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);
			writer.println("staff");  //send threadtype to server
			writer.println(String.valueOf(numOrange));

			writer.println(String.valueOf(numGreen));

			writer.println(String.valueOf(numSeats));
			writer.println("Staff Member: "+0);
			writer.println(String.valueOf(0));
			System.out.println(reader.readLine());
			staffloop:
			while (march.isParadeOngoing()) {//iterates through all methods and runs them on the server 
				for (int i = 1; i < 3; i++) {
					if(!march.isParadeOngoing()) {writer.println("over"); break staffloop;}
					writer.println("Staff Member: "+i);
					if(!march.isParadeOngoing()) {writer.println("over"); break staffloop;}
					writer.println(i);
					//if(!paradeOngoing)break;
					System.out.println(reader.readLine());
					//if(!paradeOngoing)break;
				}
			}
			writer.println("Staff Member: "+3);
			writer.println(String.valueOf(3));
			System.out.println(reader.readLine());
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
