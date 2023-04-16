package penguins.backend.Admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penguins.backend.Course.Course;
import penguins.backend.Course.Exception.CourseAlreadyExistsException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
     * Indicates whether the semester is started.
     * @return ResponseEntity containing true if the semester is started, false otherwise
     */
    @GetMapping(path = "/semester/status")
    public ResponseEntity<Boolean> getSemesterStatus() {
        return ResponseEntity.ok(adminService.getSemesterStatus());
    }



    @PostMapping("/add-examples")
    public ResponseEntity<String> addExamples() {
        adminService.addExamplesToDatabase();
        return ResponseEntity.status(HttpStatus.CREATED).body("Examples created successfully");
    }


}
