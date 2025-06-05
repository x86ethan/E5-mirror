package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

public class TeenagerInventory implements DataType{
    private ArrayList<Teenager> teenagers;

    public final static Map<String, Character> CriteriaMap = new HashMap<String, Character>() {{
        put("GUEST_ANIMAL_ALLERGY", 'B');
        put("HOST_HAS_ANIMAL", 'B');
        put("GUEST_FOOD", 'T');
        put("HOST_FOOD", 'T');
        put("HOBBIES", 'T');
        put("GENDER", 'T');
        put("PAIR_GENDER", 'T'); 
        put("HISTORY", 'T');
    }};

   

    public static String[] keys = new String[]{"GUEST_ANIMAL_HAS_ALLERGY", "HOST_ANIMAL", "GUEST_FOOD_CONSTRAINT", "HOST_FOOD", "HOBBIES", "GENDER", "PAIR_GENDER", "HISTORY"};
        
        TeenagerInventory(){
        this.teenagers = new ArrayList<>();
    }

    public ArrayList<Teenager> getTeenagers() {
        return this.teenagers;
    }

    public boolean importCSV(String filename, boolean header){
        try(BufferedReader br = new BufferedReader(
                new FileReader(System.getProperty("user.dir")+File.separator+"res"+File.separator+filename))) {
            String line = br.readLine();
            if (header){
                line = br.readLine();
            }
            while(line != null) {
                this.addTeenager(Teenager.lineToTeen(line, ";"));
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
        try(PrintWriter pw = new PrintWriter(new File(System.getProperty("user.dir")+File.separator+"res"+File.separator+filename))) {
            pw.println("FORENAME;NAME;COUNTRY;BIRTH_DATE;GUEST_ANIMAL_HAS_ALLERGY;HOST_ANIMAL;GUEST_FOOD_CONSTRAINT;HOST_FOOD;HOBBIES;GENDER;PAIR_GENDER;HISTORY");
            for(Teenager teen: this.teenagers){
                pw.println(Teenager.teenToLine(teen));
            }
        } catch(IOException e) {
            System.out.println("Writing error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
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