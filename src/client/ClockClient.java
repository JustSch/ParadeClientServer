package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import required.Marching;

public class ClockClient extends Thread {
	private int numOrange;
	private int numGreen;
	private int numSeats;
	private boolean paradeOngoing;
	private Marching march;
	public ClockClient(int numOrange,int numGreen,int numSeats, Marching march) {
		this.numOrange = numOrange;
		this.numGreen = numOrange;
		this.numSeats = numSeats;
		this.march=march;
	}
	
	public void run() {
		// connect to server
		// create input and output streams
		// run methods
		try {
			
			Socket connection = new Socket("localhost", 5000);
			
			new StaffClient(numOrange,numGreen,numSeats,march).start();
			for (int i=0; i< numGreen;i++) {
				new GreenStudentClient(numOrange,numGreen,numSeats,i,march).start();
			}
			
			for (int i=0; i< numOrange;i++) {
				new OrangeStudentClient(numOrange,numGreen,numSeats,i,march).start();
			}
			//DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			//DataInputStream input = new DataInputStream(connection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			PrintWriter writer = new PrintWriter(connection.getOutputStream(),true);
			//march.setParadeIsOngoing();
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
			march.setParadeOver();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
