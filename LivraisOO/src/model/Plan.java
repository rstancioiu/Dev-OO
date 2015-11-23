package model;

import java.util.ArrayList;

public class Plan {

	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<TimeWindow> timeWindows = new ArrayList<TimeWindow>();
	int maxY;
	double coeff;

	public Plan() {
		super();
		maxY = 0;
	}

	public void addNode(Node n) {
		nodes.add(n);
		if (n.getY() > maxY) {
			maxY = n.getY();
		}
	}

	public void addTimeWindow(TimeWindow s) {
		timeWindows.add(s);
	}

	public ArrayList<TimeWindow> getTimeWindows() {
		return this.timeWindows;
	}

	public Node getNodeById(int id) {
		return nodes.get(id);
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void display() {
		System.out.println(Integer.toString(nodes.size()) + " noeuds dans le plan");
	}

	public double getCoeff() {
		return 700.0 / maxY;
	}
}