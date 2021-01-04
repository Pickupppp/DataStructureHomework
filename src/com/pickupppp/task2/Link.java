package com.pickupppp.task2;

import java.util.LinkedList;
import java.util.List;

public class Link {
	private int element; // value for this node
	private Link next; // Pointer to next node in list

	Link(int value, Link nextval) {
		element = value;
		next = nextval;
	}

	Link(int value) {
		element = value;
	}

	Link next() {
		return next;
	}

	Link setNext(Link nextval) {
		next = nextval;
		return next;
	}

	int element() {
		return element;
	}

	int setElement(int value) {
		element = value;
		return element;
	}

	List<String> llist = new LinkedList<>();
}
