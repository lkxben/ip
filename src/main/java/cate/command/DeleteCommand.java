package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.deleteTask(index);
        storage.save(tasks);
        return ui.deleteTask(tasks.get(index), tasks.size());
    }
}
