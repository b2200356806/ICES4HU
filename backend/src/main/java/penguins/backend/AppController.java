package penguins.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/admin/manageSemester.html")
    public String viewHomePage() {return "admin/manageSemester";}

    @GetMapping("/admin/addCourse.html")
    public String viewCourse() {return "admin/addCourse";}

    @GetMapping("/admin/homePage.html")
    public String viewHome() {return "admin/homePage";}

    @GetMapping("/student/homePage.html")
    public String viewStudentHome() {return "student/homePage";}

    @GetMapping("/student/enrollCourses.html")
    public String viewStudentCourses() {return "student/enrollCourses";}

}
