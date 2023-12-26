package ui;

import dao.student.StudentDao;
import dao.student.StudentDaoImpl;
import entity.Student;

import java.util.Scanner;

public class StudentMenu implements Menu {

    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void start(Scanner scanner) {
        boolean running = true;

        do {
            System.out.println("""
                    1 - Create Student
                    2 - Show Student info
                    3 - Update Student info
                    4 - Delete student
                                
                    5 - exit
                    """);

            switch (scanner.nextLine()) {
                case "1" -> createStudent(scanner);
                case "2" -> showStudentInfo(scanner);
                case "3" -> updateStudent(scanner);
                case "4" -> deleteStudent(scanner);
                case "5" -> running = false;
                default -> System.out.println("Not an option");
            }
        } while (running);
    }


    private void createStudent(Scanner scanner) {
        Student student = new Student();

        System.out.println("Enter student name ");
        String name = scanner.nextLine();
        System.out.println("Enter student age ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter student Email ");
        String studentEmail = scanner.nextLine();
        System.out.println("Enter student class ");
        String studentClass = scanner.nextLine();

        student
                .setName(name)
                .setAge(age)
                .setEmail(studentEmail)
                .setStudentClass(studentClass);

        studentDao.create(student);
        System.out.println("Created new student");

    }

    private void showStudentInfo(Scanner scanner) {
        System.out.println("Enter student Id: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = new Student();
        student.setId(studentId);

        printTableHeader();
        printTableContent(studentDao.read(student));
    }

    private void updateStudent(Scanner scanner) {
        System.out.println("Enter Student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = studentDao.read(new Student().setId(studentId));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        boolean updateCycle = true;
        do {
            System.out.println("""                 
                    Choose what to update:
                    1 - Name
                    2 - Age
                    3 - Email
                    4 - Class
                    5 - Finish updating
                     """);
            switch (scanner.nextLine()) {
                case "1" -> updateStudentName(student, scanner);
                case "2" -> updateStudentAge(student, scanner);
                case "3" -> updateStudentEmail(student, scanner);
                case "4" -> updateStudentClass(student, scanner);
                case "5" -> updateCycle = false;
                default -> System.out.println("Invalid choice. Please choose again.");
            }
        } while (updateCycle);
        studentDao.update(student);
        System.out.println("Student information updated successfully.");
    }

    private void updateStudentName(Student student, Scanner scanner) {
        System.out.println("Enter new Name: ");
        String name = scanner.nextLine();
        student.setName(name);
    }

    private void updateStudentAge(Student student, Scanner scanner) {
        System.out.println("Enter new Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        student.setAge(age);
    }

    private void updateStudentEmail(Student student, Scanner scanner) {
        System.out.println("Enter new Email: ");
        String email = scanner.nextLine();
        student.setEmail(email);
    }

    private void updateStudentClass(Student student, Scanner scanner) {
        System.out.println("Enter new Class: ");
        String studentClass = scanner.nextLine();
        student.setStudentClass(studentClass);
    }

    private void deleteStudent(Scanner scanner) {
        System.out.println("Enter student id: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student();
        student.setId(studentId);
        studentDao.delete(student);
    }

    private void printTableHeader() {
        System.out.printf("| %-10s | %-15s | %-3s | %-30s | %-30s |%n",
                "studentId",
                "name",
                "age,",
                "email",
                "class"
        );
    }

    private void printTableContent(Student entity) {
        System.out.printf("| %-10s | %-15s | %-3s | %-30s | %-30s |%n",
                entity.id(),
                entity.name(),
                entity.age(),
                entity.email(),
                entity.studentClass()
        );
    }

}