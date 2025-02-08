public class ExMemberAlreadyBorrowing extends Exception { 
    public ExMemberAlreadyBorrowing() { super("The member is currently borrowing a set of this equipment. He/she cannot borrow one more at the same time.");} 
    public ExMemberAlreadyBorrowing(String message) { super(message);}
}
