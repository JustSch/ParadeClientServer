package required;
import java.util.Random;

public class Clock implements Runnable {

	public static long time = System.currentTimeMillis();
	public String clockName;
	public int total; //total amount of student groups for parade
	public Marching march;
	public static Object StaffNotifier = new Object();
	public int numSeat; //number of seats for puppet show

	public Clock() {

	}

	public Clock(String clockName,  int total, Marching march, int numSeat) { 																			
		this.clockName = clockName;
		this.total = total;
		this.march = march;
		this.numSeat = numSeat;
	}

	public final void setName(String clockNameToSet) {

		Thread.currentThread().setName(clockNameToSet); // Sets name of Clock

	}

	public void msg(String m) {
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

	public void run() {
		setName(clockName);
		Random random = new Random();
		int walkingTime = 1000 + random.nextInt(1000);// for walking around 20 min
		march.readyPuppetShow();
		march.setParadeIsOngoing();
		Thread staffMember = new Thread(new StaffMember(march, "Staff Member", StaffNotifier,numSeat));
		staffMember.start();
		msg("It is 11:00AM The Parade Has Started");

		try {
			msg("The Parade Is Starting: Please Wait In Line");
			Thread.sleep(walkingTime);//"sleep" while they line up
			try {
				releaseGroups();
				march.startParade(total);
			
			}
			catch (Exception e) {
				msg("Students were too slow to go to the parade at this time");
			}

			msg("It is 11:15AM. The First Show has Started");
			march.releasing(StaffNotifier);
			Thread.sleep(7500);  //sleep 75 min aka 1000 = 1min
			
			msg("It is 12:00PM. The Second Parade has Started");
			try {
				releaseGroups();
				march.startParade(total);
			}
			catch (Exception e) {
				msg("Students were too slow to go to the parade at this time");
			}
			Thread.sleep(4500);
			msg("It is 12:45PM. The Second Show Has Started");
			march.releasing(StaffNotifier);
			Thread.sleep(1500);
			msg("It is 1:00PM. The Third Parade has Started");
			try {
				releaseGroups();
				march.startParade(total);
			
			}
			catch (Exception e) {
				msg("Students were too slow to go to the parade at this time");
			}
			Thread.sleep(6000);
			
			msg("It is 2:00PM. The Fourth Parade has Started");
			try {
				releaseGroups();
				march.startParade(total);
			
			}
			catch (Exception e) {
				msg("Students were too slow to go to the parade at this time");
			}
			
			Thread.sleep(1500);
			msg("It is 2:15PM. The Third Show Has Started");
			
			march.releasing(StaffNotifier);
			Thread.sleep(4500);
			msg("It is 3:00PM. The Fifth Parade has Started");
			try {
				releaseGroups();
				march.startParade(total);
			
			}
			catch (Exception e) {
				msg("Students were too slow to go to the parade at this time");
			}
			Thread.sleep(4500);
			msg("It is 3:45PM. The Final Show Has Started");
			march.releasing(StaffNotifier);
			Thread.sleep(1500);
			msg("It is 4:00PM. The Final Parade has Started");
			try {
				releaseGroups();
				march.startParade(total);
			
			}
			catch (Exception e) {
				msg("Students were too slow to go to the parade at this time");
			}

			endParade();
			msg("The Parade has ended. You Don't have to go home but, you can't stay here");
		} catch (InterruptedException e) {
			System.out.println("Error: The Clock is Broken. Please Call The Technictian!!");
		}
		
	}
	public void endParade() {  //used to notify all objects used after parade has ended for the day
		march.setParadeOver();
		try {
			releaseGroups();
			march.releasing(StaffNotifier);	
		}
		catch (Exception e) {
			msg("Students were too slow to exit the parade");
		}
		
	}

	public void releaseGroups() {   //releases students into parade
		for (int i = 0; i < total; i++) {
			Marching.releaseParadeGroups();
		}
	}

}
