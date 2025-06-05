package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AffectationInventoryTest {
    static AffectationInventory ai1;

    private static Map<String, String> cr1;
    private static Map<String, String> cr2;
    private static Map<String, String> cr3;

    static Teenager t1;
    static Teenager t2;
    static Teenager t3;
    static Teenager t4;

    @BeforeAll
    public static void initialization() {
        cr1 = new HashMap<String,String>();
        cr2 = new HashMap<String,String>();
        cr3 = new HashMap<String,String>();

        ai1=new AffectationInventory();

        cr1.put("HOST_HAS_ANIMAL", "false");
        cr1.put("HOST_FOOD", "nonuts");
        cr2.put("GUEST_FOOD", "nonuts");
        cr2.put("HOBBIES", "videogames");
        cr1.put("HOBBIES", "videogames");
        cr3.put("HOBBIES", "videogames");

        t1 = new Teenager("Tom","Dupont", LocalDate.of(2006,5,10), cr1, Country.ES);
        t2 = new Teenager("Anna", "Durand", LocalDate.of(2007, 3, 12), cr2, Country.FR);
        t3 = new Teenager("Leo", "Martin", LocalDate.of(2008, 8, 22), cr3, Country.IT);
        t4 = t1;
    }
    @Test
    public void testAddMandatoryAffectation()throws AffectationInventoryException {
        ai1.addMandatoryAffectation(t1, t2);
        ai1.addMandatoryAffectation(t2, t3);
        ai1.addMandatoryAffectation(t3, t4);
    }

    @Test
    public void testAddForbiddenAffectation()throws AffectationInventoryException {
        ai1.addForbiddenAffectation(t2, t4);
        ai1.addForbiddenAffectation(t3, t1);
        ai1.addForbiddenAffectation(t4, t2);
        ai1.addForbiddenAffectation(t4, t1);
    }
    @Test
    public void testIsAffectationForbidden() {
        assertTrue(ai1.isAffectationForbidden(t1, t2));
        assertTrue(ai1.isAffectationForbidden(t2, t3));
        assertTrue(ai1.isAffectationForbidden(t3, t4));
        assertFalse(ai1.isAffectationForbidden(t4, t1));
        assertFalse(ai1.isAffectationForbidden(t4, t1));
    }
    @Test
    public void testIsAffectationMandatory() {
        assertTrue(ai1.isAffectationMandatory(t2, t4));
        assertTrue(ai1.isAffectationMandatory(t3, t1));
        assertTrue(ai1.isAffectationMandatory(t4, t2));
        assertTrue(ai1.isAffectationMandatory(t4, t1));
        assertFalse(ai1.isAffectationMandatory(t1, t2));
        assertFalse(ai1.isAffectationMandatory(t2, t3));
    }
}
