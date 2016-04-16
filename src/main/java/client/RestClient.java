package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class RestClient {

    private WebResource webResource;

    public RestClient() {
        String URI = "http://localhost:8080";
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource(URI);
    }

    public List<Student> getAllSrudent() {
        GenericType<List<Student>> genericType = new GenericType<List<Student>>() {
        };
        List<Student> students = webResource.path("rest").path("students").accept(MediaType.APPLICATION_XML)
                .get(genericType);
        return students;
    }

    public Student getStudentByID(int idStudent) {
        Student student = webResource.path("rest").path("students").path(String.valueOf(idStudent)).
                accept(MediaType.APPLICATION_XML).get(Student.class);
        return student;
    }

    public void deleteStudentByID(int idStudent) {
        webResource.path("rest").path("students").path(String.valueOf(idStudent)).delete();
    }

    public void upgradeStudentByID(Student student) {
        webResource.path("rest").path("students").accept(MediaType.APPLICATION_XML).post(student);     //POST
    }

    public void setNewStudent(Student newStudent) {
        webResource.path("rest").path("students").accept(MediaType.APPLICATION_XML).put(newStudent);   //PUT
    }
}
