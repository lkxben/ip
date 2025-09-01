package cate.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import cate.task.Deadline;
import cate.task.Event;
import cate.task.Task;
import cate.task.Todo;

/**
 * Handles persistent storage of tasks for the Cate application.
 * The {@code Storage} class loads tasks from a file at startup
 * and saves tasks to the file when new tasks are added.
 */
public class Storage {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String filePath;

    /**
     * Constructs a {@code Storage} instance with the given file path.
     *
     * @param filePath The path to the file where tasks will be saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into memory.
     * Each line in the file corresponds to one task in the format:
     * <ul>
     *   <li>{@code T,doneFlag,description}</li>
     *   <li>{@code D,doneFlag,description,yyyy-MM-dd HHmm}</li>
     *   <li>{@code E,doneFlag,description,start,end}</li>
     * </ul>
     * where {@code doneFlag} is {@code 1} if the task is completed, otherwise {@code 0}.
     *
     * @return An {@link ArrayList} containing the loaded tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> output = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                String desc = parts[2];
                Task t = null;
                if (parts[0].equals("T")) {
                    t = new Todo(desc);
                } else if (parts[0].equals("D")) {
                    t = new Deadline(desc, LocalDateTime.parse(parts[3], formatter));
                } else if (parts[0].equals("E")) {
                    t = new Event(desc, parts[3], parts[4]);
                }
                boolean done = parts[1].equals("1");
                if (done && t != null) {
                    t.markDone();
                }
                output.add(t);
            }
            sc.close();
        } catch (FileNotFoundException ignored) {
            return output;
        }
        return output;
    }

    /**
     * Appends a single task to the file in its storage format.
     *
     * @param task The {@link Task} to be saved.
     */
    public void saveTask(Task task) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(task.toFileString() + System.lineSeparator());
        } catch (IOException e) {
            // Ignored silently
        }
    }
}
