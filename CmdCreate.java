public class CmdCreate extends RecordedCommand{
    //We add instance fields in the objects to store the data which will be needed upon undo/redo
	private Equipment e;

	@Override
	public void execute(String[] cmdParts) throws ExInsufficientArguments, ExEquipmentCodeAlreadyInUse
	{
		if (cmdParts.length<3)
			throw new ExInsufficientArguments();

		Equipment existingEquipment = Club.getInstance().searchEquipment(cmdParts[1]);

		if (existingEquipment != null)
			throw new ExEquipmentCodeAlreadyInUse(cmdParts[1],existingEquipment.getName());

		e = new Equipment(cmdParts[1],cmdParts[2]);
		Club.getInstance().addEquipment(e);
		addUndoCommand(this); 
		clearRedoList(); 
		System.out.println("Done.");
	}
	
	@Override
	public void undoMe()
	{
        Club.getInstance().removeEquipment(e);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
        Club.getInstance().addEquipment(e);
		addUndoCommand(this);
	}
}