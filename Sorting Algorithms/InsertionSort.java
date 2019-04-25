
public class InsertionSort {
	public int[] insertionSort(int[] array) {
		
		// unsorted Index will start at 1 and traverse right
		for(int unsortedIndex = 1; unsortedIndex < array.length; unsortedIndex++) {
			int swapElement = array[unsortedIndex];		
			int i;
			for(i = unsortedIndex; i > 0 && array[i -1 ] > swapElement; i--) {
					array[i] = array[i - 1];
			}		
			array[i] = swapElement;
			printArray(array, unsortedIndex);
		}
		for(Integer i: array)
			System.out.print(i + " ");
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
		sort.insertionSort(new int[] {20, 80, 10, 50, 70 });		
	}

}
