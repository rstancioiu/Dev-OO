package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CityMap {

	// List of nodes
	private ArrayList<Node> arrayNodes = new ArrayList<Node>();
	private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

	// List of time windows
	private ArrayList<Section> sections = new ArrayList<Section>();

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
		nodes.put(n.getId(), n);
		arrayNodes.add(n);
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
	 * @return list of sections
	 */
	public ArrayList<Section> getSections() {
		return sections;
	}

	/**
	 * @param id
	 * @return node by id
	 */
	public Node getNodeById(int id) {
		Node node = nodes.get(id);
		return node;
	}

	/**
	 * @return the list of nodes
	 */
	public ArrayList<Node> getNodes() {
		return arrayNodes;
	}

	/**
	 * Clear the map
	 */
	public void clear() {
		arrayNodes.clear();
		nodes.clear();
		sections.clear();
	}

}