package penguins.backend.Student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Course.Course;
import penguins.backend.User.UserDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDto extends UserDto {
    private long studentId;
    private String department;
    private List<Course> courses;
    private boolean accountRegistered;
}
