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

        System.out.println("___________________________________________");

        System.out.println("Hello! I am Duke\nWhat can I do do for you?");

        System.out.println("___________________________________________");

        Scanner input = new Scanner(System.in);

        String command = input.next();

        while(!command.equals("bye")){
            System.out.println("___________________________________________");
            System.out.println(command);
            System.out.println("___________________________________________");
            command = input.next();
        }

        System.out.println("___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________");
    }
}

