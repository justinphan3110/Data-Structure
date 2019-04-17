import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BreadthFirstSearch {
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

	public static void main(String[] args) {
		BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(7);

		breadthFirstSearch.addEdge(v0, v1);
		breadthFirstSearch.addEdge(v0, v4);
		breadthFirstSearch.addEdge(v1, v3);
		breadthFirstSearch.addEdge(v2, v0);
		breadthFirstSearch.addEdge(v3, v0);
		breadthFirstSearch.addEdge(v3, v5);
		breadthFirstSearch.addEdge(v3, v6);
		breadthFirstSearch.addEdge(v6, v1);
		breadthFirstSearch.breadthFirstSearch(v0, v3);
	}

	// Invariant of the Graph class:
	// 1. The vertex numbers range from 0 to labels.length-1.
	// 2. For each vertex number i, labels[i] contains the label for vertex i.
	// 3. For any two vertices i and j, edges[i][j] is true if there is a
	// vertex from i to j; otherwise edges[i][j] is false.
	private double[][] edges;
	private String[] labels;

	public BreadthFirstSearch(int n) {
		edges = new double[n][n];
		// All values initially -1, mean no path
		for (int source = 0; source < edges.length; source++) {
			for (int destination = 0; destination < edges[0].length; destination++) {
				edges[source][destination] = -1;
			}
		}
		labels = new String[n];
	}

	public void breadthFirstSearch(int source, int target) {
		
		// Stack to perform BFS
		Stack<Integer> stack = new Stack<>();
		boolean[] track = new boolean[edges.length];
		track[source] = true;
		stack.add(source);
		System.out.println("Starting at : " + vertexArray[source]);
		
		while (!stack.isEmpty()) {
			
			// get neighbors array of the vertex
			int tempVertex = stack.pop();
			Integer[] neighbors = getNeighbors(tempVertex);
			
			for(Integer vertex: neighbors) {
				// Keep track if we already visit this Vertex or not
				if(!track[vertex]) {
					stack.push(vertex);
					track[vertex] = true;
				}
				
				System.out.print(vertexArray[tempVertex] + " to " + vertexArray[vertex]);
				// Print found a path if found
				if(vertex == target)
					System.out.print(" : FOUND A PATH");
				System.out.println();
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
		List<Integer> neightborsList = new ArrayList< >();
		for(int i = 0; i < edges[source].length; i++) {
			if(edges[source][i] != -1)
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
}
