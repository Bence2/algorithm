package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedGraph<T> {
	
	private Map<T, Set<T>> adjacencyMap = new HashMap();
	private int numberOfVertices = 0;

	public static void main(String[] args) {
		int numberOfEdges = 20;
		String[][] edgeArray = new String[numberOfEdges][2];
		edgeArray[0][0] = "Algorithms";
		edgeArray[0][1] = "TheoreticalCS";
		
		edgeArray[1][0] = "Algorithms";
		edgeArray[1][1] = "Databases";
		
		edgeArray[2][0] = "Algorithms";
		edgeArray[2][1] = "ScientificComputing";
		
		edgeArray[3][0] = "IntroductionToCS";
		edgeArray[3][1] = "Algorithms";
		
		edgeArray[4][0] = "IntroductionToCS";
		edgeArray[4][1] = "AdvancedProgramming";
		
		edgeArray[5][0] = "AdvancedProgramming";
		edgeArray[5][1] = "ScientificComputing";
		
		edgeArray[6][0] = "ScientificComputing";
		edgeArray[6][1] = "ComputationalBiology";
		
		edgeArray[7][0] = "TheoreticalCS";
		edgeArray[7][1] = "ComputationalBiology";
		
//		edgeArray[8][0] = "Databases";
//		edgeArray[8][1] = "IntroductionToCS";
		
		edgeArray[9][0] = "LinearAlgebra";
		edgeArray[9][1] = "TheoreticalCS";
		
		edgeArray[10][0] = "TheoreticalCS";
		edgeArray[10][1] = "ArtificialIntelligence";
		
		edgeArray[11][0] = "ArtificialIntelligence";
		edgeArray[11][1] = "MachineLearning";
//		edgeArray[11][1] = "LinearAlgebra";
		
		// erre meg teszteseteket irni
		
		DirectedGraph<String> diGraph = new DirectedGraph<>(edgeArray);
		System.out.println(DirectedGraphProcessingUtil.hasCycle(diGraph));
		System.out.println("miau");

	}
	
	public Iterable<T> getVertices() {
		return adjacencyMap.keySet();
	}
	
	public DirectedGraph(T[][] edgeArray) {
		//
		for (T[] edgePair : edgeArray) {
				addEdge(edgePair[0], edgePair[1]);
		}
	}

	
	public void addEdge(T vertexA, T vertexB) {
		if (vertexA != null && vertexB == null) {
			addStandaloneVertex(vertexA);
		}
		else if (vertexA != null && vertexB != null) {
			addEdgeHelper(vertexA, vertexB);
			addVertexWithoutConnection(vertexB);
		}
	}
	
	private void addStandaloneVertex(T vertexA) {
		Set<T> neighboringVertices = new HashSet<>();
		adjacencyMap.put(vertexA, neighboringVertices);
		numberOfVertices++;
		
	}
	
	private void addVertexWithoutConnection(T vertex) {
		if (adjacencyMap.get(vertex) == null) {
			Set<T> neighboringVertices = new HashSet<>();
			adjacencyMap.put(vertex, neighboringVertices);
			numberOfVertices++;
		}
	}
	
	private void addEdgeHelper(T vertexA, T vertexB) {
		if (adjacencyMap.get(vertexA) == null) {
			Set<T> neighboringVertices = new HashSet<>();
			neighboringVertices.add(vertexB);
			adjacencyMap.put(vertexA, neighboringVertices);
			numberOfVertices++;
		} else {
			adjacencyMap.get(vertexA).add(vertexB);
		}
	}
	
	public Iterable<T> getAdjacentVertices(T vertex) {
		return adjacencyMap.get(vertex);
	}
	
	public int size() {
		return numberOfVertices;
	}
}
