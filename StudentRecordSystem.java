import java.util.HashMap;
import java.util.Map;

public class StudentRecordSystem {
    private final Map<String, Student> studentMap;

    public StudentRecordSystem() {
        studentMap = new HashMap<>();
    }

    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }

    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    public void updateStudent(String id, String name, String address, String course, String department, String modules) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setAddress(address);
            student.setCourse(course);
            student.setDepartment(department);
            student.setModules(modules);
        }
    }

    public void displayAllStudents() {
        for (Student student : studentMap.values()) {
            System.out.println(student);
        }
    }
}