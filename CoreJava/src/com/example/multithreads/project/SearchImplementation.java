package com.example.multithreads.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchImplementation implements Callable<Integer> {

	private String wordToBeSearched;
	private String fileName;
	private AtomicInteger counter;
	
	public SearchImplementation(String wordToBeSearched, String fileName) {
		this.wordToBeSearched = wordToBeSearched;
		this.fileName = fileName;
		this.counter = new AtomicInteger(0);
	}
	
	@Override
	public Integer call() throws Exception {
		
		File file = new File(fileName);
		try {
			String line;
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			int fromIndex = 0;
			while((line = br.readLine()) != null) {
				do {
					fromIndex = line.indexOf(wordToBeSearched, fromIndex);
					if(fromIndex != -1) {
						counter.incrementAndGet();
						fromIndex++;
					}
				} while(fromIndex != -1);
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return counter.get();
	}

}
