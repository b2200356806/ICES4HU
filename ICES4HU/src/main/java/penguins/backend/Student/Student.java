package penguins.backend.Student;

import jakarta.persistence.*;
import lombok.*;
import penguins.backend.Course.Course;
import penguins.backend.Department.Department;
import penguins.backend.User.User;
import penguins.backend.User.UserType;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "userId")
public class Student extends User {

    private long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_course",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "studentId")},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id")})
    private List<Course> courses = new ArrayList<>();

    private boolean accountRegistered;

    public Student() {
        setUserType(UserType.STUDENT);
    }
}
