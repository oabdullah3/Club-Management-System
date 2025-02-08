import java.util.ArrayList;
import java.util.Collections;

public class Club {
    
    private ArrayList<Member> allMembers;
    private ArrayList<Equipment> equipments;
    private static Club instance = new Club();

    private Club() { 
        allMembers = new ArrayList<Member>();
        equipments = new ArrayList<Equipment>();
    }

    public static Club getInstance() { 
        return instance; 
    }
    
    public void arriveEquipment(String eCode) throws ExMissingEquipmentRecord{
        for (Equipment e : equipments) {
            if (e.getECode().equals(eCode)){
                e.addSet();
                return;
            }
        }
        throw new ExMissingEquipmentRecord(eCode);
    }

    public void unArriveEquipment(String eCode){
        for (Equipment e : equipments) {
            if (e.getECode().equals(eCode)){
                e.removeSet();
            }
        }
    }

    public void addMember(Member m) {
        allMembers.add(m);
        Collections.sort(allMembers);
    }

    public void removeMember(Member m) {
        allMembers.remove(m);
    }

    public void addEquipment(Equipment e) {
        equipments.add(e);
        Collections.sort(equipments);
    }

    public void removeEquipment(Equipment e) {
        equipments.remove(e);
    }

    public void listClubMembers() {
        Member.list(this.allMembers);
    }
    public void listClubEquipment() {
        Equipment.list(this.equipments);
    }
    public void listClubMembersStatus(){
        Member.listStatus(this.allMembers);
    }
    public void listClubEquipmentStatus(){
        Equipment.listStatus(this.equipments);
    }

    public Member searchMembers(String mID){
        for (Member m : allMembers) {
            if(m.getMemberID().equals(mID)){
                return m;
            }
        }
        return null;
    }
    
    public Equipment searchEquipment(String eCode){
        for (Equipment e : equipments) {
            if(e.getECode().equals(eCode)){
                return e;
            }
        }
        return null;
    }
}
