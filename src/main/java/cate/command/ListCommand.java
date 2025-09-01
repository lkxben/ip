package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.util.Storage;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public String execute(Storage storage, TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        Task[] list;
        list = tasks.getList().toArray(new Task[0]);
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            output.append(String.format("    %d. %s", i + 1, list[i]));
        }
        return output.toString();
    }
}
