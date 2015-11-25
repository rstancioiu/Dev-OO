package model;

import java.util.ArrayList;

public class Map {

	// List of nodes
	private ArrayList<Node> nodes = new ArrayList<Node>();

	// List of time windows
	private ArrayList<Section> sections = new ArrayList<Section>();

	private int maxY = 0;
	private double coeff;

	/**
	 * 
	 */
	public Map() {
		super();
	}

	/**
	 * Add a node to the list of nodes
	 * 
	 * @param n
	 */
	public void addNode(Node n) {
		nodes.add(n);
		if (n.getY() > maxY) {
			maxY = n.getY();
		}
	}

	/**
	 * Add a section to the list of sections
	 * 
	 * @param s
	 */
	public void addSection(Section s) {
		sections.add(s);
	}

	/**
	 * Return the list of sections
	 * 
	 * @return
	 */
	public ArrayList<Section> getSections() {
		return this.sections;
	}

	/**
	 * Return node by id
	 * 
	 * @param id
	 * @return
	 */
	public Node getNodeById(int id) {
		return nodes.get(id);
	}

	/**
	 * Return the list of nodes
	 * 
	 * @return
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	/**
	 * Return a coefficient used to paint the points
	 * 
	 * @return
	 */
	public double getCoeff() {
		return 600.0 / maxY;
	}
}