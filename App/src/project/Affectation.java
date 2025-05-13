package project;

import java.util.Map;
import java.util.ArrayList;
import java.lang.Math;

public class Affectation {
    private double compatibility;
    private Teenager host;
    private Teenager guest;

    public Affectation(Teenager host,Teenager guest){
        this.host=host;
        this.guest=guest;
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

    private void computeCompatibility(History h) {

        /*
            First let's take in account preferences
         */

        // Hobbies parsing
        String[] guestHobbies = this.guest.getCriteriaValue("HOBBIES").split(",");
        String[] hostHobbies = this.host.getCriteriaValue("HOBBIES").split(",");

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

        double hobbiesCompatibility = (double) matchingHobbies.size() / (double) maxHobbiesLength;

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

        if (animalAllergyCompatibility && foodAllergyCompatibility && historyCompatibility) {
            this.compatibility = (double) (genderCompatibility + hobbiesCompatibility + ageCompatibility) / 3.0;
        } else {
            this.compatibility = 0.0;
        }
    }

    public double Compatibility(){
        return this.compatibility;
    }

}
