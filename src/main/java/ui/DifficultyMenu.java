package ui;

import dao.difficulty.DifficultyDao;
import dao.difficulty.DifficultyDaoImpl;
import entity.Difficulty;

import java.util.List;
import java.util.Scanner;

public class DifficultyMenu implements Menuable {

    private final DifficultyDao difficultyDao = new DifficultyDaoImpl();

    @Override
    public void start(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("1. Add difficulty");
            System.out.println("2. View all difficulties");
            System.out.println("3. Update difficulty");
            System.out.println("4. Delete difficulty");
            System.out.println("5. Exit");
            System.out.println("Choose an option");
            int choice = scanner.nextInt();
            scanner.nextLine(); //consumea en line

            switch (choice) {
                case 1 -> addDifficulty(scanner);
                case 2 -> viewAllDifficulties();
                case 3 -> updateDifficulty(scanner);
                case 4 -> deleteDifficulty(scanner);
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }

        }
    }

    private void addDifficulty(Scanner scanner) {
        System.out.print("Enter difficulty name: ");
        String name = scanner.nextLine();
        Difficulty difficulty = new Difficulty();

        difficulty.setName(name);

        difficultyDao.create(difficulty);
        System.out.println("Difficulty added successfully!");
    }

    private void viewAllDifficulties() {
        List<Difficulty> difficulties = difficultyDao.readAll();
        for (Difficulty difficulty : difficulties) {
            System.out.println(difficulty.name());
        }
    }

    private void updateDifficulty(Scanner scanner) {
        System.out.print("Enter difficulty ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); //

        System.out.print("Enter new difficulty name: ");
        String newName = scanner.nextLine();

        Difficulty difficulty = new Difficulty();

        difficulty.setId(id).setName(newName);

        difficultyDao.update(difficulty);
        System.out.println("Difficulty updated successfully!");
    }

    private void deleteDifficulty(Scanner scanner) {
        System.out.print("Enter difficulty ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Difficulty difficulty = new Difficulty();

        difficulty.setId(id);

        difficultyDao.delete(difficulty);
        System.out.println("Difficulty deleted successfully!");
    }
}

