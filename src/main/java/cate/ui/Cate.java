package cate.ui;

import java.util.Scanner;

import cate.task.TaskList;
import cate.util.Storage;
import cate.util.Parser;
import cate.util.CateException;

public class Cate {
    private static final String filePath = "src/main/data/cate.txt";
    private static final String line = "    _______________________________________\n";

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Cate(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = ui.readCommand(scanner);
            if (userInput.equals("bye")) break;
            try {
                Parser.parse(userInput, tasks, storage);
            } catch (CateException e) {
                ui.showMessage(e.getMessage());
            }
        }
        ui.showBye();
    }

    public static void main(String[] args) {
        new Cate(filePath).run();
    }
}
