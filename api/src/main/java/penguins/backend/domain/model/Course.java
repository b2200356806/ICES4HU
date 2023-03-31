package penguins.backend.domain.model;

import lombok.Data;

@Data
public class Course {
    private String name;
    private String code;
    private String department;
    private Instructor instructor;

}
