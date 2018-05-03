package com.example.multithreading.semaphore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	static Semaphore semaphore = new Semaphore(1);
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=1; i<=5; i++) {
			Future future = executor.submit(new Runnable() {
				@Override
				public void run() {
					try {
						requestResource(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
			try {
				System.out.println("Returned value is: " + future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		
	}
	
	
	public static void requestResource(String name) throws InterruptedException {
		semaphore.acquire();
		System.out.println(name + ": Resource acquired");
		Thread.sleep(1000);
		semaphore.release();
		System.out.println(name + ": Resource released");
	}

}
