package App;

import java.util.ArrayList;

/**
 * Class History 
 * Used for better preferences matching for Teenagers affectations
 * 
 * @author Ethan Robert
 * @version v2
**/

public class History extends DataType {
    private ArrayList<Affectation> history;
    
    public History () {
        this.history = new ArrayList<Affectation>();
    }


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