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
	int numGreen = 0;

	public GreenStudentClient(int numGreen) {
		this.numGreen = numGreen;
	}

	// spawn students
	public void run() {
		try {

			Socket connection = new Socket("localhost", 5000);
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			DataInputStream input = new DataInputStream(connection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			PrintWriter writer = new PrintWriter(output);
			System.out.println(reader.readLine());
			writer.write("Clock Client Created");
			System.out.println(reader.readLine());
			
			for (int i =0;i<=19;i++) {
				writer.write(i);
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
