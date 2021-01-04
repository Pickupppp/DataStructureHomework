package com.pickupppp.task3;

import java.util.Arrays;

public class MinHeapSort {

	public static int[] binaryTreeSort(int[] array) {
		MinHeap heap = new MinHeap(array, array.length, array.length);
		System.out.println("堆中元素为:" + heap);
		int result[] = new int[array.length];
		int i = 0;
		while (heap.heapSize() > 0) {
			result[i] = heap.removeMin();
			i++;
		}
		return result;
	}

	public static int[] ternaryTreeSort(int[] array) {
		TernaryTreeHeap heap = new TernaryTreeHeap(array, array.length, array.length);
		System.out.println("堆中元素为:" + heap);
		int result[] = new int[array.length];
		int i = 0;
		while (heap.heapSize() > 0) {
			result[i] = heap.removeMin();
			i++;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] array = { 2, 4, 1, 5, 7, 10, 9, 8, 32, 77, 14 };
		int[] res = binaryTreeSort(array);
		System.out.println("排序结果为:" + Arrays.toString(res));

		int[] array2 = { 2, 4, 1, 5, 7, 10, 9, 8, 32, 77, 14 };
		int[] res2 = ternaryTreeSort(array2);
		System.out.println("排序结果为:" + Arrays.toString(res2));
	}
}
