package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Jan Toma, CS310
//LinkedList.java
public class LinkedList<E> implements ListI<E> {

	private Node<E> head, tail;
	private int size;

	private class Node<E> {
		E data;
		Node<E> next;

		public Node(E data) {
			this.data = data;
			next = null;
		}
	}

	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Adds an object to the beginning of the list.
	 * @param obj the object to be added to the list.
	 */
	public void addFirst(E obj) {		
		Node<E> newNode = new Node<E>(obj);
		if(head == null) {
			tail = newNode;
		}
		newNode.next = head;
		head = newNode;
		size++;
	}

	/**
	 * Adds an object to the end of the list.
	 * @param obj the object to be added to the list.
	 */
	public void addLast(E obj) {
		Node<E> newNode = new Node<E>(obj);
		if(head == null) {
			head = tail = newNode;
			size++;
			return;
		}
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	/**
	 * Removes the first Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	public E removeFirst() {
		if(head == null) {
			size = 0;
			return null;
		}
		E temp = head.data;
		if(head == tail) {
			head = tail = null;
			size = 0;
			return temp;
		}
		head = head.next;
		size--;
		return temp;
	}

	/**
	 * Removes the last Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	public E removeLast() {
		Node<E> previous = null;
		Node<E> current = head;

		if(head == null) {
			size = 0;
			return null;
		}
		if(head == tail) {
			return removeFirst();
		}
		while(current.next != null) {
			previous = current;
			current = current.next;
		}
		previous.next = null;
		tail = previous;	
		size--;
		return current.data;
	}

	/**
	 * Returns the first Object in the list, but does not remove it.
	 * Returns null if the list is empty.
	 * @return the object at the beginning of the list.
	 */
	public E peekFirst() {
		E temp = null;
		if(head == null) {
			return null;
		}
		temp = head.data;
		return temp;
	}

	/**
	 * Returns the last Object in the list, but does not remove it. 
	 * Returns null if the list is empty.
	 * @return the object at the end of the list.
	 */
	public E peekLast() {
		E temp = null;
		if(head == null) {
			return null;
		}
		temp = tail.data;
		return temp;
	}

	/**
	 * Return the list to an empty state.
	 * This should generally be a constant time operation.
	 */
	public void makeEmpty() {
		head = null;
		size = 0;
	}

	/**
	 * Test whether the list is empty.
	 * @return true if the list is empty, otherwise false
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Test whether the list is full.
	 * @return true if the list is full, otherwise false
	 */
	public boolean isFull() {
		return false;
	}

	/**
	 * Returns the number of Objects currently in the list.
	 * @return the number of Objects currently in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Test whether the list contains an object.
	 * @param obj The object to look for in the list
	 * @return true if the object is found in the list, false if it is not found
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(E obj) {
		Node<E> temp = head;
		while(temp != null) {
			if(((Comparable<E>)temp.data).compareTo(obj) == 0) {
				return true;
			}
			temp = temp.next;
		}	
		return false;
	}

	class iteratorHelper implements Iterator<E> {
		Node<E> iter;

		public iteratorHelper() {
			iter = head;
		}
		public boolean hasNext() {
			return iter != null;
		}
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			E value = iter.data;
			iter = iter.next;
			return value;
		}
	}
	
	/**
	 * Returns an Iterator of the values in the list, presented in
	 * the same order as the list.
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<E> iterator() {
		return new iteratorHelper(){			
		};
	}

}
