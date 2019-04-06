
public class QuickSort {

	public int[] quickSort(int[] array) {
		return quickSort(array, 0, array.length);
	}

	private int[] quickSort(int[] array, int start, int end) {

		// One element array is sorted by default
		if (end - start < 2) {
			return array;
		}
		
		// Partition the full array first and then partition the left and right subarray
		int pivotIndex = partition(array, start, end);
		for(int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
		// The left is already guarantee to be all smaller than the array[pivotIndex]
		// The right is already guarantee to be all bigger than the array[pivotIndex]
		quickSort(array, start, pivotIndex);
		quickSort(array, pivotIndex + 1, end);
		return array;
	}

	private int partition(int[] array, int start, int end) {
		int pivot = array[start];
		int i = start;
		int j = end;
		while (i < j) {
			// NOTE: we need to traverse from right to left first with j
			// NOTE: this is an empty loop traverse right to left
			// until find value smaller than pivot
			while (i < j && array[--j] >= pivot);
			if (i < j)
				array[i] = array[j];

			// NOTE: this is a empty loop traverse left to right
			// until find value bigger than pivot
			while (i < j && array[++i] <= pivot);
			if (i < j)
				array[j] = array[i];

		}
		// at this step i = j
		array[i] = pivot;
		return i;

	}

	public static void main(String[] args) {
		QuickSort test = new QuickSort();
		int[] temp = test.quickSort(new int[] {10,60,40,50,30,20,90,70,80,0});
		for (int i : temp) {
			System.out.print(i + " ");
		}

	}

}
