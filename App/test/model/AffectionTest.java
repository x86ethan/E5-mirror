package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AffectionTest {

    private static Map<String, String> cr1;
    private static Map<String, String> cr2;
    private static Map<String, String> cr3;

    private static Teenager t1;
    private static Teenager t2;
    private static Teenager t3;
    private static Teenager t4;

    private static Affectation a1;
    private static Affectation a2;
    private static Affectation a3;

    private static Map<String, String> rcr1;
    private static Map<String, String> rcr2;
    private static Map<String, String> rcr3;
    private static Map<String, String> rcr4;
    private static Map<String, String> rcr5;
    private static Map<String, String> rcr6;
    private static Map<String, String> rcr7;
    private static Map<String, String> rcr8;

    private static Teenager rt1;
    private static Teenager rt2;
    private static Teenager rt3;
    private static Teenager rt4;
    private static Teenager rt5;
    private static Teenager rt6;
    private static Teenager rt7;
    private static Teenager rt8;

    private static History rh;


    @BeforeAll
    public static void initialization(){
        cr1 = new HashMap<>();
        cr2 = new HashMap<>();
        cr3 = new HashMap<>();
        
        cr1.put("HOST_HAS_ANIMAL", "false");
        cr1.put("HOST_FOOD", "nonuts");
        cr2.put("GUEST_FOOD", "nonuts");
        cr3.put("HOBBIES", "videogames");

        t1 = new Teenager("Tom","Dupont", LocalDate.of(2006,5,10), cr1, Country.ES);
        t2 = new Teenager("Anna", "Durand", LocalDate.of(2007, 3, 12), cr2, Country.FR);
        t3 = new Teenager("Leo", "Martin", LocalDate.of(2008, 8, 22), cr3, Country.IT);
        t4 = t1;

        try {
            a1 = new Affectation(t1, t2);
            a2 = new Affectation(t2, t3);
            a3 = new Affectation(t4, t1);
        } catch (AffectationException e) {
            e.printTrace();
        }
        // Test the criteria matching
        rcr1 = new HashMap<>();
        rcr2 = new HashMap<>();
        rcr3 = new HashMap<>();
        rcr4 = new HashMap<>();
        rcr5 = new HashMap<>();
        rcr6 = new HashMap<>();
        rcr7 = new HashMap<>();
        rcr8 = new HashMap<>();

        rcr1.put("GUEST_ANIMAL_ALLERGY", "true");
        rcr2.put("HOST_HAS_ANIMAL", "true");
        rcr3.put("GUEST_FOOD", "vegetarian");
        rcr4.put("HOST_FOOD", "nonuts");
        rcr5.put("GUEST_FOOD", "nonuts");
        rcr6.put("HOST_FOOD", "vegetarian");
        rcr7.put("HISTORY", "same");
        rcr8.put("HISTORY", "other");

        rh = new History();

        rt1 = new Teenager("Gilbert", "Montagnié", LocalDate.of(1951, 12, 28), rcr1, Country.FR);
        rt2 = new Teenager("Claude", "François", LocalDate.of(1939, 2, 1), rcr2, Country.FR);
        rt3 = new Teenager("Daniel", "Balavoine", LocalDate.of(1952, 2, 5), rcr3, Country.FR);
        rt4 = new Teenager("France", "Gall", LocalDate.of(1947, 10, 9), rcr4, Country.FR);
        rt5 = new Teenager("Johnny", "Hallyday", LocalDate.of(1943, 6, 15), rcr5, Country.FR);
        rt6 = new Teenager("Eddy", "Mitchell", LocalDate.of(1942, 7, 3), rcr6, Country.FR);
        rt7 = new Teenager("Serge", "Gainsbourg", LocalDate.of(1928, 4, 2), rcr7, Country.FR);
        rt8 = new Teenager("Jacques", "Dutronc", LocalDate.of(1943, 4, 28), rcr8, Country.FR);

        try {
            rh.addAffectation(new Affectation(rt8, rt7));
            rh.addAffectation(new Affectation(rt8, rt8));
            rh.addAffectation(new Affectation(rt7, rt7));
        } catch (AffectationException e) {
            e.printTrace();
        }

    }

    @Test 
    public void testCompatibility(){
        try {
            assertTrue(a1.compatibility() > 0.0 && a1.compatibility() <= 1.0 );
            assertTrue(a2.compatibility() > 0.0 && a2.compatibility() < 1.0 );
            assertEquals(a3.compatibility(), 1.0);
        } catch (AffectationException e) {
            e.printTrace();
        }
    }
    @Test
    public void testAffectationCriteriaException() {
        assertThrows(AffectationException.class, () -> {new Affectation(rt2, rt1);});
        assertThrows(AffectationException.class, () -> {new Affectation(rt4, rt3);});
        assertThrows(AffectationException.class, () -> {new Affectation(rt6, rt5);});
        assertThrows(AffectationException.class, () -> {new Affectation(rt8, rt7, rh);});
        assertThrows(AffectationException.class, () -> {new Affectation(rt8, rt8, rh);});
        assertDoesNotThrow(() -> {new Affectation(rt7, rt7, rh);});
    }

    @Test
    public void testAffectationExceptionReason() {
        try {
            new Affectation(rt1, rt2);
        } catch (AffectationException e) {
            assertEquals(e.reason, "ANIMAL_ALLERGY");
        }

        try {
            new Affectation(rt3, rt4);
        } catch (AffectationException e) {
            assertEquals(e.reason, "FOOD_ALLERGY");
        }

        try {
            new Affectation(rt5, rt6);
        } catch (AffectationException e) {
            assertEquals(e.reason, "FOOD_ALLERGY");
        }

        try {
            new Affectation(rt7, rt8);
        } catch (AffectationException e) {
            assertEquals(e.reason, "HISTORY");
        }

        try {
            new Affectation(rt8, rt8);
        } catch (AffectationException e) {
            assertEquals(e.reason, "HISTORY");
        }

    }



}
