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

	public OrangeStudent(Marching march, String name, int numSeat, int totalParadeGroups) {
		this.march = march;
		this.name = name;
		this.numSeat = numSeat;
		this.totalParadeGroups = totalParadeGroups;

	}

	@Override
	public void run() {
		setName(name);
		Random random = new Random();
		int walkingTime = 2000 + random.nextInt(500);// for walking around 20 min
		while (march.isParadeOngoing()) {
			int waiting = march.letOrangeInParade();
			
			march.paradeWaiting(waiting%totalParadeGroups); //used to place student into the line for the parade
	
			if(!march.isParadeOngoing()) break;  //break if parade over
			
			try {
				march.walking();
				parades++;
				Thread.sleep(walkingTime);  //sleep/march for random time in parade
				if(!march.isParadeOngoing()) break;
			} catch (InterruptedException e1) {
				msg("I didn't like the parade so I went home");
			}
			if(!march.isParadeOngoing()) break;
			
			msg("I have exited the parade");

			try {
				msg("I Have Taken a Snack Break");
				Thread.sleep(1000);
				if(!march.isParadeOngoing()) break;
			} catch (InterruptedException e) {
				msg("I didn't like the parade so I went home");
			}
			march.puppetShowWait();
			if(!march.isParadeOngoing()) break;
			march.watchingPuppetShow();
			puppetShow++;
			if(!march.isParadeOngoing()) break;
		}
		msg("i am going home now");
		msg("i participated in "+parades+" parades and saw "+puppetShow+" puppet shows today");

	}

	public final void setName(String name) {
		Thread.currentThread().setName(name); // Sets name of Thread
	}

	public void msg(String m) {  //Generic Message Method
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

}
