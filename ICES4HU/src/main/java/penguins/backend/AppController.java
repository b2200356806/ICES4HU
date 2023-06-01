package penguins.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/admin/manageSemester")
    public String viewHomePage() {return "admin/manageSemester";}

    @GetMapping("/admin/addCourse")
    public String viewCourse() {return "admin/addCourse";}

    @GetMapping("/admin/homePage")
    public String viewHome() {return "admin/homePage";}

    @GetMapping("/student/homePage")
    public String viewStudentHome() {return "student/homePage";}

    @GetMapping("/student/enrollCourses")
    public String viewStudentCourses() {return "student/enrollCourses";}

}
