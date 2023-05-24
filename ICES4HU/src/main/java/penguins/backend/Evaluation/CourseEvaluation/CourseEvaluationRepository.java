package penguins.backend.Evaluation.CourseEvaluation;

import org.springframework.stereotype.Component;
import penguins.backend.Course.Course;
import penguins.backend.Student.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseEvaluationRepository {
    private final List<CourseEvaluation> courseEvaluations = new ArrayList<>();


    public List<CourseEvaluation> findByCourse(Course course) {
        List<CourseEvaluation> output = new ArrayList<>();
        for (CourseEvaluation courseEvaluation : courseEvaluations) {
            if (courseEvaluation.getCourse().equals(course)) {
                output.add(courseEvaluation);
            }
        }
        return output;
    }


    public boolean existsByCourseAndStudent(Course course, Student student) {
        for (CourseEvaluation courseEvaluation : courseEvaluations) {
            if (courseEvaluation.getCourse().equals(course) && courseEvaluation.getStudent().equals(student)) {
                return true;
            }
        }
        return false;
    }

    public CourseEvaluation save(CourseEvaluation courseEvaluation) {
        courseEvaluations.remove(courseEvaluation);
        courseEvaluations.add(courseEvaluation);
        return courseEvaluation;
    }

}
