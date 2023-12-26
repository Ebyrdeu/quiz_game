package ui;

import dao.quiz.QuizDaoimpl;
import dao.result.ResultDaoImpl;
import entity.Quiz;
import entity.Result;
import entity.Student;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AppMenu implements Menuable {
    private final QuizDaoimpl quizDao = new QuizDaoimpl();
    private final ResultDaoImpl resultDao = new ResultDaoImpl();

    private void printTableHeader() {
        System.out.printf("| %-3s | %-30s | %-5s |%n",
                "ID",
                "Student Name",
                "Score"
        );
    }

    private void printTableContent(Result entity) {
        System.out.printf("| %-10s | %-13s | %-5s |%n",
                entity.id(),
                entity.student().name(),
                entity.score()
        );
    }

    private void showLeaderboard() {
        printTableHeader();
        resultDao.readAllLeaderBoard().forEach(this::printTableContent);
    }

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
        var quiz = findQuiz();
        int userScore = 0;

        System.out.println(quiz.getFirst().getQuizQuestion());
        var quiz1 = scanner.nextLine().toLowerCase();

        System.out.println(quiz.get(1).getQuizQuestion());
        var quiz2 = scanner.nextLine().toLowerCase();

        System.out.println(quiz.get(2).getQuizQuestion());
        var quiz3 = scanner.nextLine().toLowerCase();

        System.out.println(quiz.get(3).getQuizQuestion());
        var quiz4 = scanner.nextLine().toLowerCase();

        if (quiz1.equals(quiz.getFirst().getCorrectAnswer().toLowerCase())) userScore += 25;
        if (quiz2.equals(quiz.get(1).getCorrectAnswer().toLowerCase())) userScore += 25;
        if (quiz3.equals(quiz.get(2).getCorrectAnswer().toLowerCase())) userScore += 25;
        if (quiz4.equals(quiz.get(3).getCorrectAnswer().toLowerCase())) userScore += 25;

        saveResultToDb(scanner, userScore);

        showLeaderboard();
    }

    private void saveResultToDb(Scanner scanner, int score) {
        System.out.println("Enter your ID");
        int userId = scanner.nextInt();
        scanner.nextLine();

        Result result = new Result();

        result.setStudent(new Student().setId(userId)).setScore(score);

        resultDao.create(result);
    }


    private List<Quiz> findQuiz() {
        List<Quiz> quizzes = quizDao.readAll();

        Collections.shuffle(quizzes);

        return quizzes;
    }
}
