import java.util.Scanner;

/**
 * Richal is a simple chatbot program
 * It receives user input and echoes it back until the user types "bye" to exit
 */
public class Richal {
    /**
     * Main entry point of the program
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        // Use try-with-resources to automatically manage Scanner resource
        try (Scanner sc = new Scanner(System.in)) {
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
                
                // Echo the user's input
                System.out.println(input);
            }
            
            // Print ending separator line
            System.out.println("--------------------------------");
        }
        // Scanner resource will be automatically closed when try block ends
    }
}
