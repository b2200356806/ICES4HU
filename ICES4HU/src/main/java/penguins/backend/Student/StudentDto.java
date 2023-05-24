package penguins.backend.Student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Course.Course;
import penguins.backend.Department.Department;
import penguins.backend.User.UserDto;
import penguins.backend.User.UserType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDto extends UserDto {
    private long studentId;
    private Department department;
    private List<Course> courses;
    private boolean accountRegistered;

    public StudentDto() {
        setUserType(UserType.STUDENT);
    }
}
