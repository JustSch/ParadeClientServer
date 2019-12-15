package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import required.Marching;

public class GreenStudentClient extends Thread {
	private int numGreen;
	private int numOrange;
	private int numSeats;
	private int threadNum;
	private boolean paradeOngoing;
	private Marching march;

	public GreenStudentClient(int numOrange,int numGreen,int numSeats, int threadNum, Marching march) {
		this.numOrange=numOrange;
		this.numGreen = numGreen;
		this.numSeats=numSeats;
		this.threadNum = threadNum;
		this.march=march;
	}

	
	public void run() {
		try {

			Socket connection = new Socket("localhost", 5000);  //opens socket
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			PrintWriter writer = new PrintWriter(connection.getOutputStream(),true);
			writer.println("green");//gives threadType to Server
			writer.println(String.valueOf(numOrange));

			writer.println(String.valueOf(numGreen));

			writer.println(String.valueOf(numSeats));
			
			writer.println("Green Student: "+threadNum);
			
			writer.println("Green Student: "+0);
			writer.println(String.valueOf(0));
			System.out.println(reader.readLine());
			greenloop:
			while(march.isParadeOngoing()) {
				for (int i =1;i<6;i++) {//iterates through all methods and calls them on the server
					if(!march.isParadeOngoing()) {writer.println("over"); break greenloop;} //Tells Server parade is over
					writer.println("Green Student: "+i);
					if(!march.isParadeOngoing()) {writer.println("over"); break greenloop;}//Tells Server parade is over
					writer.println(i);
					System.out.println(reader.readLine());
					
				}
				
			}
			writer.println("Green Student: "+6);
			writer.println(String.valueOf(6));
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
