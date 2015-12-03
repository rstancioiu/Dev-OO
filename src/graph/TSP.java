package graph;

/**
 * TSP interface. Defines a pattern followed by every TSP class
 *
 */
public interface TSP {

	/**
	 * Search a solution in the given Graph
	 * 
	 * @param g given graph
	 */
	public void chercheSolution(Graph g);

	/**
	 * @param i
	 * @return the integer found at index i in the best solution found
	 */
	public Integer getSolution(int i);

	/**
	 * @return the cost of the best solution found
	 */
	public double getCostSolution();
}
