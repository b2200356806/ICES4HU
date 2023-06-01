package penguins.backend.Instructor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import penguins.backend.Course.Course;
import penguins.backend.Department.Department;
import penguins.backend.User.User;
import penguins.backend.User.UserType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Instructor extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private List<Course> courses;

    public Instructor() {
        setUserType(UserType.INSTRUCTOR);
    }
}
