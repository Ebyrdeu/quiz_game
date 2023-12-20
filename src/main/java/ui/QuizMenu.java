package ui;

//ToDo användaren ska välja svårighetsgrad
//ToDo användaren ska välja kategori
//ToDo quiz frågor och rätta/fel svar
//ToDo skapa en meny för quiz
//ToDo en return till den ursprungliga menyn för att kunna se resultat osv

import java.util.Scanner;

public class QuizMenu implements Menuable {


    private static void printMainMenu() {
        System.out.println("===== Quiz Menu =====");
        System.out.println("1. Start Quiz");
        System.out.println("2. Choose Difficulty");
        System.out.println("3. Pick a Category");
        System.out.println("4. Return");
        System.out.println("=====================");
    }

    private static void startQuiz() {
        // Implement the logic for starting a quiz
        System.out.println("Starting Quiz)");
        // You can call methods or perform actions related to quiz functionality here
    }

    private static void chooseDifficulty() {

        System.out.println("Difficulty");

        // metod för svåighetsgrad, anropa?

    }

    private static void pickCategory() {

        System.out.println("Picking Category... (Not implemented yet)");
        // kategori anropa?
    }

    private static void answerQuizQuestions() {

        System.out.println("Answering Quiz Questions... (Not implemented yet)");
        //ToDo skapa metod/logik för att svara på quiz frågor
    }

    private static void viewResults() {

        System.out.println("Viewing Results");
        // Oklart om resultat ska vara med här

    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Enter your choice: ");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    @Override
    public void start(Scanner scanner) {
        while (true) {
            printMainMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1 -> startQuiz();
                case 2 -> chooseDifficulty();
                case 3 -> pickCategory();
                case 4 -> {
                    System.out.println("Exiting the program. Goodbye!"); //skapa return till main meny
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

