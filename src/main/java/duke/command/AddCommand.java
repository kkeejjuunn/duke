package duke.command;

import duke.DukeException;
import duke.WriteFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class AddCommand {
    private ArrayList<Task> tasks;
    private String command;
    private String file_name;

    public AddCommand(ArrayList<Task> tasks, String command, String file_name) {
        this.tasks = tasks;
        this.command = command;
        this.file_name = file_name;
    }

    public ArrayList<Task> updatedTasks() {
        try{
            WriteFile data = new WriteFile(file_name, true);
            String taskToBeWritten;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");

            if (command.startsWith("todo")){
                if (command.length() < 6){
                    try{
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }catch(DukeException e){
                        e.printStackTrace();
                    }
                }
                else{
                    command = command.substring(5);
                    tasks.add(new Todo(command));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size()-1).toString());
                    taskToBeWritten = "T | 0 | " + command;
                    data.writeToFile(taskToBeWritten);
                }
            }
            else if (command.startsWith("deadline")){
                command = command.substring(9);
                String[] deadlineCommand = command.split(" /by ");
                Date date = df.parse(deadlineCommand[1]);
                tasks.add(new Deadline(deadlineCommand[0], date));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size()-1).toString());
                taskToBeWritten = "D | 0 | " + deadlineCommand[0] + " | " + deadlineCommand[1];
                data.writeToFile(taskToBeWritten);
            }
            else if (command.startsWith("event")){
                command = command.substring(6);
                String[] eventCommand = command.split(" /at");
                Date date = df.parse(eventCommand[1]);
                tasks.add(new Event(eventCommand[0], eventCommand[1]));
                //tasks.add(new Event(eventCommand[0], date));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size()-1).toString());
                taskToBeWritten = "E | 0 | " + eventCommand[0] + " | " + eventCommand[1];
                data.writeToFile(taskToBeWritten);
            }
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        }catch (IOException | ParseException e) {
            System.out.println("Error initializing stream");
        }
        return tasks;
    }
}
