package com.pickupppp.task3;

public class BinNode<K extends Comparable<K>, V> {
	private K key;
	private V value;
	private BinNode<K, V> left;
	private BinNode<K, V> right;

	public BinNode() {
		left = right = null;
	}

	public BinNode(K k, V val) {
		key = k;
		value = val;
	}

	public BinNode(K k, V val, BinNode<K, V> l, BinNode<K, V> r) {
		left = l;
		right = r;
		key = k;
		value = val;
	}

	public V value() {
		return value;
	}

	public V setValue(V v) {
		return value = v;
	}

	public K key() {
		return key;
	}

	public K setKey(K k) {
		return key = k;
	}

	public BinNode<K, V> left() {
		return left;
	}

	public BinNode<K, V> setLeft(BinNode<K, V> p) {
		return left = p;
	}

	public BinNode<K, V> right() {
		return right;
	}

	public BinNode<K, V> setRight(BinNode<K, V> p) {
		return right = p;
	}

	public boolean isLeaf() {
		return (left == null) && (right == null);
	}

}
