package com.pickupppp.task3;

public class TernaryTreeHeap {
	private int[] heap; // 存储数组
	private int size; // 最大内存
	private int n; // 堆中元素个数

	public TernaryTreeHeap(int[] h, int num, int max) {
		heap = h;
		n = num;
		size = max;
		buildHeap();
	}

	public int heapSize() {
		return n;
	}

	public boolean isLeaf(int pos) {
		return (pos >= n / 3) && (pos < n);
	}

	public int leftChild(int pos) {
		if (3 * pos + 1 < n) {
			return 3 * pos + 1;
		} else {
			return -1;
		}
	}

	public int middleChid(int pos) {
		if (3 * pos + 2 < n) {
			return 3 * pos + 2;
		} else {
			return -1;
		}
	}

	public int rightChild(int pos) {
		if (3 * pos + 3 < n) {
			return 3 * pos + 3;
		} else {
			return -1;
		}
	}

	public int parent(int pos) {
		if (pos > 0) {
			return (pos - 1) / 3;
		} else {
			return -1;
		}
	}

	public void buildHeap() {
		for (int i = n / 3 - 1; i >= 0; i--) {
			siftDown(i);
		}
	}

	private void siftDown(int pos) {
		if ((pos >= 0) && (pos < n)) {
			while (!isLeaf(pos)) {
				int j = leftChild(pos);
				if ((j != -1) && (j < (n - 1))) {
					if (j < n - 2) {
						if ((heap[j] > heap[j + 1])) {
							j++;
						}
						if ((heap[j] > heap[j + 1])) {
							j++;
						}
					} else {
						if ((heap[j] > heap[j + 1])) {
							j++;
						}

					}
				}
				if (heap[pos] <= heap[j]) {
					return;
				}
				swap(heap, pos, j);
				pos = j;
			}
		}
	}

	public void insert(int val) {
		if (n < size) {
			int curr = n++;
			heap[curr] = val;
			while ((curr != 0) && (heap[curr] < heap[parent(curr)])) {
				swap(heap, curr, parent(curr));
			}
		}
	}

	public int removeMin() {
		if (!(n > 0)) {
			throw new IndexOutOfBoundsException();
		}
		swap(heap, 0, --n);
		if (n != 0) {
			siftDown(0);
		}
		return heap[n];
	}

	public int remove(int pos) {
		if (!((pos >= 0) && (pos < n))) {
			throw new IndexOutOfBoundsException();
		}
		swap(heap, pos, --n);
		if (n != 0) {
			siftDown(pos);
		}
		return heap[n];
	}

	private void swap(int[] heap, int pos1, int pos2) {
		int temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
	}

	@Override
	public String toString() {
		if (n == 0) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < n; i++) {
				sb.append(heap[i]);
				sb.append(" ,");
			}
			sb.replace(sb.length() - 2, sb.length(), "]");
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		int[] array = { 2, 3, 1, 9, 7, 10, 8, 5, 4, 0 };
		TernaryTreeHeap heap = new TernaryTreeHeap(array, array.length - 1, array.length);
		System.out.println(heap);
		System.out.println(heap.isLeaf(5)); // true
		System.out.println(heap.isLeaf(4)); // true
		System.out.println(heap.isLeaf(3)); // true
		System.out.println(heap.isLeaf(2)); // false
		heap.insert(11);
		System.out.println(heap);
		heap.removeMin();
		System.out.println(heap);
		heap.remove(3);
		System.out.println(heap);
		System.out.println(heap.leftChild(1));
		System.out.println(heap.rightChild(1));
		System.out.println(heap.parent(3));
	}
}
