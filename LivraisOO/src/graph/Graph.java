package graph;

public interface Graph {
	
	/**
	 * @return Number of nodes of the graph 
	 */
	public abstract int getNbNodes();
	
	/**
	 * @param i
	 * @param j
	 * @return the cost of the edge (i,j) if (i,j) is an edge; 
	 */
	public abstract double getCost(int i,int j);
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public abstract boolean isEdge(int i,int j);
}
