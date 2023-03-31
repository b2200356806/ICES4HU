package penguins.backend.application.port;

import penguins.backend.domain.model.Course;
import penguins.backend.domain.model.Student;

import java.util.List;

public interface StudentService {

    Student getStudentById(long id);

    Student getStudentByUsername(String username);

    void saveStudent(Student student);

    void deleteStudentById(long id);

    void deleteStudentByUsername(String username);

    List<Student> getAllStudents();

    void enrollStudentInCourse(long studentId, String courseCode);

    void dropStudentFromCourse(long studentId, String courseCode);

    List<Course> getStudentCourses(long studentId);

}
