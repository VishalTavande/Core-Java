package com.example.designpatterns.creational;

import java.util.LinkedList;
import java.util.List;

public class PrototypeDemo {

	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.loadList();
		Employee emp1 = (Employee) emp.clone();
		emp1.getList().remove("Rajaram");
		System.out.println(emp.toString());
		System.out.println(emp1.toString());
	}

}

class Employee implements Cloneable {
	
	private List<String> empList;
	
	public Employee() {
		empList = new LinkedList<String>();
	}
	
	public Employee(List<String> empList) {
		this.empList = empList;
	}
	
	public List<String> getList() {
		return this.empList;
	}
	
	public void loadList() {
		empList.add("Vishal");
		empList.add("Rajaram");
		empList.add("Tavande");
	}
	
	@Override
	public Object clone() {
		List<String> tmpList = new LinkedList<String>();
		for(String name : empList)
			tmpList.add(name);
		return new Employee(tmpList);
	}
	
	@Override
	public String toString() {
		return this.empList.toString();
	}
}
