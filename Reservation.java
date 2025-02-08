public class Reservation implements Comparable<Reservation>{ //Used to define request/borrow object, specify the reservation period and member
    private Day startDay;
    private Day endDay;
    private String mID;

    public String returnID(){
        return this.mID;
    }

    @Override
    public int compareTo(Reservation another){ //enable easier sorting of Requests
        int c = startDay.compareTo(another.startDay);
        if (c == 0){c = endDay.compareTo(another.endDay);}
        return c;
    }

    public Reservation(Day s, Day e, String m){
        this.startDay = s;
        this.endDay = e;
        this.mID = m;
    }

    public Reservation(){
        this.startDay = SystemDate.getInstance().clone();
        this.endDay = SystemDate.getInstance().clone();
        this.mID = null;
    }

    public void set(Day s, Day e, String m){ //change the borrow details for an item
        this.startDay = s;
        this.endDay = e;
        this.mID = m;
    }

    public void reset(){ //make the set avaliable for borrow again
        this.startDay = SystemDate.getInstance();
        this.endDay = SystemDate.getInstance();
        this.mID = null;
    }

    public Boolean equals(Day s, Day e, String m){
        if (((this.endDay.compareTo(e) == 0) && (this.startDay.compareTo(s) == 0)) && (this.mID.compareTo(m) == 0)){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkIfConflict(Day s, Day e){ //very important for checking period overlaps among requests and borrow
        return (this.startDay.compareTo(e) <= 0) && (s.compareTo(this.endDay) <= 0);
    }

    @Override
    public String toString(){
        return startDay.toString() + " to " + endDay.toString();
    }
}
