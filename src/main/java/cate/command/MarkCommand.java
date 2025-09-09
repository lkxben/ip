package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.markTask(index);
        storage.save(tasks);
        return ui.markTask(tasks.getTask(index));
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public String undo(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmarkTask(index);
        storage.save(tasks);
        return ui.unmarkTask(tasks.getTask(index));
    }
}
