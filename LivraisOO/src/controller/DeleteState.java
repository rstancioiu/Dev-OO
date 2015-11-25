package controller;

import model.DeliveryRound;

public class DeleteState extends DefaultState {
	//State reached when the user clicks the delete button
	//The user selects a node in the list
	@Override
	public void confirm(DeliveryRound deliveryRound) {
		//TODO: implémenter le choix dans une combobox et la suppression
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
}
