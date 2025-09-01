package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.util.Storage;

public class FindCommand extends Command {
    String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task t: tasks.findTasks(query)) {
            output.append(t.toString());
        }
        return output.toString();
    }
}
