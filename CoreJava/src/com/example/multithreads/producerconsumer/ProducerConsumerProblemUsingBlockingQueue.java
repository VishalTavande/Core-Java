package com.example.multithreads.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerProblemUsingBlockingQueue {

	public BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);
	public Random random = new Random();
	public static void main(String[] args) {
		new ProducerConsumerProblemUsingBlockingQueue().launch();
	}

	private void launch() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
	}
	
	public void produce() throws InterruptedException {
		
		while(true) {
			Integer number = random.nextInt(100);
			queue.put(number);
			System.out.println("Value produced: " + number + "; Queue size is: " + queue.size());
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			if(random.nextInt(10) != 5) {
				Thread.sleep(100);
			}
			else {
				Integer value = queue.poll();
				System.out.println("Value consumed is: " + value + "; Queue size is: " + queue.size());
			}
		}
	}
}
