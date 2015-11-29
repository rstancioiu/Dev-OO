package controller;

import java.io.File;

import model.CityMap;
import model.DeliveryRound;
import model.Node;
import model.TypicalDay;
import view.Window;

public abstract class DefaultState implements State {
	
	private Window window;

	public void loadMap(CityMap map,Window window) {
	}

	public void loadDeliveries(TypicalDay typicalDay, Window window) {
	}

	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound, Window window) {
	}

	public void addDelivery(DeliveryRound deliveryRound, CityMap m) {
	}

	public void modifyDelivery(Node d) {
	}

	public void deleteDelivery(DeliveryRound deliveryRound, Node d) {
	}

	public void modifyDelivery(DeliveryRound deliveryRound, Node d1, Node d2) {
	}

	public void generateRoadmap() {
	}

	public void rightClick(Window window, CommandsList cmdList) {
	}

	public void save(CityMap map, Window window) {
	};

	public void open(CityMap map, CommandsList cmdList, Window window) {
	}

	public void confirm(DeliveryRound deliveryRound, Window window) {
	}

	public void cancel() {
	}
}
