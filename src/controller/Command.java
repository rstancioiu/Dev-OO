package controller;

/**
 * Interface implemented by abstract class AbstractCommand
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
