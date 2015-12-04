package tests;

import java.util.ArrayList;

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

public class GraphTest extends TestCase {
	private Graph graph;
	private Delivery d1;
	private Delivery d2;
	private Delivery d3;
	private ArrayList<Section> sections1;
	private ArrayList<Section> sections2;
	private ArrayList<Section> sections3;
	private Path path1;
	private Path path2;
	private ArrayList<Path> paths1;
	private TimeWindow tm;
	private CityMap cm;
	private TypicalDay td;

	public GraphTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tm = new TimeWindow(0, 24);
		Node n0 = new Node(0, 0, 0);
		Node n1 = new Node(1, 1, 1);
		Node n2 = new Node(2, 2, 2);
		Node n3 = new Node(3, 3, 3);

		Section s1 = new Section("r1", 1, 2, 3.0, 3.0);
		n1.addOutgoing(new Section("r1", 1, 2, 3.0, 3.0));
		n2.addIncoming(new Section("r1", 1, 2, 3.0, 3.0));
		Section s2 = new Section("r2", 1, 3, 3.0, 3.0);
		n1.addOutgoing(new Section("r2", 1, 3, 3.0, 3.0));
		n3.addIncoming(new Section("r2", 1, 3, 3.0, 3.0));
		Section s3 = new Section("r3", 3, 2, 3.0, 3.0);
		n3.addOutgoing(new Section("r3", 3, 2, 3.0, 3.0));
		n2.addIncoming(new Section("r3", 3, 2, 3.0, 3.0));
		Section s4 = new Section("r4", 2, 1, 3.0, 3.0);
		n2.addOutgoing(new Section("r4", 2, 1, 3.0, 3.0));
		n1.addIncoming(new Section("r4", 2, 1, 3.0, 3.0));
		Section s5 = new Section("r5", 1, 0, 3.0, 3.0);
		n1.addOutgoing(new Section("r5", 1, 0, 3.0, 3.0));
		n0.addIncoming(new Section("r5", 1, 0, 3.0, 3.0));
		Section s6 = new Section("r6", 0, 1, 3.0, 3.0);
		n0.addOutgoing(new Section("r6", 0, 1, 3.0, 3.0));
		n1.addIncoming(new Section("r6", 0, 1, 3.0, 3.0));

		sections1 = new ArrayList<Section>();
		sections1.add(s1);
		sections2 = new ArrayList<Section>();
		sections2.add(s2);
		sections3 = new ArrayList<Section>();
		sections3.add(s3);

		d1 = new Delivery(1, 1, 1, tm);
		d2 = new Delivery(2, 2, 2, tm);
		tm.addDelivery(d1);
		tm.addDelivery(d2);

		path1 = new Path(d1, d2, sections1, 1.0);
		// path2 = new Path(d2,d3,sections2,3600);
		paths1 = new ArrayList<Path>();
		paths1.add(path1);
		// paths1.add(path2);

		cm = new CityMap();
		cm.addNode(n1);
		cm.addNode(n2);
		cm.addNode(n3);
		cm.addNode(n0);
		cm.addSection(s1);
		cm.addSection(s2);
		cm.addSection(s3);
		cm.addSection(s4);
		cm.addSection(s5);
		cm.addSection(s6);

		td = new TypicalDay();
		td.setWareHouse(0);
		td.addTimeWindow(tm);
		graph = new Graph(cm, td);
		graph.computeShortestPaths();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		graph = null;
	}

	@Test
	public void testGraph() {
		assertNotNull("Instance is created", graph);
	}

	public void testGetPath() {
		Path path = graph.getPath(1, 2);
		System.out.println(path.getSections().get(0).getArrival() + " " + path.getSections().get(0).getDeparture());
		System.out.println(path1.getSections().get(0).getArrival() + " " + path1.getSections().get(0).getDeparture());

		assertEquals("Path size", path1.getSections().size(), path.getSections().size());
		for (int i = 0; i < path.getSections().size(); ++i) {
			assertEquals("Departure of the section " + i, path1.getSections().get(i).getDeparture(),
					path.getSections().get(i).getDeparture());
			assertEquals("Arrival of the section " + i, path1.getSections().get(i).getArrival(),
					path.getSections().get(i).getArrival());
		}
		System.out.println(path.getDuration()+" "+path1.getDuration());
		assertEquals("Path duration",path.getDuration(),path1.getDuration());
	}

	public void testGetNbNodesDelivery() {
		int nbNodesDelivery = graph.getNbNodesDelivery();
		assertEquals("The number of nodes is ok", 3, nbNodesDelivery);
	}

	public void testGetCost() {
		double d = graph.getCost(1, 2);
		assertEquals("The cost is ok", 1.0, d);
	}

	public void testGetRank() {
		int i = graph.getRank(1);
		assertEquals("The rank of the first delivery is ok", 0, i);
	}

	public void testIsEdge() {
		boolean e = graph.isEdge(1, 2);
		assertTrue("There is an edge", e);
	}

	public void testGetDelivery() {
		Delivery d = graph.getDelivery(2);
		assertEquals("The delivery is found", d2, d);
	}

}
