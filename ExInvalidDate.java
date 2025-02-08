public class ExInvalidDate extends Exception { 
    public ExInvalidDate() { super("Invalid date."); } 
    public ExInvalidDate(String message) { super("Invalid new day.  The new day has to be later than the current date " + message + "."); }
}
