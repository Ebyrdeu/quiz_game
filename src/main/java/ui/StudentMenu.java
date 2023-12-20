package ui;

import java.util.Scanner;

public class StudentMenu implements Menuable {
    @Override
    public void start(Scanner scanner) {
        boolean running = true;

    }


    private void updateStudentEmail(Scanner scanner) {
        System.out.println("Select ");

        System.out.println("Select user Id");
        int userId = scanner.nextInt();

        System.out.println("write email you want to be changed to ");
        var email = scanner.nextLine();


    }

}