package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

