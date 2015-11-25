package controller;

import model.DeliveryRound;

public class SwapState extends DefaultState {
	//State reached when the user clicks the swap button
	//The user selects a node in the first list and a node in the second list then hits the confirm button
	@Override
	public void confirm(DeliveryRound deliveryRound) {
		//TODO: implémenter le choix dans des combobox et l'échange
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
}
