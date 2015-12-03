package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Delivery;
import model.TimeWindow;
import model.TypicalDay;

public class TypicalDayTest extends TestCase{

	private TypicalDay typicalDay;
	
	public TypicalDayTest(String name){
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		typicalDay = new TypicalDay();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		typicalDay = null;
	}

	@Test
	public void testTypicalDay() {
		assertNotNull("Instance is created",typicalDay);
	}
	
	public void testAddGetTimeWindow() {
		TimeWindow tm = new TimeWindow(0,24);
		typicalDay.addTimeWindow(tm);
		assertEquals("TimeWindows size is ok",1,typicalDay.getTimeWindows().size());
		assertEquals("TimeWindow is ok",tm,typicalDay.getTimeWindows().get(0));
	}
	
	public void testGetById() {
		TimeWindow tm1 = new TimeWindow(0,24);
		TimeWindow tm2 = new TimeWindow(5,20);
		typicalDay.addTimeWindow(tm1);
		typicalDay.addTimeWindow(tm2);
		assertEquals("TimeWindow is ok",tm1,typicalDay.getById(0));
		assertEquals("TimeWindow is ok",tm2,typicalDay.getById(1));
	}
	
	public void testGetNbDeliveries() {
		TimeWindow tm = new TimeWindow(0,24);
		Delivery d1 = new Delivery(1,1,1,tm);
		Delivery d2 = new Delivery(2,2,2,tm);
		tm.addDelivery(d1);
		tm.addDelivery(d2);
		typicalDay.addTimeWindow(tm);
		assertEquals("Delivery number is ok",2,typicalDay.getNbDeliveries());
	}
	
	public void testGetSetWareHouse() {
		typicalDay.setWareHouse(0);
		assertEquals("Warehouse is ok",0,typicalDay.getWareHouse());
	}
	
	public void clear() {
		TimeWindow tm = new TimeWindow(0,24);
		typicalDay.addTimeWindow(tm);
		assertEquals("TimeWindows size is ok",1,typicalDay.getTimeWindows().size());
		typicalDay.clear();
		assertEquals("TimeWindows size is empty",0,typicalDay.getTimeWindows().size());
	}

}
