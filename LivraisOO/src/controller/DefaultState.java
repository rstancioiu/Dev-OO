package controller;

import java.io.File;

import model.CityMap;
import model.Node;
import view.Window;

public abstract class DefaultState implements State {

	public void loadMap(File mapFile) {
	}

	public void loadDeliveries(File requestFile) {
	}

	public void computeDeliveries(CityMap map, Node deliveryRound) {
	}

	public void addDelivery(Node deliveryRound, CityMap m) {
	}

	public void modifyDelivery(Node d) {
	}

	public void deleteDelivery(Node deliveryRound, Node d) {
	}

	public void modifyDelivery(Node deliveryRound, Node d1, Node d2) {
	}

	public void generateRoadmap() {
	}

	public void rightClick(Window window, CommandsList cmdList) {
	}
}
