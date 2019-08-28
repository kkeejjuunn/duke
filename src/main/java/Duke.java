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
                    lists.add(new Task(command));
                    System.out.println("___________________________________________");
                    System.out.println("added: " + command);
                    System.out.println("___________________________________________");
                }
            }
            else{
                if(lists.size() > 0){
                    System.out.println("___________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < lists.size(); i++) {
                        System.out.println((i+1) + ".[" + lists.get(i).getStatusIcon() + "] " + lists.get(i).description);
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

