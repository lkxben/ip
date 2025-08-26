import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static ArrayList<Task> loadTask(String filePath) {
        ArrayList<Task> output = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
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

    public static void saveTask(Task task, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(task.toFileString() + System.lineSeparator());
        } catch (IOException e) {
            // ignore
        }
    }
}
