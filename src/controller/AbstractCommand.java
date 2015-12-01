package controller;

import graph.Graph;
import model.TimeWindow;
import model.Delivery;
import model.DeliveryRound;

/**
 * Abstract class Command, implementing ICommand interface, extended by commands
 *
 */
public abstract class AbstractCommand implements Command {

	protected Delivery delivery;
	protected DeliveryRound deliveryRound;
	protected Graph graph;

	public AbstractCommand(Delivery d, DeliveryRound dr, Graph g) {
		this.delivery = d;
		this.deliveryRound = dr;
		this.graph = g;
	}

	/**
	 * Do a command
	 */
	@Override
	public abstract void doCmd();

	/**
	 * Undo a command
	 */
	@Override
	public abstract void undoCmd();

}
