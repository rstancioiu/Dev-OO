package graph;

public interface TSP {
	
	public void chercheSolution(int tpsLimite, Graph g);
	
	public Integer getSolution(int i);
	
	public double getCoutSolution();
}
