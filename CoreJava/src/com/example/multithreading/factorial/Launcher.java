package com.example.multithreading.factorial;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.example.multithreading.factorial.Factorial.List;

/***
 * @author Vishal Tavande
 * @since 25-Apr-2018
 */
public class Launcher {
	
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);
			
		ExecutorService executor = Executors.newFixedThreadPool(5);

		FutureTask<List> list = (FutureTask<List>) executor.submit(new Runner(, latch));
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Files have been loaded.., searching for the word....");
		
		long startTime = System.currentTimeMillis();
		while(!list.get().isEmpty()) {
			FutureTask<Integer> result = (FutureTask<Integer>) executor.submit(new SearchImplementation("Vishal", list.get().remove(0)));
			counter.getAndAdd(result.get());
		}
		System.out.println("Total occurances: " + counter.get());
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.HOURS);
		long endTime = System.currentTimeMillis();
		System.out.println("ExecutorService has taken " + (endTime-startTime) + " milliseconds");
	}

}
