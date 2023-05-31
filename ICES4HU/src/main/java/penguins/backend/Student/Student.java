package penguins.backend.Student;

import jakarta.persistence.Entity;
import lombok.*;
import penguins.backend.Course.Course;
import penguins.backend.Department.Department;
import penguins.backend.User.User;
import penguins.backend.User.UserType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User {
    private long studentId;
    private Department department;
    private List<Course> courses;
    private boolean accountRegistered;

    public Student() {
        setUserType(UserType.STUDENT);
    }
}
