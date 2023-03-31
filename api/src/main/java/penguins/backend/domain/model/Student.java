package penguins.backend.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User {
    private String department;
    private List<Course> courses;

}


