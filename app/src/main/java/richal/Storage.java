package richal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading and saving tasks from/to a file.
 * Uses relative path from project root (e.g., ./data/richal.txt).
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath the relative path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * Creates the file and directory if they don't exist.
     *
     * @return a list of tasks loaded from the file
     * @throws DukeException if there is an error loading the file
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // If file doesn't exist, create directory and return empty list
        if (!file.exists()) {
            try {
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating data file: " + e.getMessage());
            }
            return tasks;
        }

        // Read tasks from file
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a line from the file into a Task object.
     * Format: TYPE | DONE | DESCRIPTION [| EXTRA_INFO]
     *
     * @param line the line to parse
     * @return the parsed Task, or null if the format is invalid
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null;
            }

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;
            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(description, parts[3]);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    task = new Event(description, parts[3], parts[4]);
                }
                break;
            default:
                return null;
            }

            if (task != null && isDone) {
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks the list of tasks to save
     * @throws DukeException if there is an error saving to the file
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(formatTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Formats a task into a string for saving to file.
     * Format: TYPE | DONE | DESCRIPTION [| EXTRA_INFO]
     *
     * @param task the task to format
     * @return the formatted string
     */
    private String formatTask(Task task) {
        String type;
        String extraInfo = "";

        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
            extraInfo = " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            type = "E";
            Event event = (Event) task;
            extraInfo = " | " + event.getFrom() + " | " + event.getTo();
        } else {
            type = "T";
        }

        String done = task.isDone() ? "1" : "0";
        return type + " | " + done + " | " + task.getDescription() + extraInfo;
    }
}
