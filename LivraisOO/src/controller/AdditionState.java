package controller;

import model.Delivery;
import model.DeliveryRound;
import model.Map;
import model.Node;
import view.Window;

public class AdditionState extends DefaultState {
	//State reached when the user clicks the add button
	//The user selects a node in the first list and a preceding delivery in the second list
	@Override
	public void confirm(DeliveryRound deliveryRound) {
		//TODO: implémenter le choix dans des combobox et l'ajout
		//deliveryRound.addDelivery()
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
}
