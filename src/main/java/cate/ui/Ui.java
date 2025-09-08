package cate.ui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cate.task.Task;
import cate.task.TaskList;

/**
 * Handles all user interface interactions for the Cate application.
 * The {@code Ui} class is responsible for displaying messages,
 * formatting output, and reading user input.
 */
public class Ui {

    public String printList(TaskList tasks) {
        List<Task> list = tasks.getList();
        String tasksStr = IntStream.range(0, list.size())
                .mapToObj(i -> String.format("    %d. %s", i + 1, list.get(i)))
                .collect(Collectors.joining("\n"));
        return "Here are the tasks in your list:\n" + tasksStr;
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
