package penguins.backend.Instructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import penguins.backend.Course.Course;
import penguins.backend.Department.Department;

import java.util.List;

@Getter
public class InstructorRegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Department department;
    private List<Course> courses;
    @JsonProperty("isDepartmentManager")
    private boolean isDepartmentManager;
}
