public class ExNoAvailableSet extends Exception{
    public ExNoAvailableSet() {super("There is no available set of this equipment for the command.");}
    public ExNoAvailableSet(String message) {super(message);}
}
