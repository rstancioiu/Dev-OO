package model;

import java.util.ArrayList;

public class Map {
	 
	private ArrayList<Node> nodes;
	private ArrayList<Section> sections;
	
	public Map (){
		nodes = new ArrayList<Node>();
		sections = new ArrayList<Section>();
	}
	
	public void addNode(Node n){
		nodes.add(n);
	}
	
	public void addSection(Section s){
		sections.add(s);
	}
	
	public ArrayList<Section> getSections(){
		return this.sections;
	}
	
	public Node getNodeById(int id){
		return nodes.get(id);
	}
}