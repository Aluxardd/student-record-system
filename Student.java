public class Student {
    private final String id;
    private String name;
    private String address;
    private String course;
    private String department;
    private String modules;

    public Student(String id, String name, String address, String course, String department, String modules) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.course = course;
        this.department = department;
        this.modules = modules;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", course='" + course + '\'' +
                ", department='" + department + '\'' +
                ", modules='" + modules + '\'' +
                '}';
    }
}