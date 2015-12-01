package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class TemplateTSP implements TSP {

	private Integer[] bestSolution;
	protected Graph g;
	private double costBestSolution;

	public void chercheSolution(Graph g) {
		this.g = g;
		bestSolution = new Integer[g.getNbNodesDelivery()];
		Collection<Integer> nonVus = new ArrayList<Integer>(g.getNbNodesDelivery() - 1);
		for (int i = 1; i < g.getNbNodesDelivery(); i++)
			nonVus.add(i);
		Collection<Integer> vus = new ArrayList<Integer>(g.getNbNodesDelivery());
		vus.add(0);
		costBestSolution = 1e12;
		branchAndBound(0, nonVus, vus, 0);
	}

	public Integer getSolution(int i) {
		if (g != null && i >= 0 && i < g.getNbNodesDelivery())
			return bestSolution[i];
		return -1;
	}

	public double getCostSolution() {
		if (g != null)
			return costBestSolution;
		return -1;
	}

	/**
	 * Bound methode
	 * 
	 * @param currentNode
	 * @param notSeen
	 * @return
	 */
	protected abstract double bound(Integer currentNode, Collection<Integer> notSeen);

	protected abstract Iterator<Integer> iterator(Integer currentNode, Collection<Integer> notSeen, Graph g);

	/**
	 * Branch and bound method
	 * 
	 * @param currentNode
	 * @param notSeen
	 * @param seen
	 * @param costSeen
	 */
	private void branchAndBound(int currentNode, Collection<Integer> notSeen, Collection<Integer> seen,
			double costSeen) {
		if (notSeen.size() == 0) {
			if (g.isEdge(currentNode, 0)) {
				if (costSeen + g.getCost(currentNode, 0) < costBestSolution) {
					seen.toArray(bestSolution);
					costBestSolution = costSeen + g.getCost(currentNode, 0);
				}
			}
		} else if (costSeen + bound(currentNode, notSeen) < costBestSolution) {
			Iterator<Integer> it = iterator(currentNode, notSeen, g);
			while (it.hasNext()) {
				Integer nextNode = it.next();
				seen.add(nextNode);
				notSeen.remove(nextNode);
				branchAndBound(nextNode, notSeen, seen, costSeen + g.getCost(currentNode, nextNode));
				seen.remove(nextNode);
				notSeen.add(nextNode);
			}
		}
	}
}
