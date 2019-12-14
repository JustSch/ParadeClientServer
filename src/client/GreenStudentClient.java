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

	public GreenStudentClient(int numOrange,int numGreen,int numSeats) {
		this.numOrange=numOrange;
		this.numGreen = numGreen;
		this.numSeats=numSeats;
	}

	// spawn students
	public void run() {
		try {

			Socket connection = new Socket("localhost", 5000);
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			DataInputStream input = new DataInputStream(connection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			PrintWriter writer = new PrintWriter(output);
			writer.write("green");
			writer.write(String.valueOf(numOrange));

			writer.write(String.valueOf(numGreen));

			writer.write(String.valueOf(numSeats));
			
			
			
			for (int i =0;i<=9;i++) {
				writer.write(i);
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
