package no.hvl.dat102.oppg4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Consumer;

public class Oppgave4 {
	
	public static void main(String[] args) {
		
		IntegerGenerator intgen = new IntegerGenerator();
		
		long runtime1 = testSort(100, arr -> insertionSort(arr), intgen, Integer.class);
		long runtime2 = testSort(100, arr -> selectionSort(arr), intgen, Integer.class);
		long runtime3 = testSort(100, arr -> bubbleSort(arr), intgen, Integer.class);
		long runtime4 = testSort(100, arr -> quickSort(arr), intgen, Integer.class);
		//long runtime5 = testSort(100, arr -> mergeSort(arr), intgen, Integer.class);
		
		System.out.println(runtime1 + " " + runtime2 + " " + runtime3 + " " + runtime4);
		
	}
	
	private static <T extends Comparable<T>> long testSort(int amounts, Consumer<T[]> sort, RandomGenerator<T> generator, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		T[] arr = (T[])Array.newInstance(clazz, amounts);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < amounts; ++i) {
			fillArray(arr, generator);
			sort.accept(arr);
			if (!isSorted(arr)) throw new IllegalStateException("not sorted. iteration: " + i + "/" + amounts);
		}
		long end = System.currentTimeMillis();
		
		return end - start;
	}
	
	private static <T extends Comparable<T>> void insertionSort(T[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			for (int j = i; j > 0 && arr[j-1].compareTo(arr[j]) > 0; --j) {
				swap(arr, j-1, j);
			}
		}
	}
	
	private static <T extends Comparable<T>> void selectionSort(T[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			int minIndex = i;
			for (int j = i+1; j < arr.length; ++j) {
				if (arr[j].compareTo(arr[minIndex]) < 0) minIndex = j;
			}
			if (minIndex != i) swap(arr, i, minIndex);	//trenger ikke 'swap' med oss selv
		}
	}
	
	private static <T extends Comparable<T>> void bubbleSort(T[] arr) {
		int n = arr.length;
		while (n > 0) {
			--n;
			for (int i = 0; i < n; ++i) {
				if (arr[i].compareTo(arr[i+1]) > 0) swap(arr, i, i+1);
			}
		}
	}
	
	private static <T extends Comparable<T>> void quickSort(T[] arr) { quickSort(arr, 0, arr.length-1); }
	private static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
		if (low >= high) return;
		int p = hoarePartition(arr, low, high);
		quickSort(arr, low, p);
		quickSort(arr, p+1, high);
	}
	
	//quickSort(arr, low, p-1) + quickSort(arr, p+1, high)
	private static <T extends Comparable<T>> int lomutoPartition(T[] arr, int low, int high) {
		T pivot = arr[high];
		int i = low;
		for (int j = low; j <= high; ++j) {
			if (arr[j].compareTo(pivot) < 0) swap(arr, i++, j);
		}
		swap(arr, i, high);
		return i;
	}
	
	//quickSort(arr, low, p) + quickSort(arr, p+1, high)
	private static <T extends Comparable<T>> int hoarePartition(T[] arr, int low, int high) {
		T pivot = arr[(high + low) / 2];
		int i = low - 1;
		int j = high + 1;
		while (true) {
			while (arr[++i].compareTo(pivot) < 0);
			while (arr[--j].compareTo(pivot) > 0);
			if (i >= j) return j;
			swap(arr, i, j);
		}
	}
	
	private static <T> void fillArray(T[] arr, RandomGenerator<T> generator) {
		for (int i = 0; i < arr.length; ++i) { arr[i] = generator.getRandom(); }
	}
	
	private static <T> void printArray(T[] arr) {
		System.out.println(Arrays.toString(arr));
	}
	
	private static <T> void swap(T[] arr, int i1, int i2) {
		T temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	private static <T extends Comparable<T>> boolean isSorted(T[] arr) {
		for (int i = 0; i < arr.length-1; ++i) {
			if (arr[i].compareTo(arr[i+1]) > 0) return false;
		}
		return true;
	}
	
	
	
	private static class IntegerGenerator implements RandomGenerator<Integer> {

		@Override
		public Integer getRandom() {
			return (int)(Math.random() * 10000);
		}
		
	}

}
