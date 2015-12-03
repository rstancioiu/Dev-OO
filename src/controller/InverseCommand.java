package controller;

/**
 * Command that inverse another command (set command.do = command.undo and conversely)
 * Extends AbstractCommand abstract class
 * Contains a command
 */
public class InverseCommand implements Command {

	private Command cmd;

	/**
	 * Create the reversed command
	 * @param cmd command to inverse
	 */
	public InverseCommand(Command cmd) {
		this.cmd = cmd;
	}

	@Override
	public void doCmd() {
		cmd.undoCmd();
	}

	@Override
	public void undoCmd() {
		cmd.doCmd();
	}

}
