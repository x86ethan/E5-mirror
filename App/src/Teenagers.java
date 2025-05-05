import java.time.LocalDate;
import java.util.Map;

public class Teenagers {
    private String name;
    private String firstname;
    private final LocalDate BIRTHDAY;
    Map<String,String> criteria;
    private Gender gender;
    private Country country;

    public Teenagers(String name,String firstname,LocalDate birthday,Map<String,String> criteria,Gender gender,Country country){
        this.name=name;
        this.firstname=firstname;
        this.BIRTHDAY=birthday;
        this.criteria=criteria;
        this.gender=gender;
        this.country=country;
    }
}
