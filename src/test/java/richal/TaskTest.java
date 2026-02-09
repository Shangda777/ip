package richal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import richal.task.Task;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Task class.
 */
public class TaskTest {

    @Test
    public void constructor_validDescription_success() {
        Task task = new Task("read book");
        assertEquals("read book", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    public void markDone_initiallyNotDone_marksAsDone() {
        Task task = new Task("read book");
        task.markDone();
        assertTrue(task.isDone());
    }

    @Test
    public void markUndone_initiallyDone_marksAsNotDone() {
        Task task = new Task("read book");
        task.markDone();
        task.markUndone();
        assertFalse(task.isDone());
    }

    @Test
    public void toDisplayString_notDone_correctFormat() {
        Task task = new Task("read book");
        assertEquals("[ ]read book", task.toDisplayString());
    }

    @Test
    public void toDisplayString_done_correctFormat() {
        Task task = new Task("read book");
        task.markDone();
        assertEquals("[X]read book", task.toDisplayString());
    }

    @Test
    public void getDescription_returnsCorrectDescription() {
        Task task = new Task("buy groceries");
        assertEquals("buy groceries", task.getDescription());
    }

    @Test
    public void isDone_initialState_returnsFalse() {
        Task task = new Task("exercise");
        assertFalse(task.isDone());
    }

    @Test
    public void markDone_multipleTimes_remainsDone() {
        Task task = new Task("study");
        task.markDone();
        task.markDone();
        assertTrue(task.isDone());
    }

    @Test
    public void markUndone_multipleTimes_remainsNotDone() {
        Task task = new Task("study");
        task.markUndone();
        task.markUndone();
        assertFalse(task.isDone());
    }
}
