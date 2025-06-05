package model;

import java.util.Map;
import java.util.HashMap;

public class AffectationInventory {
    
    private Map<Teenager, Teenager> forbiddenAffectations;
    private Map<Teenager, Teenager> mandatoryAffectations;

    private Map<Teenager, Teenager> affectationInventory;

    public AffectationInventory() {
        this.forbiddenAffectations = new HashMap<>();
        this.mandatoryAffectations = new HashMap<>();
        
        this.affectationInventory = new HashMap<>();
    }

    public void addMandatoryAffectation(Teenager t1, Teenager t2) throws AffectationInventoryException {
        if (!this.isAffectationForbidden(t1, t2)) {
            this.mandatoryAffectations.put(t1, t2);
        } else {
            throw new AffectationInventoryException("Can't force match " + t1 + " and " + t2 + ": the affectation is forbidden");
        }
    }

    public void addForbiddenAffectation(Teenager t1, Teenager t2) throws AffectationInventoryException {
        if (!this.isAffectationMandatory(t1, t2)) {
            this.forbiddenAffectations.put(t1, t2);
        } else {
            throw new AffectationInventoryException("Can't prevent " + t1 + " and " + t2 + " to be matched: already force matched");
        }
    }

    public boolean isAffectationForbidden(Teenager t1, Teenager t2) {
        if (this.forbiddenAffectations.containsKey(t1)) {
            return this.forbiddenAffectations.get(t1).equals(t2);
        }
        return false;
    }

    public boolean isAffectationMandatory(Teenager t1, Teenager t2) {
        if (this.mandatoryAffectations.containsKey(t1)) {
            return this.mandatoryAffectations.get(t1).equals(t2);
        }
        return false;
    }

    public void autoAffectation() {

        

    }

}
