package App;

import java.util.Map;
import java.util.ArrayList;
import java.lang.Math;

public class Affectation {
    private Teenager host;
    private Teenager guest;

    public Affectation(Teenager host,Teenager guest){
        this.host=host;
        this.guest=guest;
    }

    public void setHost (Teenager newHost) {
        this.host = newHost;
    }

    public void setGuest (Teenager newGuest) {
        this.guest = newGuest;
    }

    public Teenager getHost() {
        return this.host;
    }

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

    public double compatibility(History h) {

        /*
            First let's take in account preferences
         */

        // Hobbies parsing
        String[] guestHobbies = this.guest.getCriteriaValue("HOBBIES").split(",");
        String[] hostHobbies = this.host.getCriteriaValue("HOBBIES").split(",");

        double hobbiesCompatibility = getHobbiesCompatibility(guestHobbies, hostHobbies);

        int genderCompatibility = 0;

        if (this.guest.getCriteriaValue("PAIR_GENDER").equals(this.host.getCriteriaValue("GENDER"))) {
            genderCompatibility = 1;
        }

        int ageCompatibility = 0;

        if (Math.abs(this.host.getAge() - this.guest.getAge()) <= 1) {
            ageCompatibility = 1;
        }

        /*
            And then redhibitory constraints
         */


        boolean animalAllergyCompatibility = true;
        if (this.host.hasCriteria("HOST_HAS_ANIMAL", "true") && this.guest.hasCriteria("GUEST_ANIMAL_ALLERGY", "true")) {
            animalAllergyCompatibility = false;
        }

        boolean foodAllergyCompatibility = true;

        if (this.guest.hasCriteria("GUEST_FOOD", "vegetarian") && !this.host.hasCriteria("HOST_FOOD", "vegetarian")) {
            foodAllergyCompatibility = false;
        } else if (this.guest.hasCriteria("GUEST_FOOD", "nonuts") && !this.host.hasCriteria("HOST_FOOD", "nonuts")) {
            foodAllergyCompatibility = false;
        }

        /*
            And then look at the history
         */
        boolean historyCompatibility = true;
        double historyAffinity = 0;
        if (this.guest.hasCriteria("HISTORY", "same") && this.host.hasCriteria("HISTORY", "same") && !h.hasAlreadyBeenMatched(this.host, this.guest)) {
            historyCompatibility = false;
        } else if (h.hasAlreadyBeenMatched(this.guest, this.host) && (this.host.hasCriteria("HISTORY", "other") || this.guest.hasCriteria("HISTORY", "other"))) {
            historyCompatibility = false;
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


}
