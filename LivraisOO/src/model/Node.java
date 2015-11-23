package model;

import java.util.ArrayList;

public class Node {
	private int id;
	private int x;
	private int y;
	private ArrayList<TimeWindow> outgoing = new ArrayList<TimeWindow>();
	private ArrayList<TimeWindow> incoming = new ArrayList<TimeWindow>();

	public Node(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public void addOutgoing(TimeWindow s) {
		outgoing.add(s);
	}

	public void addIncoming(TimeWindow s) {
		incoming.add(s);
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
