package required;
public class StaffMember implements Runnable {

	private Marching march = null;
	public String name;
	public Object StaffNotifier;
	public static long time = System.currentTimeMillis();
	public int numSeat;

	public StaffMember(Marching march, String name, Object StaffNotifier, int numSeat) {
		this.march = march;
		this.name = name;
		this.StaffNotifier = StaffNotifier;
		this.numSeat = numSeat;
	}

	@Override
	public void run() {
		settingName();
		while (march.isParadeOngoing()) {
			prePuppetShow();
			if(!march.isParadeOngoing()) break;
			openPuppetShow();
			if(!march.isParadeOngoing()) break;


		}
		endParade();

	}
	public void settingName() {
		setName(name);
	}
	public void prePuppetShow() {
		msg("The Puppet Show Will Start Soon: Opening Tent "+numSeat+" may enter the tent");
		march.waiting(StaffNotifier);
	}
	
	public void openPuppetShow() {
		try {
			openingCurtain();
			guideToExit();

			msg("The Puppet Show Is Starting: Closing Tent");
		} catch (Exception e) {
			msg("The Students Were too slow to see the puppet show at this time");
		}
	}

	public final void setName(String name) {

		Thread.currentThread().setName(name); // Sets name of Thread

	}

	public void msg(String m) {  //Generic Message Method
		System.out.println(
				"[" + (System.currentTimeMillis() - time) + "] " + Thread.currentThread().getName() + ": " + m);
	}

	public void waiting() {  //Used for the staff to wait on it's own object
		synchronized (StaffNotifier) {
			try {
				StaffNotifier.wait();
			} catch (InterruptedException e) {
				msg("The staff member quit his job");
			}
		}
	}

	public void openingCurtain() {  //Allow students into puppet show
		for (int i = 0; i < numSeat; i++) {
			march.letInPuppetShow();
		}
	}

	public void guideToExit() {  //guide students to the exit by releasing them for the puppet vector
		for (int j = 0; j < numSeat; j++) {
			march.puppetRelease();
		}
	}
	
	public void endParade() {  //releases all puppet related vectors
		march.releasePuppetVectors();

		msg("The parade is ending please make your way to the nearest exit");
	}

}
