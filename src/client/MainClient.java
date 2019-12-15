package client;

import java.util.ArrayList;

import required.GreenStudent;
import required.Marching;
import required.OrangeStudent;

public class MainClient {
	
	
	public static boolean paradeOngoing =true;
	public static void main (String [] args) {
	
		int numGreen = 14; //default green student
		int numOrange = 7; //default orange students
		int numSeat = 6; //default tent capacity
	
		Marching march = new Marching();
		march.setParadeIsOngoing();
		ClockClient clockClient = new ClockClient(numOrange, numGreen, numSeat,march);
		clockClient.start();
		
		new StaffClient(numOrange,numGreen,numSeat,march).start();
		
		ArrayList<Thread> orangeList = new ArrayList<Thread>();//used for creating orangeStudents
		ArrayList<Thread> greenList = new ArrayList<Thread>();//used for creating greenStudents
		for (int i = 0; i < numOrange; i++) { // Creates Each Threads With Array Used
			Thread orangeStudent = new Thread(new OrangeStudentClient(numOrange,numGreen,numSeat,i,march));
			orangeList.add(orangeStudent);
		}

		for (Thread orange : orangeList)
			orange.start(); // starts each thread in the arrayList
	

		for (int j = 0; j < numGreen; j++) { // Creates Each Threads With Array Used
			Thread greenStudent = new Thread(new GreenStudentClient(numOrange,numGreen,numSeat,j,march));
			greenList.add(greenStudent);
		}

		for (Thread green : greenList)
			green.start(); // starts each thread in the arrayList

		
		
		
		
		
	}
}


