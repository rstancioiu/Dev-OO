package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.CityMap;
import model.Node;
import model.Section;

public class CityMapTest extends TestCase {

	private CityMap cityMap;

	public CityMapTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		cityMap = new CityMap();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		cityMap = null;
	}

	@Test
	public void testCityMap() {
		assertNotNull("Instance is created", cityMap);
	}

	public void testAddGetNode() {
		Node n = new Node(1, 1, 1);
		cityMap.addNode(n);
		assertEquals("Nodes size is ok", 1, cityMap.getNodes().size());
		assertEquals("Node is ok", n, cityMap.getNodes().get(0));
	}

	public void testAddGetSection() {
		Section s = new Section("r1", 1, 1, 3.0, 3.0);
		cityMap.addSection(s);
		assertEquals("Sections size is ok", 1, cityMap.getSections().size());
		assertEquals("Node is ok", s, cityMap.getSections().get(0));
	}

	public void testGetNodeById() {
		Node n = new Node(1, 1, 1);
		cityMap.addNode(n);
		assertEquals("Node is ok", n, cityMap.getNodeById(1));
	}

	public void testClear() {
		Section s = new Section("r1", 1, 1, 3.0, 3.0);
		cityMap.addSection(s);
		Node n = new Node(1, 1, 1);
		cityMap.addNode(n);
		cityMap.clear();
		assertEquals("Nodes are null", 0, cityMap.getNodes().size());
		assertEquals("Sections are null", 0, cityMap.getSections().size());
	}

}
