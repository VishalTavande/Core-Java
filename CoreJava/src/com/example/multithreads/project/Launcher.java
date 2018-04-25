package com.example.multithreads.project;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * @author Vishal Tavande
 * @since 25-Apr-2018
 */
public class Launcher {

	static List<String> fileList = new LinkedList<String>();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		String path = "C:\\APRM_PBG";
		CountDownLatch latch = new CountDownLatch(1);
		AtomicInteger counter = new AtomicInteger(0);
		
		ExecutorService executor = Executors.newFixedThreadPool(10);

		FutureTask<List<String>> list = (FutureTask<List<String>>) executor.submit(new WordFinder(path, latch));
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
