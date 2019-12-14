package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GreenStudentClient extends Thread {
	private int numGreen;
	private int numOrange;
	private int numSeats;
	private int threadNum;

	public GreenStudentClient(int numOrange,int numGreen,int numSeats, int threadNum) {
		this.numOrange=numOrange;
		this.numGreen = numGreen;
		this.numSeats=numSeats;
		this.threadNum = threadNum;
	}

	// spawn students
	public void run() {
		try {

			Socket connection = new Socket("localhost", 5000);
			//DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			//DataInputStream input = new DataInputStream(connection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			PrintWriter writer = new PrintWriter(connection.getOutputStream(),true);
			writer.println("green");
			writer.println(String.valueOf(numOrange));

			writer.println(String.valueOf(numGreen));

			writer.println(String.valueOf(numSeats));
			
			writer.println("Green Student: "+threadNum);
			
			for (int i =0;i<9;i++) {
				writer.println(i);
				System.out.println(reader.readLine());
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
