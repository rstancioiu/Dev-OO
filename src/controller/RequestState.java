package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import view.Window;
import xml.XMLDeserializer;
import xml.XMLException;
import graph.Graph;
import graph.TSP1;
import graph.TSP;
import model.CityMap;
import model.DeliveryRound;
import model.Path;
import model.TypicalDay;

/**
 * State set when the user loads a deliveries file, allow him to compute deliveries
 * Extends DefaultState abstract class
 */
public class RequestState extends DefaultState {

	@Override
	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound, Window window,
			Graph graph) {
		graph.computeShortestPaths();
		TSP tsp = new TSP1();
		tsp.chercheSolution(graph);
		ArrayList<Path> paths = new ArrayList<Path>();
		for (int i = 0; i < graph.getNbNodesDelivery() - 1; ++i) {
			paths.add(graph.getPath(tsp.getSolution(i), tsp.getSolution(i + 1)));
		}
		paths.add(graph.getPath(tsp.getSolution(graph.getNbNodesDelivery() - 1), 0));
		deliveryRound.setPaths(paths);
		window.drawDeliveryRound(deliveryRound, typicalDay);
		Controller.setCurrentState(Controller.deliveryState);
	}

	@Override
	public void loadMap(CityMap map, Window window) {
		map.clear();
		try {
			XMLDeserializer.loadMap(map);
			Controller.setCurrentState(Controller.cityMapState);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLException e) {
			e.printStackTrace();
		}
		window.drawMap(map);
	}

	@Override
	public void loadDeliveries(TypicalDay typicalDay, Window window) {
		typicalDay.clear();
		try {
			XMLDeserializer.loadDeliveries(typicalDay);
			Controller.setCurrentState(Controller.requestState);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLException e) {
			e.printStackTrace();
		}
		window.clearDeliveries();
		window.drawDeliveries(typicalDay);
	}

	@Override
	public void updateVue(Window window) {
		window.disableAll();
		window.enableLoadMap(true);
		window.enableLoadDeliveries(true);
		window.enableCompute(true);
	}

}
