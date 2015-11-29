package controller;

import graph.Graph;
import graph.TSP1;
import graph.TSP;
import model.CityMap;
import model.DeliveryRound;
import model.TypicalDay;

public class RequestState extends DefaultState {
	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound) {
		System.out.println("Current state : request state");
		Graph graph = new Graph(map,typicalDay);
		graph.compute();
		for(int i=0;i<graph.getNbNodes();++i){
			for(int j=0;j<graph.getNbNodes();++j)
				System.out.print(graph.getCost(i, j)+" ");
			System.out.println();
		}
		TSP tsp = new TSP1();
		tsp.chercheSolution(graph);
		for(int i=0;i<graph.getNbNodes();++i)
			System.out.println(tsp.getSolution(i));
		Controller.setCurrentState(Controller.deliveryState);
	}
}
