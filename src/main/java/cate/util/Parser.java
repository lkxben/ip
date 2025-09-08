package cate.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cate.command.*;
import cate.task.Deadline;
import cate.task.Event;
import cate.task.Task;
import cate.task.TaskList;
import cate.task.Todo;

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
     * @throws CateException If the command is invalid, malformed, or incomplete.
     */
    public static Command parse(String input) throws CateException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("todo")) {
            throw new CateException("The description of a todo cannot be empty.");
        } else if (input.startsWith("mark ")) {
            String number = input.split(" ")[1];
            int value = Integer.parseInt(number) - 1;
            assert value >= 0;
            return new MarkCommand(value);
        } else if (input.startsWith("unmark ")) {
            String number = input.split(" ")[1];
            int value = Integer.parseInt(number) - 1;
            assert value >= 0;
            return new UnmarkCommand(value);
        } else if (input.startsWith("delete ")) {
            String number = input.split(" ")[1];
            int value = Integer.parseInt(number) - 1;
            assert value >= 0;
            return new DeleteCommand(value);
        } else if (input.startsWith("find ")) {
            String query = input.split(" ", 2)[1];
            return new FindCommand(query);
        } else if (input.startsWith("todo ")) {
            String description = input.split(" ", 2)[1];
            Task newTask = new Todo(description);
            return new AddCommand(newTask);
        } else if (input.startsWith("deadline ")) {
            String text = input.split(" ", 2)[1];
            String[] parts = text.split(" /by ", 2);
            Task newTask = new Deadline(parts[0], LocalDateTime.parse(parts[1], formatter));
            return new AddCommand(newTask);
        } else if (input.startsWith("event ")) {
            String text = input.split(" ", 2)[1];
            String[] parts = text.split(" /from ", 2);
            String[] time = parts[1].split(" /to ", 2);
            Task newTask = new Event(parts[0], time[0], time[1]);
            return new AddCommand(newTask);
        } else {
            throw new CateException("I'm sorry I don't understand!");
        }
    }
}
