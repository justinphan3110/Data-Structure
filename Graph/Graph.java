import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	// == Initalize the town value==
	final static int Byteville = 0;
	final static int Bitburg = 1;
	final static int Nulltown = 2;
	final static int Binaria = 3;

	// == Initalize the vertex value
	final static int v0 = 0;
	final static int v1 = 1;
	final static int v2 = 2;
	final static int v3 = 3;
	final static int v4 = 4;
	final static int v5 = 5;
	final static int v6 = 6;
	final static String[] vertexArray = { "v0", "v1", "v2", "v3", "v4", "v5", "v6" };

	// == Initialize the 3 coins vertex (H for head, T for tail)
	final static int c0 = 0;
	final static int c1 = 1;
	final static int c2 = 2;
	final static int c3 = 3;
	final static int c4 = 4;
	final static int c5 = 5;
	final static int c6 = 6;
	final static int c7 = 7;
	final static String[] coinArray = { "HHH", "THH", "HTH", "HHT", "TTH", "THT", "HTT", "TTT" };

	public static void main(String[] args) {

		// V
		Graph graph = new Graph(7, vertexArray);
		graph.addEdge(v0, v1);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v3);
		graph.addEdge(v2, v0);
		graph.addEdge(v3, v0);
		graph.addEdge(v3, v5);
		graph.addEdge(v3, v6);
		graph.addEdge(v6, v1);
		graph.breadthFirstSearch(v0, v3);

		System.out.println("======================================");
		// Coins
		Graph coinGraph = new Graph(8, coinArray);
		// Two edges between each vertex Graph
		coinGraph.addEdge(c0, c1);
		coinGraph.addEdge(c1, c0);
		coinGraph.addEdge(c0, c2);
		coinGraph.addEdge(c2, c0);
		coinGraph.addEdge(c0, c3);
		coinGraph.addEdge(c3, c0);
		coinGraph.addEdge(c1, c4);
		coinGraph.addEdge(c4, c1);
		coinGraph.addEdge(c3, c6);
		coinGraph.addEdge(c6, c3);
		coinGraph.addEdge(c4, c7);
		coinGraph.addEdge(c7, c4);
		coinGraph.addEdge(c5, c7);
		coinGraph.addEdge(c7, c5);
		coinGraph.addEdge(c6, c7);
		coinGraph.addEdge(c7, c6);

		coinGraph.breadthFirstSearch(c2, c5);

		System.out.println("=======================");
		// Dijkstra Graph
		Graph graph1 = new Graph(6, vertexArray);
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

	// Invariant of the Graph class:
	// 1. The vertex numbers range from 0 to labels.length-1.
	// 2. For each vertex number i, labels[i] contains the label for vertex i.
	// 3. For any two vertices i and j, edges[i][j] is true if there is a
	// vertex from i to j; otherwise edges[i][j] is false.
	private double[][] edges;
	private String[] labels;
	private int size;

	public Graph(int size, String[] labels) {
		this.size = size;
		this.labels = labels;
		edges = new double[size][size];
		// All values initially -1, mean no path
		for (int source = 0; source < edges.length; source++) {
			for (int destination = 0; destination < edges[0].length; destination++) {
				edges[source][destination] = -1;
			}
		}
	}

	public Graph(int size) {
		this.size = size;
		edges = new double[size][size];
		// All values initially -1, mean no path
		for (int source = 0; source < edges.length; source++) {
			for (int destination = 0; destination < edges[0].length; destination++) {
				edges[source][destination] = -1;
			}
		}
		labels = new String[size];
	}

	public int[] Dijsktra(int source, int target) {
		int finalSource = source;
		// Data structure for Dijsktra Algorithm
		
		// Initalize all path except source as Infinity, source Weight = 0
		double[] path = new double[size];
		for (int i = 0 ; i< path.length; i++) {
			path[i] = Double.POSITIVE_INFINITY;
		}
		path[source] = 0;
		
		// predecessors of all vertex
		int[] predecessors = new int[size];
		for(int i = 0; i < size; i ++) predecessors[i] = -1;
		predecessors[source] = source;		
		
		// track array to keep track visited node
		boolean[] track = new boolean[size];
		track[source] = true;
		
		// Find the shortest path to every weight node
		// traverse the whole edges list to update the smallest path
		for(int trial = 0; trial < size; trial++) {
			System.out.println("Source: " + labels[source]);
			Integer[] neighbor = getNeighbors(source);
			System.out.print("Neighbor: ");
			for(int i: neighbor) {
				// Print neighbor
				System.out.print(labels[i] + " ");
				if(!track[i]) {
					// Relax the vertex if the new path is smaller than the original path
					if(path[i] > edges[source][i] + path[source]) {
						// update path to this vertex
						path[i] = edges[source][i] + path[source];
						predecessors[i] = source;
					}
				}
			}
			
			System.out.println();
			// Find smallest path
			double smallest = Double.MAX_VALUE;
			for(int i = 0; i < size; i++) {
				if(path[i] < smallest && !track[i]) {
					smallest = path[i];
					source = i;
				}
			}
			// keep track of the visited node
			track[source] = true;
			System.out.print("Track: "); printArray(track);
			System.out.print("Path Weight: ");printArray(path);
			System.out.print("Predecessors "); printArray(predecessors);
			System.out.println();
		} // == close for loop ==
		
		// Now find the shortest path to target
		Stack<Integer> stack = new Stack< >();
		stack.push(target);
		for(int i = target; i != finalSource; i = predecessors[i]) {
			stack.push(predecessors[i]);
		}
		
		// Pop stack into result array
		int[] result = new int[stack.size()];
		for(int i = 0; i < result.length; i++) {
			result[i] = stack.pop();
		}		
		return result;
	}


	public void breadthFirstSearch(int source, int target) {

		// Stack to perform BFS
		Stack<Integer> stack = new Stack<>();
		boolean[] track = new boolean[edges.length];
		track[source] = true;
		stack.add(source);
		System.out.println("Starting at : " + labels[source]);

		while (!stack.isEmpty()) {

			// get neighbors array of the vertex
			int tempVertex = stack.pop();
			Integer[] neighbors = getNeighbors(tempVertex);

			for (Integer vertex : neighbors) {
				// Keep track if we already visit this Vertex or not
				if (!track[vertex]) {
					stack.push(vertex);
					track[vertex] = true;
					System.out.println(
							labels[tempVertex] + " to " + labels[vertex] + (vertex == target ? ":FOUND A PATH " : ""));
				}
			}

		}
	}

	public void addEdge(int source, int target) {
		addEdge(source, target, 0);
	}

	public void addEdge(int source, int target, double d) {
		edges[source][target] = d;
	}

	// Get the array of all neighbors from a vertex

	public Integer[] getNeighbors(int source) {
		List<Integer> neightborsList = new ArrayList<>();
		for (int i = 0; i < edges[source].length; i++) {
			if (edges[source][i] != -1)
				neightborsList.add(i);
		}
		return neightborsList.toArray(new Integer[neightborsList.size()]);
	}

	public double getWeight(int source, int target) {
		return edges[source][target];
	}

	public void printTotalPath(int[] path) {
		double sum = 0.0;
		for (int i = 0; i < path.length - 1; i++) {

			// local path
			double localPath = edges[path[i]][path[i + 1]];
			if (localPath == -1) {
				sum = -1;
				break;
			}
			sum += localPath;
		}
		StringBuilder text = new StringBuilder("From ");
		for (int i = 0; i < path.length; i++) {
			text.append(labels[path[i]]);
			if (i != path.length - 1)
				text.append(" to ");
			else
				text.append(": ");
		}
		if (sum != -1)
			text.append(sum);
		else
			text.append(" No Valid Path");
		System.out.println(text.toString());
	}

	public void setLabel(int vertex, String name) {
		labels[vertex] = name;
	}

	public void hubs() {
		for (int source = 0; source < edges.length; source++) {
			int edgeCount = 0;
			for (int destination = 0; destination < edges[source].length; destination++) {
				if (edges[source][destination] != -1) {
					edgeCount++;
				}
			}
			System.out.println(labels[source] + ": " + edgeCount);
		}
	}

	private void printArray(int[] array) {
		for(int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
	private void printArray(double[] array) {
		for(double i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	private void printArray(boolean[] array) {
		for(boolean i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
