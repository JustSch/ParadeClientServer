package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClockClient extends Thread {
	private int numOrange;
	private int numGreen;
	private int numSeats;
	
	public ClockClient(int numOrange,int numGreen,int numSeats) {
		this.numOrange = numOrange;
		this.numGreen = numOrange;
		this.numSeats = numSeats;
	}
	
	public void run() {
		// connect to server
		// create input and output streams
		// run methods
		try {

			Socket connection = new Socket("localhost", 5000);
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			DataInputStream input = new DataInputStream(connection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			PrintWriter writer = new PrintWriter(output);
			
			//System.out.println(reader.readLine());
			writer.write("clock");
			writer.write(String.valueOf(numOrange));

			writer.write(String.valueOf(numGreen));

			writer.write(String.valueOf(numSeats));
			//System.out.println(input.readUTF());
			//output.writeUTF("Clock Client Created");
			//System.out.println(input.readUTF());
			
			for (int i =0;i<=19;i++) {
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
