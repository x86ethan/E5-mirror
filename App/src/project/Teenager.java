package project;

import java.time.LocalDate;
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
        return 0;
    }

    public boolean hasCriteria(String key, String value){
        return false;
    }

    public double compatibility(Teenager other){
        return 0;
    }

}
