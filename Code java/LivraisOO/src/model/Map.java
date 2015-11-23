package model;

import java.util.ArrayList;

public class Map {
	 
	private ArrayList<Node> nodes;
	private ArrayList<Section> sections;
	int maxY;
	double coeff;
	
	public Map (){
		nodes = new ArrayList<Node>();
		sections = new ArrayList<Section>();
		maxY = 0;
	}
	
	public void addNode(Node n){
		nodes.add(n);
		if(n.getY()>maxY){
			maxY = n.getY();
		}
	}
	
	public void addSection(Section s){
		sections.add(s);
	}
	
	public ArrayList<Section> getSections(){
		return this.sections;
	}
	
	public ArrayList<Node> getNodes(){
		return this.nodes;
	}
	
	public Node getNodeById(int id){
		return nodes.get(id);
	}
	
	public void display(){
		System.out.println(Integer.toString(nodes.size())+" noeuds dans le plan");
	}
	public double getCoeff(){
		return 700.0/maxY;
	}
}