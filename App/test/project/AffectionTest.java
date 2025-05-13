package App;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AffectionTest {

    @BeforeEach
    public void initialization(){
        Map<String,String> cr1 = new HashMap<>();
        Map<String,String> cr2 = new HashMap<>();
        Map<String,String> cr3 = new HashMap<>();
        
        cr1.put("HOST_HAS_ANIMAL", "false");
        cr2.put("GUEST_FOOD", "nonuts");
        cr3.put("HOBBIES", "videogames");

        Teenager t1 = new Teenager("Tom","Dupont", LocalDate.of(2006,5,10), cr1, Country.ES);
        Teenager t2 = new Teenager("Anna", "Durand", LocalDate.of(2007, 3, 12), cr2, Country.FR);
        Teenager t3 = new Teenager("Leo", "Martin", LocalDate.of(2008, 8, 22), cr3, Country.IT);
        Teenager t4 = t1;

        Affection a1 = new Affectation(t1, t2);
        Affection a2 = new Affectation(t2, t3);
        Affection a3 = new Affectation(t4, t1);

    }

    @Test 
    public void testCompatibility(){
        assertTrue(a1.compatibility() > 0.0 && a1.compatibility() < 1.0 );
        assertTrue(a2.compatibility() > 0.0 && a2.compatibility() < 1.0 );
        assertEquals(a3.compatibility(), 1.0);

    }

}
