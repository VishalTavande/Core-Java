package com.example.designpatterns.creational;

public class BuilderDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer computer = new Computer.ComputerBuilder("500GB", "4GB DDR3")
										.setExternalDiskEmbedded(true).setGraphicsCardEmbedded(false).build();
		System.out.println(computer);
	}

}

class Computer {
	String HDD;
	String RAM;
	
	boolean isGraphicsCardEmbedded;
	boolean isExternalDiskEmbedded;
	
	private Computer(ComputerBuilder builder) {
		this.HDD = builder.HDD;
		this.RAM = builder.RAM;
		this.isExternalDiskEmbedded  = builder.isExternalDiskEmbedded;
		this.isGraphicsCardEmbedded = builder.isGraphicsCardEmbedded;
	}
	
	public static class ComputerBuilder {
		String HDD;
		String RAM;
		
		boolean isGraphicsCardEmbedded;
		boolean isExternalDiskEmbedded;
		
		public ComputerBuilder setGraphicsCardEmbedded(boolean isGraphicsCardEmbedded) {
			this.isGraphicsCardEmbedded = isGraphicsCardEmbedded;
			return this;
		}

		public ComputerBuilder setExternalDiskEmbedded(boolean isExternalDiskEmbedded) {
			this.isExternalDiskEmbedded = isExternalDiskEmbedded;
			return this;
		}

		public ComputerBuilder(String HDD, String RAM) {
			this.HDD = HDD;
			this.RAM = RAM;
		}
		
		public Computer build() {
			return new Computer(this);
		}
	}
	
	@Override
	public String toString() {
		return "HDD: " + this.HDD + ", RAM: " + this.RAM + ", Graphics Card included: " + this.isGraphicsCardEmbedded + ", External Disk included: " + this.isExternalDiskEmbedded;
	}
}