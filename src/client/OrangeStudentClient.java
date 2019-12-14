package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class OrangeStudentClient extends Thread {
	private int numOrange;
	private int numSeats;
	private int numGreen;
	public OrangeStudentClient(int numOrange, int numGreen, int numSeats) {
		this.numOrange = numOrange;
		this.numGreen=numGreen;
		this.numSeats=numSeats;
	}

	public void run() {
		try {

			Socket connection = new Socket("localhost", 5000);
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			DataInputStream input = new DataInputStream(connection.getInputStream());

			System.out.println(input.readUTF());
			output.writeUTF("Clock Client Created");
			System.out.println(input.readUTF());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
