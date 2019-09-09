package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class ListCommand {

    public ListCommand(ArrayList<Task> tasks) {
        System.out.println("___________________________________________");
        for(int i=0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        System.out.println("___________________________________________");
    }
}
