import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class GradeTracker {
    private final ArrayList<Student> students;
    private final Scanner scanner;

    public GradeTracker() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n1. Add a student");
            System.out.println("2. Enter grades for a student");
            System.out.println("3. Compute statistics");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> enterGrades();
                case 3 -> computeStatistics();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student added successfully.");
    }

    private void enterGrades() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        Student student = findStudent(name);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter the number of grades to add: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter grade #" + (i + 1) + ": ");
            double grade = scanner.nextDouble();
            student.addGrade(grade);
        }

        System.out.println("Grades added successfully.");
    }

    private void computeStatistics() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        Student student = findStudent(name);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (student.getGrades().isEmpty()) {
            System.out.println("No grades available for this student.");
            return;
        }

        double average = student.getAverageGrade();
        double highest = Collections.max(student.getGrades());
        double lowest = Collections.min(student.getGrades());

        System.out.printf("Statistics for %s:%n", student.getName());
        System.out.printf("Average grade: %.2f%n", average);
        System.out.printf("Highest grade: %.2f%n", highest);
        System.out.printf("Lowest grade: %.2f%n", lowest);
    }

    private Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();
        tracker.run();
    }
}

class Student {
    private final String name;
    private final ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
}