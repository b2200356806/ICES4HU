package penguins.backend.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penguins.backend.Course.Course;
import penguins.backend.Course.Exception.CourseAlreadyExistsException;
import penguins.backend.Course.Exception.CourseNotFoundException;
import penguins.backend.Semester.SemesterDto;
import penguins.backend.User.Exception.UserNotFoundException;
import penguins.backend.User.UserUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/admin/{userId}")
public class AdminController {


    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Returns a list of courses in the database.
     * @return ResponseEntity containing a list of courses
     */
    @GetMapping(path = "/courses")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = adminService.getCourses();
        return ResponseEntity.ok(courses);
    }


    /**
     * Adds a course to the database.
     * @param course the course to add to the database
     * @return ResponseEntity containing the created course
     */
    @PostMapping(path = "/courses/add")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        try {
            Course createdCourse = adminService.addCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (CourseAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already exists");
        }
    }


    /**
     * Removes a course from the database.
     * @param course Code the course code of the course to remove from the database
     * @return ResponseEntity indicating if the course was removed
     */
    @PostMapping(path = "/courses/remove")
    public ResponseEntity<String> removeCourse(@RequestBody Course course) {
        try {
            adminService.removeCourse(course.getCourseCode());
            return ResponseEntity.ok("Course removed successfully");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course does not exist");
        }
    }


    /**
     * Starts the semester.
     * @return ResponseEntity containing HttpStatus OK
     */
    @PostMapping(path = "/semester/start")
    public ResponseEntity<String> startSemester() {
        adminService.startSemester(true);
        return ResponseEntity.ok("Semester started successfully");
    }


    /**
     * Finishes the semester.
     * @return ResponseEntity containing HttpStatus OK
     */
    @PostMapping(path = "/semester/finish")
    public ResponseEntity<String> finishSemester() {
        adminService.startSemester(false);
        return ResponseEntity.ok("Semester finished successfully");
    }


    /**
     * Starts the evaluation.
     * @return ResponseEntity containing HttpStatus OK
     */
    @PostMapping(path = "/evaluation/start")
    public ResponseEntity<String> startEvaluation() {
        adminService.startEvaluation(true);
        return ResponseEntity.ok("Evaluation started successfully");
    }


    /**
     * Finishes the evaluation.
     * @return ResponseEntity containing HttpStatus OK
     */
    @PostMapping(path = "/evaluation/finish")
    public ResponseEntity<String> finishEvaluation() {
        adminService.startEvaluation(false);
        return ResponseEntity.ok("Evaluation finished successfully");
    }



    /**
     * Starts add or drop.
     * @return ResponseEntity containing HttpStatus OK
     */
    @PostMapping(path = "/add-or-drop/start")
    public ResponseEntity<String> startAddOrDrop() {
        adminService.startAddOrDrop(true);
        return ResponseEntity.ok("Add/Drop started successfully");
    }


    /**
     * Finishes add or drop.
     * @return ResponseEntity containing HttpStatus OK
     */
    @PostMapping(path = "/add-or-drop/finish")
    public ResponseEntity<String> finishAddOrDrop() {
        adminService.startAddOrDrop(false);
        return ResponseEntity.ok("Add/Drop finished successfully");
    }



    /**
     * Indicates whether the semester is started.
     * @return ResponseEntity containing the semesterDto
     */
    @GetMapping(path = "/semester")
    public ResponseEntity<SemesterDto> getSemester() {
        return ResponseEntity.ok(adminService.getSemester());
    }


    /**
     * Updates the attributes of the user
     * @param userId admin user id
     * @param userUpdateRequest updated user attributes
     * @return updated admin
     */
    @PostMapping(path = "/update-info")
    public ResponseEntity<?> updateAdmin(@PathVariable long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            Admin admin = adminService.updateAdmin(userId, userUpdateRequest);
            AdminDto adminDto = adminToAdminDto(admin);
            return ResponseEntity.ok(adminDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /**
     * Admits the student.
     * @param studentId student user id
     * @return updated student
     */
    @PostMapping("/admit/{studentId}")
    public ResponseEntity<Boolean> admitStudent(long studentId) {
        try {
            Boolean output = adminService.admitStudent(studentId, true);
            return ResponseEntity.ok(output);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    /**
     * Creates the first admin object
     * @return ResponseEntity containing true if new object created, false otherwise
     */
    @PostMapping("/create-first-admin")
    public ResponseEntity<Boolean> createFirstAdmin() {
        boolean output = adminService.createFirstAdmin();
        if (output) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }


    private AdminDto adminToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setUsername(admin.getUsername());
        adminDto.setUserId(admin.getUserId());
        return adminDto;
    }


    @PostMapping("/add-examples")
    public ResponseEntity<String> addExamples() {
        adminService.addExamplesToDatabase();
        return ResponseEntity.status(HttpStatus.CREATED).body("Examples created successfully");
    }


}
