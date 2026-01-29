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
}
