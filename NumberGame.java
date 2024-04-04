package CODSOFT_JAVA_01;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Game!");
        System.out.print("Please enter your name: ");
        String playerName = scanner.nextLine();

        System.out.println("Hello, " + playerName + "! Let's get started.");

        int[] difficultyRanges = {50, 100, 150}; // Adjust the ranges as per difficulty
        int[] maxAttempts = {8, 6, 4}; // Adjust the attempts as per difficulty
        String[] difficultyNames = {"Easy", "Medium", "Hard"};

        List<Integer> scores = new ArrayList<>();

        boolean playAgain = true;

        while (playAgain) {
            System.out.println("\nChoose Difficulty Level:");
            for (int i = 0; i < difficultyNames.length; i++) {
                System.out.println((i + 1) + ". " + difficultyNames[i]);
            }
            System.out.print("Enter your choice (1-" + difficultyNames.length + "): ");

            int choice = getChoice(scanner, 1, difficultyNames.length);
            int range = difficultyRanges[choice - 1];
            int attempts = maxAttempts[choice - 1];
            System.out.println("You've chosen " + difficultyNames[choice - 1] + " level.");

            int targetNumber = random.nextInt(range) + 1;
            System.out.println("\nI've selected a number between 1 and " + range + ". You have " + attempts + " attempts to guess it.");

            int attemptCount = 0;

            while (attemptCount < attempts) {
                System.out.print("Attempt " + (attemptCount + 1) + ": Enter your guess (" + (attempts - attemptCount) + " attempt(s) left): ");

                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Please enter a number: ");
                    scanner.next(); // Consume invalid input
                }

                int guess = scanner.nextInt();
                attemptCount++;

                if (guess == targetNumber) {
                    System.out.println("Congratulations, " + playerName + "! You've guessed the correct number in " + attemptCount + " attempt(s).");
                    scores.add(attemptCount);
                    break;
                } else {
                    String hint = (guess < targetNumber) ? "Too low!" : "Too high!";
                    double difference = Math.abs(targetNumber - guess) / (double) range;
                    if (difference <= 0.1) {
                        System.out.println(hint + " You're very close!");
                    } else if (difference <= 0.3) {
                        System.out.println(hint + " You're getting closer.");
                    } else {
                        System.out.println(hint);
                    }
                }
            }

            if (attemptCount == attempts) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + targetNumber);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        // Display leaderboard
        if (!scores.isEmpty()) {
            Collections.sort(scores);
            System.out.println("\nTop Scores:");
            for (int i = 0; i < Math.min(scores.size(), 5); i++) {
                System.out.println((i + 1) + ". " + scores.get(i));
            }
        }

        System.out.println("\nThank you for playing! Goodbye.");
        scanner.close();
    }

    private static int getChoice(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // Consume invalid input
            }
            choice = scanner.nextInt();
            if (choice < min || choice > max) {
                System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
            } else {
                break;
            }
        }
        return choice;
    }
}
