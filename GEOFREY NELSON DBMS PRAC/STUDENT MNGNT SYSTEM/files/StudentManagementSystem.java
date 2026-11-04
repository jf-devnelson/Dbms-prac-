import java.util.List;
import java.util.Scanner;

/**
 * StudentManagementSystem.java
 * Entry point of the application.
 * Provides a console-based menu that lets the user perform
 * CRUD (Create, Read, Update, Delete) operations on student records.
 */
public class StudentManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAOImpl();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=========================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM (Java + MySQL)");
        System.out.println("=========================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudentById();
                case 4 -> searchStudentByName();
                case 5 -> updateStudent();
                case 6 -> deleteStudent();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        DBConnection.closeConnection();
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------- MENU -----------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Search Student by Name");
        System.out.println("5. Update Student");
        System.out.println("6. Delete Student");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------------");
    }

    private static void addStudent() {
        System.out.println("\n-- Add New Student --");
        String name = readString("Name: ");
        int age = readInt("Age: ");
        String course = readString("Course: ");
        String email = readString("Email: ");
        String phone = readString("Phone: ");

        Student student = new Student(name, age, course, email, phone);
        boolean success = studentDAO.addStudent(student);

        System.out.println(success ? "Student added successfully!" : "Failed to add student.");
    }

    private static void viewAllStudents() {
        System.out.println("\n-- All Students --");
        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudentById() {
        int id = readInt("\nEnter Student ID: ");
        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            System.out.println("No student found with ID " + id);
        } else {
            System.out.println(student);
        }
    }

    private static void searchStudentByName() {
        String name = readString("\nEnter name (or part of name) to search: ");
        List<Student> students = studentDAO.searchStudentsByName(name);

        if (students.isEmpty()) {
            System.out.println("No matching students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void updateStudent() {
        int id = readInt("\nEnter the ID of the student to update: ");
        Student existing = studentDAO.getStudentById(id);

        if (existing == null) {
            System.out.println("No student found with ID " + id);
            return;
        }

        System.out.println("Current details: " + existing);
        System.out.println("Enter new details (leave blank to keep current value):");

        String name = readString("Name [" + existing.getName() + "]: ");
        if (!name.isBlank()) existing.setName(name);

        String ageInput = readString("Age [" + existing.getAge() + "]: ");
        if (!ageInput.isBlank()) existing.setAge(Integer.parseInt(ageInput));

        String course = readString("Course [" + existing.getCourse() + "]: ");
        if (!course.isBlank()) existing.setCourse(course);

        String email = readString("Email [" + existing.getEmail() + "]: ");
        if (!email.isBlank()) existing.setEmail(email);

        String phone = readString("Phone [" + existing.getPhone() + "]: ");
        if (!phone.isBlank()) existing.setPhone(phone);

        boolean success = studentDAO.updateStudent(existing);
        System.out.println(success ? "Student updated successfully!" : "Failed to update student.");
    }

    private static void deleteStudent() {
        int id = readInt("\nEnter the ID of the student to delete: ");
        System.out.print("Are you sure you want to delete this student? (y/n): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            boolean success = studentDAO.deleteStudent(id);
            System.out.println(success ? "Student deleted successfully!" : "Failed to delete student (ID may not exist).");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ---------- Input helper methods ----------
    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
