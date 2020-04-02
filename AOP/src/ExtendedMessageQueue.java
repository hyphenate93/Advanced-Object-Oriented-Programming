


/**
 * @author {Andreas}
 * @invariant count <=0 && count <= elements.length
 *
 */
public class ExtendedMessageQueue 
{ 
	
	public static void main(String [] args) {
		ExtendedMessageQueue test = new ExtendedMessageQueue(5);
		Message hej1 = new Message("1");
		Message hej2 = new Message("2");
		Message hej3 = new Message("3");
		Message hej4 = new Message("4");
		Message hej5 = new Message("5");
		Message hej6 = new Message("6");
		Message hej7 = new Message("7");
		Message hej8 = new Message("8");
		Message hej9 = new Message("9");
		Message hej10 = new Message("10");
		test.add(hej1);
		test.add(hej2);
		test.add(hej3);
		test.add(hej4);
		test.add(hej5);
		test.printList(test);
		System.out.println(test.getHead() +" this is the head");
		System.out.println(test.getTail() + " this is the tail");
		System.out.println();
		test.remove();
		test.remove();
		test.remove();
		test.remove();
		test.add(hej6);
		test.add(hej7);
		test.add(hej8);
		test.add(hej9);
		test.add(hej10);
		test.printList(test);
		System.out.println(test.getHead() +" this is the head");
		System.out.println(test.getTail() + " this is the tail");
		
		
		
		
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
	   
	  
			for (Message i : Messages) {

				 elements[tail] = i; 
			      tail = (tail + 1) % elements.length; 
			      count++; 

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
  /* public void printList() {
	   for (int i = 0; i < elements.length; i ++) {
		   System.out.println(elements[i]);
	   }
	   
   } */
   public void printList(ExtendedMessageQueue a) {
	   
	   for (int i = 0; i < elements.length; i ++) {
		 //  System.out.println(PrintElements.peek());
		   if(elements[i]!=null) {
		   System.out.println(elements[i]);
		   }
		   //PrintElements.remove();
	
	   }
	   
   }
   
   private void doublequeue() {
	     
		
		Message[] elements2 = new Message[elements.length * 2];
	
		
		
		for(int i = 0; i < elements.length; i++ ) {
			elements2[i] = remove();
			count++;
		}
		this.head = 0;
		this.tail = count;
		elements = elements2;
		
	}
   public int getTail() {
	   return tail;
	   }
	   public int getHead() {
	 	  return head;
	 	  }

   private Message[] elements; 
   private int head; 
   private int tail; 
   private int count; 
}