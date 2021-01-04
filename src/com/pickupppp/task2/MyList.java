package com.pickupppp.task2;

public interface MyList {
	boolean isEmpty();

	int search(int x);

	boolean insert(int x);

	int length();

	boolean delete(int x);
	
	boolean append(int x);
	
	int delete();

	int successor(int x); // 获得该线性表中x元素的直接后继元素

	int predecessor(int x);// 获得线性表中x元素的直接前驱元素

	int minimum(); // 获得该线性表的最小元素

	int maximum(); // 获得该线性表的最大元素

	int KthElement(int k); // 获得线性表中第k大元素，参数为指定的k值的大小
}
