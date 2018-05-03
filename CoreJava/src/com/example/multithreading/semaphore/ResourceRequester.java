package com.example.multithreading.semaphore;

public class ResourceRequester implements Runnable {

	Object resource  = new Object();
	volatile boolean lockAquired = true;
	
	@Override
	public void run() {
		try {
			while(this.acquire()) {
				System.out.println(Thread.currentThread().getId() + " has acquired the lock.");
				System.out.println(Thread.currentThread().getId() + " has released lock? " + this.release());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean acquire() throws InterruptedException {
		synchronized (resource) {
			while(!lockAquired) {
				this.wait();
				return false;
			}
			this.notifyAll();
			return true;
		}
	}
	
	
	public boolean release() throws InterruptedException {
		synchronized (resource) {
			while(lockAquired) {
				this.wait();
				return false;
			}
			this.notifyAll();
			return true;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=1; i<10; i++) {
			new Thread(new ResourceRequester()).start();
		}
		System.out.println("Main ends");
	}

}
