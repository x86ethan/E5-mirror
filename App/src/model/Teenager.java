package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class Teenager {
    private String name;
    private String firstname;
    private final LocalDate BIRTHDAY;
    Map<String,String> criteria;
    private Country country;

    public Teenager(String firstname, String lastname, LocalDate birthday, Map<String,String> criteria, Country country){
        this.name=lastname;
        this.firstname=firstname;
        this.BIRTHDAY=birthday;
        this.criteria=criteria;
        this.country=country;
    }

    public int getAge(){
        return this.BIRTHDAY.until(LocalDate.now()).getYears();
    }

    public boolean hasCriteria(String key, String value){
        try {
            return (this.criteria.get(key).equals(value));
        } catch (NullPointerException e) {
            return false;
        }
    }

    public String getCriteriaValue(String key) {
        if (this.criteria.containsKey(key)) {
            return this.criteria.get(key);
        }
        return "";
    }

    public double compatibility(Teenager other, History history) throws AffectationException {
        // Create temporary affectation to check its compatibility

        Affectation aff = new Affectation(this, other);
        return aff.compatibility(history);

    }

    public double compatibility(Teenager other) throws AffectationException {
        Affectation aff = new Affectation(this, other);
        return aff.compatibility();
    }

    public String toString() {
        return this.firstname + " " + this.name + " (age " + this.getAge() + ")";
    }

    public Country getCountry() {
        return this.country;
    }
}
