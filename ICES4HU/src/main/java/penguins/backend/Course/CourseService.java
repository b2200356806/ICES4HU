package penguins.backend.Course;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Exception.CourseAlreadyExistsException;
import penguins.backend.Course.Exception.CourseNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    /**
     * Finds a list of all courses in the system.
     * @return a list of courses that are in the database
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courseRepository.findAll());
    }


    /**
     * Finds the course with the given course code.
     * @param courseCode course code
     * @return the course object with the given course code, if it exists
     * @throws CourseNotFoundException if there is no course with the given course code
     */
    public Course getCourseByCourseCode(String courseCode) throws CourseNotFoundException {
        return courseRepository.findByCourseCode(courseCode).orElseThrow(() -> new CourseNotFoundException("Course not found. Course Code: " + courseCode));
    }


    /**
     * Adds a course to database if it doesn't already exist.
     * @param course the course object to add to the database
     * @return the course in the database
     * @throws CourseAlreadyExistsException if there is another course with the same course code
     */
    public Course addCourse(Course course) throws CourseAlreadyExistsException {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new CourseAlreadyExistsException("Course with this code already exists. Course Code: " + course.getCourseCode());
        }
        return this.courseRepository.save(course);
    }


    /**
     * Removes the course with the given course code.
     * @param courseCode course code
     * @throws CourseNotFoundException if there is no course with the given course code
     */
    public void removeCourse(String courseCode) throws CourseNotFoundException {

        if (!courseRepository.existsByCourseCode(courseCode)) {
            throw new CourseNotFoundException("Course does not exist. Course Code: " + courseCode);
        }

        courseRepository.deleteByCourseCode(courseCode);
    }


}
