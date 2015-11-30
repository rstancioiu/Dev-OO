package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Delivery;
import model.Path;
import model.Section;
import model.TimeWindow;

public class PathTest extends TestCase{

	private Path path;
	private Delivery departure;
	private Delivery arrival;
	private ArrayList<Section> sections;

	public PathTest (String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		departure = new Delivery(1, 2, 0, new TimeWindow(0, 24));
		arrival = new Delivery(2, 4, 2, new TimeWindow(0, 24));
		sections = new ArrayList<Section>();
		sections.add(new Section("v0",0,1,4.3,100.0));
		sections.add(new Section("v1",0,2,3.2,50.0));
		sections.add(new Section("v2",1,2,2.8,150.0));
		sections.add(new Section("v3",2,1,2.8,150.0));
		path = new Path(departure, arrival, sections,0);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		path = null;
	}

	@Test
	public void testPath() {
		assertNotNull("Instance is created", path);
	}

	@Test
	public void testGetSetSections() {
		ArrayList<Section > s = new ArrayList<Section>();
		s.add(new Section("v2",1,2,2.8,150.0));
		s.add(new Section("v3",2,1,2.8,150.0));
		path.SetSections(s);
		assertEquals("Is that sections correct", s, path.getSections());

	}

	@Test
	public void testGetSetDeparture() {
		Delivery d = new Delivery (1, 2, 3, new TimeWindow(0, 24));
		path.setDeparture(d);
		assertEquals("Is that departure correct", d, path.getDeparture());
	}

	@Test
	public void testGetSetArrival() {
		Delivery a = new Delivery (1, 2, 3, new TimeWindow(0, 24));
		path.setArrival(a);
		assertEquals("Is that arrival correct", a, path.getArrival());
	}

}
