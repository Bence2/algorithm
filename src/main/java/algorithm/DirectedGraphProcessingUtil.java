package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DirectedGraphProcessingUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
