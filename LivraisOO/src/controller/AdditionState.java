package controller;

import model.Delivery;
import model.Map;
import model.Node;
import view.Window;

public class AdditionState extends DefaultState {
	//State reached when the user clicks the add button
	//The user selects a node by clicking on it
	@Override
	public void confirm() {
		//TODO: implémenter le choix dans des combobox et l'ajout
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
}
