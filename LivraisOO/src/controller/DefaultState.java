package controller;

import java.io.File;

import model.DeliveryRound;
import model.Map;
import model.Node;
import view.Window;

public abstract class DefaultState implements State {
	
	public void loadMap(Window window) {}

	public void loadDeliveries() {}

	public void computeDeliveries(Map map, Node deliveryRound) {}

	public void addDelivery(Node deliveryRound, Map m) {}

	public void modifyDelivery(Node d) {}

	public void deleteDelivery(Node deliveryRound, Node d) {}

	public void modifyDelivery(Node deliveryRound, Node d1, Node d2) {}

	public void generateRoadmap() {}

	public void rightClick(Window window, CommandsList cmdList) {}
	
	public void save(Map map, Window window){};
	
	public void open(Map map, CommandsList cmdList, Window window){}
	
	public void confirm(DeliveryRound deliveryRound){}
	
	public void cancel(){}
}

