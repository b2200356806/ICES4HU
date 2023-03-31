package penguins.backend.domain.repository;

import penguins.backend.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findById(long id);

    boolean existsById(long id);

    Optional<Student> findByUsername(String username);

    boolean existsByUsername(String username);

    List<Student> findAll();

    void save(Student student);

    void deleteById(long id);

    void deleteByUsername(String username);
}
