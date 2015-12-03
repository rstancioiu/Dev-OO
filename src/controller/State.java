package controller;

import graph.Graph;
import model.CityMap;
import model.Delivery;
import model.DeliveryRound;
import model.Node;
import model.TypicalDay;
import view.Window;

/**
 * State interface. Defines a pattern followed by every state class
 */
public interface State {

	/**
	 * Method called by controller when the button 'Load Map' is clicked. It
	 * loads a map from a .XML file
	 * @param map map of the city
	 * @param window main window
	 */
	public void loadMap(CityMap map, Window window);

	/**
	 * Method called by controller when the button 'Load Request' is clicked. It
	 * loads the deliveries request from a .XML file
	 * @param typicalDay typical day loaded
	 * @param window main window
	 */
	public void loadDeliveries(TypicalDay typicalDay, Window window);

	/**
	 * Method called by controller when the button 'Compute Deliveries' is
	 * clicked. It computes the delivery round from a deliveries list
	 * 
	 * @param map map of the city
	 * @param deliveryRound delivery round computing
	 */
	public void computeDeliveries(CityMap map, TypicalDay typicalDay, DeliveryRound deliveryRound, Window window,
			Graph graph);

	/**
	 * Method called by controller when the button 'Generate Roadmap' is clicked
	 */
	public void generateRoadmap(DeliveryRound deliveryRound);

	/**
	 * Method called by the controller when the button 'Confirm' is clicked. It adds
	 * a new delivery and recomputes the delivery round.
	 * @param deliveryRound the delivery will be added to this delivery round
	 * @param delivery the delivery which will be added
	 * @param node the delivery will be added to this node
	 * @param typicalDay the delivery will be added to this typical day
	 * @param graph the delivery will be added to this graph
	 * @param cmdList add the add command to this command list
	 */
	public void confirmAdd(DeliveryRound deliveryRound, Delivery delivery, Node node, TypicalDay typicalDay,
			Graph graph, CommandsList cmdList);

	/**
	 * Method called by controller when the button 'Confirm' is clicked. It
	 * removes a delivery and recomputes the delivery round.
	 * @param deliveryRound the delivery will be removed from this delivery round
	 * @param delivery the delivery which will be removed
	 * @param typicalDay the delivery will be removed from this typical day
	 * @param graph the delivery will be removed from this graph
	 * @param cmdList add the delete command to this command list
	 */
	public void confirmDelete(DeliveryRound deliveryRound, Delivery delivery, TypicalDay typicalDay, Graph graph,
			CommandsList cmdList);

	/**
	 * Method called by controller when the button 'Confirm' is clicked. It
	 * swaps two deliveries and recomputes the delivery round.
	 * @param deliveryRound the delivery round containing the swapped deliveries
	 * @param start the first delivery to swap
	 * @param end the second delivery to swap
	 * @param graph the graph containing the swapped deliveries
	 * @param cmdList the swap command will be added to this command list
	 */
	public void confirmSwap(DeliveryRound deliveryRound, Delivery start, Delivery end, Graph graph,
			CommandsList cmdList);

	/**
	 * Method called by controller when the button 'Cancel' is clicked. It
	 * cancels the add/delete/swap command which was started.
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
	 * a delete command.
	 */
	public void clickDeleteButton();

	/**
	 * Method called by controller when the button 'Swap' is clicked. It enables
	 * the confirm and cancel buttons and it corresponds to the start of a swap
	 * command.
	 */
	public void clickSwapButton();

}
