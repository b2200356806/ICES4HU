package penguins.backend.domain.repository;

import penguins.backend.domain.model.Course;

import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findByCode(String courseCode);
    void save(Course course);
}
