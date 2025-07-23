import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentRecordSystem studentRecordSystem = new StudentRecordSystem();
            StudentRecordSystemGUI gui = new StudentRecordSystemGUI(studentRecordSystem);
            gui.setVisible(true);
        });
    }
}
