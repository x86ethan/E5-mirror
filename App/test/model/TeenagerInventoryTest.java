package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TeenagerInventoryTest {
    private static String filename1;
    private static String filename2;
    private static String filename3;

    private static Map<String, String> cr1;
    private static Map<String, String> cr2;
    private static Map<String, String> cr3;

    private static Teenager t1;
    private static Teenager t2;
    private static Teenager t3;

    private static TeenagerInventory ti1;
    private static TeenagerInventory ti2;
    private static TeenagerInventory ti3;
    private static TeenagerInventory ti4;

    @BeforeAll
    public static void initialization(){
        filename1 = "Adolescent.csv";
        filename2 = "Adolescent2.csv";
        filename3 = "ExportTest.csv";

        cr1 = new HashMap<>();
        cr2 = new HashMap<>();
        cr3 = new HashMap<>();
        
        cr1.put("HOST_HAS_ANIMAL", "false");
        cr2.put("GUEST_FOOD", "nonuts");
        cr3.put("HOBBIES", "videogames");

        t1 = new Teenager("Tom","Dupont", LocalDate.of(2006,5,10), cr1, Country.ES);
        t2 = new Teenager("Anna", "Durand", LocalDate.of(2007, 3, 12), cr2, Country.FR);
        t3 = new Teenager("Léo", "Martin", LocalDate.of(2008, 8, 22), cr3, Country.IT);

        ti1 = new TeenagerInventory();
        ti2 = new TeenagerInventory();
        ti3 = new TeenagerInventory();
        ti4 = new TeenagerInventory();
    }

    @Test
    public void testAddTeenager(){
        assertTrue(ti1.addTeenager(t1));
        assertTrue(ti1.addTeenager(t2));
        assertTrue(ti1.addTeenager(t3));
        assertFalse(ti1.addTeenager(null));
        assertTrue(ti1.addTeenager(t1));
        assertTrue(ti1.addTeenager(t3));
        

        assertEquals(ti1.getTeenagers().get(0), t1);
        assertEquals(ti1.getTeenagers().get(1), t2);
        assertEquals(ti1.getTeenagers().get(2), t3);
        assertEquals(ti1.getTeenagers().get(3), t1);
        assertEquals(ti1.getTeenagers().get(4), t3);
    }

    @Test
    public void testImportation(){
        assertFalse(ti2.importCSV(filename1, false));
        assertTrue(ti2.importCSV(filename1, true));

        Teenager firsTeen = ti2.getTeenagers().get(0);
        Teenager lastTeen = ti2.getTeenagers().get(49);

        assertEquals("Adonia", firsTeen.getFirstname());
        assertEquals("A", firsTeen.getName());
        assertEquals(Country.FR, firsTeen.getCountry());
        assertEquals(LocalDate.of(2004, 3, 15), firsTeen.getBIRTHDAY());
        assertEquals("no", firsTeen.getCriteria().get("GUEST_ANIMAL_HAS_ALLERGY"));
        assertEquals("yes", firsTeen.getCriteria().get("HOST_ANIMAL"));
        assertEquals("vegetarian", firsTeen.getCriteria().get("HOST_FOOD"));
        assertEquals("sports,technology", firsTeen.getCriteria().get("HOBBIES"));
        assertEquals("female", firsTeen.getCriteria().get("GENDER"));
        assertEquals("other", firsTeen.getCriteria().get("HISTORY"));

        assertEquals("Wren", lastTeen.getFirstname());
        assertEquals("W", lastTeen.getName());
        assertEquals(Country.GE, lastTeen.getCountry());
        assertEquals(LocalDate.of(2005, 10, 2), lastTeen.getBIRTHDAY());
        assertEquals("yes", lastTeen.getCriteria().get("GUEST_ANIMAL_HAS_ALLERGY"));
        assertEquals("no", lastTeen.getCriteria().get("HOST_ANIMAL"));
        assertEquals("vegetarian", lastTeen.getCriteria().get("GUEST_FOOD_CONSTRAINT"));
        assertEquals("cooking,sports", lastTeen.getCriteria().get("HOBBIES"));
        assertEquals("male", lastTeen.getCriteria().get("GENDER"));
        assertEquals("same", lastTeen.getCriteria().get("HISTORY"));
    }

    @Test
    public void testExportation(){
        assertFalse(ti3.importCSV(filename2, false));
        assertTrue(ti3.importCSV(filename2, true));

        assertTrue(ti3.exportCSV(filename3));
        assertTrue(ti4.importCSV(filename3, true));

        assertTrue(ti3.getTeenagers().equals(ti4.getTeenagers()));
    }
}