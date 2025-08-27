package cate.util;

import cate.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles parsing and execution of user commands in the Cate application.
 * The {@code Parser} interprets user input, updates the {@link TaskList},
 * and saves tasks through {@link Storage}.
 */
public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a user input command and executes the corresponding action.
     * Supported commands include:
     * <ul>
     *     <li>{@code list} – Shows all tasks.</li>
     *     <li>{@code todo <description>} – Adds a new {@link Todo}.</li>
     *     <li>{@code deadline <description> /by <yyyy-MM-dd HHmm>} – Adds a new {@link Deadline}.</li>
     *     <li>{@code event <description> /from <start> /to <end>} – Adds a new {@link Event}.</li>
     *     <li>{@code mark <index>} – Marks a task as done.</li>
     *     <li>{@code unmark <index>} – Marks a task as not done.</li>
     *     <li>{@code delete <index>} – Deletes a task.</li>
     * </ul>
     *
     * @param input   The raw user input string.
     * @param tasks   The {@link TaskList} that stores tasks.
     * @param storage The {@link Storage} used for saving tasks persistently.
     * @throws CateException If the command is invalid, malformed, or incomplete.
     */
    public static void parse(String input, TaskList tasks, Storage storage) throws CateException {
        if (input.equals("list")) {
            tasks.printList();
        } else if (input.equals("todo")) {
            throw new CateException("The description of a todo cannot be empty.");
        } else if (input.startsWith("mark ")) {
            String number = input.split(" ")[1];
            int value = Integer.parseInt(number);
            tasks.markTask(value);
        } else if (input.startsWith("unmark ")) {
            String number = input.split(" ")[1];
            int value = Integer.parseInt(number);
            tasks.unmarkTask(value);
        } else if (input.startsWith("delete ")) {
            String number = input.split(" ")[1];
            int value = Integer.parseInt(number);
            tasks.deleteTask(value);
        } else if (input.startsWith("todo ")) {
            String description = input.split(" ", 2)[1];
            Task newTask = new Todo(description);
            tasks.addTask(newTask);
            storage.saveTask(newTask);
        } else if (input.startsWith("deadline ")) {
            String text = input.split(" ", 2)[1];
            String[] parts = text.split(" /by ", 2);
            Task newTask = new Deadline(parts[0], LocalDateTime.parse(parts[1], formatter));
            tasks.addTask(newTask);
            storage.saveTask(newTask);
        } else if (input.startsWith("event ")) {
            String text = input.split(" ", 2)[1];
            String[] parts = text.split(" /from ", 2);
            String[] time = parts[1].split(" /to ", 2);
            Task newTask = new Event(parts[0], time[0], time[1]);
            tasks.addTask(newTask);
            storage.saveTask(newTask);
        } else {
            throw new CateException("I'm sorry I don't understand!");
        }
    }
}
