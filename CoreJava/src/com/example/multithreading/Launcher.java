package com.example.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Launcher {

	public AtomicInteger counter = new AtomicInteger();
	public static void main(String[] args) throws InterruptedException {
		
		final Launcher launcher = new Launcher();

		System.out.println("Main starts");
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10; i++) {
					launcher.increment();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				for(int i=0; i<10; i++) {
					launcher.decrement();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10; i++) {
					launcher.increment();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				for(int i=0; i<10; i++) {
					launcher.decrement();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		thread1.start();
		thread2.start();
		
		thread2.join();
		thread1.join();
		
		System.out.println(launcher.value());
		
		System.out.println("Main Ends.");
	}

	public void increment() {
		counter.incrementAndGet();
	}
	
	public void decrement() {
		counter.decrementAndGet();
	}
	
	public int value() {
		return counter.get();
	}
}
