public class CmdListEquipment extends RecordedCommand{
	
	@Override
	public void execute(String[] cmdParts)
	{
        Club.getInstance().listClubEquipment();
	}
	
	@Override
	public void undoMe(){}
	
	@Override
	public void redoMe(){}
    
}
