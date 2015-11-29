package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Test extends TestCase {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new TestSuite(DeliveryTest.class));
	}
}