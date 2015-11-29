package controller;

import graph.Graph;
import model.TimeWindow;
import model.Delivery;
import model.DeliveryRound;

/**
 * Abstract class Command, implementing ICommand interface, extended by commands
 * @author Heptaswagnome
 *
 */
public abstract class AbstractCommand implements Command {

	protected TimeWindow timeWindow;
	protected Delivery delivery;
	protected DeliveryRound deliveryRound;
	protected Graph graph;
	
	public AbstractCommand(TimeWindow tw, Delivery d, DeliveryRound dr, Graph g) {
		this.timeWindow = tw;
		this.delivery = d;
		this.deliveryRound = dr;
		this.graph = g;
	}
	
	/**
	 * Do the command
	 */
	@Override
	public abstract void doCmd();
	
	/**
	 * Undo the command
	 */
	@Override
	public abstract void undoCmd();

}
