import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Member implements Comparable<Member> {
    
    private String id;
    private String name;
    private Day joinDate;
    private int borrowed;
    private int requested;
    private  ArrayList<BorrowStatus> borrowstatus;
    private ArrayList<RequestStatus> requeststatus;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowed = 0;
        this.requested = 0;
        this.joinDate = SystemDate.getInstance().clone();
        this.requeststatus = new ArrayList<>();
        this.borrowstatus = new ArrayList<>();
    }

    public String getName() {return name;}
    public String getMemberID(){return this.id;}

    @Override
    public int compareTo(Member another) {
        return this.id.compareTo(another.id);
    }

    public void addBorrowStatus(String borrowString){
        borrowstatus.add(new BorrowStatus(borrowString));
        this.borrowed++;
        Collections.sort(borrowstatus);
    }

    public void removeBorrowStatus(String eCodeSet){
        for (int i = 0; i < borrowstatus.size(); i++) {
            String[] parts = borrowstatus.get(i).toString().split(" ");
            if (parts[0].equals("borrows") && parts[1].equals(eCodeSet)){
                borrowstatus.remove(i);
                this.borrowed--;
                return;
            }
        }
    }

    public void addRequestStatus(String requestString){
        requeststatus.add(new RequestStatus(requestString));
        this.requested++;
        Collections.sort(requeststatus);    
    }

    public void removeRequestStatus(String eCodeSet, String startDay, String endDay){

        for (int i = 0; i < requeststatus.size(); i++) {
            String[] parts = requeststatus.get(i).toString().split(" ");
            if (parts[0].equals("requests") && parts[1].equals(eCodeSet) && parts[4].equals(startDay) && parts[6].equals(endDay)){
                requeststatus.remove(i);
                this.requested--;
                return;
            }
        }
    }
    
    public static void listStatus(ArrayList<Member> allMembers){
        for (Member m : allMembers) {
            System.out.printf("[%s %s]\n",m.id,m.name);
            if (m.requeststatus.size() == 0 && m.borrowstatus.size() == 0){
                System.out.println("No record.");
            }
            else{
                if (m.borrowstatus.size() != 0){
                    for (BorrowStatus status : m.borrowstatus) {
                        System.out.printf("- %s\n",status.toString());
                    }
                }
                if (m.requeststatus.size() != 0){
                    for (RequestStatus status : m.requeststatus) {
                        System.out.printf("- %s\n",status.toString());
                    }
                }
            }
            System.out.println();
        }
    }

    public static void list(ArrayList<Member> allMembers) {
        System.out.printf("%-5s%-9s%11s%11s%13s\n", "ID", "Name", "Join Date ", "#Borrowed", "#Requested");//Learn: "-" means left-aligned
        for (Member m: allMembers) {
        System.out.printf("%-5s%-9s%11s%7d%13d\n", m.id, m.name, m.joinDate.toString(), m.borrowed, m.requested);
        }
    }
}
