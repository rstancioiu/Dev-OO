package controller;

import graph.Graph;
import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.TypicalDay;
import view.Window;

/**
 * DefaultState abstract class, implementing State interface, extended by states
 */
public abstract class DefaultState implements State {

	@Override
	public void loadMap(CityMap map, Window window) {
	}

	@Override
	public void loadDeliveries(TypicalDay typicalDay, Window window) {
	}

	@Override
	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound, Window window,
			Graph graph) {
	}

	@Override
	public void generateRoadmap(DeliveryRound deliveryRound) {
	}

	@Override
	public void cancel() {
	}

	@Override
	public void clickAddButton() {
	}

	@Override
	public void clickDeleteButton() {
	}

	@Override
	public void clickSwapButton() {
	}

	@Override
	public void confirmAdd(DeliveryRound deliveryRound, Delivery delivery, Node node, TypicalDay typicalDay,
			Graph graph, CommandsList cmdList) {
	}

	@Override
	public void confirmDelete(DeliveryRound deliveryRound, Delivery delivery, TypicalDay typicalDay, Graph graph,
			CommandsList cmdList) {
	}

	@Override
	public void confirmSwap(DeliveryRound deliveryRound, Delivery start, Delivery end, Graph graph,
			CommandsList cmdList) {
	}
}
