package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public class ListCommand extends Command {

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.printList(tasks);
    }
}
