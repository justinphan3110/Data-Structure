
public class Graph 
{
	
   public static void main(String[] args) {
	   Graph graph = new Graph(4);
	   // == Initalize the town value== 
	   final int  Byteville = 0;
	   final int  Bitburg = 1;
	   final int  Nulltown = 2;
	   final int  Binaria = 3;
	   
	   //== Set up Path weight for each town == 
	   //  Byteville --> Bitburg : 1.5
	   //  Bitburg --> Nulltown : 2
	   //  Nulltown --> Binaria : 2
	   //  Binaria --> Byteville : 3.9
	   
	   graph.addEdge(Byteville, Bitburg, 1.5);
	   graph.addEdge(Bitburg, Nulltown, 2);
	   graph.addEdge(Nulltown, Binaria, 2);
	   graph.addEdge(Binaria, Byteville, 3.9);
	   
	   graph.setLabel(0, "Byteville");
	   graph.setLabel(1, "Bitburg");
	   graph.setLabel(2, "Nulltown");
	   graph.setLabel(3, "Binaria");
	   
	   graph.printTotalPath(new int[] {Byteville, Bitburg, Nulltown});
	   graph.printTotalPath(new int[] {Nulltown, Byteville});
	   graph.printTotalPath(new int[] {Binaria, Bitburg});
	   
	   graph.addEdge(Byteville, Nulltown, 2);
	   graph.addEdge(Bitburg, Byteville, 1.5);
	   graph.addEdge(Bitburg, Binaria, 1.5);
	   graph.hubs();
	   
	   
	   
   }
	
   // Invariant of the Graph class:
   //   1. The vertex numbers range from 0 to labels.length-1.
   //   2. For each vertex number i, labels[i] contains the label for vertex i.
   //   3. For any two vertices i and j, edges[i][j] is true if there is a
   //      vertex from i to j; otherwise edges[i][j] is false.  
   private double[ ][ ] edges;
   private String[] labels;
   public Graph(int n)
   {
      edges = new double[n][n];  
   // All values initially -1, mean no path
      for(int source = 0; source < edges.length; source++) {
    	  for(int destination = 0; destination < edges[0].length; destination ++) {
    		  edges[source][destination] = -1;
    	  }
      }
      labels = new String[n];
   }
   

   public void addEdge(int source, int target, double d)   
   {
      edges[source][target] = d;
   }
   
   public double getWeight(int source, int target) {
	   return edges[source][target];
   }

  public void printTotalPath(int[] path) {
	  double sum = 0.0;
	  for(int i = 0; i < path.length - 1; i++) {
		  
		  // local path
		  double localPath = edges[path[i]][path[i+1]];
		  if(localPath == -1) {
			  sum = -1;
			  break;
		  }
		  sum += localPath;
	  }
	  StringBuilder text = new StringBuilder("From ");
	  for(int i = 0; i < path.length; i++) {
		  text.append(labels[path[i]]);
		  if(i != path.length - 1)
			  text.append(" to ");
		  else text.append(": ");
	  }
	  if(sum != -1)
		  text.append(sum);
	  else text.append(" No Valid Path");
	  System.out.println(text.toString());
  }


  
  public void setLabel(int vertex, String name) {
	  labels[vertex] = name;
  }
  
  public void hubs() {
	  for(int source = 0; source < edges.length; source++) {
		  int edgeCount = 0;
		  for(int destination = 0; destination < edges[source].length; destination++) {
			  if(edges[source][destination] != -1) {
				  edgeCount++;
			  }
		  }
		  System.out.println(labels[source] + ": " +  edgeCount);
	  }
  }
}
           
