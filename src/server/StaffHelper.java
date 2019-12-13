package server;

import required.Marching;
import required.StaffMember;

public class StaffHelper extends StaffMember{

	public StaffHelper(Marching march, String name, Object StaffNotifier, int numSeat) {
		super(march, name, StaffNotifier, numSeat);
		// TODO Auto-generated constructor stub
	}
	
	public void runningMethods(int method) {
		switch(method) {
		case 0:
			settingName();
			break;

		case 1:
			prePuppetShow();
			break;

		case 2:
			openPuppetShow();
			break;

		case 3:
			endParade();
			break;
		}
	}

}
