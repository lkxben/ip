package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.CateException;
import cate.util.Storage;

public class UndoCommand extends Command {
    private final CommandManager manager;

    public UndoCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws CateException {
        if (!manager.hasUndoableCommand()) {
            throw new CateException("Nothing to undo!");
        }
        Command last = manager.popLastCommand();
        return last.undo(storage, tasks, ui);
    }
}
