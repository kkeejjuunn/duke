package duke;

import duke.command.*;
import duke.task.Task;

import java.util.ArrayList;

public class Parser {
    private DoneCommand doneCommand;
    private DeleteCommand deleteCommand;
    private AddCommand addCommand;
    private FindCommand findCommand;
    private ListCommand listCommand;

    private ArrayList<Task> tasks;
    private String command;
    private String file_name;

    public Parser (ArrayList<Task> tasks, String command, String file_name){
        this.tasks = tasks;
        this.command = command;
        this.file_name = file_name;
    }

    public ArrayList<Task> parseCommand() {
        if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            addCommand = new AddCommand(tasks, command, file_name);
            return addCommand.updatedTasks();
        }
        else if (command.startsWith("find")) {
            findCommand = new FindCommand(tasks, command);
        }
        else if (command.startsWith("done")) {
            doneCommand = new DoneCommand(tasks, command);
            return doneCommand.UpdatedTasks();
        }
        else if (command.startsWith("delete")) {
            deleteCommand = new DeleteCommand(tasks, command);
            return deleteCommand.UpdatedTasks();
        }
        else if (command.equals("list")) {
            listCommand = new ListCommand(tasks);
        }
        else {
            try{
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }catch(DukeException e){
                e.printStackTrace();
            }
        }
        return tasks;
    }
}
