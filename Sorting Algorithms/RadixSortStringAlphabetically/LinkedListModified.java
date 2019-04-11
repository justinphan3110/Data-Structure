import java.util.LinkedList;

class LinkedListModified<T> extends LinkedList<T> {

	public void append(LinkedList<T> list) {
		this.addAll(list);
	}
}