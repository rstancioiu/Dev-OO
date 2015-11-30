package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class TemplateTSP implements TSP {

	private Integer[] meilleureSolution;
	protected Graph g;
	private double coutMeilleureSolution;

	public void chercheSolution(Graph g) {
		this.g = g;
		meilleureSolution = new Integer[g.getNbNodesDelivery()];
		Collection<Integer> nonVus = new ArrayList<Integer>(g.getNbNodesDelivery() - 1);
		for (int i = 1; i < g.getNbNodesDelivery(); i++)
			nonVus.add(i);
		Collection<Integer> vus = new ArrayList<Integer>(g.getNbNodesDelivery());
		vus.add(0);
		coutMeilleureSolution = 1e12;
		branchAndBound(0, nonVus, vus, 0);	
	}

	public Integer getSolution(int i) {
		if (g != null && i >= 0 && i < g.getNbNodesDelivery())
			return meilleureSolution[i];
		return -1;
	}

	public double getCoutSolution() {
		if (g != null)
			return coutMeilleureSolution;
		return -1;
	}

	protected abstract double bound(Integer sommetCourant, Collection<Integer> nonVus);

	protected abstract Iterator<Integer> iterator(Integer sommetCrt, Collection<Integer> nonVus, Graph g);

	private void branchAndBound(int sommetCrt, Collection<Integer> nonVus, Collection<Integer> vus, double coutVus) {
		if (nonVus.size() == 0) {
			if (g.isEdge(sommetCrt, 0)) {
				if (coutVus + g.getCost(sommetCrt, 0) < coutMeilleureSolution) {
					vus.toArray(meilleureSolution);
					for(Integer i : vus)
						System.out.print(i+" ");
					System.out.println();
					coutMeilleureSolution = coutVus + g.getCost(sommetCrt, 0);
				}
			}
		} else if (coutVus + bound(sommetCrt, nonVus) < coutMeilleureSolution) {
			Iterator<Integer> it = iterator(sommetCrt, nonVus, g);
			while (it.hasNext()) {
				Integer prochainSommet = it.next();
				vus.add(prochainSommet);
				nonVus.remove(prochainSommet);
				branchAndBound(prochainSommet, nonVus, vus, coutVus + g.getCost(sommetCrt, prochainSommet));
				vus.remove(prochainSommet);
				nonVus.add(prochainSommet);
			}
		}
	}
}
