package model;

import java.util.ArrayList;

/**
 * Class History 
 * Used for better preferences matching for Teenagers affectations
 * 
 * @author Ethan Robert
 * @version v2
**/

public class History implements DataType {
    private ArrayList<Affectation> history;
    
    public History (ArrayList<Affectation> pastAffectations) {
        this.history = pastAffectations;
    }

    public History () {
        this(new ArrayList<Affectation>());
    }

    public void addAffectation(Affectation affectation) {
        this.history.add(affectation);
    }

    public void addAffectations(ArrayList<Affectation> affectations) {
        this.history.addAll(affectations);
    }

    public boolean importCSV(String filename, boolean header){
        return false;
    }

    public boolean exportCSV(String filename){
        return false;
    }

    public boolean hasAlreadyBeenMatched(Teenager host, Teenager guest) {

        boolean found = false;

        for (Affectation affectation : history){
            if (affectation.getHost().equals(host)) {
                if (affectation.getGuest().equals(guest)) {
                    found = true;
                }
            }
        }

        return found;
    }
}