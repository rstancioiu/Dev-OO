package controller;

import model.CityMap;
import model.DeliveryRound;
import model.Node;
import view.Window;

public class DeleteState extends DefaultState {
	//State reached when the user clicks the delete button
	//The user selects a node in the list
	@Override
	public void confirm(DeliveryRound deliveryRound, Window window) {
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void updateVue(Window window){
	}
}
