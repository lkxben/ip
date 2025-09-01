package cate.command;

import cate.task.TaskList;
import cate.util.Storage;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        tasks.unmarkTask(index);
//        storage.save(tasks);
        return "UNMARKED";
    }
}
