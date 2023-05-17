package penguins.backend.Student;

import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseException.CourseNotFoundException;
import penguins.backend.Course.CourseService;
import penguins.backend.Evaluation.CourseEvaluation.CourseEvaluationService;
import penguins.backend.Evaluation.EvaluationException.CourseAlreadyEvaluatedException;
import penguins.backend.Evaluation.EvaluationForm.EvaluationForm;
import penguins.backend.Evaluation.EvaluationForm.EvaluationFormService;
import penguins.backend.Evaluation.EvaluationResponse.EvaluationResponseDto;
import penguins.backend.Semester.SemesterException.AddOrDropFinishedException;
import penguins.backend.Semester.SemesterException.EvaluationNotStartedException;
import penguins.backend.Semester.SemesterException.SemesterNotStartedException;
import penguins.backend.User.UserException.UserNotFoundException;
import penguins.backend.Semester.Semester;
import penguins.backend.User.UserService;
import penguins.backend.User.UserUpdateRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final EvaluationFormService evaluationFormService;
    private final CourseEvaluationService courseEvaluationService;
    private final UserService userService;

    public StudentService(StudentRepository studentRepository,
                          CourseService courseService,
                          EvaluationFormService evaluationFormService,
                          CourseEvaluationService courseEvaluationService,
                          UserService userService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
        this.evaluationFormService = evaluationFormService;
        this.courseEvaluationService = courseEvaluationService;
        this.userService = userService;
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
    public List<Course> enroll(long userId, String courseCode)
            throws UserNotFoundException, CourseNotFoundException, SemesterNotStartedException, AddOrDropFinishedException {

        if (!Semester.isSemesterStarted()) throw new SemesterNotStartedException("Semester is not started yet");
        if (!Semester.isAddOrDropStarted()) throw new AddOrDropFinishedException("Add/Drop is finished or not started yet");

        Student student = getStudentById(userId);
        Course course = courseService.getCourseByCourseCode(courseCode);

        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            updateStudent(student);
        }
        return student.getCourses();
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
    public List<Course> drop(long userId, String courseCode)
            throws UserNotFoundException, CourseNotFoundException, SemesterNotStartedException, AddOrDropFinishedException {

        if (!Semester.isSemesterStarted()) throw new SemesterNotStartedException("Semester is not started yet");
        if (!Semester.isAddOrDropStarted()) throw new AddOrDropFinishedException("Add/Drop is finished or not started yet");

        Student student = getStudentById(userId);
        Course course = courseService.getCourseByCourseCode(courseCode);

        if (student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            updateStudent(student);
        }

        return student.getCourses();
    }


    /**
     * Gets evaluationForms that are not null for the courses taken by the student.
     * @param userId student user id
     * @return EvaluationForms for the courses of the student
     * @throws UserNotFoundException if there is no student with the given id
     * @throws EvaluationNotStartedException if the evaluation is not started yet
     */
    public List<EvaluationForm> getEvaluationForms(long userId) throws UserNotFoundException, EvaluationNotStartedException {
        if (!Semester.isEvaluationStarted()) {
            throw new EvaluationNotStartedException("Evaluation is not started yet.");
        }

        Student student = getStudentById(userId);

        List<EvaluationForm> evaluationForms = new ArrayList<>();
        for (Course course : student.getCourses()) {
            EvaluationForm e = evaluationFormService.getEvaluationForm(course);
            if (e != null) evaluationForms.add(e);
        }
        return evaluationForms;
    }

    
    /**
     * Gets evaluationForm with the given id
     * @param formId evaluation form id
     * @return EvaluationForm with the given id
     * @throws EvaluationNotStartedException if the evaluation is not started yet
     */
    public EvaluationForm getEvaluationForm(long formId) throws EvaluationNotStartedException {
        if (!Semester.isEvaluationStarted()) {
            throw new EvaluationNotStartedException("Evaluation is not started yet.");
        }
        return evaluationFormService.getEvaluationForm(formId);
    }


    /**
     * Evaluates a course and sets the responses
     * @param userId student user id
     * @param courseCode course code
     * @param responses Responses given by the student
     * @throws UserNotFoundException if there is no student with the given id
     * @throws CourseNotFoundException if there is no course with the given course code
     */
    public void evaluate(long userId, String courseCode, List<EvaluationResponseDto> responses)
            throws UserNotFoundException, CourseNotFoundException, CourseAlreadyEvaluatedException {
        Student student = getStudentById(userId);
        Course course = courseService.getCourseByCourseCode(courseCode);
        courseEvaluationService.evaluate(student, course, responses);
    }


    /**
     * Updates the attributes of the user
     * @param userId student user id
     * @param userUpdateRequest updated user attributes
     * @return updated student
     */
    public Student updateStudent(long userId, UserUpdateRequest userUpdateRequest) throws UserNotFoundException {
        Student student = getStudentById(userId);
        userService.updateUser(student, userUpdateRequest);
        studentRepository.save(student);
        return student;
    }


    /**
     * Admits the student.
     * @param userId student user id
     * @param admit true to admit, false to reject
     * @return updated student
     * @throws UserNotFoundException if there is no student with the given id
     */
    public Boolean admitStudent(long userId, boolean admit) throws UserNotFoundException {
        Student student = getStudentById(userId);
        student.setAccountRegistered(admit);
        updateStudent(student);
        userService.saveUser(student);
        return student.isAccountRegistered();
    }


    /**
     * Updates the student in the database.
     * @param student student with updated attributes
     * @return Updated student
     */
    private Student updateStudent(Student student) {
        studentRepository.save(student);
        userService.saveUser(student);
        return student;
    }



}
