package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Storage {
    /**
     * The name of the file that stores the tasks
     */
    private String file_name;
    /**
     * The task should be either a todo task, a deadline task or an event task
     */
    private Task task;

    /**
     * Creates a new Storage with the given file_name
     * @param file_name should be the name of the file to store the tasks
     */
    public Storage(String file_name) {
        this.file_name = file_name;
    }

    /**
     * Gets the tasks in the given file_name
     * @return ArrayList of Tasks in the created Storage
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> lists = new ArrayList<>();
        try{
            ReadFile file = new ReadFile(file_name);
            String[] aryLines = file.OpenFile();

            for (int i=0; i<aryLines.length;i++){
                String individualTask = aryLines[i];
                String[] taskToBeRead = individualTask.split(" \\| ");
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
                switch (taskToBeRead[0]){
                    case "T":
                        lists.add(new Todo(taskToBeRead[2]));
                        if (taskToBeRead[1] == "1"){
                            Task t = lists.get(i);
                            t.markAsDone();
                        }
                        System.out.println(lists.get(i).toString());
                        break;
                    case "D":
                        Date by = df.parse(taskToBeRead[3]);
                        lists.add(new Deadline(taskToBeRead[2],by));
                        if (taskToBeRead[1] == "1"){
                            Task t = lists.get(i);
                            t.markAsDone();
                        }
                        System.out.println(lists.get(i).toString());
                        break;
                    case "E":
                        //Date at = df.parse(taskToBeRead[3]);
                        lists.add(new Event(taskToBeRead[2],taskToBeRead[3]));
                        //lists.add(new Event(taskToBeRead[2],at));
                        if (taskToBeRead[1] == "1"){
                            Task t = lists.get(i);
                            t.markAsDone();
                        }
                        System.out.println(lists.get(i).toString());
                        break;
                }
            }
        }catch (IOException | ParseException e){
            System.out.println(e.getMessage());
        }
        return lists;
    }
}
