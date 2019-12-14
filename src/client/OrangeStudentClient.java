package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class OrangeStudentClient extends Thread {
	private int numOrange;
	private int numSeats;
	private int numGreen;
	private int threadNum;
	private boolean paradeOngoing;
	public OrangeStudentClient(int numOrange, int numGreen, int numSeats, int threadNum, boolean paradeOngoing) {
		this.numOrange = numOrange;
		this.numGreen=numGreen;
		this.numSeats=numSeats;
		this.threadNum=threadNum;
		this.paradeOngoing=paradeOngoing;
	}

	public void run() {
		try {

			Socket connection = new Socket("localhost", 5000);
			//DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			//DataInputStream input = new DataInputStream(connection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			PrintWriter writer = new PrintWriter(connection.getOutputStream(),true);
			writer.println("orange");
			writer.println(String.valueOf(numOrange));

			writer.println(String.valueOf(numGreen));

			writer.println(String.valueOf(numSeats));
			
			writer.println("Orange Student: "+ threadNum);
			while (paradeOngoing) {
				for (int i =0;i<6;i++) {
					writer.println("Orange Student: "+i);
					writer.println(i);
					System.out.println(reader.readLine());
				}
				
			}
			writer.println("Orange Student: "+6);
			writer.println(6);
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
