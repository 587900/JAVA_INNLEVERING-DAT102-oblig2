package no.hvl.dat102.oppg4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Consumer;

public class Oppgave4 {
	
	public static void main(String[] args) {
		
		IntegerGenerator intgen = new IntegerGenerator();
		
		int size = 100;
		int iterations = 10;
		
		long runtime;
		
		runtime = testSort(size, iterations, Oppgave4::insertionSort, intgen, Integer.class);
		System.out.println("Algorithm: insertionSort spent: " + runtime + "ns (" + (runtime/1e6) + "ms) to sort [" + iterations + "] arrays of size: " + size);
		System.out.println("Algorithm spent on average: " + (runtime / iterations) + "ns (" + (runtime/iterations/1e6) + "ms) to sort arrays of size: " + size);
		System.out.println();

		runtime = testSort(size, iterations, Oppgave4::selectionSort, intgen, Integer.class);
		System.out.println("Algorithm: selectionSort spent: " + runtime + "ns (" + (runtime/1e6) + "ms) to sort [" + iterations + "] arrays of size: " + size);
		System.out.println("Algorithm spent on average: " + (runtime / iterations) + "ns (" + (runtime/iterations/1e6) + "ms) to sort arrays of size: " + size);
		System.out.println();

		runtime = testSort(size, iterations, Oppgave4::bubbleSort, intgen, Integer.class);
		System.out.println("Algorithm: bubbleSort spent: " + runtime + "ns (" + (runtime/1e6) + "ms) to sort [" + iterations + "] arrays of size: " + size);
		System.out.println("Algorithm spent on average: " + (runtime / iterations) + "ns (" + (runtime/iterations/1e6) + "ms) to sort arrays of size: " + size);
		System.out.println();
size=16000; iterations=1000;
		runtime = testSort(size, iterations, Oppgave4::quickSort, intgen, Integer.class);
		System.out.println("Algorithm: quickSort spent: " + runtime + "ns (" + (runtime/1e6) + "ms) to sort [" + iterations + "] arrays of size: " + size);
		System.out.println("Algorithm spent on average: " + (runtime / iterations) + "ns (" + (runtime/iterations/1e6) + "ms) to sort arrays of size: " + size);
		System.out.println();
size=100;iterations=10;
		runtime = testSort(size, iterations, Oppgave4::mergeSort, intgen, Integer.class);
		System.out.println("Algorithm: mergeSort spent: " + runtime + "ns (" + (runtime/1e6) + "ms) to sort [" + iterations + "] arrays of size: " + size);
		System.out.println("Algorithm spent on average: " + (runtime / iterations) + "ns (" + (runtime/iterations/1e6) + "ms) to sort arrays of size: " + size);
		System.out.println();

		runtime = testSort(size, iterations, Oppgave4::specialQuickSort, intgen, Integer.class);
		System.out.println("Algorithm: specialQuickSort spent: " + runtime + "ns (" + (runtime/1e6) + "ms) to sort [" + iterations + "] arrays of size: " + size);
		System.out.println("Algorithm spent on average: " + (runtime / iterations) + "ns (" + (runtime/iterations/1e6) + "ms) to sort arrays of size: " + size);
		System.out.println();
		
		
		//more iterations (2nd parameter) = more accurate reading, however, the operation is O(m*n*log(n)) where n=size and m=iterations
		/*
		System.out.println("Optimizing specialQuickSort...");
		long[] result1 = optimizeQuickSort(100, 10000, intgen, Integer.class, v -> System.out.println("Runtime: " + v[0] + "ns (" + (v[0]/1e6) + "ms) for iteration (MIN_COUNT): " + v[1] + "/100. Size: 100"));
		long[] result2 = optimizeQuickSort(1000, 1000, intgen, Integer.class, v -> System.out.println("Runtime: " + v[0] + "ns (" + (v[0]/1e6) + "ms) for iteration (MIN_COUNT): " + v[1] + "/100. Size: 1000"));
		long[] result3 = optimizeQuickSort(10000, 100, intgen, Integer.class, v -> System.out.println("Runtime: " + v[0] + "ns (" + (v[0]/1e6) + "ms) for iteration (MIN_COUNT): " + v[1] + "/100. Size: 10000"));
		
		System.out.println("Most optimized for size: 100 = " + result1[0] + "ns (" + (result1[0]/1e6) + "ms) at MIN_COUNT: " + result1[1]);
		System.out.println("Most optimized for size: 1000 = " + result2[0] + "ns (" + (result2[0]/1e6) + "ms) at MIN_COUNT: " + result2[1]);
		System.out.println("Most optimized for size: 10000 = " + result3[0] + "ns (" + (result3[0]/1e6) + "ms) at MIN_COUNT: " + result3[1]);
		*/
		
		//print results for excel graphing
		/*
		long[] result;
		result = optimizeQuickSort(100, 10000, intgen, Integer.class, v -> System.out.println(v[0]));
		System.out.println("Minimum: " + result[0] + " " + result[1]);
		result = optimizeQuickSort(1000, 1000, intgen, Integer.class, v -> System.out.println(v[0]));
		System.out.println("Minimum: " + result[0] + " " + result[1]);
		result = optimizeQuickSort(10000, 100, intgen, Integer.class, v -> System.out.println(v[0]));
		System.out.println("Minimum: " + result[0] + " " + result[1]);
		*/
		
		//Everything averages to about MIN_COUNT = 20
		
		System.out.println("Testing quickSort on sorted arrays...");
		size = 512000; iterations = 10;
		runtime = testQuickSortSorted(size, iterations, 5);
		System.out.println("testQuickSortSorted spent " + runtime + "ns (" + (runtime/1e6) + "ms) (" + (runtime/iterations) + "ns (" + (runtime/iterations/1e6) + "ms) on average) to sort [" + iterations + "] arrays of size: " + size);
		
	}
	
