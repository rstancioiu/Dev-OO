package controller;

public class InverseCmd implements ICommand {

	private ICommand cmd;

	/**
	 * Create the inverse command of the command cmd (in a way the cmd.doCmd
	 * correspond to this.undoCmd and the other wat)
	 * 
	 * @param cmd
	 */
	public InverseCmd(ICommand cmd) {
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
