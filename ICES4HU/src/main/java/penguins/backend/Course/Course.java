package penguins.backend.Course;

import lombok.Data;
import penguins.backend.Department.Department;

@Data
public class Course {
    private long id;
    private String courseCode;
    private String name;
    private Department department;
    private CourseType courseType;
}
