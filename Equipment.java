import java.util.ArrayList;
import java.util.Collections;

public class Equipment implements Comparable<Equipment>{

    private String eCode;
    private String eName;
    private ArrayList<EquipmentSet> eSetList;
    private String borrowedSets;

    public Equipment(String code, String name){
        this.eCode = code;
        this.eName = name;
        eSetList = new ArrayList<EquipmentSet>();
        borrowedSets = "";
    }

    public String getECode(){return eCode;}
    public String getName() {return eName;}

    
    @Override
    public int compareTo(Equipment another) {
        return this.eCode.compareTo(another.eCode);
    }

    public void addSet(){
        eSetList.add(new EquipmentSet(this.eCode,eSetList.size()+1));
        Collections.sort(eSetList);
    }

    public void removeSet(){
        eSetList.remove(eSetList.size()-1);
    }

    public void borrowEquipment(Member m, Boolean redo, int borrowDays) throws ExNoAvailableSet, ExOverlapBorrow, ExMemberAlreadyBorrowing{
        Day startDay = SystemDate.getInstance().clone();
        Day endDay = SystemDate.getInstance().incrementByDays(borrowDays);
        for (EquipmentSet eS : eSetList) {
            Boolean b = eS.borrowSet(startDay, endDay , m, this.eName, redo);
            if (b && borrowedSets == ""){
                borrowedSets = "Borrowed set(s): " + eS.geteCode_Set() +"("+m.getMemberID()+")";
                return;
            }
            else if (b){
                borrowedSets += ", " + eS.geteCode_Set() +"("+m.getMemberID()+")";
                return;
            }
        }
        throw new ExNoAvailableSet();
    }

    public void unborrowEquipment(Member m){
        Boolean b = false;
        for (EquipmentSet eS : eSetList) {
            if (eS.getMemberID() != null && eS.getMemberID().equals(m.getMemberID())){
                eS.unborrowSet(m);
                b = true;
                break;
            }
        }
        if (b){
            String[] bsParts = borrowedSets.split(",");
            if (bsParts.length <= 1){
                borrowedSets = "";
            }
            else{
                borrowedSets = "";
                for (int i = 0; i < bsParts.length - 1;i++ ){
                    borrowedSets += bsParts[i];
                    if (i != bsParts.length - 2){
                        borrowedSets += ", ";
                    }
                }
            }
        }
    }

    public void requestEquipment(Member m, int requestDays, String sDay, Boolean redo) throws ExNoAvailableSet, ExOverlapRequest{   
        Day startDay = new Day(sDay);
        Day endDay = startDay.incrementByDays(requestDays);
        for (EquipmentSet eS : eSetList) {
            Boolean b = eS.requestSet(startDay, endDay, m, this.eName, redo);
            if (b){return;}
        }
        throw new ExNoAvailableSet();
    }

    public void unrequestEquipment(Member m, int requestDays, String sDay){
        for (EquipmentSet eS : eSetList) {
            if (eS.unRequestSet(m, requestDays, sDay)){
                break;
            }
        }
    }

    public static void listStatus(ArrayList<Equipment> equipments){
        for (Equipment e : equipments) {
            System.out.printf("[%s %s]\n",e.eCode,e.eName);
            if (e.eSetList.size() == 0){
                System.out.println("  We do not have any sets for this equipment.");
            }
            else{
                for (EquipmentSet eS : e.eSetList) {
                    System.out.printf("  %s\n",eS.geteCode_Set());
                    System.out.printf("    Current status: %s\n",eS.getcurrentStatus());
                    if (eS.getRequestPeriods() != "")
                        System.out.printf("    Requested period(s): %s\n",eS.getRequestPeriods());
                }
            }
            System.out.println();
        }
    }

    public static void list(ArrayList<Equipment> equipments) {
        System.out.printf("%-5s%-15s%9s\n", "Code", "Name", "#sets ");//Learn: "-" means left-aligned
        for (Equipment e: equipments) {
            if (e.borrowedSets == "")
                System.out.printf("%-5s%-15s%6d\n", e.eCode, e.eName, e.eSetList.size(),e.borrowedSets);
            else
                System.out.printf("%-5s%-15s%6d  (%s)\n", e.eCode, e.eName, e.eSetList.size(),e.borrowedSets);
        }
    }
}
