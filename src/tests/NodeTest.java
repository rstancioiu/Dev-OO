package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Delivery;
import model.Node;
import model.Section;

public class NodeTest extends TestCase {

	private Node node;

	public NodeTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		node = new Node(1, 2, 3);
	}

	@After
	public void tearDown() throws Exception {
		node = null;
	}

	@Test
	public void testNode() {
		assertNotNull("Instance is created", node);
	}

	public void testAddAndGetOutgoing() {
		Section s = new Section("street", 1, 2, 3.0, 4.0);
		node.addOutgoing(s);
		ArrayList<Section> sections = node.getOutgoing();
		assertEquals("Is that length correct", 1, sections.size());
		assertEquals("Is that section correct", s, sections.get(0));
	}

	public void testAddAndGetIncoming() {
		Section s = new Section("street", 1, 2, 3.0, 4.0);
		node.addIncoming(s);
		ArrayList<Section> sections = node.getIncoming();
		assertEquals("Is that length correct", 1, sections.size());
		assertEquals("Is that section correct", s, sections.get(0));
	}

	public void testGetId() {
		assertEquals("Is that id correct", 1, node.getId());
	}

	public void testGetX() {
		assertEquals("Is that x correct", 2, node.getX());
	}

	public void testGetY() {
		assertEquals("Is that y correct", 3, node.getY());
	}
}
