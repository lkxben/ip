package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public class DeleteCommand extends Command {
    private int index;
    private Task deleted;

    public DeleteCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task t = tasks.getTask(index);
        deleted = t;
        tasks.deleteTask(index);
        storage.save(tasks);
        return ui.deleteTask(t, tasks.size());
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public String undo(Storage storage, TaskList tasks, Ui ui) {
        tasks.insertTask(index, deleted);
        storage.save(tasks);
        return ui.undoDeleteTask(deleted);
    }
}
