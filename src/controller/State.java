package controller;

import java.io.File;

import graph.Graph;
import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.TypicalDay;
import view.Window;

public interface State {

	/**
	 * Method called by controller when the button 'Load Map' is clicked. It
	 * loads a map from a .XML file
	 * 
	 * @param mapFile
	 */
	public void loadMap(CityMap map, Window window);

	/**
	 * Method called by controller when the button 'Load Request' is cliked. It
	 * loads the deliveries request from a .XML file
	 * 
	 * @param requestFile
	 */
	public void loadDeliveries(TypicalDay typicalDay, Window window);

	/**
	 * Method called by controller when the button 'Compute Deliveries' is
	 * clicked. It computes the delivery round from a deliveries list
	 * 
	 * @param map
	 * @param deliveryRound
	 */
	public void computeDeliveries(CityMap map, TypicalDay typicalDay,DeliveryRound deliveryRound, Window window, Graph graph);

	/**
	 * Method called by controller when the button 'Generate Roadmap' is hit
	 */
	public void generateRoadmap(DeliveryRound deliveryRound);

	/**
	 * Method called by controller after a right click
	 * 
	 * @param window
	 * @param cmdList
	 */
	public void rightClick(Window window, CommandsList cmdList);
	
	public void confirmAdd(DeliveryRound deliveryRound, Delivery delivery,Node node, TypicalDay typicalDay, Graph graph, CommandsList cmdList);
	
	public void confirmDelete(DeliveryRound deliveryRound,  Delivery delivery, TypicalDay typicalDay, Graph graph, CommandsList cmdList);
	
	public void confirmSwap(DeliveryRound deliveryRound, Delivery start, Delivery end, Graph graph,CommandsList cmdList);
	
	/**
	 * Method called by controller after a right click
	 * 
	 */
	public void cancel();

	/**
	 * Method called by controller to update the view
	 */
	public void updateVue(Window window);

	public void clickAddButton();

	public void clickDeleteButton();

	public void clickSwapButton();

}
