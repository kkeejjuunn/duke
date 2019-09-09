package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand {
    private ArrayList<Task> tasks;
    private String command;

    public DeleteCommand(ArrayList<Task> tasks, String command) {
        this.tasks = tasks;
        this.command = command;
    }

    public ArrayList<Task> UpdatedTasks () {
        String[] specificCommand = command.split(" ");
        int index = Integer.parseInt(specificCommand[1]) - 1;
        Task t = tasks.get(index);
        System.out.println("___________________________________________");
        System.out.println("Noted! I've removed this task:");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the lit.");
        System.out.println("___________________________________________");
        return tasks;
    }
}
