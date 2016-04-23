package service;

import service.DAO.DAO;
import service.DAO.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("students")
public class RestService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Student> getAllStudents() throws SQLException {
        DAO dao = new DAO();
        return dao.getAllStudent();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Student getStudent(@PathParam("id") String id) throws SQLException {
        DAO dao = new DAO();
        Student student = dao.searchStudentById(Integer.parseInt(id));
        return student;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void addStudent(Student student) throws SQLException {
        DAO dao = new DAO();
        dao.setStudent(student);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void editStudent(Student student) throws SQLException {
        DAO dao = new DAO();
        dao.updateStudentById(student);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) throws SQLException {
        DAO dao = new DAO();
        dao.deleteStudentById(Integer.parseInt(id));
    }

}