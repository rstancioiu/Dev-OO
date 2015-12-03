package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * CityMap class, represents the map of a city
 * Contains a list and a map of nodes, and a list of sections
 */
public class CityMap {

	// List of nodes
	private ArrayList<Node> arrayNodes = new ArrayList<Node>();
	private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

	// List of sections
	private ArrayList<Section> sections = new ArrayList<Section>();

	/**
	 * Constructor of CityMap
	 */
	public CityMap() {
		super();
	}

	/**
	 * Add a node to the list of nodes
	 * 
	 * @param n node to add
	 */
	public void addNode(Node n) {
		nodes.put(n.getId(), n);
		arrayNodes.add(n);
	}

	/**
	 * Add a section to the list of sections
	 * 
	 * @param s section to add
	 */
	public void addSection(Section s) {
		sections.add(s);
	}

	/**
	 * Return the list of sections
	 * 
	 * @return the list of sections
	 */
	public ArrayList<Section> getSections() {
		return sections;
	}

	/**
	 * Return a node from an id
	 * 
	 * @param id id of node
	 * @return node by id
	 */
	public Node getNodeById(int id) {
		Node node = nodes.get(id);
		return node;
	}

	/**
	 * Return the list of nodes
	 * 
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