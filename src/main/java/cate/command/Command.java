package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

public abstract class Command {

    public abstract String execute(Storage storage, TaskList tasks, Ui ui);
}
