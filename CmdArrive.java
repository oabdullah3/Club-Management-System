public class CmdArrive extends RecordedCommand{
	
	private String eCode;

	@Override
	public void execute(String[] cmdParts) throws ExInsufficientArguments, ExMissingEquipmentRecord
	{
		if (cmdParts.length!=2)
			throw new ExInsufficientArguments();
				
		Club.getInstance().arriveEquipment(cmdParts[1]);
		eCode = cmdParts[1];
		addUndoCommand(this);
		clearRedoList();
		System.out.println("Done.");
	}
	
	@Override
	public void undoMe()
	{
        Club.getInstance().unArriveEquipment(eCode);
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe() throws ExMissingEquipmentRecord
	{
        Club.getInstance().arriveEquipment(eCode);
		addUndoCommand(this);
	}
}
