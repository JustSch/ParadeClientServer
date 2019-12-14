package required;
import java.util.Vector;

public class Marching {

	private static Vector<Object> paradeGroups = new Vector<Object>(); // Used to Store groups formed for parade
	private static Vector<Object> puppetShow = new Vector<Object>(); // all the seats in the puppet show
	private static Vector<Object> puppetShowWait = new Vector<Object>(); // used to store students who wait for the
																			// puppet show
	private int greenStudents = 0;
	private int orangeStudents = -1;
	private static Object ClockNotifier = new Object();
	private static int greenParadeGroups = 0;// Keeps track of where green should be added into vector
	private static boolean paradeOngoing;

	public static long time = System.currentTimeMillis();

	public synchronized void startParade(int paradeNumber) { // Used to Initialize objects in vector
		for (int i = 0; i < paradeNumber; i++) {
			Object convey = new Object();
			paradeGroups.add(convey);
		}

	}

	public void readyPuppetShow() { // Used to Initialize objects in vector
		for (int i = 0; i < 6; i++) {
			Object convey = new Object();
			puppetShow.add(convey);
		}
	}

	public synchronized int letOrangeInParade() { // used to keep track of where orange should be added into vector
		orangeStudents++;
		return orangeStudents;

	}

	public synchronized int letGreenInParade() { // used to keep track of where green should be added into vector

		if (greenStudents % 2 == 0 && greenStudents != 0) {
			greenParadeGroups++;
		}
		greenStudents++;
		return greenParadeGroups;
	}

	public static void releaseParadeGroups() { // Releases Students into the Parade by notifying their object and
												// removing it from the vector
		Object convey = paradeGroups.get(0);
		synchronized (convey) {
			msg("A group is entering in the parade");
			convey.notifyAll();
			paradeGroups.remove(0);

		}
	}

	public void paradeWaiting(int paradeWaitNumber) { // Used to put students into groups before they go into the vector
		Object convey = paradeGroups.get(paradeWaitNumber);
		synchronized (convey) {
			while (true) {
				try {
					msg("I lined up for the parade");
					convey.wait();
					msg("I have entered the parade");
					break;
				} catch (InterruptedException e) {
					msg("Cant wait for parade");
				}
			}
		}
	}

	public void waiting(Object Notifier) { // Used for Staff Object to wait
		Object convey = Notifier;
		synchronized (convey) {
			while (true) {
				try {
					convey.wait();
					break;
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public void releasing(Object Notifier) { // used for staff object to be released
		Object convey = Notifier;
		synchronized (convey) {
			convey.notify();
		}
	}

	public void letInPuppetShow() {  //used to allow students into the puppet show
		Object convey = puppetShowWait.remove(0);
		synchronized (convey) {
			convey.notify();
		}

	}

	public void watchingPuppetShow() {  //Used to keep student in the puppetshow vector
		Object convey = new Object();
		synchronized (convey) {
			while (true) {
				try {
					msg("I am watching the puppet show");
					puppetShow.add(convey);
					convey.wait();
					msg("I left the puppet show");
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public void releasePuppetVectors() {  //used to release all puppet show related vectors when parade is over for the day
		for (Object o : puppetShow) {
			synchronized (o) {
				o.notify();
			}
		}
		for (Object j : puppetShowWait) {
			synchronized (j) {
				j.notify();
			}
		}
	}

	public void puppetShowWait() {  //Used to wait to get into puppet show
		Object convey = new Object();
		synchronized (convey) {
			try {
				while (true) {
					puppetShowWait.add(convey);
					msg("I am standing in line for the puppet show");
					msg("I will wander while I wait for the next show to begin");
					convey.wait();
					break;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void puppetRelease() {  //Used to remove students from puppet show once it is over
		Object convey = puppetShow.remove(0);
		synchronized (convey) {
			convey.notify();
		}
	}

	public synchronized static void msg(String m) {  //Generic Message Method
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

	public synchronized void walking() {
		msg("I am Marching in the Parade");
	}

	public synchronized boolean isParadeOngoing() {
		return paradeOngoing;
	}

	public synchronized void setParadeIsOngoing() {
		paradeOngoing = true;
	}

	public synchronized void setParadeOver() {
		paradeOngoing = false;
	}

}
