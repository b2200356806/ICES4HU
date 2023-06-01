package penguins.backend.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import penguins.backend.Department.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /*private final List<Course> courses = new ArrayList<>();


    public List<Course> findAll() {
        return courses;
    }


    public List<Course> findByDepartment(Department department) {
        List<Course> output = new ArrayList<>();
        for (Course course : courses) {
            if (course.getDepartment().equals(department)) {
                output.add(course);
            }
        }

        return output;
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
    }*/

    List<Course> findByDepartment(Department Department);

   Optional<Course> findByCourseCode(String courseCode);

    Boolean existsByCourseCode(String courseCode);

    void deleteByCourseCode(String courseCode);
}
