package client;

public class MainClient {
	public int numGreen = 14; //default green student
	public int numOrange = 7; //default orange students
	public int numSeat = 6; //default tent capacity
	
	
	public void spawnGreenStudents() {
		for (int i=0; i< numGreen;i++) {
			new GreenStudentClient(numGreen);
		}
	}
	public void spawnOrangeStudents() {
		
	}
	
	public void spawnClock() {
		
	}
	
	public void spawnStaff() {
		
	}
	public void main (String [] args) {
		spawnClock();
		spawnStaff();
		spawnGreenStudents();
		spawnOrangeStudents();
		
		
	}
}


