package penguins.backend.application.service;

import org.springframework.stereotype.Service;
import penguins.backend.application.port.StudentService;
import penguins.backend.domain.exception.CourseNotFoundException;
import penguins.backend.domain.exception.DuplicateStudentException;
import penguins.backend.domain.exception.StudentNotFoundException;
import penguins.backend.domain.model.Course;
import penguins.backend.domain.model.Student;
import penguins.backend.domain.repository.CourseRepository;
import penguins.backend.domain.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    /**
     * Finds the student with the given id.
     *
     * @param id student id
     * @return the student with the given id
     * @throws StudentNotFoundException if there is no student with the given id
     */
    @Override
    public Student getStudentById(long id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id \"" + id + "\" was not found."));
    }


    /**
     * Finds the student with the given username.
     *
     * @param username student username
     * @return the student with the given username
     * @throws StudentNotFoundException if there is no student with the given username
     */
    @Override
    public Student getStudentByUsername(String username) throws StudentNotFoundException {
        return studentRepository.findByUsername(username).orElseThrow(() -> new StudentNotFoundException("Student with username \"" + username + "\" was not found."));
    }


    /**
     * Saves the given student to the database.
     *
     * @param student the student to save
     * @throws DuplicateStudentException if there is a student with the given id or username in the database
     */
    @Override
    public void saveStudent(Student student) throws DuplicateStudentException {
        if (studentRepository.existsById(student.getId())) {
            throw new DuplicateStudentException("Student with id \"" + student.getId() + "\" already exists.");
        }
        if (studentRepository.existsByUsername(student.getUsername())) {
            throw new DuplicateStudentException("Student with username \"" + student.getUsername() + "\" already exists.");
        }
        studentRepository.save(student);
    }


    /**
     * Deletes the student with the given id from the database.
     *
     * @param id student id
     * @throws StudentNotFoundException if there is no student with the given id
     */
    @Override
    public void deleteStudentById(long id) throws StudentNotFoundException {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id \"" + id + "\" was not found.");
        }
        studentRepository.deleteById(id);
    }


    /**
     * Deletes the student with the given username from the database.
     *
     * @param username student username
     * @throws StudentNotFoundException if there is no student with the given username
     */
    @Override
    public void deleteStudentByUsername(String username) throws StudentNotFoundException {
        if (!studentRepository.existsByUsername(username)) {
            throw new StudentNotFoundException("Student with username \"" + username + "\" was not found.");
        }
        studentRepository.deleteByUsername(username);
    }


    /**
     * Returns a list of all students in the database.
     *
     * @return a list of all students in the database
     */
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }


    /**
     * Adds the course to the list of courses of the student, if the course is not already in the list.
     *
     * @param studentId student id
     * @param courseCode course code
     * @throws StudentNotFoundException if there is no student with the given id
     * @throws CourseNotFoundException if there is no course with the given course code
     */
    @Override
    public void enrollStudentInCourse(long studentId, String courseCode) throws StudentNotFoundException, CourseNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id \"" + studentId + "\" was not found."));
        Course course = courseRepository.findByCode(courseCode).orElseThrow(() -> new CourseNotFoundException("Course with code \"" + courseCode + "\" was not found."));
        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            studentRepository.save(student);
        }
    }


    /**
     * Removes the course from the list of courses of the student, if the course is in the list.
     *
     * @param studentId student id
     * @param courseCode course code
     * @throws StudentNotFoundException if there is no student with the given id
     * @throws CourseNotFoundException if there is no course with the given course code
     */
    @Override
    public void dropStudentFromCourse(long studentId, String courseCode) throws StudentNotFoundException, CourseNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id \"" + studentId + "\" was not found."));
        Course course = courseRepository.findByCode(courseCode).orElseThrow(() -> new CourseNotFoundException("Course with code \"" + courseCode + "\" was not found."));
        if (student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            studentRepository.save(student);
        }
    }


    /**
     * Returns a list of the courses taken by the student.
     *
     * @param id student id
     * @return list of the courses taken by the student
     * @throws StudentNotFoundException if there is no student with the given id
     */
    @Override
    public List<Course> getStudentCourses(long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id \"" + id + "\" was not found."));
        return new ArrayList<>(student.getCourses());
    }


}
