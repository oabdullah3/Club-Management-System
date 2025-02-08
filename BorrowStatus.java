public class BorrowStatus extends Status{

    public BorrowStatus(String s){
        super(s);
    }

    public int compareTo(Status another){ //sort borrow status according to the equipment set code
        String thiseSetCode = this.getStatus().split(" ")[1];
        String anothereSetCode = another.getStatus().split(" ")[1];
        return thiseSetCode.compareTo(anothereSetCode);
    }
}
