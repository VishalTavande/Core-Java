package com.example.multithreads.project;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/***
 * @author Vishal Tavande
 * @since 25-Apr-2018
 */
public class WordFinder implements Callable<List<String>> {

	private static List<String> fileList;
	private String path;
	CountDownLatch latch;
	
	public WordFinder(String path, CountDownLatch latch) {
		fileList = new LinkedList<String>();
		this.path = path;
		this.latch = latch;
	}

	public static void listDirectory(File file) {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File currFile : files) {
				listDirectory(currFile);
			}
		}
		else {
			fileList.add(file.getPath());
		}
	}

	@Override
	public List<String> call() throws Exception {
		File file = new File(path);
		listDirectory(file);
		latch.countDown();
		return fileList;
	}
}
