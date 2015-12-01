package graph;

import java.util.Collection;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {

	@Override
	protected double bound(Integer currentNode, Collection<Integer> notSeen) {
		double sumBound = getMinCost(currentNode, notSeen);
		for (Integer i : notSeen) {
			sumBound += getMinCost(i, notSeen);
		}
		return sumBound;
	}

	@Override
	protected Iterator<Integer> iterator(Integer currentNode, Collection<Integer> notSeen, Graph g) {
		return new IteratorSeq(notSeen, currentNode, g);
	}

	/**
	 * Get minimal cost for the next edge used to compute the bound
	 * 
	 * @param currentNode
	 * @param notSeen
	 * @return
	 */
	private double getMinCost(int currentNode, Collection<Integer> notSeen) {
		double minimum = 1e12;
		if (g.isEdge(currentNode, 0)) {
			minimum = g.getCost(currentNode, 0);
		}
		for (Integer i : notSeen) {
			if (g.isEdge(currentNode, i)) {
				if (g.getCost(currentNode, i) < minimum) {
					minimum = g.getCost(currentNode, i);
				}
			}
		}
		return minimum;
	}

}
