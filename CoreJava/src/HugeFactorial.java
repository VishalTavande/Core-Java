import java.util.Scanner;

public class HugeFactorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		List firstNumber = convertToList(scan.nextInt());
		List secondNumber = convertToList(scan.nextInt());
		
		List result = multiply(firstNumber, secondNumber);
		
		result = reverseList(result);
		System.out.println("Answer is:");
		displayList(result);
		
	}
	
	private static List reverseList(List result) {
		List current = result;
		List next = current.next;
			
		while(next != null) {
			if(current == result) {
					current.next = null;
			}
			List tmp = next.next;
			next.next = current;
			current = next;
			next = tmp;
		}
		result = current;
		return result;
	}

	private static List convertToList(int no) {
		List head = null;
		while(no>0) {
			int digit = no%10;
			head = insert(digit, head);
			no = no/10;
		}
		return head;
	}

	private static List insert(int digit, List head) {
		if(head == null) {
			head = new List();
			head.data = digit;
		}
		else {
			List node = new List();
			node.data = digit;
			List tmp = head;
			while(tmp.next != null)
				tmp = tmp.next;
			tmp.next = node;
		}
		return head;
	}
	
	private static List multiply(List firstNumber, List secondNumber) {
		List result = null;
		List currRef = null;
		int secondNumberDigits = 0;
		while(secondNumber != null) {
			List firstTmp = firstNumber;
			int carry = 0;
			while(firstTmp != null) {
				int digit = (secondNumber.data * firstTmp.data) + carry;
				if(digit > 9) {
					carry = digit/10;
					digit = digit%10;
				}
				if(secondNumberDigits == 0)
					result = insert(digit, result);
				else 
					currRef = addToExisting(digit, currRef, result);
				
				firstTmp = firstTmp.next;
			}
			if(carry>0) {
				if(secondNumberDigits == 0)
					result = insert(carry, result);
				else
					currRef = addToExisting(carry, currRef, result);
			}
			
			secondNumber = secondNumber.next;
			secondNumberDigits++;
			int no=secondNumberDigits;
			currRef = result;
			
			while(no>0 && currRef != null) {
				currRef = currRef.next;
				no--;
			}
		}
		return result;
	}

	private static List addToExisting(int digit, List currRef, List result) {
		List head = currRef;
		int sum, carry = 0;
		while(currRef != null && (sum=currRef.data+digit)>9) {
			currRef.data = sum%10 + carry;
			carry = sum/10;
			digit = carry;
			currRef = currRef.next;
		}
		
		if(currRef != null) {
			currRef.data = currRef.data+digit;
		}
		else {
			currRef = insert(digit, result);
			return currRef;
		}
		return currRef.next;
	}
	
	
	private static void displayList(List head) {
		while(head != null) {
			System.out.print(head.data);
			head = head.next;
		}
	}

}


class List {
	int data;
	List next;
}