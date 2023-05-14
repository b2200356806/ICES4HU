package penguins.backend.Instructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Course.Course;
import penguins.backend.Department.Department;
import penguins.backend.User.UserDto;
import penguins.backend.User.UserType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InstructorDto extends UserDto {
    private Department department;
    private List<Course> courses;

    public InstructorDto() {
        setUserType(UserType.INSTRUCTOR);
    }
}
