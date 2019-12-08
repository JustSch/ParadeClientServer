package required;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Parade extends Object {

	public static void main(String[] args) {

		int numGreen = 14; //default green student
		int numOrange = 7; //default orange students
		int numSeat = 6; //default tent capacity
		
		try {
			numGreen = Integer.valueOf(args[0]); //used if values given via command line
			numOrange = Integer.valueOf(args[1]);
			numSeat = Integer.valueOf(args[2]);
		}

		catch (Exception e) {

		}
		ArrayList<Thread> orangeList = new ArrayList<Thread>();//used for creating orangeStudents
		ArrayList<Thread> greenList = new ArrayList<Thread>();//used for creating greenStudents

		Marching march = new Marching();
		march.startParade(numOrange); //Initializes objects in parade vector
		

		Thread clockThread = new Thread(new Clock("Clock", numOrange, march, numSeat)); 

		clockThread.start(); //creates and starts clockThread

		for (int i = 0; i < numOrange; i++) { // Creates Each Threads With Array Used
			Thread orangeStudent = new Thread(new OrangeStudent(march, "orange " + i, numSeat, numOrange));
			orangeList.add(orangeStudent);
		}

		for (Thread orange : orangeList)
			orange.start(); // starts each thread in the arrayList
	

		for (int j = 0; j < numGreen; j++) { // Creates Each Threads With Array Used
			Thread greenStudent = new Thread(new GreenStudent(march, "green " + j, numSeat, numOrange));
			greenList.add(greenStudent);
		}

		for (Thread green : greenList)
			green.start(); // starts each thread in the arrayList

	}

	

}
