package tests;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import model.Delivery;
import model.DeliveryRound;
import model.Path;
import model.Section;
import model.TimeWindow;

public class DeliveryRoundTest extends TestCase{

	private DeliveryRound deliveryRound;

	public DeliveryRoundTest (String name){
		super(name);
	}
	@Before
	public void setUp() throws Exception {
		super.setUp();
		deliveryRound = new DeliveryRound();
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
		ArrayList <Section> sections = new ArrayList<Section>();
		Delivery d1 = new Delivery(1, 1, 1, new TimeWindow(0, 24));
		Delivery d2 = new Delivery(2, 2, 2, new TimeWindow(0, 24));
		Path path = new Path(d1,d2,sections,0);

		ArrayList <Path> paths = new ArrayList<Path>();
		paths.add(path);
		deliveryRound.setPaths(paths);

		assertEquals("Is that length correct", 1, deliveryRound.getPaths().size());
		assertEquals("Is that path correct", path, deliveryRound.getPaths().get(0));
	}
	public void testGetSetStart() {
		deliveryRound.setStart(3*3600);
		assertEquals("Is that start hour correct",3*3600, deliveryRound.getStart());
	}
	public void testGetSetEnd() {
		deliveryRound.setEnd(20*3600);
		assertEquals("Is that end hour correct",20*3600, deliveryRound.getEnd());
	}
	public void testGetSetDuration() {
		deliveryRound.setDuration(5.0);
		assertEquals("Is that duration correct",5.0, deliveryRound.getDuration());
	}
	@Test
	public void testAddDelivery() {
		fail("Not yet implemented");
	}
	public void testDeleteDelivery() {
		fail("Not yet implemented");
	}
	public void testSwapDelivery() {
		fail("Not yet implemented");
	}
	public void testGetNewId() {
		fail("Not yet implemented");
	}
	/*public void testAddDelivery(){

		Section s1 = new Section("r1",1,2,3.0,3.0);
		ArrayList <Section> sections1 = new ArrayList<Section>();
		sections1.add(s1);
		Section s2 = new Section("r2",2,3,3.0,3.0);
		ArrayList <Section> sections2 = new ArrayList<Section>();
		sections2.add(s2);
		Delivery d1 = new Delivery(1,1,1);
		Delivery d2 = new Delivery(2,2,2);
		Delivery d3 = new Delivery(3,3,3);
		Path path = new Path(d1,d2,sections1);
		Path path2 = new Path(d2,d3,sections2);
		ArrayList <Path> paths = new ArrayList<Path>();
		paths.add(path);
		paths.add(path2);
		deliveryRound.setPaths(paths);

		Delivery d4 = new Delivery(4,4,4);
		addDelivery()
	}*/
	//public void deleteDelivery(Delivery d, Graph graph)
	//public void swapDeliveries(Delivery first, Delivery second, Graph graph)
	//public int getNewID()

}