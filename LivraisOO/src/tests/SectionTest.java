package tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Section;

public class SectionTest extends TestCase {
	private Section section;
	
	public SectionTest(String name){
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		section = new Section("street", 1, 2, 3.0, 4.0);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		section = null;
	}

	@Test
	public void testSection() {
		assertNotNull("Instance is created", section);
	}
	@Test
	public void testGetStreet() {
		assertEquals("Is that street correct","street", section.getStreet());
	}
	@Test
	public void testGetDeparture() {
		assertEquals("Is that departure correct", 1, section.getDeparture());
	}
	@Test
	public void testGetArrival() {
		assertEquals("Is that arrival correct", 2, section.getArrival());
	}
	@Test
	public void testGetSpeed() {
		assertEquals("Is that speed correct", 3.0, section.getSpeed());
	}
	@Test
	public void testGetLength() {
		assertEquals("Is that length correct", 4.0, section.getLength());
	}

}
