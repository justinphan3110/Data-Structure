import java.util.LinkedList;

public class RadixSortAlphabet {
	
	public LinkedListModified<WordCount> radixSort(LinkedListModified<WordCount> list) {
		// maxRadix is 27
		LinkedListModified<WordCount>[] array = new LinkedListModified[27];
		// Initialized array
		for (int i = 0; i < array.length; i++) {
			array[i] = new LinkedListModified();
		}

		int maxWidth = 0;
		// Get the maxWidth for Radix Sort
		for (WordCount w : list) {
			if (w.getWord().length() > maxWidth)
				maxWidth = w.getWord().length();
		}
		System.out.println("MaxWidth is: " + maxWidth);
		for (int i = maxWidth - 1; i >= 0; i--) {
			for (WordCount w : list) {
				System.out.println(w.getWord() + " at ");
				if (i >= w.getWord().length()) {
					System.out.println("Adding " + w.getWord() + " into blank");
					array[0].add(new WordCount(w.getWord(), w.getCount()));
				} else {
					System.out.println("Adding " + w.getWord() + " into " + (w.getWord().charAt(i) - 'a' + 1));
					array[w.getWord().charAt(i) - 'a' + 1].add(new WordCount(w.getWord(), w.getCount()));
				}
			}

			list = new LinkedListModified<>();
			for (LinkedListModified<WordCount> l : array) {
				list.append(l);
			}
			array = new LinkedListModified[27];
			// Initialized array
			for (int j = 0; j < array.length; j++) {
				array[j] = new LinkedListModified();
			}

		}
		return list;
	}

	public static void main(String[] args) {
		RadixSortAlphabet test = new RadixSortAlphabet();
		LinkedListModified<WordCount> list = new LinkedListModified<>();
		list.add(new WordCount("cat", 1));
		list.add(new WordCount("bit", 1));
		list.add(new WordCount("cop", 1));
		list.add(new WordCount("cap", 1));
		list.add(new WordCount("be", 1));
		list.add(new WordCount("car", 1));
		list.add(new WordCount("dart", 1));
		list.add(new WordCount("bat", 1));
		list.add(new WordCount("cart", 1));
		LinkedListModified<WordCount> result = test.radixSort(list);
		result.forEach(a -> System.out.println(a.getWord()));

	}
}
