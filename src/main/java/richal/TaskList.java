package richal;

public class TaskList {
    private Task[] tasks;
    private int size;

    public TaskList(int size) {
        this.tasks = new Task[size];
        this.size = 0;
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
}
