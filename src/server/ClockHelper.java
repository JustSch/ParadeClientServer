package server;

import required.Clock;

public class ClockHelper extends Clock{
	ClockHelper(){
		this.run();
	}
	@Override
	public void run(){
		setName("b");
		System.out.println("jjjjjjjjjjjjjj");
		msg("ggggg");
	}
}
