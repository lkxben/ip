package cate.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void taskList_addTask(){
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Task task = new Todo("new");
        ArrayList<Task> output = new ArrayList<>();
        output.add(task);
        tasks.addTask(task);
        assertEquals(tasks.getList(), output);
    }
}
