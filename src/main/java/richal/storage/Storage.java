package richal.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import richal.DukeException;
import richal.task.Deadline;
import richal.task.Event;
import richal.task.Task;
import richal.task.Todo;

/**
 * Handles loading and saving tasks from/to a file.
 */
public class Storage {
    private static final String SEPARATOR = " | ";

    private final String filePath;

    /**
     * Creates a Storage with the given file path.
     *
     * @param filePath path to the data file
     */
    public Storage(String filePath) {
        assert filePath != null : "Storage file path should not be null";
        assert !filePath.isBlank() : "Storage file path should not be blank";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file. Creates the file if it does not exist.
     *
     * @return list of loaded tasks
     * @throws DukeException if the file cannot be read or created
     */
    public List<Task> load() throws DukeException {
        File file = new File(filePath);

        if (!file.exists()) {
            createFile(file);
            return new ArrayList<>();
        }

        try (Scanner scanner = new Scanner(file)) {
            List<Task> tasks = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
    }

    private void createFile(File file) throws DukeException {
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error creating data file: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        assert line != null : "Storage line to parse should not be null";
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null;
            }

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = createTask(type, description, parts);
            if (task != null && isDone) {
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            return null;
        }
    }

    private Task createTask(String type, String description, String[] parts) {
        switch (type) {
            case "T":
                return new Todo(description);
            case "D":
                return parts.length >= 4 ? new Deadline(description, parts[3]) : null;
            case "E":
                return parts.length >= 5 ? new Event(description, parts[3], parts[4]) : null;
            default:
                return null;
        }
    }

    /**
     * Saves all tasks to the file.
     *
     * @param tasks the list of tasks to save
     * @throws DukeException if the file cannot be written
     */
    public void save(List<Task> tasks) throws DukeException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        String content = tasks.stream()
                .map(this::formatTask)
                .collect(Collectors.joining("\n"));

        try (FileWriter writer = new FileWriter(file)) {
            if (!content.isEmpty()) {
                writer.write(content + "\n");
            }
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }

    private String formatTask(Task task) {
        assert task != null : "Task to format should not be null";
        String type;
        String extraInfo = "";

        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
            extraInfo = SEPARATOR + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            type = "E";
            extraInfo = SEPARATOR + event.getFrom() + SEPARATOR + event.getTo();
        } else {
            type = "T";
        }

        String done = task.isDone() ? "1" : "0";
        return type + SEPARATOR + done + SEPARATOR + task.getDescription() + extraInfo;
    }
}
