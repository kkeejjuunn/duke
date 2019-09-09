package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
