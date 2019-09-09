package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class FindCommand {

    public FindCommand (ArrayList<Task> tasks, String command) {
        String[] specificCommand = command.split(" ");
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for(int i=0; i<tasks.size();i++){
            if(tasks.get(i).containsKeyword(specificCommand[1])){
                matchingTasks.add(tasks.get(i));
            }
        }
        if(matchingTasks.size() > 0){
            System.out.println("___________________________________________");
            System.out.println("Here are the matching tasks in your list:");
            for(int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i+1) + "." + matchingTasks.get(i).toString());
            }
            System.out.println("___________________________________________");
        }
    }
}
