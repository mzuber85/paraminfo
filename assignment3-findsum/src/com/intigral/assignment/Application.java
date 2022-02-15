package com.intigral.assignment;

public class Application {

	/**
	 * @param array
	 * @param x
	 * @return
	 */
	static int[] findPair(int array[], int x) {

		System.out.println("Start finding sum closest:" + x);

		if (array == null || array.length < 0)
			return array;

		int sum = Integer.MAX_VALUE;
		int n = array.length;

		int l = 0, r = n - 1;

		int targetIdx1 = 0, targetIdx2 = 0;

		while (l < r) {
			int lrsum = array[l] + array[r];
			int absSum = Math.abs(lrsum-x);
			if(absSum < sum) {
				sum = absSum;
				targetIdx1 = l;
				targetIdx2 = r;
			}
			if (sum == 0) {
				break;
			}
			if(lrsum > x)
				r--;
			else
				l++;
		}
		if (sum != 0) {
			System.out.println("No Pair in array has exact sum:" + x + " closest pair is " + array[targetIdx1]
					+ ", at index [" + targetIdx1 + "] AND " + array[targetIdx2] + ", at index [" + targetIdx2 + "]");
		}
		return new int[] { array[targetIdx1], array[targetIdx2] };

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

		int[] array = null;
		printArray(array, "INPUT");
		printArray(findPair(array, 2), "OUTPUT");
		System.out.println("=================================");

		array = new int[] { 7, 9, 11 };

		printArray(array, "INPUT");
		printArray(findPair(array, 20), "OUTPUT");
		System.out.println("=================================");

		array = new int[] { 2, 3, 10, 11, 20 };

		printArray(array, "");
		printArray(findPair(array, 14), "OUTPUT");
		System.out.println("=================================");

		array = new int[] { 1, 5, 9, 11, 12, 19 };

		printArray(array, "");
		printArray(findPair(array, 11), "OUTPUT");
		System.out.println("=================================");
		
		array = new int[] { 1, 3, 5, 10, 11, 19 };

		printArray(array, "");
		printArray(findPair(array, 7), "OUTPUT");
		System.out.println("=================================");

	}

}
