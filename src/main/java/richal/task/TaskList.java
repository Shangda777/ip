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
        this.tasks = new Task[capacity];
        this.size = 0;
    }

    public TaskList(List<Task> taskList) {
        this.tasks = new Task[100];
        this.size = 0;
        for (Task task : taskList) {
            this.tasks[size++] = task;
        }
    }

    public void addTask(Task task) {
        tasks[size++] = task;
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public void deleteTask(int index) {
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
