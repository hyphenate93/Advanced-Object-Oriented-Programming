import java.util.Arrays;


/**
 * @author {Andreas}
 * @invariant count <=0 && count <= elements.length
 *
 */
public class ExtendedMessageQueue 
{ 
	
	public static void main(String [] args) {
		ExtendedMessageQueue test = new ExtendedMessageQueue(10);
		Message hej = new Message("hej");
		
		test.add(hej);
		test.dynamicAdd(hej,hej,hej);
		test.printList();
		test.Multiremove(3);
		System.out.println();
		test.printList();
		
	}
   /** 
       Constructs an empty message queue. 
       @param capacity the maximum capacity of the queue 
       @precondition capacity > 0
   */ 
   public ExtendedMessageQueue(int capacity) 
   { 
	   assert capacity > 0 : "Violated precondition, capacity > 0";
      elements = new Message[capacity]; 
      count = 0; 
      head = 0; 
      tail = 0; 
   } 

   /** 
       Remove message at head. 
       @return the message that has been removed from the queue
       @precondition size() > 0 
   */ 
   public Message remove() 
   { 
	   assert size() > 0  : "Violated precondition, capacity > 0";
      Message r = elements[head]; 
      head = (head + 1) % elements.length; 
      count--; 
      return r; 
   } 
   /** 
   Remove messages at head until desired amount has been removed. 
   @return the last message that has been removed from the queue
   @precondition size() > 0 
*/ 
   public Message Multiremove(int n) 
   { 
	   assert size() > 0  : "Violated precondition, capacity > 0";
	   Message r= elements[head];
      
      for(int i= 0 ; i < n; i++) {
    	   r = elements[head]; 
      head = (head + 1) % elements.length; 
      count--; 
      }
      return r; 
   } 
   

   /** 
       Append a message at tail. 
       @param aMessage the message to be appended 
       @precondition !isFull();
   */ 
   public void add(Message aMessage) 
   { 
	  
	   if(this.isFull()) {
		   doublequeue();
	   }
	   
      elements[tail] = aMessage; 
      tail = (tail + 1) % elements.length; 
      count++; 
	   
   } 
   /** 
   Append a message at tail Messages amount of times. 
   @param Messages the messages to be appended 
   @precondition !isFull();
*/ 
   public void dynamicAdd(Message... Messages) {
	   
	   if(this.isFull()) {
		   doublequeue();
		   
	   } 
	   
	   else {
			for (Message i : Messages) {

				 elements[tail] = i; 
			      tail = (tail + 1) % elements.length; 
			      count++; 

			}

		}
	}

   /** 
       Get the total number of messages in the queue. 
       @return the total number of messages in the queue 
   */ 
   public int size() 
   { 
      return count; 
   } 

   /** 
       Checks whether this queue is full
       @return true if the queue is full
   */ 
   public boolean isFull()
   { 
      return count == elements.length; 
   } 

   /** 
       Get message at head. 
       @return the message that is at the head of the queue 
       @precondition size() > 0 
   */ 
   public Message peek() 
   { 
	   assert size() > 0  : "Violated precondition, capacity > 0";
      return elements[head]; 
   } 
   public void printList() {
	   for (int i = 0; i < count; i ++) {
		   System.out.println(elements[i]);
	   }
	   
   }

   
   private void doublequeue() {
		int increasedSize = elements.length * 2;
		this.tail = elements.length;
		this.head = 0;
		elements = Arrays.copyOf(elements, increasedSize);
		
	}

   private Message[] elements; 
   private int head; 
   private int tail; 
   private int count; 
}
