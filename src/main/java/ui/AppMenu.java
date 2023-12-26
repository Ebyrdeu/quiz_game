package ui;

import dao.quiz.QuizDaoimpl;
import dao.result.ResultDaoImpl;
import entity.Quiz;
import entity.Result;
import entity.Student;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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
        System.out.printf("| %-3s | %-30s | %-5s |%n",
                entity.id(),
                entity.student().name(),
                entity.score()
        );
    }

    private void showLeaderboard() {
        var result = resultDao.readAllLeaderBoard();
        printTableHeader();
        result.forEach(this::printTableContent);
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
        List<Quiz> quiz = findQuiz();
        var userScore = new AtomicInteger(0);

        quiz.forEach(q -> {
            System.out.println(q.getQuizQuestion());
            var answer = scanner.nextLine().toLowerCase();

            if (answer.equals(q.getCorrectAnswer().toLowerCase())) userScore.addAndGet(25);
        });

        saveResultToDb(scanner, userScore.get());

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

        return quizzes.subList(0, 4);
    }
}
