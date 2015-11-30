package controller;

import java.util.ArrayList;
import java.util.List;

import view.Window;
import graph.Graph;
import graph.TSP1;
import graph.TSP;
import model.CityMap;
import model.DeliveryRound;
import model.Path;
import model.TypicalDay;

public class RequestState extends DefaultState {
	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound, Window window, Graph graph) {
		graph.computeShortestPaths();
		TSP tsp = new TSP1();
		tsp.chercheSolution(graph);
		ArrayList<Path> paths = new ArrayList<Path>();
		for(int i=0;i<graph.getNbNodesDelivery()-1;++i)
		{
			paths.add(graph.getPath(tsp.getSolution(i), tsp.getSolution(i+1)));
		}
		paths.add(graph.getPath(tsp.getSolution(graph.getNbNodesDelivery()-1),0));
		deliveryRound.setPaths(paths);
		deliveryRound.setTypicalDay(typicalDay);
		window.drawDeliveryRound(deliveryRound, typicalDay);
		
		Controller.setCurrentState(Controller.deliveryState);
	}

	public void updateVue(Window window){
		window.disableAll();
		window.enableLoadMap(true);
		window.enableLoadDeliveries(true);
		window.enableCompute(true);
	}
}
