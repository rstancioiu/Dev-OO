package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CityMap {

	// List of nodes
	private ArrayList<Node> arrayNodes = new ArrayList<Node>();
	private HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();

	// List of time windows
	private ArrayList<Section> sections = new ArrayList<Section>();

	private int maxY = 0;
	private double coeff;

	/**
	 * 
	 */
	public CityMap() {
		super();
	}

	/**
	 * Add a node to the list of nodes
	 * 
	 * @param n
	 */
	public void addNode(Node n) {
		nodes.put(n.getId(),n);
		arrayNodes.add(n);
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
		return sections;
	}

	/**
	 * Return node by id
	 * 
	 * @param id
	 * @return
	 */
	public Node getNodeById(int id) {
		Node node = nodes.get(id);
		return node;
	}

	/**
	 * Return the list of nodes
	 * 
	 * @return
	 */
	public ArrayList<Node> getNodes() {
		return arrayNodes;
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