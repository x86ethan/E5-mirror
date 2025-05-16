package model;

import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

public class TeenagerInventory extends DataType{
    private ArrayList<Teenager> teenagers;

    public static Map<String, Character> CriteriaMap = new HashMap<String, Character>() {{
        put("GUEST_ANIMAL_ALLERGY", 'B');
        put("HOST_HAS_ANIMAL", 'B');
        put("HOST_FOOD", 'T');
        put("HOBBIES", 'T');
        put("GUEST_FOOD", 'T');
        put("GENDER", 'T');
        put("PAIR_GENDER", 'T'); 
        put("HISTORY", 'T');
    }};

    TeenagerInventory(){
        this.teenagers = new ArrayList<>();
    }

    public boolean importCSV(String filename){
        return false;
    }

    public boolean exportCSV(String filename){
        return false;
    }

    public void addTeenager(Teenager teen){

    }

    public ArrayList<Teenager> findTeenagerByCountry(Country country){
        return new ArrayList<>();
    }

    public ArrayList<Teenager> findTeenagerByCriterion(Country country){
        return new ArrayList<>();
    }

}