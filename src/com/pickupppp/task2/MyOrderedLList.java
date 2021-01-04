package com.pickupppp.task2;

public class MyOrderedLList implements MyList {

	private Link head; // Pointer to list head
	private Link dummy; // Pointer to list first
	private Link curr; // Pointer to the predecessor of current node
	private int size; // Size of list

	public MyOrderedLList() { // Constructor
		dummy = new Link(0, null);
		curr = dummy;
		head = dummy.next();
		size = 0;
	}

	public MyOrderedLList(int capacity) { // Ignore the capacity
		dummy = new Link(0, null);
		curr = dummy;
		head = dummy.next();
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int search(int x) {
		if (!isEmpty()) {
			int index = 0;
			Link temp = head;
			while (temp != null) {
				if (temp.element() == x) {
					return index;
				}
				temp = temp.next();
				index++;
			}
		}
		return -1;
	}

	@Override
	public boolean insert(int x) {
		Link node = new Link(x, null);
		if (isEmpty()) {
			dummy.setNext(node);
			head = dummy.next();
		} else {
			Link temp = dummy;
			while (temp.next() != null) {
				if(head.element()>=x) {
					node.setNext(head);
					dummy.setNext(node);
					break;
				}
				if (temp.next().element() <= x) {
					if (temp.next().next() != null && temp.next().next().element() > x) {
						node.setNext(temp.next().next());
						temp.next().setNext(node);
						break;
					}
					if (temp.next().next() == null) {
						temp.next().setNext(node);
						break;
					}
				}
				temp = temp.next();
			}
		}
		head = dummy.next();
		size++;
		return true;
	}

	@Override
	public int length() {
		return size;
	}

	@Override
	public boolean delete(int x) {
		if (isEmpty()) {
			return false;
		}
		Link node = dummy;
		while (node.next() != null) {
			if (node.next().element() == x) {
				node.setNext(node.next().next());
				size--;
				return true;
			}
			node = node.next();
		}
		return false;
	}

	@Override
	public boolean append(int x) {
		return insert(x);
	}

	@Override
	public int delete() {
		if (curr.next() == null) {
			return -1;
		}
		int value = curr.next().element();
		curr.setNext(curr.next().next());
		size--;
		head = dummy.next();
		return value;
	}

	@Override
	public int successor(int x) {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		Link temp = dummy;
		while (temp.next() != null) {
			if (temp.next().element() == x) {
				if (temp.next().next() == null) {
					return -1;
				} else {
					return temp.next().next().element();
				}
			}
			temp = temp.next();
		}
		return -1;
	}

	@Override
	public int predecessor(int x) {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		Link temp = dummy;
		while (temp.next() != null) {
			if (temp.next().element() == x) {
				if (temp == dummy) {
					return -1;
				} else {
					return temp.element();
				}
			}
			temp = temp.next();
		}
		return -1;
	}

	@Override
	public int minimum() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		return head.element();
	}

	@Override
	public int maximum() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		Link temp = head;
		int res = temp.element();
		while (temp.next() != null) {
			if (temp.next().element() > res) {
				res = temp.next().element();
			}
			temp = temp.next();
		}
		return res;
	}

	@Override
	public int KthElement(int k) {
		if (k < 1 || k >size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("list is empty");
		}
		int count = size - k;
		Link temp = head;
		while (count > 0) {
			temp = temp.next();
			count--;
		}
		return temp.element();
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Link temp = head;
		while (temp != null) {
			sb.append(temp.element());
			sb.append(", ");
			temp = temp.next();
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}

	public static void main(String[] args) {
		MyOrderedLList l1 = new MyOrderedLList();
		l1.insert(2);
		l1.insert(10);
		l1.insert(7);
		l1.insert(1);
		l1.insert(5);
		l1.insert(0);
		l1.insert(4);
		System.out.println("the elements of OLlist:" + l1);
		l1.delete(7);
		l1.delete(5);
		System.out.println("the elements of OLlist:" + l1);
		System.out.print(l1.minimum() + "\t");
		System.out.print(l1.maximum() + "\t");
		System.out.println(l1.KthElement(3));
		System.out.print(l1.successor(10) + "\t");
		System.out.println(l1.predecessor(10));
		System.out.print(l1.successor(0) + "\t");
		System.out.println(l1.predecessor(0));
	}
}
