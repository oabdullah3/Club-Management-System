import java.util.ArrayList;
import java.util.Collections;
public class EquipmentSet implements Comparable<EquipmentSet>{
    
    private String eCode_Set;
    private Boolean available;
    private String memberID;
    private Reservation borrow;
    private String currentStatus;
    private ArrayList<Reservation> requestList;

    public EquipmentSet(String eCode, int setNumber){
        this.eCode_Set = eCode + "_" + Integer.toString(setNumber);
        this.available = true;
        this.currentStatus = "Available";
        this.requestList = new ArrayList<>();
        this.borrow = new Reservation();
        this.memberID = null;
    }

    public String geteCode_Set(){return this.eCode_Set;}
    public String getMemberID() {return this.memberID;}
    public String getcurrentStatus(){return this.currentStatus;}

    public String getRequestPeriods(){
        String returnString = "";
        for (int i=0;i<requestList.size();i++) {
            returnString += requestList.get(i).toString();
            if (i!=requestList.size()-1)
                returnString += ", ";
        }
        return returnString;
    }

    @Override
    public int compareTo(EquipmentSet another) {
        return this.eCode_Set.compareTo(another.eCode_Set);
    }

    public Boolean borrowSet(Day startDay, Day endDay, Member m, String eName, Boolean redo) throws ExMemberAlreadyBorrowing, ExOverlapBorrow{
        String mID = m.getMemberID();

        Boolean overlap = false;
        if (!this.available){
            if (borrow.returnID()==mID){
                if (borrow.checkIfConflict(startDay,endDay)){
                    throw new ExMemberAlreadyBorrowing(); 
                }
                else{
                    overlap = true;
                }
            }
            else{
                overlap = borrow.checkIfConflict(startDay,endDay);
            }
        }else{
            for (Reservation rl : requestList) {
                if (mID == rl.returnID()){
                    if (rl.checkIfConflict(startDay,endDay)){
                        throw new ExOverlapBorrow(); 
                    }
                } else{
                    overlap = rl.checkIfConflict(startDay,endDay);
                    if (overlap) break;
                }
            }
        }

        if (!overlap){
            String mName = m.getName();
            this.available = false;
            this.memberID = mID;
            this.borrow.set(startDay,endDay,mID);
            String period = borrow.toString();
            String memberStatus = "borrows " + eCode_Set + " (" + eName + ")" + " for " +  period;
            this.currentStatus = mID + " "+ mName + " borrows for " + period;
            m.addBorrowStatus(memberStatus);
            if (!redo)
                System.out.println(mID + " "+ mName + " " + memberStatus);
            return true;
        }
        return false;
    }

    public void unborrowSet(Member m){
        this.available = true;
        this.borrow.reset();
        this.memberID = null;
        this.currentStatus = "Available";
        m.removeBorrowStatus(eCode_Set);
    }

    public Boolean requestSet(Day startDay, Day endDay, Member m, String eName, Boolean redo) throws ExOverlapRequest{
        
        String mID = m.getMemberID();

        Boolean overlap = false;
        
        if (borrow.checkIfConflict(startDay,endDay)){
            if (borrow.returnID() == mID){
                throw new ExOverlapRequest(); 
            }
            overlap = true;
        }

        for (Reservation rl : requestList) {
            if (mID == rl.returnID()){
                if (rl.checkIfConflict(startDay,endDay)){
                    throw new ExOverlapRequest(); 
                }
            } else{
                overlap = rl.checkIfConflict(startDay,endDay);
                if (overlap) break;
            }
        }

        if (!overlap){
            String mName = m.getName(); 
            Reservation r = new Reservation(startDay,endDay,mID);
            String memberStatus = "requests " + eCode_Set + " (" + eName + ")" + " for " +  r.toString();
            m.addRequestStatus(memberStatus);
            requestList.add(r);
            Collections.sort(requestList);
            if (!redo)
                System.out.println(mID + " "+ mName + " " + memberStatus);
            return true;
        }
        return false;
    }

    public Boolean unRequestSet(Member m, int requestDays, String sDay){
        String mID = m.getMemberID();
        Day startDay = new Day(sDay);
        Day endDay = startDay.incrementByDays(requestDays);
        for (Reservation rl : requestList) {
            if (rl.equals(startDay,endDay,mID)){
                requestList.remove(rl);
                m.removeRequestStatus(eCode_Set,startDay.toString(),endDay.toString());
                return true;
            }
        }
        return false;
    }
    
}
