package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import required.Marching;

public class ServerHelper extends Thread {
	private boolean paradeOngoing =true;
	private Socket socket = null;
	private String threadType;
	//static int greenCount=0;
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

			//DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			//DataInputStream dis = new DataInputStream(socket.getInputStream());

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			
			threadType = reader.readLine();
			System.out.println(threadType);
			numOrange = Integer.parseInt(reader.readLine());
			numGreen = Integer.parseInt(reader.readLine());
			numSeats = Integer.parseInt(reader.readLine());
			
			Marching march = new Marching();
			march.startParade(numOrange);
			march.setParadeIsOngoing();
			System.out.println("ffffffffffffffffff");
			switch (threadType) {
			case "clock":

				
				clock(reader,writer,march);
				break;
			case "orange":
				orange(reader,writer,march);
				break;
				
			case "green":
				green(reader,writer,march);
				break;
			case "staff":
				staff(reader,writer,march);
				break;
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clock(BufferedReader reader, PrintWriter writer, Marching march) {
		System.out.println("oooooooooooooooo");
		ClockHelper helper = new ClockHelper("clock",numOrange,march,numSeats,StaffNotifier);
		System.out.println("JJJJJJJJJJJJJ");
		while(march.isParadeOngoing()) {
			try {
				String request = reader.readLine();
				System.out.println("clock requested: "+request);
				int runNumber = Integer.parseInt(reader.readLine());
				//if (runNumber==19)march.setParadeOver();
				helper.runMethods(runNumber);
				writer.println("Request: "+request+" Complete");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		//march.setParadeOver();
		
		try {
			String request = reader.readLine();
			System.out.println("clock requested: "+request);
			helper.runMethods((Integer.parseInt(reader.readLine())));
			writer.println("Request: "+request+" Complete");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void green(BufferedReader reader, PrintWriter writer, Marching march) {
		try {
			threadName=reader.readLine();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		GreenStudentHelper helper = new GreenStudentHelper(march,threadName,numSeats,numOrange);
		
		while(march.isParadeOngoing()) {
			try {
				//if(!march.isParadeOngoing())break;
				String request = reader.readLine();
				if(request == "parade over") break;
				System.out.println("Green Student requested: "+request);
				String requestNum = reader.readLine();
				if (requestNum=="parade over") break;
				helper.runningMethods(Integer.parseInt(requestNum));
				
				writer.println("Request: "+request+" Complete");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			String request = reader.readLine();
			System.out.println("Green Student requested: "+request);
			helper.runningMethods((Integer.parseInt(reader.readLine())));
			writer.println("Request: "+request+" Complete");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void orange(BufferedReader reader, PrintWriter writer, Marching march) {
		try {
			threadName = reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		OrangeStudentHelper helper = new OrangeStudentHelper(march,threadName,numSeats,numOrange);
		
		while(march.isParadeOngoing()) {
			try {
				//if(!march.isParadeOngoing())break;
				String request = reader.readLine();
				if(request == "parade over") break;
				
				System.out.println("Orange Student requested: "+request);
				//if(!march.isParadeOngoing())break;
				String requestNum = reader.readLine();
				if (requestNum == "parade over")break;
				helper.runningMethods((Integer.parseInt(requestNum)));
				//if(!march.isParadeOngoing())break;

				writer.println("Request: "+request+" Complete");
				if(!march.isParadeOngoing())break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			String request = reader.readLine();
			System.out.println("Orange Student requested: "+request);
			helper.runningMethods((Integer.parseInt(reader.readLine())));

			writer.println("Request: "+request+" Complete");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void staff(BufferedReader reader, PrintWriter writer, Marching march) {
		/*
		 * try { threadName = reader.readLine(); } catch (IOException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */
		
		
		StaffHelper helper = new StaffHelper(march,"Staff Member",StaffNotifier,numSeats);
		
		while(march.isParadeOngoing()) {
			try {
				//if(!march.isParadeOngoing())break;
				String request = reader.readLine();
				if (request == "parade over")break;
				//if(!march.isParadeOngoing())break;
				System.out.println("staff requested: "+request);
				//if(!march.isParadeOngoing())break;
				String requestNum = reader.readLine();
				if (requestNum == "parade over")break;
				helper.runningMethods((Integer.parseInt(requestNum)));
				//if(!march.isParadeOngoing())break;
				writer.println("Request: "+request+" Complete");
				//if(!march.isParadeOngoing())break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			String request = reader.readLine();
			System.out.println("staff requested: "+request);
			helper.runningMethods((Integer.parseInt(reader.readLine())));
			writer.println("Request: "+request+" Complete");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
