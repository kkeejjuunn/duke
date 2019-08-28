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

        ArrayList<String> lists = new ArrayList<String>();

        System.out.println("___________________________________________");

        System.out.println("Hello! I am Duke\nWhat can I do do for you?");

        System.out.println("___________________________________________");

        Scanner input = new Scanner(System.in);

        String command = input.nextLine();

        while(!command.equals("bye")){
            if(!command.equals("list")){
                lists.add(command);
                System.out.println("___________________________________________");
                System.out.println("added: " + command);
                System.out.println("___________________________________________");
            }
            else{
                if(lists.size() > 0){
                    System.out.println("___________________________________________");
                    for(int i = 0; i < lists.size(); i++) {
                        System.out.println((i+1) + ". " + lists.get(i));
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

