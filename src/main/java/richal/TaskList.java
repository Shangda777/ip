package richal;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private Task[] tasks;
    private int size;

    /**
     * Creates an empty task list with the specified capacity.
     *
     * @param capacity the maximum number of tasks
     */
    public TaskList(int capacity) {
        this.tasks = new Task[capacity];
        this.size = 0;
    }

    /**
     * Creates a task list from a list of tasks.
     *
     * @param taskList the list of tasks to initialize with
     */
    public TaskList(List<Task> taskList) {
        this.tasks = new Task[100];
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
        tasks[size++] = task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task
     * @return the task at the index
     */
    public Task getTask(int index) {
        return tasks[index];
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete
     */
    public void deleteTask(int index) {
        for (int i = index; i < size - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        size--;
    }

    /**
     * Returns all tasks as a list.
     *
     * @return a list containing all tasks
     */
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            taskList.add(tasks[i]);
        }
        return taskList;
    }

    /**
     * Finds tasks whose description contains the given keyword (case-insensitive).
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks
     */
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
