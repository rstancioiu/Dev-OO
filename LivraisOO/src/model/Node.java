package model;

import java.util.ArrayList;

public class Node {

	private int id;
	private int x;
	private int y;
	private ArrayList<Section> outgoing = new ArrayList<Section>();
	private ArrayList<Section> incoming = new ArrayList<Section>();

	/**
	 * A node represents a possible delivery point
	 * 
	 * @param id
	 * @param x
	 * @param y
	 */
	public Node(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	/**
	 * Add section(edge) to successors
	 * 
	 * @param s
	 */
	public void addOutgoing(Section s) {
		outgoing.add(s);
	}

	/**
	 * Add section(edge) to predecessors
	 * 
	 * @param s
	 */
	public void addIncoming(Section s) {
		incoming.add(s);
	}

	/**
	 * Return id of the node
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Return x-coordinate of the node
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Return y-coordinate of the node
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}
}
