
public class DoubleHashingHT {
	public static void main(String[] args) {
		DoubleHashingHT test = new DoubleHashingHT(13);
//		test.put(1, "Hi");
//		test.put(5, "Bye");
//		test.print();
		
		test.put(1);
		test.put(2);
		test.put(12);
		test.put(13);
		test.put(14);
		test.put(130);
		test.put(1212);
		test.print();
	}

	private Object[] keys;
	private Object[] values;
	private int capacity;
	private int size;

	public DoubleHashingHT(int capacity) {
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

		int index = hash1(key);
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
			// Double Hashing to find the empty space
			int trial = 1;
			while (keys[index] != null) {
				index = doubleHashing(key, trial++);
			}
			keys[index] = key;
			values[index] = value;
			size++;
			return;
		}
	}

	public void resize() {
		DoubleHashingHT temp = new DoubleHashingHT(this.capacity * 2);
		for (int i = 0; i < this.capacity; i++) {
			if (keys[i] != null) {
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
		int index = hash1(key);

		// If there is no values correspond with the key
		if (keys[index] == null) {
			System.out.println("No value here");
			return null;
		}
		
		// while loop until find the place for value
		int trial = 1;
		while (!(keys[index].equals(key))) {
			index = this.doubleHashing(key, trial++);
		}
		return values[index];
	}

	public void print() {
		for (int i = 0; i < this.capacity; i++) {
			System.out.format("[%d] %s %s \n", i, keys[i], values[i]);
		}
	}

	private int doubleHashing(Object key, int trial) {
		return (hash1(key) + trial * hash2(key)) % this.capacity;
	}

	private int hash2(Object key) {
		return (Math.abs(key.hashCode() % (this.capacity - 2)));
	}

	private int hash1(Object key) {
		return (Math.abs(key.hashCode()) % this.capacity);
	}

}
