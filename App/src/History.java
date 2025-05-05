import java.util.ArrayList;

public class History extends DataType{
    private ArrayList<Affectation> history;
    
    public boolean importCSV(String filename){
        return false;
    }

    public boolean exportCSV(String filename){
        return false;
    }

    public boolean hasAlreadyBeenMatched(Teenager host, Teenager guest){
        return false;
    }

}