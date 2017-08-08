package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GraphProcessingUtil {

	public static void main(String[] args) {
		boolean[] isVisited = new boolean[3];
		for (boolean isVertexVisited : isVisited) {
			System.out.println(isVertexVisited);
		}
		
	}
	
	
	public static boolean isConnected(Graph graph, int sourceVertex, int destinationVertex) {
		// not sure whether the graph is connected
		// depth first search
		boolean[] isVisited = new boolean[graph.size()];
		isVisited[sourceVertex] = true;
		
		Stack<Integer> verticesToBeDiscovered = new Stack<>();
		verticesToBeDiscovered.push(sourceVertex);
		while (verticesToBeDiscovered.size() != 0) {
			int currentVertex = verticesToBeDiscovered.pop();
			isVisited[currentVertex] = true;
			for (Integer vertex : graph.getAdjacentVertices(currentVertex)) {
				if (!isVisited[vertex]) {
					verticesToBeDiscovered.push(vertex);
				}
			}
		}
		printVisited(isVisited);
		
		return isVisited[sourceVertex] == isVisited[destinationVertex];
	}
	
	public static Map<Integer, List<Integer>> getGraphComponents(Graph graph) {
		Map<Integer, List<Integer>> componentMap = new HashMap<>();
		boolean[] isVisited = new boolean[graph.size()];
		int componentId = 0;
		for (int outerVertex = 0; outerVertex < graph.size(); outerVertex++) {
			if (!isVisited[outerVertex])
			{
				List<Integer> verticesInSameComponent = new ArrayList<Integer>();
				componentMap.put(componentId, verticesInSameComponent);
				++componentId;
				
				Stack<Integer> verticesToBeDiscovered = new Stack<>();
				verticesToBeDiscovered.push(outerVertex);
				
				while (verticesToBeDiscovered.size() != 0) {
					int currentVertex = verticesToBeDiscovered.pop();
					
					if (!isVisited[currentVertex]) {
						isVisited[currentVertex] = true;
						verticesInSameComponent.add(currentVertex);

						for (Integer vertex : graph.getAdjacentVertices(currentVertex)) {
							if (!isVisited[vertex]) {
								verticesToBeDiscovered.push(vertex);
							}
						}
					}
				}
			}
		}
		return componentMap;
	}
	
	public static Iterable<Integer> getShortestPath(Graph graph, int sourceVertex, int destinationVertex) {
		// bfs
		if (isConnected(graph, sourceVertex, destinationVertex)) {
			// edgeTo array parent linkek
			boolean[] isVisited = new boolean[graph.size()];
			int[] parentLink = new int[graph.size()];
			
			Queue<Integer> verticesToBeDiscovered = new LinkedList<>();
			verticesToBeDiscovered.add(sourceVertex);
			isVisited[sourceVertex] = true;
			
			// amikor a queue-ra tesszuk vs amikor onnet levesszuk akkor jeloljuk meg, mint visited elem
			// mi a kulonbseg??
			// ha a queue-ra tetelkor tesszuk, ugy mukodik
			while (!verticesToBeDiscovered.isEmpty()) {
				Integer currentVertex = verticesToBeDiscovered.poll();
				for (Integer adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
					if (!isVisited[adjacentVertex]) {
						isVisited[adjacentVertex] = true;
						parentLink[adjacentVertex] = currentVertex;
						verticesToBeDiscovered.add(adjacentVertex);
					}
				}
			}
			Iterable<Integer> shortestPath = generateIterableFromParentLink(parentLink, sourceVertex, destinationVertex);
			return shortestPath;
			
		} else {
			return null;
		}
		
		
	}
	
	private static Iterable<Integer> generateIterableFromParentLink(int[] parentLink, int sourceVertex, int destinationVertex) {
		Stack<Integer> parentLinkStack = new Stack<>();
		for (int parent = destinationVertex; parent != sourceVertex; parent = parentLink[parent]) {
			parentLinkStack.push(parent);
		}
		return parentLinkStack;
	}
	
	
	
	
	private static void printVisited(boolean[] isVisited) {
		for (boolean b : isVisited) {
			System.err.println(b);
		}
	}
}
