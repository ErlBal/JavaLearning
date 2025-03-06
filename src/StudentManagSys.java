import java.util.*;

// класс Student уже занят в другом файле :(
class StudentSys {
    private int id;
    private String name;
    private int age;
    private Set<String> courses;

    public StudentSys(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void addCourse(String course) {
        courses.add(course);
    }
}

class StudentManager {
    private Map<Integer, StudentSys> students;
    private Map<String, Set<StudentSys>> courseMap;

    public StudentManager() {
        students = new HashMap<>();
        courseMap = new HashMap<>();
    }

    public void addStudent(StudentSys student) {
        students.put(student.getId(), student);
        for (String course : student.getCourses()) {
            addStudentToCourse(course, student);
        }
    }

    public void removeStudent(int id) {
        StudentSys student = students.get(id);
        if (student != null) {
            for (String course : student.getCourses()) {
                Set<StudentSys> enrolledStudents = courseMap.get(course);
                if (enrolledStudents != null) {
                    enrolledStudents.remove(student);
                }
            }
            students.remove(id);
            System.out.println("student " + id + " removed");
        } else {
            System.out.println("student " + id + " not found");
        }
    }

    public void updateStudent(int id, String name, int age) {
        StudentSys student = students.get(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
            System.out.println("student updated");
        } else {
            System.out.println("student" + id + " not found");
        }
    }

    public void addCourseToStudent(int id, String course) {
        StudentSys student = students.get(id);
        if (student != null) {
            student.addCourse(course);
            addStudentToCourse(course, student);
            System.out.println("course added");
        } else {
            System.out.println("student" + id + " not found");
        }
    }

    private void addStudentToCourse(String course, StudentSys student) {
        courseMap.putIfAbsent(course, new HashSet<>());
        courseMap.get(course).add(student);
    }

    private void printStudentInfo(StudentSys student) {
        System.out.println("id: " + student.getId() + ", name: " + student.getName() + ", age: " + student.getAge() +
                ", courses: " + student.getCourses());
    }

    public void allStudents() {
        if (students.isEmpty()) {
            System.out.println("no students");
            return;
        }

        System.out.println("\nstudents sorted by id");
        students.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> printStudentInfo(entry.getValue()));
    }

    public void searchStudentById(int id) {
        StudentSys student = students.get(id);
        if (student != null) {
            System.out.println("\nstudent info");
            printStudentInfo(student);
        } else {
            System.out.println("student " + id + " not found");
        }
    }

    public void listStudentsByCourse(String course) {
        Set<StudentSys> enrolledStudents = courseMap.get(course);
        if (enrolledStudents == null || enrolledStudents.isEmpty()) {
            System.out.println("no students enrolled in: " + course);
            return;
        }

        System.out.println("\nstudents enrolled in: " + course);
        for (StudentSys student : enrolledStudents) {
            printStudentInfo(student);
        }
    }

    public void studentsSortByName() {
        if (students.isEmpty()) {
            System.out.println("no students");
            return;
        }

        System.out.println("\nstudents sorted by name");
        TreeSet<StudentSys> sortedStudents = new TreeSet<>(Comparator.comparing(StudentSys::getName));
        //не знаю можно ли использовать компаратор, но как я понял без него TreeSet не реализовать
        sortedStudents.addAll(students.values());
        for (StudentSys student : sortedStudents) {
            printStudentInfo(student);
        }
    }
}

public class StudentManagSys {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        StudentSys student1 = new StudentSys(101, "student1", 20);
        student1.addCourse("jp");
        student1.addCourse("cs");
        manager.addStudent(student1);

        StudentSys student2 = new StudentSys(102, "studentOdin", 22);
        student2.addCourse("jp");
        student2.addCourse("wp");
        manager.addStudent(student2);

        StudentSys student3 = new StudentSys(103, "studentOne", 21);
        student3.addCourse("algebra");
        manager.addStudent(student3);

        manager.allStudents();

        manager.searchStudentById(102);

        manager.listStudentsByCourse("jp");

        manager.updateStudent(101, "student1", 21);

        manager.addCourseToStudent(103, "jp");

        student1.addCourse("jp");

        manager.allStudents();

        manager.studentsSortByName();

        manager.removeStudent(102);

        manager.allStudents();
    }
}
