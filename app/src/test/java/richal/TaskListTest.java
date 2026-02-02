package richal;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the TaskList class.
 */
public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(100);
    }

    @Test
    public void constructor_withCapacity_createsEmptyList() {
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void constructor_withList_copiesAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("task1"));
        tasks.add(new Todo("task2"));
        tasks.add(new Todo("task3"));
        
        TaskList newList = new TaskList(tasks);
        assertEquals(3, newList.getSize());
        assertEquals("task1", newList.getTask(0).getDescription());
        assertEquals("task2", newList.getTask(1).getDescription());
        assertEquals("task3", newList.getTask(2).getDescription());
    }

    @Test
    public void addTask_singleTask_increasesSize() {
        Task task = new Todo("read book");
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void addTask_multipleTasks_correctSize() {
        taskList.addTask(new Todo("task1"));
        taskList.addTask(new Todo("task2"));
        taskList.addTask(new Todo("task3"));
        assertEquals(3, taskList.getSize());
    }

    @Test
    public void getTask_validIndex_returnsCorrectTask() {
        Task task1 = new Todo("buy groceries");
        Task task2 = new Todo("read book");
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    public void deleteTask_middleTask_shiftsRemainingTasks() {
        taskList.addTask(new Todo("task1"));
        taskList.addTask(new Todo("task2"));
        taskList.addTask(new Todo("task3"));
        
        taskList.deleteTask(1);
        
        assertEquals(2, taskList.getSize());
        assertEquals("task1", taskList.getTask(0).getDescription());
        assertEquals("task3", taskList.getTask(1).getDescription());
    }

    @Test
    public void deleteTask_firstTask_correctSize() {
        taskList.addTask(new Todo("task1"));
        taskList.addTask(new Todo("task2"));
        
        taskList.deleteTask(0);
        
        assertEquals(1, taskList.getSize());
        assertEquals("task2", taskList.getTask(0).getDescription());
    }

    @Test
    public void deleteTask_lastTask_correctSize() {
        taskList.addTask(new Todo("task1"));
        taskList.addTask(new Todo("task2"));
        
        taskList.deleteTask(1);
        
        assertEquals(1, taskList.getSize());
        assertEquals("task1", taskList.getTask(0).getDescription());
    }

    @Test
    public void getAllTasks_emptyList_returnsEmptyList() {
        List<Task> tasks = taskList.getAllTasks();
        assertEquals(0, tasks.size());
    }

    @Test
    public void getAllTasks_withTasks_returnsAllTasks() {
        taskList.addTask(new Todo("task1"));
        taskList.addTask(new Todo("task2"));
        taskList.addTask(new Todo("task3"));
        
        List<Task> tasks = taskList.getAllTasks();
        
        assertEquals(3, tasks.size());
        assertEquals("task1", tasks.get(0).getDescription());
        assertEquals("task2", tasks.get(1).getDescription());
        assertEquals("task3", tasks.get(2).getDescription());
    }

    @Test
    public void getSize_emptyList_returnsZero() {
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void getSize_afterAddingAndDeleting_correctSize() {
        taskList.addTask(new Todo("task1"));
        taskList.addTask(new Todo("task2"));
        taskList.addTask(new Todo("task3"));
        taskList.deleteTask(1);
        assertEquals(2, taskList.getSize());
    }
}
