package richal.task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    /** Returns the number of tasks in the list. */
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

    /** Returns all tasks as a list. */
    public List<Task> getAllTasks() {
        return Arrays.stream(tasks, 0, size)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if an identical task already exists in the list.
     * Two tasks are considered identical if they are of the same type,
     * have the same description, and the same date/time fields (if any).
     *
     * @param task the task to check for duplicates
     */
    public boolean hasDuplicate(Task task) {
        assert task != null : "Task to check should not be null";
        return Arrays.stream(tasks, 0, size)
                .anyMatch(t -> t.equals(task));
    }

    /**
     * Returns tasks whose description contains the keyword (case-insensitive).
     *
     * @param keyword the search keyword
     */
    public List<Task> findTasks(String keyword) {
        assert keyword != null : "Find keyword should not be null";
        String lowerKeyword = keyword.toLowerCase();
        return Arrays.stream(tasks, 0, size)
                .filter(t -> t.getDescription().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
}
