package richal;

import java.util.Scanner;

/**
 * Richal is a simple chatbot program
 * It receives user input and echoes it back until the user types "bye" to exit
 */
public class Richal {
    /**
     * Main entry point of the program
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        // Use try-with-resources to automatically manage Scanner resource
        try (Scanner sc = new Scanner(System.in)) {
            // Initialize tasks array and size
            Task[] tasks = new Task[100];
            int size = 0;

            // Print welcome message and separator line
            System.out.println("--------------------------------");
            System.out.println("Hello! I'm Richal");
            System.out.println("What can I do for you?");
            System.out.println("--------------------------------");

            // Enter main loop to continuously receive user input
            while (true) {
                // Read a line of text from user input
                String input = sc.nextLine();

                // Check if user typed "bye" to exit the program
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break; // Exit the loop
                }
                // Check if user typed "list" to list all tasks
                if (input.equals("list")) {
                    System.out.println("--------------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < size; i++) {
                        System.out.println((i + 1) + ". " + tasks[i].toDisplayString());
                    }
                    System.out.println("--------------------------------");
                }
                // Check if user typed "mark <number>" to mark a task as done
                else if (input.startsWith("mark ")) {
                    System.out.println("--------------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    int number = Integer.parseInt(input.substring(5).trim());
                    tasks[number - 1].markDone();
                    System.out.println(tasks[number - 1].toDisplayString());
                    System.out.println("--------------------------------");
                }
                // Check if user typed "unmark <number>" to mark a task as not done
                else if (input.startsWith("unmark ")) {
                    System.out.println("--------------------------------");
                    System.out.println("OK, I've marked this task as not done yet:");
                    int number = Integer.parseInt(input.substring(7).trim());
                    tasks[number - 1].markUndone();
                    System.out.println(tasks[number - 1].toDisplayString());
                    System.out.println("--------------------------------");
                }
                // If user typed anything else, add it to the tasks array
                else {
                    System.out.println("--------------------------------");
                    if (input.startsWith("todo ")) {
                        tasks[size++] = new Todo(input.substring(5).trim());
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[size - 1].toDisplayString());
                    } else if (input.startsWith("deadline ")) {
                        String[] parts = input.substring(8).trim().split("/by");
                        tasks[size++] = new Deadline(parts[0].trim(), parts[1].trim());
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[size - 1].toDisplayString());
                    } else if (input.startsWith("event ")) {
                        String[] parts = input.substring(6).trim().split("/from");
                        String[] toParts = parts[1].trim().split("/to");
                        tasks[size++] = new Event(parts[0].trim(), toParts[0].trim(), toParts[1].trim());
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[size - 1].toDisplayString());
                    }
                    System.out.println("Now you have " + size + " tasks in the list.");
                    System.out.println("--------------------------------");
                }
            }

            // Print ending separator line
            System.out.println("--------------------------------");
        }
        // Scanner resource will be automatically closed when try block ends
    }
}

