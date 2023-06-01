package penguins.backend.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import penguins.backend.Course.Course;
import penguins.backend.Course.CourseRepository;
import penguins.backend.Course.CourseService;
import penguins.backend.Course.CourseType;
import penguins.backend.Course.Exception.CourseAlreadyExistsException;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Department.Department;
import penguins.backend.Department.DepartmentService;
import penguins.backend.DepartmentManager.DepartmentManager;
import penguins.backend.DepartmentManager.DepartmentManagerRepository;
import penguins.backend.DepartmentManager.DepartmentManagerService;
import penguins.backend.Instructor.Instructor;
import penguins.backend.Instructor.InstructorRegisterRequest;
import penguins.backend.Instructor.InstructorRepository;
import penguins.backend.Instructor.InstructorService;
import penguins.backend.Semester.Semester;
import penguins.backend.Semester.SemesterDto;
import penguins.backend.Student.Student;
import penguins.backend.Student.StudentRepository;
import penguins.backend.Student.StudentService;
import penguins.backend.User.UserException.UserExistsException;
import penguins.backend.User.UserException.UserNotFoundException;
import penguins.backend.User.UserRepository;
import penguins.backend.User.UserService;
import penguins.backend.User.UserUpdateRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private DepartmentManagerService departmentManagerService;

    /* Used for addExamplesToDatabase() method */
    @Autowired
    private AdminRepository adminRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private DepartmentManagerRepository departmentManagerRepository;
    @Autowired
    private UserRepository userRepository;

    public AdminService(AdminRepository adminRepository, CourseService courseService, UserService userService,
                        StudentService studentService, DepartmentService departmentService,
                        DepartmentManagerService departmentManagerService, InstructorService instructorService) {
        this.adminRepository = adminRepository;
        this.courseService = courseService;
        this.userService = userService;
        this.studentService = studentService;
        this.departmentService = departmentService;
        this.departmentManagerService = departmentManagerService;
        this.instructorService = instructorService;
    }


    /**
     * Returns a list of courses in the database.
     *
     * @return ResponseEntity containing a list of courses
     */
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }


    /**
     * Adds a course to the database if the course doesn't already exist.
     *
     * @param course course object to add to the database
     * @return the created course in the database
     * @throws CourseAlreadyExistsException if there is another course in the database with the same course code
     */
    public Course addCourse(Course course) throws CourseAlreadyExistsException {
        Department department = departmentService.getOrCreateDepartment(course.getDepartment().getName());
        course.setDepartment(department);
        return courseService.addCourse(course);
    }


    /**
     * Removes a course from the database.
     *
     * @param courseCode course code for the course to remove from the database
     * @throws CourseNotFoundException if there is no course in the database with the given course code
     */
    public void removeCourse(String courseCode) throws CourseNotFoundException {
        courseService.removeCourse(courseCode);
    }


    /**
     * Starts and finishes the semester.
     *
     * @param isStarted true to start the semester, false to end the semester
     */
    public void startSemester(boolean isStarted) {
        Semester.setSemesterStarted(isStarted);
    }


    /**
     * Starts and finishes the evaluation.
     *
     * @param isStarted true to start, false to end
     */
    public void startEvaluation(boolean isStarted) {
        Semester.setEvaluationStarted(isStarted);
    }


    /**
     * Starts and finishes add or drop.
     *
     * @param isStarted true to start, false to end
     */
    public void startAddOrDrop(boolean isStarted) {
        Semester.setAddOrDropStarted(isStarted);
    }


    /**
     * Returns the semester info.
     *
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
     *
     * @param userId            admin user id
     * @param userUpdateRequest updated user attributes
     * @return updated admin
     */
    public Admin updateAdmin(long userId, UserUpdateRequest userUpdateRequest) throws UserNotFoundException {
        Admin admin = getAdminByUserId(userId);
        /*admin.setFirstName(userUpdateRequest.getFirstName());
        admin.setLastName(userUpdateRequest.getLastName());
        admin.setUsername(admin.getUsername());
        admin.setPassword(admin.getPassword());*/
        userService.updateUser(admin, userUpdateRequest);
        adminRepository.save(admin);
        return admin;
    }

    /**
     * Admits the student.
     *
     * @param userId student user id
     * @param admit  true to admit, false to reject
     * @return updated student
     * @throws UserNotFoundException if there is no student with the given id
     */
    public boolean admitStudent(long userId, boolean admit) throws UserNotFoundException {
        return studentService.admitStudent(userId, admit);
    }


    /**
     * Creates the first admin object
     *
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
        admin.setUserId(0L);
        adminRepository.save(admin);
        userService.saveUser(admin);
        return true;
    }


    /**
     * Finds the admin with the given user id.
     *
     * @param userId admin user id
     * @return the admin with the given user id
     * @throws UserNotFoundException if there is no admin with the given user id
     */
    private Admin getAdminByUserId(long userId) throws UserNotFoundException {
        return adminRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Admin not found. User id: " + userId));
    }


    /**
     * Creates an instructor/departmentManager
     *
     * @param instructorRegisterRequest the user register request
     */
    public void createInstructor(InstructorRegisterRequest instructorRegisterRequest) throws UserExistsException {

        String username = instructorRegisterRequest.getUsername();
//        if (userService.existsByUsername(username)) {
//            throw new UserExistsException("User exists with this username");
//        }

        boolean isDepartmentManager = instructorRegisterRequest.isDepartmentManager();
        Department department = departmentService.getOrCreateDepartment(instructorRegisterRequest.getDepartment().getName());

        if (isDepartmentManager) {
            DepartmentManager departmentManager = new DepartmentManager();
            departmentManager.setDepartment(department);
            departmentManager.setFirstName(instructorRegisterRequest.getFirstName());
            departmentManager.setLastName(instructorRegisterRequest.getLastName());
            departmentManager.setUsername(username);
            departmentManager.setPassword(instructorRegisterRequest.getPassword());
            departmentManager.setCourses(instructorRegisterRequest.getCourses());
//            departmentManager.setUserId(5L);
            instructorService.saveInstructor(departmentManager);
            departmentManagerService.saveDepartmentManager(departmentManager);
        } else {
            Instructor instructor = new Instructor();
            instructor.setDepartment(department);
            instructor.setFirstName(instructorRegisterRequest.getFirstName());
            instructor.setLastName(instructorRegisterRequest.getLastName());
            instructor.setUsername(username);
            instructor.setPassword(instructorRegisterRequest.getPassword());
            instructor.setCourses(instructorRegisterRequest.getCourses());
//            instructor.setUserId(10L);
            instructorService.saveInstructor(instructor);
//            instructorRepository.save(instructor);
        }
    }


    /* ADD SOME EXAMPLES TO THE DATABASE */
