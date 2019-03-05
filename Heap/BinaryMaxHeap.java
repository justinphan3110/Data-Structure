
public class BinaryMaxHeap {
	// the array that hold all the value
	private int array[];

	// Last Position to keep track where to add or swap (in Delete);
	private int lastPosition;

	public static void main(String[] args) {
		BinaryMaxHeap heap = new BinaryMaxHeap(5);
		heap.add(1);
		heap.add(3);
		heap.add(2);
		heap.add(4);
		heap.add(6);
		heap.add(7);
		heap.add(8);
		heap.add(5);
		heap.removeMax();
		heap.removeMax();
		heap.removeMax();
		heap.removeMax();
		heap.removeMax();
		heap.removeMax();
		heap.printHeap();
	}

	// Constructor receive the array size
	public BinaryMaxHeap(int size) {
		this.array = new int[size];
		this.lastPosition = -1;
	}

	// Swap
	public void swap(int from, int to) {
		int temp = array[from];
		array[from] = array[to];
		array[to] = temp;

	}

	// Add the value to the next available lastPosition
	public void add(int value) {
		// Check if the current array is full, 
		// if full, create a new array and copy everything to it
		if (lastPosition >= (this.array.length-1)) {
			this.array = this.newArray();
			
		}
		array[++lastPosition] = value;
		this.heapifyUp(lastPosition);
	}

	// Heapify up from the lastPosition to maintain max heap property
	private void heapifyUp(int position) {
		if (position == 0) {
			return;
		}
		// Find the parent position to check and swap up
		int parentPosition = (int) ((position - 1) / 2);
		if (array[parentPosition] < array[position]) {
			swap(position, parentPosition);
			heapifyUp(parentPosition);
		}
	}

	private int[] newArray() {
		int newLength = array.length * 2;
		int[] newArray = new int[newLength];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	// Remove and return the root of the Heap
	public int removeMax() {
		int temp = array[0];
		swap(0, this.lastPosition--);
		this.heapifyDown(0);
		return temp;
	}

	// Heapify down form the root to make sure the the heap maintain MaxHeap
	// property
	private void heapifyDown(int parent) {
		int left = 2 * parent + 1;
		int right = 2 * parent + 2;

		// Check if left is a leaf. If yes, swap and return;
		if (left == (this.lastPosition) && array[parent] < array[left]) {
			swap(parent, left);
			return;
		}

		// Check if right is a leaf, If yes, swap and return;
		// For this case we need to check the right leaf is bigger or smaller than the
		// left leaf also
		if (right == (this.lastPosition) && array[parent] < array[right] && array[right] > array[left]) {
			swap(parent, right);
			return;
		}
		// If right leaf still smaller than left leaf, swap with left leaf
		if (right == (this.lastPosition) && array[parent] < array[right] && array[right] < array[left]) {
			swap(parent, left);
			return;
		}

		// Check if left or right is out of bound. If yes, we done
		if (left > this.lastPosition || right > this.lastPosition) {
			return;
		}

		// keep Heapify Down to check
		// Swap to the largest value between right and left
		// Check if Left and bigger than right, if yes heapify left side
		if (array[left] > array[right] && array[parent] < array[left]) {
			swap(parent, left);
			heapifyDown(left);
		}

		else {
			// Check if Right is bigger than left, if yes Heapify right side
			if (array[right] > array[left] && array[parent] < array[right]) {
				swap(parent, right);
				heapifyDown(right);
			}
		}
	}

	// Print out the Heap
	public void printHeap() {
		for (int i = 0; i <= this.lastPosition; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
}
