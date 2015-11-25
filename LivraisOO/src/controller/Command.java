package controller;

/**
 * Interface implemented by abstract class AbstractCommand
 * @author Heptaswagnome
 *
 */
public interface Command {

	/**
	 * Execute the 'this' command
	 */
	public void doCmd();

	/**
	 * Execute the 'this' reverse command
	 */
	public void undoCmd();
}
