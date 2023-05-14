package penguins.backend.Department;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseException.CourseNotFoundException;
import penguins.backend.Course.CourseService;
import penguins.backend.Department.DepartmentException.DifferentDepartmentException;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestion;
import penguins.backend.Evaluation.EvaluationQuestion.EvaluationQuestionService;
import penguins.backend.Instructor.Instructor;
import penguins.backend.Instructor.InstructorService;
import penguins.backend.User.UserException.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final CourseService courseService;
    private final InstructorService instructorService;
    private final EvaluationQuestionService evaluationQuestionService;

    public DepartmentService(DepartmentRepository departmentRepository,
                             CourseService courseService,
                             InstructorService instructorService,
                             EvaluationQuestionService evaluationQuestionService) {

        this.departmentRepository = departmentRepository;
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.evaluationQuestionService = evaluationQuestionService;
    }


    /**
     * Assigns an instructor to a course
     * @param courseCode course code
     * @param instructorId user id of the instructor
     * @return the updated course
     * @throws CourseNotFoundException if there is no course with the given course code
     * @throws UserNotFoundException if there is no instructor with the given user id
     */
    public Course assignInstructor(Department department, String courseCode, long instructorId)
            throws CourseNotFoundException, UserNotFoundException, DifferentDepartmentException {

        Course course = courseService.getCourseByCourseCode(courseCode);
        Instructor instructor = instructorService.getInstructorByUserId(instructorId);

        if (!department.equals(course.getDepartment())) {
            throw new DifferentDepartmentException("Department manager has a different department than the course");
        }

        if (!department.equals(instructor.getDepartment())) {
            throw new DifferentDepartmentException("Department manager has a different department than the instructor");
        }

        instructor.getCourses().add(course);
        instructorService.updateStudent(instructor);
        return course;
    }


    /**
     * Adds a default evaluation question to the list of questions for the department
     * @param department Department to add the evaluation question to
     * @param questionText The new evaluation question
     * @return The updated list of evaluation questions for the department
     * @throws UserNotFoundException if there is no department manager with the given user id
     */
    public List<EvaluationQuestion> addEvaluationQuestion(Department department, String questionText) {
        EvaluationQuestion evaluationQuestion = evaluationQuestionService.createEvaluationQuestion(questionText);
        if (department.getDefaultEvaluationQuestions() == null) {
            department.setDefaultEvaluationQuestions(new ArrayList<>());
        }
        department.getDefaultEvaluationQuestions().add(evaluationQuestion);
        updateDepartment(department);
        return department.getDefaultEvaluationQuestions();
    }



    /**
     * Removes a default evaluation question from the list of questions for the department
     * @param department Department to add the evaluation question to
     * @param questionId The evaluation question id
     * @return The updated list of evaluation questions for the department
     * @throws UserNotFoundException if there is no department manager with the given user id
     */
    public List<EvaluationQuestion> removeEvaluationQuestion(Department department, long questionId) {

        if (department.getDefaultEvaluationQuestions() != null) {
            department.getDefaultEvaluationQuestions().removeIf(evaluationQuestion -> evaluationQuestion.getId() == questionId);
            updateDepartment(department);
        }

        evaluationQuestionService.removeEvaluationQuestion(questionId);
        return department.getDefaultEvaluationQuestions();
    }


    /**
     * Finds all instructors in the department
     * @param department department
     * @return list of all instructors in the department
     * @throws UserNotFoundException if there is no department manager with the given id
     */
    public List<Instructor> getInstructorsByDepartment(Department department) {
        return instructorService.getInstructorsByDepartment(department);
    }


    /**
     * Finds all courses in the department
     * @param department department
     * @return list of all courses in the department
     * @throws UserNotFoundException if there is no department manager with the given id
     */
    public List<Course> getCoursesByDepartment(Department department) {
        return courseService.getCoursesByDepartment(department);
    }


    /**
     * Updates a department in the database
     * @param department department object with updated attributes
     * @return updated department
     */
    private Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
