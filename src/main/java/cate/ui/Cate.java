package cate.ui;

import java.util.Scanner;

import cate.task.TaskList;
import cate.util.CateException;
import cate.util.Parser;
import cate.util.Storage;

/**
 * The main entry point for the Cate application.
 * {@code Cate} manages the user interface, command parsing,
 * task storage, and task execution loop.
 */
public class Cate {
    private static final String filePath = "src/main/data/cate.txt";
    private static final String line = "    _______________________________________\n";

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a {@code Cate} instance with the given file path
     * for loading and saving tasks.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Cate(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main program loop for the Cate application.
     * Continuously reads user input, parses it into commands,
     * and executes them until the user enters {@code "bye"}.
     * Handles invalid commands and errors by showing messages via {@link Ui}.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = ui.readCommand(scanner);
            if (userInput.equals("bye")) {
                break;
            }
            try {
                Parser.parse(userInput, tasks, storage);
            } catch (CateException e) {
                ui.showMessage(e.getMessage());
            }
        }
        ui.showBye();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Cate heard: " + input;
    }

    /**
     * Launches the Cate application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Cate(filePath).run();
    }
}