//    public void addExamplesToDatabase() {
//
//        Department computerEngineering = new Department();
//        computerEngineering.setName("Computer Engineering");
//
//        DepartmentManager departmentManager = new DepartmentManager();
//        departmentManager.setFirstName("Department Manager Firstname");
//        departmentManager.setLastName("Department Manager Lastname");
//        departmentManager.setUsername("department-manager-username");
//        departmentManager.setPassword("department-manager-password");
//        departmentManager.setDepartment(computerEngineering);
//        departmentManager.setUserId(500);
//        departmentManager.setCourses(new ArrayList<>());
//        instructorService.saveInstructor(departmentManager);
//        departmentManagerService.saveDepartmentManager(departmentManager);
//
//
//        Instructor instructor = new Instructor();
//        instructor.setFirstName("Instructor Firstname");
//        instructor.setLastName("Instructor Lastname");
//        instructor.setDepartment(computerEngineering);
//        instructor.setCourses(new ArrayList<>());
//        instructor.setUsername("instructor-username");
//        instructor.setPassword("instructor-password");
//        instructor.setUserId(501);
//        instructorService.saveInstructor(instructor);
//
//
//        /* Create 10 students */
//        for (int i = 1; i < 11; i++) {
//            Student student = new Student();
//            student.setUserId(i);
//            student.setFirstName("Student" + i + " Firstname");
//            student.setLastName("Student" + i + " Lastname");
//            student.setUsername("student" + i + "username");
//            student.setPassword("student" + i + "password");
//            student.setStudentId(i * 10);
//            student.setDepartment(computerEngineering);
//            student.setCourses(new ArrayList<>());
//            student.setAccountRegistered(true);
//            studentRepository.save(student);
//            userRepository.save(student);
//        }
//
//        /* Create 99 courses */
//        for (int i = 300; i < 400; i++) {
//            Course course = new Course();
//            course.setId(i);
//            course.setCourseCode("BBM" + i);
//            course.setDepartment(computerEngineering);
//            course.setCourseType(CourseType.UNDERGRADUATE);
//            course.setName("Introduction to BBM" + i);
//            courseRepository.save(course);
//        }
//
//    }


    /**
     * Returns a list of instructors in the database.
     *
     * @return ResponseEntity containing a list of instructors
     */
    public List<Instructor> getInstructors() {
        return instructorService.getAllInstructors();
    }

}
