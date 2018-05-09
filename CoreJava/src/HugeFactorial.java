
public class HugeFactorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private static List convertToList(int no) {
		List head = null;
		while(no>0) {
			int digit = no%10;
			insert(digit, head);
			no = no/10;
		}
		return head;
	}

	private static void insert(int digit, List head) {
		if(head == null) {
			head = new List();
			head.data = digit;
			return;
		}
		else {
			List node = new List();
			node.data = digit;
			List tmp = head;
			while(tmp.next != null)
				tmp = tmp.next;
			tmp.next = node;
		}
	}
	
	private static List multiply(List firstNumber, List secondNumber) {
		List result = new List();
		int secondNumberDigits = 0;
		while(secondNumber != null) {
			secondNumberDigits++;
			List firstTmp = firstNumber;
			int carry = 0;
			while(firstTmp != null) {
				int digit = (secondNumber.data * firstTmp.data) + carry;
				if(digit > 9) {
					carry = digit/10;
					digit = digit%10;
				}
				if(secondNumberDigits == 0)
					insert(digit, result);
				else 
					addToExisting(digit, secondNumberDigits, result);
				
				firstTmp = firstTmp.next;
			}
			
			secondNumber = secondNumber.next;
		}
	}

	private static void addToExisting(int digit, int secondNumberDigits, List result) {
		while(secondNumberDigits>0) {
			result = result.next;
			secondNumberDigits--;
		}
		int sum, carry = digit;
		while(result != null && (sum=result.data+digit)>9) {
			result.data = sum%10 + carry;
			carry = sum/10;
			result = result.next;
		}
		if(carry > 0) {
			if(result != null) {
				result.data; 
			}
		}
			
	}

}


class List {
	int data;
	List next;
}

