package graph;

import java.util.Collection;
import java.util.Iterator;

public class IteratorSeq implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	public IteratorSeq(Collection<Integer> notSeen, int sommetCrt, Graph g){
		this.candidats = new Integer[notSeen.size()];
		Iterator<Integer> it = notSeen.iterator();
		while (it.hasNext()){
			Integer s = it.next();
			if (g.isEdge(sommetCrt, s))
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
	public void remove() {}
}
