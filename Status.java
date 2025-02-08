public abstract class Status implements Comparable<Status>{

    private String status;
    public String getStatus(){return status;}

    public Status(String s){
        this.status = s;
    }

    @Override
    public abstract int compareTo(Status another); //enables sorting of status according to reservation type (request or borrow)
    
    @Override
    public String toString(){
        return status;
    }
}
