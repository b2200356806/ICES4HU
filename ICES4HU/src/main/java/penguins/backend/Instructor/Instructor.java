package penguins.backend.Instructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Course.Course;
import penguins.backend.Department.Department;
import penguins.backend.User.User;
import penguins.backend.User.UserType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Instructor extends User {
    private Department department;
    private List<Course> courses;

    public Instructor() {
        setUserType(UserType.INSTRUCTOR);
    }
}
