import java.time.LocalDate;
import java.util.Map;

public class Teenager {
    private String name;
    private String firstname;
    private final LocalDate BIRTHDAY;
    Map<String,String> criteria;
    private Gender gender;
    private Country country;

    public Teenager(String name,String firstname,LocalDate birthday,Map<String,String> criteria,Gender gender,Country country){
        this.name=name;
        this.firstname=firstname;
        this.BIRTHDAY=birthday;
        this.criteria=criteria;
        this.gender=gender;
        this.country=country;
    }

    public int getAge(){
        return 0;
    }

    public boolean hasCriterion(String key, String value){
        return false;
    }

    public double compatibility(Teenager other){
        return 0;
    }

}
