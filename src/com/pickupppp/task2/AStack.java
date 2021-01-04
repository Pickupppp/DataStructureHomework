package com.pickupppp.task2;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class AStack<E> { // Array based stack class

	private static final int DEFAULT_SIZE = 10;

	private int msize; // Maximum size of stack
	private int top; // Index for top Object
	private Object[] listArray; // Array holding stack Objects

	public AStack() {
		setup(DEFAULT_SIZE);
	}

	public AStack(int size) {
		if (size <= 0) {
			setup(DEFAULT_SIZE);
		} else {
			setup(size);
		}
	}

	private void setup(int capacity) {
		msize = capacity;
		top = 0;
		listArray = new Object[capacity];
	}
	
	private void grow() {
		msize = msize * 2 / 3;
		listArray = Arrays.copyOf(listArray, msize);
	}

	public void clear() {
		top = 0;
	}

	public void push(E it) {
		if (top >= msize) {
			grow();
		}
		listArray[top++] = it;
	}

	public E pop() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("stack is empty");
		}
		return (E) listArray[--top];
	}

	public E topValue() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("stack is empty");
		}
		return (E) listArray[top - 1];
	}

	public boolean isEmpty() {
		return top == 0;
	}
	
	public int size() {
		return top;
	}
	
	public boolean contains(E item) {
		for(int i=0;i<top;i++) {
			if(listArray[i].equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < top; i++) {
			sb.append(listArray[i]);
			sb.append(", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

}
