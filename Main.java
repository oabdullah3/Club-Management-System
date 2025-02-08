import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

	public static void main(String [] args) throws FileNotFoundException {	
		
		Scanner in = new Scanner(System.in);
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		Scanner inFile = null;
		
		try {

			inFile = new Scanner(new File(filepathname));
			String cmdLine1 = inFile.nextLine();
			String[] cmdLine1Parts = cmdLine1.split(" ");
			System.out.println("\n> "+cmdLine1);
			SystemDate.createTheInstance(cmdLine1Parts[1]);
			
			while (inFile.hasNext()) {
				String cmdLine = inFile.nextLine().trim();
				
				if (cmdLine.equals("")) continue;  

				System.out.println("\n> "+cmdLine);
				
				String[] cmdParts = cmdLine.split(" "); 

				try{
					if (cmdParts[0].equals("register"))
						(new CmdRegister()).execute(cmdParts);
					else if (cmdParts[0].equals("create"))
						(new CmdCreate()).execute(cmdParts);
					else if (cmdParts[0].equals("borrow"))
						(new CmdBorrow()).execute(cmdParts);
					else if (cmdParts[0].equals("request"))
						(new CmdRequest()).execute(cmdParts);
					else if (cmdParts[0].equals("arrive"))
						(new CmdArrive()).execute(cmdParts);
					else if (cmdParts[0].equals("listMembers"))
						(new CmdListMembers()).execute(cmdParts);
					else if (cmdParts[0].equals("listEquipment"))
						(new CmdListEquipment()).execute(cmdParts);
					else if (cmdParts[0].equals("listMemberStatus"))
						(new CmdListMemberStatus()).execute(cmdParts);
					else if (cmdParts[0].equals("listEquipmentStatus"))
						(new CmdListEquipmentStatus()).execute(cmdParts);
					else if (cmdParts[0].equals("startNewDay"))
						(new CmdStartNewDay()).execute(cmdParts);
					else if (cmdParts[0].equals("undo"))
						RecordedCommand.undoOneCommand();
					else if (cmdParts[0].equals("redo"))
						RecordedCommand.redoOneCommand();
					else
						throw new ExUnknownCommand();

				} catch (ExInsufficientArguments e) {
					System.out.println(e.getMessage());
				} catch (ExMemberIdAlreadyInUse e) {
					System.out.println(e.getMessage());
				} catch (ExEquipmentCodeAlreadyInUse e) {
					System.out.println(e.getMessage());
				} catch (ExOverlapBorrow e) {
					System.out.println(e.getMessage());
				} catch (ExOverlapRequest e) {
					System.out.println(e.getMessage());
				} catch (ExNoAvailableSet e) {
					System.out.println(e.getMessage());
				} catch (ExMemberNotFound e) {
					System.out.println(e.getMessage());
				} catch (ExEquipmentRecordNotFound e) {
					System.out.println(e.getMessage());
				} catch (ExIncorrectNumberOfDays e) {
					System.out.println(e.getMessage());
				} catch (ExInvalidDate e) {
					System.out.println(e.getMessage());
				} catch (ExMissingEquipmentRecord e) {
					System.out.println(e.getMessage());
				} catch (ExMemberAlreadyBorrowing e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ExUnknownCommand e) {
			System.out.println(e.getMessage());
		} finally{
			if (inFile!=null)
            	inFile.close(); 
			in.close();
		}
	}
}
