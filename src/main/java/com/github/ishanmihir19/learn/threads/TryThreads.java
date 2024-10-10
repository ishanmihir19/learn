package com.github.ishanmihir19.learn.threads;

public class TryThreads extends Thread {
	
	@Override
	public void run() {
		super.run();
		System.out.println("In run : ");
	}
	
	public static void main(String[] args) {
		
		TryThreads t = new TryThreads();
		t.start();
		
		for (int i = 0; i < 3; i++) {
			TryThreads tn = new TryThreads();
			tn.start();
		}
	}

}
