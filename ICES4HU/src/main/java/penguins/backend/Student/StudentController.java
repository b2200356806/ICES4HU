package penguins.backend.Student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penguins.backend.Course.Course;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Evaluation.EvaluationException.CourseAlreadyEvaluatedException;
import penguins.backend.Evaluation.EvaluationForm.EvaluationForm;
import penguins.backend.Evaluation.EvaluationResponse.EvaluationResponseDto;
import penguins.backend.Semester.SemesterException.EvaluationNotStartedException;
import penguins.backend.Semester.Exception.SemesterNotStartedException;
import penguins.backend.Semester.SemesterException.AddOrDropFinishedException;
import penguins.backend.User.Exception.UserNotFoundException;
import penguins.backend.User.UserUpdateRequest;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/students/{userId}")
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
    @GetMapping(path = "")
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
    @GetMapping(path = "/courses")
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
     * @return ResponseEntity containing the updated list of courses
     */
    @PostMapping(path = "/courses/enroll/{courseCode}")
    public ResponseEntity<?> enroll(@PathVariable long userId, @PathVariable String courseCode) {
        try {
            List<Course> courses = studentService.enroll(userId, courseCode);
            return ResponseEntity.ok(courses);
        } catch (UserNotFoundException | CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SemesterNotStartedException | AddOrDropFinishedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /**
     * Drops the student from a course with the given id.
     * @param userId user id of the student
     * @param courseCode course code
     * @return ResponseEntity containing the updated list of courses
     */
    @PostMapping(path = "/courses/drop/{courseCode}")
    public ResponseEntity<?> drop(@PathVariable long userId, @PathVariable String courseCode) {
        try {
            List<Course> courses = studentService.drop(userId, courseCode);
            return ResponseEntity.ok(courses);
        } catch (UserNotFoundException | CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SemesterNotStartedException | AddOrDropFinishedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /**
     * Gets evaluationForms that are not null for the courses taken by the student.
     * @param userId student user id
     * @return EvaluationForms for the courses of the student
     */
    @GetMapping(path = "/evaluation")
    public ResponseEntity<?> getEvaluationForms(@PathVariable long userId) {
        try {
            List<EvaluationForm> output = studentService.getEvaluationForms(userId);
            return ResponseEntity.ok(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EvaluationNotStartedException | CourseAlreadyEvaluatedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /**
     * Gets an evaluationForm with the given id.
     * @param evaluationFormId evaluation form id
     * @return EvaluationForm with the given id.
     */
    @GetMapping(path = "/evaluation/{evaluationFormId}")
    public ResponseEntity<?> getEvaluationFormForCourse(@PathVariable long evaluationFormId) {
        try {
            EvaluationForm output = studentService.getEvaluationForm(evaluationFormId);
            return ResponseEntity.ok(output);
        } catch (EvaluationNotStartedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    /**
     * Evaluates a course.
     * @param userId student user id
     * @param courseCode course code
     * @param responses Responses given by the student
     */
    @PostMapping(path = "/evaluation/{courseCode}")
    public ResponseEntity<String> evaluate(@PathVariable long userId, @PathVariable String courseCode,
                                           @RequestBody List<EvaluationResponseDto> responses) {

        try {
            studentService.evaluate(userId, courseCode, responses);
            return ResponseEntity.ok("Course evaluated successfully");
        } catch (UserNotFoundException | CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (CourseAlreadyEvaluatedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    /**
     * Updates the attributes of the user
     * @param userId student user id
     * @param userUpdateRequest updated user attributes
     * @return updated student
     */
    @PostMapping(path = "/update-info")
    public ResponseEntity<?> updateStudent(@PathVariable long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            Student student = studentService.updateStudent(userId, userUpdateRequest);
            StudentDto studentDto = studentToStudentDto(student);
            return ResponseEntity.ok(studentDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
