public class CmdRegister extends RecordedCommand{

	private Member m;

	@Override
	public void execute(String[] cmdParts) throws ExInsufficientArguments, ExMemberIdAlreadyInUse
	{
		if (cmdParts.length<3)
			throw new ExInsufficientArguments();

		Member exisitingMember = Club.getInstance().searchMembers(cmdParts[1]);
		if (exisitingMember != null)
			throw new ExMemberIdAlreadyInUse(cmdParts[1],exisitingMember.getName());

		m = new Member(cmdParts[1],cmdParts[2]);
		Club.getInstance().addMember(m);

		addUndoCommand(this);
		clearRedoList();
		System.out.println("Done.");
	}
	
	@Override
	public void undoMe()
	{
        Club.getInstance().removeMember(m);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
        Club.getInstance().addMember(m);
		addUndoCommand(this);
	}
}