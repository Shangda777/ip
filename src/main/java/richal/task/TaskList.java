package richal.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private Task[] tasks;
    private int size;

    public TaskList(int capacity) {
        assert capacity > 0 : "TaskList capacity should be positive";
        this.tasks = new Task[capacity];
        this.size = 0;
    }

    public TaskList(List<Task> taskList) {
        assert taskList != null : "Initial task list should not be null";
        assert taskList.size() <= 100 : "Initial task list should fit in default capacity";
        this.tasks = new Task[100];
        this.size = 0;
        for (Task task : taskList) {
            this.tasks[size++] = task;
        }
    }

    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        assert size < tasks.length : "TaskList should have available capacity";
        tasks[size++] = task;
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) {
        assert index >= 0 && index < size : "Task index should be in range";
        assert tasks[index] != null : "Task at index should not be null";
        return tasks[index];
    }

    public void deleteTask(int index) {
        assert index >= 0 && index < size : "Delete index should be in range";
        for (int i = index; i < size - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        size--;
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            taskList.add(tasks[i]);
        }
        return taskList;
    }

    public List<Task> findTasks(String keyword) {
        assert keyword != null : "Find keyword should not be null";
        List<Task> matching = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (int i = 0; i < size; i++) {
            if (tasks[i].getDescription().toLowerCase().contains(lowerKeyword)) {
                matching.add(tasks[i]);
            }
        }
        return matching;
    }
}
