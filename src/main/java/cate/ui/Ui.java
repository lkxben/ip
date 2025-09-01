package cate.ui;

import cate.task.Task;
import cate.task.TaskList;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the Cate application.
 * The {@code Ui} class is responsible for displaying messages,
 * formatting output, and reading user input.
 */
public class Ui {
    private static final String line = "    _______________________________________\n";

    /**
     * Constructs a {@code Ui} instance and immediately
     * shows the startup message.
     */
    public Ui() {
        showStartupMessage();
    }

    /**
     * Displays the startup message when the program begins.
     */
    public void showStartupMessage() {
        System.out.println(line
                + "    Hello! I'm cate.ui.Cate\n"
                + "    What can I do for you?\n"
                + line);
    }

    /**
     * Displays the farewell message when the program ends.
     */
    public void showBye() {
        System.out.print(line + "    Bye. Hope to see you again soon!\n" + line);
    }

    public String printList(TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        Task[] list;
        list = tasks.getList().toArray(new Task[0]);
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            output.append(String.format("    %d. %s\n", i + 1, list[i]));
        }
        return output.toString();
    }

    public String addTask(Task t) {
        return String.format("Got it. I've added this task:\n%s", t);
    }

    public String deleteTask(Task t, int size) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n", t, size);
    }

    public String markTask(Task t) {
        return String.format("Nice! I've marked this task as done:\n%s", t);
    }

    public String unmarkTask(Task t) {
        return String.format("OK, I've marked this task as not done yet:\n%s", t);
    }
}
