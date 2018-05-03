package com.example.multithreading.reentrantloacks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	public static void main(String[] args) throws InterruptedException {
		final Runner runner = new Runner();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				runner.firstRun();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				runner.secondRun();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		runner.finalRun();
	}

}

class Runner {
	
	private Lock lock = new ReentrantLock();
	private int count=0;
	
	private Condition condition = lock.newCondition();
	
	public void firstRun() {
		try {
			System.out.println("Inside firstRun().. going to wait..");
			lock.lock();
			condition.await();
			System.out.println("firstRun() is signalled/awaken");
			increment();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void secondRun() {
		try {
			Thread.sleep(1000);
			System.out.println("Inside secondRun.. got the lock..waiting for newline character..");
			lock.lock();
			new Scanner(System.in).nextLine();
			System.out.println("Got the newline character.. going to signal");
			condition.signal();
			System.out.println("Signal has been given by secondRun()");
			increment();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void finalRun() {
		System.out.println("Count :" + count);
	}
	
	private void increment() {
		for(int i=0; i<10000; i++)
			count++;
	}
}