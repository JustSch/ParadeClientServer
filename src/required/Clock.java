package required;
import java.util.Random;

public class Clock implements Runnable {

	public static long time = System.currentTimeMillis();
	public String clockName;
	public int total; //total amount of student groups for parade
	public Marching march;
	public static Object StaffNotifier = new Object();
	public int numSeat; //number of seats for puppet show
	public int walkingTime;
	private boolean local;
	public String [] paradeMessages = {"It is 11:00AM The Parade Has Started",
			"It is 12:00PM. The Second Parade has Started",
			"It is 1:00PM. The Third Parade has Started",
			"It is 2:00PM. The Fourth Parade has Started",
			"It is 3:00PM. The Fifth Parade has Started",
			"It is 3:45PM. The Final Show Has Started"};
	public String [] puppetShowMessages = {"It is 11:15AM. The First Show has Started",
			"It is 12:45PM. The Second Show Has Started",
			"It is 2:15PM. The Third Show Has Started",
			"It is 3:45PM. The Final Show Has Started"
					};
	public int [] sleepTimes = {7500,
			4500,
			1500,
			6000,
			1500,
			4500,
			4500,
			1500
			
	};

	public Clock() {

	}

	public Clock(String clockName,  int total, Marching march, int numSeat) { 																			
		this.clockName = clockName;
		this.total = total;
		this.march = march;
		this.numSeat = numSeat;
		local=true;
	}
	public Clock(String clockName,  int total, Marching march, int numSeat,Object StaffNotifier) {
		this.clockName = clockName;
		this.total = total;
		this.march = march;
		this.numSeat = numSeat;
		this.StaffNotifier = StaffNotifier;
		local=false;
	}
	public final void setName(String clockNameToSet) {

		Thread.currentThread().setName(clockNameToSet); // Sets name of Clock

	}

	public void msg(String m) {
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

	public void run() {
		prep();
		welcomeStaffMember();
		startFirstParade();
		startPuppetShow(0);
		theShowsAndParadeAreHappening(0);
		startAnotherParade(1);
		theShowsAndParadeAreHappening(1);
		startPuppetShow(1);
		theShowsAndParadeAreHappening(2);
		startAnotherParade(2);
		theShowsAndParadeAreHappening(3);
		startAnotherParade(3);
		theShowsAndParadeAreHappening(4);
		startPuppetShow(2);
		theShowsAndParadeAreHappening(5);
		startAnotherParade(4);
		theShowsAndParadeAreHappening(6);
		startPuppetShow(3);
		theShowsAndParadeAreHappening(7);
		startAnotherParade(5);
		endParade();

	}
	
	public void prep() {
		setName(clockName);
		Random random = new Random();
		walkingTime = 1000 + random.nextInt(1000);// for walking around 20 min
		march.readyPuppetShow();
		march.setParadeIsOngoing();
		
	}
	
	public void welcomeStaffMember() {
		
		Thread staffMember = new Thread(new StaffMember(march, "Staff Member", StaffNotifier,numSeat));
		staffMember.start();
	}
	
	public void startFirstParade() {
		msg(paradeMessages[0]);
		try {
			msg("The Parade Is Starting: Please Wait In Line");
			Thread.sleep(walkingTime);//"sleep" while they line up (first time only)
			releaseGroups();
			march.startParade(total);
		}
		catch (Exception e) {
			msg("Students were too slow to go to the parade at this time");
		}
	}
	
	public void startAnotherParade(int paradeNumber) {
		msg(paradeMessages[paradeNumber]);
		try {
			releaseGroups();
			march.startParade(total);
		}
		catch (Exception e) {
			msg("Students were too slow to go to the parade at this time");
		}
	}
	
	public void startPuppetShow(int showNumber) {
		msg(puppetShowMessages[showNumber]);
		march.releasing(StaffNotifier);  
	}
	
	public void theShowsAndParadeAreHappening(int sleepNumber) {
		try {
			Thread.sleep(sleepTimes[sleepNumber]);
		} catch (InterruptedException e) {
			msg("Error: The Clock is Broken. Please Call The Technictian!!");
		}
	}
	public void endParade() {  //used to notify all objects used after parade has ended for the day
		if (local)march.setParadeOver();//allows to still function if just run as project 1
		
		try {
			releaseGroups();
			march.releasing(StaffNotifier);	
		}
		catch (Exception e) {
			msg("Students were too slow to exit the parade");
		}
		msg("The Parade has ended. You Don't have to go home but, you can't stay here");
	}

	public void releaseGroups() {   //releases students into parade
		for (int i = 0; i < total; i++) {
			Marching.releaseParadeGroups();
		}
	}

}
