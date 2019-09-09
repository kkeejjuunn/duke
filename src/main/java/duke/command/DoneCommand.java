package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class DoneCommand {
    private ArrayList<Task> tasks;
    private String command;

    public DoneCommand(ArrayList<Task> tasks, String command) {
        this.tasks = tasks;
        this.command = command;
    }

    public ArrayList<Task> UpdatedTasks () {
        String[] specificCommand = command.split(" ");
        int index = Integer.parseInt(specificCommand[1]) - 1;
        Task t = tasks.get(index);
        t.markAsDone();
        System.out.println("___________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index).toString());
        System.out.println("___________________________________________");
        return tasks;
    }
}
