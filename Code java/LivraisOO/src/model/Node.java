package model;

import java.util.ArrayList;

public class Node {
	int id;
	int x;
	int y;
	ArrayList<Section> outgoing;
	ArrayList<Section> incoming;
	public Node(int i, int a, int b){
		outgoing = new ArrayList<Section>();
		incoming = new ArrayList<Section>();
		id = i ;
		x = a;
		y = b;
	}
	
	public void addOutgoing(Section s){
		outgoing.add(s);
	}
	
	public void addIncoming(Section s){
		incoming.add(s);
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public int getId(){
		return this.id;
	}
}
