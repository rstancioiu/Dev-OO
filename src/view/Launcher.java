package view;

import controller.Controller;
import model.CityMap;
import model.DeliveryRound;
import model.TypicalDay;

public class Launcher {

	public static void main(String[] args) {
		CityMap map = new CityMap();
		TypicalDay typicalDay = new TypicalDay();
		DeliveryRound deliveryRound = new DeliveryRound();
		new Controller(map,typicalDay, deliveryRound);
	}
}
