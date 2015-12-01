package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Delivery;
import model.TimeWindow;

public class DeliveryTest extends TestCase {

	private Delivery delivery;

	public DeliveryTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		delivery = new Delivery(1, 2, 3, new TimeWindow(0, 24));
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		delivery = null;
	}

	@Test
	public void testDelivery() {
		assertNotNull("Instance is created", delivery);
	}

	@Test
	public void testGetId() {
		assertEquals("Is that id correct", 1, delivery.getId());
	}

	@Test
	public void testGetClient() {
		assertEquals("Is that client correct", 2, delivery.getClient());
	}

	@Test
	public void testGetAddress() {
		assertEquals("Is that address correct", 3, delivery.getAddress());
	}

}
