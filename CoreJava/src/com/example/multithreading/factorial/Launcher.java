package com.example.multithreading.factorial;

/***
 * @author Vishal Tavande
 * @since 25-Apr-2018
 */
public class Launcher {
	
	public static void main(String[] args) {
		
		int totalThreads = 100000/200;
		
		Runner runners[] = new Runner[totalThreads];
		for(int i=0; i<totalThreads; i++) {
			runners[i] = new Runner((i*200)+1);
		}
		
		//start threads
		for(int i=0; i<totalThreads; i++) {
			runners[i].start();
		}

		for(int i=0; i<totalThreads; i++) {
			//wait for all threads to finish
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Calculated fractionally.. Merging the results");
		System.out.println("Factorial is: ");
		
		Runner first = runners[0];
		for(int i=1; i<totalThreads; i++) {
			first.setResult(Factorial.multiply(first.getResult(), runners[i].getResult()));
		}

		Factorial.displayList(Factorial.reverseList(first.getResult()));
		
	}
}