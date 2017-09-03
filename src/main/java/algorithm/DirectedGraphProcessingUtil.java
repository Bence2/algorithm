package algorithm;

import com.google.common.collect.Iterables;

import java.util.*;

public class DirectedGraphProcessingUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static <T> Iterable<T> topologicalOrdering(DirectedGraph<T> diGraph) {
		// a symbol tableben a graph keyekhez csak a kimeno elek vannak meg
		// kell egy, amiben a bejovo elek is megvannak
		Stack<T> topologicalOrder = new Stack<>();
        DirectedGraph<T> reversedDiGraph = reverseDiGraph(diGraph);
        Queue<T> vertexToBeVisited = new LinkedList<>();
        vertexToBeVisited.addAll(getSinkVertices(reversedDiGraph));
        int topologicalCounter = 0;
        while (!vertexToBeVisited.isEmpty()) {
        	Queue<T> newVertexToBeVisited = new LinkedList<>();
			for (T vertex : vertexToBeVisited) {
				for (T incomingEdgeVertex: diGraph.getAdjacentVertices(vertex)) {
					newVertexToBeVisited.add(incomingEdgeVertex);
					topologicalOrder.push(incomingEdgeVertex);
					topologicalCounter++;
				}
			}
			vertexToBeVisited = newVertexToBeVisited;
        }
        return topologicalOrder;
	}

	private static <T> List<T> getSinkVertices(DirectedGraph<T> reversedDiGraph) {
	    List<T> verticesToBeReturned = null;
	    for (T vertex : reversedDiGraph.getVertices()) {
	        if (Iterables.isEmpty(reversedDiGraph.getAdjacentVertices(vertex))) {
	            verticesToBeReturned.add(vertex);
            }
        };
	    return verticesToBeReturned;
    }
	
	private static <T> DirectedGraph<T> reverseDiGraph(DirectedGraph<T> diGraph) {
		DirectedGraph<T> reversedDiGrap = new DirectedGraph<>();
		for (T keyVertex : diGraph.getVertices()) {
			for (T adjacentVertex : diGraph.getAdjacentVertices(keyVertex)) {
				reversedDiGrap.addEdge(adjacentVertex, keyVertex);
			}
		}
		return reversedDiGrap;
	}
	
	public static <T> Iterable<T> hasCycle(DirectedGraph<T> diGraph) {
		
		// nem csak eszre kene venni a cycle-t, hanem vissza is adni
		// kene egy topological sortot is nyomni
		
		Map<T, Boolean> isMarked = new HashMap<>();
		initBooleanValueMap(diGraph.getVertices(), isMarked);
		Map<T, T> edgeTo = new HashMap<>();
		
		for (T vertex : diGraph.getVertices()) {
			// dfs-sel az adott resz felderitese
			if (!isMarked.get(vertex)) {
				//
				Map<T, Boolean> isPoppedFromStack = new HashMap<>();
				initBooleanValueMap(diGraph.getVertices(), isPoppedFromStack);
				
				Stack<T> verticesToBeDiscovered = new Stack<>();
				verticesToBeDiscovered.push(vertex);
				
				isMarked.put(vertex, true);
//				isPoppedFromStack.put(vertex, true);
				
				while (!verticesToBeDiscovered.isEmpty()) {
					T currentVertex = verticesToBeDiscovered.pop();
					isPoppedFromStack.put(currentVertex, true);
					
					for (T adjacentVertex : diGraph.getAdjacentVertices(currentVertex)) {
						edgeTo.put(adjacentVertex, currentVertex);
						if (isPoppedFromStack.get(adjacentVertex)) {
							return getCycle(edgeTo, adjacentVertex);
						} else {
							isMarked.put(adjacentVertex, true);
							verticesToBeDiscovered.push(adjacentVertex);
						}
					}
				}
			}
		}
		
		return null;
	}
	
	
	private static <T> Iterable<T> getCycle(Map<T, T> parentLink, T lastVertex) {
		Stack<T> cycle = new Stack<>();
//		for (int i = 0; i < array.length; i++) {
//		}
//		for (T currentVertex = lastVertex; lastVertex != currentVertex; currentVertex = parentLink.get(currentVertex)) {
//			cycle.push(currentVertex);
//		}
		T currentVertex = parentLink.get(lastVertex);
		while (lastVertex != currentVertex) {
			cycle.push(currentVertex);
			currentVertex = parentLink.get(currentVertex);
		}
		cycle.push(lastVertex);
		
		return cycle;
	}
	
	private static <T> void initBooleanValueMap(Iterable<T> keySet, Map<T, Boolean> map) {
		for (T key : keySet) {
			map.put(key, false);
		}
	}

}
