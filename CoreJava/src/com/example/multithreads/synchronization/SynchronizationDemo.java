package com.example.multithreads.synchronization;



public class SynchronizationDemo {

	private volatile int count=0;
	
	public static void main(String[] args) {
		
		SynchronizationDemo launcher = new SynchronizationDemo();
		launcher.doWork();

	}
	
	public synchronized void increment() {
		count++;
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++)
					increment();
			}
		});

		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++)
					increment();
			}
		});
		
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Count is: " + count);
	}
}
