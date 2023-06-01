package penguins.backend.Course;

import jakarta.persistence.*;
import lombok.Data;
import penguins.backend.Department.Department;
import penguins.backend.Student.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String courseCode;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Set<Student> students = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private CourseType courseType;
}