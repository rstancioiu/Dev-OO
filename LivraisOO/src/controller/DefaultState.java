package controller;

import java.io.File;

import graph.Graph;
import model.CityMap;
import model.Delivery;
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

	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound, Window window, Graph graph) {
	}

	public void generateRoadmap(DeliveryRound deliveryRound) {
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
	
	public void clickAddButton(){
	}
	
	public void clickDeleteButton(){
	}
	
	public void clickSwapButton(){
	}
	
	public void confirmAdd(DeliveryRound deliveryRound, Delivery delivery,Node node, Graph graph){	
	}
	
	public void confirmDelete(DeliveryRound deliveryRound, Delivery delivery, TypicalDay typicalDay, Graph graph){
	}
	
	public void confirmSwap(DeliveryRound deliveryRound, Delivery start, Delivery end, Graph graph){
	}
	
}
