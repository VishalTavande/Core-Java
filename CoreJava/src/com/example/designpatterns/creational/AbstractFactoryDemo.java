package com.example.designpatterns.creational;

public class AbstractFactoryDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car =  VehicleFactory.getVehicle(new CarFactory("Car", "1005-A", "Audi S5", 30000000));
		Vehicle truck = VehicleFactory.getVehicle(new TruckFactory("Truck", "5005-Z", "Tata truck", 1500000));
		
		System.out.println("Car details are: " + car);
		System.out.println("Truck details are: " + truck);
	}
}

interface VehicleAbstractFactory {
	public Vehicle getVehicle();
}

class CarFactory implements VehicleAbstractFactory {
	String type;
	String chessisNumber;
	String model;
	double price;
	
	public CarFactory(String type, String chessisNumber, String model, double price) {
		super();
		this.type = type;
		this.chessisNumber = chessisNumber;
		this.model = model;
		this.price = price;
	}

	@Override
	public Vehicle getVehicle() {
		return new Car(type, chessisNumber, model, price);
	}
	
}

class TruckFactory implements VehicleAbstractFactory {
	String type;
	String chessisNumber;
	String model;
	double price;
	
	public TruckFactory(String type, String chessisNumber, String model, double price) {
		super();
		this.type = type;
		this.chessisNumber = chessisNumber;
		this.model = model;
		this.price = price;
	}
	
	@Override
	public Vehicle getVehicle() {
		return new Truck(type, chessisNumber, model, price);
	}
}


class VehicleFactory {
	public static Vehicle getVehicle(VehicleAbstractFactory factory) {
		return factory.getVehicle();
	}
}
