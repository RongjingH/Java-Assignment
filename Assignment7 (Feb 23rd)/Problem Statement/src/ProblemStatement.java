import java.util.InputMismatchException;
import java.util.Scanner;

public class ProblemStatement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numElements = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter the number of elements: ");
                numElements = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter an integer value.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        // Create an array of integers
        int[] array = new int[numElements];

        // Let the user enter integer values for the array
        for (int i = 0; i < numElements; i++) {
            validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Enter element " + (i + 1) + ": ");
                    array[i] = scanner.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter an integer value.");
                    scanner.nextLine();
                }
            }
        }

        // Calculate the sum
        int sum = 0;
        for (int value : array) {
            sum += value;
        }

        System.out.println("Sum of all elements: " + sum);
    }
}
