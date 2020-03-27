
/**
 * @author {Andreas}
 * @invariant stackPos <=0 && stackPos <= elements.length
 */

import java.util.EmptyStackException;

public class MessageStack {

	private int stackPos = 0;
	private Message[] messages;

	public MessageStack() {

		messages = new Message[10];
	}

	/**
	 * removes the element in the top of the stack
	 * @precondition stackPos > 0
	 * @return the removed element
	 */
	public Message pop() {
		assert stackPos > 0 : "Violated precondition, List had no elements to pop > 0"; 
		
			
			Message t = messages[--stackPos];
			return t;
		
	}

	/**
	 * Push operation which adds item to the stack
	 * @param elementToPush
	 */
	public void push(Message newMessage) {

		if (stackPos == 10) {
			throw new IllegalArgumentException("Stack is full");
		} else {
			messages[stackPos] = newMessage;
			stackPos++;
		}
	}

	public void dynamicPush(Message... Messages) {
		if (stackPos == 10) {
			throw new IllegalArgumentException("Stack is full");
		} else {
			for (Message i : Messages) {

				messages[stackPos] = i;

				stackPos++;
			}

		}
	}

	/**
	 * @param 
	 * @return
	 * @precondition stackPos-n > 0
	 */
	public Message[] multiPop(int n) {
		
		assert stackPos-n > 0 : "Violated precondition, tried to pop more elements then is in the list"; 
			
		Message[] poppedStack = new Message[n];

			for (int i = n - 1; i >= 0; i--) {
				poppedStack[i] = messages[--stackPos];
				

			}
			return poppedStack;
		

	}

	/**
	 * doubles the size of an array and puts copies of the original values into it
	 *//*
	private void doubleArray() {
		int increasedSize = integers.length * 2;

		integers = Arrays.copyOf(integers, increasedSize);
	}
*/
	public int size() {
		return stackPos;
	}

	public Message returnIndex(int x) {
		for (int i = 0; i < stackPos; i++) {
			if (i == x) {
				return messages[i];
			}
		}
		throw new IllegalArgumentException("index out of bounds");
	}
	  public void printList() {
		    for (int i = 0; i < stackPos; i++) {
		    	System.out.println(messages[i]);
		    }
		   }
}
