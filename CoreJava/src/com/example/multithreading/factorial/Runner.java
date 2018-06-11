package com.example.multithreading.factorial;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.example.multithreading.factorial.Factorial.List;

public class Runner implements Callable<List> {
	
	int first;
	CountDownLatch latch;
	
	Runner(int first, CountDownLatch latch) {
		this.first = first;
		this.latch = latch;
	}

	@Override
	public List call() throws Exception {
		
		int last = first + 19999;
		List firstNumber = Factorial.convertToList(first);
		List secondNumber = Factorial.convertToList(first+1);
		
		int no = first+2; 
		while(no <= last+1) {
			secondNumber = Factorial.multiply(secondNumber, firstNumber);
			firstNumber = Factorial.convertToList(no);
			no++;
		}
		
		latch.countDown();
		return Factorial.reverseList(secondNumber);
	}


}
