package penguins.backend.Course;

import lombok.Data;
import penguins.backend.Instructor.Instructor;

@Data
public class Course {
    private long id;
    private String courseCode;
    private String name;
    private String department;
    private Instructor instructor;
    private CourseType courseType;
}
