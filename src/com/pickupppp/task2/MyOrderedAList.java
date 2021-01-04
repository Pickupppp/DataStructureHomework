package com.pickupppp.task2;

public class MyOrderedAList implements MyList {

	private static final int DEFAULT_SIZE = 10; // Default array size

	private int msize; // Maximum size of list
	private int numInList; // Actual number of Objects in list
	private int curr; // Position of current Object
	private int[] listArray; // Array holding list Objects

	public MyOrderedAList() { // Constructor:use default size
		msize = DEFAULT_SIZE;
		numInList = curr = 0;
		listArray = new int[DEFAULT_SIZE];
	}

	public MyOrderedAList(int capacity) { // Constructor:use given size
		if (capacity >= 0) {
			msize = capacity;
			numInList = curr = 0;
			listArray = new int[capacity];
		} else {
			msize = DEFAULT_SIZE;
			numInList = curr = 0;
			listArray = new int[DEFAULT_SIZE];
		}
	}

	@Override
	public boolean isEmpty() { // Return true if list is empty
		return numInList == 0;
	}

	public boolean isInList() { // True if curr is within list
		if (curr == numInList && curr == 0) {
			return true;
		}
		return (curr >= 0) && (curr < numInList);
	}

	private int binarySearch(int[] array, int x, int l, int r) {
		if (r >= l) {
			int mid = (l + r) / 2;
			if (array[mid] == x) {
				return mid;
			} else if (array[mid] > x) {
				return binarySearch(array, x, l, mid - 1);
			} else {
				return binarySearch(array, x, mid + 1, r);
			}
		}
		return -1;
	}

	@Override
	public int search(int x) { // Return index of Object x
		return binarySearch(listArray, x, 0, numInList - 1);
	}

	@Override
	public boolean insert(int x) { // Insert Object x at curr position
		if (numInList >= msize) {
			return false;
		}
		if (!isInList()) {
			return false;
		}
		if (isEmpty()) {
			listArray[0] = x;
			numInList++;
			return true;
		}
		if (x <= listArray[0]) {
			for (int i = numInList; i > 0; i--) {
				listArray[i] = listArray[i - 1];
			}
			listArray[0] = x;
		} else if (x > listArray[numInList - 1]) {
			listArray[numInList] = x;
		} else {
			for (int i = 1; i < numInList; i++) {
				if (listArray[i] >= x && listArray[i - 1] < x) {
					for (int j = numInList; j > i; j--) {
						listArray[j] = listArray[j - 1];
					}
					listArray[i] = x;
					break;
				}
			}
		}
		numInList++;
		return true;
	}

	@Override
	public int length() { // Return length of list
		return numInList;
	}

	@Override
	public boolean delete(int x) { // Remove given Object x
		if (isEmpty()) {
			return false;
		}
		int index = search(x);
		if (index == -1) {
			return false;
		}
		for (int i = index; i < numInList - 1; i++) {
			listArray[i] = listArray[i + 1];
		}
		if (curr == numInList - 1) {
			curr--;
		}
		numInList--;
		return true;
	}

	@Override
	public int delete() { // Remove and return current Object
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		if (!isInList()) {
			throw new IndexOutOfBoundsException("no current element");
		}
		int res = listArray[curr];
		for (int i = curr; i < numInList - 1; i++) {
			listArray[i] = listArray[i + 1];
		}
		if (curr == numInList - 1) {
			curr--;
		}
		numInList--;
		return res;
	}

	@Override
	public int successor(int x) { // Return successor of element x
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		int index = search(x);
		if (index == numInList - 1) {
			return -1;
		}
		return listArray[index + 1];
	}

	@Override
	public int predecessor(int x) { // Return predecessor of element x
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		int index = search(x);
		if (index == 0) {
			return -1;
		}
		return listArray[index - 1];
	}

	@Override
	public int minimum() { // Return the minimum element of list
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		return listArray[0];
	}

	@Override
	public int maximum() { // Return the maximum element of list
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		return listArray[numInList - 1];
	}

	@Override
	public int KthElement(int k) {
		if (k > numInList || k < 1) {
			throw new IndexOutOfBoundsException("k is out of bounds");
		}
		return listArray[numInList - k];
	}

	@Override
	public String toString() {
		if (numInList == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < numInList; i++) {
			sb.append(listArray[i]);
			sb.append(", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	@Override
	public boolean append(int x) {
		return insert(x);
	}

	public static void main(String[] args) {
		MyOrderedAList l1 = new MyOrderedAList();
		l1.insert(2);
		l1.insert(10);
		l1.insert(7);
		l1.insert(1);
		l1.insert(5);
		l1.insert(0);
		l1.insert(4);
		System.out.println("the elements of OAlist:"+l1);
		l1.delete(7);
		l1.delete(5);
		System.out.println("the elements of OAlist:"+l1);
		System.out.print(l1.minimum() + "\t");
		System.out.print(l1.maximum() + "\t");
		System.out.println(l1.KthElement(3));
		System.out.print(l1.successor(10) + "\t");
		System.out.println(l1.predecessor(10));
		System.out.print(l1.successor(0) + "\t");
		System.out.println(l1.predecessor(0));
	}

}
