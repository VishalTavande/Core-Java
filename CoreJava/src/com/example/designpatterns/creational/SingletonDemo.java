package com.example.designpatterns.creational;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SingletonDemo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Singleton s1 = Singleton.getInstance();
		//Singleton s2 = Singleton.getInstance();
		System.out.println(s1.hashCode());
		final String filename = "Serialization.ser";
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(s1);
			ObjectInputStream inp = new ObjectInputStream(new FileInputStream(filename));
			Singleton s2 = (Singleton)inp.readObject();
			System.out.println(s2.hashCode());
		} catch (IOException e) {
			System.out.println("Not able to create file " + filename);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		

	}

}

class Singleton implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5317420132799781859L;

	private Singleton() {
		//define private constructor to avoid object creation from outside of class
	}
	
	private static class SingletonInner {
		private static Singleton instance = new Singleton();
		
		private static Singleton getInstance() {
			return instance;
		}
	}
	
	public static Singleton getInstance() {
		return SingletonInner.getInstance();
	}
	
	protected Object readResolve() {
		return SingletonInner.getInstance();
	}
}