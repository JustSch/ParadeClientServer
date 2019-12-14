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
	private boolean paradeOngoing;
	public ClockClient(int numOrange,int numGreen,int numSeats, boolean paradeOngoing) {
		this.numOrange = numOrange;
		this.numGreen = numOrange;
		this.numSeats = numSeats;
		this.paradeOngoing=paradeOngoing;
	}
	
	public void run() {
		// connect to server
		// create input and output streams
		// run methods
		try {

			Socket connection = new Socket("localhost", 5000);
			//DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			//DataInputStream input = new DataInputStream(connection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			PrintWriter writer = new PrintWriter(connection.getOutputStream(),true);
			
			//System.out.println(reader.readLine());
			writer.println("clock");
			writer.println(String.valueOf(numOrange));

			writer.println(String.valueOf(numGreen));

			writer.println(String.valueOf(numSeats));
			
			
			for (int i =0;i<=19;i++) {
				writer.println("Clock: "+i);
				writer.println(i);
				System.out.println(reader.readLine());
			}
			paradeOngoing=false;

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
