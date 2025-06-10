package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public static String teenToLine(Teenager teen){
        StringBuilder line = new StringBuilder();
        line.append(teen.getFirstname()+";"+teen.getName()+";"+teen.getCountry()+";"+teen.getBIRTHDAY()+";");
        for(String key : TeenagerInventory.keys){
            if(teen.getCriteria().containsKey(key)){
                line.append(teen.getCriteriaValue(key)+";");
            }else{
                line.append(";");
            }
        }
        return line.toString().substring(0, line.length()-1);
    }

    public static Teenager lineToTeen(String line, String delimiter) throws IOException{
        Scanner s = new Scanner(line);
        s.useDelimiter(delimiter);
        String forename, name, tmp;
        LocalDate date;
        Country country;
        Map<String, String> criteria = new HashMap<>();

        forename = s.next();
        name = s.next();
        country = Country.valueOf(s.next());
        date = LocalDate.parse(s.next());
        
        int cpt = 0;
        while (s.hasNext() && TeenagerInventory.keys.length > cpt) {
            tmp = s.next();
            if (!tmp.equals("")){
                criteria.put(TeenagerInventory.keys[cpt], tmp);
            }
            cpt ++;
        }

        s.close();
        return new Teenager(forename, name, date, criteria, country);
    }

    public LocalDate getBIRTHDAY() {
        return BIRTHDAY;
    }

    public Map<String, String> getCriteria() {
        return criteria;
    }

    public boolean isCriteriaNull(String criteria) {
        return this.criteria.get(criteria) == null || this.criteria.get(criteria).equals("");
    }

    public String getFirstname() {
        return firstname;
    }

    public String getName() {
        return name;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (this == obj){return true;}
        if (this.getClass() != obj.getClass()){return false;}
        Teenager other = (Teenager) obj;
        if (this.firstname == null){
            if(other.firstname != null){return false;}
        }else if (!this.firstname.equals(other.firstname)){return false;}
        if (this.name == null){
            if(other.name != null){return false;}
        }else if (!this.name.equals(other.name)){return false;}
        if (this.BIRTHDAY == null){
            if(other.BIRTHDAY != null){return false;}
        }else if (!this.BIRTHDAY.equals(other.BIRTHDAY)){return false;}
        if (this.country == null){
            if(other.country != null){return false;}
        }else if (!this.country.equals(other.country)){return false;}
        if (this.criteria == null){
            if(other.criteria != null){return false;}
        }else if (!this.criteria.equals(other.criteria)){return false;}
        return true;
    }
}
