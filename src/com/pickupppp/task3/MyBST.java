package com.pickupppp.task3;

import java.io.IOException;
import java.io.PrintWriter;

public class MyBST<K extends Comparable<K>, V> implements BST<K, V> {

	private int nodesNum = 0;

	private BinNode<K, V> root;

	private V deletedValue = null;

	public MyBST() {
		root = null;
	}

	@Override
	public void insert(K key, V value) {
		root = inserthelp(root, key, value);
	}

	private BinNode<K, V> inserthelp(BinNode<K, V> root, K key, V value) {
		if (null == root) {
			if (null != key && null != value) {
				nodesNum++;
				return new BinNode<K, V>(key, value);
			} else {
				return null;
			}
		}
		if (root.key().compareTo(key) > 0) {
			root.setLeft(inserthelp(root.left(), key, value));
		} else if (root.key().compareTo(key) < 0) {
			root.setRight(inserthelp(root.right(), key, value));
		} else {
			root.setValue(value);
		}
		return root;
	}

	@Override
	public V remove(K key) {
		deletedValue = null;
		root = removehelp(root, key);
		if (null != deletedValue) {
			nodesNum--;
		}
		return deletedValue;
	}

	private BinNode<K, V> removehelp(BinNode<K, V> root, K key) {
		if (null == key) {
			return null;
		}
		if (null == root) {
			return null;
		}
		if (root.key().compareTo(key) > 0) {
			root.setLeft(removehelp(root.left(), key));
		} else if (root.key().compareTo(key) < 0) {
			root.setRight(removehelp(root.right(), key));
		} else {
			deletedValue = (V) root.value();
			if (null == root.left() && null != root.right()) {
				root = root.right();
			} else if (null == root.right() && null != root.left()) {
				root = root.left();
			} else if (null == root.left() && null == root.right()) {
				root = null;
			} else {
				root.setKey(getmin(root.right()).key());
				root.setValue(getmin(root.right()).value());
				root.setRight(deletemin(root.right()));
			}
		}
		return root;
	}

	private BinNode<K, V> deletemin(BinNode<K, V> root) {
		if (null == root.left()) {
			return root.right();
		} else {
			root.setLeft(deletemin(root.left()));
			return root;
		}
	}

	private BinNode<K, V> getmin(BinNode<K, V> root) {
		if (null == root.left()) {
			return root;
		} else {
			return getmin(root.left());
		}
	}

	@Override
	public V search(K key) {
		return (V) searchhelp(root, key);
	}

	private V searchhelp(BinNode<K, V> root, K key) {
		if (root.key().compareTo(key) > 0) {
			if (root.left() != null) {
				return searchhelp(root.left(), key);
			} else {
				return null;
			}
		} else if (root.key().compareTo(key) < 0) {
			if (root.right() != null) {
				return searchhelp(root.right(), key);
			} else {
				return null;
			}
		} else {
			return (V) root.value();
		}
	}

	@Override
	public boolean update(K key, V value) {
		return updatehelp(root, key, value);
	}

	public boolean updatehelp(BinNode<K, V> root, K key, V value) {
		if (null != key && null != value && null != root) {
			if (root.key().compareTo(key) > 0) {
				return updatehelp(root.left(), key, value);
			} else if (root.key().compareTo(key) < 0) {
				return updatehelp(root.right(), key, value);
			} else {
				root.setValue(value);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean isEmpty() {
		return null == root;
	}

	@Override
	public void clear() {
		root = null;
		nodesNum = 0;
	}

	@Override
	public void showStructure(PrintWriter pw) throws IOException {
		pw.println("--------------------");
		if (isEmpty()) {
			pw.println("The BST is empty.");
		} else {
			pw.println("There are " + nodesNum + " nodes in this BST.");
			pw.println("The height of this BST is " + getHeight(root) + ".");
		}
		pw.println("--------------------");
		pw.flush();
	}

	private int getHeight(BinNode<K, V> root) {
		if (null == root) {
			return 0;
		}
		int leftHeight = getHeight(root.left());
		int rightHeight = getHeight(root.right());
		return Math.max(leftHeight, rightHeight) + 1;
	}

	@Override
	public void printInorder(PrintWriter pw) throws IOException {
		if (null == root) {
			pw.println("[]");
		} else {
			printHelp(root, pw);
		}
	}

	private void printHelp(BinNode<K, V> root, PrintWriter pw) {
		if (null == root) {
			return;
		}
		printHelp(root.left(), pw);
		pw.println("[" + root.key() + " -- < " + root.value() + " >]");
		printHelp(root.right(), pw);
		pw.flush();
	}

	public static void main(String[] args) {
		MyBST<String, String> tree = new MyBST<String, String>();
		// 测试插入
		tree.insert("37", "37");
		tree.insert("24", "24");
		tree.insert("42", "42");
		tree.insert("7", "7");
		tree.insert("32", "32");
		tree.insert("40", "40");
		tree.insert("42", "42");
		tree.insert("2", "2");
		tree.insert("120", "120");
		// 测试删除
		System.out.println(tree.remove("40"));
		System.out.println(tree.remove("32"));
		// 测试查找
		System.out.println(tree.search("24"));
		System.out.println(tree.search("32"));
		// 测试更新
		System.out.println(tree.update("24", "244"));
		System.out.println(tree.search("24"));
		System.out.println(tree.update("32", "244"));
		// 测试非空
		System.out.println(tree.isEmpty());
		try {
			tree.showStructure(new PrintWriter(System.out));
			tree.printInorder(new PrintWriter(System.out));
			// 测试清除
			tree.clear();
			System.out.println(tree.isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
