package controller;

import java.io.File;

import model.Plan;
import model.Node;
import view.Window;

public abstract class DefaultState implements State {
	
	//Definition of the default comportaments

	public void loadMap(File mapFile) {}

	public void loadDeliveries(File requestFile) {}

	public void computeDeliveries(Plan map, Node deliveryRound) {}

	public void addDelivery(Node deliveryRound, Plan m) {}

	public void modifyDelivery(Node d) {}

	public void deleteDelivery(Node deliveryRound, Node d) {}

	public void modifyDelivery(Node deliveryRound, Node d1, Node d2) {}

	public void generateRoadmap() {}

	public void rightClick(Window window, CommandsList cmdList) {}
	
	public void save(Plan map, Window window){};
	
	public void open(Plan map, CommandsList cmdList, Window window){}
}

