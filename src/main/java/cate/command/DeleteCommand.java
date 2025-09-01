package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task t = tasks.get(index);
        tasks.deleteTask(index);
        storage.save(tasks);
        return ui.deleteTask(t, tasks.size());
    }
}
