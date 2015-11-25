package controller;

import java.io.File;

import model.Map;
import model.Node;
import view.Window;

public abstract class DefaultState implements State {
	
	//Definition of the default comportaments

	public void loadMap(File mapFile) {}

	public void loadDeliveries(File requestFile) {}

	public void computeDeliveries(Map map, Node deliveryRound) {}

	public void addDelivery(Node deliveryRound, Map m) {}

	public void modifyDelivery(Node d) {}

	public void deleteDelivery(Node deliveryRound, Node d) {}

	public void modifyDelivery(Node deliveryRound, Node d1, Node d2) {}

	public void generateRoadmap() {}

	public void rightClick(Window window, CommandsList cmdList) {}
	
	public void save(Map map, Window window){};
	
	public void open(Map map, CommandsList cmdList, Window window){}
}

