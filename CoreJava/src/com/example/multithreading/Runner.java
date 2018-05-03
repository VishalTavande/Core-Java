package com.example.multithreading;

public class Runner implements Runnable {

	String currentThread;
	Runner(String currentThread) {
		this.currentThread = currentThread;
	}
	
	@Override
	public void run() {
		
		for(int i=0; i<1000; i++) {
			System.out.println(this.currentThread + " says: " + i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(this.currentThread + " stops!");
	}

}
