
public class LinearProbingHT {
	public static void main(String[] args) {
		LinearProbingHT test = new LinearProbingHT(4);
		test.put(1, "Hi");
		test.put(5, "Bye");
		
//		test.put(1);
//		test.put(2);
//		test.put(12);
//		test.put(13);
//		test.put(14);
//		test.put(130);
//		test.put(1212);
		test.print();
	}

	private Object[] keys;
	private Object[] values;
	private int capacity;
	private int size;

	public LinearProbingHT(int capacity) {
		this.keys = new Object[capacity];
		this.values = new String[capacity];
		this.size = 0;
		this.capacity = capacity;
	}

	// This put method for a hashset
	public void put(Object key) {
		this.put(key, null);
	}

	public void put(Object key, Object value) {
		// If the table is fulll
		if (size == this.capacity) {
			this.resize();		
		}

		int index = hashCode(key);
		// If the keys already in the table, Update the new value
		if (keys[index] != null && keys[index].equals(key)) {
			values[index] = value;
			return;
		}

		// When the space is empty
		if (keys[index] == null) {
			keys[index] = key;
			values[index] = value;
			size++;
			return;
		} else {
			// Linear Probing to find the empty space
			while (keys[index] != null) {
				index = linearProbing(index);
			}
			keys[index] = key;
			values[index] = value;
			size++;
			return;
		}
	}
	
	public void resize() {
		LinearProbingHT temp = new LinearProbingHT(this.capacity * 2);
		for(int i = 0; i < this.capacity; i++) {
			if(keys[i] != null) {
				temp.put(keys[i], values[i]);
			}
		}
		keys = temp.keys;
		values = temp.values;
		capacity = temp.capacity;
		
	}
	
	public Object get(Object key) {
		if (key == null) {
			System.out.println("key is null");
			return null;
		}
		int index = hashCode(key);

		// If there is no values correspond with the key
		if (keys[index] == null) {
			System.out.println("No value here");
			return null;
		}

		// while loop until find the place for value
		while (!(keys[index].equals(key))) {
			index = this.linearProbing(index);
		}
		return values[index];
	}

	public void print() {
		for (int i = 0; i < this.capacity; i++) {
			System.out.format("[%d] %s %s \n", i, keys[i], values[i]);
		}
	}
	
	

	private int linearProbing(int index) {
		if ((index + 1) == this.capacity) {
			return 0;
		} else
			return ++index;

	}

	private int hashCode(Object key) {
		return (Math.abs(key.hashCode()) % this.capacity);
	}

}
