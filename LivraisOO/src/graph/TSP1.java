package graph;

import java.util.Collection;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {

	@Override
	protected int bound(Integer sommetCourant, Collection<Integer> nonVus) {
		return 0;
	}

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, Collection<Integer> nonVus, Graph g) {
		return new IteratorSeq(nonVus, sommetCrt, g);
	}

}
