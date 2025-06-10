package model;

import java.util.Map;


import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

public class AffectationInventory {
    
    private Map<Teenager, Teenager> forbiddenAffectations;
    private Map<Teenager, Teenager> mandatoryAffectations;

    private ArrayList<Affectation> affectationInventory;

    public AffectationInventory() {
        this.forbiddenAffectations = new HashMap<>();
        this.mandatoryAffectations = new HashMap<>();
        
        this.affectationInventory = new ArrayList<>();
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


    public String toString() {
        
        return this.affectationInventory.toString();

    }

    public void autoAffectation(TeenagerInventory teenagers) {

        this.affectationInventory.clear();

        for (Teenager t : this.mandatoryAffectations.keySet()) {
            try {
                this.affectationInventory.add(new Affectation(t, this.mandatoryAffectations.get(t)));
            } catch (AffectationException e) {
                e.printTrace();
                e.printStackTrace();
            }

        }

        double affectationMax;
        Teenager affectationMaxTeenager = null;
        boolean affectationAlreadyExists;

        for (Teenager host : teenagers.sortHosts()) {
            affectationMax = 0;
            for (Teenager guest : teenagers.sortGuests()) {
                
                affectationAlreadyExists = false;
                for (Affectation a : this.affectationInventory) {
                    if (a.getHost().equals(host) && a.getGuest().equals(guest)) {
                        affectationAlreadyExists = true;
                    }
                }

                if (!affectationAlreadyExists) {
                    try {
                        if (host.compatibility(guest) > affectationMax) {
                            affectationMaxTeenager = guest;
                            affectationMax = host.compatibility(guest);
                        } 
                    } catch (AffectationException e) {
                        // Nothing 
                    }
                }
            }

            if (affectationMaxTeenager != null) {
                try {
                    this.affectationInventory.add(new Affectation(host, affectationMaxTeenager));
                } catch (AffectationException e) {
                    e.printTrace();
                    e.printStackTrace();
                }        
            }
        }

    }

    public static void main (String[] args) {
        

        AffectationInventory ai = new AffectationInventory();
        TeenagerInventory ti = new TeenagerInventory();

        HashMap<String, String> cr1 = new HashMap<>();
        HashMap<String, String> cr2 = new HashMap<>();
        HashMap<String, String> cr3 = new HashMap<>();

        cr1.put("HOST_FOOD", "nonuts");
        cr1.put("HOBBIES", "videogames");

        cr2.put("GUEST_FOOD", "nonuts");
        cr2.put("HOBBIES", "videogames");
        
        cr3.put("HOBBIES", "videogames");

        ti.addTeenager(new Teenager("Ethan", "Robert", LocalDate.of(2006, 11, 12), cr1, Country.FR));
        ti.addTeenager(new Teenager("Antonin", "Marouzè", LocalDate.of(1999, 12, 31), cr2, Country.FR));
        ti.addTeenager(new Teenager("Jonas", "Facon", LocalDate.of(1899, 11, 30), cr3, Country.FR));

        ai.autoAffectation(ti);

        System.out.println(ai);
    }

}
