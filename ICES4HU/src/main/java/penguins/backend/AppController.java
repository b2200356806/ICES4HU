package penguins.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/admin/manageSemester")
    public String viewHomePage() {
        return "admin/manageSemester";
    }

    @GetMapping("/admin/addCourse")
    public String viewCourse() {
        return "admin/addCourse";
    }

    @GetMapping("/admin/homePage")
    public String viewHome() {
        return "admin/homePage";
    }

    @GetMapping("/admin/createInstAcc")
    public String viewInstAccCreate() {
        return "admin/createInstAcc";
    }

    @GetMapping("/admin/manageAccount")
    public String viewAcc() {
        return "admin/manageAccount";
    }

    @GetMapping("/admin/regRequest")
    public String viewReg() {
        return "admin/regRequest";
    }

    @GetMapping("/student/homePage")
    public String viewStudentHome() {
        return "student/homePage";
    }

    @GetMapping("/student/enrollCourses")
    public String viewStudentCourses() {
        return "student/enrollCourses";
    }

    @GetMapping("/student/manageAccount")
    public String viewStuAcc() {
        return "student/manageAccount";
    }

    @GetMapping("/student/evaluate")
    public String viewStuEval() {
        return "student/evaluate";
    }

    @GetMapping("/department_manager/assignInst")
    public String viewAssign() {
        return "department_manager/assignInst";
    }

    @GetMapping("/department_manager/createSurvey")
    public String viewSurvey() {
        return "department_manager/createSurvey";
    }

    @GetMapping("/department_manager/homePage")
    public String viewDMHome() {
        return "department_manager/homePage";
    }

    @GetMapping("/department_manager/manageAccount")
    public String viewDMAcc() {
        return "department_manager/manageAccount";
    }

    @GetMapping("/department_manager/viewResult")
    public String viewResult() {
        return "department_manager/viewResult";
    }

    @GetMapping("/department_manager/createDefault")
    public String viewDefault() {
        return "department_manager/createDefault";
    }

    @GetMapping("/instructor/createSurvey")
    public String viewInsSurvey() {
        return "instructor/createSurvey";
    }

    @GetMapping("/instructor/viewResult")
    public String viewInsResult() {
        return "instructor/viewResult";
    }

    @GetMapping("/instructor/homePage")
    public String viewInsHome() {
        return "instructor/homePage";
    }

    @GetMapping("/instructor/manageAccount")
    public String viewInsAcc() {
        return "instructor/manageAccount";
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login/login";
    }

    @GetMapping("/homepage")
    public String viewDefaultHome() {
        return "login/defaultHomepage";
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "login/register";
    }
}