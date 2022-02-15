package com.intigral.assignment;

import java.util.Map;
import java.util.TreeMap;

public class Application {

	static int[] mergeArrays(int array1[], int array2[]) {

		System.out.println("Start Merging");

		if (array1 == null || array1.length < 1) {
			return array2;
		}

		if (array2 == null || array2.length < 1) {
			return array1;
		}

		int outputArray[] = null;

		Map<Integer, Integer> map = new TreeMap<>();

		setUpSortedArray(array1, map);
		setUpSortedArray(array2, map);

		int outputArrayIdx = 0;
		outputArray = new int[array1.length + array2.length];
		for (Map.Entry<Integer, Integer> sortedEntries : map.entrySet()) {
			for (int i = 0; i < sortedEntries.getValue(); i++) { // number of times integer appeared
				outputArray[outputArrayIdx++] = sortedEntries.getKey();
			}
		}

		return outputArray;
	}

	static void setUpSortedArray(int array[], Map<Integer, Integer> map) {
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				Integer intt = map.get(array[i]);
				intt = intt == null ? 1 : (intt + 1);
				map.put(array[i], intt);
			}
		}
	}

	static void printArray(int array[], String msgPrefix) {
		if (array != null && array.length > 0) {
			System.out.print(msgPrefix + "[");
			for (int i : array) {
				System.out.print(i + ",");
			}
			System.out.println("]");
		} else {
			System.out.println("null/empty array");
		}
	}

	public static void main(String[] args) {

		int[] array1 = null;
		int[] array2 = null;
		printArray(array1, "");
		printArray(array2, "");
		printArray(mergeArrays(array1, array2), "OUTPUT");
		System.out.println("=================================");

		array1 = new int[] { 7, 9, 11 };

		printArray(array1, "");
		printArray(array2, "");
		printArray(mergeArrays(array1, array2), "OUTPUT");
		System.out.println("=================================");

		array2 = new int[] { 10, 11, 20 };
		array1 = null;

		printArray(array1, "");
		printArray(array2, "");
		printArray(mergeArrays(array1, array2), "OUTPUT");
		System.out.println("=================================");

		array1 = new int[] { 1, 2, 3, 4, 5 };
		array2 = new int[] { 4, 6, 40 };

		printArray(array1, "");
		printArray(array2, "");
		printArray(mergeArrays(array1, array2), "OUTPUT");
		System.out.println("=================================");

		array1 = new int[] { 2, 4, 6, 8, 10 };
		array2 = new int[] { 1, 3, 5, 7 };

		printArray(array1, "");
		printArray(array2, "");
		printArray(mergeArrays(array1, array2), "OUTPUT");
		System.out.println("=================================");

		array1 = new int[] { 1, 7, 3, 2, 4 };
		array2 = new int[] { 25, 90, 3, 4 };

		printArray(array1, "");
		printArray(array2, "");
		printArray(mergeArrays(array1, array2), "OUTPUT");
		System.out.println("=================================");
	}

}
