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
	 * @param map
	 * @param window
	 */
	public void loadMap(CityMap map, Window window);

	/**
	 * Method called by controller when the button 'Load Request' is cliked. It
	 * loads the deliveries request from a .XML file
	 * 
	 * @param typicalDay
	 * @param window
	 */
	public void loadDeliveries(TypicalDay typicalDay, Window window);

	/**
	 * Method called by controller when the button 'Compute Deliveries' is
	 * clicked. It computes the delivery round from a deliveries list
	 * 
	 * @param map
	 * @param deliveryRound
	 */
	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound, Window window,
			Graph graph);

	/**
	 * Method called by controller when the button 'Generate Roadmap' is clicked
	 */
	public void generateRoadmap(DeliveryRound deliveryRound);

	/**
	 * Method called by controller when the button 'Confirm' is clicked. It adds
	 * a new delivery and recomputes the delivery round.
	 * 
	 * @param deliveryRound
	 * @param delivery
	 * @param node
	 * @param typicalDay
	 * @param graph
	 * @param cmdList
	 */
	public void confirmAdd(DeliveryRound deliveryRound, Delivery delivery, Node node, TypicalDay typicalDay,
			Graph graph, CommandsList cmdList);

	/**
	 * Method called by controller when the button 'Confirm' is clicked. It
	 * removes a delivery and recomputes the delivery round.
	 * 
	 * @param deliveryRound
	 * @param delivery
	 * @param typicalDay
	 * @param graph
	 * @param cmdList
	 */
	public void confirmDelete(DeliveryRound deliveryRound, Delivery delivery, TypicalDay typicalDay, Graph graph,
			CommandsList cmdList);

	/**
	 * Method called by controller when the button 'Confirm' is clicked. It
	 * swaps two deliveries and recomputes the delivery round.
	 * 
	 * @param deliveryRound
	 * @param start
	 * @param end
	 * @param graph
	 * @param cmdList
	 */
	public void confirmSwap(DeliveryRound deliveryRound, Delivery start, Delivery end, Graph graph,
			CommandsList cmdList);

	/**
	 * Method called by controller when the button 'Cancel' is clicked. It
	 * cancels the add/delete/swap command which was started.
	 * 
	 */
	public void cancel();

	/**
	 * Method called by controller to update the view
	 */
	public void updateVue(Window window);

	/**
	 * Method called by controller when the button 'Add' is clicked. It enables
	 * the confirm and cancel buttons and it corresponds to the start of an
	 * addition command.
	 */
	public void clickAddButton();

	/**
	 * Method called by controller when the button 'Delete' is clicked. It
	 * enables the confirm and cancel buttons and it corresponds to the start of
	 * an delete command.
	 */
	public void clickDeleteButton();

	/**
	 * Method called by controller when the button 'Swap' is clicked. It enables
	 * the confirm and cancel buttons and it corresponds to the start of a swap
	 * command.
	 */
	public void clickSwapButton();

}
