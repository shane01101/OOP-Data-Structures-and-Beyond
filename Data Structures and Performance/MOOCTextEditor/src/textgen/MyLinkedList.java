package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null)
			throw new NullPointerException();
		
//		LLNode<E> node = new LLNode<>(element);
//		
//		if(size > 0)
//		{
//			tail.next = node;
//			node.prev = tail;
//		}
//		else
//		{
//			head = node;
//		}
//		tail = node;
//
//		size++;
		add(size, element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(size == 0 || index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		
//		LLNode<E> curPtr = head;
//		
//		int pos = 0;
//		while (pos <= index)
//		{
//			if(pos == index)
//				return curPtr.data;
//			
//			curPtr = curPtr.next;
//			pos++;
//		}
//		return null;
		return find(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException();
		
		LLNode<E> node = new LLNode(element);
		
		LLNode<E> curPtr = find(index);
		
		if(head == null) //empty
		{
			head = node;
			tail = node;
		}
		else if(index == size) //end
		{
			tail.next = node;
			node.prev = tail;
			tail = node;
			
			if(head == null)
				head = node;
		}
		else if(index == 0)
		{
			node.next = head;
			node.next.prev = node;
			head = node;
		}
		else //middle
		{
			node.next = curPtr; 
			node.prev = curPtr.prev;
			node.prev.next = node;
			curPtr.prev = node;
		}
		size++;
	}
	
	private LLNode<E> find(int index)
	{
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		int pos = 0;
		LLNode<E> curPtr = head;
		
		while(pos <= index)
		{
			if(pos == index)
				return curPtr;
			
			curPtr = curPtr.next;
			pos++;
		}
		return null;
	
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> curPtr = find(index);
		
		if(size == 1)
		{
			head = null;
			tail = null;
		}
		else if(curPtr == head) //begin
		{
			head = head.next;
			head.prev = null;
		}
		else if (curPtr == tail) //end
		{
			tail.prev = null;
			tail = curPtr.prev;
			//tail.next = null;

		}
		else //middle
		{
			curPtr.next.prev = curPtr.prev;
			curPtr.prev.next = curPtr.next;
			curPtr.next = null;
			curPtr.prev = null;
		}
		size--;
		return curPtr.data;
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null)
			throw new NullPointerException();
		
		LLNode<E> curPtr = find(index);
		E old = curPtr.data;
		curPtr.data = element;
		return old;
	}   
	
	public String toString()
	{
		String s="";
		for(int i=0 ;i<size; i++)
		{
			s += this.get(i) + " ";
		}
		return s;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public String toString() {
        return data.toString();
    }

}
