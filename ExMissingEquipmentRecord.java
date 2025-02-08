public class ExMissingEquipmentRecord extends Exception { 
    public ExMissingEquipmentRecord(String eCode) { super("Missing record for Equipment " + eCode + ".  Cannot mark this item arrival."); }
}
