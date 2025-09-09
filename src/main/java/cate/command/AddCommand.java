package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        assert task != null;
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        storage.saveTask(task);
        return ui.addTask(task);
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public String undo(Storage storage, TaskList tasks, Ui ui) {
        tasks.deleteTask(tasks.getIndexOfTask(task));
        storage.save(tasks);
        return ui.undoAddTask(task);
    }
}
