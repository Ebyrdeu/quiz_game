package ui;

import java.util.Scanner;

public class AppMenu implements Menuable {


    @Override
    public void start(Scanner scanner) {
        boolean running = true;
        CategoryMenu categoryMenu = new CategoryMenu();
        DifficultyMenu difficultyMenu = new DifficultyMenu();
        QuizMenu quizMeny = new QuizMenu();
        ResultMenu resultMenu = new ResultMenu();
        StudentMenu studentMenu = new StudentMenu();


        while (running) {
            System.out.println("\\n--- Quiz Program ---");
            System.out.println("1. Start quiz!");
            System.out.println("2. Show category options");
            System.out.println("3. Show difficulty options");
            System.out.println("4. Show quiz options");
            System.out.println("5. Show result options");
            System.out.println("6. Show student options");
            System.out.println("7. Exit");

            System.out.println("Choose an option");
            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1 -> quizStart(scanner);
                case 2 -> categoryMenu.start(scanner);
                case 3 -> difficultyMenu.start(scanner);
                case 4 -> quizMeny.start(scanner);
                case 5 -> resultMenu.start(scanner);
                case 6 -> studentMenu.start(scanner);
                case 7 -> {
                    running = false;
                    System.out.println("Exiting");
                }
                default -> System.out.println("Not a valid option, try again");

            }


        }


    }

    private void quizStart(Scanner scanner) {
        System.out.println("Select user Id ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select difficulty for your quiz");
        System.out.println("1 = Easy");
        System.out.println("2 = Medium");
        System.out.println("3 = Hard");
        int selectedDifficulty = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select Category for your quiz");
        System.out.println("1  = Science");
        System.out.println("2  = History");
        System.out.println("3  = Geography");
        int selectedCategory = scanner.nextInt();
        scanner.nextLine();


    }
}
