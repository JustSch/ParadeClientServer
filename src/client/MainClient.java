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
		//clients will make their own sockets
		//will pass information need for threads in stream to be received on other end (during thread creation)
		//after creation then clients only need to send method name/number
		//Clients must execute concurrently too!!
		//Clients need to be synchronized? 
		//will wait using code in server?
		//cant progress until message recieved from server aka will block until it recieves input (is possible example does it)
		//while true loops for everything!!!!!
		spawnClock();
		spawnStaff();
		spawnGreenStudents();
		spawnOrangeStudents();
		
		
	}
}


