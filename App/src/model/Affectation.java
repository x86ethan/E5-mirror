package model;

import java.util.ArrayList;
import java.lang.Math;

/**
 * Class affectation
 * Used to manage an affectation of two Teenager objects (host & guest)
 * @author Ethan Robert
 * @version 2.0
 */

public class Affectation {
    private Teenager host;
    private Teenager guest;

    /**
     * Create an affectation between two Teenagers
     * @param host the Teenager representing the host
     * @param guest the Teenager representing the guest
     */
    public Affectation(Teenager host, Teenager guest) throws AffectationException {
        this.host = host;
        this.guest = guest;

        // Check the respect of redhibitory criteria with an exception mechanism
        this.compatibility();
    }

    public Affectation (Teenager host, Teenager guest, History h) throws AffectationException {
        this.host = host;
        this.guest = guest;

        this.compatibility(h);
    }

    /**
     * Setter method for the host Teenager
     * @param newHost the new host Teeanger
     */
    public void setHost (Teenager newHost) {
        this.host = newHost;
    }

    /**
     * Setter method for the guest Teenager
     * @param newGuest the new guest Teenager
    **/
    public void setGuest (Teenager newGuest) {
        this.guest = newGuest;
    }

    /**
     * Getter method for the host Teenager
     * @return the current host Teenager
    **/
    public Teenager getHost() {
        return this.host;
    }

    /**
     * Getter method for the guest Teenager
     * @return the current guest Teenager
    **/
    public Teenager getGuest() {
        return this.guest;
    }

    private static boolean arrayContains(String[] array, String element) {
        for (String s : array) {
            if (s.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean arrayListContains(ArrayList<String> array, String element) {
        for (String s : array) {
            if (s.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate a compatibility score between the two current Teenagers in the affectation
     * @return a score in the interval [0;1] following the compatibility of the teenagers in the affectation. The closest it is to 1, the more compatible they are.
    **/
    public double compatibility() throws AffectationException {
        return this.compatibility(null);
    }

    public static String printArray(String[] array) {
        String string = "[";

        for (int i = 0 ; i < array.length ; i++) {
            string += array[i];
            if (i < array.length - 1) {
                string += ", ";
            }
        }

        return string += "]";
    }

    /**
     * Calculate a compatibility score between the two current Teenagers in the affectation
     * @param h history of teenager affectations, for better criteria matching
     * @return a score in the interval [0;1] following the compatibility of the teenagers in the affectation. The closest it is to 1, the more compatible they are.
    **/
    public double compatibility(History h) throws AffectationException {

        /* =========== */
        /* PREFERENCES */
        /* =========== */

        // Hobbies parsing
        String[] guestHobbies = this.guest.getCriteriaValue("HOBBIES").split(",");
        String[] hostHobbies = this.host.getCriteriaValue("HOBBIES").split(",");

        double hobbiesCompatibility = getHobbiesCompatibility(guestHobbies, hostHobbies);

        // Just French
        if (this.host.getCountry() == Country.FR || this.guest.getCountry() == Country.FR) {
            if (hobbiesCompatibility == 0) {
                throw new AffectationException("(" + this.host + ", " + this.guest + ") FRENCH");
            }
        }

        int genderCompatibility = 0;

        if (this.guest.getCriteriaValue("PAIR_GENDER").equals(this.host.getCriteriaValue("GENDER"))) {
            genderCompatibility = 1;
        }

        int ageCompatibility = 0;

        if (Math.abs(this.host.getAge() - this.guest.getAge()) <= 1) {
            ageCompatibility = 1;
        }


        /* ======================= */
        /* REDHIBITORY CONSTRAINTS */
        /* ======================= */

        // ANIMAL ALLERGY
        boolean animalAllergyCompatibility = true;
        if (this.host.hasCriteria("HOST_HAS_ANIMAL", "true") && this.guest.hasCriteria("GUEST_ANIMAL_ALLERGY", "true")) {
            animalAllergyCompatibility = false;
        }

        if (!animalAllergyCompatibility) {
            throw new AffectationException("(" + this.host + ", " + this.guest + ") ANIMAL_ALLERGY");
        }

        // FOOD ALLERGY
        boolean foodAllergyCompatibility = true;

        if (this.guest.hasCriteria("GUEST_FOOD", "vegetarian") && !this.host.hasCriteria("HOST_FOOD", "vegetarian")) {
            foodAllergyCompatibility = false;
            System.out.println("hello");
        } else if (this.guest.hasCriteria("GUEST_FOOD", "nonuts") && !this.host.hasCriteria("HOST_FOOD", "nonuts")) {
            System.out.println("hello again");
            foodAllergyCompatibility = false;
        }

        if (!foodAllergyCompatibility) {
            throw new AffectationException("(" + this.host + ", " + this.guest + ") FOOD_ALLERGY");
        }

        
        /* ======= */
        /* HISTORY */
        /* ======= */

        boolean historyCompatibility = true;
        if (h != null) {
            if (this.guest.hasCriteria("HISTORY", "same") && this.host.hasCriteria("HISTORY", "same") && !h.hasAlreadyBeenMatched(this.host, this.guest)) {
                historyCompatibility = false;
            } else if (h.hasAlreadyBeenMatched(this.host, this.guest) && (this.host.hasCriteria("HISTORY", "other") || this.guest.hasCriteria("HISTORY", "other"))) {
                historyCompatibility = false;
            }
        }

        if (!historyCompatibility) {
            throw new AffectationException("(" + this.host + ", " + this.guest + ") HISTORY");
        }

        double compatibility = 0;
        if (animalAllergyCompatibility && foodAllergyCompatibility && historyCompatibility) {
            compatibility = (double) (genderCompatibility + hobbiesCompatibility + ageCompatibility) / 3.0;
        } else {
            compatibility = 0.0;
        }

        return compatibility;
    }


    private static double getHobbiesCompatibility(String[] guestHobbies, String[] hostHobbies) {
        ArrayList<String> matchingHobbies = new ArrayList<String>();

        for (String hobby : guestHobbies) {
            if (arrayContains(hostHobbies, hobby)) {
                matchingHobbies.add(hobby);
            }
        }

        for (String hobby : hostHobbies) {
            if (arrayContains(guestHobbies, hobby) && !(arrayListContains(matchingHobbies, hobby))) {
                matchingHobbies.add(hobby);
            }
        }

        

        int maxHobbiesLength;
        if (hostHobbies.length > guestHobbies.length) {
            maxHobbiesLength = hostHobbies.length;
        } else {
            maxHobbiesLength = guestHobbies.length;
        }

        return ((double) matchingHobbies.size() / (double) maxHobbiesLength);
    }

    /**
     * Compare this object to another one 
     * @param other the other object given for comparison
     * @return boolean following their equality
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        if (other == null) {
            return false;
        }

        if (this.getClass() == other.getClass()) {
            Affectation that = (Affectation) other;
            if (that.getHost().equals(this.host) && that.getGuest().equals(this.guest)) {
                return true;
            }
        }

        return false;
    }


}
