package penguins.backend.Instructor;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseService;
import penguins.backend.Department.Department;
import penguins.backend.Evaluation.CourseEvaluation.CourseEvaluation;
import penguins.backend.Evaluation.CourseEvaluation.CourseEvaluationDto;
import penguins.backend.Evaluation.CourseEvaluation.CourseEvaluationService;
import penguins.backend.Evaluation.EvaluationForm.EvaluationForm;
import penguins.backend.Evaluation.EvaluationForm.EvaluationFormService;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.Evaluation.EvaluationResponse.EvaluationResponse;
import penguins.backend.User.UserException.UserNotFoundException;
import penguins.backend.User.UserService;
import penguins.backend.User.UserUpdateRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseService courseService;
    private final EvaluationFormService evaluationFormService;
    private final CourseEvaluationService courseEvaluationService;
    private final UserService userService;

    public InstructorService(InstructorRepository instructorRepository,
                             CourseService courseService,
                             EvaluationFormService evaluationFormService,
                             CourseEvaluationService courseEvaluationService,
                             UserService userService) {
        this.instructorRepository = instructorRepository;
        this.courseService = courseService;
        this.evaluationFormService = evaluationFormService;
        this.courseEvaluationService = courseEvaluationService;
        this.userService = userService;
    }


    /**
     * Returns a list of courses of the instructor.
     *
     * @param instructorId instructor user id
     * @return list of courses of the instructor
     * @throws UserNotFoundException if there is no instructor with the given id
     */
    public List<Course> getCourses(long instructorId) throws UserNotFoundException {
        Instructor instructor = getInstructorByUserId(instructorId);
        return new ArrayList<>(instructor.getCourses());
    }


    /**
     * Creates evaluation forms for the courses of the instructor if they don't already exist. Otherwise,
     * it returns the existing evaluation forms.
     *
     * @param instructorId instructor id
     * @return List of evaluation forms for the courses of the instructor
     * @throws UserNotFoundException if there is no instructor with the given id
     */
    public List<EvaluationForm> createOrGetEvaluationForms(long instructorId) throws UserNotFoundException {
        Instructor instructor = getInstructorByUserId(instructorId);
        List<EvaluationForm> output = new ArrayList<>();
        for (Course course : instructor.getCourses()) {
            output.add(evaluationFormService.createOrGetEvaluationForm(course));
        }

        return output;
    }


    /**
     * Finds the evaluation form using the given id
     *
     * @param evaluationFormId evaluation form id
     * @return Evaluation form object with the given id
     */
    public EvaluationForm getEvaluationForm(long evaluationFormId) {
        return evaluationFormService.getEvaluationForm(evaluationFormId);
    }


    /**
     * Adds an evaluationQuestion to the evaluationForm for the given course.
     *
     * @param evaluationFormId The evaluationForm id for which the question is being created
     * @param questionText     EvaluationQuestion text
     * @return The evaluationForm for the course with the new question
     */
    public EvaluationForm addEvaluationQuestion(long evaluationFormId, String questionText) {
        return evaluationFormService.addEvaluationQuestion(evaluationFormId, questionText);
    }


    /**
     * Removes the evaluationQuestion from the given evaluationForm.
     *
     * @param evaluationFormId Evaluation form id
     * @param questionId       Question id
     * @return the updated evaluation form
     */
    public EvaluationForm removeEvaluationQuestion(long evaluationFormId, long questionId) {
        return evaluationFormService.removeEvaluationQuestion(evaluationFormId, questionId);
    }


    /**
     * Finds and returns the instructor object with the given id.
     *
     * @param instructorId instructor id
     * @return instructor object with the given user id
     * @throws UserNotFoundException if there is no instructor with the given id
     */
    public Instructor getInstructorByUserId(long instructorId) throws UserNotFoundException {
        return instructorRepository.findByUserId(instructorId)
                .orElseThrow(() -> new UserNotFoundException("Instructor not found. User id: " + instructorId));
    }


    /**
     * Finds and returns the instructors in the given department.
     *
     * @param department instructor department
     * @return instructors in the given department
     */
    public List<Instructor> getInstructorsByDepartment(Department department) {
        return new ArrayList<>(instructorRepository.findByDepartment(department));
    }


    /**
     * Returns a courseEvaluationDto based on the courseEvaluations for the course
     *
     * @param courseCode course code
     * @return courseEvaluationDto based on the courseEvaluations for the course
     */
    public CourseEvaluationDto getEvaluationResult(String courseCode) {
        Course course = courseService.getCourseByCourseCode(courseCode);
        List<CourseEvaluation> courseEvaluations = courseEvaluationService.getCourseEvaluations(course);
        CourseEvaluationDto courseEvaluationDto = new CourseEvaluationDto();
        courseEvaluationDto.setCourse(course);

        Map<EvaluationQuestion, List<Integer>> responses = new HashMap<>();

        for (CourseEvaluation courseEvaluation : courseEvaluations) {
            for (EvaluationResponse evaluationResponse : courseEvaluation.getResponses()) {
                EvaluationQuestion evaluationQuestion = evaluationResponse.getEvaluationQuestion();
                responses.computeIfAbsent(evaluationQuestion, k -> new ArrayList<>());
                responses.get(evaluationQuestion).add(evaluationResponse.getResponse());
            }
        }

        courseEvaluationDto.setResponses(responses);
        return courseEvaluationDto;
    }


    /**
     * Updates the attributes of the user
     *
     * @param userId            instructor user id
     * @param userUpdateRequest updated user attributes
     * @return updated instructor
     */
    public Instructor updateInstructor(long userId, UserUpdateRequest userUpdateRequest) throws UserNotFoundException {
        Instructor instructor = getInstructorByUserId(userId);
        userService.updateUser(instructor, userUpdateRequest);
        instructorRepository.save(instructor);
        return instructor;
    }

    /**
     * Updates an instructor
     *
     * @param instructor instructor with updated attributes
     * @return the updated instructor
     */
    public Instructor updateStudent(Instructor instructor) {
        instructorRepository.save(instructor);
        userService.saveUser(instructor);
        return instructor;
    }

    /**
     * Saves the instructor.
     *
     * @param instructor Instructor to save
     * @return the saved instructor
     */
    public Instructor saveInstructor(Instructor instructor) {
        userService.saveUser(instructor);
        return instructorRepository.save(instructor);
    }


    /**
     * Finds the instructor with username.
     *
     * @param username instructor username
     * @return the instructor with the given username
     */
    public Instructor findInstructorByUsername(String username) {
        return instructorRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Instructor not found. Username: " + username));
    }


    /**
     * Finds a list of all instructors in the system.
     *
     * @return a list of instructors that are in the database
     */
    public List<Instructor> getAllInstructors() {
        return new ArrayList<>(instructorRepository.findAll());
    }

}
