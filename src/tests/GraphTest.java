package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import graph.Graph;
import junit.framework.TestCase;
import model.CityMap;
import model.Delivery;
import model.Node;
import model.Path;
import model.Section;
import model.TimeWindow;
import model.TypicalDay;

public class GraphTest extends TestCase{
	private Graph graph;
	private Delivery d1;
	private Delivery d2;
	private Delivery d3;
	private ArrayList <Section> sections1;
	private ArrayList <Section> sections2;
	private ArrayList <Section> sections3;
	private Path path1;
	private Path path2;
	private ArrayList <Path> paths1;
	private TimeWindow tm;
	private CityMap cm;
	private TypicalDay td;
	public GraphTest(String name){
		super(name);
	}
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tm = new TimeWindow(0, 24);
		Node n0 = new Node (0,0,0);
		Node n1 = new Node(1,1,1);
		Node n2 = new Node(2,2,2);
		Node n3 = new Node(3,3,3);
		
		Section s1 = new Section("r1",1,2,3.0,3.0);
		Section s2 = new Section("r2",1,3,3.0,3.0);
		Section s3 = new Section("r3",2,3,3.0,3.0);
		
		sections1= new ArrayList<Section>();
		sections1.add(s1);
		sections2 = new ArrayList<Section>();
		sections2.add(s2);
		sections3 = new ArrayList<Section>();
		sections3.add(s3);
		
		d1 = new Delivery(1, 1, 1, tm);
		d2 = new Delivery(2, 2, 2, tm);
		tm.addDelivery(d1);
		tm.addDelivery(d2);
		
		d3 = new Delivery(3, 3, 3, tm);
		 
		path1 = new Path(d1,d2,sections1,3600); 
		path2 = new Path(d2,d3,sections2,3600);
		paths1 = new ArrayList<Path>();
		paths1.add(path1);
		//paths1.add(path2);

		cm = new CityMap();
		cm.addNode(n1);
		cm.addNode(n2);
		cm.addNode(n3);
		cm.addNode(n0);
		cm.addSection(s1);
		cm.addSection(s2);
		cm.addSection(s3);

		td = new TypicalDay();
		td.setWareHouse(0);
		td.addTimeWindow(tm);
		graph = new Graph(cm,td);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		graph = null;
	}

	@Test
	public void testGraph() {
		assertNotNull("Instance is created",graph);
	}
	public void testComputeShortestPaths() {
		fail("Not yet implemented");
	}
	public void testGetPath() {
		fail("Not yet implemented");
	}
	public void testGetNbNodesDelivery() {
		fail("Not yet implemented");
	}
	public void testGetCost() {
		fail("Not yet implemented");
	}
	public void testGetRank() {
		fail("Not yet implemented");
	}
	public void testIsEdge() {
		fail("Not yet implemented");
	}
	public void testGeneratePath() {
		fail("Not yet implemented");
	}
	public void testGetDelivery() {
		fail("Not yet implemented");
	}

}
