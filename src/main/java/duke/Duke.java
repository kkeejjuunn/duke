package duke;

import duke.command.*;
import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private Ui ui;
    private Parser parser;
    private DoneCommand doneCommand;
    private DeleteCommand deleteCommand;
    private AddCommand addCommand;
    private static ArrayList<Task> lists;

    public Duke() {
        ui = new Ui();
        ui.greet();

        String file_name = "data/duke.txt";
        storage = new Storage(file_name);
        lists = storage.loadTasks();

        Scanner input = new Scanner(System.in);

        String command = input.nextLine();
        
        while(!command.equals("bye")) {
            parser = new Parser(lists, command, file_name);
            lists = parser.parseCommand();

            command = input.nextLine();
        }
        ui.bye();
    }

    public static void main(String[] args) throws IOException {
        new Duke();
    }

}

