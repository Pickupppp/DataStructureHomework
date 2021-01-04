package com.pickupppp.task2;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class RadixSort {

	private static int getMaxValue(int[] array) { // Return the maximum number
		if (array.length == 0) {
			return -1;
		}
		int maxValue = array[0];
		for (int value : array) {
			if (value > maxValue) {
				maxValue = value;
			}
		}
		return maxValue;
	}

	private static int getNumLength(long num) {
		if (num == 0) {
			return 1;
		}
		int length = 0;
		for (long temp = num; temp != 0; temp = temp / 10) {
			length++;
		}
		return length;
	}

	public static int[] radixSort(int[] array) {
		int maxDigit = getNumLength(getMaxValue(array));
		int mod = 10;
		int dev = 1;
		AQueue<Integer>[] bucket = new AQueue[10];
		for (int i = 0; i < 10; i++) {
			bucket[i] = new AQueue<Integer>();
		}
		for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
			for (int j = 0; j < array.length; j++) {
				bucket[(array[j] % mod) / dev].enqueue(array[j]);
			}
			int pos = 0;
			for (AQueue<Integer> queue : bucket) {
				while (!queue.isEmpty()) {
					array[pos++] = queue.dequeue();
				}
			}
		}
		return array;
	}

	public static String[] radixSort(String[] array) {
		if (array.length == 0) {
			return null;
		}
		int times = array[0].length();
		AQueue<String>[] bucket = new AQueue[52];
		for (int i = 0; i < 52; i++) {
			bucket[i] = new AQueue<String>();
		}
		for (int i = times - 1; i >= 0; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[j].charAt(i) >= 65 && array[j].charAt(i) <= 90) {
					bucket[array[j].charAt(i) - 65].enqueue(array[j]);
				} else {
					bucket[array[j].charAt(i) - 71].enqueue(array[j]);
				}
			}
			int pos = 0;
			for (AQueue<String> queue : bucket) {
				while (!queue.isEmpty()) {
					array[pos++] = queue.dequeue();
				}
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 21, 12, 322, 44, 123, 2312, 765, 56 };
		radixSort(arr);
		System.out.println(Arrays.toString(arr));

		String[] arr1 = { "abc", "bde", "fad", "abd", "bef", "fdd", "abe" };
		radixSort(arr1);
		System.out.println(Arrays.toString(arr1));
	}
}
