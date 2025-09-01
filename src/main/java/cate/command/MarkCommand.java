package cate.command;

import cate.task.TaskList;
import cate.util.Storage;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        tasks.markTask(index);
//        storage.save(tasks);
        return "MARKED";
    }
}
