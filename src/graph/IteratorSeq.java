package graph;

import java.util.Collection;
import java.util.Iterator;

public class IteratorSeq implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Constructor of the Iterator which is used to browse threw an integer
	 * collection
	 * 
	 * @param notSeen
	 * @param currentNode
	 * @param g
	 */
	public IteratorSeq(Collection<Integer> notSeen, int currentNode, Graph g) {
		this.candidats = new Integer[notSeen.size()];
		Iterator<Integer> it = notSeen.iterator();
		while (it.hasNext()) {
			Integer s = it.next();
			if (g.isEdge(currentNode, s))
				candidats[nbCandidats++] = s;
		}
	}

	@Override
	public boolean hasNext() {
		return nbCandidats > 0;
	}

	@Override
	public Integer next() {
		nbCandidats--;
		return candidats[nbCandidats];
	}

	@Override
	public void remove() {
	}
}