	private static <T extends Comparable<T>> long testQuickSortSorted(int size, int iterations, T value) {
		@SuppressWarnings("unchecked")
		T[][] arr = (T[][])Array.newInstance(value.getClass(), iterations, size);
		
		//Fill array (set every element to value)
		for (int i = 0; i < arr.length; ++i) for (int j = 0, l = arr[i].length; j < l; ++j) arr[i][j] = value;
		
		//Measure time
		long start = System.nanoTime();
		for (int i = 0; i < iterations; ++i) quickSort(arr[i]);
		long end = System.nanoTime();
		
		//Verify sorted
		for (int i = 0; i < iterations; ++i) if (!isSorted(arr[i])) throw new IllegalStateException("array not sorted at index: " + i);
		
		return end - start;
	}
	
	private static <T extends Comparable<T>> long[] optimizeQuickSort(int size, int iterations, RandomGenerator<T> generator, Class<T> clazz, Consumer<Long[]> callback) {
		
		long[] runtimes = new long[100];
		for (int i = 1; i < 100; ++i) {		//assume best case is always < 100
			final int x = i;
			long runtime = testSort(size, iterations, arr -> specialQuickSort(arr, x), generator, clazz);
			runtimes[i] = runtime;
			callback.accept(new Long[] { runtime, (long)i });
		}
		
		runtimes[0] = Integer.MAX_VALUE;
		int index = minByIndex(runtimes);
		return new long[] { runtimes[index], index };
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> long testSort(int size, int iterations, Consumer<T[]> sort, RandomGenerator<T> generator, Class<T> clazz) {
		T[][] arr = (T[][])Array.newInstance(clazz, iterations, size);
		
		for (int i = 0; i < iterations; ++i) fillArray(arr[i], generator);
		
		long start = System.nanoTime();
		for (int i = 0; i < iterations; ++i) sort.accept(arr[i]);
		long end = System.nanoTime();
		
		for (int i = 0; i < iterations; ++i) if (!isSorted(arr[i])) throw new IllegalStateException("not sorted. iteration: " + i + "/" + iterations);
		
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
	
	private static <T extends Comparable<T>> void mergeSort(T[] arr) { mergeSort(arr, 0, arr.length-1); }
	private static <T extends Comparable<T>> void mergeSort(T[] arr, int low, int high) {
		if (low >= high) return;
		
		int middle = (low + high) / 2;
		mergeSort(arr, low, middle);
		mergeSort(arr, middle+1, high);
		mergeInPlace(arr, low, middle, high);
	}
	
	//start - pivot, pivot+1 - end
	private static <T extends Comparable<T>> void mergeInPlace(T[] arr, int start, int pivot, int end) {
		@SuppressWarnings("unchecked")
		T[] helper = (T[])Array.newInstance(arr.getClass().getComponentType(), end-start+1);
		
		int i1 = start, i2 = pivot+1;
		int index = 0;
		while (i1 <= pivot && i2 <= end) {
			helper[index++] = (arr[i1].compareTo(arr[i2]) < 0) ? arr[i1++] : arr[i2++];
		}
		while (i1 <= pivot) helper[index++] = arr[i1++];
		while (i2 <= end) helper[index++] = arr[i2++];
		
		for (int i = 0, l = end-start+1; i < l; ++i) arr[start+i] = helper[i];
	}
	
	private static <T extends Comparable<T>> void specialQuickSort(T[] arr) { specialQuickSort(arr, 20); }
	private static <T extends Comparable<T>> void specialQuickSort(T[] arr, int MIN_COUNT) { 
		quickSortMinimum(arr, MIN_COUNT, 0, arr.length-1);
		insertionSort(arr);
	}
	
	private static <T extends Comparable<T>> void quickSortMinimum(T[] arr, int MIN_COUNT, int low, int high) {
		if (high - low + 1 <= MIN_COUNT) return;
		int p = hoarePartition(arr, low, high);
		quickSortMinimum(arr, MIN_COUNT, low, p);
		quickSortMinimum(arr, MIN_COUNT, p+1, high);
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
	
	//find index of minimum value
	private static int minByIndex(long[] arr) {
		if (arr.length == 0) return -1;
		int m = 0;
		for (int i = 0; i < arr.length; ++i) if (arr[i] < arr[m]) m = i;
		return m;
	}
	
	
	
	private static class IntegerGenerator implements RandomGenerator<Integer> {

		@Override
		public Integer getRandom() {
			return (int)(Math.random() * 10000);
		}
		
	}

}
