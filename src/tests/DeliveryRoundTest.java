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

public class DeliveryRoundTest extends TestCase {

	private DeliveryRound deliveryRound;

	public DeliveryRoundTest(String name) {
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
		ArrayList<Section> sections = new ArrayList<Section>();
		Delivery d1 = new Delivery(1, 1, 1, new TimeWindow(0, 24));
		Delivery d2 = new Delivery(2, 2, 2, new TimeWindow(0, 24));
		Path path = new Path(d1, d2, sections, 0);

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
}
