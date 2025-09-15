package cate.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import cate.task.Deadline;
import cate.task.Event;
import cate.task.Task;
import cate.task.TaskList;
import cate.task.Todo;

/**
 * Handles persistent storage of tasks for the Cate application.
 * <p>
 * The {@code Storage} class loads tasks from a file at startup and saves tasks
 * to the file whenever they are added, deleted, or updated. Each task is stored
 * in a simple text format, which can be read back into memory at runtime.
 * </p>
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
     * <p>
     * Each line in the file corresponds to one task in the format:
     * <ul>
     *   <li>{@code T,doneFlag,description}</li>
     *   <li>{@code D,doneFlag,description,yyyy-MM-dd HHmm}</li>
     *   <li>{@code E,doneFlag,description,start,end}</li>
     * </ul>
     * where {@code doneFlag} is {@code 1} if the task is completed, otherwise {@code 0}.
     * </p>
     *
     * @return An {@link ArrayList} containing the loaded tasks.
     *      Returns an empty list if the file does not exist or is empty.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> output = new ArrayList<>();
        try {
            ensureFileExists();
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
        } catch (IOException ignored) {
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
        try {
            ensureFileExists();
            try (FileWriter writer = new FileWriter(filePath, true)) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            // Ignored silently
        }
    }

    /**
     * Saves the entire task list to the file, overwriting any existing content.
     *
     * @param tasks The {@link TaskList} containing tasks to be saved.
     */
    public void save(TaskList tasks) {
        try {
            ensureFileExists();
            try (FileWriter writer = new FileWriter(filePath, false)) {
                for (Task task : tasks.getList()) {
                    writer.write(task.toFileString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            // Ignored silently
        }
    }

    /**
     * Ensures that the storage file exists. Creates the file and parent directories if they do not exist.
     *
     * @throws IOException if the file or directories cannot be created
     */
    private void ensureFileExists() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }
}
