package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HistoryTest {
    private static Map<String, String> cr1;
    private static Map<String, String> cr2;
    private static Teenager host;
    private static Teenager guest;
    private static Affectation affectation;
    private static History history;

    @BeforeAll
    public static void initialization() {
        cr1 = new HashMap<>();
        cr2 = new HashMap<>();
        cr1.put("HOST_HAS_ANIMAL", "false");
        cr1.put("HOST_FOOD", "nonuts");
        cr2.put("GUEST_FOOD", "nonuts");
        
        host = new Teenager("Anna", "Durand", LocalDate.of(2007, 3, 12), cr1, Country.FR);
        guest = new Teenager("Léo", "Martin", LocalDate.of(2008, 8, 22), cr2, Country.IT);
        try {
            affectation = new Affectation(host, guest);
        } catch (Exception e) {e.printStackTrace();}
            
        history = new History();
        history.addAffectation(affectation);
    }

    @Test
    public void testAddAffectation() {
        history.addAffectation(affectation);
        assertTrue(history.hasAlreadyBeenMatched(host, guest));

        Map<String, String> cr3 = new HashMap<>();
        cr3.put("HOBBIES", "videogames");

        Teenager other = new Teenager("Léo", "Martin", LocalDate.of(2008, 8, 22), cr3, Country.IT);
        assertFalse(history.hasAlreadyBeenMatched(host, other));
    }

    @Test
    public void testImportCSV() {
        History history = new History();

        boolean success = history.importCSV("History.csv", true);
        assertTrue(success);
        assertEquals(50, history.getHistory().size());
    }
}
