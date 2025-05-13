package App;

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

        boolean found = false;

        for (Affectation affectation : history){
            if (affectation.getHost() == host) {
                if (affectation.getGuest() == guest) {
                    found = true;
                }
            }
        }

        return found;
    }

}