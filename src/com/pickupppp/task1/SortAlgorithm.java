package com.pickupppp.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 直接插入算法、简单选择排序、冒泡排序、快速排序和归并排序
 * 
 * @author Pickupppp
 *
 */
public class SortAlgorithm {

	/**
	 * 记录比较次数
	 */
	public static int compareTimes = 0;

	/**
	 * 记录交换次数
	 */
	public static int swapTimes = 0;

	/**
	 * 记录归并排序移动次数
	 */
	public static int moveTimes = 0;

	/**
	 * 交换数组中两个元素位置
	 * 
	 * @param array 输入的数组
	 * @param pos1  原位置
	 * @param pos2  新位置
	 */
	public static void swap(double[] array, int pos1, int pos2) {
		double temp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = temp;
	}
	
	public static void swap(int[] array, int pos1, int pos2) {
		int temp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = temp;
	}

	/**
	 * 直接插入排序:逐个处理待排序的记录
	 * 
	 * @param array 待排序的数组
	 */
	public static void inssort(double[] array) {
		if (null == array) {
			return;
		}
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				compareTimes++;
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
					swapTimes++;
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 简单选择排序:选择未排序中最小的记录
	 * 
	 * @param array 待排序的数组
	 */
	public static void selsort(double[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int lowIndex = i;
			// 找未排序中最小的记录的数组下标
			for (int j = array.length - 1; j > i; j--) {
				compareTimes++;
				if (array[j] < array[lowIndex]) {
					lowIndex = j;
				}
			}
			if (lowIndex != i) {
				swap(array, lowIndex, i);
				swapTimes++;
			}
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param array 待排数组
	 */
	public static void bubsort(double[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length - 1; j > i; j--) {
				compareTimes++;
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
					swapTimes++;
				}
			}
		}
	}

	/**
	 * 三值选中法找轴值
	 * 
	 * @param array 待排数组
	 * @param l     数组最小索引
	 * @param r     数组最大索引
	 * @return 轴值
	 */
	public static double findPivot(double[] array, int l, int r) {
		int center = (l + r) / 2;
		compareTimes++;
		if (array[l] > array[center]) {
			swap(array, l, center);
			swapTimes++;
		}
		compareTimes++;
		if (array[l] > array[r]) {
			swap(array, l, r);
			swapTimes++;
		}
		compareTimes++;
		if (array[center] > array[r]) {
			swap(array, center, r);
			swapTimes++;
		}
		swap(array, center, r);
		swapTimes++;
		return array[r];
	}
	
	public static int findPivot(int[] array, int l, int r) {
		int center = (l + r) / 2;
		compareTimes++;
		if (array[l] > array[center]) {
			swap(array, l, center);
			swapTimes++;
		}
		compareTimes++;
		if (array[l] > array[r]) {
			swap(array, l, r);
			swapTimes++;
		}
		compareTimes++;
		if (array[center] > array[r]) {
			swap(array, center, r);
			swapTimes++;
		}
		swap(array, center, r);
		swapTimes++;
		return array[r];
	}

	/**
	 * 快速排序
	 * 
	 * @param array 待排数组
	 * @param l     数组最小索引
	 * @param r     数组最大索引
	 */
	public static void qsort(double[] array, int l, int r) {
		if ((r + 1 - l) > 2) {
			// 数组元素大于二进行快速排序
			double pivot = findPivot(array, l, r);
			int start = l;
			int end = r;
			while (start < end) {
				while (start != r && array[++start] < pivot) {
					compareTimes++; // 满足条件进行比较，比较次数增加
				}
				compareTimes++; // 不满足条件也进行了一次比较，次数加一
				while (end != l && array[--end] >= pivot) {
					compareTimes++;
				}
				compareTimes++;
				swap(array, start, end);
				swapTimes++;
			}
			swap(array, start, end);
			swapTimes++;
			swap(array, r, start);
			swapTimes++;
			qsort(array, l, start - 1);
			qsort(array, start + 1, r);
		} else {
			// 数组元素小于等于2,直接比较大小
			if (array[l] > array[r]) {
				swap(array, l, r);
				swapTimes++;
			}
		}
	}
	
	public static void qsort(int[] array, int l, int r) {
		if ((r + 1 - l) > 2) {
			// 数组元素大于二进行快速排序
			double pivot = findPivot(array, l, r);
			int start = l;
			int end = r;
			while (start < end) {
				while (start != r && array[++start] < pivot) {
					compareTimes++; // 满足条件进行比较，比较次数增加
				}
				compareTimes++; // 不满足条件也进行了一次比较，次数加一
				while (end != l && array[--end] >= pivot) {
					compareTimes++;
				}
				compareTimes++;
				swap(array, start, end);
				swapTimes++;
			}
			swap(array, start, end);
			swapTimes++;
			swap(array, r, start);
			swapTimes++;
			qsort(array, l, start - 1);
			qsort(array, start + 1, r);
		} else {
			// 数组元素小于等于2,直接比较大小
			if (array[l] > array[r]) {
				swap(array, l, r);
				swapTimes++;
			}
		}
	}

	/**
	 * 归并排序
	 * 
	 * @param array 待排数组
	 * @param temp  辅助数组
	 * @param l     数组最小索引
	 * @param r     数组最大索引
	 */
	public static void mergeSort(double[] array, double[] temp, int l, int r) {
		int mid = (l + r) / 2;
		if (l == r)
			return; // 只有一个元素
		mergeSort(array, temp, l, mid);
		mergeSort(array, temp, mid + 1, r);
		for (int i = l; i <= r; i++) {
			temp[i] = array[i];
		}
		int i1 = l;
		int i2 = mid + 1;
		for (int curr = l; curr <= r; curr++) {
			// 其中一个数组进行全部排完
			if (i1 > mid) {
				for (; curr <= r; curr++) {
					array[curr] = temp[i2++];
					if (curr != (i2 - 1)) {
						moveTimes++;
					}
				}
				return;
			}
			if (i2 > r) {
				for (; curr <= r; curr++) {
					array[curr] = temp[i1++];
					if (curr != (i1 - 1)) {
						moveTimes++;
					}
				}
				return;
			}

			// 进行比较，比较次数+1
			compareTimes++;
			if (temp[i1] == temp[i2]) {
				array[curr] = temp[i1++];
				// 原位置与当先位置不同，记位一次移动
				if (curr != (i1 - 1)) {
					moveTimes++;
				}
			} else if (temp[i1] > temp[i2]) {
				array[curr] = temp[i2++];
				if (curr != (i2 - 1)) {
					moveTimes++;
				}
			} else {
				array[curr] = temp[i1++];
				if (curr != (i1 - 1)) {
					moveTimes++;
				}
			}
		}
	}

	/**
	 * 寻找中位数算法
	 * 
	 * @param array 待寻找的数组
	 * @param l     数组最小下标
	 * @param r     数组最大下标
	 * @return 中位数的值
	 */
	public static double findMid(double[] array, int l, int r) {
		int index = (0 + array.length - 1) / 2;
		if ((r + 1 - l) > 2) {
			double pivot = findPivot(array, l, r);
			int start = l;
			int end = r;
			while (start < end) {
				while (start != r && array[++start] < pivot)
					;
				while (end != l && array[--end] >= pivot)
					;
				swap(array, start, end);
			}
			swap(array, start, end);
			swap(array, r, start);
			if (start == index) {
				return array[start];
			}
			if (start > index) {
				return findMid(array, l, start - 1);
			} else {
				return findMid(array, start + 1, r);
			}
		} else {
			// 数组元素小于等于2,直接比较大小
			if (array[l] > array[r]) {
				swap(array, l, r);
			}
			return array[index];
		}
	}

	/**
	 * 算法批处理测试
	 * 
	 * @param size 输入数组规模
	 * @param f    要写入的文件
	 */
	public static void batchTest(int size, File f) {
		long sum = 0;
		// 每个算法的每个规模计算3次,取平均值
		for (int i = 0; i < 3; i++) {
			double[] array = new double[size];
			double[] temp = new double[size];
			for (int j = 0; j < size; j++) {
				// array[j] = j + 1;
				array[j] = size - j;
			}
			compareTimes = 0;
			swapTimes = 0;
			moveTimes = 0;
			long start = System.nanoTime();
			// inssort(array);
			// selsort(array);
			// bubsort(array);
			// qsort(array, 0, array.length - 1);
			mergeSort(array, temp, 0, array.length - 1);
			long end = System.nanoTime();
			long time = end - start;
			sum += time;
		}
		long avg = sum / 3;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f, true);
			fos.write(String.valueOf(avg).getBytes());
			fos.write("\t".getBytes());
			fos.write(String.valueOf(compareTimes).getBytes());
			fos.write("\t".getBytes());
			fos.write(String.valueOf(moveTimes / 3).getBytes());
			fos.write("\n".getBytes());
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批处理测试，随机数版
	 * 
	 * @param size 输入数组规模
	 * @param f    写入的文件
	 */
	public static void batchTestRandom(int size, File f) {
		long sum1 = 0;
		// 随机生成规模为size的数组
		double[] array = new double[size];
		double[] temp = new double[size];
		for (int i = 0; i < size; i++) {
			array[i] = (int) (Math.random() * 100000);
		}
		// 每个算法的每个规模计算3次,取平均值
		for (int i = 0; i < 3; i++) {
			// 初始化数组
			double[] temp1 = new double[size];
			for (int j = 0; j < size; j++) {
				temp1[j] = array[j];
			}
			System.out.println(Arrays.toString(temp1));
			compareTimes = 0;
			swapTimes = 0;
			moveTimes = 0;
			long start = System.nanoTime();
			inssort(temp1);
			long end = System.nanoTime();
			System.out.println(Arrays.toString(temp1));
			long time = end - start;
			sum1 += time;
		}
		long avg1 = sum1 / 3;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f, true);
			fos.write(String.valueOf(avg1).getBytes());
			fos.write("\t".getBytes());
			fos.write(String.valueOf(compareTimes).getBytes());
			fos.write("\t".getBytes());
			fos.write(String.valueOf(swapTimes).getBytes());
			fos.write("\n".getBytes());
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		long sum2 = 0;
		for (int i = 0; i < 3; i++) {
			// 初始化数组
			double[] temp1 = new double[size];
			for (int j = 0; j < size; j++) {
				temp1[j] = array[j];
			}
			System.out.println(Arrays.toString(temp1));
			compareTimes = 0;
			swapTimes = 0;
			moveTimes = 0;
			long start = System.nanoTime();
			selsort(temp1);
			long end = System.nanoTime();
			System.out.println(Arrays.toString(temp1));
			long time = end - start;
			sum2 += time;
		}
		long avg2 = sum2 / 3;
		FileOutputStream fos2 = null;
		try {
			fos2 = new FileOutputStream(f, true);
			fos2.write(String.valueOf(avg2).getBytes());
			fos2.write("\t".getBytes());
			fos2.write(String.valueOf(compareTimes).getBytes());
			fos2.write("\t".getBytes());
			fos2.write(String.valueOf(swapTimes).getBytes());
			fos2.write("\n".getBytes());
			fos2.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		long sum3 = 0;
		for (int i = 0; i < 3; i++) {
			// 初始化数组
			double[] temp1 = new double[size];
			for (int j = 0; j < size; j++) {
				temp1[j] = array[j];
			}
			System.out.println(Arrays.toString(temp1));
			compareTimes = 0;
			swapTimes = 0;
			moveTimes = 0;
			long start = System.nanoTime();
			bubsort(temp1);
			long end = System.nanoTime();
			System.out.println(Arrays.toString(temp1));
			long time = end - start;
			sum3 += time;
		}
		long avg3 = sum3 / 3;
		FileOutputStream fos3 = null;
		try {
			fos3 = new FileOutputStream(f, true);
			fos3.write(String.valueOf(avg3).getBytes());
			fos3.write("\t".getBytes());
			fos3.write(String.valueOf(compareTimes).getBytes());
			fos3.write("\t".getBytes());
			fos3.write(String.valueOf(swapTimes).getBytes());
			fos3.write("\n".getBytes());
			fos3.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos3.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		long sum4 = 0;
		for (int i = 0; i < 3; i++) {
			// 初始化数组
			double[] temp1 = new double[size];
			for (int j = 0; j < size; j++) {
				temp1[j] = array[j];
			}
			System.out.println(Arrays.toString(temp1));
			compareTimes = 0;
			swapTimes = 0;
			moveTimes = 0;
			long start = System.nanoTime();
			qsort(temp1, 0, array.length - 1);
			long end = System.nanoTime();
			System.out.println(Arrays.toString(temp1));
			long time = end - start;
			sum4 += time;
		}
		long avg4 = sum4 / 3;
		FileOutputStream fos4 = null;
		try {
			fos4 = new FileOutputStream(f, true);
			fos4.write(String.valueOf(avg4).getBytes());
			fos4.write("\t".getBytes());
			fos4.write(String.valueOf(compareTimes).getBytes());
			fos4.write("\t".getBytes());
			fos4.write(String.valueOf(swapTimes).getBytes());
			fos4.write("\n".getBytes());
			fos4.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos4.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		long sum5 = 0;
		for (int i = 0; i < 3; i++) {
			// 初始化数组
			double[] temp1 = new double[size];
			for (int j = 0; j < size; j++) {
				temp1[j] = array[j];
			}
			System.out.println(Arrays.toString(temp1));
			compareTimes = 0;
			swapTimes = 0;
			moveTimes = 0;
			long start = System.nanoTime();
			mergeSort(temp1, temp, 0, array.length - 1);
			long end = System.nanoTime();
			System.out.println(Arrays.toString(temp1));
			long time = end - start;
			sum5 += time;
		}
		long avg5 = sum5 / 3;
		FileOutputStream fos5 = null;
		try {
			fos5 = new FileOutputStream(f, true);
			fos5.write(String.valueOf(avg5).getBytes());
			fos5.write("\t".getBytes());
			fos5.write(String.valueOf(compareTimes).getBytes());
			fos5.write("\t".getBytes());
			fos5.write(String.valueOf(moveTimes / 3).getBytes());
			fos5.write("\n".getBytes());
			fos5.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos5.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		File f = new File("datas.txt");
		for (int i = 100; i <= 10000; i = i + 100) {
			batchTestRandom(i, f);
		}
//		double[] array = new double[1000];
//		double[] temp = new double[1000];
//		for (int j = 0; j < 1000; j++) {
//			array[j] = (int) (Math.random() * 1000);
//		}
//		compareTimes = 0;
//		swapTimes = 0;
//		moveTimes = 0;
//		long start = System.nanoTime();
//		// inssort(array);
//		// selsort(array);
//		 bubsort(array);
//		// qsort(array, 0, array.length - 1);
//		// mergeSort(array, temp, 0, array.length - 1);
//		long end = System.nanoTime();
//		long time = end - start;
//		System.out.println(Arrays.toString(array));
//		System.out.println("运行时间:" + time + "ns");
//		System.out.println("比较次数:" + compareTimes);
//		System.out.println("交换次数:" + swapTimes);
//		System.out.println("移动次数:" + moveTimes);
	}

}
