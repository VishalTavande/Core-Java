package com.example.designpatterns.creational;

public class FactoryDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car =  VehicleFactory.getVehicle("Luxury", "Car", "1005-A", "Audi S5", 30000000);
		Vehicle truck =  VehicleFactory.getVehicle("Heavyload", "Truck", "5005-Z", "Tata truck", 1500000);
		
		System.out.println("Car details are: " + car);
		System.out.println("Truck details are: " + truck);
	}
}


abstract class Vehicle {
	String type;
	String chessisNumber;
	String model;
	double price;
	
	public abstract String getType();
	public abstract String getChessisNumber();
	public abstract String getModel();
	public abstract double getPrice();
	
	@Override
	public String toString() {
		return "Type: " + this.type + ", Chessis Number: " + this.chessisNumber + ", Model: " + this.model + ", Price: " + this.price;
	}
}

class Car extends Vehicle {
	
	Car(String type, String chessisNumber, String model, double price) {
		this.type = type;
		this.chessisNumber = chessisNumber;
		this.model = model;
		this.price = price;
	}
	
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getChessisNumber() {
		return this.chessisNumber;
	}

	@Override
	public String getModel() {
		return this.model;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
}


class Truck extends Vehicle {

	Truck(String type, String chessisNumber, String model, double price) {
		this.type = type;
		this.chessisNumber = chessisNumber;
		this.model = model;
		this.price = price;
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getChessisNumber() {
		return this.chessisNumber;
	}

	@Override
	public String getModel() {
		return this.model;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
}

class VehicleFactory {
	public static Vehicle getVehicle(String category, String type, String chessisNumber, String model, double price) {
		if(category.equalsIgnoreCase("Luxury")) {
			return new Car(type, chessisNumber, model, price);
		}
		else if(category.equalsIgnoreCase("Heavyload")) {
			return new Truck(type, chessisNumber, model, price);
		}
		else
			return null;
	}
}