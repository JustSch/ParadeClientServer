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
			
			System.out.println(reader.readLine());
			writer.write("Clock Client");
			//System.out.println(input.readUTF());
			//output.writeUTF("Clock Client Created");
			//System.out.println(input.readUTF());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
