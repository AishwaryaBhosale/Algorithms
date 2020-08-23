package src;

public class Main {
	public static void main(String[] args) {
	      // Use this main to test your code by hand before implementing the program.
	      WeightedGraph g = new WeightedGraph(6);
	      g.addEdge(0, 1, 1);
	      g.addEdge(0, 2, 3);
	      g.addEdge(1, 2, 1);
	      g.addEdge(1, 3, 1);
	      g.addEdge(1, 4, 4);
	      g.addEdge(2, 3, 1);
	      g.addEdge(2, 5, 2);
	      g.addEdge(3, 4, 1);
	      g.addEdge(3, 5, 3);
	      g.addEdge(4, 5, 2);
	      g.printGraph();
	      
	      
	      /*double[] distances = g.getShortestPathsFrom(0);
	      for (int i = 0; i < distances.length; i++) {
	         System.out.println("Distance from 0 to " + i + ": " + 
	          distances[i]);
	         
	      }*/
	      
	      WeightedGraph mst = g.getMinimumSpanningTree();
	      System.out.println("Minimum spanning tree:");
	      mst.printGraph();
	   }
}
