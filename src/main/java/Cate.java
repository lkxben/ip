import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cate {
    private static final String filePath = "src/main/data/cate.txt";
    private static final ArrayList<Task> list = Storage.loadTask(filePath);
    private static final String line = "    _______________________________________\n";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private Ui ui;

    public Cate(String filePath) {
        ui = new Ui();
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

    private static void processUserInput(String userInput) throws CateException {
        if (userInput.equals("list")) {
            printList();
        } else if (userInput.equals("todo")) {
            throw new CateException("The description of a todo cannot be empty.");
        } else if (userInput.startsWith("mark ")) {
            String number = userInput.split(" ")[1];
            int value = Integer.parseInt(number);
            markTask(value);
        } else if (userInput.startsWith("unmark ")) {
            String number = userInput.split(" ")[1];
            int value = Integer.parseInt(number);
            unmarkTask(value);
        } else if (userInput.startsWith("delete ")) {
            String number = userInput.split(" ")[1];
            int value = Integer.parseInt(number);
            deleteTask(value);
        } else if (userInput.startsWith("todo ")) {
            String description = userInput.split(" ", 2)[1];
            Task newTask = new Todo(description);
            addTask(newTask);
            Storage.saveTask(newTask, filePath);
        } else if (userInput.startsWith("deadline ")) {
            String input = userInput.split(" ", 2)[1];
            String[] parts = input.split(" /by ", 2);
            Task newTask = new Deadline(parts[0], LocalDateTime.parse(parts[1], formatter));
            addTask(newTask);
            Storage.saveTask(newTask, filePath);
        } else if (userInput.startsWith("event ")) {
            String input = userInput.split(" ", 2)[1];
            String[] parts = input.split(" /from ", 2);
            String[] time = parts[1].split(" /to ", 2);
            Task newTask = new Event(parts[0], time[0], time[1]);
            addTask(newTask);
            Storage.saveTask(newTask, filePath);
        } else {
            throw new CateException("I'm sorry I don't understand!");
        }
    }

    private static void addTask(Task task) {
        System.out.println(line + "    Got it. I've added this task:\n      " + task.toString());
        list.add(task);
        System.out.println("    Now you have " + list.size() + " tasks in the list.\n" + line);
    }

    private static void deleteTask(int i) {
        Task t = list.get(i - 1);
        System.out.print(line);
        System.out.println(String.format("    Noted. I've removed this task:\n      %s", t));
        list.remove(t);
        System.out.println("    Now you have " + list.size() + " tasks in the list.\n" + line);
    }

    private static void markTask(int i) {
        Task t = list.get(i - 1);
        t.markDone();
        System.out.print(line);
        System.out.println(String.format("    Nice! I've marked this task as done:\n      %s", t));
        System.out.println(line);
    }

    private static void unmarkTask(int i) {
        Task t = list.get(i - 1);
        t.markUndone();
        System.out.print(line);
        System.out.println(String.format("    OK, I've marked this task as not done yet:\n      %s", t));
        System.out.println(line);
    }

    private static void printList() {
        System.out.print(line);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) ==  null) break;
            System.out.println(String.format("    %d. %s", i + 1, list.get(i)));
        }
        System.out.println(line);
    }
}
