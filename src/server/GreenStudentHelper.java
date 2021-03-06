package server;
import required.GreenStudent;
import required.Marching;
public class GreenStudentHelper extends GreenStudent{

	public GreenStudentHelper(Marching march, String name, int numSeat, int totalParadeGroups) {
		super(march, name, numSeat, totalParadeGroups);
		
	}
	
	public void runningMethods(int methods) { //all methods for green students
		switch (methods) {
		case 0:
			getReadyToWait();
			break;

		case 1:
			beginWaiting();
			break;

		case 2:
			marchInParade();
			break;

		case 3:
			snackBreak();
			break;

		case 4:
			lineUpForPuppetShow();
			break;

		case 5:
			watchPuppetShow();
			break;

		case 6:
			leaving();
			break;
		}
	}

}
