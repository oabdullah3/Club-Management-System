import java.util.*;

public abstract class RecordedCommand implements Command{
    public abstract void undoMe(); 
    public abstract void redoMe() throws ExMissingEquipmentRecord, ExOverlapBorrow, ExOverlapRequest, ExNoAvailableSet, ExMemberAlreadyBorrowing;

    private static ArrayList<RecordedCommand> undoList=new ArrayList<>(); 
    private static ArrayList<RecordedCommand> redoList=new ArrayList<>(); 

    protected static void addUndoCommand(RecordedCommand cmd) {undoList.add(cmd);} 
    protected static void addRedoCommand(RecordedCommand cmd) {redoList.add(cmd);}

    protected static void clearRedoList() {redoList.clear();} 

    public static void undoOneCommand(){
        if (undoList.size() == 0){
            System.out.println( "Nothing to undo.");
        }
        else{
            undoList.remove(undoList.size()-1).undoMe();
        }
    } 
    public static void redoOneCommand() throws ExMissingEquipmentRecord, ExOverlapBorrow, ExOverlapRequest, ExNoAvailableSet, ExMemberAlreadyBorrowing{
        if (redoList.size() == 0){
            System.out.println( "Nothing to redo.");
        }
        else{
            redoList.remove(redoList.size()-1).redoMe();
        }
    }
}
