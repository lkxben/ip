package cate.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cate.command.AddCommand;
import cate.command.Command;
import cate.command.DeleteCommand;
import cate.command.ExitCommand;
import cate.command.FindCommand;
import cate.command.ListCommand;
import cate.command.MarkCommand;
import cate.command.UnmarkCommand;
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
     * Parses a raw user input string into a corresponding {@link Command}.
     *
     * @param input the raw user input
     * @return a {@link Command} instance representing the parsed input
     * @throws CateException if the input is invalid or cannot be parsed into a command
     */
    public static Command parse(String input) throws CateException {
        String[] tokens = input.trim().split(" ", 2);
        String command = tokens[0];

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseIndex(tokens));
            case "unmark":
                return new UnmarkCommand(parseIndex(tokens));
            case "delete":
                return new DeleteCommand(parseIndex(tokens));
            case "find":
                return new FindCommand(requireArgument(tokens, "find"));
            case "todo":
                return parseTodo(tokens);
            case "deadline":
                return parseDeadline(tokens);
            case "event":
                return parseEvent(tokens);
            default:
                throw new CateException("I'm sorry I don't understand!");
        }
    }

    /**
     * Extracts and parses a 1-based index from a token array.
     *
     * @param tokens the split user input tokens, where the second token should contain an integer index
     * @return a zero-based index corresponding to the parsed number
     * @throws CateException if the index is missing or cannot be parsed as an integer
     */
    private static int parseIndex(String[] tokens) throws CateException {
        if (tokens.length < 2) {
            throw new CateException("Index required.");
        }
        return Integer.parseInt(tokens[1]) - 1;
    }

    /**
     * Ensures that a command requiring arguments has a non-empty argument string.
     *
     * @param tokens the split user input tokens
     * @param cmd the name of the command (used in error messages)
     * @return the non-empty argument string
     * @throws CateException if the argument is missing or empty
     */
    private static String requireArgument(String[] tokens, String cmd) throws CateException {
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new CateException("The description of a " + cmd + " cannot be empty.");
        }
        return tokens[1].trim();
    }

    /**
     * Parses a {@code todo} command into an {@link AddCommand} with a {@link Todo}.
     *
     * @param tokens the split user input tokens
     * @return an {@link AddCommand} containing a {@link Todo}
     * @throws CateException if the description is missing or empty
     */
    private static Command parseTodo(String[] tokens) throws CateException {
        String description = requireArgument(tokens, "todo");
        return new AddCommand(new Todo(description));
    }

    /**
     * Parses a {@code deadline} command into an {@link AddCommand} with a {@link Deadline}.
     *
     * @param tokens the split user input tokens
     * @return an {@link AddCommand} containing a {@link Deadline}
     * @throws CateException if the description or deadline time is missing, or improperly formatted
     */
    private static Command parseDeadline(String[] tokens) throws CateException {
        String text = requireArgument(tokens, "deadline");
        String[] parts = text.split(" /by ", 2);
        if (parts.length < 2) {
            throw new CateException("Deadline requires /by.");
        }
        return new AddCommand(new Deadline(parts[0], LocalDateTime.parse(parts[1], formatter)));
    }

    /**
     * Parses an {@code event} command into an {@link AddCommand} with an {@link Event}.
     *
     * @param tokens the split user input tokens
     * @return an {@link AddCommand} containing an {@link Event}
     * @throws CateException if the description or event times are missing, or improperly formatted
     */
    private static Command parseEvent(String[] tokens) throws CateException {
        String text = requireArgument(tokens, "event");
        String[] parts = text.split(" /from ", 2);
        if (parts.length < 2) {
            throw new CateException("Event requires /from and /to.");
        }
        String[] time = parts[1].split(" /to ", 2);
        if (time.length < 2) {
            throw new CateException("Event requires both /from and /to.");
        }
        return new AddCommand(new Event(parts[0], time[0], time[1]));
    }
}
