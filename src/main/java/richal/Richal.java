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
                else if (input.equals("list")) {
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
                    tasks[size++] = new Task(input);
                    System.out.println("added: " + input);
                    System.out.println("--------------------------------");
                }
            }

            // Print ending separator line
            System.out.println("--------------------------------");
        }
        // Scanner resource will be automatically closed when try block ends
    }
}

