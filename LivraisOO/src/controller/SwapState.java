package controller;

import graph.Graph;
import model.Delivery;
import model.DeliveryRound;
import model.TimeWindow;
import model.TypicalDay;
import view.Window;

public class SwapState extends DefaultState {
	
	//State reached when the user clicks the swap button
	//The user selects a node in the first list and a node in the second list then hits the confirm button
	@Override
	public void confirmSwap(DeliveryRound deliveryRound, Delivery start, Delivery end, Graph graph) {
		deliveryRound.swapDeliveries(start, end, graph);
		if(start.getTimeWindow().equals(end.getTimeWindow())){
			start.getTimeWindow().swapDeliveries(start, end);
		} else {
			TimeWindow timeWindow1 = start.getTimeWindow();
			TimeWindow timeWindow2 = end.getTimeWindow();
			int pos1 = timeWindow1.getDeliveryPos(start);
			int pos2 = timeWindow2.getDeliveryPos(end);
			timeWindow1.getDeliveries().remove(pos1);
			timeWindow2.getDeliveries().remove(pos2);
			timeWindow1.insertAt(end, pos1);
			timeWindow2.insertAt(start, pos2);
			end.setTimeWindow(timeWindow1);
			start.setTimeWindow(timeWindow2);			
		}
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void cancel(){
		Controller.setCurrentState(Controller.deliveryState);
	}
	
	public void updateVue(Window window){
		window.showButtons();
	}
}
