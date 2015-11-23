package livraisoo;

import java.util.List;

public class Node {
	int id;
	int x;
	int y;
	List<Section> sectionList;
	public Node(int i, int a, int b){
		id = i ;
		x = a;
		y = b;
	}
	public void add(Section s){
		sectionList.add(s);
	}
}
