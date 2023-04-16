package penguins.backend.Student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Semester.Exception.SemesterNotStartedException;
import penguins.backend.Semester.Exception.SemesterOngoingException;
import penguins.backend.User.Exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    /**
     * Returns a list of all students in the database
     * @return ResponseEntity containing a list of students in the database
     */
    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentDto> output = new ArrayList<>();
        for (Student student : students)
            output.add(studentToStudentDto(student));

        return ResponseEntity.ok(output);
    }

    /**
     * Finds the student with the given id.
     * @param userId user id of the student
     * @return ResponseEntity containing the StudentDto with the given id if it exists
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> getStudentByUserId(@PathVariable long userId) {
        try {
            Student student = studentService.getStudentById(userId);
            StudentDto studentDto = studentToStudentDto(student);
            return ResponseEntity.ok(studentDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }


    /**
     * Finds the courses taken by the student with the given id.
     * @param userId user id of the student
     * @return ResponseEntity containing a list of courses taken by the student with the given id
     */
    @GetMapping(path = "/{userId}/courses")
    public ResponseEntity<?> getStudentCourses(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(studentService.getStudentCourses(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }


    /**
     * Enrolls the student to the course with the given id.
     * @param userId user id of the student
     * @param courseCode course code
     * @return ResponseEntity containing a StudentDto with updated info
     */
    @PostMapping(path = "/{userId}/enroll/{courseCode}")
    public ResponseEntity<?> enroll(@PathVariable long userId, @PathVariable String courseCode) {
        try {
            Student student = studentService.enroll(userId, courseCode);
            StudentDto studentDto = studentToStudentDto(student);
            return ResponseEntity.ok(studentDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        } catch (SemesterNotStartedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Semester is not started yet");
        }
    }


    /**
     * Drops the student from a course with the given id.
     * @param userId user id of the student
     * @param courseCode course code
     * @return ResponseEntity containing a StudentDto with updated info
     */
    @PostMapping(path = "/{userId}/drop/{courseCode}")
    public ResponseEntity<?> drop(@PathVariable long userId, @PathVariable String courseCode) {
        try {
            Student student = studentService.drop(userId, courseCode);
            StudentDto studentDto = studentToStudentDto(student);
            return ResponseEntity.ok(studentDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        } catch (SemesterOngoingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Semester is still ongoing");
        }
    }



    /**
     * Converts a Student object to a StudentDto
     * @param student the Student object
     * @return a StudentDto object created based on the Student object
     */
    private StudentDto studentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();

        studentDto.setUserId(student.getUserId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setUsername(student.getUsername());

        studentDto.setStudentId(student.getStudentId());
        studentDto.setDepartment(student.getDepartment());
        studentDto.setCourses(student.getCourses());
        studentDto.setAccountRegistered(student.isAccountRegistered());

        return studentDto;
    }

}
