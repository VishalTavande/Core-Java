package com.example.multithreading.factorial;

import com.example.multithreading.factorial.Factorial.List;

public class Runner extends Thread {
	
	int first;
	
	public List result = null;

	Runner(int first) {
		this.first = first;
	}

	
	public List getResult() {
		return result;
	}
	
	public void setResult(List result) {
		this.result = result;
	}


	@Override
	public void run() {
		
		int last = first + 199;
		List firstNumber = Factorial.convertToList(first);
		List secondNumber = Factorial.convertToList(first+1);
		
		int no = first+2; 
		while(no <= last+1) {
			secondNumber = Factorial.multiply(secondNumber, firstNumber);
			firstNumber = Factorial.convertToList(no);
			no++;
		}

		setResult(secondNumber);
	}


}
