package richal.task;

/**
 * Represents a todo task with no date or time.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDisplayString() {
        return "[T]" + super.toDisplayString();
    }
}
