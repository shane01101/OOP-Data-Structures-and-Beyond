/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		int b = list1.remove(0);
		assertEquals("Remove: check b is correct ", 21, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)42, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		int c = list1.remove(0);
		assertEquals("Remove: check c is correct ", 42, c);
		assertEquals("Remove: check size is correct ", 0, list1.size());
		
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		String s = shortList.remove(1);
		assertEquals("Remove: check s is correct ", "B", s);
		assertEquals("Remove: check element 1 is correct ", "A", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());
		
		String t = shortList.remove(0);
		assertEquals("Remove: check t is correct ", "A", t);
		assertEquals("Remove: check size is correct ", 0, shortList.size());
		
		for(int i = LONG_LIST_LENGTH - 1; i >=0 ; i--)
		{
			int x = longerList.remove(i);
			assertEquals("Remove: check " + i + " is correct ", i, x);
			//assertEquals("Remove: check element 0 is correct ", (Integer)i, list1.get(list1.size()-1));
			assertEquals("Remove: check size is correct ", i, longerList.size());
			
		}
		
		try {
			list1.remove(10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
	
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		shortList.add("Z");
		assertEquals("Check Last", "Z", shortList.get(shortList.size() - 1));
		list1.add(100);
		assertEquals("Check Last", (Integer)100, list1.get(list1.size() - 1));
		
		try {
			list1.add(10, 1000);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			list1.add(null);
			fail("Check null value");
		}
		catch (NullPointerException e) {}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Check size is correct ", (Integer)3, (Integer)list1.size());
		list1.add(5);
		assertEquals("Check size is correct ", (Integer)4, (Integer)list1.size());
		list1.add(6);
		assertEquals("Check size is correct ", (Integer)5, (Integer)list1.size());
		
		for(int i = 0; i < 5; i++)
			list1.remove(0);
		
		assertEquals("Check size is correct ", (Integer)0, (Integer)list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		shortList.add(0, "L");
		assertEquals("Check Index 0", "L", shortList.get(0));
		shortList.add(1, "S");
		assertEquals("Check Index 1", "S", shortList.get(1));
		shortList.add(4, "M");
		assertEquals("Check Index Last", "M", shortList.get(4));
		list1.add(0, 100);
		assertEquals("Check Index 0", (Integer)100, list1.get(0));
		list1.add(1, 105);
		assertEquals("Check Index 1", (Integer)105, list1.get(1));
		list1.add(4, 500);
		assertEquals("Check Index Last", (Integer)500, list1.get(4));
		
		try {
			list1.add(-1, 1000);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			list1.add(10, 1000);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			list1.add(0, null);
			fail("Check null value");
		}
		catch (NullPointerException e) {}
		
		try {
			list1.add(2, null);
			fail("Check null value");
		}
		catch (NullPointerException e) {}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		int oldVal = list1.set(0, 1000);
		int newVal = list1.get(0);
		assertEquals("Check old", (Integer)65, (Integer)oldVal);
		assertEquals("Check new set val", (Integer)1000, (Integer)newVal);
		
		try {
			list1.set(-1, 1000);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			list1.set(6, 1005);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
