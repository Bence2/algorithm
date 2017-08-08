package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DirectedGraphProcessingUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static <T> boolean hasCycle(DirectedGraph<T> diGraph) {
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
				isPoppedFromStack.put(vertex, true);
				
				while (!verticesToBeDiscovered.isEmpty()) {
					T currentVertex = verticesToBeDiscovered.pop();
					isPoppedFromStack.put(vertex, true);
					
					for (T adjacentVertex : diGraph.getAdjacentVertices(currentVertex)) {
						if (isPoppedFromStack.get(adjacentVertex)) {
							return true;
						} else {
							isMarked.put(adjacentVertex, true);
							verticesToBeDiscovered.push(adjacentVertex);
							edgeTo.put(adjacentVertex, currentVertex);
						}
					}
				}
			}
		}
		
		return false;
	}
	
	private static <T> void initBooleanValueMap(Iterable<T> keySet, Map<T, Boolean> map) {
		for (T key : keySet) {
			map.put(key, false);
		}
	}

}
