package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * IteratorSeq class, used to browse threw an integer collection Contains an
 * array of integer and its length as an integer Implements Iterator<Integer>
 */
public class IteratorSeq implements Iterator<Integer> {

	private int nbCandidats;
	private List<Edge> candidats;
	private Graph g;

	/**
	 * Constructor of the class
	 * 
	 * @param notSeen
	 * @param currentNode
	 * @param g
	 */
	public IteratorSeq(Collection<Integer> notSeen, int currentNode, Graph g) {
		this.g = g;
		nbCandidats = 0;
		candidats = new ArrayList<Edge>();
		Iterator<Integer> it = notSeen.iterator();
		while (it.hasNext()) {
			Integer s = it.next();
			if (g.isEdge(currentNode, s))
			{
				Double d = g.getCost(currentNode, s);
				candidats.add(new Edge(s, d));
			}
		}
		Collections.sort(candidats);
	}

	@Override
	public boolean hasNext() {
		return nbCandidats < candidats.size();
	}

	@Override
	public Integer next() {
		return candidats.get(nbCandidats++).getNode();
	}

	@Override
	public void remove() {
	}

	private class Edge implements Comparable<Edge> {

		private int node;
		private Double cost;

		public Edge(int node, Double cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge edge2) {
			return cost.compareTo(edge2.cost);
		}

		public int getNode() {
			return node;
		}
	}
}