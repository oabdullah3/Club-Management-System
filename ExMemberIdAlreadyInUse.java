public class ExMemberIdAlreadyInUse extends Exception { 
    public ExMemberIdAlreadyInUse(String eCode, String eName) { super("Member ID already in use: " + eCode + " " + eName); } 
    public ExMemberIdAlreadyInUse(String message) { super(message); }
}