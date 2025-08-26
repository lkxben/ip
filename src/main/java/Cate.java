import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cate {
    private static final String filePath = "src/main/data/cate.txt";
    private static final String line = "    _______________________________________\n";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private Ui ui;
    private TaskList tasks;

    public Cate(String filePath) {
        ui = new Ui();
        tasks = new TaskList(Storage.loadTask(filePath));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) break;
            try {
                processUserInput(userInput);
            } catch (CateException e) {
                System.out.println(line + "    " + e.getMessage() + "\n" + line);
            }
        }

        System.out.print(line + "    Bye. Hope to see you again soon!\n" + line);
    }

    public static void main(String[] args) {
        new Cate(filePath).run();
    }

    private void processUserInput(String userInput) throws CateException {
        if (userInput.equals("list")) {
            tasks.printList();
        } else if (userInput.equals("todo")) {
            throw new CateException("The description of a todo cannot be empty.");
        } else if (userInput.startsWith("mark ")) {
            String number = userInput.split(" ")[1];
            int value = Integer.parseInt(number);
            tasks.markTask(value);
        } else if (userInput.startsWith("unmark ")) {
            String number = userInput.split(" ")[1];
            int value = Integer.parseInt(number);
            tasks.unmarkTask(value);
        } else if (userInput.startsWith("delete ")) {
            String number = userInput.split(" ")[1];
            int value = Integer.parseInt(number);
            tasks.deleteTask(value);
        } else if (userInput.startsWith("todo ")) {
            String description = userInput.split(" ", 2)[1];
            Task newTask = new Todo(description);
            tasks.addTask(newTask);
            Storage.saveTask(newTask, filePath);
        } else if (userInput.startsWith("deadline ")) {
            String input = userInput.split(" ", 2)[1];
            String[] parts = input.split(" /by ", 2);
            Task newTask = new Deadline(parts[0], LocalDateTime.parse(parts[1], formatter));
            tasks.addTask(newTask);
            Storage.saveTask(newTask, filePath);
        } else if (userInput.startsWith("event ")) {
            String input = userInput.split(" ", 2)[1];
            String[] parts = input.split(" /from ", 2);
            String[] time = parts[1].split(" /to ", 2);
            Task newTask = new Event(parts[0], time[0], time[1]);
            tasks.addTask(newTask);
            Storage.saveTask(newTask, filePath);
        } else {
            throw new CateException("I'm sorry I don't understand!");
        }
    }

}
