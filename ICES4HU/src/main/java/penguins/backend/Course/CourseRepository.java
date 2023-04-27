package penguins.backend.Course;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();


    public List<Course> findAll() {
        return courses;
    }


    public Optional<Course> findByCourseCode(String courseCode) {
        for (Course course : courses)
            if (course.getCourseCode().equals(courseCode))
                return Optional.of(course);

        return Optional.empty();
    }


    public boolean existsByCourseCode(String courseCode) {
        for (Course course : courses)
            if (course.getCourseCode().equals(courseCode))
                return true;
        return false;
    }


    public Course save(Course course) {
        courses.remove(course);
        courses.add(course);
        return course;
    }


    public void deleteByCourseCode(String courseCode) {
        courses.removeIf(course -> course.getCourseCode().equals(courseCode));
    }
}
