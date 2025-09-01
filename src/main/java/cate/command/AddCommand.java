package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.util.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        tasks.addTask(task);
        storage.saveTask(task);
        return "ADDED";
    }
}
