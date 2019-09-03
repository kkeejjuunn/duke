import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.at + ")";
    }
}
