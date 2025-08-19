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
            } else {
                addTask(userInput);
            }
        }
        System.out.println(line + "    Bye. Hope to see you again soon! \n" + line);
    }

    private static void addTask(String description) {
        System.out.println(line + "    added: " + description + "\n" + line);
        Task newTask = new Task(description);
        list[index++] = newTask;
    }

    private static void markTask(int i) {
        list[i - 1].markDone();
        System.out.print(line);
        System.out.println(String.format("    Nice! I've marked this task as done: \n      %s", list[i - 1]));
        System.out.print(line);
    }

    private static void unmarkTask(int i) {
        list[i - 1].markUndone();
        System.out.print(line);
        System.out.println(String.format("    OK, I've marked this task as not done yet: \n      %s", list[i - 1]));
        System.out.print(line);
    }

    private static void printList() {
        System.out.print(line);
        System.out.println("    Here are the tasks in your list: ");
        for (int i = 0; i < list.length; i++) {
            if (list[i] ==  null) break;
            System.out.println(String.format("    %d. %s", i + 1, list[i]));
        }
        System.out.print(line);
    }
}
