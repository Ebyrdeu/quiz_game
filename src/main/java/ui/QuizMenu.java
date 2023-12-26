package ui;

import dao.quiz.QuizDao;
import dao.quiz.QuizDaoimpl;
import entity.Category;
import entity.Difficulty;
import entity.Quiz;

import java.util.Scanner;

public class QuizMenu implements Menu {

    private final QuizDao quizDao = new QuizDaoimpl();

    // instansvariabel för quiz-data i databasen

    private static void printMainMenu() {
        System.out.println("===== Quiz Menu =====");
        System.out.println("1. Show all quiz questions and right answers");
        System.out.println("2. Update a quiz question");
        System.out.println("3. Delete a quiz question");
        System.out.println("4. Create a new quiz question");
        System.out.println("5. Return to main menu");
        System.out.println("=====================");
    }
    // meny skapad, behöver kanske lägga till att man kan skapa en fråga???


    private void printTableHeader() {
        System.out.printf("| %-10s | %-15s | %-15s | %-30s | %-30s |%n", "ID", "Difficulty Type", "Category Name", "Quiz Question", "Correct Answer");
    } // metod för att skriva ut tabellrubriken när quiz frågorna visas
    //ToDo behöver koppla med difficulty och category

    private void printTableContent(Quiz entity) {
        System.out.printf("| %-10s | %-15s | %-15s | %-30s | %-30s |%n", entity.id(), entity.difficulty().name(), entity.category().name(), entity.quizQuestion(), entity.correctAnswer());
    } // Metod för att skriva ut innehållet i ett quiz

    @Override
    public void start(Scanner scanner) {
        boolean cycle = true;

        do {
            printMainMenu();

            switch (scanner.nextLine()) {
                case "1" -> showAllQuizQuestions();
                case "2" -> updateQuizQuestion(scanner);
                case "3" -> deleteQuizQuestion(scanner);
                case "4" -> createNewQuizQuestion(scanner);
                case "5" -> cycle = false;
                default -> System.out.println("Wrong Key");
            }
        } while (cycle);
    }

    private void showAllQuizQuestions() {
        printTableHeader();
        quizDao.readAll().forEach(this::printTableContent);
        // metod för att visa alla quizfrågor
    }
    // updatera quiz fråga metod, behöver fixa ihop med category och difficulty om den ska fungera


    private void deleteQuizQuestion(Scanner scanner) {
        System.out.println("Enter quiz ID to delete: ");
        int quizId = scanner.nextInt();
        scanner.nextLine();

        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        quizDao.delete(quiz);
        // delete quiz metoden
    }

    private void updateQuizQuestion(Scanner scanner) {
        System.out.println("Enter Quiz ID to update: ");
        int quizId = scanner.nextInt();
        scanner.nextLine();

        Quiz quiz = quizDao.read(new Quiz().setId(quizId));
        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        boolean updateCycle = true;

        do {
            System.out.println("""
                    Choose what to update:
                    1 - Difficulty Type
                    2 - Category Name
                    3 - Quiz Question
                    4 - Correct Answer
                    5 - Finish updating
                    """);

            switch (scanner.nextLine()) {
                case "1" -> updateQuizDifficulty(quiz, scanner);
                case "2" -> updateQuizCategory(quiz, scanner);
                case "3" -> updateQuizQuestionText(quiz, scanner);
                case "4" -> updateQuizCorrectAnswer(quiz, scanner);
                case "5" -> updateCycle = false;
                default -> System.out.println("Invalid choice. Please choose again.");
            }
        } while (updateCycle);

        quizDao.update(quiz);
        System.out.println("Quiz information updated successfully.");
    }

    private void updateQuizDifficulty(Quiz quiz, Scanner scanner) {
        System.out.println("Enter new Difficulty Type (easy, medium, hard): ");
        String difficultyType = scanner.nextLine();
        quiz.difficulty().setName(difficultyType);
    }

    private void updateQuizCategory(Quiz quiz, Scanner scanner) {
        System.out.println("Enter new Category Name: ");
        String categoryName = scanner.nextLine();
        quiz.category().setName(categoryName);
    }

    private void updateQuizQuestionText(Quiz quiz, Scanner scanner) {
        System.out.println("Enter new Quiz Question: ");
        String quizQuestion = scanner.nextLine();
        quiz.setQuizQuestion(quizQuestion);
    }

    private void updateQuizCorrectAnswer(Quiz quiz, Scanner scanner) {
        System.out.println("Enter new Correct Answer: ");
        String correctAnswer = scanner.nextLine();
        quiz.setCorrectAnswer(correctAnswer);
    }

    private void createNewQuizQuestion(Scanner scanner) {
        System.out.println("Enter Difficulty Type (easy, medium, hard): ");
        int difficultyId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Category Id: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Quiz Question: ");
        String quizQuestion = scanner.nextLine();

        System.out.println("Enter Correct Answer: ");
        String correctAnswer = scanner.nextLine();

        Quiz quiz = new Quiz();


        quiz.setDifficulty(new Difficulty().setId(difficultyId));
        quiz.setCategory(new Category().setCategoryId(categoryId));

        quiz.setQuizQuestion(quizQuestion);
        quiz.setCorrectAnswer(correctAnswer);

        quizDao.create(quiz);
        System.out.println("Quiz question created successfully!");
    }

}

