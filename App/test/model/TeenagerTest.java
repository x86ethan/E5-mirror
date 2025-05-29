package model;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TeenagerTest {

    private static Map<String, String> cr1;
    private static Map<String, String> cr2;
    private static Map<String, String> cr3;

    private static Teenager t1;
    private static Teenager t2;
    private static Teenager t3;
    private static Teenager t4;

    @BeforeEach
    public void initialization(){
    cr1 = new HashMap<>();
    cr2 = new HashMap<>();
    cr3 = new HashMap<>();
    
    cr1.put("HOST_HAS_ANIMAL", "false");
    cr2.put("GUEST_FOOD", "nonuts");
    cr3.put("HOBBIES", "videogames");

    t1 = new Teenager("Tom","Dupont", LocalDate.of(2006,5,10), cr1, Country.ES);
    t2 = new Teenager("Anna", "Durand", LocalDate.of(2007, 3, 12), cr2, Country.FR);
    t3 = new Teenager("Léo", "Martin", LocalDate.of(2008, 8, 22), cr3, Country.IT);
    t4 = t1;

    }

    @Test
    public void TestgetAge() {
        assertEquals(19, t1.getAge());
        assertEquals(18, t2.getAge());
        assertEquals(16, t3.getAge());
        assertEquals(t4.getAge(), t1.getAge());
        }
        
    @Test
    public void hasCriteriaTest(){
        assertTrue(t1.hasCriteria("HOST_HAS_ANIMAL","false"));
        assertTrue(t2.hasCriteria("Guest_Food","nonuts"));
        assertFalse(t3.hasCriteria("HOBBIES",""));
    }

    @Test
    public void Testcompatibility(){
        try {
            assertTrue(t1.compatibility(t2) > 0 && t1.compatibility(t2) < 1);
            assertTrue(t2.compatibility(t3) > 0 && t2.compatibility(t3) < 1);
            assertEquals(t4.compatibility(t1), 1);
        } catch (AffectationException e) {
            e.printTrace();
        }
    }
}