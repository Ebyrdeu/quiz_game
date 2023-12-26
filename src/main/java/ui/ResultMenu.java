package ui;

import dao.result.ResultDaoImpl;
import entity.Result;
import entity.Student;

import java.util.Scanner;

public class ResultMenu implements Menuable {
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

    @Override
    public void start(Scanner scanner) {
        boolean cycle = true;

        do {
            System.out.println("""
                    1 - Create Result
                    2 - Read Result
                    3 - Read Results
                    4 - Update Result
                    5 - Delete Result
                                
                    6 - Show Leaderboard
                    7 - exit
                    """);

            switch (scanner.nextLine()) {
                case "1" -> createResult(scanner);
                case "2" -> showSingleResult(scanner);
                case "3" -> showResults();
                case "4" -> updateResult(scanner);
                case "5" -> deleteResult(scanner);
                case "6" -> showLeaderboard();
                case "E", "e", "7" -> cycle = false;
                default -> System.out.println("Wrong Key");
            }

        } while (cycle);
    }

    private void createResult(Scanner scanner) {
        System.out.println("Enter Student Id");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Result Score");
        int score = scanner.nextInt();
        scanner.nextLine();

        Result result = new Result();

        result.setStudent(new Student().setId(studentId))
                .setScore(score);

        resultDao.create(result);
    }

    private void showSingleResult(Scanner scanner) {
        System.out.println("Enter Result Id");
        int id = scanner.nextInt();
        scanner.nextLine();
        Result result = new Result();

        result.setId(id);

        Result resultResult = resultDao.read(result);

        printTableHeader();
        printTableContent(resultResult);
    }

    private void showResults() {
        printTableHeader();
        resultDao.readAll().forEach(this::printTableContent);
    }

    private void showLeaderboard() {
        printTableHeader();
        resultDao.readAllLeaderBoard().forEach(this::printTableContent);
    }

    private void deleteResult(Scanner scanner) {
        System.out.println("Select result Id");
        int resultId = scanner.nextInt();

        Result result = new Result();

        result.setId(resultId);

        resultDao.delete(result);
    }

    private void updateResult(Scanner scanner) {
        System.out.println("Select result Id");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter score number to update to");
        int score = scanner.nextInt();
        scanner.nextLine();

        Result result = new Result();

        result.setId(id).setScore(score);

        resultDao.update(result);
    }


}
