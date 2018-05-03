import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Person implements Comparable<Person>{
	int age;
	String name;
	
	Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public String toString() {
		return this.age + ":" + this.name;
	}

	public int compareTo(Person p) {
		return this.age - p.age;
	}
	
}

public class Main {

    public static void main (String[] args) {
       /*Set<String> set = new HashSet<String>();
       set.add("Vishal");
       set.add("Mahesh");
       set.add("Rajaram");
       set.add("Parvati");
       set.add("Sarika");
       
       Iterator<String> iterator = set.iterator(); 
       
       while(iterator.hasNext()) {
    	   if(iterator.next().equals("Vishal")) {
    		  iterator.remove();
    		   //set.remove("Vishal");
    	   }
       }
       
       System.out.println(set.toString());*/

       /*Set<Person> treeSet = new TreeSet<Person>(new Comparator<Person>() {
	
			
			public int compare(Person p1, Person p2) {
				return p1.age - p2.age;
			}
    	   
       });
       treeSet.add(new Person(26, "Mahesh"));
       treeSet.add(new Person(28, "Vishal"));
       treeSet.add(new Person(23, "Sarika"));
       treeSet.add(new Person(1, "Arnav"));
       
       //System.out.println(treeSet.toString());
       
       Person p1 = new Person(32, "Shreya");
       Person p2 = new Person(40, "Harry");
       Person p3 = new Person(30, "Paul");
       Person[] objArray = new Person[]{p1, p2, p3};*/
       //Arrays.sort(objArray);
       //for (Person p:objArray) System.out.print(p + " ");

       Person p1 = new Person(10, "Shreya");
       Person p2 = new Person(30, "Harry");
       Person p3 = new Person(20, "Paul");
       Person p4 = new Person(50, "Paul");
       HashSet<Person> set = new HashSet<>();
       set.add(p1);
       set.add(p2);
       set.add(p3);
       set.add(p4);
       //System.out.println(set.size());
       
       List<Person> list = new LinkedList<Person>(set);
       
       Collections.sort(list);
       
       for(Person p : list) 
    	  System.out.println(p.toString());

    }
    
}

