package penguins.backend.Student;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseRepository;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Semester.Exception.SemesterNotStartedException;
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
     * Returns all students in the system.
     * @return a list of student in the database
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }



    /**
     * Returns the student with the given id.
     * @param userId user id of the student
     * @return student object with the given id
     * @throws UserNotFoundException if there is no student with the given id
     */
    public Student getStudentById(long userId) throws UserNotFoundException {
        return studentRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Student not found. User Id: " + userId));
    }


    /**
     * Returns the courses taken by the student with the given id.
     * @param userId user id of the student
     * @return a new ArrayList of Courses taken by the student
     * @throws UserNotFoundException if there is no student with the given id
     */
    public List<Course> getStudentCourses(long userId) throws UserNotFoundException {
        return new ArrayList<>(getStudentById(userId).getCourses());
    }


    /**
     * Adds a course to the list of courses taken by the student, if it doesn't already exist in the list.
     * @param userId user id of the student
     * @param courseCode course code
     * @return updated student
     * @throws UserNotFoundException if there is no student with the given id
     * @throws CourseNotFoundException if there is no course with the given course code
     * @throws SemesterNotStartedException if the semester is not started yet
     */
    public Student enroll(long userId, String courseCode)
            throws UserNotFoundException, CourseNotFoundException, SemesterNotStartedException {

        if (!Semester.isStarted()) throw new SemesterNotStartedException("Semester is not started yet");

        Student student = getStudentById(userId);
        Course course = courseRepository.findByCourseCode(courseCode).orElseThrow(() -> new CourseNotFoundException("Course not found. CourseCode: " + courseCode));

        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            updateStudent(student);
        }
        return student;
    }


    /**
     * Drops a course from the list of courses taken by the student.
     * @param userId user id of the student
     * @param courseCode course code
     * @return updated student
     * @throws UserNotFoundException if there is no student with the given id
     * @throws CourseNotFoundException if there is no course with the given course code.
     * @throws SemesterNotStartedException if the semester is not started yet
     */
    public Student drop(long userId, String courseCode)
            throws UserNotFoundException, CourseNotFoundException, SemesterNotStartedException {

        if (Semester.isStarted()) throw new SemesterOngoingException("Semester is still ongoing");

        Student student = getStudentById(userId);
        Course course = courseRepository.findByCourseCode(courseCode).orElseThrow(() -> new CourseNotFoundException("Course not found. CourseCode: " + courseCode));

        if (student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            updateStudent(student);
        }

        return student;
    }


    /**
     * Drops all the courses taken by the student.
     * @param userId user id of the student
     * @return the updated student with an empty list of courses
     * @throws UserNotFoundException if there is no student with the given id
     * @throws SemesterOngoingException if the semester is still ongoing
     */
    public Student dropAllCourses(long userId) throws UserNotFoundException, SemesterOngoingException {
        if (Semester.isStarted()) {
            throw new SemesterOngoingException("The semester is still ongoing.");
        }
        Student student = getStudentById(userId);
        student.setCourses(new ArrayList<>());
        return updateStudent(student);
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
