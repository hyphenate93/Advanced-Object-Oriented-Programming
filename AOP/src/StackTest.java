import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StackTest {

	@Test
	void testPop() {
		Stack test = new Stack();
		assertEquals(test.size(), 0);
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		assertEquals(test.size(), 4);
		test.pop();
		assertEquals(test.size(), 3);
		assertEquals(test.pop(), 3);
		test.pop();
		test.pop();
		assertEquals(test.size(), 0);
		
	}
	
	@Test
	@DisplayName("throws EmptyStackException when popped")
	void throwsExceptionWhenPopped() {
		Stack test = new Stack();
		assertThrows(EmptyStackException.class, test::pop);
	}

	@Test
	@DisplayName("Push the right size and location")
	void testPush() {
		Stack test = new Stack();
		assertEquals(test.size(), 0);
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		assertEquals(test.size(), 4); /*4 integers added, list size should be 4 */
		assertEquals(test.returnIndex(2), 3);
	}

	@Test
	@DisplayName("Push the right size and location with n amount")
	void testDynamicPush() {
		Stack test = new Stack();
		test.dynamicPush(1,2,3,4,5);
		assertEquals(test.size(), 5); /*4 integers added, list size should be 4 */
		assertEquals(test.returnIndex(4), 5);
	}

	@Test
	void testMultiPop() {
		Stack test = new Stack();
		test.dynamicPush(1,2,3,4,5);
		assertEquals(test.size(), 5);
		test.multiPop(5);
		assertEquals(test.size(), 0);
		
	}



}
