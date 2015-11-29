package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import graph.Graph;
import junit.framework.TestCase;
import model.Delivery;
import model.DeliveryRound;
import model.Path;

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
	
	//public void addDelivery(Delivery previous, Delivery newDelivery, Graph graph)
	//public void deleteDelivery(Delivery d, Graph graph)
	//public void swapDeliveries(Delivery first, Delivery second, Graph graph)
	//public void setPaths(ArrayList<Path> p)
	//public void setStart(String s)
	//public void setEnd(String e)
	//public void setDuration(int d)
	//public int getNewID()

}
