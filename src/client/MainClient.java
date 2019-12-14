package client;

import required.Marching;

public class MainClient {
	
	
	public static boolean paradeOngoing =true;
	public static void main (String [] args) {
		//clients will make their own sockets
		//will pass information need for threads in stream to be received on other end (during thread creation)
		//after creation then clients only need to send method name/number
		//Clients must execute concurrently too!!
		//Clients need to be synchronized? 
		//will wait using code in server?
		//cant progress until message recieved from server aka will block until it recieves input (is possible example does it)
		//while true loops for everything!!!!!
		int numGreen = 14; //default green student
		int numOrange = 7; //default orange students
		int numSeat = 6; //default tent capacity
		//boolean paradeOngoing=true;
		Marching march = new Marching();
		ClockClient clockClient = new ClockClient(numOrange, numGreen, numSeat,march);
		clockClient.start();
		
		new StaffClient(numOrange,numGreen,numSeat,march).start();
		for (int i=0; i< numGreen;i++) {
			new GreenStudentClient(numOrange,numGreen,numSeat,i,march).start();
		}
		
		for (int i=0; i< numOrange;i++) {
			new OrangeStudentClient(numOrange,numGreen,numSeat,i,march).start();
		}
		
		
		
	}
}


