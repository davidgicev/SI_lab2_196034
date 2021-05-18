import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    public static String[] exceptionMessages = new String[]{
            "The hours are smaller than the minimum",
            "The hours are grater than the maximum",
            "The minutes are not valid!",
            "The seconds are not valid",
            "The time is greater than the maximum"
    };

    final SILab2 instance = new SILab2();

    @Test
    void multipleConditionCoverage() {
        Integer result;
        RuntimeException e;

        // (hr < 0 || hr > 24)
        // TX, FT, FF
        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(-1, 0, 0)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[0]),
                "Expected: "+exceptionMessages[0]);

        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(26, 0, 0)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[1]),
                "Expected: "+exceptionMessages[1]);

        // (min < 0 || min > 59)
        // TX, FT, FF

        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(0, -1, 0)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[2]),
                "Expected: "+exceptionMessages[2]);

        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(0, 100, 0)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[2]),
                "Expected: "+exceptionMessages[2]);

        // (sec >= 0 && sec <= 59)
        // TF, FX, TT

        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(0, 0, -1)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[3]),
                "Expected: "+exceptionMessages[3]);


        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(0, 0, 100)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[3]),
                "Expected: "+exceptionMessages[3]);

        // (hr == 24 && min == 0 && sec == 0)
        // TTT, TTF, TFX, FXX

        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(24, 0, 10)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[4]),
                "Expected: "+exceptionMessages[4]);

        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(24, 10, 0)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[4]),
                "Expected: "+exceptionMessages[4]);

        e = assertThrows(
                RuntimeException.class,
                () -> instance.function(Collections.singletonList(
                        new Time(24, 10, 10)
                ))
        );
        assertTrue(e.getMessage().contains(exceptionMessages[4]),
                "Expected: "+exceptionMessages[4]);

        // validni primeri koi gi pokrivaat ostanatite uslovi i poduslovi

        assertEquals(
                Collections.singletonList(24 * 3600),
                instance.function(Collections.singletonList(
                        new Time(24, 0, 0)
                ))
        );

        assertEquals(
                Collections.singletonList(10*3600 + 10*60 + 10),
                instance.function(Collections.singletonList(
                        new Time(10, 10, 10)
                ))
        );
    }

    @Test
    void everyBranchCriterion() {
        // testovi koi ke napravat exceptions
        RuntimeException e = null;
        Time[] params = new Time[]{
                new Time(-1, 10, 10),
                new Time(26, 10, 10),
                new Time(10, -1, 10),
                new Time(10, 10, -1),
                new Time(24, 10, 10)
        };
        for(int i=0; i<5; i++) {
            Time p = params[i];
            e = assertThrows(
                    RuntimeException.class,
                    () -> instance.function(Collections.singletonList(p))
            );
            assertTrue(e.getMessage().contains(exceptionMessages[i]),
                    "Expected: "+exceptionMessages[i]);
        }

        // testovi koi ke dadat rezultat
        assertEquals(
                Collections.singletonList(24 * 3600),
                instance.function(Collections.singletonList(
                        new Time(24, 0, 0)
                ))
        );

        assertEquals(
                Collections.singletonList(10*3600 + 10*60 + 10),
                instance.function(Collections.singletonList(
                        new Time(10, 10, 10)
                ))
        );
    }
}