public class ExOverlapBorrow extends Exception{
    public ExOverlapBorrow() {super("The period overlaps with a current period that the member requests the equipment.");}
    public ExOverlapBorrow(String message) {super(message);}
}
