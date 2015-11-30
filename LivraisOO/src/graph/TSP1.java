package graph;

import java.util.Collection;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {

	@Override
	protected double bound(Integer sommetCourant, Collection<Integer> nonVus) {
		double sumBound = getMinCost(sommetCourant, nonVus);
		for (Integer i : nonVus) {
			sumBound += getMinCost(i, nonVus);
		}
		return sumBound;
	}

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, Collection<Integer> nonVus, Graph g) {
		return new IteratorSeq(nonVus, sommetCrt, g);
	}

	private double getMinCost(int currentNode, Collection<Integer> nonVus) {
		double minimum = 1e12;
		if (g.isEdge(currentNode, 0)) {
			minimum = g.getCost(currentNode, 0);
		}
		for (Integer i : nonVus) {
			if (g.isEdge(currentNode, i)) {
				if (g.getCost(currentNode, i) < minimum) {
					minimum = g.getCost(currentNode, i);
				}
			}
		}
		return minimum;
	}

}
