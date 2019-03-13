import java.util.ArrayList;

public class BinarySearchString {
	private ArrayList<String> dictionary;
	
	public BinarySearchString(ArrayList<String> dictionary) {
		this.dictionary = dictionary;
	}
	public int binarySearchString(String str1) {
		return binarySearchString(0, dictionary.size() - 1, str1);
	}

	private int binarySearchString(int start, int end, String target) {

		int midpoint = (start + end) / 2;
		String stringMid = dictionary.get(midpoint);
		//If string is equal
		if (compareString(stringMid, target) == 0) {
			return midpoint;
		}
		
		if (start >= end)
			return -1;

		// If target is smaller than mid, go left
		if (compareString(stringMid, target) > 0) {
			return binarySearchString(0, midpoint, target);
		} else {
			// If target is bigger than mid, go right
			// if (compareString(stringMid, target) < 0) {
			return binarySearchString(midpoint + 1, end, target);
		}
	}
	
	public int compareString(String str1, String str2) {
		int l1 = str1.length();
		int l2 = str2.length();
		int lmin = Math.min(l1, l2);

		for (int i = 0; i < lmin; i++) {
			int num1 = str1.charAt(i);
			int num2 = str2.charAt(i);

			// return the first different in value
			if (num1 != num2) {
				return num1 - num2;
			}
		}
		// When string has first minLength the same
		if (l1 != l2) {
			return l1 - l2;
		}
		// string are the same
		else
			return 0;
	}
}
