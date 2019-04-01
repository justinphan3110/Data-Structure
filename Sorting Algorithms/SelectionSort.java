import java.util.Arrays;

public class SelectionSort {
	public int[] selectionSort(int[] array) {
		// Return null if the array is empty
		if (array.length == 0)
			return null;
		
		// The lastUnsortedIndex start at the last position and will decrement after each loop
		for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex >= 0; lastUnsortedIndex--) {
			// Traverse the array until the lastUnsortedIndex
			array = selectionSort(array, lastUnsortedIndex);
			printArray(array, lastUnsortedIndex);
		}
		return array;
	}

	private int[] selectionSort(int[] array, int lastUnsortedIndex) {
		int largestValue = array[0];
		int largestValueIndex = 0;
		// Traverse the array until the lastUnsortedIndex and update array[i] as the biggestValue it get
		for (int i = 1; i <= lastUnsortedIndex; i++) {
			if (array[i] > largestValue) {
				largestValue = array[i];
				largestValueIndex = i;
			}
		}
		
		// Swap the array[lastUnsortedIndex] with the biggestValue until its place 
		return swap(array, largestValueIndex, lastUnsortedIndex);
	}

	private void printArray(int[] array, int lastUnsortedIndex) {
		// Print out the array at each loop with the "*" after the lastUnsortedIndex
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
			if (i == lastUnsortedIndex)
				System.out.print("* ");
		}
		System.out.println();
	}

	private int[] swap(int[] array, int index1, int index2) {
		int temp = array[index2];
		array[index2] = array[index1];
		array[index1] = temp;
		return array;

	}

	public static void main(String[] args) {
		SelectionSort sort = new SelectionSort();
		sort.selectionSort(new int[] { 80, 10, 50, 70 });
	}

}
