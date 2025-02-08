public class ExOverlapRequest extends Exception{
    public ExOverlapRequest() {super("The period overlaps with a current period that the member borrows / requests the equipment.");}
    public ExOverlapRequest(String message) {super(message);}
}
