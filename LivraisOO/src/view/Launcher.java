package view;

import controller.Controller;
import model.CityMap;
import model.TypicalDay;

public class Launcher {

	public static void main(String[] args) {
		CityMap map = new CityMap();
		TypicalDay typicalDay = new TypicalDay();
		new Controller(map,typicalDay);
	}
}
