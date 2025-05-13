package App;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public class HistoryTest {
    History h1;
    History h2;
    History h3;

    Teenager t1;
    Teenager t2;
    Teenager t3;
    Teenager t4;
    Teenager t5;
    Teenager t6;

    @BeforeEach
    public void initialization(){
        h1 = new History();
        h2 = new History();
        h3 = new History();
        h1.importCSV("./test/data/HistorySample1");
        h2.importCSV("./test/data/HistorySample2");
        h3.importCSV("./test/data/HistorySample3");

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
