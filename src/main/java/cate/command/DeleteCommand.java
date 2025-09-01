package cate.command;

import cate.task.TaskList;
import cate.util.Storage;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        tasks.deleteTask(index);
//        storage.save(tasks);
        return "DELETED";
    }
}
