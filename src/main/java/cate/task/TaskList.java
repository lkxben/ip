package cate.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private static final String line = "    _______________________________________\n";

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        System.out.println(line + "    Got it. I've added this task:\n      " + task.toString());
        list.add(task);
        System.out.println("    Now you have " + list.size() + " tasks in the list.\n" + line);
    }

    public void deleteTask(int i) {
        Task t = list.get(i - 1);
        System.out.print(line);
        System.out.println(String.format("    Noted. I've removed this task:\n      %s", t));
        list.remove(t);
        System.out.println("    Now you have " + list.size() + " tasks in the list.\n" + line);
    }

    public void markTask(int i) {
        Task t = list.get(i - 1);
        t.markDone();
        System.out.print(line);
        System.out.println(String.format("    Nice! I've marked this task as done:\n      %s", t));
        System.out.println(line);
    }

    public void unmarkTask(int i) {
        Task t = list.get(i - 1);
        t.markUndone();
        System.out.print(line);
        System.out.println(String.format("    OK, I've marked this task as not done yet:\n      %s", t));
        System.out.println(line);
    }

    public void printList() {
        System.out.print(line);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) ==  null) break;
            System.out.println(String.format("    %d. %s", i + 1, list.get(i)));
        }
        System.out.println(line);
    }
}
