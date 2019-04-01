
public class InsertionSort {
	public int[] insertionSort(int[] array) {
		
		// unsorted Index will start at 1 and traverse right
		for(int unsortedIndex = 1; unsortedIndex < array.length; unsortedIndex++) {
			array = insertionSort(array, unsortedIndex);
			printArray(array, unsortedIndex);
		}
		return array;
	}
	
	private int[] insertionSort(int[] array, int unsortedIndex) {
		
		int swapIndex = unsortedIndex;
		// Traverse left to find the value smaller than array[unsortedIndex]
		for(int i = unsortedIndex; i >= 0; i--) {
			if(array[i] > array[swapIndex])
				swapIndex = i;
		}
		
		return swap(array, unsortedIndex, swapIndex);
	}
	
	
	private int[] swap(int[] array, int index1, int index2) {
		int temp = array[index2];
		array[index2] = array[index1];
		array[index1] = temp;
		return array;

	}
	
	private void printArray(int[] array, int unsortedIndex) {
		for(int i = 0; i < array.length; i ++) {
			System.out.print(array[i] + " ");
			if(i == unsortedIndex)
				System.out.print("* ");
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		InsertionSort sort = new InsertionSort();
		sort.insertionSort(new int[] { 80, 10, 50, 70 });		
	}

}
