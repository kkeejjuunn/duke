package duke;

public class Ui {
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("___________________________________________");

        System.out.println("Hello! I am duke.Duke\nWhat can I do do for you?");

        System.out.println("___________________________________________");

        return;
    }

    public void showLine() {
        System.out.println("___________________________________________");
    }

    public void bye() {
        System.out.println("___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________");
    }
}
