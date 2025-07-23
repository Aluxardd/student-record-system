import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StudentRecordSystemGUI extends JFrame {
    private final StudentRecordSystem studentRecordSystem;
    private JTabbedPane tabbedPane;
    private JPanel addStudentPanel;
    private JPanel viewAllStudentsPanel;
    private JPanel searchStudentPanel;
    private JPanel updateStudentPanel;

    // Components for Add Student panel
    private JTextField addIdField;
    private JTextField addNameField;
    private JTextField addAddressField;
    private JTextField addCourseField;
    private JTextField addDepartmentField;
    private JTextField addModulesField;

    // Components for View All Students panel
    private JTable studentsTable;
    private DefaultTableModel tableModel;

    // Components for Search Student panel
    private JTextField searchIdField;
    private JTextArea searchResultArea;

    // Components for Update Student panel
    private JTextField updateIdField;
    private JTextField updateNameField;
    private JTextField updateAddressField;
    private JTextField updateCourseField;
    private JTextField updateDepartmentField;
    private JTextField updateModulesField;
    private JButton findStudentButton;

    public StudentRecordSystemGUI(StudentRecordSystem studentRecordSystem) {
        this.studentRecordSystem = studentRecordSystem;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Student Record System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Create panels
        createAddStudentPanel();
        createViewAllStudentsPanel();
        createSearchStudentPanel();
        createUpdateStudentPanel();

        // Add panels to tabbed pane
        tabbedPane.addTab("Add Student", addStudentPanel);
        tabbedPane.addTab("View All Students", viewAllStudentsPanel);
        tabbedPane.addTab("Search Student", searchStudentPanel);
        tabbedPane.addTab("Update Student", updateStudentPanel);

        add(tabbedPane);

        // Initialize the student table with any existing data
        refreshStudentTable();
    }

    private void createAddStudentPanel() {
        addStudentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Create form fields
        addIdField = new JTextField(20);
        addNameField = new JTextField(20);
        addAddressField = new JTextField(20);
        addCourseField = new JTextField(20);
        addDepartmentField = new JTextField(20);
        addModulesField = new JTextField(20);
        JButton addButton = new JButton("Add Student");

        // Add components to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        addStudentPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addStudentPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addStudentPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addAddressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addStudentPanel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addCourseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        addStudentPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addDepartmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        addStudentPanel.add(new JLabel("Modules:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addModulesField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        addStudentPanel.add(addButton, gbc);

        // Add action listener to button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
    }

    private void createViewAllStudentsPanel() {
        viewAllStudentsPanel = new JPanel(new BorderLayout());

        // Create table model with columns
        String[] columnNames = {"ID", "Name", "Address", "Course", "Department", "Modules"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentsTable = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        viewAllStudentsPanel.add(scrollPane, BorderLayout.CENTER);

        // Add refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshStudentTable();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        viewAllStudentsPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createSearchStudentPanel() {
        searchStudentPanel = new JPanel(new BorderLayout());

        // Create search form
        JPanel searchForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchForm.add(new JLabel("Student ID:"));
        searchIdField = new JTextField(15);
        searchForm.add(searchIdField);
        JButton searchButton = new JButton("Search");
        searchForm.add(searchButton);

        // Create result area
        searchResultArea = new JTextArea(10, 40);
        searchResultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(searchResultArea);

        // Add components to panel
        searchStudentPanel.add(searchForm, BorderLayout.NORTH);
        searchStudentPanel.add(scrollPane, BorderLayout.CENTER);

        // Add action listener to button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
    }

    private void createUpdateStudentPanel() {
        updateStudentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Create form fields
        updateIdField = new JTextField(20);
        updateNameField = new JTextField(20);
        updateAddressField = new JTextField(20);
        updateCourseField = new JTextField(20);
        updateDepartmentField = new JTextField(20);
        updateModulesField = new JTextField(20);
        findStudentButton = new JButton("Find Student");
        JButton updateButton = new JButton("Update Student");

        // Initially disable fields until student is found
        updateNameField.setEnabled(false);
        updateAddressField.setEnabled(false);
        updateCourseField.setEnabled(false);
        updateDepartmentField.setEnabled(false);
        updateModulesField.setEnabled(false);
        updateButton.setEnabled(false);

        // Add components to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        updateStudentPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        updateStudentPanel.add(updateIdField, gbc);
        gbc.gridx = 2;
        updateStudentPanel.add(findStudentButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        updateStudentPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        updateStudentPanel.add(updateNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        updateStudentPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        updateStudentPanel.add(updateAddressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        updateStudentPanel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        updateStudentPanel.add(updateCourseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        updateStudentPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        updateStudentPanel.add(updateDepartmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        updateStudentPanel.add(new JLabel("Modules:"), gbc);
        gbc.gridx = 1;
        updateStudentPanel.add(updateModulesField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        updateStudentPanel.add(updateButton, gbc);

        // Add action listeners to buttons
        findStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findStudentForUpdate();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
    }

    private void addStudent() {
        try {
            String id = addIdField.getText().trim();
            String name = addNameField.getText().trim();
            String address = addAddressField.getText().trim();
            String course = addCourseField.getText().trim();
            String department = addDepartmentField.getText().trim();
            String modules = addModulesField.getText().trim();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Name are required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = new Student(id, name, address, course, department, modules);
            studentRecordSystem.addStudent(student);

            // Clear fields after adding
            addIdField.setText("");
            addNameField.setText("");
            addAddressField.setText("");
            addCourseField.setText("");
            addDepartmentField.setText("");
            addModulesField.setText("");

            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh the table if it's visible
            if (tabbedPane.getSelectedIndex() == 1) {
                refreshStudentTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshStudentTable() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Get all students and add them to the table
        for (Student student : studentRecordSystem.getAllStudents()) {
            Vector<String> row = new Vector<>();
            row.add(student.getId());
            row.add(student.getName());
            row.add(student.getAddress());
            row.add(student.getCourse());
            row.add(student.getDepartment());
            row.add(student.getModules());
            tableModel.addRow(row);
        }
    }

    private void searchStudent() {
        String id = searchIdField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = studentRecordSystem.getStudentById(id);
        if (student != null) {
            searchResultArea.setText(student.toString());
        } else {
            searchResultArea.setText("No student found with ID: " + id);
        }
    }

    private void findStudentForUpdate() {
        String id = updateIdField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = studentRecordSystem.getStudentById(id);
        if (student != null) {
            // Populate fields with student data
            updateNameField.setText(student.getName());
            updateAddressField.setText(student.getAddress());
            updateCourseField.setText(student.getCourse());
            updateDepartmentField.setText(student.getDepartment());
            updateModulesField.setText(student.getModules());

            // Enable fields for editing
            updateNameField.setEnabled(true);
            updateAddressField.setEnabled(true);
            updateCourseField.setEnabled(true);
            updateDepartmentField.setEnabled(true);
            updateModulesField.setEnabled(true);
            findStudentButton.setEnabled(true);

            // Enable update button
            Component[] components = updateStudentPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JButton && ((JButton) component).getText().equals("Update Student")) {
                    component.setEnabled(true);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No student found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        try {
            String id = updateIdField.getText().trim();
            String name = updateNameField.getText().trim();
            String address = updateAddressField.getText().trim();
            String course = updateCourseField.getText().trim();
            String department = updateDepartmentField.getText().trim();
            String modules = updateModulesField.getText().trim();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Name are required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            studentRecordSystem.updateStudent(id, name, address, course, department, modules);

            JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear and disable fields after updating
            updateIdField.setText("");
            updateNameField.setText("");
            updateAddressField.setText("");
            updateCourseField.setText("");
            updateDepartmentField.setText("");
            updateModulesField.setText("");

            updateNameField.setEnabled(false);
            updateAddressField.setEnabled(false);
            updateCourseField.setEnabled(false);
            updateDepartmentField.setEnabled(false);
            updateModulesField.setEnabled(false);

            // Disable update button
            Component[] components = updateStudentPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JButton && ((JButton) component).getText().equals("Update Student")) {
                    component.setEnabled(false);
                    break;
                }
            }

            // Refresh the table if it's visible
            if (tabbedPane.getSelectedIndex() == 1) {
                refreshStudentTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
