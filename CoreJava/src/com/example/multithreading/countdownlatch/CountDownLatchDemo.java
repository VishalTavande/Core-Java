package com.example.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch latch = new CountDownLatch(3);
		Thread cacheService = new Thread(new Service("Cache service", latch));
		Thread alertService = new Thread(new Service("Alert service", latch));
		Thread validateService = new Thread(new Service("Validate service", latch));
		
		cacheService.start();
		alertService.start();
		validateService.start();
		
		latch.await();
		System.out.println("All Services are UP now. We are ready to accept requests..");
	}

}

class Service implements Runnable {
	
	private String service;
	private CountDownLatch latch;
	Service(String service, CountDownLatch latch) {
		this.service = service;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Logger.getLogger(Service.class.getName()).log(Level.SEVERE, service + " is interrupted!");
		}
		System.out.println(service + " is started now..");
		latch.countDown();
	}
}
