public class CmdStartNewDay extends RecordedCommand{
	
    private String oldDay;
    private String newDay;
	
	@Override
	public void execute(String[] cmdParts) throws ExInvalidDate, ExInsufficientArguments
	{
		if (cmdParts.length<2)
			throw new ExInsufficientArguments();

		Day.validString(cmdParts[1]); //throws exception if the day string provided is invalid
					
		newDay = cmdParts[1];
		oldDay = SystemDate.getInstance().toString();
		SystemDate.getInstance().set(newDay);

		addUndoCommand(this);
		clearRedoList();
		System.out.println("Done.");
	}
	
	@Override
	public void undoMe()
	{
        SystemDate.getInstance().set(oldDay);
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe()
	{
        SystemDate.getInstance().set(newDay);
		addUndoCommand(this);
	}
}
