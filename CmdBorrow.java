public class CmdBorrow extends RecordedCommand{
	private Member m;
	private Equipment e;
	private int borrowDays;
	@Override
	public void execute(String[] cmdParts) throws ExInsufficientArguments, ExMemberAlreadyBorrowing, ExOverlapBorrow, 
	ExMemberNotFound, ExEquipmentRecordNotFound, ExNoAvailableSet, ExIncorrectNumberOfDays
	{
		if (cmdParts.length<3)
			throw new ExInsufficientArguments();
		
		borrowDays = 0;

		//check if borrow days value provided is valid or not
		if (cmdParts.length==4){
			try{
				borrowDays = Integer.parseInt(cmdParts[3]);
			}catch (NumberFormatException e){
				throw new ExIncorrectNumberOfDays("Please provide an integer for the number of days.");
			}
			if(borrowDays < 1)
				throw new ExIncorrectNumberOfDays("The number of days must be at least 1.");
		}
		else{
			borrowDays = 7;
		}

		//check whether the member and/or equipment exist
		m = Club.getInstance().searchMembers(cmdParts[1]);
		if( m == null){
			throw new ExMemberNotFound();
		}
		e = Club.getInstance().searchEquipment(cmdParts[2]);
		if( e == null){
			throw new ExEquipmentRecordNotFound();
		}

		e.borrowEquipment(m,false,borrowDays);
		addUndoCommand(this);
		clearRedoList(); 
		System.out.println("Done.");
	}
	
	@Override
	public void undoMe()
	{
		e.unborrowEquipment(m);
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe() throws ExOverlapBorrow, ExOverlapRequest, ExNoAvailableSet, ExMemberAlreadyBorrowing
	{
		e.borrowEquipment(m,true,borrowDays);
		addUndoCommand(this);
	}
}
