package penguins.backend.Student;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findById(long id);

    Student save(Student student);
}
