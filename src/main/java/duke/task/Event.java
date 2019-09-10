package duke.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String description, String at) {
        super(description);
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.at = df.parse(at);
        }catch (ParseException e){
            System.out.println("Error initializing stream");
        }
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
