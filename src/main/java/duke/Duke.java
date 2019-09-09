package duke;

import duke.command.*;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private DoneCommand doneCommand;
    private DeleteCommand deleteCommand;
    private AddCommand addCommand;
    private static ArrayList<Task> lists;

    public static void greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("___________________________________________");

        System.out.println("Hello! I am duke.Duke\nWhat can I do do for you?");

        System.out.println("___________________________________________");
    }

    public Duke() {
        String file_name = "data/duke.txt";
        storage = new Storage(file_name);
        lists = storage.loadTasks();

        Scanner input = new Scanner(System.in);

        String command = input.nextLine();

        while(!command.equals("bye")) {
            if(command.equals("list")) {
                new ListCommand(lists);
            }
            else if (command.substring(0, 4).equals("done")) {
                doneCommand = new DoneCommand(lists, command);
                lists = doneCommand.UpdatedTasks();
            }
            else if (command.substring(0, 6).equals("delete")) {
                deleteCommand = new DeleteCommand(lists, command);
                lists = deleteCommand.UpdatedTasks();
            }
            else if (command.substring(0, 4).equals("find")) {
                new FindCommand(lists, command);
            }
            else {
                if (!command.startsWith("todo") && !command.startsWith("deadline") && !command.startsWith("event")){
                    try{
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }catch(DukeException e){
                        e.printStackTrace();
                    }
                }
                else {
                    addCommand = new AddCommand(lists, command, file_name);
                    lists = addCommand.updatedTasks();
                }
            }

            command = input.nextLine();
        }

        System.out.println("___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________");
    }

    public static void main(String[] args) throws IOException {
        greet();

        new Duke();

        /*
        Scanner input = new Scanner(System.in);

        String command = input.nextLine();

        while(!command.equals("bye")){
            if(!command.equals("list")){
                //input is done
                if (command.substring(0, 4).equals("done")){
                    String[] specificCommand = command.split(" ");
                    int index = Integer.parseInt(specificCommand[1]) - 1;
                    Task t = lists.get(index);
                    t.isDone = true;
                    System.out.println("___________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + lists.get(index).isDone + "] " + lists.get(index).description);
                    System.out.println("___________________________________________");
                }
                else if (command.substring(0, 6).equals("delete")){
                    String[] specificCommand = command.split(" ");
                    int index = Integer.parseInt(specificCommand[1]) - 1;
                    Task t = lists.get(index);
                    System.out.println("___________________________________________");
                    System.out.println("Noted! I've removed this task:");
                    System.out.println(lists.get(index).toString());
                    lists.remove(index);
                    System.out.println("Now you have " + lists.size() + " tasks in the lit.");
                    System.out.println("___________________________________________");
                }
                else if (command.substring(0, 4).equals("find")){
                    String[] specificCommand = command.split(" ");
                    ArrayList<Task> matchingTasks = new ArrayList<Task>();
                    for(int i=0; i<lists.size();i++){
                        if(lists.get(i).description.contains(specificCommand[1])){
                            matchingTasks.add(lists.get(i));
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
                else{
                    System.out.println("___________________________________________");
                    if (!command.startsWith("todo") && !command.startsWith("deadline") && !command.startsWith("event")){
                        try{
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }catch(DukeException e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        try{
                            WriteFile data = new WriteFile(file_name, true);
                            String taskToBeWritten;

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
                                    lists.add(new Todo(command));
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println("  [T][" + lists.get(lists.size()-1).getStatusIcon() + "] " + lists.get(lists.size()-1).description);
                                    taskToBeWritten = "T | 0 | " + command;
                                    data.writeToFile(taskToBeWritten);
                                }
                            }
                            else if (command.startsWith("deadline")){
                                command = command.substring(9);
                                String[] deadlineCommand = command.split(" /by ");
                                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                Date date = df.parse(deadlineCommand[1]);
                                lists.add(new Deadline(deadlineCommand[0], date));
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  [D][" + lists.get(lists.size()-1).isDone + "] " + lists.get(lists.size()-1).description + " (by: " + date + ")");
                                taskToBeWritten = "D | 0 | " + deadlineCommand[0] + " | " + deadlineCommand[1];
                                data.writeToFile(taskToBeWritten);
                            }
                            else if (command.startsWith("event")){
                                command = command.substring(6);
                                String[] eventCommand = command.split(" /at");
                                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                Date date = df.parse(eventCommand[1]);
                                lists.add(new Event(eventCommand[0], date));
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  [E][" + lists.get(lists.size()-1).isDone + "] " + lists.get(lists.size()-1).description + " (at: " + date + ")");
                                taskToBeWritten = "E | 0 | " + eventCommand[0] + " | " + eventCommand[1];
                                data.writeToFile(taskToBeWritten);
                            }
                            System.out.println("Now you have " + lists.size() + " tasks in the lit.");

                        }catch (IOException | ParseException e) {
                            System.out.println("Error initializing stream");
                        }
                    }
                    System.out.println("___________________________________________");
                }
            }
            //input is list
            else{
                showList
            }
            command = input.nextLine();


        }*/

        System.out.println("___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________");
    }

}

