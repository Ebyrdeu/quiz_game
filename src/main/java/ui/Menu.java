package ui;

import dao.result.ResultDaoImpl;
import entity.Result;
import entity.Student;

import java.util.Scanner;

public class Menu implements Menuable {

    private final ResultDaoImpl resultDao = new ResultDaoImpl();

    @Override
    public void start(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\\n--- Quiz Program ---");
            System.out.println("1. Start The Quiz");
            System.out.println("2. Show Results");
            System.out.println("3. Show Leaderboard");
            System.out.println("4. Update Student Email");
            System.out.println("5. Delete Result");
            System.out.println("6. Exit");

            System.out.println("Choose an option");
            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1 -> quizStart(scanner);
                case 2 -> showResults();
                case 3 -> showLeaderboard();
                case 4 -> updateStudentEmail(scanner);
                case 5 -> deleteResult(scanner);
                case 6 -> {
                    running = false;
                    System.out.println("Exiting");
                }
                default -> System.out.println("Not a valid option, try again");

            }


        }


    }

    private void quizStart(Scanner scanner) {
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

    private void showResults() {
        resultDao.readAll().forEach(result -> System.out.printf("Name: %s ---- Score: %d\n", result.student().name(), result.score()));
    }

    private void showLeaderboard() {
        resultDao.readAllLeaderBoard().forEach(result -> System.out.printf("Name: %s ---- Score: %d\n", result.student().name(), result.score()));
    }

    private void updateStudentEmail(Scanner scanner) {
        System.out.println("Select ");

        System.out.println("Select user Id");
        int userId = scanner.nextInt();

        System.out.println("write email you want to be changed to ");
        var email = scanner.nextLine();

        Student student = new Student();

    }

    private void deleteResult(Scanner scanner) {
        System.out.println("Select result Id");
        int resultId = scanner.nextInt();

        Result result = new Result();

        result.setId(resultId);

        resultDao.delete(result);
    }
}
