package richal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Todo class.
 */
public class TodoTest {

    @Test
    public void constructor_validDescription_success() {
        Todo todo = new Todo("read book");
        assertEquals("read book", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void toDisplayString_notDone_correctFormat() {
        Todo todo = new Todo("buy groceries");
        assertEquals("[T][ ]buy groceries", todo.toDisplayString());
    }

    @Test
    public void toDisplayString_done_correctFormat() {
        Todo todo = new Todo("buy groceries");
        todo.markDone();
        assertEquals("[T][X]buy groceries", todo.toDisplayString());
    }

    @Test
    public void markDone_changesStatus() {
        Todo todo = new Todo("exercise");
        todo.markDone();
        assertTrue(todo.isDone());
        assertEquals("[T][X]exercise", todo.toDisplayString());
    }

    @Test
    public void markUndone_afterMarkDone_changesStatus() {
        Todo todo = new Todo("study");
        todo.markDone();
        todo.markUndone();
        assertFalse(todo.isDone());
        assertEquals("[T][ ]study", todo.toDisplayString());
    }
}
