package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class History 
 * Used for better preferences matching for Teenagers affectations
 * 
 * @author Ethan Robert
 * @version v2
**/

public class History implements DataType {
    private ArrayList<Affectation> history;
    
    public History (ArrayList<Affectation> pastAffectations) {
        this.history = pastAffectations;
    }

    public History () {
        this(new ArrayList<Affectation>());
    }

    public ArrayList<Affectation> getHistory() {
        return this.history;
    }

    public void addAffectation(Affectation affectation) {
        this.history.add(affectation);
    }

    public void addAffectations(ArrayList<Affectation> affectations) {
        this.history.addAll(affectations);
    }

    public boolean importCSV(String filename, boolean header){
        try(BufferedReader br = new BufferedReader(
                new FileReader(System.getProperty("user.dir")+File.separator+"res"+File.separator+filename))) {
            String line = br.readLine();
            if (header){
                line = br.readLine();
            }
            while(line != null) {
                String[] columns = line.split(";");

                final int HALF = 12;

                String host = String.join(";", Arrays.copyOfRange(columns, 0, HALF));
                String guest = String.join(";", Arrays.copyOfRange(columns, HALF, columns.length));

                System.out.println(host);
                System.out.println(guest);

                Teenager teenHost = Teenager.lineToTeen(host, ";");
                Teenager teenGuest = Teenager.lineToTeen(guest, ";");

                this.addAffectation(new Affectation(teenHost, teenGuest));

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
            for(Affectation aff: this.history){
                pw.println(Teenager.teenToLine(aff.getHost())+";"+Teenager.teenToLine(aff.getGuest()));
            }
        } catch(IOException e) {
            System.out.println("Writing error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean hasAlreadyBeenMatched(Teenager host, Teenager guest) {

        boolean found = false;

        for (Affectation affectation : history){
            if (affectation.getHost().equals(host)) {
                if (affectation.getGuest().equals(guest)) {
                    found = true;
                }
            }
        }

        return found;
    }
}