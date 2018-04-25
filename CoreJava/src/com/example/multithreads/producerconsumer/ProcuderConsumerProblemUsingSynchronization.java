package com.example.multithreads.producerconsumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class ProcuderConsumerProblemUsingSynchronization {

	private Queue<Integer> queue = new ArrayDeque<Integer>(10);
	Random random = new Random();
	Object lock = new Object();
	
	public static void main(String[] args) {
		try {
			new ProcuderConsumerProblemUsingSynchronization().launch();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void launch() throws InterruptedException {
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				produce();
			}
		});
		
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				consume();
			}
		});
		
		producer.start();
		consumer.start();
		
		producer.join();
		consumer.join();
	}

	public void produce() {
		while(true) {
			synchronized (lock) {
				if(queue.size() == 10) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					Integer number = random.nextInt(100);
					queue.offer(number);
					lock.notify();
					System.out.println("Produced.. Queue size now:" + queue.size());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void consume() {
		while(true) {
			synchronized (lock) {
				if(queue.size() == 0) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
						Integer value = queue.poll();
						lock.notify();
						System.out.println("Value consumed: " + value + "; Queue size: " + queue.size());
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		}
	}
}
