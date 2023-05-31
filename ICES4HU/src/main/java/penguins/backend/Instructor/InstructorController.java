package penguins.backend.Instructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penguins.backend.Course.Course;
import penguins.backend.Evaluation.CourseEvaluation.CourseEvaluationDto;
import penguins.backend.Evaluation.EvaluationForm.EvaluationForm;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.User.Exception.UserNotFoundException;
import penguins.backend.User.UserUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/instructors/{userId}")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    /**
     * Finds and returns the instructorDto object with the given id.
     * @param userId instructor id
     * @return instructorDto object with the given user id
     */
    @GetMapping(path = "")
    public ResponseEntity<?> getInstructorByUserId(@PathVariable long userId) {
        try {
            Instructor instructor = instructorService.getInstructorByUserId(userId);
            InstructorDto instructorDto = instructorToInstructorDto(instructor);
            return ResponseEntity.ok(instructorDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /**
     * Returns a list of courses of the instructor.
     * @param userId instructor user id
     * @return list of courses of the instructor
     */
    @GetMapping(path = "/courses")
    public ResponseEntity<?> getCourses(@PathVariable long userId) {
        try {
            List<Course> output = instructorService.getCourses(userId);
            return ResponseEntity.ok(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /**
     * Creates evaluation forms for the courses of the instructor if they don't already exist. Otherwise,
     * it returns the existing evaluation forms.
     * @param userId instructor id
     * @return List of evaluation forms for the courses of the instructor
     */
    @GetMapping(path = "/evaluation")
    public ResponseEntity<List<EvaluationForm>> createOrGetEvaluationForms(@PathVariable long userId) {
        try {
            List<EvaluationForm> output = instructorService.createOrGetEvaluationForms(userId);
            return ResponseEntity.ok(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    /**
     * Finds the evaluation form using the given id
     * @param evaluationFormId evaluation form id
     * @return Evaluation form object with the given id
     */
    @GetMapping(path = "/evaluation/{evaluationFormId}")
    public ResponseEntity<EvaluationForm> getEvaluationForm(@PathVariable long evaluationFormId) {
        EvaluationForm evaluationForm = instructorService.getEvaluationForm(evaluationFormId);
        return ResponseEntity.ok(evaluationForm);
    }


    /**
     * Adds an evaluationQuestion to the evaluationForm for the given course.
     * @param evaluationFormId The evaluationForm id for which the question is being created
     * @param evaluationQuestion EvaluationQuestion with given text
     * @return The evaluationForm for the course with the new question
     */
    @PostMapping(path = "/evaluation/{evaluationFormId}/add-question")
    public ResponseEntity<EvaluationForm> addEvaluationQuestion(@PathVariable long evaluationFormId, @RequestBody EvaluationQuestion evaluationQuestion) {
        EvaluationForm evaluationForm = instructorService.addEvaluationQuestion(evaluationFormId, evaluationQuestion.getQuestionText());
        return ResponseEntity.ok(evaluationForm);
    }


    /**
     * Removes the evaluationQuestion from the given evaluationForm.
     * @param evaluationFormId Evaluation form id
     * @param evaluationQuestion Question with the given id
     * @return the updated evaluation form
     */
    @PostMapping(path = "/evaluation/{evaluationFormId}/remove-question")
    public ResponseEntity<EvaluationForm> removeEvaluationQuestion(@PathVariable long evaluationFormId, @RequestBody EvaluationQuestion evaluationQuestion) {
        EvaluationForm evaluationForm = instructorService.removeEvaluationQuestion(evaluationFormId, evaluationQuestion.getId());
        return ResponseEntity.ok(evaluationForm);
    }


    /**
     * Returns a courseEvaluationDto based on the courseEvaluations for the course
     * @param courseCode course code
     * @return courseEvaluationDto based on the courseEvaluations for the course
     */
    @GetMapping(path = "/evaluation/result/{courseCode}")
    public ResponseEntity<CourseEvaluationDto> getEvaluationResult(@PathVariable String courseCode) {
        CourseEvaluationDto courseEvaluationDto = instructorService.getEvaluationResult(courseCode);
        return ResponseEntity.ok(courseEvaluationDto);
    }



    /**
     * Updates the attributes of the user
     * @param userId instructor user id
     * @param userUpdateRequest updated user attributes
     * @return updated instructor
     */
    @PostMapping(path = "/update-info")
    public ResponseEntity<?> updateInstructor(@PathVariable long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            Instructor instructor = instructorService.updateInstructor(userId, userUpdateRequest);
            InstructorDto instructorDto = instructorToInstructorDto(instructor);
            return ResponseEntity.ok(instructorDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    /**
     * Converts an Instructor object to an InstructorDto
     * @param instructor the Instructor object
     * @return an InstructorDto object created based on the Instructor object
     */
    private InstructorDto instructorToInstructorDto(Instructor instructor) {
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setFirstName(instructor.getFirstName());
        instructorDto.setLastName(instructor.getLastName());
        instructorDto.setUsername(instructor.getUsername());
        instructorDto.setUserId(instructor.getUserId());
        instructorDto.setCourses(instructor.getCourses());
        instructorDto.setDepartment(instructor.getDepartment());
        return instructorDto;
    }

}
