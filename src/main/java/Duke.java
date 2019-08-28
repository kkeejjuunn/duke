import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
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
                    System.out.println("Got it. I've added this task:");
                    if (command.startsWith("todo")){
                        command = command.substring(5);
                        lists.add(new Todo(command));
                        System.out.println("  [T][" + lists.get(lists.size()-1).isDone + "] " + lists.get(lists.size()-1).description);
                    }
                    else if (command.startsWith("deadline")){
                        command = command.substring(9);
                        String[] deadlineCommand = command.split(" /by ");
                        lists.add(new Deadline(deadlineCommand[0], deadlineCommand[1]));
                        System.out.println("  [D][" + lists.get(lists.size()-1).isDone + "] " + lists.get(lists.size()-1).description + " (by: " + deadlineCommand[1] + ")");
                    }
                    else if (command.startsWith("event")){
                        command = command.substring(6);
                        String[] eventCommand = command.split(" /at");
                        lists.add(new Event(eventCommand[0], eventCommand[1]));
                        System.out.println("  [E][" + lists.get(lists.size()-1).isDone + "] " + lists.get(lists.size()-1).description + " (at: " + eventCommand[1] + ")");
                    }
                    System.out.println("Now you have " + lists.size() + " tasks in the list.");
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

