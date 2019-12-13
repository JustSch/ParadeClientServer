package server;
import required.Marching;
import required.OrangeStudent;
public class OrangeStudentHelper extends OrangeStudent{

	public OrangeStudentHelper(Marching march, String name, int numSeat, int totalParadeGroups) {
		super(march, name, numSeat, totalParadeGroups);
		// TODO Auto-generated constructor stub
	}
	
	public void runningMethods(int methods) {
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
