package penguins.backend.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseRepository;
import penguins.backend.Course.CourseService;
import penguins.backend.Course.CourseType;
import penguins.backend.Course.CourseException.CourseAlreadyExistsException;
import penguins.backend.Course.CourseException.CourseNotFoundException;
import penguins.backend.Department.Department;
import penguins.backend.DepartmentManager.DepartmentManager;
import penguins.backend.DepartmentManager.DepartmentManagerRepository;
import penguins.backend.Instructor.Instructor;
import penguins.backend.Instructor.InstructorRepository;
import penguins.backend.Semester.Semester;
import penguins.backend.Semester.SemesterDto;
import penguins.backend.Student.Student;
import penguins.backend.Student.StudentRepository;
import penguins.backend.Student.StudentService;
import penguins.backend.User.UserException.UserNotFoundException;
import penguins.backend.User.UserRepository;
import penguins.backend.User.UserService;
import penguins.backend.User.UserUpdateRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final CourseService courseService;
    private final UserService userService;
    private final StudentService studentService;

    /* Used for addExamplesToDatabase() method */
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private DepartmentManagerRepository departmentManagerRepository;
    @Autowired
    private UserRepository userRepository;

    public AdminService(AdminRepository adminRepository, CourseService courseService, UserService userService,
                        StudentService studentService) {
        this.adminRepository = adminRepository;
        this.courseService = courseService;
        this.userService = userService;
        this.studentService = studentService;
    }


    /**
     * Returns a list of courses in the database.
     * @return ResponseEntity containing a list of courses
     */
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }


    /**
     * Adds a course to the database if the course doesn't already exist.
     * @param course course object to add to the database
     * @return the created course in the database
     * @throws CourseAlreadyExistsException if there is another course in the database with the same course code
     */
    public Course addCourse(Course course) throws CourseAlreadyExistsException {
        return courseService.addCourse(course);
    }


    /**
     * Removes a course from the database.
     * @param courseCode course code for the course to remove from the database
     * @throws CourseNotFoundException if there is no course in the database with the given course code
     */
    public void removeCourse(String courseCode) throws CourseNotFoundException {
        courseService.removeCourse(courseCode);
    }


    /**
     * Starts and finishes the semester.
     * @param isStarted true to start the semester, false to end the semester
     */
    public void startSemester(boolean isStarted) {
        Semester.setSemesterStarted(isStarted);
    }


    /**
     * Starts and finishes the evaluation.
     * @param isStarted true to start, false to end
     */
    public void startEvaluation(boolean isStarted) {
        Semester.setEvaluationStarted(isStarted);
    }


    /**
     * Starts and finishes add or drop.
     * @param isStarted true to start, false to end
     */
    public void startAddOrDrop(boolean isStarted) {
        Semester.setAddOrDropStarted(isStarted);
    }


    /**
     * Returns the semester info.
     * @return SemesterDto
     */
    public SemesterDto getSemester() {
        SemesterDto semesterDto = new SemesterDto();
        semesterDto.setSemesterStarted(Semester.isSemesterStarted());
        semesterDto.setEvaluationStarted(Semester.isEvaluationStarted());
        semesterDto.setAddOrDropStarted(Semester.isAddOrDropStarted());
        return semesterDto;
    }


    /**
     * Updates the attributes of the user.
     * @param userId admin user id
     * @param userUpdateRequest updated user attributes
     * @return updated admin
     */
    public Admin updateAdmin(long userId, UserUpdateRequest userUpdateRequest) throws UserNotFoundException {
        Admin admin = getAdminByUserId(userId);
        userService.updateUser(admin, userUpdateRequest);
        adminRepository.save(admin);
        return admin;
    }

    /**
     * Admits the student.
     * @param userId student user id
     * @param admit true to admit, false to reject
     * @return updated student
     * @throws UserNotFoundException if there is no student with the given id
     */
    public boolean admitStudent(long userId, boolean admit) throws UserNotFoundException {
        return studentService.admitStudent(userId, admit);
    }


    /**
     * Creates the first admin object
     * @return true if new object created, false otherwise
     */
    public boolean createFirstAdmin() {
        if (adminRepository.findAll().size() > 0) {
            return false;
        }
        Admin admin = new Admin();
        admin.setFirstName("Admin Firstname");
        admin.setLastName("Admin Lastname");
        admin.setUsername("admin");
        admin.setPassword("admin-password");
        admin.setUserId(0);
        adminRepository.save(admin);
        userService.saveUser(admin);
        return true;
    }


    /**
     * Finds the admin with the given user id.
     * @param userId admin user id
     * @return the admin with the given user id
     * @throws UserNotFoundException if there is no admin with the given user id
     */
    private Admin getAdminByUserId(long userId) throws UserNotFoundException {
        return adminRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Admin not found. User id: " + userId));
    }


    /* ADD SOME EXAMPLES TO THE DATABASE */
    public void addExamplesToDatabase() {

        Department computerEngineering = new Department();
        computerEngineering.setName("Computer Engineering");

        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.setFirstName("Department Manager Firstname");
        departmentManager.setLastName("Department Manager Lastname");
        departmentManager.setUsername("department-manager-username");
        departmentManager.setPassword("department-manager-password");
        departmentManager.setDepartment(computerEngineering);
        departmentManager.setUserId(500);
        departmentManager.setCourses(new ArrayList<>());
        departmentManagerRepository.save(departmentManager);
        userRepository.save(departmentManager);


        Instructor instructor = new Instructor();
        instructor.setFirstName("Instructor Firstname");
        instructor.setLastName("Instructor Lastname");
        instructor.setDepartment(computerEngineering);
        instructor.setCourses(new ArrayList<>());
        instructor.setUsername("instructor-username");
        instructor.setPassword("instructor-password");
        instructor.setUserId(501);
        instructorRepository.save(instructor);
        userRepository.save(instructor);


        /* Create 10 students */
        for (int i = 1; i < 11; i++) {
            Student student = new Student();
            student.setUserId(i);
            student.setFirstName("Student" + i + " Firstname");
            student.setLastName("Student" + i + " Lastname");
            student.setUsername("student" + i + "username");
            student.setPassword("student" + i + "password");
            student.setStudentId(i * 10);
            student.setDepartment(computerEngineering);
            student.setCourses(new ArrayList<>());
            student.setAccountRegistered(true);
            studentRepository.save(student);
            userRepository.save(student);
        }

        /* Create 99 courses */
        for (int i = 300; i < 400; i++) {
            Course course = new Course();
            course.setId(i);
            course.setCourseCode("BBM" + i);
            course.setDepartment(computerEngineering);
            course.setCourseType(CourseType.UNDERGRADUATE);
            course.setName("Introduction to BBM" + i);
            courseRepository.save(course);
        }

    }

}
