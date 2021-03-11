package no.hvl.dat102.oppg4;

import java.util.Arrays;
import java.util.function.Consumer;

public class Old {

	public static void main(String[] args) {
		int[] arr;
		
		for (int i = 0; i < 100; ++i) {
			arr = new int[100];
			fillArray(arr);
			quickSort(arr);
			if (!isSorted(arr)) throw new IllegalStateException("not sorted. index: " + i);
		}
		
		System.out.println("tested 100 times");
		
		
		arr = new int[10];
		
		fillArray(arr);
		printArray(arr);
		quickSort(arr);
		printArray(arr);
		
	}
	
	private static void insertionSort(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			for (int j = i; j > 0 && arr[j-1] > arr[j]; --j) {
				swap(arr, j-1, j);
			}
		}
	}
	
	private static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			int minIndex = i;
			for (int j = i+1; j < arr.length; ++j) {
				if (arr[j] < arr[minIndex]) minIndex = j;
			}
			if (minIndex != i) swap(arr, i, minIndex);	//trenger ikke 'swap' med oss selv
		}
	}
	
	private static void bubbleSort(int[] arr) {
		int n = arr.length;
		while (n > 0) {
			--n;
			for (int i = 0; i < n; ++i) {
				if (arr[i] > arr[i+1]) swap(arr, i, i+1);
			}
		}
	}
	
	private static void quickSort(int[] arr) { quickSort(arr, 0, arr.length-1); }
	private static void quickSort(int[] arr, int low, int high) {
		if (low >= high) return;
		int p = hoarePartition(arr, low, high);
		quickSort(arr, low, p);
		quickSort(arr, p+1, high);
	}
	
	//quickSort(arr, low, p-1) + quickSort(arr, p+1, high)
	private static int lomutoPartition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low;
		for (int j = low; j <= high; ++j) {
			if (arr[j] < pivot) swap(arr, i++, j);
		}
		swap(arr, i, high);
		return i;
	}
	
	//quickSort(arr, low, p) + quickSort(arr, p+1, high)
	private static int hoarePartition(int[] arr, int low, int high) {
		int pivot = arr[(high + low) / 2];
		int i = low - 1;
		int j = high + 1;
		while (true) {
			while (arr[++i] < pivot);
			while (arr[--j] > pivot);
			if (i >= j) return j;
			swap(arr, i, j);
		}
	}
	
	private static void fillArray(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = (int)(Math.random() * 10000);
		}
	}
	
	private static void printArray(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}
	
	private static void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	private static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length-1; ++i) {
			if (arr[i] > arr[i+1]) return false;
		}
		return true;
	}
	
}
