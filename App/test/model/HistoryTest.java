package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public class HistoryTest {
    private static History h1;
    private static History h2;
    private static History h3;

    private static Teenager t1;
    private static Teenager t2;
    private static Teenager t3;
    private static Teenager t4;
    private static Teenager t5;
    private static Teenager t6;

    @BeforeEach
    public void initialization(){
        h1 = new History();
        h2 = new History();
        h3 = new History();
        h1.importCSV("./test/data/HistorySample1", true);
        h2.importCSV("./test/data/HistorySample2", true);
        h3.importCSV("./test/data/HistorySample3", true);

        t1 = new Teenager("Bernard", "Luca", LocalDate.of(2006, 7, 10), null, Country.FR);
        t2 = new Teenager("Bernard", "Jonas", LocalDate.of(2006, 11, 25), null, Country.IT);
        t3 = new Teenager("Kowalski", "Lucie", LocalDate.of(2006, 8, 25), null, Country.ES);
        t4 = new Teenager("Smith", "Noah", LocalDate.of(2006, 01, 03), null, Country.IT);
        t5 = new Teenager("Dupont", "Thomas", LocalDate.of(2005, 05, 14), null, Country.ES);
    }

    @Test
    public void testInitialization(){
        
    }

    @Test
    public void testHasAlreadyBeenMatched(){
        assertTrue(h2.hasAlreadyBeenMatched(t1, t2));
        assertTrue(h1.hasAlreadyBeenMatched(t1, t3));
        assertTrue(h3.hasAlreadyBeenMatched(t4, t5));
        
        assertFalse(h2.hasAlreadyBeenMatched(t1, t2));
        assertFalse(h3.hasAlreadyBeenMatched(t1, t2));
        assertFalse(h1.hasAlreadyBeenMatched(t3, t3));
    }
}
