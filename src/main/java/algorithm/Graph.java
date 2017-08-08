package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	
	private Map<Integer, Set<Integer>> adjacencyMap = new HashMap();
	private int numberOfVertices = 0;
	
	public static void main(String[] args) {
		int numberOfEdges = 21;
		Integer[][] edgeArray = new Integer[numberOfEdges][2];
		
		// egy el egy kolcsonos viszonyt jelol
		edgeArray[0][0] = 0;
		edgeArray[0][1] = 1;
		
		edgeArray[1][0] = 0;
		edgeArray[1][1] = 2;
		
		edgeArray[2][0] = 1;
		edgeArray[2][1] = 3;
		
		edgeArray[3][0] = 1;
		edgeArray[3][1] = 4;
		
		edgeArray[4][0] = 1;
		edgeArray[4][1] = 5;
		
		edgeArray[5][0] = 1;
		edgeArray[5][1] = 2;
		
		edgeArray[6][0] = 4;
		edgeArray[6][1] = 5;
		
		edgeArray[7][0] = 6;
		edgeArray[7][1] = 5;
		
		edgeArray[8][0] = 6;
		edgeArray[8][1] = 2;
		
		edgeArray[9][0] = 7;
		edgeArray[9][1] = 8;
		
		edgeArray[10][0] = 9;
		edgeArray[10][1] = 8;
		
		edgeArray[11][0] = 4;
		edgeArray[11][1] = 11;
		
		edgeArray[12][0] = 12;
		edgeArray[12][1] = 11;
		
		edgeArray[13][0] = 10;
		
		
		
		Graph graph = new Graph(edgeArray);
		System.out.println("is connected?");
		System.out.println(GraphProcessingUtil.isConnected(graph, 7, 0));
		GraphProcessingUtil.getShortestPath(graph, 0, 12);
		Map<Integer, List<Integer>> componentMap = GraphProcessingUtil.getGraphComponents(graph);
		System.out.println("kiscica");
	}
	
	public Graph() {
		
	}
	
	public Graph(Integer[][] edgeArray) {
		//
		for (Integer[] edgePair : edgeArray) {
				addEdge(edgePair[0], edgePair[1]);
		}
	}

	public void addEdge(Integer vertexA, Integer vertexB) {
		if (vertexA != null && vertexB == null) {
			addStandaloneVertex(vertexA);
		}
		else if (vertexA != null && vertexB != null) {
			addEdgeHelper(vertexA, vertexB);
			addEdgeHelper(vertexB, vertexA);
		}
	}
	
	private void addStandaloneVertex(int vertexA) {
		Set<Integer> neighboringVertices = new HashSet<>();
		adjacencyMap.put(vertexA, neighboringVertices);
		numberOfVertices++;
		
	}
	
	private void addEdgeHelper(int vertexA, int vertexB) {
		if (adjacencyMap.get(vertexA) == null) {
			Set<Integer> neighboringVertices = new HashSet<>();
			neighboringVertices.add(vertexB);
			adjacencyMap.put(vertexA, neighboringVertices);
			numberOfVertices++;
		} else {
			adjacencyMap.get(vertexA).add(vertexB);
		}
	}
	
	public Iterable<Integer> getAdjacentVertices(int vertex) {
		return adjacencyMap.get(vertex);
	}
	
	public int size() {
		return numberOfVertices;
	}
	
	
}
