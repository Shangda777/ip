package richal;

import java.util.Scanner;

/**
 * Richal is a simple chatbot program It receives user input and echoes it back
 * until the user types "bye" to exit
 */
public class Richal {

    /**
     * Main entry point of the program
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        // Use try-with-resources to automatically manage Scanner resource
        Scanner sc = new Scanner(System.in);
        // Initialize tasks array and size
        TaskList taskList = new TaskList(100);
        int size = 0;

        // Print welcome message and separator line
        System.out.println("--------------------------------");
        System.out.println("Hello! I'm Richal");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------");

        // Enter main loop to continuously receive user input
        while (true) {
            // Read a line of text from user input
            String input = sc.nextLine().trim();

            try {
                // Check if user typed "bye" to exit the program
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break; // Exit the loop
                }
                // Process the input
                processInput(input, taskList);
                System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
            } catch (DukeException e) {
                // Print the error message
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (Exception e) {
                // Print the error message
                System.out.println("OOPS!!! Something went wrong: " + e.getMessage());
            } finally {
                System.out.println("--------------------------------");
                
            }
        }
        // Close the scanner
        sc.close();
    }

    /**
     * Processes the user input and performs the corresponding action.
     *
     * @param input the user input
     * @param taskList the task list
     * @throws DukeException if the input is invalid
     */
    private static void processInput(String input, TaskList taskList) throws DukeException {
        // Check if user typed "list" to list all tasks
        if (input.equals("list")) {
            System.out.println("--------------------------------");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + ". " + taskList.getTask(i).toDisplayString());
            }
            System.out.println("--------------------------------");
        } 
        // Check if user typed "mark <number>" to mark a task as done
        else if (input.startsWith("mark ")) {
            System.out.println("--------------------------------");
            int number = parseIndex(input.substring(5).trim(), taskList.getSize());
            taskList.getTask(number).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.getTask(number).toDisplayString());
            System.out.println("--------------------------------");
        } 
        // Check if user typed "unmark <number>" to mark a task as not done
        else if (input.startsWith("unmark ")) {
            System.out.println("--------------------------------");
            int number = parseIndex(input.substring(7).trim(), taskList.getSize());
            taskList.getTask(number).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.getTask(number).toDisplayString());
            System.out.println("--------------------------------");
        } 
        // Check if user typed "delete <number>" to delete a task
        else if (input.startsWith("delete ")) {
            System.out.println("--------------------------------");
            int number = parseIndex(input.substring(7).trim(), taskList.getSize());
            taskList.deleteTask(number);
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskList.getTask(number).toDisplayString());
            System.out.println("--------------------------------");
        }
        // If user typed anything else, add it to the tasks array
        else {
            System.out.println("--------------------------------");
           
            // Check if user typed "todo <description>" to add a todo task
            if (input.startsWith("todo")) {
                String desc = input.length() > 4 ? input.substring(5).trim() : "";
                if (desc.isEmpty()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                taskList.addTask(new Todo(desc));
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList.getTask(taskList.getSize() - 1).toDisplayString());
               
                // Check if user typed "deadline <description> /by <date/time>" to add a deadline task
            } else if (input.startsWith("deadline")) {
                String[] parts = input.length() > 8 ? input.substring(8).trim().split("/by") : new String[0];
                if (parts.length != 2) {
                    throw new DukeException("The description of a deadline must contain a /by date/time.");
                }
                taskList.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList.getTask(taskList.getSize() - 1).toDisplayString());
               
                // Check if user typed "event <description> /from <date/time> /to <date/time>" to add an event task
            } else if (input.startsWith("event")) {
                String[] parts = input.length() > 6 ? input.substring(6).trim().split("/from") : new String[0];
                if (parts.length != 2) {
                    throw new DukeException("The description of an event must contain a /from date/time.");
                }
                String[] toParts = parts[1].length() > 3 ? parts[1].trim().split("/to") : new String[0];
                if (toParts.length != 2) {
                    throw new DukeException("The description of an event must contain a /to date/time.");
                }
                taskList.addTask(new Event(parts[0].trim(), toParts[0].trim(), toParts[1].trim()));
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList.getTask(taskList.getSize() - 1).toDisplayString());
                
                // If user typed anything else, throw an exception
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
    /**
     * Parses a string to an integer and checks if it is a valid index.
     *
     * @param s the string to parse
     * @param size the size of the list
     * @return the index
     * @throws DukeException if the string is not a valid integer or the index is out of range
     */
    private static int parseIndex(String s, int size) throws DukeException {
        try {
            int number = Integer.parseInt(s);
            int idx = number - 1;
            if (idx < 0 || idx >= size) {
                throw new DukeException("That task number is out of range.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number.");
        }
    }
}

