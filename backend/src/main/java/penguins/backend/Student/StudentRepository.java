package penguins.backend.Student;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return this.students;
    }

    public Optional<Student> findById(long userId) {
        for (Student student : students)
            if (student.getUserId() == userId)
                return Optional.of(student);
        return Optional.empty();
    }

    public Student save(Student student) {
        students.remove(student);
        students.add(student);
        return student;
    }
}
