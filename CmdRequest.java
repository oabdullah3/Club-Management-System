public class CmdRequest extends RecordedCommand{
    private Member m;
	private Equipment e;
    private String sDay;
    private int requestDays;

	@Override
	public void execute(String[] cmdParts) throws ExInsufficientArguments, ExOverlapRequest, 
	ExMemberNotFound, ExEquipmentRecordNotFound, ExNoAvailableSet, ExIncorrectNumberOfDays, ExInvalidDate
	{
		if (cmdParts.length!=5)
			throw new ExInsufficientArguments();
		
		sDay = cmdParts[3];
			
		Day.validString(sDay);//throws exception if the day string provided is invalid

		//check whether the request days provided are valid
		try{
			requestDays = Integer.parseInt(cmdParts[4]);
		}catch (NumberFormatException e){
			throw new ExIncorrectNumberOfDays("Please provide an integer for the number of days.");
		}
		if(requestDays < 1)
			throw new ExIncorrectNumberOfDays("The number of days must be at least 1.");

		//check whether the member and/or equipment exist
		m = Club.getInstance().searchMembers(cmdParts[1]);
		if( m == null){
			throw new ExMemberNotFound();
		}
		e = Club.getInstance().searchEquipment(cmdParts[2]);
		if( e == null){
			throw new ExEquipmentRecordNotFound();
		}

		e.requestEquipment(m,requestDays, sDay, false);
		addUndoCommand(this);
		clearRedoList(); 
		System.out.println("Done.");
	}
	
	@Override
	public void undoMe()
	{
		e.unrequestEquipment(m,requestDays, sDay);
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe() throws ExNoAvailableSet, ExOverlapBorrow, ExOverlapRequest
	{
		e.requestEquipment(m,requestDays, sDay, true);
		addUndoCommand(this);
    }

}
