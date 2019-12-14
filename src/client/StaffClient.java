package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class StaffClient extends Thread {
	private int numSeats;
	private int numOrange;
	private int numGreen;
	private boolean paradeOngoing;

	public StaffClient(int numOrange, int numGreen, int numSeats, boolean paradeOngoing) {
		this.numSeats = numSeats;
		this.numOrange = numOrange;
		this.numGreen = numGreen;
		this.paradeOngoing=paradeOngoing;
	}

	public void run() {
		try {

			Socket connection = new Socket("localhost", 5000);
			//DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			//DataInputStream input = new DataInputStream(connection.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);
			writer.println("staff");
			writer.println(String.valueOf(numOrange));

			writer.println(String.valueOf(numGreen));

			writer.println(String.valueOf(numSeats));
			writer.println("Staff Member: "+0);
			writer.println(3);
			System.out.println(reader.readLine());
			while (paradeOngoing) {
				for (int i = 1; i < 3; i++) {
					writer.println("Staff Member: "+i);
					writer.println(i);
					System.out.println(reader.readLine());
				}
			}
			writer.println("Staff Member: "+3);
			writer.println(3);
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
