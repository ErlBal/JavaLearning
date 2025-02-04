import java.util.*;

class Student {
    private String firstName;
    private String lastName;
    private Integer studentId; // 6-digit ID
    private String major;
    private int year;
    private List<Course> coursesList;
    private final int maxCredit = 12;

    public Student(String firstName, String lastName, Integer studentId, String major, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.major = major;
        this.year = year;
        this.coursesList = new ArrayList<>();
    }

    public boolean addCourse(Course course) {
        int currentCredits = coursesList.stream().mapToInt(Course::getCredits).sum();
        if (currentCredits + course.getCredits() > maxCredit) {
            System.out.println("Cannot add course. Credit limit exceeded.");
            return false;
        }
        coursesList.add(course);
        return true;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId +
                ", Name: " + firstName + " " + lastName +
                ", Major: " + major +
                ", Year: " + year;
    }
}

class Course {
    private String professorName;
    private String courseName;
    private String courseId; // Example: MATH101, CE203, etc.
    private final int maxStudentQuota = 100;
    private List<Student> studentList;
    private int credits; // 1 to 12

    public Course(String professorName, String courseName, String courseId, int credits) {
        this.professorName = professorName;
        this.courseName = courseName;
        this.courseId = courseId;
        this.credits = credits;
        this.studentList = new ArrayList<>();
    }

    public boolean enrollStudent(Student student) {
        if (studentList.size() >= maxStudentQuota) {
            System.out.println("Cannot enroll. Maximum student quota reached.");
            return false;
        }
        studentList.add(student);
        return true;
    }

    public int getCredits() {
        return credits;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProfessorName() {
        return professorName;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId +
                ", Name: " + courseName +
                ", Professor: " + professorName +
                ", Credits: " + credits;
    }
}

public class Univ {
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:" +
                    "\n1. Add a Student" +
                    "\n2. Add a Course" +
                    "\n3. Register a Student for a Course" +
                    "\n4. List All Courses a Student is Enrolled In" +
                    "\n5. List All Students in a Course" +
                    "\n6. List All Students" +
                    "\n7. List All Courses" +
                    "\n8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> addCourse(scanner);
                case 3 -> registerStudentForCourse(scanner);
                case 4 -> listCoursesForStudent(scanner);
                case 5 -> listStudentsInCourse(scanner);
                case 6 -> listAllStudents();
                case 7 -> listAllCourses();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        // Проверка ввода 6-значного student ID
        Integer studentId = null;
        while (studentId == null) {
            System.out.println("Enter 6-digit student ID:");
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                scanner.nextLine(); // очистка буфера
                if (id >= 100000 && id <= 999999) {
                    studentId = id;
                } else {
                    System.out.println("Invalid ID. Please enter a 6-digit number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // очистка некорректного ввода
            }
        }

        System.out.println("Enter major:");
        String major = scanner.nextLine();
        System.out.println("Enter year:");
        int year = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student(firstName, lastName, studentId, major, year);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    private static void addCourse(Scanner scanner) {
        System.out.println("Enter professor name:");
        String professorName = scanner.nextLine();
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine();

        // Проверка количества кредитов (от 1 до 12)
        int credits = 0;
        while (true) {
            System.out.println("Enter course credits (1-12):");
            if (scanner.hasNextInt()) {
                credits = scanner.nextInt();
                scanner.nextLine(); // очистка буфера
                if (credits >= 1 && credits <= 12) {
                    break;
                } else {
                    System.out.println("Invalid credit value. Credits must be between 1 and 12.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // очистка некорректного ввода
            }
        }

        Course course = new Course(professorName, courseName, courseId, credits);
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    private static void registerStudentForCourse(Scanner scanner) {
        System.out.println("Enter student ID:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine();

        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.addCourse(course)) {
            course.enrollStudent(student);
            System.out.println("Student registered for course successfully.");
        }
    }

    private static void listCoursesForStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Courses for " + student + ":");
        for (Course course : student.getCoursesList()) {
            System.out.println(course);
        }
    }

    private static void listStudentsInCourse(Scanner scanner) {
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine();

        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("Students in " + course + ":");
        for (Student student : course.getStudentList()) {
            System.out.println(student);
        }
    }

    // Вывод всех студентов и их информации
    private static void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("List of all students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Вывод всех курсов и их информации
    private static void listAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("List of all courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}
