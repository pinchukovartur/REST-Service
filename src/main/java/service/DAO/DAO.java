package service.DAO;

import java.applet.Applet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static service.DAO.Constants.*;


public class DAO extends Applet {

    public DAO() {
    }

    public void setStudent(Student student) {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(Constants.INSERT);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3, student.getGroup());
            statement.setInt(4, student.getScore());
            statement.execute();
            connection.close();
            statement.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteStudentById(int idStudent) {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(Constants.DELETE);
            statement.setInt(1, idStudent);
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student searchStudentById(int idStudent) {
        Student student = null;
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(Constants.SEARCH);
            statement.setInt(1, idStudent);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setIdStudent(resultSet.getInt("idstudent"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setScore(resultSet.getInt("score"));
                student.setGroup(resultSet.getInt("brigada"));
            }
            statement.execute();
            connection.close();
            statement.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return student;
    }

    public void updateStudentById(Student student) {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(Constants.UPDATE);
            statement.setInt(5, student.getIdStudent());
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3, student.getGroup());
            statement.setInt(4, student.getScore());
            statement.executeUpdate();
            connection.close();
            statement.close();
            statement.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<Student>();
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(Constants.SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setIdStudent(resultSet.getInt("idstudent"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setScore(resultSet.getInt("score"));
                student.setGroup(resultSet.getInt("brigada"));
                students.add(student);
            }
            statement.execute();
            connection.close();
            statement.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return students;
    }
}
