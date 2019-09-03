import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String file_name = "data/duke.txt";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //ArrayList<String> lists = new ArrayList<String>();
        ArrayList<Task> lists = new ArrayList<Task>();

        System.out.println("___________________________________________");

        System.out.println("Hello! I am Duke\nWhat can I do do for you?");

        System.out.println("___________________________________________");

        //read file from duke.txt
        try{
            ReadFile file = new ReadFile(file_name);
            String[] aryLines = file.OpenFile();

            for (int i=0; i<aryLines.length;i++){
                String individualTask = aryLines[i];
                String[] taskToBeRead = individualTask.split(" \\| ");
                switch (taskToBeRead[0]){
                    case "T":
                        lists.add(new Todo(taskToBeRead[2]));
                        if (taskToBeRead[1] == "1"){
                            Task t = lists.get(i);
                            t.isDone = true;
                            System.out.println("[T][\u2713] " + taskToBeRead[2]);
                        }
                        else{
                            System.out.println("[T][\u2718] " + taskToBeRead[2]);
                        }
                        break;
                    case "D":
                        lists.add(new Deadline(taskToBeRead[2],taskToBeRead[3]));
                        if (taskToBeRead[1] == "1"){
                            Task t = lists.get(i);
                            t.isDone = true;
                            System.out.println("[D][\u2713] " + taskToBeRead[2] + " (by: " + taskToBeRead[3] + ")");
                        }
                        else{
                            System.out.println("[D][\u2718] " + taskToBeRead[2] + " (by: " + taskToBeRead[3] + ")");
                        }
                        break;
                    case "E":
                        lists.add(new Event(taskToBeRead[2],taskToBeRead[3]));
                        if (taskToBeRead[1] == "1"){
                            Task t = lists.get(i);
                            t.isDone = true;
                            System.out.println("[E][\u2713] " + taskToBeRead[2] + " (at: " + taskToBeRead[3] + ")");
                        }
                        else{
                            System.out.println("[E][\u2718] " + taskToBeRead[2] + " (at: " + taskToBeRead[3] + ")");
                        }
                        break;
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

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
                                lists.add(new Deadline(deadlineCommand[0], deadlineCommand[1]));
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  [D][" + lists.get(lists.size()-1).isDone + "] " + lists.get(lists.size()-1).description + " (by: " + deadlineCommand[1] + ")");
                                taskToBeWritten = "D | 0 | " + deadlineCommand[0] + " | " + deadlineCommand[1];
                                data.writeToFile(taskToBeWritten);
                            }
                            else if (command.startsWith("event")){
                                command = command.substring(6);
                                String[] eventCommand = command.split(" /at");
                                lists.add(new Event(eventCommand[0], eventCommand[1]));
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  [E][" + lists.get(lists.size()-1).isDone + "] " + lists.get(lists.size()-1).description + " (at: " + eventCommand[1] + ")");
                                taskToBeWritten = "E | 0 | " + eventCommand[0] + " | " + eventCommand[1];
                                data.writeToFile(taskToBeWritten);
                            }
                            System.out.println("Now you have " + lists.size() + " tasks in the list.");

                        }catch (IOException e) {
                            System.out.println("Error initializing stream");
                        }
                    }
                    System.out.println("___________________________________________");
                }
            }
            //input is list
            else{
                if(lists.size() > 0){
                    System.out.println("___________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < lists.size(); i++) {
                        System.out.println((i+1) + "." + lists.get(i).toString());
                    }
                    System.out.println("___________________________________________");
                }
            }
            command = input.nextLine();
        }

        System.out.println("___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________");
    }
}

