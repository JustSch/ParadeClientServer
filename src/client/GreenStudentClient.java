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
	private boolean paradeOngoing;

	public GreenStudentClient(int numOrange,int numGreen,int numSeats, int threadNum, boolean paradeOngoing) {
		this.numOrange=numOrange;
		this.numGreen = numGreen;
		this.numSeats=numSeats;
		this.threadNum = threadNum;
		this.paradeOngoing=paradeOngoing;
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
			
			writer.println("Green Student: "+0);
			writer.println(String.valueOf(0));
			System.out.println(reader.readLine());
			while(paradeOngoing) {
				for (int i =1;i<6;i++) {
					if(!paradeOngoing) {writer.println("parade over"); break;}//send 55 to break in server!!
					writer.println("Green Student: "+i);
					if(!paradeOngoing) {writer.println("parade over"); break;}
					writer.println(i);
					//if(!paradeOngoing)break;
					System.out.println(reader.readLine());
					//if(!paradeOngoing)break;
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
