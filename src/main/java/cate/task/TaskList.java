package cate.task;

import java.util.ArrayList;

/**
 * Represents a collection of {@link Task} objects.
 * A {@code TaskList} supports adding, deleting, marking, unmarking,
 * and printing tasks, while also providing access to the underlying list.
 */
public class TaskList {
    private static final String line = "    _______________________________________\n";
    private ArrayList<Task> list;

    /**
     * Constructs a {@code TaskList} with an existing list of tasks.
     *
     * @param list The list of tasks to initialize this {@code TaskList} with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns a copy of the internal list of tasks.
     * Modifications to the returned list will not affect the {@code TaskList}.
     *
     * @return A new {@link ArrayList} containing all tasks in this list.
     */
    public ArrayList<Task> getList() {
        return new ArrayList<>(list);
    }

    /**
     * Adds a task to the list and prints a confirmation message,
     * including the number of tasks in the list after addition.
     *
     * @param task The {@link Task} to be added.
     */
    public void addTask(Task task) {
        System.out.println(line + "    Got it. I've added this task:\n      " + task.toString());
        list.add(task);
        System.out.println("    Now you have " + list.size() + " tasks in the list.\n" + line);
    }

    /**
     * Deletes a task at the given index (1-based) from the list
     * and prints a confirmation message.
     *
     * @param i The 1-based index of the task to delete.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void deleteTask(int i) {
        Task t = list.get(i - 1);
        System.out.print(line);
        System.out.println(String.format("    Noted. I've removed this task:\n      %s", t));
        list.remove(t);
        System.out.println("    Now you have " + list.size() + " tasks in the list.\n" + line);
    }

    /**
     * Marks the task at the given index (1-based) as done
     * and prints a confirmation message.
     *
     * @param i The 1-based index of the task to mark as done.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void markTask(int i) {
        Task t = list.get(i - 1);
        t.markDone();
        System.out.print(line);
        System.out.println(String.format("    Nice! I've marked this task as done:\n      %s", t));
        System.out.println(line);
    }

    /**
     * Marks the task at the given index (1-based) as not done
     * and prints a confirmation message.
     *
     * @param i The 1-based index of the task to unmark.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void unmarkTask(int i) {
        Task t = list.get(i - 1);
        t.markUndone();
        System.out.print(line);
        System.out.println(String.format("    OK, I've marked this task as not done yet:\n      %s", t));
        System.out.println(line);
    }

    /**
     * Searches the task list for tasks whose descriptions contain the given query
     * string and prints the matching tasks to the console.
     *
     * @param query the keyword to search for within task descriptions
     */
    public ArrayList<Task> findTasks(String query) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task t : list) {
            if (t.descriptionContains(query)) {
                output.add(t);
            }
        }
        return output;
    }

    /**
     * Prints all tasks in the list in order,
     * including their index and string representation.
     */
    public void printList() {
        System.out.print(line);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            }
            System.out.println(String.format("    %d. %s", i + 1, list.get(i)));
        }
        System.out.println(line);
    }
}
