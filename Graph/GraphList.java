import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class GraphList {

	public static void main(String[] args) {
		// == constant==
		// Vertex
		final int v0 = 0;
		final int v1 = 1;
		final int v2 = 2;
		final int v3 = 3;
		final int v4 = 4;
		final int v5 = 5;
		final int v6 = 6;
		// Labels for each vertex
		final String[] labels = { "v0", "v1", "v2", "v3", "v4", "v5", "v6" };

		// === Test BFS ==
		GraphList graph = new GraphList(7);
		graph.addEdge(v0, v1);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v3);
		graph.addEdge(v2, v0);
		graph.addEdge(v3, v0);
		graph.addEdge(v3, v5);
		graph.addEdge(v3, v6);
		graph.addEdge(v6, v1);
		System.out.println(graph.BreadthFirstSearch(v0, v6));

		// == Test Dijkstra ==
		// Dijkstra Graph
		GraphList graph1 = new GraphList(6, labels);
		graph1.addEdge(v0, v1, 2);
		graph1.addEdge(v0, v5, 9);
		graph1.addEdge(v1, v2, 8);
		graph1.addEdge(v1, v3, 15);
		graph1.addEdge(v1, v5, 6);
		graph1.addEdge(v2, v3, 1);
		graph1.addEdge(v4, v2, 7);
		graph1.addEdge(v4, v3, 3);
		graph1.addEdge(v5, v4, 3);

		int[] path = graph1.Dijsktra(v0, v4);
		System.out.println("Path from " + graph1.labels[v0] + " to " + graph1.labels[v4] + ": ");
		for (int i : path)
			System.out.print(i + " ");
	}

	// Number of vertex
	int size;
	List<List<Integer>>[] vertexArray;
	String[] labels;

	public GraphList(int size, String[] labels) {
		this.size = size;
		this.labels = labels;
		this.vertexArray = new ArrayList[size];
		for (int i = 0; i < vertexArray.length; i++)
			vertexArray[i] = new ArrayList<>(size);

	}

	public GraphList(int size) {
		this.size = size;
		labels = new String[size];
		vertexArray = new ArrayList[size];
		for (int i = 0; i < vertexArray.length; i++)
			vertexArray[i] = new ArrayList<>(size);
	}

	public void addEdge(int source, int target, int weight) {
		List<Integer> temp = new ArrayList<>();
		temp.add(target);
		temp.add(weight);
		vertexArray[source].add(temp);
	}

	public void addEdge(int source, int target) {
		addEdge(source, target, 0);
	}

	public List<List<Integer>> getNeighbors(int source) {
		return vertexArray[source];
	}

	// == Algorithms

	public int[] Dijsktra(int source, int target) {
		int finalSource = source;
		if (source < -1 || target > size - 1)
			return null;

		if (source == target)
			return new int[] { source };

		int originalSource = source;
		// Data structure for Dijsktra Algorithm

		// Initalize all path except source as Infinity, source Weight = 0
		double[] path = new double[size];
		for (int i = 0; i < path.length; i++) {
			path[i] = Double.POSITIVE_INFINITY;
		}
		path[source] = 0;

		// predecessors of all vertex
		int[] predecessors = new int[size];
		for (int i = 0; i < size; i++)
			predecessors[i] = -1;
		predecessors[source] = source;

		// track array to keep track visited node
		boolean[] track = new boolean[size];
		track[source] = true;

		// Find the shortest path to every weight node
		// traverse the whole edges list to update the smallest path
		for (int i = 0; i < this.size; i++) {
			System.out.println("Source: " + labels[source]);
			List<List<Integer>> neighbors = getNeighbors(source);
			System.out.print("Neighbors: ");
			for (List<Integer> vertex : neighbors) {
				// Print Neighbors
				System.out.println("Neighbors : " + labels[vertex.get(0)]);

				// check if this vertex already visited
				if (!track[vertex.get(0)]) {
					// Relax the vertex if the new path is smaller than the original path
					if (path[vertex.get(0)] > path[source] + vertex.get(1)) {
						// Relax
						path[vertex.get(0)] = path[source] + vertex.get(1);
						predecessors[vertex.get(0)] = source;
					}

				}
			}
			System.out.println();
			
			// Find smallest path
			double smallest = Double.MAX_VALUE;
			for (int j = 0; j < size; j++) {
				if (path[j] < smallest && !track[j]) {
					smallest = path[j];
					source = j;
				}
			}
			// keep track of the visited node
			track[source] = true;
			System.out.print("Track: ");
			printArray(track);
			System.out.print("Path Weight: ");
			printArray(path);
			System.out.print("Predecessors ");
			printArray(predecessors);
			System.out.println();
		} // == close for loop ==

		// Now find the shortest path to target
		Stack<Integer> stack = new Stack<>();
		stack.push(target);
		for (int i = target; i != finalSource; i = predecessors[i]) {
			stack.push(predecessors[i]);
		}

		// Pop stack into result array
		int[] result = new int[stack.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = stack.pop();
		}
		return result;
	}

	public boolean BreadthFirstSearch(int source, int target) {
		if (source == target)
			return true;
		// Stack and track array
		Stack<List<List<Integer>>> stack = new Stack<>();
		boolean[] track = new boolean[size];

		// Add source into the stack and start from source
		stack.push(vertexArray[source]);
		track[source] = true;
		while (!stack.isEmpty()) {
			// Neighbors List of source
			List<List<Integer>> neighbors = stack.pop();
			System.out.println("Neighbor: " + neighbors);

			// Breadth first search through each neighborsList
			for (int i = 0; i < neighbors.size(); i++) {
				System.out.println("Check vertex: " + neighbors.get(i));
				// 0 index is target and 1 index is weight
				if (neighbors.get(i).get(0) == target)
					return true;

				// check if the neighbor already checked ? add into stack
				if (!track[neighbors.get(i).get(0)]) {
					stack.push(vertexArray[neighbors.get(i).get(0)]);
					track[neighbors.get(i).get(0)] = true;
				}
			}
		}
		// Return false if there is no correspond path
		return false;
	}

	private void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();

	}

	private void printArray(double[] array) {
		for (double i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	private void printArray(boolean[] array) {
		for (boolean i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
