package ui;

import dao.category.CategoryDao;
import dao.category.CategoryDaoImpl;
import entity.Category;

import java.util.Scanner;

public class CategoryMenu implements Menu {
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void start(Scanner scanner) {
        boolean running = true;

        do {
            System.out.println("""
                    1 - Show categories
                    2 - create new category
                    3 - update category
                    4 - Delete category
                                        
                    e - exit
                    """);

            switch (scanner.nextLine()) {
                case "1" -> showCategory();
                case "2" -> createCategory(scanner);
                case "3" -> updateCategory(scanner);
                case "4" -> deleteCategory(scanner);
                case "e", "E" -> running = false;
                default -> System.out.println("Not an option");
            }
        } while (running);
    }


    private void showCategory() {
        var result = categoryDao.readAll();
        printTableHeader();
        result.forEach(this::printTableContent);
    }

    private void createCategory(Scanner scanner) {
        System.out.println("Enter new category");
        String categoryName = scanner.nextLine();

        Category category = new Category();
        category.setName(categoryName);

        categoryDao.create(category);
    }

    private void updateCategory(Scanner scanner) {
        System.out.println("Enter id of category to update");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new name of category");
        var categoryName = scanner.nextLine();

        Category category = new Category();

        category.setCategoryId(categoryId);
        category.setName(categoryName);

        categoryDao.update(category);
    }

    private void deleteCategory(Scanner scanner) {
        System.out.println("Enter id of category to delete");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        Category category = new Category();
        category.setCategoryId(categoryId);

        categoryDao.delete(category);
    }

    private void printTableHeader() {
        System.out.printf("| %-10s | %-15s |%n",
                "categoryId",
                "Category Name"
        );
    }

    private void printTableContent(Category entity) {
        System.out.printf("| %-10s | %-15s |%n",
                entity.categoryId(),
                entity.name()
        );
    }
}
