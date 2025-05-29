package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class TeenagerInventory implements DataType{
    private ArrayList<Teenager> teenagers;

    public static Map<String, Character> CriteriaMap = new HashMap<String, Character>() {{
        put("GUEST_ANIMAL_ALLERGY", 'B');
        put("HOST_HAS_ANIMAL", 'B');
        put("GUEST_FOOD", 'T');
        put("HOST_FOOD", 'T');
        put("HOBBIES", 'T');
        put("GENDER", 'T');
        put("PAIR_GENDER", 'T'); 
        put("HISTORY", 'T');
    }};

    TeenagerInventory(){
        this.teenagers = new ArrayList<>();
    }

    public ArrayList<Teenager> getTeenagers() {
        return this.teenagers;
    }

    private Teenager lineToTeen(String line, String delimiter) throws IOException{
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
        String[] keys = new String[]{"GUEST_ANIMAL_HAS_ALLERGY", "HOST_ANIMAL", "GUEST_FOOD_CONSTRAINT", "HOST_FOOD", "HOBBIES", "GENDER", "PAIR_GENDER", "HISTORY"};
        while (s.hasNext()) {
            tmp = s.next();
            if (!tmp.equals("")){
                criteria.put(keys[cpt], tmp);
            }
            cpt ++;
        }

        s.close();
        return new Teenager(forename, name, date, criteria, country);
    }

    public boolean importCSV(String filename, boolean header){
        try(BufferedReader br = new BufferedReader(
                new FileReader(System.getProperty("user.dir")+File.separator+"res"+File.separator+filename))) {
            String line = br.readLine();
            if (header){
                line = br.readLine();
            }
            while(line != null) {
                this.teenagers.add(this.lineToTeen(line, ";"));
                line = br.readLine();
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found: "); e.printStackTrace();
            return false;
        } catch(IOException e) {
            System.out.println("Reading error: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e){
            System.out.println("Unreadable thing in line: "); e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean exportCSV(String filename){
        return false;
    }

    public boolean addTeenager(Teenager teen){
        if(teen != null){
            this.teenagers.add(teen);
            return true;
        }
        return false;
    }

    public ArrayList<Teenager> findTeenagerByCountry(Country country){
        return new ArrayList<>();
    }

    public ArrayList<Teenager> findTeenagerByCriterion(Country country){
        return new ArrayList<>();
    }
}