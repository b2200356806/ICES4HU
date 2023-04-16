package penguins.backend.Instructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Course.Course;
import penguins.backend.User.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Instructor extends User {
    private String department;
    private List<Course> courses;
}
