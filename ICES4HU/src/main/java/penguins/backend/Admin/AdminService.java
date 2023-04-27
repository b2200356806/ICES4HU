package penguins.backend.Admin;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseRepository;
import penguins.backend.Course.CourseService;
import penguins.backend.Course.CourseType;
import penguins.backend.Course.Exception.CourseAlreadyExistsException;
import penguins.backend.Semester.Semester;
import penguins.backend.Student.Student;
import penguins.backend.Student.StudentRepository;

import java.util.ArrayList;

@Service
public class AdminService {

    private final CourseService courseService;

    /* Used for addExamplesToDatabase() method */
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public AdminService(CourseService courseService, StudentRepository studentRepository, CourseRepository courseRepository) {

        this.courseService = courseService;

        /* Used for addExamplesToDatabase() method */
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    /**
     * Adds a course to the database if the course doesn't already exist.
     * @param course course object to add to the database
     * @return the created course in the database
     * @throws CourseAlreadyExistsException if there is another course in the database with the same course code
     */
    public Course addCourse(Course course) throws CourseAlreadyExistsException {
        return courseService.addCourse(course);
    }


    /**
     * Starts and finishes the semester.
     * @param isStarted true to start the semester, false to end the semester
     */
    public void startSemester(boolean isStarted) {
        Semester.setStarted(isStarted);
    }


    /**
     * Returns the status of the semester.
     * @return true if the semester is started/ongoing, false otherwise.
     */
    public boolean getSemesterStatus() {
        return Semester.isStarted();
    }


    /* ADD SOME EXAMPLES TO THE DATABASE */
    public void addExamplesToDatabase() {

        /* Create 10 students */
        for (int i = 1; i < 11; i++) {
            Student student = new Student();
            student.setUserId(i);
            student.setFirstName("Student" + i + " Firstname");
            student.setLastName("Student" + i + " Lastname");
            student.setUsername("student" + i + "username");
            student.setPassword("student" + i + "password");
            student.setStudentId(i * 10);
            student.setDepartment("Computer Engineering");
            student.setCourses(new ArrayList<>());
            student.setAccountRegistered(true);
            studentRepository.save(student);
        }

        /* Create 99 courses */
        for (int i = 300; i < 400; i++) {
            Course course = new Course();
            course.setId(i);
            course.setCourseCode("BBM" + i);
            course.setDepartment("Computer Engineering");
            course.setCourseType(CourseType.UNDERGRADUATE);
            course.setName("Introduction to BBM" + i);
            courseRepository.save(course);
        }

    }

}
