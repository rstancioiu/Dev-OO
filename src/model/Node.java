package model;

import java.util.ArrayList;

/**
 * Node class, identified by an id, and two coordinates.
 * It contains a list of outgoing sections, and a list of incoming sections
 */
public class Node {

	private int id;
	private int x;
	private int y;
	private ArrayList<Section> outgoing = new ArrayList<Section>();
	private ArrayList<Section> incoming = new ArrayList<Section>();

	/**
	 * A node represents a possible delivery point
	 * 
	 * @param id id of the node
	 * @param x first coordinate of the node
	 * @param y second coordinate of the node
	 */
	public Node(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	/**
	 * Add an outgoing section
	 * 
	 * @param s outgoing section to add
	 */
	public void addOutgoing(Section s) {
		outgoing.add(s);
	}

	/**
	 * Add an incoming section
	 * 
	 * @param s incoming section to add
	 */
	public void addIncoming(Section s) {
		incoming.add(s);
	}

	/**
	 * Return the id
	 * 
	 * @return id of the node
	 */
	public int getId() {
		return id;
	}

	/**
	 * Return the first coordinate
	 * 
	 * @return x-coordinate of the node
	 */
	public int getX() {
		return x;
	}

	/**
	 * Return the second coordinate
	 * 
	 * @return y-coordinate of the node
	 */
	public int getY() {
		return y;
	}

	/**
	 * Return outgoing sections
	 * 
	 * @return array of outgoing sections
	 */
	public ArrayList<Section> getOutgoing() {
		return outgoing;
	}

	/**
	 * Return incoming sections
	 * 
	 * @return array of incoming sections
	 */
	public ArrayList<Section> getIncoming() {
		return incoming;
	}

}
