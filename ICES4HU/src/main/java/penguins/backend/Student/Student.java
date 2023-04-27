package penguins.backend.Student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Course.Course;
import penguins.backend.User.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User {
    private long studentId;
    private String department;
    private List<Course> courses;
    private boolean accountRegistered;
}
