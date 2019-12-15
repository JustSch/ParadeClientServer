package client;

import java.io.BufferedReader;
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
	private String hostName;
	private int portNumber;
	public ClockClient(int numOrange,int numGreen,int numSeats, Marching march,String hostName, int portNumber) {
		this.numOrange = numOrange;
		this.numGreen = numOrange;
		this.numSeats = numSeats;
		this.march=march;
		this.hostName=hostName;
		this.portNumber=portNumber;
	}
	
	public void run() {
		
		try {
			
			Socket connection = new Socket(hostName, portNumber); //opens socket
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			PrintWriter writer = new PrintWriter(connection.getOutputStream(),true);
			
			writer.println("clock");//lets server thread know client type
			writer.println(String.valueOf(numOrange));

			writer.println(String.valueOf(numGreen));

			writer.println(String.valueOf(numSeats));
			
			
			for (int i =0;i<=19;i++) {	//iterates through all methods and calls them on server
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
