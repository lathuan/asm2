import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        do {
            System.out.println("Enter the number of students:");
            n = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (n <= 0) {
                System.out.println("The number of students must be greater than 0. Please enter again.");
            }
        } while (n <= 0);

        for (int i = 0; i < n; i++) {
            input(scanner);
        }

        System.out.println("PRINT STUDENT LIST:");
        output();

        System.out.println("Enter the student code you want to delete:");
        String codeToRemove = scanner.nextLine();
        removeByCode(codeToRemove);
        System.out.println("List of students after deletion:");
        output();

        sortByGradeDesc();
        System.out.println("List of students after being sorted by score:");
        output();

        System.out.println("Enter the student code or name you want to find:");
        String keyword = scanner.nextLine();
        Student foundStudent = findByCodeOrName(keyword);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("No students found.");
        }

        System.out.println("Enter the student score you want to search for (>= x):");
        float x = scanner.nextFloat();
        List<Student> filteredStudents = filterByGrade(x);
        System.out.println("List of students with score >= " + x + ":");
        for (Student student : filteredStudents) {
            System.out.println(student);
        }
    }

    public static void input(Scanner scanner) {
        System.out.println("Enter student information:");

        System.out.print("Enter student code: ");
        String code = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter score: ");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // consume newline

        studentList.add(new Student(code, name, grade));
    }

    public static void output() {
        studentList.forEach(System.out::println);
    }

    public static void removeByCode(String code) {
        studentList.removeIf(student -> student.getCode().equals(code));
    }

    public static void sortByGradeDesc() {
        studentList.sort(Comparator.comparing(Student::getGrade).reversed());
    }

    public static Student findByCodeOrName(String keyword) {
        return studentList.stream()
                .filter(student -> student.getCode().equals(keyword) || student.getName().equalsIgnoreCase(keyword))
                .findFirst()
                .orElse(null);
    }

    public static List<Student> filterByGrade(float x) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= x) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }
}

class Student {
    private String code;
    private String name;
    private float grade;

    public Student(String code, String name, float grade) {
        this.code = code;
        this.name = name;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
