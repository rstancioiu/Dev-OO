package controller;

import model.CityMap;
import model.Node;

public class RequestState extends DefaultState {
	@Override
	public void computeDeliveries(CityMap map, Node deliveryRound){
		//TODO: calcul de la tourn�e
		Controller.setCurrentState(Controller.deliveryState);
	}

}
