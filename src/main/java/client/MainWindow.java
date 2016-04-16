package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JFrame mainFrame;
    private RestClient restClient;

    public MainWindow(){}

    public void addMainWindow(){

        restClient = new RestClient();
        mainFrame = new JFrame();
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setSize(new Dimension(700, 490));

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");
        JPanel jPanel = new JPanel();
        jPanel.add(addButton); jPanel.add(searchButton); jPanel.add(deleteButton); jPanel.add(updateButton);

        mainFrame.add(jPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(1,
                1, 1, 1), 0, 0));

        updateTable();

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer result = Integer.valueOf(JOptionPane.showInputDialog("Enter ID:"));
                    restClient.deleteStudentByID(result);
                    updateTable();
                }
                catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(mainFrame,"incorrect input!");
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Student student = new Student();
                    student.setName(JOptionPane.showInputDialog("Enter name:"));
                    student.setSurname(JOptionPane.showInputDialog("Enter surname:"));
                    student.setGroup(Integer.parseInt(JOptionPane.showInputDialog("Enter Group:")));
                    student.setScore(Integer.parseInt(JOptionPane.showInputDialog("Enter Score:")));
                    restClient.setNewStudent(student);
                    updateTable();
                }
                catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(mainFrame,"incorrect input!");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Student student = restClient.
                            getStudentByID(Integer.parseInt(JOptionPane.showInputDialog("Enter ID:")));
                    String message = student.getIdStudent() + " " + student.getName() + " " + student.getSurname() +
                            " " + student.getGroup() + " " + student.getScore();
                    JOptionPane.showMessageDialog(mainFrame, message);
                }
                catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(mainFrame,"incorrect input!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Student student = new Student();
                    student.setIdStudent(Integer.parseInt(JOptionPane.showInputDialog("Enter ID:")));
                    student.setName(JOptionPane.showInputDialog("Enter name:"));
                    student.setSurname(JOptionPane.showInputDialog("Enter surname:"));
                    student.setGroup(Integer.parseInt(JOptionPane.showInputDialog("Enter Group:")));
                    student.setScore(Integer.parseInt(JOptionPane.showInputDialog("Enter Score:")));
                    restClient.upgradeStudentByID(student);
                    updateTable();
                }
                catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(mainFrame,"incorrect input!");
                }
            }
        });
    }

    public void updateTable(){
        StudentTableModel studentTableModel = new StudentTableModel(restClient.getAllSrudent());
        JTable table = new JTable(studentTableModel);
        JScrollPane scrollPaneTable = new JScrollPane(table);
        mainFrame.add(scrollPaneTable, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(1,
                1, 1, 1), 0, 0));
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.addMainWindow();
    }
}
