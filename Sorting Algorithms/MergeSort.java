import java.util.Arrays;

public class MergeSort {
	public static int[] array = { 80, 10, 50, 70 };

	public int[] mergeSort(int[] array) {
		return mergeSort(array, 0, array.length);
	}

	public int[] mergeSort(int[] array, int start, int end) {
		if (end - start < 2)
			return array;

		int mid = (start + end) / 2;
		mergeSort(array, start, mid);
		mergeSort(array, mid, end);
		merge(array, start, mid, end);
		return array;
	}

	public void merge(int[] array, int start, int mid, int end) {

		// If the array[mid - 1] is smaller than array[mid], this subarray already sorted so return
		if (array[mid - 1] < array[mid]) {
			return;
		}
		
		// i is to traverse the left array
		// j is to traverse the right array
		// tempIndex to keep track of the index in the tempArray
		int i = start;
		int j = mid;
		int tempIndex = 0;
		// the temp array with the size of end - start and sorted as traverse
		int[] temp = new int[end - start];

		// break the while loop when either the left or right subArray is all traverse
		// this will left either the left or right array to have 1 extra element
		while (i < mid && j < end) {
			// make sure array[i] <= array[j] because Merge Sort is stable
			temp[tempIndex++] = array[i] <= array[j] ? array[i++] : array[j++];
		}
		
		// If the extra element is in the left subarray, we need to copy it back into the original array
		// If the extra element is in the right subarray, It already be the biggest number so we don't need to copy it back because it is already biggest
		// copy start from i, which is the last elements in the left subarray and start at start+tempIndex which is the element the last element has been sorted in
		// the tempArray. the length of this first copy is mid - 1 which if the leftarray is fullly used i = mid which no copy here.
		System.arraycopy(array, i, array, start + tempIndex, mid - i);
		System.arraycopy(temp, 0, array, start, tempIndex);
	}

	public void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}

	public void printArray(int[] array, int start, int mid, int end) {
		for (int i = start; i < end; i++) {
			if (i == mid)
				System.out.print("* ");
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MergeSort test = new MergeSort();
		int[] temp = test.mergeSort(new int[] { 80, 10, 50, 70 });
		test.printArray(temp);
	}
}
