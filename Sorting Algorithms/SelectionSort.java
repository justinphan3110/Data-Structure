
public class SelectionSort {
	public int[] selectionSort(int[] array) {
		// Return null if the array is empty
		if (array.length == 0)
			return null;
		
		// The lastUnsortedIndex start at the last position and will decrement after each loop
		for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex >= 0; lastUnsortedIndex--) {
			
			// Traverse the array until the lastUnsortedIndex
			int largestIndex = array[0];
			for(int i = 0; i <  lastUnsortedIndex; i++) {
				if(array[i] > array[largestIndex])
					largestIndex = i;
			}		
			swap(array, largestIndex, lastUnsortedIndex);
			printArray(array, lastUnsortedIndex);
		}
		return array;
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
		sort.selectionSort(new int[] {5, 120, 10, 60, 40, 50, 30, 20, 90, 70, 80, 0});
	}

}
