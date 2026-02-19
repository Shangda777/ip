package richal.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private static final int DEFAULT_CAPACITY = 100;

    private Task[] tasks;
    private int size;

    /**
     * Creates an empty task list with the specified capacity.
     *
     * @param capacity the maximum number of tasks
     */
    public TaskList(int capacity) {
        assert capacity > 0 : "TaskList capacity should be positive";
        this.tasks = new Task[capacity];
        this.size = 0;
    }

    /**
     * Creates a task list initialised from an existing list.
     *
     * @param taskList the list of tasks to copy
     */
    public TaskList(List<Task> taskList) {
        assert taskList != null : "Initial task list should not be null";
        assert taskList.size() <= DEFAULT_CAPACITY : "Initial task list should fit in default capacity";
        this.tasks = new Task[DEFAULT_CAPACITY];
        this.size = 0;
        for (Task task : taskList) {
            this.tasks[size++] = task;
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        assert size < tasks.length : "TaskList should have available capacity";
        tasks[size++] = task;
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the task at the given index.
     *
     * @param index 0-based index
     */
    public Task getTask(int index) {
        assert index >= 0 && index < size : "Task index should be in range";
        assert tasks[index] != null : "Task at index should not be null";
        return tasks[index];
    }

    /**
     * Deletes the task at the given index, shifting subsequent tasks left.
     *
     * @param index 0-based index of the task to delete
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < size : "Delete index should be in range";
        for (int i = index; i < size - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        size--;
    }

    /**
     * Returns all tasks as a list.
     */
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            taskList.add(tasks[i]);
        }
        return taskList;
    }

    /**
     * Returns tasks whose description contains the keyword (case-insensitive).
     *
     * @param keyword the search keyword
     */
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
