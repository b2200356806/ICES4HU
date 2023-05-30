package penguins.backend.DepartmentManager;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Department.Department;
import penguins.backend.Department.DepartmentException.DifferentDepartmentException;
import penguins.backend.Department.DepartmentService;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.Instructor.Instructor;
import penguins.backend.User.UserException.UserNotFoundException;
import penguins.backend.User.UserService;
import penguins.backend.User.UserUpdateRequest;

import java.util.List;

@Service
public class DepartmentManagerService {
    private final DepartmentManagerRepository departmentManagerRepository;
    private final DepartmentService departmentService;
    private final UserService userService;


    public DepartmentManagerService(DepartmentManagerRepository departmentManagerRepository,
                                    DepartmentService departmentService,
                                    UserService userService) {
        this.departmentManagerRepository = departmentManagerRepository;
        this.departmentService = departmentService;
        this.userService = userService;
    }


    /**
     * Assigns an instructor to a course
     * @param departmentManagerUserId user id of the department manager
     * @param courseCode course code
     * @param instructorId user id of the instructor
     * @return the updated course
     * @throws CourseNotFoundException if there is no course with the given course code
     * @throws UserNotFoundException if there is no instructor with the given user id
     * @throws DifferentDepartmentException if department manager has a different department than the course or instructor
     */
    public Course assignInstructor(long departmentManagerUserId, String courseCode, long instructorId)
            throws CourseNotFoundException, UserNotFoundException, DifferentDepartmentException {

        DepartmentManager departmentManager = getDepartmentManager(departmentManagerUserId);
        return departmentService.assignInstructor(departmentManager.getDepartment(), courseCode, instructorId);
    }


    /**
     * Adds a default evaluation question to the list of questions for the department
     * @param departmentManagerId Department manager id
     * @param questionText The new evaluation question
     * @return The updated list of evaluation questions for the department
     * @throws UserNotFoundException if there is no department manager with the given user id
     */
    public List<EvaluationQuestion> addEvaluationQuestion(long departmentManagerId,
                                                          String questionText) throws UserNotFoundException {
        DepartmentManager departmentManager = getDepartmentManager(departmentManagerId);
        return departmentService.addEvaluationQuestion(departmentManager.getDepartment(), questionText);
    }


    /**
     * Removes a default evaluation question from the list of questions for the department
     * @param departmentManagerId Department manager id
     * @param questionId The evaluation question id
     * @return The updated list of evaluation questions for the department
     * @throws UserNotFoundException if there is no department manager with the given user id
     */
    public List<EvaluationQuestion> removeEvaluationQuestion(long departmentManagerId,
                                                             long questionId) throws UserNotFoundException {
        DepartmentManager departmentManager = getDepartmentManager(departmentManagerId);
        return departmentService.removeEvaluationQuestion(departmentManager.getDepartment(), questionId);
    }


    /**
     * Finds all instructors in the department
     * @param departmentManagerId department manager id
     * @return list of all instructors in the department
     * @throws UserNotFoundException if there is no department manager with the given id
     */
    public List<Instructor> getInstructors(long departmentManagerId) throws UserNotFoundException {
        DepartmentManager departmentManager = getDepartmentManager(departmentManagerId);
        return departmentService.getInstructorsByDepartment(departmentManager.getDepartment());
    }


    /**
     * Finds all courses in the department
     * @param departmentManagerId department manager id
     * @return list of all courses in the department
     * @throws UserNotFoundException if there is no department manager with the given id
     */
    public List<Course> getCourses(long departmentManagerId) throws UserNotFoundException {
        DepartmentManager departmentManager = getDepartmentManager(departmentManagerId);
        return departmentService.getCoursesByDepartment(departmentManager.getDepartment());
    }


    /**
     * Returns the department manager's department object
     * @param departmentManagerId department manager user id
     * @return department of the department manager
     * @throws UserNotFoundException if there is no department manager with the given user id
     */
    public Department getDepartment(long departmentManagerId) throws UserNotFoundException {
        DepartmentManager departmentManager = getDepartmentManager(departmentManagerId);
        return departmentManager.getDepartment();
    }


    /**
     * Finds and returns the department manager with the given userId.
     * @param departmentManagerId department manager user id
     * @return Department manager
     * @throws UserNotFoundException if there is no department manager with the given user id
     */
    public DepartmentManager getDepartmentManager(long departmentManagerId) throws UserNotFoundException {

        return departmentManagerRepository.findByUserId(departmentManagerId)
                .orElseThrow(() -> new UserNotFoundException("Department manager not found. User id: " + departmentManagerId));

    }


    /**
     * Updates the attributes of the user
     * @param userId department manager user id
     * @param userUpdateRequest updated user attributes
     * @return updated department manager
     */
    public DepartmentManager updateDepartmentManager(long userId, UserUpdateRequest userUpdateRequest) throws UserNotFoundException {
        DepartmentManager departmentManager = getDepartmentManager(userId);
        userService.updateUser(departmentManager, userUpdateRequest);
        departmentManagerRepository.save(departmentManager);
        return departmentManager;
    }


    /**
     * Saves the department manager.
     * @param departmentManager Department manager to save
     * @return the saved department manager
     */
    public DepartmentManager saveDepartmentManager(DepartmentManager departmentManager) {
        return departmentManagerRepository.save(departmentManager);
    }

}
