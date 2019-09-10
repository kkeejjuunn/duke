package duke.task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals("[E][\u2718] Orientation (at: Fri Dec 20 09:00:00 SGT 2019)", new Event("Orientation", "20/12/2019 0900").toString());
    }
}
