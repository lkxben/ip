package cate.ui;

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
     * Prints a horizontal line for separating sections of output.
     */
    public void showLine() {
        System.out.print(line);
    }

    /**
     * Displays a message to the user in a formatted box.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(line + "    " + message + "\n" + line);
    }

    /**
     * Displays the farewell message when the program ends.
     */
    public void showBye() {
        System.out.print(line + "    Bye. Hope to see you again soon!\n" + line);
    }

    /**
     * Reads a command from the user input.
     *
     * @param scanner The {@link Scanner} object used to read input.
     * @return The line of text entered by the user.
     */
    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }
}
