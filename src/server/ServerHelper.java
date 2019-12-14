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
	private boolean paradeOngoing;
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

			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(dis));
			
			PrintWriter writer = new PrintWriter(dos);
			
			threadType = reader.readLine();
			numOrange = Integer.parseInt(reader.readLine());
			numGreen = Integer.parseInt(reader.readLine());
			numSeats = Integer.parseInt(reader.readLine());

			Marching march = new Marching();
			march.startParade(numOrange);
			switch (threadType) {
			case "clock":
				clock(reader,dis,dos,writer,march);
				break;
			case "orange":
				orange(reader,dis,dos,writer,march);
				break;
				
			case "green":
				green(reader,dis,dos,writer,march);
				break;
			case "staff":
				staff(reader,dis,dos,writer,march);
				break;
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clock(BufferedReader reader, DataInputStream dis, DataOutputStream dos, PrintWriter writer, Marching march) {
		
		ClockHelper helper = new ClockHelper("clock",numOrange,march,numSeats,StaffNotifier);
		
		while(paradeOngoing) {
			try {
				System.out.println("clock requested: "+reader.readLine());
				helper.runMethods((Integer.parseInt(reader.readLine())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public void green(BufferedReader reader, DataInputStream dis, DataOutputStream dos, PrintWriter writer, Marching march) {
		try {
			threadName=reader.readLine();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		GreenStudentHelper helper = new GreenStudentHelper(march,threadName,numSeats,numOrange);
		
		while(paradeOngoing) {
			try {
				System.out.println("Green Student requested: "+reader.readLine());
				helper.runningMethods((Integer.parseInt(reader.readLine())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void orange(BufferedReader reader, DataInputStream dis, DataOutputStream dos, PrintWriter writer, Marching march) {
		try {
			threadName = reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		OrangeStudentHelper helper = new OrangeStudentHelper(march,threadName,numSeats,numOrange);
		
		while(paradeOngoing) {
			try {
				System.out.println("Orange Student requested: "+reader.readLine());
				helper.runningMethods((Integer.parseInt(reader.readLine())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void staff(BufferedReader reader, DataInputStream dis, DataOutputStream dos, PrintWriter writer, Marching march) {
		try {
			threadName = reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		StaffHelper helper = new StaffHelper(march,threadName,StaffNotifier,numSeats);
		
		while(paradeOngoing) {
			try {
				System.out.println("staff requested: "+reader.readLine());
				helper.runningMethods((Integer.parseInt(reader.readLine())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
