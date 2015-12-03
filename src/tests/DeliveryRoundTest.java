package tests;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import graph.Graph;
import junit.framework.TestCase;
import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.Path;
import model.Section;
import model.TimeWindow;
import model.TypicalDay;

public class DeliveryRoundTest extends TestCase {

	private DeliveryRound deliveryRound;
	private Path path;
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
	private Graph g;
	private Section ss1;

	public DeliveryRoundTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		deliveryRound = new DeliveryRound();
		
		tm = new TimeWindow(0, 24);
		Node n0 = new Node (0,0,0);
		Node n1 = new Node(1,1,1);
		Node n2 = new Node(2,2,2);
		Node n3 = new Node(3,3,3);

		Section s0 = new Section("r3",0,1,3.0,3.0);
		Section ss0 = new Section("rr1",1,0,3.0,3.0);
		Section s1 = new Section("r1",1,2,3.0,3.0);
		Section s2 = new Section("r2",1,3,3.0,3.0);
		Section s3 = new Section("r3",2,3,3.0,3.0);
		ss1 = new Section("rr1",2,1,3.0,3.0);
		Section ss2 = new Section("rr2",3,1,3.0,3.0);
		Section ss3 = new Section("rr3",3,2,3.0,3.0);
		
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
		cm.addSection(s0);
		cm.addSection(s1);
		cm.addSection(s2);
		cm.addSection(s3);
		cm.addSection(ss0);
		cm.addSection(ss1);
		cm.addSection(ss2);
		cm.addSection(ss3);

		td = new TypicalDay();
		td.setWareHouse(0);
		td.addTimeWindow(tm);
		
		g = new Graph(cm,td);
		
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		deliveryRound = null;
	}

	@Test
	public void testDeliveryRound() {
		assertNotNull("Instance is created", deliveryRound);
	}

	@Test
	public void testGetSetPaths() {
		ArrayList<Section> sections = new ArrayList<Section>();
		path = new Path(d1, d2, sections, 0);
		
		ArrayList<Path> paths = new ArrayList<Path>();
		
		paths.add(path);
		
		deliveryRound.setPaths(paths);
		assertEquals("Is that length correct", 1, deliveryRound.getPaths().size());
		assertEquals("Is that path correct", path, deliveryRound.getPaths().get(0));
	}

	public void testGetSetStart() {
		deliveryRound.setStart(3 * 3600);
		assertEquals("Is that start hour correct", 3 * 3600, deliveryRound.getStart());
	}

	public void testGetSetEnd() {
		deliveryRound.setEnd(20 * 3600);
		assertEquals("Is that end hour correct", 20 * 3600, deliveryRound.getEnd());
	}

	public void testGetSetDuration() {
		deliveryRound.setDuration(5.0);
		assertEquals("Is that duration correct", 5.0, deliveryRound.getDuration());
	}

	public void testDeleteDelivery() {
		deliveryRound.setPaths(paths1);
		Delivery d0 = deliveryRound.getPaths().get(0).getArrival();
		assertEquals("Is the delivery deleted",d1,d0);
		deliveryRound.deleteDelivery(d1, g);
		Delivery d00 = deliveryRound.getPaths().get(0).getArrival();
		assertNotSame("Is the delivery deleted",d1,d00);
		fail("IdK");
	}

	public void testSwapDelivery() {
		deliveryRound.setPaths(paths1);
		deliveryRound.swapDeliveries(d2,d1,g);
		Path path = deliveryRound.getPaths().get(0);
		Delivery dd1 = path.getDeparture();
		Delivery dd2 = path.getArrival();
		assertEquals("Is the first delivery ok",d2,dd1);
		assertEquals("Is the second delivery ok",d1,dd2);
		fail("IdK");
	}

	public void testGetNewId() {
		assertEquals("Is this the first unused id",2,deliveryRound.getNewID());
	}
	public void testAddDelivery(){ 
		deliveryRound.setPaths(paths1);
		//deliveryRound.addDelivery(d1,d3,g,tm);
		Path path = deliveryRound.getPaths().get(0);
		Delivery d = path.getDeparture();
		assertEquals("Is this the added delivery",d3,d);
		fail("IdK");
	}
	// public void swapDeliveries(Delivery first, Delivery second, Graph graph)

}
