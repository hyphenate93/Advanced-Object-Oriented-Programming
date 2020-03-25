
/**
 * @author {Andreas}
 *
 */
import java.util.Arrays;
import java.util.EmptyStackException;
 
public class Stack {
 
	private int stackPos = 0;
	private int[] integers;
	
 

	public Stack() {
		
		integers = new int[10];
	}
 

	/**
	 * removes the element in the top of the stack
	 * @return the removed element
	 */
	public int pop() {
		if (stackPos==0) {
		System.out.println("List is empty. can't pop");	
		  throw new EmptyStackException();
		}
		else {
		int t =  integers[--stackPos];
		return t;
	}
	}
 
	/**
	 * Push operation which adds item to the stack
	 * 
	 * @param elementToPush
	 */
	public void push(int newInt) {
		
	if(stackPos == 10) {
		 throw new IllegalArgumentException("Stack is full");
	}
	else {
		integers[stackPos] = newInt;
		stackPos++;
	}
	}
	
	public void dynamicPush(int ... ints) {
		if(stackPos == 10) {
			 throw new IllegalArgumentException("Stack is full");
		}
		else {
		 for (int i: ints) {
		
			 integers[stackPos] = i;
			
			 stackPos++;
	        }

	}
	}
	
	public int[] multiPop(int n) { 
		if (stackPos-n <0) {
			System.out.println("Trying to pop more elements then are in the stack");	
			  throw new EmptyStackException();
			}
			else {
				int [] poppedStack = new int[n];
				
				for(int i =n-1 ; i >= 0; i--) {
					poppedStack[i] = integers[--stackPos];
					
				}
				return poppedStack;
				}
				
		
		}
	
 
	/**
	 * doubles the size of an array and puts copies of the original values into it
	 */
	private void doubleArray() {
		int increasedSize = integers.length * 2;
		
		integers = Arrays.copyOf(integers, increasedSize);
	}

	
	public int size() {
		return integers.length;
	}
}
 

