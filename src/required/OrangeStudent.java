package required;
import java.util.Random;

public class OrangeStudent implements Runnable {
	private Marching march = null;
	public String name;
	public static long time = System.currentTimeMillis();
	public int numSeat;
	public int mySeat;
	public int totalParadeGroups;
	public int parades;//for parades watched
	public int puppetShow; //for puppet shows watched
	public int walkingTime;

	public OrangeStudent(Marching march, String name, int numSeat, int totalParadeGroups) {
		this.march = march;
		this.name = name;
		this.numSeat = numSeat;
		this.totalParadeGroups = totalParadeGroups;

	}

	@Override
	public void run() {
		getReadyToWait();
		while (march.isParadeOngoing()) {
			beginWaiting();
			if(!march.isParadeOngoing()) break;  //break if parade over
			marchInParade();
			if(!march.isParadeOngoing()) break;
			snackBreak();
			if(!march.isParadeOngoing()) break;
			lineUpForPuppetShow();
			if(!march.isParadeOngoing()) break;
			watchPuppetShow();
			if(!march.isParadeOngoing()) break;
		}
		leaving();

	}

	public final void setName(String name) {
		Thread.currentThread().setName(name); // Sets name of Thread
	}

	public void msg(String m) {  //Generic Message Method
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}
	
	public void getReadyToWait() {
		setName(name);
		Random random = new Random();
		walkingTime = 2000 + random.nextInt(500);// for walking around 20 min
	}
	public void beginWaiting() {
		int waiting = march.letOrangeInParade();
		
		march.paradeWaiting(waiting%totalParadeGroups); //used to place student into the line for the parade

	}
	public void marchInParade() {
		march.walking();
		parades++;
		try {
			
			Thread.sleep(walkingTime);  //sleep/march for random time in parade
			
		} catch (InterruptedException e1) {
			msg("I didn't like the parade so I went home");
		}
		msg("I have exited the parade");
	}
	
	public void snackBreak() {
		try {
			msg("I Have Taken a Snack Break");
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			msg("I didn't like the parade so I went home");
		}
	}
	
	public void lineUpForPuppetShow() {
		march.puppetShowWait();
	}
	
	public void watchPuppetShow() {
		march.watchingPuppetShow();
		puppetShow++;
	}
	
	public void leaving() {
		msg("i am going home now");
		msg("i participated in "+parades+" parades and saw "+puppetShow+" puppet shows today");
	}

}
