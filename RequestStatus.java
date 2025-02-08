public class RequestStatus extends Status{

    public RequestStatus(String s){
        super(s);
    }

    public int compareTo(Status another){ //sort request status accoring to starting date of period
        Day thisStartDay = new Day(this.getStatus().split(" ")[4]);
        Day anotherStartDay = new Day(another.getStatus().split(" ")[4]);
        return thisStartDay.compareTo(anotherStartDay);
    }
}
