package com.example.multithreading.reentrantloacks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BalanceTransferDemo {

	public static void main(String[] args) throws InterruptedException {
		final RunnerClass runner = new RunnerClass();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				runner.firstCall();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				runner.secondCall();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		runner.finalCall();
	}
}

class RunnerClass {
	
	Account account1 = new Account();
	Account account2 = new Account();
	
	/***
	 * Using synchronization below is commented
	 */
	
	/*Object lock = new Object();
	
	public void firstCall() {
		Random random = new Random();
		synchronized (lock) {
			for(int i=0; i<10000; i++)
				Account.balanceTransfer(account1, account2, random.nextInt(100));
		}
	}
	
	public void secondCall() {
		Random random = new Random();
		synchronized (lock) {
			for(int i=0; i<10000; i++)
				Account.balanceTransfer(account2, account1, random.nextInt(100));
		}
		
	}*/
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	public void firstCall() {
		Random random = new Random();
		for(int i=0; i<10000; i++) {
			try {
			    acquireLocks(lock1, lock2);
				Account.balanceTransfer(account1, account2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void secondCall() {
		Random random = new Random();
		for(int i=0; i<10000; i++) {
			try {
				acquireLocks(lock2, lock1);
				Account.balanceTransfer(account2, account1, random.nextInt(100));
			} finally {
				lock2.unlock();
				lock1.unlock();
			}
		}	
	}
	
	private void acquireLocks(Lock firstLock, Lock secondLock) {
		boolean gotFirstLock = false;
		boolean gotSecondLock = false;
		while(true) {
			
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			} finally {
				if(gotFirstLock && gotSecondLock)
					return;
				if(gotFirstLock)
					firstLock.unlock();
				if(gotSecondLock)
					secondLock.unlock();
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void finalCall() {
		System.out.println("Account 1 balance is: " + account1.getBalance());
		System.out.println("Account 2 balance is: " + account2.getBalance());
		System.out.println("Total balance is: " + (account1.getBalance()+account2.getBalance()));
	}
}


class Account {
	private int balance = 10000;
	
	public void deposit(int balance) {
		this.setBalance(this.getBalance() + balance);
	}
	
	public void withdraw(int balance) {
		this.setBalance(this.getBalance() - balance);
	}
	
	public static void balanceTransfer(Account acc1, Account acc2, int amount) {
		acc1.setBalance(acc1.getBalance()+amount);
		acc2.setBalance(acc2.getBalance()-amount);
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
