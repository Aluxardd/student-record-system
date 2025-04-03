import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentRecordSystem studentRecordSystem = new StudentRecordSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Modules: ");
                    String modules = scanner.nextLine();
                    Student student = new Student(id, name, address, course, department, modules);
                    studentRecordSystem.addStudent(student);
                }
                case 2 -> studentRecordSystem.displayAllStudents();
                case 3 -> {
                    System.out.print("Enter Student ID: ");
                    String searchId = scanner.nextLine();
                    Student searchedStudent = studentRecordSystem.getStudentById(searchId);
                    if (searchedStudent != null) {
                        System.out.println(searchedStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Student ID: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Address: ");
                    String newAddress = scanner.nextLine();
                    System.out.print("Enter New Course: ");
                    String newCourse = scanner.nextLine();
                    System.out.print("Enter New Department: ");
                    String newDepartment = scanner.nextLine();
                    System.out.print("Enter New Modules: ");
                    String newModules = scanner.nextLine();
                    studentRecordSystem.updateStudent(updateId, newName, newAddress, newCourse, newDepartment, newModules);
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}