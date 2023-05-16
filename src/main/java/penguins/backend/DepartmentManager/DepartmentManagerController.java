package penguins.backend.DepartmentManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penguins.backend.Course.Course;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Department.Department;
import penguins.backend.Department.DepartmentException.DifferentDepartmentException;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.Instructor.Instructor;
import penguins.backend.User.Exception.UserNotFoundException;
import penguins.backend.User.UserUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/department-managers/{userId}")
public class DepartmentManagerController {
    private final DepartmentManagerService departmentManagerService;

    public DepartmentManagerController(DepartmentManagerService departmentManagerService) {
        this.departmentManagerService = departmentManagerService;
    }


    /**
     * Finds department manager with the given id.
     * @param userId user id
     * @return ResponseEntity containing the DepartmentManagerDto with the given id if it exists
     */
    @GetMapping(path = "")
    public ResponseEntity<?> getDepartmentManagerByUserId(@PathVariable long userId) {
        try {
            DepartmentManager departmentManager = departmentManagerService.getDepartmentManager(userId);
            DepartmentManagerDto departmentManagerDto = departmentManagerToDepartmentManagerDto(departmentManager);
            return ResponseEntity.ok(departmentManagerDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department manager not found");
        }
    }


    /**
     * Assigns an instructor to a course
     * @param userId user id of the department manager
     * @param courseCode course code
     * @param instructor the instructor
     * @return the updated course
     */
    @PostMapping(path = "/assign/{courseCode}")
    public ResponseEntity<?> assignInstructor(@PathVariable long userId,
                                              @PathVariable String courseCode,
                                              @RequestBody Instructor instructor) {
        try {
            Course course = departmentManagerService.assignInstructor(userId, courseCode, instructor.getUserId());
            return ResponseEntity.ok(course);
        } catch (CourseNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DifferentDepartmentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /**
     * Adds a default evaluation question to the list of questions for the department
     * @param userId Department manager id
     * @param evaluationQuestion The new evaluation question with given text
     * @return The updated list of evaluation questions for the department
     */
    @PostMapping(path = "/add-evaluation-question")
    public ResponseEntity<?> addEvaluationQuestion(@PathVariable long userId, @RequestBody EvaluationQuestion evaluationQuestion) {

        try {
            List<EvaluationQuestion> output = departmentManagerService.addEvaluationQuestion(userId, evaluationQuestion.getQuestionText());
            return ResponseEntity.status(HttpStatus.CREATED).body(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department manager not found. User id: " + userId);
        }

    }


    /**
     * Removes a default evaluation question from the list of questions for the department
     * @param userId Department manager id
     * @param evaluationQuestion The evaluation question with given id
     * @return The updated list of evaluation questions for the department
     */
    @PostMapping(path = "/remove-evaluation-question")
    public ResponseEntity<?> removeEvaluationQuestion(@PathVariable long userId, @RequestBody EvaluationQuestion evaluationQuestion) {

        try {
            List<EvaluationQuestion> output = departmentManagerService.removeEvaluationQuestion(userId, evaluationQuestion.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department manager not found. User id: " + userId);
        }

    }


    /**
     * Finds all instructors in the department
     * @param userId department manager user id
     * @return List of instructors in the department
     */
    @GetMapping(path = "/instructors")
    public ResponseEntity<List<Instructor>> getInstructors(@PathVariable long userId) {
        try {
            List<Instructor> output = departmentManagerService.getInstructors(userId);
            return ResponseEntity.ok(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    /**
     * Finds all courses in the department
     * @param userId department manager user id
     * @return List of courses in the department
     */
    @GetMapping(path = "/courses")
    public ResponseEntity<List<Course>> getCourses(@PathVariable long userId) {
        try {
            List<Course> output = departmentManagerService.getCourses(userId);
            return ResponseEntity.ok(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    /**
     * Finds the department information of the department manager
     * @param userId department manager user id
     * @return department object of the department manager
     */
    @GetMapping(path = "/department")
    public ResponseEntity<Department> getDepartment(@PathVariable long userId) {
        try {
            Department output = departmentManagerService.getDepartment(userId);
            return ResponseEntity.ok(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    /**
     * Updates the attributes of the user
     * @param userId department manager user id
     * @param userUpdateRequest updated user attributes
     * @return updated department manager
     */
    @PostMapping(path = "/update-info")
    public ResponseEntity<?> updateStudent(@PathVariable long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            DepartmentManager departmentManager = departmentManagerService.updateStudent(userId, userUpdateRequest);
            DepartmentManagerDto departmentManagerDto = departmentManagerToDepartmentManagerDto(departmentManager);
            return ResponseEntity.ok(departmentManagerDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    /**
     * Converts a DepartmentManager object to a DepartmentManagerDto
     * @param departmentManager departmentManager object
     * @return a DepartmentManagerDto object created based on the DepartmentManager object
     */
    private DepartmentManagerDto departmentManagerToDepartmentManagerDto(DepartmentManager departmentManager) {
        DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
        departmentManagerDto.setFirstName(departmentManager.getFirstName());
        departmentManagerDto.setLastName(departmentManager.getLastName());
        departmentManagerDto.setDepartment(departmentManager.getDepartment());
        departmentManagerDto.setCourses(departmentManager.getCourses());
        departmentManagerDto.setUsername(departmentManager.getUsername());
        departmentManagerDto.setUserId(departmentManager.getUserId());
        return departmentManagerDto;
    }


}
