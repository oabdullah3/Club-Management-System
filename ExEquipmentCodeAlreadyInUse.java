public class ExEquipmentCodeAlreadyInUse extends Exception { 
    public ExEquipmentCodeAlreadyInUse(String memberID, String memberName) { super("Equipment code already in use: " + memberID + " " + memberName); } 
    public ExEquipmentCodeAlreadyInUse(String message) { super(message); }
}