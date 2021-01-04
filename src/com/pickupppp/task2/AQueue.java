package com.pickupppp.task2;

@SuppressWarnings("unchecked")
public class AQueue<E> { // Array based queue class

	private static final int DEFAULT_SIZE = 10;

	private int msize; // Maximum size of queue
	private int front; // Index prior to front item
	private int rear; // Index of rear item
	private Object[] listArray; // Array holding Objects

	public AQueue() {
		setup(DEFAULT_SIZE);
	}

	public AQueue(int size) {
		setup(size);
	}

	private void setup(int capacity) {
		msize = capacity + 1;
		front = rear = 0;
		listArray = new Object[capacity + 1];
	}

	public boolean isEmpty() {
		return front == rear;
	}

	private boolean isFull() {
		return (rear + 1) % msize == front;
	}

	public void clear() {
		front = rear = 0;
	}

	public void enqueue(E item) {
		if (!isFull()) {
			rear = (rear + 1) % msize;
			listArray[rear] = item;
		}
	}

	public E dequeue() {
		if (!isEmpty()) {
			front = (front + 1) % msize;
			return (E) listArray[front];
		}
		return null;
	}

	public E firstValue() {
		if (!isEmpty()) {
			return (E) listArray[(front + 1) % msize];
		}
		return null;
	}
}
