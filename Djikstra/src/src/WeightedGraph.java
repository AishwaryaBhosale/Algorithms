package src;

import java.util.*;

public class WeightedGraph {

	class WeightedNode {
		int mIndex;
		private List<WeightedEdge> mNeighbors = new ArrayList<WeightedEdge>();

		WeightedNode(int index) {
			mIndex = index;
		}
	}

	private class WeightedEdge implements Comparable {

		private WeightedNode mFirst, mSecond;
		private double mWeight;

		WeightedEdge(WeightedNode first, WeightedNode second, double weight) {
			mFirst = first;
			mSecond = second;
			mWeight = weight;
		}

		@Override
		public int compareTo(Object o) {
			WeightedEdge e = (WeightedEdge) o;
			return Double.compare(mWeight, e.mWeight);
		}
	}

	private List<WeightedNode> mVertices = new ArrayList<WeightedNode>();

	public WeightedGraph(int numberOfVertices) {
		for (int i = 0; i < numberOfVertices; i++) {
			mVertices.add(new WeightedNode(i));
		}
	}

	public void addEdge(int firstVertex, int secondVertex, double weight) {
		WeightedNode first = mVertices.get(firstVertex);
		WeightedNode second = mVertices.get(secondVertex);

		WeightedEdge edge = new WeightedEdge(first, second, weight);
		first.mNeighbors.add(edge);
		// second.mNeighbors.add(edge);
	}

	/**
	 * Prints the graph to the console. Each vertex is printed on its own line,
	 * starting with the vertex's number (its index in the mVertices list), then a
	 * colon, then a sequence of pairs for each edge incident to the vertex. For
	 * each edge, print the number of the vertex at the opposite end of the edge, as
	 * well as the edge's weight.
	 *
	 * Example: in a graph with three vertices (0, 1, and 2), with edges from 0 to 1
	 * of weight 10, and from 0 to 2 of weight 20, printGraph() should print
	 *
	 * Vertex 0: (1, 10), (2, 20) Vertex 1: (0, 10) Vertex 2: (0, 20)
	 */
	public void printGraph() {
		int count = 0;
		for (WeightedNode vertice : mVertices) {
			System.out.print("Vertex " + count + ":");
			count++;
			for (WeightedEdge edge : vertice.mNeighbors) {
				System.out.print("(" + edge.mSecond.mIndex + "," + edge.mWeight + ") ");
			}
			System.out.print("\n");
		}

	}

	/**
	 * Applies Prim's algorithm to build and return a minimum spanning tree for the
	 * graph. Start by constructing a new WeightedGraph with the same number of
	 * vertices as this graph. Then apply Prim's algorithm. Each time an edge is
	 * selected by Prim's, add an equivalent edge to the other graph. When complete,
	 * return the new graph, which is the minimum spanning tree.
	 *
	 * @return an UnweightedGraph representing this graph's minimum spanning tree.
	 */
	public WeightedGraph getMinimumSpanningTree() {
		// TODO: build and return the MST.
		int v = mVertices.size();
		WeightedGraph mst = new WeightedGraph(v);
		int key[] = new int[v];
		Boolean visited[] = new Boolean[v];
		// Making all vertices visited false and keys infinite
		for (int i = 0; i < v; i++) {
			key[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		key[0] = 0;
		// mst.addEdge(firstVertex, secondVertex, weight);
		for (int count = 0; count < v - 1; count++) {
			int u = minKey(key, visited);
			visited[u] = true;
			WeightedNode vertex = mVertices.get(u);
			for (WeightedEdge edge : vertex.mNeighbors) {
				if (edge.mWeight < key[edge.mSecond.mIndex]) {
					mst.addEdge(vertex.mIndex, edge.mSecond.mIndex, 0.0);
					key[edge.mSecond.mIndex] = (int) edge.mWeight;
				}
			}
		}
		return mst;
	}

	int minKey(int key[], Boolean mstSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < mVertices.size(); v++)
			if (mstSet[v] == false && key[v] < min) {
				min = key[v];
				min_index = v;
			}

		return min_index;
	}

	/**
	 * Applies Dijkstra's algorithm to compute the shortest paths from a source
	 * vertex to all other vertices in the graph. Returns an array of path lengths;
	 * each value in the array gives the length of the shortest path from the source
	 * vertex to the corresponding vertex in the array.
	 */
	public double[] getShortestPathsFrom(int source) {
		// TODO: apply Dijkstra's algorithm and return the distances array.

		// This queue is used to select the vertex with the smallest "d" value
		// so far.
		// Each time a "d" value is changed by the algorithm, the corresponding
		// DijkstraDistance object needs to be removed and then re-added to
		// the queue so its position updates.
		PriorityQueue<DijkstraDistance> vertexQueue = new PriorityQueue<DijkstraDistance>();

		// Initialization: set the distance of the source node to 0, and all
		// others to infinity. Add all distances to the vertex queue.
		DijkstraDistance[] distances = new DijkstraDistance[mVertices.size()];
		distances[source] = new DijkstraDistance(source, 0);
		for (int i = 0; i < distances.length; i++) {
			if (i != source)
				distances[i] = new DijkstraDistance(i, Integer.MAX_VALUE);

			vertexQueue.add(distances[i]);
		}

		while (vertexQueue.size() > 0) {
			// Finish the algorithm.

		}

		return null;
	}

	class DijkstraDistance implements Comparable {

		int mVertex;
		double mCurrentDistance;

		DijkstraDistance(int vertex, double distance) {
			mVertex = vertex;
			mCurrentDistance = distance;
		}

		@Override
		public int compareTo(Object other) {
			DijkstraDistance d = (DijkstraDistance) other;
			return Double.compare(mCurrentDistance, d.mCurrentDistance);
		}
	}

}
