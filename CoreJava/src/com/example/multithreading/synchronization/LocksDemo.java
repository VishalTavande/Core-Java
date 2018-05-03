package com.example.multithreading.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Worker {
	
	static Random random = new Random();
	
	static Object evenLock = new Object();
	static Object oddLock = new Object();
	
	List<Integer> listWithEvenNumbers = new ArrayList<Integer>();
	List<Integer> listWithOddNumbers = new ArrayList<Integer>();
	
	public void populateEvenList() {
		
		synchronized (evenLock) {
			for(int i=0; i<1000; i++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				listWithEvenNumbers.add(random.nextInt(1000));
			}
		}
	}
	
	public void populateOddList() {
		synchronized (oddLock) {
			for(int i=0; i<1000; i++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				listWithOddNumbers.add(random.nextInt(1000));
			}
		}
	}
	
	public void launch() {
		System.out.println("Main started");
		
		long startTime = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				populateLists();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				populateLists();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken: " + (endTime-startTime));
		System.out.println("ListWithEvenNumbers : " + listWithEvenNumbers.size());
		System.out.println("ListWithOddNumbers : " + listWithOddNumbers.size());
		System.out.println("Main ended");
	}
	
	public void populateLists() {
		populateEvenList();
		populateOddList();
	}
}

public class LocksDemo {

	public static void main(String[] args) {
		new Worker().launch();
	}
	
}
