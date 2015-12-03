package view;

import controller.Controller;
import model.CityMap;
import model.DeliveryRound;
import model.TypicalDay;

/**
 * Launcher class, containing the main method and launching the application
 */
public class Launcher {

	/**
	 * Main method, launches the application
	 * @param args arguments given when the application is launched
	 */
	public static void main(String[] args) {
		CityMap map = new CityMap();
		TypicalDay typicalDay = new TypicalDay();
		DeliveryRound deliveryRound = new DeliveryRound();
		new Controller(map, typicalDay, deliveryRound);
	}
}
