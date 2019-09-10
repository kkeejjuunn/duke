package duke.command;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    private ArrayList<Task> tasks;
    private AddCommand addCommand;

    @Test
    public void addTodoTaskTest() {
        addCommand = new AddCommand(new ArrayList<Task>(), "todo play golf", "duke.txt");
        tasks = addCommand.updatedTasks();
        assertEquals(1, tasks.size());
    }
}
