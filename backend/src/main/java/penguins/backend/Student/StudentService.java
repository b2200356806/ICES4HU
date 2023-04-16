package penguins.backend.Student;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseRepository;
import penguins.backend.Course.Exception.CourseAlreadyExistsException;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Semester.Exception.CourseRegistrationException;
import penguins.backend.Semester.Exception.SemesterOngoingException;
import penguins.backend.User.Exception.UserNotFoundException;
import penguins.backend.Semester.Semester;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    /**
     * Returns the courses taken by the student with the given id.
     * @param studentId student id
     * @return a new ArrayList of Courses taken by the student
     * @throws UserNotFoundException if there is no student with the given id
     */
    public List<Course> getStudentCourses(long studentId) throws UserNotFoundException {
        return new ArrayList<>(getStudentById(studentId).getCourses());
    }


    /**
     * Adds a course to the list of courses taken by the student, if it doesn't already exist in the list.
     * @param studentId student id
     * @param courseCode course code
     * @return updated student
     * @throws UserNotFoundException if there is no student with the given id
     * @throws CourseNotFoundException if there is no course with the given course code.
     * @throws CourseRegistrationException if it is not the course registration week.
     */
    public Student enroll(long studentId, String courseCode)
            throws UserNotFoundException, CourseNotFoundException, CourseRegistrationException {

        if (!Semester.isCourseRegistration()) throw new CourseRegistrationException("It is not the registration week");

        Student student = getStudentById(studentId);
        Course course = courseRepository.findByCourseCode(courseCode).orElseThrow(() -> new CourseNotFoundException("Course not found. CourseCode: " + courseCode));

        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            updateStudent(student);
        }
        return student;
    }


    /**
     * Drops a course from the list of courses taken by the student.
     * @param studentId student id
     * @param courseCode course code
     * @return updated student
     * @throws UserNotFoundException if there is no student with the given id
     * @throws CourseNotFoundException if there is no course with the given course code.
     * @throws CourseRegistrationException if it is not the course registration week.
     */
    public Student drop(long studentId, String courseCode)
            throws UserNotFoundException, CourseNotFoundException, CourseRegistrationException {

        if (!Semester.isCourseRegistration()) throw new CourseRegistrationException("It is not the registration week");

        Student student = getStudentById(studentId);
        Course course = courseRepository.findByCourseCode(courseCode).orElseThrow(() -> new CourseNotFoundException("Course not found. CourseCode: " + courseCode));

        if (student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            updateStudent(student);
        }

        return student;
    }


    /**
     * Drops all the courses taken by the student.
     * @param studentId student id
     * @return the updated student with an empty list of courses
     * @throws UserNotFoundException if there is no student with the given id
     * @throws SemesterOngoingException if the semester is ongoing and course registration week is over
     */
    public Student dropAllCourses(long studentId) throws UserNotFoundException, SemesterOngoingException {
        if (Semester.isStarted() && !Semester.isCourseRegistration()) {
            throw new SemesterOngoingException("The semester is still ongoing.");
        }
        Student student = getStudentById(studentId);
        student.setCourses(new ArrayList<>());
        return updateStudent(student);
    }


    /**
     * Returns the student with the given id.
     * @param id student id
     * @return student object with the given id
     * @throws UserNotFoundException if there is no student with the given id
     */
    private Student getStudentById(long id) throws UserNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Student not found. Id: " + id));
    }


    /**
     * Updates the student in the database.
     * @param student student with updated attributes
     * @return Updated student
     */
    private Student updateStudent(Student student) {
        return studentRepository.save(student);
    }



}
