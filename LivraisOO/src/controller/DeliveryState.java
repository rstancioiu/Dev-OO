package controller;

import graph.GraphComplete;
import model.CityMap;
import model.Node;
import model.TypicalDay;

public class DeliveryState extends DefaultState {
	
	public void computeDeliveries(CityMap map, TypicalDay typicalDay) {
		GraphComplete graph = new GraphComplete(map,typicalDay);
		graph.compute();
	}
}
