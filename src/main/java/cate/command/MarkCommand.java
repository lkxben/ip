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
        return ui.markTask(tasks.get(index));
    }
}
