package client;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StudentTableModel extends AbstractTableModel{

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Student> student;

    public StudentTableModel(List<Student> student) {
        this.student = student;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID Студента";
            case 1:
                return "Имя";
            case 2:
                return "Фамилия";
            case 3:
                return "Группа";
            case 4:
                return "Средний балл";
        }
        return "";
    }

    public int getRowCount() {
        return student.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Student stud = student.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stud.getIdStudent();
            case 1:
                return stud.getName();
            case 2:
                return stud.getSurname();
            case 3:
                return stud.getGroup();
            case 4:
                return stud.getScore();
        }
        return "";


    }

    public void removeStudent(Student oldStudent){
        student.remove(oldStudent);
    }

    public Student getValueRow(int rowIndex) {
        return student.get(rowIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

}