package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import required.Marching;

public class ServerHelper extends Thread {
	private boolean paradeOngoing = true;
	private Socket socket = null;
	private String threadType;
	private int numOrange;
	private int numGreen;
	private int numSeats;
	private String threadName;
	public static Object StaffNotifier = new Object();

	public ServerHelper(Socket socket) {
		super("ServerHelper");
		this.socket = socket;

	}

	@Override
	public void run() {
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			threadType = reader.readLine();
			System.out.println(threadType);  //displays thread type of client that has connected
			numOrange = Integer.parseInt(reader.readLine());
			numGreen = Integer.parseInt(reader.readLine());
			numSeats = Integer.parseInt(reader.readLine());

			Marching march = new Marching();
			march.startParade(numOrange);
			march.setParadeIsOngoing();
			
			switch (threadType) {  //switches threadtype to methods that it needs to use
			case "clock":

				clock(reader, writer, march);
				break;
			case "orange":
				orange(reader, writer, march);
				break;

			case "green":
				green(reader, writer, march);
				break;
			case "staff":
				staff(reader, writer, march);
				break;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void clock(BufferedReader reader, PrintWriter writer, Marching march) {
	
		ClockHelper helper = new ClockHelper("clock", numOrange, march, numSeats, StaffNotifier);
		
		while (march.isParadeOngoing()) {
			try {
				String request = reader.readLine();
				System.out.println("clock requested: " + request);  //Receives and displays request from client
				int runNumber = Integer.parseInt(reader.readLine());
				
				helper.runMethods(runNumber);  //runs request using helper
				writer.println("Request: " + request + " Complete");
				if (runNumber == 19)
					break;

			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

		}
		

		try {

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void green(BufferedReader reader, PrintWriter writer, Marching march) {
		try {
			threadName = reader.readLine();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		GreenStudentHelper helper = new GreenStudentHelper(march, threadName, numSeats, numOrange);

		while (march.isParadeOngoing()) {
			try {
				String request = reader.readLine();
				if (request.equals("over") || request.equals(null))  //allows loop to break when parade is over
					break;
				System.out.println("Green Student requested: " + request);
				String requestNum = reader.readLine();
				if (request.equals("over") || request.equals(null))  //allows loop to break when parade is over
					break;
				helper.runningMethods(Integer.parseInt(requestNum));

				writer.println("Request: " + request + " Complete");

			} catch (Exception e) {
				break;
			}
		}
		try {
			String request = reader.readLine();
			if (request.equals(null))//skips if it can't receive request
				;
			else
				System.out.println("Green Student requested: " + request);
			helper.runningMethods((Integer.parseInt(reader.readLine())));
			writer.println("Request: " + request + " Complete");
			socket.close();
		} catch (Exception e) {
			
			try {
				socket.close();
			} catch (IOException e1) {
				
			}
		}
	}

	public void orange(BufferedReader reader, PrintWriter writer, Marching march) {
		try {
			threadName = reader.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		OrangeStudentHelper helper = new OrangeStudentHelper(march, threadName, numSeats, numOrange);

		while (march.isParadeOngoing()) {
			try {
				String request = reader.readLine();//reads request
				if (request.equals("over"))  //breaks when parade is over
					break;

				System.out.println("Orange Student requested: " + request);
				
				String requestNum = reader.readLine();
				
				if (requestNum.equals("over"))
					break;
				helper.runningMethods((Integer.parseInt(requestNum)));
				

				writer.println("Request: " + request + " Complete");
			
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		try {
			String request = reader.readLine();
			System.out.println("Orange Student requested: " + request);
			String requestNum = reader.readLine();

			helper.runningMethods((Integer.parseInt(requestNum)));

			writer.println("Request: " + request + " Complete");
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void staff(BufferedReader reader, PrintWriter writer, Marching march) {
		

		StaffHelper helper = new StaffHelper(march, "Staff Member", StaffNotifier, numSeats);

		while (march.isParadeOngoing()) {
			try {
				String request = reader.readLine();
				if (request.equals("over") || request.equals(null))  //breaks loop when parade is over
					break;
				System.out.println("staff requested: " + request);
				
				String requestNum = reader.readLine();
				if (request.equals("over") || request.equals(null))
					break;
				helper.runningMethods((Integer.parseInt(requestNum)));
				
				writer.println("Request: " + request + " Complete");
				
			} catch (Exception e) {
				
				break;
			}
		}

		try {
			String request = reader.readLine();
			if (request.equals(null))
				;
			else
				System.out.println("staff requested: " + request);
			helper.runningMethods((Integer.parseInt(reader.readLine())));
			writer.println("Request: " + request + " Complete");
			socket.close();

		} catch (Exception e) {
			
			try {
				socket.close();
			} catch (IOException e1) {
				
			}
		}
	}

}
