import java.util.Scanner;

public class Cate {
    private static final Task[] list = new Task[100];
    private static int index = 0;
    private static final String line = "    _______________________________________ \n";

    public static void main(String[] args) {
        String startup = line
                + "    Hello! I'm Cate \n"
                + "    What can I do for you? \n"
                + line;
        System.out.println(startup);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                printList();
            } else if (userInput.equals("bye")) {
                break;
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ");
                String number = parts[1];
                int value = Integer.parseInt(number);
                markTask(value);
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ");
                String number = parts[1];
                int value = Integer.parseInt(number);
                unmarkTask(value);
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.split(" ", 2)[1];
                Task newTask = new Todo(description);
                addTask(newTask);
            } else if (userInput.startsWith("deadline ")) {
                String input = userInput.split(" ", 2)[1];
                String[] parts = input.split(" /by ", 2);
                Task newTask = new Deadline(parts[0], parts[1]);
                addTask(newTask);
            } else if (userInput.startsWith("event ")) {
                String input = userInput.split(" ", 2)[1];
                String[] parts = input.split(" /from ", 2);
                String[] time = parts[1].split(" /to ", 2);
                Task newTask = new Event(parts[0], time[0], time[1]);
                addTask(newTask);
            }
        }
        System.out.print(line + "    Bye. Hope to see you again soon! \n" + line);
    }

    private static void addTask(Task task) {
        System.out.println(line + "    Got it. I've added this task: \n      " + task.toString());
        list[index++] = task;
        System.out.println("    Now you have " + index + " tasks in the list. \n" + line);
    }

    private static void markTask(int i) {
        list[i - 1].markDone();
        System.out.print(line);
        System.out.println(String.format("    Nice! I've marked this task as done: \n      %s", list[i - 1]));
        System.out.println(line);
    }

    private static void unmarkTask(int i) {
        list[i - 1].markUndone();
        System.out.print(line);
        System.out.println(String.format("    OK, I've marked this task as not done yet: \n      %s", list[i - 1]));
        System.out.println(line);
    }

    private static void printList() {
        System.out.print(line);
        System.out.println("    Here are the tasks in your list: ");
        for (int i = 0; i < list.length; i++) {
            if (list[i] ==  null) break;
            System.out.println(String.format("    %d. %s", i + 1, list[i]));
        }
        System.out.println(line);
    }
}
