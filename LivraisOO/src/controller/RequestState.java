package controller;

import model.Map;
import model.Node;

public class RequestState extends DefaultState {
	@Override
	public void computeDeliveries(Map map, Node deliveryRound){
		//TODO: calcul de la tourn�e
		Controller.setCurrentState(Controller.deliveryState);
	}

}
