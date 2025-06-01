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

                int half = columns.length / 2;

                String host = String.join(";", Arrays.copyOfRange(columns, 0, half));
                String guest = String.join(";", Arrays.copyOfRange(columns, half, columns.length));

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