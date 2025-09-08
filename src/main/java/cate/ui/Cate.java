package cate.ui;

import cate.command.Command;
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
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private boolean isExit = false;

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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            output = c.execute(storage, tasks, ui);
            isExit = c.isExit();
        } catch (CateException e) {
            return e.getMessage();
        }
        return output;
    }

    public boolean isExit() {
        return isExit;
    }

    public static void main(String[] args) {

    }
}
