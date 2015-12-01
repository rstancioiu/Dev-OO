package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Delivery;
import model.DeliveryRound;
import model.TimeWindow;

public class TimeWindowTest extends TestCase {
	private TimeWindow timeWindow;
	public TimeWindowTest (String name){
		super(name);
	}
	@Before
	public void setUp() throws Exception {
		super.setUp();
		timeWindow = new TimeWindow(1, 2);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		timeWindow = null;
	}

	@Test
	public void testTimeWindow() {
		assertNotNull("Instance is created", timeWindow);
	}
	public void testGetStart() {
		assertEquals("Is that start correct",1, timeWindow.getStart());
	}
	public void testGetEnd() {
		assertEquals("Is that start correct",2, timeWindow.getEnd());
	}
	public void testAddAndGetDeliveries() {
		Delivery d = new Delivery(1, 1, 1, new TimeWindow(0, 24));
		timeWindow.addDelivery(d);
		assertEquals("Is that length correct",1, timeWindow.getDeliveries().size());
		assertEquals("Is that delivery correct",d,  timeWindow.getDeliveries().get(0));
	}
	public void testAddDelivery(){
		int beforeLength = timeWindow.getDeliveries().size();
		Delivery d = new Delivery(2, 2, 2, new TimeWindow(0, 24));
		timeWindow.addDelivery(d);
		ArrayList <Delivery> after = timeWindow.getDeliveries();
		assertEquals("Is that length correct",beforeLength+1, after.size());
		assertEquals("Is that delivery added",d,after.get(beforeLength));
	}
	public void testDeleteDelivery(){
		Delivery d = new Delivery(3, 3, 3, new TimeWindow(0, 24));
		timeWindow.addDelivery(d);
		int beforeLength = timeWindow.getDeliveries().size();
		timeWindow.deleteDelivery(d);
		assertEquals("Is that length correct",beforeLength-1, timeWindow.getDeliveries().size());
		assertFalse("Is that delivery still there",timeWindow.getDeliveries().contains(d));
	}
	public void testSwapDeliveries(){
		int beforeLength = timeWindow.getDeliveries().size();
		Delivery d = new Delivery(4, 4, 4, new TimeWindow(0, 24));
		Delivery d2 = new Delivery(5, 5, 5, new TimeWindow(0, 24));
		timeWindow.addDelivery(d);
		timeWindow.addDelivery(d2);
		timeWindow.swapDeliveries(d,d2);
		assertEquals("Is d2 now before d",d2,timeWindow.getDeliveries().get(beforeLength));
		assertEquals("Is d now after d2",d,timeWindow.getDeliveries().get(beforeLength+1));
	}

}
