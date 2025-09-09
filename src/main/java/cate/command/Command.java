package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.CateException;
import cate.util.Storage;

public abstract class Command {

    public abstract String execute(Storage storage, TaskList tasks, Ui ui) throws CateException;

    public boolean isExit() {
        return false;
    }

    public boolean canUndo() {
        return false;
    }

    public String undo(Storage storage, TaskList tasks, Ui ui) throws CateException {
        throw new CateException("This command cannot be undone.");
    }
}
