package server;

import required.Clock;
import required.Marching;

public class ClockHelper extends Clock{
	private String string;
	private int numOrange;
	private int numSeats;
	private Marching march;
	public Object StaffNotifier = new Object();
	public ClockHelper(String string, int numOrange, Marching march, int numSeats,Object StaffNotifier) {
		
		super(string,numOrange, march, numSeats,StaffNotifier);
		
	}

	
	public void runMethods(int method) {  //all methods for the clock
		
	
		switch(method)
		{
		case 0:
			prep();
			break;

		case 1:
			startFirstParade();
			break;

		case 2:
			startPuppetShow(0);
			break;

		case 3:
			theShowsAndParadeAreHappening(0);
			break;

		case 4:
			startAnotherParade(1);
			break;

		case 5:
			theShowsAndParadeAreHappening(1);
			break;

		case 6:
			startPuppetShow(1);
			break;

		case 7:
			theShowsAndParadeAreHappening(2);
			break;

		case 8:
			startAnotherParade(2);
			break;

		case 9:
			theShowsAndParadeAreHappening(3);
			break;

		case 10:
			startAnotherParade(3);
			break;

		case 11:
			theShowsAndParadeAreHappening(4);
			break;

		case 12:
			startPuppetShow(2);
			break;

		case 13:
			theShowsAndParadeAreHappening(5);
			break;

		case 14:
			startAnotherParade(4);
			break;

		case 15:
			theShowsAndParadeAreHappening(6);
			break;

		case 16:
			startPuppetShow(3);
			break;

		case 17:
			theShowsAndParadeAreHappening(7);
			break;

		case 18:
			startAnotherParade(5);
			break;

		case 19:
			endParade();
			break;


		}
	}
	
	
	
}
